package com.tccv.core.util.code;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码工具类.
 */
public class CodeUtils
{
	private static final String CHARSET = "utf-8";  
    private static final String FORMAT_NAME = "JPG";  
    // 二维码尺寸  
    private static final int QRCODE_SIZE = 300;  
  
    private static BufferedImage createImage(String content, String imgPath,  
            boolean needCompress, Integer width_logo, Integer height_logo) throws Exception {  
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();  
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);  
        hints.put(EncodeHintType.MARGIN, 1);  
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,  
                BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);  
        int width = bitMatrix.getWidth();  
        int height = bitMatrix.getHeight();  
        BufferedImage image = new BufferedImage(width, height,  
                BufferedImage.TYPE_INT_RGB);  
        for (int x = 0; x < width; x++) {  
            for (int y = 0; y < height; y++) {  
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000  
                        : 0xFFFFFFFF);  
            }  
        }  
        if (StringUtils.isBlank(imgPath)) {  
            return image;  
        }  
        // 插入logo图片  
        if(width_logo!=null&&height_logo!=null){
        	insertImage(image, imgPath, needCompress,width_logo,height_logo);  
        }
        return image;  
    }
    
    /** 
     * 插入LOGO 
     *  
     * @param source 
     *            二维码图片 
     * @param imgPath 
     *            LOGO图片地址 
     * @param needCompress 
     *            是否压缩 
     * @throws Exception 
     */  
    private static void insertImage(BufferedImage source, String imgPath,  
            boolean needCompress, Integer width_logo, Integer height_logo) throws Exception {  
        File file = new File(imgPath);  
        if (!file.exists()) {  
            System.err.println(""+imgPath+"   该文件不存在！");  
            return;  
        }  
        Image src = ImageIO.read(new File(imgPath));  
        int width = src.getWidth(null);  
        int height = src.getHeight(null);  
        if (needCompress) { // 压缩LOGO  
            if (width > width_logo) {  
                width = width_logo;  
            }  
            if (height > height_logo) {  
                height = height_logo;  
            }  
            Image image = src.getScaledInstance(width, height,  
                    Image.SCALE_SMOOTH);  
            BufferedImage tag = new BufferedImage(width, height,  
                    BufferedImage.TYPE_INT_RGB);  
            Graphics g = tag.getGraphics();  
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图  
            g.dispose();  
            src = image;  
        }  
        // 插入LOGO  
        Graphics2D graph = source.createGraphics();  
        int x = (QRCODE_SIZE - width) / 2;  
        int y = (QRCODE_SIZE - height) / 2;  
        graph.drawImage(src, x, y, width, height, null);  
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);  
        graph.setStroke(new BasicStroke(3f));  
        graph.draw(shape);  
        graph.dispose();  
    }
    
    /**
     * 
     * @param content 解析二维码指向的路径
     * @param name 二维码图片名称
     * @param imgPath 嵌入的logo的存放路径
     * @param path 二维码存放路径
     * @param width 二维码嵌入logo保存的宽度
     * @param height 二维码嵌入logo保存的高度
     * @return
     */
	 public static String createTwoDimensionalCode(String content, String name,String imgPath,
				String path, Integer width, Integer height) {
	    	try
			{
				StringBuffer sb = new StringBuffer(path);
				sb.append("/").append(name).append(".jpg");
				encode(content, imgPath, path, name, true,width,height);
				return sb.toString();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
	    }
	    /** 
	     * 生成二维码(内嵌LOGO) 
	     *  
	     * @param content 
	     *            内容 
	     * @param imgPath 
	     *            LOGO地址 
	     * @param destPath 
	     *            存放目录 
	     * @param needCompress 
	     *            是否压缩LOGO 
	     * @throws Exception 
	     */  
	    public static File encode(String content, String imgPath, String destPath, String name, 
	            boolean needCompress, Integer width, Integer height) throws Exception {  
	        BufferedImage image = createImage(content, imgPath,  
	                needCompress,width,height);  
	        mkdirs(destPath);
	        File file = new File(destPath+"/"+name);
	        ImageIO.write(image, FORMAT_NAME, file);
	        return file;
	    }  
	    
	    /** 
	     * 生成二维码(无内嵌LOGO) 
	     *  
	     * @param content 
	     *            内容 
	     * @param imgPath 
	     *            LOGO地址 
	     * @param destPath 
	     *            存放目录 
	     * @param needCompress 
	     *            是否压缩LOGO 
	     * @throws Exception 
	     */  
	    public static File encode(String content, String destPath, String name
	            ) throws Exception {  
	        return encode(content, null, destPath, name, false, null, null);
	    }  
	  
	    /**
	     * 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常) 
	     * @param destPath 存放目录 
	     */  
	    public static void mkdirs(String destPath) {  
	        File file =new File(destPath);      
	        if (!file.exists() && !file.isDirectory()) {  
	            file.mkdirs();  
	        }  
	    }  
	  
	    /** 
	     * 生成二维码(内嵌LOGO) 
	     *  
	     * @param content 
	     *            内容 
	     * @param imgPath 
	     *            LOGO地址 
	     * @param output 
	     *            输出流 
	     * @param needCompress 
	     *            是否压缩LOGO 
	     * @throws Exception 
	     */  
	    public static void encode(String content, String imgPath,  
	            OutputStream output, boolean needCompress) throws Exception {  
	        BufferedImage image = createImage(content, imgPath,  
	                needCompress,null,null);  
	        ImageIO.write(image, FORMAT_NAME, output);  
	    }  
	  
	    /** 
	     * 生成二维码 
	     *  
	     * @param content 
	     *            内容 
	     * @param output 
	     *            输出流 
	     * @throws Exception 
	     */  
	    public static void encode(String content, OutputStream output)  
	            throws Exception {  
	        encode(content, null, output, false);  
	    }  
	  
	    /** 
	     * 解析二维码 
	     *  
	     * @param file 
	     *            二维码图片 
	     * @return 
	     * @throws Exception 
	     */  
	    public static String decode(File file) throws Exception {  
	        BufferedImage image;  
	        image = ImageIO.read(file);  
	        if (image == null) {  
	            return null;  
	        }  
	        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(  
	                image);  
	        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));  
	        Result result;  
	        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();  
	        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);  
	        result = new MultiFormatReader().decode(bitmap, hints);  
	        String resultStr = result.getText();  
	        return resultStr;  
	    }  
	  
	    /** 
	     * 解析二维码 
	     *  
	     * @param path 
	     *            二维码图片地址 
	     * @return 
	     * @throws Exception 
	     */  
	    public static String decode(String path) throws Exception {  
	        return decode(new File(path));  
	    }
}
