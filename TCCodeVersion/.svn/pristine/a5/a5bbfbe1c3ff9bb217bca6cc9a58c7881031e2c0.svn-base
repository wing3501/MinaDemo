package com.tccv.core.util.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tccv.core.util.constant.CommonConstants;
import com.tccv.core.util.image.ImageUtils;
import com.tccv.core.util.time.TimeUtils;



/**
 * 文件Utils
 * @author chenjunhui 
 */
public class FileUtils {
	
	
	private final static Logger logger = LoggerFactory.getLogger(FileUtils.class);

	public static final String FILE_SEPARATOR = "/";

	/**
	 * 请fileDirs不要为空
	 * 
	 * @param fileDirs
	 * @return
	 */
	public static String getFileDir(final String imageDir,String... fileDirs) {
		String dir = imageDir;
		for (String fileDir : fileDirs) {
			dir = dir + FILE_SEPARATOR + fileDir;
		}
		dir += FILE_SEPARATOR;
		File file = new File(dir);
		if (!file.exists()) {
			file.mkdirs();
		}
		return dir;
	}

	public static String getFileName(String savedFilePath,String proFileName, String... fileDirs) {
		return getFileDir(savedFilePath,fileDirs) + getRandomFileName(proFileName);
	}

	public static String getDateFileName(String proFileName) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		String fileName = getFileName(proFileName, String.valueOf(year), String
				.valueOf(month));
		File file = new File(fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				fileName = null;
			}
		}
		return fileName;
	}

	/**
	 * 先调用其他方法包装proFileName不为空且含有"."
	 * 
	 * @param proFileName
	 * @return
	 */
	public static String getRandomFileName(String proFileName) {
		int length = proFileName.lastIndexOf(".");
		if(length==-1){
			return UUID.randomUUID().toString();
		}
		String suffix = proFileName.substring(length);
		return UUID.randomUUID().toString() + suffix;
	}

	/**
	 * 是否是Image
	 * 
	 * @param fileType
	 * @return
	 */
	public static boolean isImage(String fileType) {
		for (String type : CommonConstants.imgFileTypes) {
			if (type.equals(fileType))
				return true;
		}
		return false;
	}

	/**
	 * 将FTP地址改成web地址
	 * 
	 * @param ftpDir
	 * @param imgRoot
	 * @return
	 */
	public static String changeToWebRoot(String ftpDir, String imgRoot) {
		int index = ftpDir.indexOf(imgRoot) + imgRoot.length();
		return ftpDir.substring(index).replace(
				System.getProperty("file.separator"), "/");
	}

	public static void main(String args[]) {
		System.out.println(FileUtils.getFileDir("D:/aa", DateFormatUtils.format(new Date(), "yyyy-MM-dd")));
		/*System.out.println(getRandomFileName("aaaa.jpg"));
		System.out.println(FileUtils.getFileName("aaaa.jpg", "2011","10"));
		System.out.println(FileUtils.changeToWebRoot(FileUtils.getFileName("aaaa.jpg", "2011","10"), "d:/sdfsdf"));*/
	}
	
	public static final String IMG_START = "image/";
	
	public static boolean isImage(String[] imgTypes){
		if(imgTypes!=null && imgTypes.length>0){
			for(String imgType:imgTypes){
				if(!imgType.startsWith(IMG_START)){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 保存文件
	 * @param stream
	 * @param fileName
	 * @param fileContentType
	 * @return
	 */
	public static String saveFile(InputStream stream,String saveImageDir,String fileName, String fileContentType){
		try{
			String realPath = FileUtils.getFileName(saveImageDir,fileName, String
					.valueOf(TimeUtils.getCurrentYear()), String
					.valueOf(TimeUtils.getCurrentMonth()));
			IOUtils.copyLarge(stream, new FileOutputStream(realPath));
			return changeToWebRoot(realPath, saveImageDir);	
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			try {
				stream.close();
			} catch (IOException e) {
				logger.error("close stream fail",e);
			}
		}
	}
	
	public static String saveFile(File file,String fileName,String saveFileDir, String fileContentType){
		try{
			InputStream stream = new FileInputStream(file);
			return FileUtils.saveFile(stream,saveFileDir, fileName, fileContentType);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 保存图片 并返回物理路径和 相对web路径
	 * @param file
	 * @param fileName
	 * @param fileContentType
	 * @param width
	 * @param height
	 * @return String[] length=2 String[0] = 物理路径 String[1] = 相对web路径 
	 */
	public static String saveImage(File file,String savedDir,String fileName,String fileContentType,int width,int height){
		try{
			InputStream stream = new FileInputStream(file);
			return saveImage(stream,savedDir,fileName, fileContentType, width, height);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @param stream
	 * @param saveImageDir 需要保存图片到那个目录
	 * @param fileName
	 * @param fileContentType
	 * @param width
	 * @param height
	 * @return
	 */
	public static String saveImage(InputStream stream,String saveImageDir,String fileName, String fileContentType,
			int width, int height){
		try {
			if (!StringUtils.startsWith(fileContentType, IMG_START)) {
				throw new RuntimeException("the file type is not image");
			}
			String realPath = FileUtils.getFileName(saveImageDir,fileName, String
					.valueOf(TimeUtils.getCurrentYear()), String
					.valueOf(TimeUtils.getCurrentMonth()));
			if (width > 0 || height > 0) { // 传了限制宽高的话 就压缩
				ImageUtils.saveImageAsJpg(stream, realPath, width, height, true);
			} else {// 不压缩的话 直接保存
				IOUtils.copyLarge(stream, new FileOutputStream(realPath));
			}
			return changeToWebRoot(realPath, saveImageDir);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			try {
				stream.close();
			} catch (IOException e) {
				logger.error("close stream fail",e);
			}
		}
	}
	
	/**
	 * 保存图片 并返回物理路径和 相对web路径
	 * @param file
	 * @param fileName
	 * @param fileContentType
	 * @return String[] length=2 String[0] = 物理路径 String[1] = 相对web路径 
	 */
	public static String saveImage(File file,String savedDir,String fileName,String fileContentType){
		return FileUtils.saveImage(file, savedDir,fileName, fileContentType, 0, 0);
	}
	
	public static String saveImage(InputStream stream,String savedDir,String fileName,String fileContentType){
		return FileUtils.saveImage(stream, savedDir,fileName, fileContentType, 0, 0);
	}
	
	
	
	static boolean flag;
	static File file;
	// 验证字符串是否为正确路径名的正则表达式
	private static String matches = "[A-Za-z]:\\\\[^:?\"><*]*";
	// 通过 sPath.matches(matches) 方法的返回值判断是否正确
	// sPath 为路径字符串
    /**
     *  根据路径删除指定的目录或文件，无论存在与否
     *@param sPath  要删除的目录或文件
     *@return 删除成功返回 true，否则返回 false。
     */
    public static boolean DeleteFolder(String sPath) {
        flag = false;
        file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                return deleteFile(sPath);
            } else {  // 为目录时调用删除目录方法
                return deleteDirectory(sPath);
            }
        }
    }
    /**
     * 删除单个文件
     * @param   sPath    被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        flag = false;
        file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
    /**
     * 删除目录（文件夹）以及目录下的文件
     * @param   sPath 被删除目录的文件路径
     * @return  目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }
}
