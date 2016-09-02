/**
 * 
 */
package com.tccv.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.tccv.constants.ErrorCode;
import com.tccv.constants.UserErrorCode;
import com.tccv.core.dto.ResultPageDOHelper;
import com.tccv.core.util.ajax.AjaxUtils;
import com.tccv.exception.CustomException;
import com.tccv.pojo.AppVersion;
import com.tccv.pojo.FileVersion;
import com.tccv.pojo.resp.UploadResp;
import com.tccv.service.AppVersionService;
import com.tccv.service.FileVersionService;
import com.tccv.util.FileUtil;
import com.tccv.util.OssUtil;
import com.tccv.util.PlistUtil;
import com.tccv.util.TextSearchFileUtil;
import com.tccv.util.UnzipIpaUtil;
import com.tccv.util.apk.entity.ApkInfo;
import com.tccv.util.apk.util.ApkUtil;
import com.tccv.util.apk.util.IconUtil;

/**
 *    
 * 此类描述的是：   .
 *
 * @author: styf
 * @version: 2016年4月15日 下午12:03:39
 */
@Controller
@RequestMapping("/av")
public class AppVersionController extends BaseController{
	
	/** The app version service. */
	@Autowired
	private AppVersionService appVersionService;
	
	/** The file version service. */
	@Autowired
	private FileVersionService fileVersionService;
	
/** The Constant fileFath. */
	public final static String fileFath = "/appfile/";
//	public final static String fileFath = "/Users/defa/Documents/tccv/appfile/";
	
	
	
	/** The info plist name. */
private static String infoPlistName = "Info.plist";
	
	/** The app icon. */
	private static String appIcon = "AppIcon";
	
	/**
	 * Upload.上传文件
	 *
	 * @param appVersion the app version
	 * @param appFile the app file
	 * @param request the request
	 * @param response the response
	 * @throws IllegalStateException the illegal state exception
	 */
	@RequestMapping(value = "/upload",method = RequestMethod.POST)
	public void upload(AppVersion appVersion,MultipartFile appFile,HttpServletRequest request, HttpServletResponse response){
				try{
					if(appFile == null)
						throw new CustomException(ErrorCode.PARAM_ERROR.getErrorCode() , ErrorCode.PARAM_ERROR.getZhMessage() + "appFile=>" + appFile);
					if(appVersion.getAppName() == null)
						throw new CustomException(ErrorCode.PARAM_ERROR.getErrorCode() , ErrorCode.PARAM_ERROR.getZhMessage() + "appName=>" + appVersion.getAppName());
					if(appVersion.getAppVersion() == null)
						throw new CustomException(ErrorCode.PARAM_ERROR.getErrorCode() , ErrorCode.PARAM_ERROR.getZhMessage() + "appVersion=>" + appVersion.getAppVersion());
//					if(appVersion.getIsForced() == null)
//						throw new CustomException(ErrorCode.PARAM_ERROR.getErrorCode() , ErrorCode.PARAM_ERROR.getZhMessage() + "isForced=>" + appVersion.getIsForced());
					
					
					logger.info("上传参数:"+appVersion.toString());
					//1.以项目名作为文件名
					//原始文件名称
					String originalFileName =  appFile.getOriginalFilename();
					//新文件名称   xxx.xip
				
					if(!(originalFileName.substring(originalFileName.lastIndexOf(".")).equals(".zip"))){
						throw new CustomException(ErrorCode.FILE_FORMAT_ERROR.getErrorCode() , ErrorCode.FILE_FORMAT_ERROR.getZhMessage() + "file format=>" + originalFileName.substring(originalFileName.lastIndexOf(".")));
					}
					String newFileName = appVersion.getAppName()+originalFileName.substring(originalFileName.lastIndexOf("."));
					//保存路径    项目名/版本号/xxx.zip
					String ossPath = appVersion.getAppName()+"/"+appVersion.getAppVersion()+"/"+newFileName;
					
					boolean success = OssUtil.uploadFile(OssUtil.jsObjectKey,ossPath, appFile.getInputStream());
					if(success){
						//是否已存在该版本信息
						List<AppVersion>old = appVersionService.selectByAppNameAndVersion(appVersion);
						UploadResp resp = new UploadResp();
						if(old != null && old.size() >0){
							//已存在该版本信息，则不做新增
							appVersion = old.get(0);
						}else{
							//新增版本
							String fullPath = OssUtil.uploadPoint + "/" + OssUtil.jsObjectKey + "/" + ossPath;
							String appKey = UUID.randomUUID().toString();
							appVersion.setAppKey(appKey);
							List<AppVersion>list = appVersionService.selectByAppName(appVersion.getAppName());
							if(list != null && list.size() > 0){
								AppVersion oldAppVersion = list.get(0);
								appVersion.setAppKey(oldAppVersion.getAppKey());
							}
							appVersion.setAppUrl(fullPath);
							appVersion.setCreateTime(new Date());
							appVersion.setUpdateTime(new Date());
							
							
							appVersionService.save(appVersion);
						}

						resp.setAppKey(appVersion.getAppKey());
						resp.setAppName(appVersion.getAppName());
						resp.setAppVersion(appVersion.getAppVersion());
						
						AjaxUtils.renderJsonporJson(response, null,
								ResultPageDOHelper.getMsgResultDO(resp, "上传文件成功！"));
						
					}else{
						throw new CustomException(ErrorCode.OSS_UPLOAD_ERROR.getErrorCode() , ErrorCode.OSS_UPLOAD_ERROR.getZhMessage());
					}

					
				}catch(Exception e){
					logger.error("upload error! ");
					exceptionOperate(e, response);
				}
	}
	
	
	/**
	 * Download.下载 (废弃)
	 *
	 * @param appVersion the app version
	 * @param response the response
	 * @return the model and view
	 */
	@RequestMapping(value = "/download",method = RequestMethod.POST)
	public void download(AppVersion appVersion,HttpServletResponse response){
		try
		{
			logger.info(appVersion.toString());
			if(appVersion.getAppName() == null)
				throw new CustomException(ErrorCode.PARAM_ERROR.getErrorCode() , ErrorCode.PARAM_ERROR.getZhMessage() + "appName=>" + appVersion.getAppName());
			
			List<AppVersion>list = appVersionService.selectByAppVersion(appVersion);
			if(list == null || list.size() == 0)
				throw new CustomException(ErrorCode.NOTEXIST.getErrorCode() , ErrorCode.NOTEXIST.getZhMessage());
			AppVersion version = list.get(0);
			
			String path = version.getAppUrl();
			String fileName = path.substring(path.lastIndexOf("/")+1);
			
			response.reset();// 不加这一句的话会出现下载错误 
	        response.setHeader("Content-disposition", "attachment; filename="+fileName);// 设定输出文件头   
	        response.setContentType("text/x-plain");// 定义输出类型 
	        
	        ServletOutputStream out = response.getOutputStream();
 
            File file = new File(path);
            
            FileInputStream fis = new java.io.FileInputStream(file);
            ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream(4096);
            
            byte[] cache = new byte[4096];
            for (int offset = fis.read(cache); offset != -1; offset = fis.read(cache)) {
                    byteOutputStream.write(cache, 0, offset);
            }
            
            byte[] bt = null;
            bt = byteOutputStream.toByteArray();               
            
            out.write(bt);
            out.flush();
            out.close();
            fis.close();
	        
		}
		catch (Exception e)
		{
			logger.error("download error! ");
			exceptionOperate(e, response);
		}
	}
	
