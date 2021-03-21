package com.lmj.zhangyunkai.base.response;

import com.lmj.zhangyunkai.base.SYSConstant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务代码枚举
 *
 * @author framework
 */
@Getter
@AllArgsConstructor
public enum ResultCode implements IResultCode {


	/**
	 * 操作成功 1
	 */
	SUCCESS(SYSConstant.DEFAULT_SUCCESS_CODE, SYSConstant.DEFAULT_SUCCESS_MESSAGE),

	/**
	 * 操作失败 99
	 */
	FAILURE(SYSConstant.DEFAULT_FAILURE_CODE, SYSConstant.DEFAULT_FAILURE_MESSAGE),

	/**
	 * 需要登录 999
	 */
	NEED_LOGIN(SYSConstant.NEED_LOGIN_CODE, SYSConstant.NEED_LOGIN_MESSAGE),

	/**
	 * 请求没有权限 909
	 */
	UN_AUTHORIZED(SYSConstant.UN_AUTHORIZED_CODE, SYSConstant.UN_AUTHORIZED_MESSAGE);

	/**
	 * code编码
	 */
	final int status;
	/**
	 * 中文信息描述
	 */
	final String statusDesc;

}
