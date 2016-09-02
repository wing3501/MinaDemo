package com.tccv.core.util.pay.wxpay;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class WXpayCore
{
	
	public static String buildRequest(Map<String, String> sParaTemp)
	{
		
		sParaTemp = createLinkMap(sParaTemp);
		// 待请求参数数组
		List<String> keys = new ArrayList<String>(sParaTemp.keySet());
		
		StringBuffer sbHtml = new StringBuffer();
		
		sbHtml.append("<xml>");
		
		for (int i = 0; i < keys.size(); i++)
		{
			String name = (String) keys.get(i);
			String value = (String) sParaTemp.get(name);
			
			sbHtml.append("<" + name + ">" + value + "</" + name + ">");
		}
		
		sbHtml.append("</xml>");
		
		return sbHtml.toString();
	}
	
	public static Map<String, String> createLinkMap(Map<String, String> params)
	{
		
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < keys.size(); i++)
		{
			String key = keys.get(i);
			String value = params.get(key);
			map.put(key, value);
		}
		
		return map;
	}
	
	public static Map<String, String> parse(String protocolXML)
		throws Exception
	{
		
		Map<String, String> map = new HashMap<String, String>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(protocolXML)));
		
		Element root = doc.getDocumentElement();
		NodeList books = root.getChildNodes();
		if (books != null)
		{
			for (int i = 0; i < books.getLength(); i++)
			{
				Node book = books.item(i);
				map.put(book.getNodeName(), book.getFirstChild().getNodeValue());
//				System.out.println("节点=" + book.getNodeName() + "\ttext=" + book.getFirstChild().getNodeValue());
			}
		}
		return map;
	}
}
