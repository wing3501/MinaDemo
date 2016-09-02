package com.tccv.core.util.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 短信发送工具
 * 
 * @author niujh
 * 		
 */
public class SmsUtils
{
	
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(SmsUtils.class);
	
	/**
	 * Send2.
	 *
	 * @param mobile the mobile
	 * @param smsContent the sms content
	 * @return true, if send2
	 */
	public static boolean send(String mobile, String smsContent)
	{
		
		BufferedReader in = null;
		String result = "";
		OutputStream out = null;
		try
		{
			// http://121.40.133.21:8082/SendSms.aspx?uid=test&pass=123456&sjh=13071841785
			// &memo=内容测试&ftype=1&sendtime=2011-11-11 10:10:10
			String gbkString = smsContent;
			String urlvalue = SmsConfigHolder.getSmsUrl();
			StringBuilder params = new StringBuilder();
			params.append("uid=" + SmsConfigHolder.getSmsUid());
			params.append("&pass=" + SmsConfigHolder.getSmsPwd());
			params.append("&sjh=" + mobile);
			params.append("&memo=" + gbkString + " " + SmsConfigHolder.getSmsDomain());
			params.append("&ftype=1");
			params.append("&sendtime=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			URL url = new URL(urlvalue);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod(SmsConstants.Method.POST);
			// 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
			urlConnection.connect();
			// post信息 ,这步很重要,不然就乱码
			out = urlConnection.getOutputStream();
			out.write(params.toString().getBytes(SmsConstants.ENCODE.GB2312));
			
			// // 获取该动态链接响应的状态码
			int iHttpResult = urlConnection.getResponseCode();
			
			in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null)
			{
				result += line;
			}
			System.out.println(result);
			// 判断该动态链接的响应是否能正确连接
			if (iHttpResult == HttpURLConnection.HTTP_OK && StringUtils.isNotBlank(result) && result.contains("|"))
			{
				return true;// urlvalue + SmsConstants.FALSE;
			}
			else
			{
				logger.error("网络连接错误,{}", iHttpResult);
				return false;
			}
		}
		catch (Exception e)
		{
			logger.error("send sms fail:", e);
		}
		finally
		{
			try
			{
				if (out != null)
				{
					out.close();
				}
				if (in != null)
				{
					in.close();
				}
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * 发送短信通知.
	 *
	 * @param mobile 手机号
	 * @param smsContent 短信内容
	 * @return true：短信发送成功
	 */
	public static boolean sendSMS(String mobile, String smsContent)
	{
		StringBuilder params = new StringBuilder();
		params.append("uid=" + SmsConfigHolder.getSmsUid());
		params.append("&pass=" + SmsConfigHolder.getSmsPwd());
		params.append("&sjh=" + mobile);
		params.append("&memo=" + smsContent);
		params.append("&ftype=1");
		params.append("&sendtime=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		return sendSMS(params);
	}
	
	/**
	 * Send msg.
	 *
	 * @param params the params
	 */
	private static boolean sendSMS(StringBuilder params)
	{
		BufferedReader in = null;
		String result = "";
		OutputStream out = null;
		try
		{
			URL url = new URL(SmsConfigHolder.getSmsUrl());
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod(SmsConstants.Method.POST);
			// 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
			urlConnection.connect();
			// post信息 ,这步很重要,不然就乱码
			out = urlConnection.getOutputStream();
			out.write(params.toString().getBytes(SmsConstants.ENCODE.GB2312));
			
			// 获取该动态链接响应的状态码
			int iHttpResult = urlConnection.getResponseCode();
			in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null)
			{
				result += line;
			}
			// 判断该动态链接的响应是否能正确连接
			if (iHttpResult == HttpURLConnection.HTTP_OK && StringUtils.isNotBlank(result) && result.contains("|"))
			{
				return true;
			}
			else
			{
				logger.error("网络连接错误,{}", iHttpResult);
				return false;
			}
		}
		catch (Exception e)
		{
			logger.error("send sms fail:", e);
		}
		finally
		{
			try
			{
				if (out != null)
				{
					out.close();
				}
				if (in != null)
				{
					in.close();
				}
			}
			catch (IOException ex)
			{
				logger.error("发送短信失败", ex);
			}
		}
		return false;
	}
}
