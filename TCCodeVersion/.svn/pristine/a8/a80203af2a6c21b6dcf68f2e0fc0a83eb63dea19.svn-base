package com.tccv.core.util.pay.alipay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 支付宝支付参数.
 */
@Component
public class AlipayConfig
{
	
	/** The subject. */
	@Value("${alipay.mobile.subject}")
	public String subject;
	
	/** The body. */
	@Value("${alipay.mobile.body}")
	public String body;
	
	/** 密钥. */
	@Value("${alipay.mobile.key}")
	public static String key;
	
	/** The private_key. */
	@Value("${alipay.mobile.private.key}")
	public static String private_key;
	
	/** The rsa_private_key. */
	@Value("${alipay.mobile.rsa.private.key}")
	public static String rsa_private_key;
	
	/** The partner. */
	@Value("${alipay.mobile.partner}")
	public static String partner;
	
	/** The ali_public_key. */
	@Value("${alipay.mobile.public.key}")
	public String ali_public_key;
	
	/** 卖家邮箱或手机号 就是卖家的支付宝登入名. */
	@Value("${alipay.mobile.seller.email}")
	public String sellerEmail;
	
	/** 异步通知地址. */
	@Value("${alipay.mobile.notify.url}")
	public String notifyUrl;
	
	/** 同步回调地址. */
	@Value("${alipay.mobile.callback.url}")
	public String callbackUrl;
	
	/** 中断回调地址. */
	@Value("${alipay.mobile.merchant.url}")
	public String merchantUrl;
	
	/** 版本. */
	@Value("${alipay.mobile.version}")
	public String version;
	
	/** 帐号名. */
	public String accountName;
	
	/** 签名加密方式. */
	public static String sign_type_md5="MD5";
	
	/** 签名方式，选择项：0001(RSA)、MD5. */
	public static String sign_type = "0001";
	
	/** 返回格式. */
	public static String format = "xml";
	
	/**字符编码格式 目前支持  utf-8. */
	public static String input_charset = "utf-8";	
	
	/** 支付宝网关地址. */
	public static String ALIPAY_GATEWAY_NEW = "http://wappaygw.alipay.com/service/rest.htm?";
	
	/**
	 * Gets the subject.
	 *
	 * @return the subject
	 */
	public String getSubject()
	{
		return subject;
	}
	
	/**
	 * Sets the subject.
	 *
	 * @param subject the new subject
	 */
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	
	
	
	
	public String getAccountName()
	{
		return accountName;
	}

	
	public void setAccountName(String accountName)
	{
		this.accountName = accountName;
	}

	/**
	 * Gets the body.
	 *
	 * @return the body
	 */
	public String getBody()
	{
		return body;
	}
	
	/**
	 * Sets the body.
	 *
	 * @param body the new body
	 */
	public void setBody(String body)
	{
		this.body = body;
	}
	
	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey()
	{
		return key;
	}
	
	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKey(String key)
	{
		AlipayConfig.key = key;
	}
	
	/**
	 * Gets the private_key.
	 *
	 * @return the private_key
	 */
	public String getPrivate_key()
	{
		return private_key;
	}
	
	/**
	 * Sets the private_key.
	 *
	 * @param private_key the new private_key
	 */
	public void setPrivate_key(String private_key)
	{
		AlipayConfig.private_key = private_key;
	}
	
	/**
	 * Gets the rsa_private_key.
	 *
	 * @return the rsa_private_key
	 */
	public String getRsa_private_key()
	{
		return rsa_private_key;
	}
	
	/**
	 * Sets the rsa_private_key.
	 *
	 * @param rsa_private_key the new rsa_private_key
	 */
	public void setRsa_private_key(String rsa_private_key)
	{
		this.rsa_private_key = rsa_private_key;
	}
	
	/**
	 * Gets the partner.
	 *
	 * @return the partner
	 */
	public String getPartner()
	{
		return partner;
	}
	
	/**
	 * Sets the partner.
	 *
	 * @param partner the new partner
	 */
	public void setPartner(String partner)
	{
		AlipayConfig.partner = partner;
	}
	
	/**
	 * Gets the ali_public_key.
	 *
	 * @return the ali_public_key
	 */
	public String getAli_public_key()
	{
		return ali_public_key;
	}
	
	/**
	 * Sets the ali_public_key.
	 *
	 * @param ali_public_key the new ali_public_key
	 */
	public void setAli_public_key(String ali_public_key)
	{
		this.ali_public_key = ali_public_key;
	}
	
	/**
	 * Gets the seller email.
	 *
	 * @return the seller email
	 */
	public String getSellerEmail()
	{
		return sellerEmail;
	}
	
	/**
	 * Sets the seller email.
	 *
	 * @param sellerEmail the new seller email
	 */
	public void setSellerEmail(String sellerEmail)
	{
		this.sellerEmail = sellerEmail;
	}
	
	/**
	 * Gets the notify url.
	 *
	 * @return the notify url
	 */
	public String getNotifyUrl()
	{
		return notifyUrl;
	}
	
	/**
	 * Sets the notify url.
	 *
	 * @param notifyUrl the new notify url
	 */
	public void setNotifyUrl(String notifyUrl)
	{
		this.notifyUrl = notifyUrl;
	}
	
	/**
	 * Gets the callback url.
	 *
	 * @return the callback url
	 */
	public String getCallbackUrl()
	{
		return callbackUrl;
	}
	
	/**
	 * Sets the callback url.
	 *
	 * @param callbackUrl the new callback url
	 */
	public void setCallbackUrl(String callbackUrl)
	{
		this.callbackUrl = callbackUrl;
	}
	
	/**
	 * Gets the merchant url.
	 *
	 * @return the merchant url
	 */
	public String getMerchantUrl()
	{
		return merchantUrl;
	}
	
	/**
	 * Sets the merchant url.
	 *
	 * @param merchantUrl the new merchant url
	 */
	public void setMerchantUrl(String merchantUrl)
	{
		this.merchantUrl = merchantUrl;
	}
	
	/**
	 * Gets the v.
	 *
	 * @return the v
	 */
	public String getVersion()
	{
		return version;
	}
	
	/**
	 * Sets the v.
	 *
	 * @param v the new v
	 */
	public void setVersion(String version)
	{
		this.version = version;
	}
}
