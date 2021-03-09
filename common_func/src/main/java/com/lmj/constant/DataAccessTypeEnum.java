package com.lmj.constant;

/**
 * @author 李梦杰
 * @date 2020-5-14
 * 数据接入类型
 */
public enum DataAccessTypeEnum {
	/**定时同步*/
	TIMING_SYNCHRONIZATION("0"),
	/**手动补录*/
	ADDITIONAL_RECORDING("1");
	
	public final String code; // 代码
	
	DataAccessTypeEnum(String code){
		this.code = code;
	}
}
