package com.tccv.core.ali.oss;

import java.io.File;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.tccv.core.config.SystemConfig;
import com.tccv.core.util.image.CropperSize;
import com.tccv.core.util.image.ImageUtils;

/**
 * 此类描述的是： oss上传文件的工具类.
 *
 * @author: chenshanben
 * @version: 2016年3月1日 下午2:50:07
 */
public class OssUpload
{
	
	
	/** The logger. */
	static Logger logger = LoggerFactory.getLogger("OssUpload");
	
	/** 访问url. */
	protected static String endpoint = "oss-cn-hangzhou.aliyuncs.com";
	
	/** The access key id. */
	protected static String accessKeyId = "qkBgJf562q2ikgZg";
	
	/** The access key secret. */
	protected static String accessKeySecret = "aM7GJnNkzj4XVi8RpM451Z9WQNE3Ed";
	
	/**
	 * 上传图片.
	 *
	 * @param bucketName the bucket name
	 * @param path 暂时无效
	 * @param key the key
	 * @param img the img
	 * @return true, if put image
	 */
	public static boolean putImage(String bucketName, String path, String key, File img)
	{
		boolean flag = false;
		OSSClient client = null;
		try
		{
			client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			client.putObject(bucketName, path + "/" + key, img);
			flag = true;
		}
		catch (OSSException oe)
		{
			logger.error("Caught an OSSException, which means your request made it to OSS, "
					+ "but was rejected with an error response for some reason.");
			logger.error("Error Message: " + oe.getErrorCode());
			logger.error("Error Code:       " + oe.getErrorCode());
			logger.error("Request ID:      " + oe.getRequestId());
			logger.error("Host ID:           " + oe.getHostId());
		}
		catch (ClientException ce)
		{
			logger.error("Caught an ClientException, which means the client encountered "
					+ "a serious internal problem while trying to communicate with OSS, "
					+ "such as not being able to access the network.");
			logger.error("Error Message: " + ce.getMessage());
		}
		finally
		{
			/*
			 * Do not forget to shut down the client finally to release all
			 * allocated resources.
			 */
			client.shutdown();
		}
		return flag;
	}
	
	/**
	 * 上传图片到oss，并对图片做处理，如果图片的大小不符合width:height,会缩小留白.
	 *
	 * @param bucketName the bucket文件名
	 * @param path the 上传的路径名
	 * @param key object的名
	 * @param is the 上传的图片流
	 * @param height the 缩放的高
	 * @param width the 缩放的宽
	 * @return true, 是否上传成功
	 */
	public static boolean putImage(String bucketName, String path, String key, InputStream is, int height, int width)
	{
		// 缩放图片
		File file = OssImgUtil.scale(is, SystemConfig.uploadPath + "/" + key + ".jpg", 100, 100, true);
		
		// 将图片上传到oss
		boolean result = putImage(bucketName, path, key, file);
		
		// 删除本地图片
		file.deleteOnExit();
		
		return result;
	}
	
	/**
	 * 上传图片到oss，通过CropperSize对图片进行裁剪缩放，策略以x-x2,y-y2裁剪出图片，再根据w,h缩放
	 *
	 * @param bucketName the bucket文件名
	 * @param path the 上传的路径名
	 * @param key object的名
	 * @param is the 上传的图片流
	 * @param size the 裁剪的对象
	 * @param index the 数组的标记
	 * @return true, 是否上传成功
	 */
	public static boolean putImage(String bucketName, String path, String key, InputStream is, CropperSize size,
			int index)
	{
		// 缩放图片
		// File file = OssImgUtil.scale(is, SystemConfig.uploadPath + "/" + key
		// + ".jpg", 100, 100, true);
		File file = ImageUtils.cutAndScale(is, SystemConfig.uploadPath + "/" + key + ".jpg", size.getX()[index],
				size.getY()[index], size.getX2()[index], size.getY2()[index], size.getW()[index], size.getH()[index]);
		// 将图片上传到oss
		boolean result = putImage(bucketName, path, key, file);
		
		// 删除本地图片
		file.deleteOnExit();
		
		return result;
	}
	
	/**
	 * 上传图片到oss，通过CropperSize对图片进行裁剪缩放，默认选择CropperSize第一列数组，策略以x-x2,y-y2裁剪出图片，再根据w,h缩放
	 *
	 * @param bucketName the bucket文件名
	 * @param path the 上传的路径名
	 * @param key object的名
	 * @param is the 上传的图片流
	 * @param size the 裁剪的对象
	 * @return true, 是否上传成功
	 */
	public static boolean putImage(String bucketName, String path, String key, InputStream is, CropperSize size)
	{
		return putImage(bucketName, path, key, is, size, 0);
	}
	
}