	/**
	 * Download.下载 (废弃)
	 *
	 * @param appVersion the app version
	 * @param response the response
	 * @return the model and view
	 */
	@RequestMapping(value = "/downloadJson",method = RequestMethod.POST)
	public void downloadJson(@RequestBody AppVersion appVersion,HttpServletResponse response){
		try
		{
			logger.info(appVersion.toString());
			if(appVersion.getAppName() == null)
				throw new CustomException(ErrorCode.PARAM_ERROR.getErrorCode() , ErrorCode.PARAM_ERROR.getZhMessage() + "appName=>" + appVersion.getAppName());
			
			List<AppVersion>list = appVersionService.selectByAppVersion(appVersion);
			if(list == null || list.size() == 0)
				throw new CustomException(ErrorCode.NOTEXIST.getErrorCode() , ErrorCode.NOTEXIST.getZhMessage());
			AppVersion version = list.get(0);
			
			String path = version.getAppUrl();
			String fileName = path.substring(path.lastIndexOf("/")+1);
			
			response.reset();// 不加这一句的话会出现下载错误 
	        response.setHeader("Content-disposition", "attachment; filename="+fileName);// 设定输出文件头   
	        response.setContentType("text/x-plain");// 定义输出类型 
	        
	        ServletOutputStream out = response.getOutputStream();
 
            File file = new File(path);
            
            FileInputStream fis = new java.io.FileInputStream(file);
            ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream(4096);
            
            byte[] cache = new byte[4096];
            for (int offset = fis.read(cache); offset != -1; offset = fis.read(cache)) {
                    byteOutputStream.write(cache, 0, offset);
            }
            
            byte[] bt = null;
            bt = byteOutputStream.toByteArray();               
            
            out.write(bt);
            out.flush();
            out.close();
            fis.close();
	        
		}
		catch (Exception e)
		{
			logger.error("download error! ");
			exceptionOperate(e, response);
		}
	}
	
	
	/**
	 * Gets the app version.根据项目名查询最新的版本信息
	 *
	 * @param appVersion the app version
	 * @param response the response
	 * @return the app version
	 */
	@RequestMapping(value = "/getAppVersion",method = RequestMethod.POST)
	public void getAppVersion(AppVersion appVersion,HttpServletResponse response){
		try
		{
			logger.info(appVersion.toString());
			if(StringUtils.isNotBlank(appVersion.getAppName())){
				List<AppVersion>list = appVersionService.selectByAppName(appVersion.getAppName());
				if(list != null && list.size() > 0){
					appVersion = list.get(0);
					AjaxUtils.renderJsonporJson(response, null,
							ResultPageDOHelper.getMsgResultDO(appVersion, "获取版本信息成功！"));
				}else{
					throw new CustomException(ErrorCode.NOTEXIST.getErrorCode() , ErrorCode.NOTEXIST.getZhMessage());
				}
			}else{
				throw new CustomException(ErrorCode.PARAM_ERROR.getErrorCode() , ErrorCode.PARAM_ERROR.getZhMessage() + "appName=>" + appVersion.getAppName());
			}
	        
		}
		catch (Exception e)
		{
			logger.error("getAppVersion error! ");
			exceptionOperate(e, response);
		}
		
	}
	
