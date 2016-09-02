package com.tccv.core.util.map;

import java.math.BigDecimal;

import com.tccv.core.util.http.HttpUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**   
 * 此类描述的是：   
 * @author: kangbiao   
 * @version: 2016年3月9日 下午1:59:04    
 */
public class BaiduMapUtils
{
	
	private static final BigDecimal big1000 = new BigDecimal(1000);

	/**
	 *   根据经纬度反差地址.
	 *
	 * @param lng the lng
	 * @param lat the lat
	 * @return the JSON object
	 * @throws Exception the exception
	 */	
	public static JSONObject renderReverse(Double lng, Double lat) throws Exception{
		
		String url = "http://api.map.baidu.com/geocoder/v2/?";
		String param = "ak=ios7yWn0gRGrPpxUvIGGkPmC&location="+lat+","+lng+"&output=json&pois=0";
//		{"status":0,"result":{"location":{"lng":120.07392699772,"lat":30.318808990556},"formatted_address":"浙江省杭州市西湖区振华路250","business":"","addressComponent":{"adcode":"330106","city":"杭州市","country":"中国","direction":"北","distance":"61","district":"西湖区","province":"浙江省","street":"振华路","street_number":"250","country_code":0},"poiRegions":[{"direction_desc":"\u5185","name":"\u897f\u6e2f\u00b7\u65b0\u754c\u4e1c\u533a","tag":"\u516c\u53f8\u4f01\u4e1a"}],"sematic_description":"西港·新界东区内,杭州联合银行科技支行北87米","cityCode":179}}
		String result = HttpUtils.sendPost(url, param);
		return JSONObject.fromObject(JSONObject.fromObject(JSONObject.fromObject(result).get("result")).get("addressComponent"));
//		jObject.get("province")
//      jObject.get("city")
//      jObject.get("district"));
	}
	
	//"http://api.map.baidu.com/geocoder/v2/?address="+shop.getAddress().replaceAll(" ", "")+"&output=json&ak=702632E1add3d4953d0f105f27c294b9&callback=showLocation"
	
	/**
	 * 根据地址反查经纬度
	 * @param address
	 * @return
	 * @throws Exception
	 */
	public static JSONObject getLocationByAddress(String address) throws Exception{
		
		String url = "http://api.map.baidu.com/geocoder/v2/?";
		String param = "address="+address.replaceAll(" ", "")+"&output=json&ak=702632E1add3d4953d0f105f27c294b9&callback=showLocation";
		//{"status":0,"result":{"location":{"lng":120.22094019744,"lat":30.257786137657},"precise":0,"confidence":70,"level":"\u5730\u4ea7\u5c0f\u533a"}}
		String result = HttpUtils.sendPost(url, param);
		//{"lng":120.22094,"lat":30.257786}
		if(!JSONObject.fromObject(result).get("status").equals(0)){
			throw new Exception("反查经纬度失败");
		}
		return JSONObject.fromObject(JSONObject.fromObject(JSONObject.fromObject(result).get("result")).get("location"));
		//jsonObject.get("lng")  jsonObject.get("lat")
	}

	/**
	 * 计算两点实际最小距离.
	 *
	 * @param fromLng the from lng
	 * @param fromLat the from lat
	 * @param toLng the to lng
	 * @param toLat the to lat
	 * @return the big decimal
	 * @throws Exception the exception
	 */
	public static BigDecimal realDistance(Double fromLng, Double fromLat,Double toLng, Double toLat) throws Exception{
		
		//计算 收发货实际距离
		String url = "http://api.map.baidu.com/direction/v1/routematrix?";
		String param = "&origins="+fromLat+","+fromLng
		+"&destinations="+toLat+","+toLng+"&mode=driving&ak=ios7yWn0gRGrPpxUvIGGkPmC&output=json";
		String result = HttpUtils.sendGet(url, param);
		JSONArray jsonArray = JSONArray.fromObject(JSONObject.fromObject(JSONObject.fromObject(result).get("result")).get("elements"));
		//取最短距离，并向上取整 2100米=3公里
		return getMinDistance(jsonArray).divide(big1000,0,BigDecimal.ROUND_CEILING);
	}
	
    private static BigDecimal getMinDistance(JSONArray jsonArray)throws Exception {
    	
    	BigDecimal distance = new BigDecimal(JSONObject.fromObject(jsonArray.getJSONObject(0).get("distance")).get("value").toString());;
    	BigDecimal CurrDistance ;
    	for (int i = 0; i < jsonArray.size(); i++) {
    		CurrDistance = new BigDecimal(JSONObject.fromObject(jsonArray.getJSONObject(i).get("distance")).get("value").toString());
    		if(CurrDistance.compareTo(distance)==-1){
    			distance = CurrDistance;
    		}
		}
		return distance;
	}
	
}
