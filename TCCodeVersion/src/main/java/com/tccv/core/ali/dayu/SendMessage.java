package com.tccv.core.ali.dayu;

/**
 * The Class SendMessage.
 */
public class SendMessage
{
//	private static Logger logger = LoggerFactory.getLogger("阿里大鱼短信发送");
//	
//	/** key */
//	public final static String APP_KEY = "23340510";
//	
//	/** 密钥. */
//	public final static String APP_SECRET = "1bece055fc2da00fae3ba593e2a61cc5";
//	
//	/** url */
//	public final static String APP_URL = "http://gw.api.taobao.com/router/rest";
//	
//	/** The client. */
//	public static TaobaoClient client = null;
//	
//	/**
//	 * 单例.
//	 *
//	 * @return the client
//	 */
//	private static TaobaoClient getClient(){
//		if(client == null){
//			synchronized (TaobaoClient.class)
//			{
//				if (client == null)
//				{
//					client = new DefaultTaobaoClient(APP_URL, APP_KEY, APP_SECRET);
//				}
//			}
//		}
//		
//		return client;
//	}
//	
//	/**
//	 * 发送短信,传入param为json或xml字符串.
//	 *
//	 * @param templateCode 模版code
//	 * @param mobile the 手机号
//	 * @param param the 模版内的参数
//	 * @param freeSignName 签名名称
//	 * @param extend 透传信息
//	 * @return the JSON object
//	 */
//	public static JSONObject sendMessage(String templateCode, String mobile, String param, String freeSignName, String extend){
//		//建立客户端
//		TaobaoClient client = getClient();
//		
//		//请求对象
//		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
//		req.setExtend(extend);
//		req.setSmsType("normal");
//		req.setSmsFreeSignName(freeSignName);
//		req.setSmsParamString(param);
//		req.setRecNum(mobile);
//		req.setSmsTemplateCode(templateCode);
//		
//		//返回对象
//		AlibabaAliqinFcSmsNumSendResponse rsp = null;
//		try {
//			//发送请求
//			rsp = client.execute(req);
//		} catch (ApiException e) {
//			logger.error("短信发送失败",e);
//		}
//		
//		//string 转 json
//		return JSONObject.fromObject(rsp.getBody());
//	}
//	
//	/**
//	 * 发送短信,传入param为json或xml字符串，没有透传.
//	 *
//	 * @param templateCode 模版code
//	 * @param mobile the 手机号
//	 * @param param the 模版内的参数，可以是json，map，entity
//	 * @param freeSignName 签名名称
//	 * @return the JSON object
//	 */
//	public static JSONObject sendMessage(String templateCode, String mobile, String param, String freeSignName){
//		return sendMessage(templateCode, mobile, param, freeSignName,null);
//	}
//	
//	/**
//	 * 发送短信,传入param为对象.
//	 *
//	 * @param templateCode 模版code
//	 * @param mobile the 手机号
//	 * @param param the 模版内的参数，可以是json，map，entity
//	 * @param freeSignName 签名名称
//	 * @param extend 透传信息
//	 * @return the JSON object
//	 */
//	public static JSONObject sendMessage(String templateCode, String mobile, Object param, String freeSignName, String extend){
//		return sendMessage(templateCode, mobile, JsonUtils.objectToString(param), freeSignName, extend);
//	}
//	
//	/**
//	 * 发送短信,传入param为对象,没有透传.
//	 *
//	 * @param templateCode 模版code
//	 * @param mobile the 手机号
//	 * @param param the 模版内的参数，可以是json，map，entity
//	 * @param freeSignName 签名名称
//	 * @return the JSON object
//	 */
//	public static JSONObject sendMessage(String templateCode, String mobile, Object param, String freeSignName){
////		return sendMessage(templateCode, mobile, param, freeSignName, null);
//		return sendMessage(templateCode, mobile, JsonUtils.objectToString(param), freeSignName, null);
//	}
//
//	/**
//	 * 发送短信验证码
//	 * @param mobile
//	 * @param verMsg
//	 */
//	public static void sendVerifyMessage(String mobile, String verMsg) {
//		sendMessage("SMS_7365323", mobile, "{'code':'"+verMsg+"'}", "德发网络","");
//	}
}