	/**
	 * Setup latest. 设置为最新版本
	 *
	 * @param appVersion the app version
	 * @param response the response
	 */
	@RequestMapping(value = "/setupLatest",method = RequestMethod.POST)
	public void setupLatest(AppVersion appVersion,HttpServletResponse response){
		try
		{
			logger.info(appVersion.toString());
			if(StringUtils.isNotBlank(appVersion.getAppName())&&StringUtils.isNotBlank(appVersion.getAppVersion())){
				List<AppVersion>list = appVersionService.selectByAppNameAndVersion(appVersion);
				if(list != null && list.size() > 0){
					appVersion = list.get(0);
					if(appVersion.getIsReleased().equals(0)){
						throw new CustomException(ErrorCode.NOT_RELEASED.getErrorCode() , ErrorCode.NOT_RELEASED.getZhMessage());
					}else{
						appVersion.setUpdateTime(new Date());
						appVersionService.updateById(appVersion);
						AjaxUtils.renderJson(response, ResultPageDOHelper.getMsgResultDO(UserErrorCode.SUCCESS.getErrorCode(), "设置最新版本成功！"));
					}

				}else{
					throw new CustomException(ErrorCode.NOTEXIST.getErrorCode() , ErrorCode.NOTEXIST.getZhMessage());
				}
			}else{
				throw new CustomException(ErrorCode.PARAM_ERROR.getErrorCode() , ErrorCode.PARAM_ERROR.getZhMessage() + "appName=>" + appVersion.getAppName()+",appVersion=>"+appVersion.getAppVersion());
			}
	        
		}
		catch (Exception e)
		{
			logger.error("getAppVersion error! ");
			exceptionOperate(e, response);
		}
		
	}
	
	/**
	 * Setup latest. 发布版本
	 *
	 * @param appVersion the app version
	 * @param response the response
	 */
	@RequestMapping(value = "/setupReleased",method = RequestMethod.POST)
	public void setupReleased(AppVersion appVersion,HttpServletResponse response){
		try
		{
			logger.info(appVersion.toString());
			if(StringUtils.isNotBlank(appVersion.getAppName())&&StringUtils.isNotBlank(appVersion.getAppVersion())&&appVersion.getIsReleased() != null){
				List<AppVersion>list = appVersionService.selectByAppNameAndVersion(appVersion);
				if(list != null && list.size() > 0){
					AppVersion old = list.get(0);
					old.setIsReleased(appVersion.getIsReleased());
					old.setIsForced(appVersion.getIsForced());
//					old.setUpdateTime(new Date());   不更新时间
					appVersionService.updateById(old);
					AjaxUtils.renderJson(response, ResultPageDOHelper.getMsgResultDO(UserErrorCode.SUCCESS.getErrorCode(), "发布版本成功！"));

				}else{
					throw new CustomException(ErrorCode.NOTEXIST.getErrorCode() , ErrorCode.NOTEXIST.getZhMessage());
				}
			}else{
				throw new CustomException(ErrorCode.PARAM_ERROR.getErrorCode() , ErrorCode.PARAM_ERROR.getZhMessage() + "appName=>" + appVersion.getAppName()+",appVersion=>"+appVersion.getAppVersion());
			}
	        
		}
		catch (Exception e)
		{
			logger.error("getAppVersion error! ");
			exceptionOperate(e, response);
		}
		
	}
	
	/**
	 * Gets the app version.根据项目名查询版本信息
	 *
	 * @param appVersion the app version
	 * @param response the response
	 * @return the app version
	 */
	@RequestMapping(value = "/getAppVersionJson",method = RequestMethod.POST)
	public void getAppVersionJson(@RequestBody AppVersion appVersion,HttpServletResponse response) {
		try
		{
			logger.info(appVersion.toString());
			if(StringUtils.isNotBlank(appVersion.getAppName())){
				List<AppVersion>list = appVersionService.selectByAppName(appVersion.getAppName());
				if(list != null && list.size() > 0){
					appVersion = list.get(0);
					AjaxUtils.renderJsonporJson(response, null,
							ResultPageDOHelper.getMsgResultDO(appVersion, "获取版本信息成功！"));
				}else{
					throw new CustomException(ErrorCode.NOTEXIST.getErrorCode() , ErrorCode.NOTEXIST.getZhMessage());
				}
			}else{
				throw new CustomException(ErrorCode.PARAM_ERROR.getErrorCode() , ErrorCode.PARAM_ERROR.getZhMessage() + "appName=>" + appVersion.getAppName());
			}
	        
		}
		catch (Exception e)
		{
			logger.error("getAppVersion error! ");
			exceptionOperate(e, response);
		}
		
	}
	
