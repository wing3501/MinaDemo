package com.tccv.core.util.pay.wxpay;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tccv.core.util.http.HttpUtils;
import com.tccv.core.util.pay.alipay.AlipayCore;
import com.tccv.core.util.random.RandomUtils;
public class WXpayNotify
{
	@Autowired
	private static WXpayConfig wc;
	
	public static final Logger logger = LoggerFactory.getLogger(WXpayNotify.class);
	
    /**
     * 微信订单结果查询
     */
    private static final String HTTPS_VERIFY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
    
    /**
     * 验证消息是否是支付宝发出的合法消息，验证callback
     * @param params 通知返回来的参数数组
     * @return 验证结果
     */
    public static boolean verifyReturn(Map<String, String> params) {
	    String sign = "";
	    //获取返回时的签名验证结果
	    if(params.get("sign") != null) {sign = params.get("sign");}
	    //验证签名
	    boolean isSign = getSignVeryfy(params, sign, true);

        //写日志记录（若要调试，请取消下面两行注释）
        //String sWord = "isSign=" + isSign + "\n 返回回来的参数：" + AlipayCore.createLinkString(params);
	    //AlipayCore.logResult(sWord);

        //判断isSign是否为true
        //isSign不是true，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
        if (isSign) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证消息是否是微信发出的合法消息，验证服务器异步通知
     * @param params 通知返回来的参数数组
     * @return 验证结果
     */
    public static boolean verifyNotify(Map<String, String> params) throws Exception {
    	
    	//获取返回时的签名验证结果
    	String sign = "";
    	if(params.get("sign") != null) {sign = params.get("sign");}
    	boolean isSign = getSignVeryfy(params, sign,false);
    	logger.info("签名验证结果："+isSign);
    	
    	//获取微信订单实际支付结果
    	String responseTxt = "";
    	if(params.get("transaction_id") != null) {
			String transaction_id = params.get("transaction_id");
			responseTxt = verifyResponse(transaction_id);
		}
    	logger.info("获取微信订单实际支付结果："+responseTxt);
    	
        if (isSign && responseTxt.equals("SUCCESS")) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 根据反馈回来的信息，生成签名结果
     * @param Params 通知返回来的参数数组
     * @param sign 比对的签名结果
     * @param isSort 是否排序
     * @return 生成的签名结果
     */
	private static boolean getSignVeryfy(Map<String, String> map, String sign, boolean isSort) {
		
    	//过滤空值、sign与sign_type参数
		Map<String, String> params = new HashMap<String, String>();
		params.put("appid", map.get("appid"));
		params.put("mch_id", map.get("mch_id"));
		params.put("nonce_str", map.get("nonce_str"));
		params.put("body", map.get("appid"));
		params.put("out_trade_no", map.get("out_trade_no"));
		params.put("total_fee", map.get("total_fee"));
		try
		{
			params.put("spbill_create_ip", InetAddress.getLocalHost().getHostAddress().toString());
		}
		catch (UnknownHostException e1)
		{
			logger.error("获取本地ip失败");
			e1.printStackTrace();
		}
		params.put("notify_url", wc.getNotify_url());
		params.put("trade_type", map.get("appid"));
		
		String sortStr = AlipayCore.createLinkStringNoSort(params);
		
		String key = wc.getKey();
		
		//获取待签名字符串
		String stringSignTemp = sortStr + "&key=" + key;
		
		String localSign = "";
		try
		{
			localSign = WXMD5.md5Encode(stringSignTemp).toUpperCase();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
        //获得签名验证结果
		logger.info("localSign is "+localSign);
        return sign.equals(localSign);
    }

    /**
    * 获取远程服务器结果,微信订单实际支付结果
    */
    private static String verifyResponse(String transaction_id) throws Exception {
    	
        //微信订单实际支付结果
    	Map<String, String> params = new HashMap<String, String>();
		params.put("appid", wc.getAppid());
		params.put("mch_id", wc.getMch_id());
		params.put("nonce_str", RandomUtils.getRandomStr(22));
		params.put("transaction_id", transaction_id);
		
		String sortStr = AlipayCore.createLinkStringNoSort(params);
		
		String key = wc.getKey();
		
		String stringSignTemp = sortStr + "&key=" + key;
		
		String sign = WXMD5.md5Encode(stringSignTemp).toUpperCase();
		
		params.put("sign", sign);
		
        String sendPost = HttpUtils.sendPost(HTTPS_VERIFY_URL, WXpayCore.buildRequest(params));
        
        logger.info("微信订单实际支付结果:sendPost is "+sendPost);
        
        Map<String, String> parseMap = WXpayCore.parse(sendPost);
        
        String result_code="";
        if("SUCCESS".equals(parseMap.get("return_code"))){
        	result_code = parseMap.get("result_code");
        }else{
        	result_code = parseMap.get("return_msg");
        }
        return result_code;
    }

}
