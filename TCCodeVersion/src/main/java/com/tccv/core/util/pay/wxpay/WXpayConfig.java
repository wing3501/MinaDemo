package com.tccv.core.util.pay.wxpay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WXpayConfig
{
	/** 应用ID. */
	@Value("${wxpay.mobile.appid}")
	public String appid;
	
	/** 商户号. */
	@Value("${wxpay.mobile.mch.id}")
	public String mch_id;
	
	/** 商品描述. */
	@Value("${wxpay.mobile.body}")
	public String body;
	
	/** 通知地址. */
	@Value("${wxpay.mobile.notify.url}")
	public String notify_url;
	
	/** 交易类型. */
	@Value("${wxpay.mobile.trade.type}")
	public String trade_type;
	
	/** 商户密钥. */
	@Value("${wxpay.mobile.key}")
	public String key;

	/** 统一下单. */
	@Value("${wxpay.mobile.url}")
	public String url;

	/** 扩展字段. */
	@Value("${wxpay.mobile.package}")
	public String _package;
	
	public String getAppid()
	{
		return appid;
	}

	
	public void setAppid(String appid)
	{
		this.appid = appid;
	}

	
	public String getMch_id()
	{
		return mch_id;
	}

	
	public void setMch_id(String mch_id)
	{
		this.mch_id = mch_id;
	}

	
	public String getBody()
	{
		return body;
	}

	
	public void setBody(String body)
	{
		this.body = body;
	}

	
	public String getNotify_url()
	{
		return notify_url;
	}

	
	public void setNotify_url(String notify_url)
	{
		this.notify_url = notify_url;
	}

	
	public String getTrade_type()
	{
		return trade_type;
	}

	
	public void setTrade_type(String trade_type)
	{
		this.trade_type = trade_type;
	}

	
	public String getKey()
	{
		return key;
	}

	
	public void setKey(String key)
	{
		this.key = key;
	}

	
	public String getUrl()
	{
		return url;
	}

	
	public void setUrl(String url)
	{
		this.url = url;
	}


	
	public String get_package()
	{
		return _package;
	}


	
	public void set_package(String _package)
	{
		this._package = _package;
	}
	
}