	/**
	 * Gets the all version by key.查询历史版本
	 *
	 * @param appVersion the app version
	 * @param response the response
	 * @return the all version by key
	 */
	@RequestMapping(value = "/getAllVersionByName",method = RequestMethod.POST)
	public void getAllVersionByKey(AppVersion appVersion,HttpServletResponse response){
		try
		{
			logger.info(appVersion.toString());
			if(StringUtils.isNotBlank(appVersion.getAppName())){
				List<AppVersion>list = appVersionService.selectHistoryByAppName(appVersion.getAppName());
				if(list != null && list.size() > 0){
					AjaxUtils.renderJsonporJson(response, null,
							ResultPageDOHelper.getMsgResultDO(list, "获取版本信息成功！"));
				}else{
					throw new CustomException(ErrorCode.NOTEXIST.getErrorCode() , ErrorCode.NOTEXIST.getZhMessage());
				}
			}else{
				throw new CustomException(ErrorCode.PARAM_ERROR.getErrorCode() , ErrorCode.PARAM_ERROR.getZhMessage() + "appName=>" + appVersion.getAppName());
			}
	        
		}
		catch (Exception e)
		{
			logger.error("getAllVersionByKey error! ");
			exceptionOperate(e, response);
		}
		
	}
	
	
	/**
	 * Gets the all app.获取所有项目的最新版本
	 *
	 * @param response the response
	 * @return the all app
	 */
	@RequestMapping(value = "/getAllApp",method = RequestMethod.POST)
	public void getAllApp(HttpServletResponse response){
		try
		{
			
			List<AppVersion>list = appVersionService.selectAllApp();
			AjaxUtils.renderJsonporJson(response, null,
					ResultPageDOHelper.getMsgResultDO(list, "获取版本信息成功！"));
	        
		}
		catch (Exception e)
		{
			logger.error("download error! ");
			exceptionOperate(e, response);
		}
		
	}
	
	
	/**
	 * Save app version. 保存版本信息(废弃)
	 *
	 * @param appVersion the app version
	 * @param response the response
	 */
	@RequestMapping(value = "/saveAppVersion",method = RequestMethod.POST)
	public void saveAppVersion(AppVersion appVersion,HttpServletResponse response){
		try
		{
			if(StringUtils.isNotBlank(appVersion.getAppUrl())){
				AppVersion newAppVersion = new AppVersion();
				String appKey = UUID.randomUUID().toString();
				newAppVersion.setAppKey(appKey);
				List<AppVersion>list = appVersionService.selectByAppName(appVersion.getAppName());
				if(list != null && list.size() > 0){
					AppVersion oldAppVersion = list.get(0);
					newAppVersion.setAppKey(oldAppVersion.getAppKey());
				}
				
				if(StringUtils.isNotBlank(appVersion.getAppName()))
					newAppVersion.setAppName(appVersion.getAppName());
				else
					newAppVersion.setAppName(appKey);
				newAppVersion.setAppUrl(appVersion.getAppUrl());
				if(StringUtils.isNotBlank(appVersion.getAppVersion()))
					newAppVersion.setAppVersion(appVersion.getAppVersion());
				else
					newAppVersion.setAppVersion("1.0");//默认版本号
				newAppVersion.setIsDelete(0);
				newAppVersion.setCreateTime(new Date());
				newAppVersion.setUpdateTime(new Date());
				appVersionService.save(newAppVersion);
				
				
				
				AjaxUtils.renderJsonporJson(response, null,
						ResultPageDOHelper.getMsgResultDO(newAppVersion, "保存版本信息成功！"));
			}else{
				throw new CustomException(ErrorCode.PARAM_ERROR.getErrorCode() , ErrorCode.PARAM_ERROR.getZhMessage() + "appUrl=>" + appVersion.getAppUrl());
			}
		}
		catch (Exception e)
		{
			logger.error("saveAppVersion error! ");
			exceptionOperate(e, response);
		}
		
	}
	
