/**
 * 
 */
package com.tccv.core.spring.mail;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.tccv.core.config.SystemConfig;

/**
 * 此类描述的是：.
 *
 * @author: chenshanben
 * @version: 2016年4月19日 上午10:51:09
 */
public class SendErrorMail
{
	
	/** The java mail sender. */
	private static JavaMailSenderImpl javaMailSender;
	
	/**
	 * Gets the java mail sender impl.
	 *
	 * @return the java mail sender impl
	 */
	private static JavaMailSenderImpl getJavaMailSenderImpl(){
		if (javaMailSender == null)
		{
			synchronized (SendErrorMail.class)
			{
				if(javaMailSender == null){
					@SuppressWarnings("resource")
					ApplicationContext context = new ClassPathXmlApplicationContext(SystemConfig.getClassPathXmlApplicationContext());
					javaMailSender = (JavaMailSenderImpl) context.getBean("javaMailSender");
				}
			}
		}
		return javaMailSender;
	}
	
	/** The to mail. */
	private static String[] toMail = new String[]{
		"platform@o2otown.cn" 
		};
	
	/**
	 * 发送错误信息的邮件.
	 *
	 * @param context the context
	 * @param title the title
	 * @param e the e
	 */
	public static void sendErrorMail(String context, String title, Exception e){
		MailSender mailSender = new MailSender();
		mailSender.setJavaMailSender(getJavaMailSenderImpl());
		String message = combExceptionContext(e);
		mailSender.sendMail(context + "\n" + message, title, toMail);
	}
	
	/**
	 * 发送错误信息的邮件.
	 *
	 * @param title the title
	 * @param e the e
	 */
	public static void sendErrorMail(String title, Exception e){
		sendErrorMail("", title, e);
	}
	
	/**
	 * 组装错误信息.
	 *
	 * @param e the e
	 * @return the string
	 */
	public static String combExceptionContext(Exception e){
		StringBuilder context = new StringBuilder();
		context.append("错误栈信息" + "\n");
		/*context.append(e.getClass().getName());
		context.append(": " + e.getMessage() + "\n");*/
		context.append(e + "\n");
		
		for (StackTraceElement element : e.getStackTrace())
		{
			context.append("\tat " + element + "\n");
		}
		return context.toString();
	}
	
}
