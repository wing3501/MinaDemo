package com.tccv.core.util.pay.wxpay;

import java.security.MessageDigest;

/**
 * 
 * 此类描述的是：   MD5加密生成签名
 * @author: zengfanlin   
 * @version: 2016年4月13日 下午3:27:08
 */
public class WXMD5 {
	public static String md5Encode(String inStr)
			throws Exception
		{
			MessageDigest md5 = null;
			try
			{
				md5 = MessageDigest.getInstance("MD5");
			}
			catch (Exception e)
			{
				System.out.println(e.toString());
				e.printStackTrace();
				return "";
			}
			
			byte[] byteArray = inStr.getBytes("UTF-8");
			byte[] md5Bytes = md5.digest(byteArray);
			StringBuffer hexValue = new StringBuffer();
			for (int i = 0; i < md5Bytes.length; i++)
			{
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16)
				{
					hexValue.append("0");
				}
				hexValue.append(Integer.toHexString(val));
			}
			return hexValue.toString();
		}
}