	/**
	 * Save app version. 保存版本信息(废弃)
	 *
	 * @param appVersion the app version
	 * @param response the response
	 */
	@RequestMapping(value = "/saveAppVersionJson",method = RequestMethod.POST)
	public void saveAppVersionJson(@RequestBody AppVersion appVersion,HttpServletResponse response){
		try
		{
			if(StringUtils.isNotBlank(appVersion.getAppUrl())){
				AppVersion newAppVersion = new AppVersion();
				String appKey = UUID.randomUUID().toString();
				newAppVersion.setAppKey(appKey);
				List<AppVersion>list = appVersionService.selectByAppName(appVersion.getAppName());
				if(list != null && list.size() > 0){
					AppVersion oldAppVersion = list.get(0);
					newAppVersion.setAppKey(oldAppVersion.getAppKey());
				}
				
				if(StringUtils.isNotBlank(appVersion.getAppName()))
					newAppVersion.setAppName(appVersion.getAppName());
				else
					newAppVersion.setAppName(appKey);
				newAppVersion.setAppUrl(appVersion.getAppUrl());
				if(StringUtils.isNotBlank(appVersion.getAppVersion()))
					newAppVersion.setAppVersion(appVersion.getAppVersion());
				else
					newAppVersion.setAppVersion("1.0");//默认版本号
				newAppVersion.setIsDelete(0);
				newAppVersion.setCreateTime(new Date());
				newAppVersion.setUpdateTime(new Date());
				appVersionService.save(newAppVersion);
				
				
				
				AjaxUtils.renderJsonporJson(response, null,
						ResultPageDOHelper.getMsgResultDO(newAppVersion, "保存版本信息成功！"));
			}else{
				throw new CustomException(ErrorCode.PARAM_ERROR.getErrorCode() , ErrorCode.PARAM_ERROR.getZhMessage() + "appUrl=>" + appVersion.getAppUrl());
			}
		}
		catch (Exception e)
		{
			logger.error("saveAppVersion error! ");
			exceptionOperate(e, response);
		}
		
	}
	
	
	/**
	 * Upload version.
	 *
	 * @param fVersion the f version
	 * @param appFile the app file
	 * @param request the request
	 * @param response the response
	 */
	@RequestMapping(value = "/uploadVersion",method = RequestMethod.POST)
	public void uploadVersion(FileVersion fVersion,MultipartFile appFile,HttpServletRequest request, HttpServletResponse response){
		
				try{
					if(appFile == null)
						throw new CustomException(ErrorCode.PARAM_ERROR.getErrorCode() , ErrorCode.PARAM_ERROR.getZhMessage() + "appFile=>" + appFile);
					
					//1.以项目名作为文件名
					//原始文件名称
					String originalFileName =  appFile.getOriginalFilename();
					//检查后缀名
					String fileFormat = originalFileName.substring(originalFileName.lastIndexOf("."));
					if(!(fileFormat.equals(".ipa"))&&!(fileFormat.equals(".apk"))){
						throw new CustomException(ErrorCode.FILE_FORMAT_ERROR.getErrorCode() , ErrorCode.FILE_FORMAT_ERROR.getZhMessage() + "file format=>" + originalFileName.substring(originalFileName.lastIndexOf(".")));
					}
					
					if(fileFormat.equals(".ipa")){
						//如果是ipa文件
						//1.文件保存到服务器
						String webRootPath = request.getSession().getServletContext().getRealPath("/");
						String dir = UUID.randomUUID().toString();
						String newFileName = UUID.randomUUID().toString() + originalFileName;
						String dirPath = webRootPath + "temp/" + dir;
						String filePath = dirPath + "/" +  newFileName;
						
						logger.info("文件完整路径:" + filePath);
						File ipaFile = new java.io.File(filePath);
						if(!ipaFile.exists()){
							ipaFile.mkdirs();
						}
						//向磁盘写文件
						appFile.transferTo(ipaFile);
						
						//2.ipa拆包
						String unzipPath = UnzipIpaUtil.unzipIpa(ipaFile);
						System.out.println("解压缩位置:"+unzipPath);
						
						//查找Info.plist InfoPlist.strings appicon
						String infoPath = getInfoPlistFromUnzipPath(unzipPath, infoPlistName);
//						String infoStringsPath = getFilePath(unzipPath, infoPlistNameStrings);
						String appiconPath = getAppIconPath(unzipPath, appIcon);
						System.out.println("Info.plist地址===>"+infoPath);
//						System.out.println("InfoPlist.strings地址===>"+infoStringsPath);
						System.out.println("appIcon地址====>"+appiconPath);
						//解析info.plist,获取项目中文名，版本号,bundleId,appicon
						Map<String,String> infoMap = UnzipIpaUtil.getIpaInfoMap(new File(infoPath));
						for (String key : infoMap.keySet())
						{
							System.out.println("infoPlist信息===>"+key+"===>"+infoMap.get(key));
						}
						String CFBundleIdentifier = infoMap.get(UnzipIpaUtil.CFBundleIdentifier);
						String CFBundleVersion = infoMap.get(UnzipIpaUtil.CFBundleVersion);
						String CFBundleDisplayName = infoMap.get(UnzipIpaUtil.CFBundleDisplayName);
						String CFBundleName = infoMap.get(UnzipIpaUtil.CFBundleName);
						if(StringUtils.isBlank(CFBundleDisplayName)){
							CFBundleDisplayName = CFBundleName;
						}
						
						//3.上传ipa 文件
						//新文件名称   xxx.ipa
						newFileName = CFBundleName + originalFileName.substring(originalFileName.lastIndexOf("."));
						//保存路径    项目名/版本号/时间戳/xxx.ipa
						Long nowTime = new Date().getTime();
						String ossPath = CFBundleName+"/"+CFBundleVersion + "/"+ nowTime + "/" +newFileName;
						
						boolean success = OssUtil.uploadFile(OssUtil.fileObjectKey,ossPath, new FileInputStream(ipaFile));
						if(success){
							String ipaFullPath = OssUtil.uploadPoint + "/" + OssUtil.fileObjectKey + "/" + ossPath;
							System.out.println("上传oss的ipa地址:" + ipaFullPath);
							//上传app图标
							String iconFullPath = "";
							String iconOssPath = CFBundleName+"/"+CFBundleVersion + "/"+ nowTime + appiconPath.substring(appiconPath.lastIndexOf("/"));
							success = OssUtil.uploadFile(OssUtil.fileObjectKey,iconOssPath, new FileInputStream(new File(appiconPath)));
							if(success)
								iconFullPath = OssUtil.uploadPoint + "/" + OssUtil.fileObjectKey + "/" + iconOssPath;
							System.out.println("上传oss的icon地址:" + iconFullPath);
							
							//4.生成plist文件
							//读取模板
							String templatePath = AppVersionController.class.getClassLoader().getResource("template.plist").getPath();
							System.out.println("读取模板......");
							String fileContent = PlistUtil.readPlistToString(templatePath);
							//替换数据，并生成文件
							fileContent = fileContent.replace("[oss-ipa]", ipaFullPath);
							fileContent = fileContent.replace("[oss-icon]", iconFullPath);
							fileContent = fileContent.replace("[bundle-identifier]", CFBundleIdentifier);
							fileContent = fileContent.replace("[bundle-version]", CFBundleVersion);
							fileContent = fileContent.replace("[app-name]", CFBundleDisplayName);
							
							String newPlistPath = dirPath + "/" + CFBundleName +".plist";
							PlistUtil.createPlistFromString(newPlistPath, fileContent);
							//上传plist文件
						    String plistOssPath = CFBundleName+"/" + CFBundleVersion+ "/"+ nowTime + "/"+CFBundleName + ".plist";
							success = OssUtil.uploadFile(OssUtil.fileObjectKey,plistOssPath, new FileInputStream(new File(newPlistPath)));
							if(success){
								//5.保存版本信息到数据库
								String plistFullPath = OssUtil.uploadPoint + "/" + OssUtil.fileObjectKey + "/" + plistOssPath;
								
								//5.1处理url
								plistFullPath = plistFullPath.replace("http", "itms-services://?action=download-manifest&url=https");
								
								
								FileVersion fileVersion = new FileVersion();
								fileVersion.setAppKey(UUID.randomUUID().toString());
								List<FileVersion>list = fileVersionService.selectByAppName(CFBundleName);
								if(list != null && list.size() > 0){
    								FileVersion oldAppVersion = list.get(0);
    								fileVersion.setAppKey(oldAppVersion.getAppKey());
    							}
								fileVersion.setAppName(CFBundleName);
								fileVersion.setAppUrl(plistFullPath);
								fileVersion.setAppVersion(CFBundleVersion);
								fileVersion.setCreateTime(new Date());
								fileVersion.setUpdateTime(new Date());
								fileVersion.setIsDelete(0);
								if(fVersion.getIsForced() != null)
									fileVersion.setIsForced(fVersion.getIsForced());
								if(fVersion.getIsReleased() != null)
									fileVersion.setIsReleased(fVersion.getIsReleased());
								if(fVersion.getDescription() != null)
									fileVersion.setDescription(fVersion.getDescription());
								if(fVersion.getDisplayVersion() != null)
									fileVersion.setDisplayVersion(fVersion.getDisplayVersion());
								
								fileVersionService.save(fileVersion);
								//返回plist文件oss地址
								AjaxUtils.renderJsonporJson(response, null,
								ResultPageDOHelper.getMsgResultDO(fileVersion, "上传文件成功！"));
							}else{
								throw new CustomException(ErrorCode.OSS_UPLOAD_ERROR.getErrorCode() , ErrorCode.OSS_UPLOAD_ERROR.getZhMessage());
							}
							//6.清理文件夹
							FileUtil.clearFiles(dirPath);
						}else{
							throw new CustomException(ErrorCode.OSS_UPLOAD_ERROR.getErrorCode() , ErrorCode.OSS_UPLOAD_ERROR.getZhMessage());
						}
						
					}else{
						//如果是apk文件
						
						//1.上传apk到服务器临时文件夹
						String webRootPath = request.getSession().getServletContext().getRealPath("/");
						String dir = UUID.randomUUID().toString();
						String newFileName = UUID.randomUUID().toString() + originalFileName;
						String dirPath = webRootPath + "temp/" + dir;
						String filePath = dirPath + "/" +  newFileName;
						
						logger.info("文件完整路径:" + filePath);
						File apkFile = new java.io.File(filePath);
						if(!apkFile.exists()){
							apkFile.mkdirs();
						}
						//向磁盘写文件
						appFile.transferTo(apkFile);
						//2.解析apk文件获取  包名  内部版本号    
						ApkInfo apkInfo = new ApkUtil().getApkInfo(filePath);
						String versionCode = apkInfo.getVersionCode();
						String packageName = apkInfo.getPackageName();
						//3.上传apk到oss
						Long nowTime = new Date().getTime();
						//新文件名称   例如 com.sankuai.meituan.apk
						newFileName = packageName + originalFileName.substring(originalFileName.lastIndexOf("."));
						//保存路径    包名/版本号/xxx.ipa
						String ossPath = packageName+"/"+versionCode+ "/"+ nowTime + "/"+newFileName;
						boolean success = OssUtil.uploadFile(OssUtil.fileObjectKey,ossPath, new FileInputStream(apkFile));
						if(success){
							String apkFullPath = OssUtil.uploadPoint + "/" + OssUtil.fileObjectKey + "/" + ossPath;
							System.out.println("上传oss的apk地址:" + apkFullPath);
							//4.保存版本信息到数据库
							FileVersion fileVersion = new FileVersion();
							fileVersion.setAppKey(UUID.randomUUID().toString());
							List<FileVersion>list = fileVersionService.selectByAppName(packageName);
							if(list != null && list.size() > 0){
								FileVersion oldAppVersion = list.get(0);
								fileVersion.setAppKey(oldAppVersion.getAppKey());
							}
							fileVersion.setAppName(packageName);
							fileVersion.setAppUrl(apkFullPath);
							fileVersion.setAppVersion(versionCode);
							fileVersion.setCreateTime(new Date());
							fileVersion.setUpdateTime(new Date());
							fileVersion.setIsDelete(0);
							if(fVersion.getIsForced() != null)
								fileVersion.setIsForced(fVersion.getIsForced());
							if(fVersion.getIsReleased() != null)
								fileVersion.setIsReleased(fVersion.getIsReleased());
							if(fVersion.getDescription() != null)
								fileVersion.setDescription(fVersion.getDescription());
							if(fVersion.getDisplayVersion() != null)
								fileVersion.setDisplayVersion(fVersion.getDisplayVersion());
							
							fileVersionService.save(fileVersion);
							//返回plist文件oss地址
							AjaxUtils.renderJsonporJson(response, null,
							ResultPageDOHelper.getMsgResultDO(fileVersion, "上传文件成功！"));
						}else{
							throw new CustomException(ErrorCode.OSS_UPLOAD_ERROR.getErrorCode() , ErrorCode.OSS_UPLOAD_ERROR.getZhMessage());
						}
						
						//5.删除临时文件夹
						FileUtil.clearFiles(dirPath);
						
					}
				}catch(Exception e){
					logger.error("upload error! ");
					exceptionOperate(e, response);
				}
	}
	
	
	/**
	 * Gets the app version.根据项目名查询最新的ipa/apk
	 *
	 * @param fileVersion the file version
	 * @param response the response
	 * @return the app version
	 */
	@RequestMapping(value = "/getFileVersion",method = RequestMethod.POST)
	public void getFileVersion(FileVersion fileVersion,HttpServletResponse response){
		try
		{
			logger.info(fileVersion.toString());
			if(StringUtils.isNotBlank(fileVersion.getAppName())){
				List<FileVersion>list = fileVersionService.selectByAppName(fileVersion.getAppName());
				if(list != null && list.size() > 0){
					fileVersion = list.get(0);
					AjaxUtils.renderJsonporJson(response, null,
							ResultPageDOHelper.getMsgResultDO(fileVersion, "获取版本信息成功！"));
				}else{
					throw new CustomException(ErrorCode.NOTEXIST.getErrorCode() , ErrorCode.NOTEXIST.getZhMessage());
				}
			}else{
				throw new CustomException(ErrorCode.PARAM_ERROR.getErrorCode() , ErrorCode.PARAM_ERROR.getZhMessage() + "appName=>" + fileVersion.getAppName());
			}
	        
		}
		catch (Exception e)
		{
			logger.error("getFileVersion error! ");
			exceptionOperate(e, response);
		}
		
	}
	
