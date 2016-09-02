/**
 * 
 */
package com.tccv.core.config;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 此类描述的是：   系统配置.
 *
 * @author: chenshanben
 * @version: 2016年3月8日 下午5:00:08
 */
public class SystemConfig
{
	
	/** 静态文件路径. */
	public static String staticRoot;
	
	/** 上传文件本地路径. */
	public static String uploadPath;
	
	/** 支付宝批量转账回调通知url. */
	public static String alipayBatchTransferAccountNotifyUrl;
	
	/** 应用上下文的路径，通俗的说就是spring的配置文件名. */
	public static String classPathXmlApplicationContext;
	
	/** 域名. */
	public static String domainName;
	
	/** 项目的名称：如，同城免，同城付. */
	public static String projectName;
	
	/** 错误页面. */
	public static String errorPage;
	
	public static String getErrorPage()
	{
		return errorPage;
	}





	
	public static void setErrorPage(String errorPage)
	{
		SystemConfig.errorPage = errorPage;
	}





	public static String getProjectName()
	{
		return projectName;
	}




	
	public static void setProjectName(String projectName)
	{
		SystemConfig.projectName = projectName;
	}




	public static String getDomainName()
	{
		return domainName;
	}



	
	public static void setDomainName(String domainName)
	{
		SystemConfig.domainName = domainName;
	}



	public static String getClassPathXmlApplicationContext()
	{
		return classPathXmlApplicationContext;
	}


	
	public static void setClassPathXmlApplicationContext(String classPathXmlApplicationContext)
	{
		SystemConfig.classPathXmlApplicationContext = classPathXmlApplicationContext;
	}


	public static String getStaticRoot()
	{
		return staticRoot;
	}

	
	public static void setStaticRoot(String staticRoot)
	{
		SystemConfig.staticRoot = staticRoot;
	}

	
	public static String getUploadPath()
	{
		return uploadPath;
	}

	
	public static void setUploadPath(String uploadPath)
	{
		SystemConfig.uploadPath = uploadPath;
	}

	
	public static String getAlipayBatchTransferAccountNotifyUrl()
	{
		return alipayBatchTransferAccountNotifyUrl;
	}

	
	public static void setAlipayBatchTransferAccountNotifyUrl(String alipayBatchTransferAccountNotifyUrl)
	{
		SystemConfig.alipayBatchTransferAccountNotifyUrl = alipayBatchTransferAccountNotifyUrl;
	}


	public SystemConfig()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
