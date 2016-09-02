package com.tccv.core.util.json;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json相关
 * @author ChenJunhui
 *
 */
public class JsonUtils {

	public static ObjectMapper mapper = new ObjectMapper();
	public static String jsonString = null;
	
	/**
	 * 对象转JSON
	 * @param response
	 * @param obj
	 */
	public static void objToJSON(HttpServletResponse response,final Object obj) {
		try {
			setResponse(response);
			jsonString = mapper.writeValueAsString(obj);
			PrintWriter out = response.getWriter();
			out.print(jsonString);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 对象转json
	 * @param obj
	 * @return
	 */
	public static String objectToString(Object obj){
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * json转对象
	 * @param clazz
	 * @param json
	 * @return
	 */
	public static <T> T  jsonToObject(Class<T> clazz,String json){
		try {
			return mapper.readValue(json, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	/**
	 * 设置编码格式
	 * @param response
	 */
	public static void setResponse(HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
	}
}