	/**
	 * Gets the app version.根据项目名查询最新的ipa/apk
	 *
	 * @param fileVersion the file version
	 * @param response the response
	 * @return the app version
	 */
	@RequestMapping(value = "/getFileVersionJson",method = RequestMethod.POST)
	public void getFileVersionJson(@RequestBody FileVersion fileVersion,HttpServletResponse response){
		try
		{
			logger.info(fileVersion.toString());
			if(StringUtils.isNotBlank(fileVersion.getAppName())){
				List<FileVersion>list = fileVersionService.selectByAppName(fileVersion.getAppName());
				if(list != null && list.size() > 0){
					fileVersion = list.get(0);
					AjaxUtils.renderJsonporJson(response, null,
							ResultPageDOHelper.getMsgResultDO(fileVersion, "获取版本信息成功！"));
				}else{
					throw new CustomException(ErrorCode.NOTEXIST.getErrorCode() , ErrorCode.NOTEXIST.getZhMessage());
				}
			}else{
				throw new CustomException(ErrorCode.PARAM_ERROR.getErrorCode() , ErrorCode.PARAM_ERROR.getZhMessage() + "appName=>" + fileVersion.getAppName());
			}
	        
		}
		catch (Exception e)
		{
			logger.error("getFileVersion error! ");
			exceptionOperate(e, response);
		}
		
	}
	
	
	
	
	/**
	 * Gets the all version by key.查询ipa/apk信息历史版本
	 *
	 * @param fileVersion the file version
	 * @param response the response
	 * @return the all version by key
	 */
	@RequestMapping(value = "/getAllFileVersionByName",method = RequestMethod.POST)
	public void getAllFileVersionByName(FileVersion fileVersion,HttpServletResponse response){
		try
		{
			logger.info(fileVersion.toString());
			if(StringUtils.isNotBlank(fileVersion.getAppName())){
				List<FileVersion>list = fileVersionService.selectByAppName(fileVersion.getAppName());
				if(list != null && list.size() > 0){
					AjaxUtils.renderJsonporJson(response, null,
							ResultPageDOHelper.getMsgResultDO(list, "获取版本信息成功！"));
				}else{
					throw new CustomException(ErrorCode.NOTEXIST.getErrorCode() , ErrorCode.NOTEXIST.getZhMessage());
				}
			}else{
				throw new CustomException(ErrorCode.PARAM_ERROR.getErrorCode() , ErrorCode.PARAM_ERROR.getZhMessage() + "appName=>" + fileVersion.getAppName());
			}
	        
		}
		catch (Exception e)
		{
			logger.error("getAllFileVersionByName error! ");
			exceptionOperate(e, response);
		}
		
	}
	
	
	
