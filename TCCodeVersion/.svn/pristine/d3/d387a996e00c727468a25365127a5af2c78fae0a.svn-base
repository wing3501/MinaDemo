/**
 * 
 */
package com.tccv.constants;

/**
 * 
 * 此类描述的是：   用户版错误信息
 * @author: zengfanlin   
 * @version: 2016年2月26日 上午10:11:06
 */
public enum UserErrorCode
{
	SUCCESS("0", "成功", "success"),
	ERROR("-9999", "出错啦", "error"),
	LOCATION_FAIL("-8", "经纬度信息缺失", "location fail"),
	NONE_USER("-1", "用户不存在", "user　not exist"),
	EXIST_USER("-2", "用户已存在", "user exist"),
	EMPTY_PASSWORD("-3", "密码为空", "password is empty"),
	EMPTY_VERIFYCODE("-4", "验证码为空", "verify code is empty"),
	EMPTY_MOBILE("-5", "手机号为空", "mobile is empty"),
	ERROR_VERIFYCODE("-6", "验证码不匹配", "verify code is error"),
	ERROR_PASSWORD("-7", "用户名或密码错误", "mobile or password is error"),
	ERROR_PWD("-8", "密码错误", "password is error"),
	NONE_MEMBER("-9", "您还没有会员", "none member"),
	WAIT_CHECK("-10", "您的账号还在等待审核", "wait check"),
	CHECK_FAIL("-11", "您的账号审核不通过", "check fail"),
	EMPTY_USERNAME("-12", "用户名为空", "password is empty");
	/** 错误码. */
	private String errorCode;
	
	/** 中文描述. */
	private String zhMessage;
	
	/** 英文描述. */
	private String enMessage;
	
	/**
	 * The Constructor.
	 *
	 * @param errorCode the error code
	 * @param zhMessage the zh message
	 * @param enMessage the en message
	 */
	private UserErrorCode(String errorCode, String zhMessage, String enMessage)
	{
		this.errorCode = errorCode;
		this.zhMessage = zhMessage;
		this.enMessage = enMessage;
	}
	
	/**
	 * The Constructor.
	 *
	 * @param errorCode the error code
	 * @param zhMessage the zh message
	 */
	private UserErrorCode(String errorCode, String zhMessage)
	{
		this.errorCode = errorCode;
		this.zhMessage = zhMessage;
	}
	
	/**
	 * The Constructor.
	 *
	 * @param errorCode the error code
	 */
	private UserErrorCode(String errorCode)
	{
		this.errorCode = errorCode;
	}
	
	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public String getErrorCode()
	{
		return errorCode;
	}
	
	/**
	 * Sets the error code.
	 *
	 * @param errorCode the error code
	 */
	public void setErrorCode(String errorCode)
	{
		this.errorCode = errorCode;
	}
	
	/**
	 * Gets the zh message.
	 *
	 * @return the zh message
	 */
	public String getZhMessage()
	{
		return zhMessage;
	}
	
	/**
	 * Sets the zh message.
	 *
	 * @param zhMessage the zh message
	 */
	public void setZhMessage(String zhMessage)
	{
		this.zhMessage = zhMessage;
	}
	
	/**
	 * Gets the en message.
	 *
	 * @return the en message
	 */
	public String getEnMessage()
	{
		return enMessage;
	}
	
	/**
	 * Sets the en message.
	 *
	 * @param enMessage the en message
	 */
	public void setEnMessage(String enMessage)
	{
		this.enMessage = enMessage;
	}
}