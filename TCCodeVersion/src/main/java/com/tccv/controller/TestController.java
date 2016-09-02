/**   
* @Title: TestController.java 
* @Package com.cn.styf.controller 
* @Description: TODO
* @author styf  
* @date 2015年12月13日 上午10:13:52   
*/
package com.tccv.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tccv.exception.CustomException;
import com.tccv.pojo.AppVersion;
import com.tccv.service.AppVersionService;

/** 
* @ClassName: TestController 
* @Description: TODO
* @author styf 
* @date 2015年12月13日 上午10:13:52  
*/
@Controller
@RequestMapping("/test")
public class TestController extends BaseController{
	
	@Autowired
	private AppVersionService appVersionService;
	
	@RequestMapping("/testTccv")
	public void testTccv(){
		AppVersion av = new AppVersion();
		av.setAppKey("kay");
		av.setAppName("name");
		av.setAppUrl("www.baidu.com");
		av.setAppVersion("1.0.0");
		av.setIsDelete(0);
		av.setCreateTime(new Date());
		av.setUpdateTime(new Date());
		
		appVersionService.save(av);
	}
	
	/**
	 * 
	* @Title: requestJson 
	* @Description: 测试json
	* @param @param user
	* @param @return
	* @param @throws Exception    
	* @return User    
	* @throws
	 */
//	@RequestMapping("/requestJson")
//	public @ResponseBody User requestJson(@RequestBody User user)throws Exception{
//		user.setNickname("styf");
//		
//		logger.info("sssssssssssssss");
//		logger.info("ddddddddddddd");
//		logger.info(user.getUsername());
//		return user;
//	}
	
	/**
	 * 
	* @Title: testRequestPatam 
	* @Description: 测试RequestPatam
	* @param @param id    
	* @return void    
	* @throws
	 */
	@RequestMapping(value="testRequestPatam",method={RequestMethod.POST})
	public void testRequestPatam(@RequestParam(value="userId",required=false,defaultValue = "1")Integer id){
		logger.info("userid:"+id);
	}
	
	/**
	 * 
	* @Title: testDateConverter 
	* @Description: 测试时间转换器
	* @param @param time    
	* @return void    
	* @throws
	 */
	@RequestMapping(value ="testDateConverter",method={RequestMethod.POST})
	public void testDateConverter(Date time){
		logger.info("传入的时间为:"+time);
	}
	
	
	/**
	 * 
	* @Title: testUploadFile 
	* @Description: 测试文件上传
	* @param @param pictureFile
	* @param @throws IllegalStateException
	* @param @throws IOException    
	* @return void    
	* @throws
	 */
	@RequestMapping("/testUploadFile")
	public void testUploadFile(MultipartFile pictureFile) throws IllegalStateException, IOException{
				//原始文件名称
				String pictureFile_name =  pictureFile.getOriginalFilename();
				logger.info("原始文件名称:"+pictureFile_name);
				//新文件名称
				String newFileName = UUID.randomUUID().toString()+pictureFile_name.substring(pictureFile_name.lastIndexOf("."));
				
				//上传图片
				File uploadPic = new java.io.File("/Users/defa/Desktop/"+newFileName);
				
				if(!uploadPic.exists()){
					uploadPic.mkdirs();
				}
				
				
				//向磁盘写文件
				pictureFile.transferTo(uploadPic);

	}
	
	/**
	 * 
	* @Title: testException 
	* @Description: 测试异常处理器
	* @param @throws CustomException    
	* @return void    
	* @throws
	 */
	@RequestMapping("/testException")
	public void testException() throws CustomException{
		throw new CustomException("测试一下异常","");
	}
	
	/**
	 * 
	* @Title: testRestFul 
	* @Description: 测试RestFul
	* @param @throws CustomException    
	* @return void    
	* @throws
	 */
	@RequestMapping("/testRestFul/{id}")
	public void testRestFul(@PathVariable("id") Integer id) throws CustomException{
		System.out.println("测试RestFul_id="+id);
	}
}
