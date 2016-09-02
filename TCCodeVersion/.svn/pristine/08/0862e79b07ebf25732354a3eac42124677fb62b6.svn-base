/**
 * 
 */
package com.tccv.constants;


/**   
 * 此类描述的是：   
 * @author: styf   
 * @version: 2016年4月18日 上午10:59:05    
 */
public enum ErrorCode
{
	SUCCESS("0", "成功", "success"),
	ERROR("-9999", "出错啦", "error"),
	PARAM_ERROR("-1", "请求参数错误:", "param error"),
	NOTEXIST("-2", "版本不存在", "not exist"),
	APP_EXIST("-3", "项目已存在:", "app exists"),
	OSS_UPLOAD_ERROR("-4", "oss上传失败", "oss upload error"),
	FILE_FORMAT_ERROR("-5", "文件格式错误", "file format error"),
	NOT_RELEASED("-6", "该版本还未发布", "this version is not released"),
	
	ERROR_PASSWORD("-7", "用户名或密码错误", "mobile or password is error"),
	ERROR_PWD("-8", "密码错误", "password is error"),
	NONE_MEMBER("-9", "您还没有会员", "none member"),
	WAIT_CHECK("-10", "您的账号还在等待审核", "wait check"),
	CHECK_FAIL("-11", "您的账号审核不通过", "check fail"),
	EMPTY_USERNAME("-12", "用户名为空", "password is empty"),
	E999999999("999999999", "未知错误", "unknown error");
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
	private ErrorCode(String errorCode, String zhMessage, String enMessage)
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
	private ErrorCode(String errorCode, String zhMessage)
	{
		this.errorCode = errorCode;
		this.zhMessage = zhMessage;
	}
	
	/**
	 * The Constructor.
	 *
	 * @param errorCode the error code
	 */
	private ErrorCode(String errorCode)
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
