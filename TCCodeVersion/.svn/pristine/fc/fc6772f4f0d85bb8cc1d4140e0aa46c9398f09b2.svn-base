/**
 * 
 */
package com.tccv.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;

/**
 *    
 * 此类描述的是：   plist工具类.
 *
 * @author: styf
 * @version: 2016年5月4日 上午11:21:17
 */
public class PlistUtil
{
	
	/**
	 * Read plist to string.读取一个plist文件到String
	 *
	 * @param filePath the file path
	 * @return the string
	 */
	public static String readPlistToString(String filePath){
		  Reader reader = null;
		  BufferedReader br = null;
		  StringBuffer sb = new StringBuffer();
		  try {
		     reader = new FileReader(filePath);
		     br = new BufferedReader(reader);
		     String data = null;
		     while ((data = br.readLine()) != null) {
		     sb.append(data);
		     }
		   } catch (IOException e) {
		     e.printStackTrace();
		   } finally {
		     try {
		      reader.close();
		      br.close();
		     } catch (Exception e) {
		      e.printStackTrace();
		     }
		   }
		  
		  return sb.toString();
	}
	
	
	public static boolean createPlistFromString(String filePath,String content){
		ByteArrayInputStream stream = null;
		OutputStream os = null;
		boolean success = false;
		try
		{
			stream = new ByteArrayInputStream(content.getBytes());
			File plist = new File(filePath);
			os = new FileOutputStream(plist);
			int bytesRead = 0;
			   byte[] buffer = new byte[8192];
			   while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
			      os.write(buffer, 0, bytesRead);
			   }
			success = true;
			os.close();
		    stream.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return success;
	}
}