	/**
	 * Setup latest. 设置ipa/apk最新版本
	 *
	 * @param fileVersion the file version
	 * @param response the response
	 */
	@RequestMapping(value = "/setupFileLatest",method = RequestMethod.POST)
	public void setupFileLatest(FileVersion fileVersion,HttpServletResponse response){
		try
		{
			logger.info(fileVersion.toString());
			if(StringUtils.isNotBlank(fileVersion.getAppName())&&StringUtils.isNotBlank(fileVersion.getAppVersion())){
				List<FileVersion>list = fileVersionService.selectByAppNameAndVersion(fileVersion);
				if(list != null && list.size() > 0){
					fileVersion = list.get(0);
					if(fileVersion.getIsReleased().equals(0)){
						throw new CustomException(ErrorCode.NOT_RELEASED.getErrorCode() , ErrorCode.NOT_RELEASED.getZhMessage());
					}else{
						fileVersion.setUpdateTime(new Date());
						fileVersionService.updateById(fileVersion);
						AjaxUtils.renderJson(response, ResultPageDOHelper.getMsgResultDO(UserErrorCode.SUCCESS.getErrorCode(), "设置最新版本成功！"));
					}

				}else{
					throw new CustomException(ErrorCode.NOTEXIST.getErrorCode() , ErrorCode.NOTEXIST.getZhMessage());
				}
			}else{
				throw new CustomException(ErrorCode.PARAM_ERROR.getErrorCode() , ErrorCode.PARAM_ERROR.getZhMessage() + "appName=>" + fileVersion.getAppName()+",appVersion=>"+fileVersion.getAppVersion());
			}
	        
		}
		catch (Exception e)
		{
			logger.error("getAppVersion error! ");
			exceptionOperate(e, response);
		}
		
	}
	
