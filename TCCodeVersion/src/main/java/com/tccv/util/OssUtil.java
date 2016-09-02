/**
 * 
 */
package com.tccv.util;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;

/**   
 * 此类描述的是：  项目版本管理 oss工具类
 * @author: styf   
 * @version: 2016年5月3日 上午10:32:44    
 */
public class OssUtil
{
	static Logger logger = LoggerFactory.getLogger(OssUtil.class);

    private static String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    public static String uploadPoint = "http://tccv.oss-cn-hangzhou.aliyuncs.com";
    private static String accessKeyId = "qkBgJf562q2ikgZg";
    private static String accessKeySecret = "aM7GJnNkzj4XVi8RpM451Z9WQNE3Ed";

    public static String bucketName = "tccv";
    public static String jsObjectKey = "jsbundle";
    public static String fileObjectKey = "versionfile";
    
    public static boolean uploadFile (String key,String ossPath,InputStream inputStream){
    	
    	boolean success = false;
    	logger.info("oss 上传文件开始....");
    	OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    	try {

            if (ossClient.doesBucketExist(bucketName)) {
            	logger.info("您已经创建Bucket：" + bucketName + "。");
            } else {
            	logger.info("您的Bucket不存在，创建Bucket：" + bucketName + "。");
                ossClient.createBucket(bucketName);
            }

            
            ossClient.putObject(bucketName, key+"/"+ossPath, inputStream);
            logger.info("Object：" + ossPath + "存入OSS成功。");
            success = true;

        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }

        logger.info("oss 上传文件结束....");
        return success;
    }
    

}
