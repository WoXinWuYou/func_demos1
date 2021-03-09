package com.lmj.entity;

/**
 * 耗时记录
 * @author lmj
 *
 */
public class Timer {
	private Long startInMillis = System.currentTimeMillis(); // 开始操作时间（毫秒）
	private Long stageInMillis = System.currentTimeMillis(); // 阶段开始计时（毫秒）
	private Long redisInMillis = 0L; // redis操作时间（毫秒）
	private Long mysqlInMillis = 0L; // mysql操作时间（毫秒）
	private Long mongodbInMillis = 0L; // mongodb操作时间（毫秒）
	private Long otherInMillis = 0L; // 其他操作1时间（毫秒）
	
	private int redisSize = 0; //redis操作计数
	private int mysqlSize = 0; //mysql操作计数
	private int mongodbSize = 0; //mongodb操作计数
	
	/**
	 * 阶段计时
	 */
	public void stageTime() {
		stageInMillis = System.currentTimeMillis();
	}
	
	/**
	 * redis累积计时
	 */
	public void redisTime() {
		redisInMillis += ( System.currentTimeMillis()-stageInMillis );
		stageInMillis = System.currentTimeMillis();
	}
	/**
	 * mysql累积计时
	 */
	public void mysqlTime() {
		mysqlInMillis += ( System.currentTimeMillis()-stageInMillis );
		stageInMillis = System.currentTimeMillis();
	}
	/**
	 * mongodb累积计时
	 */
	public void mongodbTime() {
		mongodbInMillis += ( System.currentTimeMillis()-stageInMillis );
		stageInMillis = System.currentTimeMillis();
	}
	/**
	 * other累积计时
	 */
	public void otherTime() {
		otherInMillis += ( System.currentTimeMillis()-stageInMillis );
		stageInMillis = System.currentTimeMillis();
	}
	
	/**
	 * redis操作数据量累加
	 * @return
	 */
	public int addRedisSize(int size) {
		redisSize += size;
		return redisSize;
	}
	/**
	 * mysql操作数据量累加
	 * @return
	 */
	public int addMysqlSize(int size) {
		mysqlSize += size;
		return mysqlSize;
	}
	/**
	 * mongodb操作数据量累加
	 * @return
	 */
	public int addMongodbSize(int size) {
		mongodbSize += size;
		return mongodbSize;
	}
	
	public Double getRedisInSeconds() {
		return redisInMillis/1000.0;
	}
	public Double getMysqlInSeconds() {
		return mysqlInMillis/1000.0;
	}
	public Double getMongodbInSeconds() {
		return mongodbInMillis/1000.0;
	}
	public Double getOtherInSeconds() {
		return otherInMillis/1000.0;
	}
	
	public Long stageUseInMillis() {
		Long stageUse = System.currentTimeMillis() - stageInMillis;
		stageInMillis = System.currentTimeMillis();
		return stageUse;
	}
	public Double stageUseInSeconds() {
		Double stageUse = (System.currentTimeMillis() - stageInMillis) / 1000.0;
		stageInMillis = System.currentTimeMillis();
		return stageUse;
	}
	
	public Long totalInMillis() {
		return System.currentTimeMillis() - startInMillis;
	}
	public Double totalInSeconds() {
		stageInMillis = System.currentTimeMillis();
		return (System.currentTimeMillis() - startInMillis) / 1000.0;
	}
	
	public Long getStartInMillis() {
		return startInMillis;
	}
	public void setStartInMillis(Long startInMillis) {
		this.startInMillis = startInMillis;
	}
	public Long getStageInMillis() {
		return stageInMillis;
	}
	public void setStageInMillis(Long stageInMillis) {
		this.stageInMillis = stageInMillis;
	}
	public Long getRedisInMillis() {
		return redisInMillis;
	}
	public void setRedisInMillis(Long redisInMillis) {
		this.redisInMillis = redisInMillis;
	}
	public Long getMysqlInMillis() {
		return mysqlInMillis;
	}
	public void setMysqlInMillis(Long mysqlInMillis) {
		this.mysqlInMillis = mysqlInMillis;
	}
	public Long getMongodbInMillis() {
		return mongodbInMillis;
	}
	public void setMongodbInMillis(Long mongodbInMillis) {
		this.mongodbInMillis = mongodbInMillis;
	}
	public Long getOtherInMillis() {
		return otherInMillis;
	}
	public void setOtherInMillis(Long otherInMillis) {
		this.otherInMillis = otherInMillis;
	}

	public int getRedisSize() {
		return redisSize;
	}

	public void setRedisSize(int redisSize) {
		this.redisSize = redisSize;
	}

	public int getMysqlSize() {
		return mysqlSize;
	}

	public void setMysqlSize(int mysqlSize) {
		this.mysqlSize = mysqlSize;
	}

	public int getMongodbSize() {
		return mongodbSize;
	}

	public void setMongodbSize(int mongodbSize) {
		this.mongodbSize = mongodbSize;
	}
}
