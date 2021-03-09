package com.lmj.utils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 事件记录提示器 事件：一段时间内出现事件多少次后为一次提示，持续多长时间内不重复提示 功能描述：指定统计周期，次数限制，可自动判断是否提示并更新事件记录
 * 缺陷：没有自动清理功能，会一直存在于内存中；可以定时清理（或者其他条件满足时清理，如当队列尺寸过大时），最新访问时间远大于当前时间的队列；
 * TODO：配合redis 缓存记录结果 使用示例：
 * 	应给提供额外信息记录
 * TODO 示例编写
 * 
 * @author 李梦杰
 * @date 2020-10-29
 */
public class EventRecordingPrompter implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long statisticalPeriodInMillis; // 统计周期
	private int maxAllowableTimes; // 最大不提示允许事件次数，最大允许次数为-1或0，表示不允许出现
	private Long unpromptedIntervalInMillis; // 不重复提示间隔

	Map<String, Queue<Long>> eventTimeRecorders = new HashMap<String, Queue<Long>>(); // 有效事件记录<事件源标识，事件时间队列>
	Map<String, Long> lastPromptedTimeInMilliss = new HashMap<String, Long>(); // 上次提示时间<事件源标识，提示时间> 问题：提示时间是用那个时间？（当前时间）
	
	/**
	 * 何时使用，何时维护？
	 * 每次事件发生时的
	 */
	/*Map<String, Object> messageRecoders = new HashMap<String, Object>(); // 信息记录<事件源标识，提示时间>，按需使用（不提供使用支持）
	*/
	
	/**
	 * 无参构造方法，反序列化时使用
	 */
	public EventRecordingPrompter() {
		
	}
	/**
	 * @param statisticalPeriodInMillis  统计周期
	 * @param timesLimit                 次数限制
	 * @param unpromptedIntervalInMillis 不重复提示间隔
	 */
	public EventRecordingPrompter(Long statisticalPeriodInMillis, int timesLimit, Long unpromptedIntervalInMillis) {
		super();
		this.statisticalPeriodInMillis = statisticalPeriodInMillis;
		this.maxAllowableTimes = timesLimit;
		this.unpromptedIntervalInMillis = unpromptedIntervalInMillis;
	}

	/**
	 * 刷新 事件记录提示器设置
	 * 统计周期变化：（重置）
	 * 最大不提示允许事件次数：（重置），维持eventTimeRecorder长度小于等于该值；
	 * 不重复提示间隔：重置；
	 */
	public void refresh(Long statisticalPeriodInMillis, int timesLimit, Long unpromptedIntervalInMillis) {
		this.statisticalPeriodInMillis = statisticalPeriodInMillis;
		this.unpromptedIntervalInMillis = unpromptedIntervalInMillis;
		if(this.maxAllowableTimes != timesLimit) {
			this.maxAllowableTimes = timesLimit;
			eventTimeRecorders.forEach((eventSourceId, eventTimeQueue) ->{
				int restNum = eventTimeQueue.size() - (timesLimit<0 ? 0 : timesLimit);
				if(restNum > 0) {
					while (restNum > 0) {
						eventTimeQueue.poll(); // 移除首元素
						restNum--;
					}
				}
			});
		}
	}
	
	/**
	 * 是否需要判断事件发生
	 * 
	 * @param eventSourceId 事件源标识
	 * @return 当距上次提示时间间隔大于 不重复提示时间间隔限制时 需要判断
	 */
	public boolean isNeededJudge(String eventSourceId) {
		// 获取上次提示时间
		Long lastPromptedTimeInMillis = lastPromptedTimeInMilliss.get(eventSourceId);
		Long curEventTimeInMillis = Calendar.getInstance().getTimeInMillis();
		
		/*Date tempCurHour = DateUtils.truncate(new Date(), Calendar.HOUR);
		Date curHour = DateUtils.addHours(tempCurHour, -6);
		Long curEventTimeInMillis = curHour.getTime();*/
		
		if (lastPromptedTimeInMillis == null || curEventTimeInMillis - lastPromptedTimeInMillis > unpromptedIntervalInMillis) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 事件记录
	 * 
	 * @param eventSourceId 事件源标识
	 * @return 是否需要提示
	 */
	public boolean eventRecord(String eventSourceId) {
		boolean isNeededPrompt = false; // 是否需要提示
		Queue<Long> eventTimeQueue = getEventTimeQueue(eventSourceId);
		// 判断累积事件次数是否超过限制
		Long curEventTimeInMillis = Calendar.getInstance().getTimeInMillis();
		
		/*Date tempCurHour = DateUtils.truncate(new Date(), Calendar.HOUR);
		Date curHour = DateUtils.addHours(tempCurHour, -6);
		Long curEventTimeInMillis = curHour.getTime();*/
		
		if(maxAllowableTimes < 1) { // 最大允许次数为-1或0，表示不允许出现
			// 维护提示记录
			lastPromptedTimeInMilliss.put(eventSourceId, curEventTimeInMillis);
			return true;
		}
		int curRecordEventTimes = eventTimeQueue.size();
		if(curRecordEventTimes >= maxAllowableTimes) { // 当已记录事件次数大于等于周期内限制事件次数时
			Long earliestEventTimeInMillsLong = eventTimeQueue.peek(); // 获取最早事件时间
			
			if(earliestEventTimeInMillsLong != null && (curEventTimeInMillis - earliestEventTimeInMillsLong) <= statisticalPeriodInMillis) {
				isNeededPrompt = true; // 周期内事件次数超过限制，清空所有事件时间，重新记录
				eventTimeQueue.clear(); // 清空所有记录
				// 维护提示记录
				lastPromptedTimeInMilliss.put(eventSourceId, curEventTimeInMillis);
			}else {
				eventTimeQueue.poll(); // 移除首元素
				eventTimeQueue.offer(curEventTimeInMillis); // 非频繁访问
			}
		}else { // 事件时间入队列
			eventTimeQueue.offer(curEventTimeInMillis); // 非频繁访问
		}
		return isNeededPrompt;
	}

	private Queue<Long> getEventTimeQueue(String eventSourceId) {
		Queue<Long> eventTimeQueue = eventTimeRecorders.get(eventSourceId);
		if (eventTimeQueue == null) {
			// 未找到，添加并返回
			eventTimeQueue = new LinkedList<Long>();
			eventTimeRecorders.put(eventSourceId, eventTimeQueue);
		}
		return eventTimeQueue;
	}

	
	
	public Long getStatisticalPeriodInMillis() {
		return statisticalPeriodInMillis;
	}

	public void setStatisticalPeriodInMillis(Long statisticalPeriodInMillis) {
		this.statisticalPeriodInMillis = statisticalPeriodInMillis;
	}

	public Long getUnpromptedIntervalInMillis() {
		return unpromptedIntervalInMillis;
	}

	public void setUnpromptedIntervalInMillis(Long unpromptedIntervalInMillis) {
		this.unpromptedIntervalInMillis = unpromptedIntervalInMillis;
	}

	public Map<String, Queue<Long>> getEventTimeRecorders() {
		return eventTimeRecorders;
	}

	public void setEventTimeRecorders(Map<String, Queue<Long>> eventTimeRecorders) {
		this.eventTimeRecorders = eventTimeRecorders;
	}

	public Map<String, Long> getLastPromptedTimeInMilliss() {
		return lastPromptedTimeInMilliss;
	}

	public void setLastPromptedTimeInMilliss(Map<String, Long> lastPromptedTimeInMilliss) {
		this.lastPromptedTimeInMilliss = lastPromptedTimeInMilliss;
	}
	public int getMaxAllowableTimes() {
		return maxAllowableTimes;
	}
	public void setMaxAllowableTimes(int maxAllowableTimes) {
		this.maxAllowableTimes = maxAllowableTimes;
	}
	/*public Map<String, Object> getMessageRecoders() {
		return messageRecoders;
	}
	public void setMessageRecoders(Map<String, Object> messageRecoders) {
		this.messageRecoders = messageRecoders;
	}*/
}
