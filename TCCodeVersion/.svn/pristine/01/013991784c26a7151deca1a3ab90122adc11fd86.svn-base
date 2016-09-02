/**
 * 
 */
package com.tccv.util;

import java.io.File;

/**   
 * 此类描述的是：   
 * @author: styf   
 * @version: 2016年5月4日 下午2:05:38    
 */
public class FileUtil
{
	 //删除文件和目录
	 public static void clearFiles(String dirPath){
	       File file = new File(dirPath);
	       if(file.exists()){
	           deleteFile(file);
	      }
	 }
	 public static void deleteFile(File file){
	      if(file.isDirectory()){
	           File[] files = file.listFiles();
	           for(int i=0; i<files.length; i++){
	                deleteFile(files[i]);
	           }
	      }
	      file.delete();
	 }
}
