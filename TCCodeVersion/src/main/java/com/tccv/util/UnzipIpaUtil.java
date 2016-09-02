/**
 * 
 */
package com.tccv.util;





import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.dd.plist.NSDictionary;
import com.dd.plist.NSString;
import com.dd.plist.PropertyListParser;

/**
 *    
 * 此类描述的是：   .
 *
 * @author: styf
 * @version: 2016年5月3日 下午4:44:23
 */
public class UnzipIpaUtil
{
	
	/** The CF bundle version. */
	public static String CFBundleVersion = "CFBundleVersion";
	
	/** The CF bundle short version string. */
	public static String CFBundleShortVersionString = "CFBundleShortVersionString";
	
	/** The CF bundle identifier. */
	public static String CFBundleIdentifier = "CFBundleIdentifier";
	
	/** The CF bundle name. */
	public static String CFBundleName = "CFBundleName";
	
	public static String CFBundleDisplayName = "CFBundleDisplayName";
	/**
	 * Unzip ipa.通过ipa文件生成一个zip，并解压，返回解压的路径
	 *
	 * @param ipaFile the ipa file
	 * @return the string
	 */
	public static String unzipIpa(File ipaFile){
		try
		{
			    int byteread = 0;
	            String filename = ipaFile.getAbsolutePath().replaceAll(".ipa", ".zip");
	            File newfile = new File(filename);
	            if (ipaFile.exists()){
	                // 创建一个Zip文件
	                InputStream inStream = new FileInputStream(ipaFile);
	                FileOutputStream fs = new FileOutputStream(newfile);     
	                byte[] buffer = new byte[1444];            
	                while ((byteread = inStream.read(buffer)) != -1){
	                    fs.write(buffer,0,byteread);                   
	                }
	                if(inStream != null){
	                    inStream.close();
	                }
	                if(fs != null){
	                    fs.close(); 
	                }
	            }
	        //解压缩
	        String unzipPath = newfile.getAbsolutePath().substring(0, newfile.getAbsolutePath().lastIndexOf("."))+"/";
	        ZipUtil.unZipFiles(newfile, unzipPath);
			return unzipPath;
		}
		catch (Exception e)
		{	
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 通过IPA文件获取Info信息
	 * 这个方法可以重构，原因是指获取了部分重要信息，如果想要获取全部，那么应该返回一个Map<String,Object>
	 * 对于plist文件中的数组信息应该序列化存储在Map中，那么只需要第三发jar提供的NSArray可以做到！.
	 *
	 * @param infoPlist the info plist
	 * @return the ipa info map
	 * @throws Exception the exception
	 */
    public static Map<String,String> getIpaInfoMap(File infoPlist) throws Exception{
         
        Map<String,String> map = new HashMap<String,String>();
        
        // 第三方jar包提供
        NSDictionary rootDict = (NSDictionary) PropertyListParser.parse(infoPlist);
        // 应用包名
        NSString parameters = (NSString) rootDict.objectForKey(CFBundleIdentifier);
        map.put(CFBundleIdentifier, parameters.toString());
        // 应用名称
        parameters = (NSString) rootDict.objectForKey(CFBundleName);
        map.put(CFBundleName, parameters.toString());
        // 应用版本
        parameters = (NSString) rootDict.objectForKey(CFBundleVersion);
        map.put(CFBundleVersion, parameters.toString());
        // 发布版本
        parameters = (NSString) rootDict.objectForKey(CFBundleShortVersionString);
        map.put(CFBundleShortVersionString, parameters.toString());
        // 应用展示的名称
        parameters = (NSString) rootDict.objectForKey("CFBundleDisplayName");
        if(parameters != null)
        	map.put("CFBundleDisplayName", parameters.toString());
        // 应用所需IOS最低版本
//        parameters = (NSString) rootDict.objectForKey("MinimumOSVersion");
//        map.put("MinimumOSVersion", parameters.toString());
         
        // 如果有必要，应该删除解压的结果文件
//        infoPlist.delete();
//        infoPlist.getParentFile().delete();
         
        return map;
    }
    
}