	/**
	 * Setup latest. 发布ipa/apk版本
	 *
	 * @param fileVersion the file version
	 * @param response the response
	 */
	@RequestMapping(value = "/setupFileReleased",method = RequestMethod.POST)
	public void setupFileReleased(FileVersion fileVersion,HttpServletResponse response){
		try
		{
			logger.info(fileVersion.toString());
			if(StringUtils.isNotBlank(fileVersion.getAppName())&&StringUtils.isNotBlank(fileVersion.getAppVersion())&&fileVersion.getIsReleased() != null){
				List<FileVersion>list = fileVersionService.selectByAppNameAndVersion(fileVersion);
				if(list != null && list.size() > 0){
					FileVersion old = list.get(0);
					old.setIsReleased(fileVersion.getIsReleased());
					old.setIsForced(fileVersion.getIsForced());
//					old.setUpdateTime(new Date());   不更新时间
					fileVersionService.updateById(old);
					AjaxUtils.renderJson(response, ResultPageDOHelper.getMsgResultDO(UserErrorCode.SUCCESS.getErrorCode(), "发布版本成功！"));

				}else{
					throw new CustomException(ErrorCode.NOTEXIST.getErrorCode() , ErrorCode.NOTEXIST.getZhMessage());
				}
			}else{
				throw new CustomException(ErrorCode.PARAM_ERROR.getErrorCode() , ErrorCode.PARAM_ERROR.getZhMessage() + "appName=>" + fileVersion.getAppName()+",appVersion=>"+fileVersion.getAppVersion());
			}
	        
		}
		catch (Exception e)
		{
			logger.error("getAppVersion error! ");
			exceptionOperate(e, response);
		}
		
	}
	
	
	/**
	 * Do test.
	 *
	 * @param fileVersion the file version
	 * @param response the response
	 */
	@RequestMapping(value = "/doTest",method = RequestMethod.POST)
	public void doTest(FileVersion fileVersion,HttpServletResponse response){
		try
		{
			logger.info(fileVersion.toString());
			File f = new File(fileVersion.getAppUrl()); 
			System.out.println("是否存在:"+f.exists());
			Map<String,String> infoMap = UnzipIpaUtil.getIpaInfoMap(f);
			for (String key : infoMap.keySet())
			{
				System.out.println("infoPlist信息===>"+key+"===>"+infoMap.get(key));
			}
	        
		}
		catch (Exception e)
		{
			logger.error("getAppVersion error! ");
			exceptionOperate(e, response);
		}
		
	}
	
	
	public String getInfoPlistFromUnzipPath (String dirPath,String key){
		File dir = new File(dirPath);  
        File[] searchFile = TextSearchFileUtil.searchFile(dir, key);
        for (File file : searchFile)
		{
        	System.out.println("所有infoplist路径:" + file.getAbsolutePath());
			if(file.getAbsolutePath().contains(".app/" + key)){
				return file.getAbsolutePath();
			}
        	
		}
        
        return searchFile[0].getAbsolutePath();
	}
	
	
	/**
	 * Gets the file path. 从一个文件夹中递归查找含有关键字的文件
	 *
	 * @param dirPath the dir path
	 * @param key the key
	 * @return the file path
	 */
	public String getFilePath (String dirPath,String key){
		 // 查找目录  
        File dir = new File(dirPath);  
        File[] searchFile = TextSearchFileUtil.searchFile(dir, key);
     
        return searchFile[0].getAbsolutePath();
	}
	
	
	
	
	/**
	 * Gets the app icon path.从一个文件夹中递归查找含有关键字并且大小最大的app图标
	 *
	 * @param dirPath the dir path
	 * @param key the key
	 * @return the app icon path
	 */
	public String getAppIconPath(String dirPath,String key){
		 // 查找目录  
        File dir = new File(dirPath);  
        File[] searchFile = TextSearchFileUtil.searchFile(dir, key);
        File maxFile = searchFile[0];
        for (File file : searchFile)
		{
			if(file.length() > maxFile.length()){//2G以内的文件大小比较用length没问题
				maxFile = file;
			}
		}
        return maxFile.getAbsolutePath();
	}
	
	
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException
	{
//		String templatePath = AppVersionController.class.getClassLoader().getResource("template.plist").getPath();
//		File templateFile = new File(templatePath);
//		System.out.println(templateFile.exists());
//		
//		System.out.println("开始读取。。。。");
//		String fileContent = PlistUtil.readPlistToString(templatePath);
//		fileContent = fileContent.replace("[oss-ipa]", "www.baidu.com");
//		fileContent = fileContent.replace("[oss-icon]", "www.ipa.com");
//		fileContent = fileContent.replace("[bundle-identifier]", "com.defa.tcf");
//		fileContent = fileContent.replace("[bundle-version]", "1.0.1");
//		fileContent = fileContent.replace("[app-name]", "tcf");
////		System.out.println(PlistUtil.readPlistToString(templatePath));
//		//[oss-ipa]   [oss-icon]  [bundle-identifier]    
//		
//		
//		ByteArrayInputStream stream = new ByteArrayInputStream(fileContent.getBytes());
//		File plist = new File("/Users/defa/Downloads/测试.plist");
//		OutputStream os = new FileOutputStream(plist);
//		int bytesRead = 0;
//		   byte[] buffer = new byte[8192];
//		   while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
//		      os.write(buffer, 0, bytesRead);
//		   }
//		   os.close();
//		   stream.close();
		
		
		try {
			String apkpath = "/Users/defa/Downloads/meituan.apk";
			if (args.length > 0) {
				apkpath = args[0];
			}
			ApkInfo apkInfo = new ApkUtil().getApkInfo(apkpath);
			// 打印获取到的信息
			System.out.println(apkInfo);
			// 获取Icon并保存到指定位置
			Map<String, String> applicationIcons = apkInfo.getApplicationIcons();
			for (String key : applicationIcons.keySet())
			{
				System.out.println("key===>"+key+",value===>"+applicationIcons.get(key));
			}
			IconUtil.extractFileFromApk(apkpath, apkInfo.getApplicationIcon(), "/Users/defa/Downloads/test/icon.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
