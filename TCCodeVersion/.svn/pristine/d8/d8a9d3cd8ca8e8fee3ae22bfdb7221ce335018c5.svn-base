package com.tccv.core.util.image;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * 图片处理
 * 
 * @author ChenJunHui
 */
public class ImageUtils
{
	
	
	/**
	 * 图片缩放
	 * 
	 * @param source
	 * @param targetW
	 * @param targetH
	 * @return
	 */
	public static BufferedImage resize(BufferedImage source, int targetW, int targetH)
	{
		int type = source.getType();
		BufferedImage target = null;
		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();
		if (sx < sy)
		{
			sx = sy;
			targetW = (int) (sx * source.getWidth());
		}
		else
		{
			sy = sx;
			targetH = (int) (sy * source.getHeight());
		}
		if (type == BufferedImage.TYPE_CUSTOM)
		{
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		}
		else
		{
			target = new BufferedImage(targetW, targetH, type);
		}
		Graphics2D g = target.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return target;
	}
	
	public static void saveImageAsJpg(InputStream in, String outFilePath, int width, int hight, boolean proportion)
		throws Exception
	{
		File saveFile = new File(outFilePath);
		BufferedImage srcImage = ImageIO.read(in);
		if (width > 0 || hight > 0)
		{
			int sw = srcImage.getWidth();
			int sh = srcImage.getHeight();
			if (sw > width && sh > hight)
			{
				srcImage = resize(srcImage, width, hight);
			}
			else
			{
				String fileName = saveFile.getName();
				String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);
				ImageIO.write(srcImage, formatName, saveFile);
				return;
			}
		}
		int w = srcImage.getWidth();
		int h = srcImage.getHeight();
		if (w == width)
		{
			int x = 0;
			int y = h / 2 - hight / 2;
			saveSubImage(srcImage, new Rectangle(x, y, width, hight), saveFile);
		}
		else if (h == hight)
		{
			int x = w / 2 - width / 2;
			int y = 0;
			saveSubImage(srcImage, new Rectangle(x, y, width, hight), saveFile);
		}
		in.close();
	}
	
	public static void saveSubImage(BufferedImage image, Rectangle subImageBounds, File subImageFile)
		throws IOException
	{
		if (subImageBounds.x < 0 || subImageBounds.y < 0 || subImageBounds.width - subImageBounds.x > image.getWidth()
				|| subImageBounds.height - subImageBounds.y > image.getHeight())
		{
			return;
		}
		BufferedImage subImage =
			image.getSubimage(subImageBounds.x, subImageBounds.y, subImageBounds.width, subImageBounds.height);
		String fileName = subImageFile.getName();
		String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);
		ImageIO.write(subImage, formatName, subImageFile);
	}
	
	/**
	 * 裁剪图像.
	 *
	 * @param srcImageFile the 目标文件的路径
	 * @param result the 保存路径
	 * @param x the 起点x
	 * @param y the 起点y
	 * @param width the 宽
	 * @param height the 高
	 */
	public final static void cut(File source, String result, int x, int y, int width, int height)
	{
		try
		{
			// 读取源图像
			BufferedImage bi = ImageIO.read(source);
			int srcWidth = bi.getWidth(); // 源图宽度
			int srcHeight = bi.getHeight(); // 源图高度
			if (srcWidth > 0 && srcHeight > 0)
			{
				Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
				// 四个参数分别为图像起点坐标和宽高
				// 即: CropImageFilter(int x,int y,int width,int height)
				ImageFilter cropFilter = new CropImageFilter(x, y, width, height);
				Image img =
					Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
				BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics g = tag.getGraphics();
				g.drawImage(img, 0, 0, width, height, null); // 绘制切割后的图
				g.dispose();
				// 输出为文件
				ImageIO.write(tag, "JPEG", new File(result));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 裁剪图像.
	 *
	 * @param is the 目标流文件
	 * @param result the 保存路径
	 * @param x the 起点x
	 * @param y the 起点y
	 * @param width the 宽
	 * @param height the 高
	 * @return the file
	 */
	public final static File cut(InputStream is, String result, int x, int y, int width, int height)
	{
		try
		{
			// 保存的文件
			File file = new File(result);
			// 读取源图像
			BufferedImage bi = ImageIO.read(is);
			int srcWidth = bi.getWidth(); // 源图宽度
			int srcHeight = bi.getHeight(); // 源图高度
			if (srcWidth > 0 && srcHeight > 0)
			{
				Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
				// 四个参数分别为图像起点坐标和宽高
				// 即: CropImageFilter(int x,int y,int width,int height)
				ImageFilter cropFilter = new CropImageFilter(x, y, width, height);
				Image img =
					Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
				BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics g = tag.getGraphics();
				g.drawImage(img, 0, 0, width, height, null); // 绘制切割后的图
				g.dispose();
				// 输出为文件
				ImageIO.write(tag, "JPEG", file);
				return file;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 裁剪图像并缩放.
	 *
	 * @param is the 目标流文件
	 * @param result the 保存路径
	 * @param x the 起点x
	 * @param y the 起点y
	 * @param x2 the 终点x
	 * @param y2 the 终点y2
	 * @param width the 目标图片宽
	 * @param height the 目标图片高
	 * @return the file
	 */
	public final static File cutAndScale(InputStream is, String result, int x, int y, int x2, int y2, int width,
			int height)
	{
		try
		{
			// 保存的文件
			File file = new File(result);
			// 读取源图像
			BufferedImage bi = ImageIO.read(is);
			int srcWidth = bi.getWidth(); // 源图宽度
			int srcHeight = bi.getHeight(); // 源图高度
			if (srcWidth > 0 && srcHeight > 0)
			{
				Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
				// 四个参数分别为图像起点坐标和宽高
				// 即: CropImageFilter(int x,int y,int width,int height)
				ImageFilter cropFilter = new CropImageFilter(x, y, Math.abs(x2 - x), Math.abs(y2 - y));
				Image img =
					Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
				BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics g = tag.getGraphics();
				g.drawImage(img, 0, 0, width, height, null); // 绘制切割后的图
				g.dispose();
				// 输出为文件
				ImageIO.write(tag, "JPEG", file);
				return file;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 裁剪图像并缩放.
	 *
	 * @param source the 目标文件的文件目录
	 * @param result the 保存路径
	 * @param x the 起点x
	 * @param y the 起点y
	 * @param x2 the 终点x
	 * @param y2 the 终点y2
	 * @param width the 目标图片宽
	 * @param height the 目标图片高
	 * @return the file
	 */
	public final static File cutAndScale(File source, String result, int x, int y, int x2, int y2, int width,
			int height)
	{
		try
		{
			// 保存的文件
			File file = new File(result);
			// 读取源图像
			BufferedImage bi = ImageIO.read(source);
			int srcWidth = bi.getWidth(); // 源图宽度
			int srcHeight = bi.getHeight(); // 源图高度
			if (srcWidth > 0 && srcHeight > 0)
			{
				Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
				// 四个参数分别为图像起点坐标和宽高
				// 即: CropImageFilter(int x,int y,int width,int height)
				ImageFilter cropFilter = new CropImageFilter(x, y, x2 - x, y2 - y);
				Image img =
					Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
				BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics g = tag.getGraphics();
				g.drawImage(img, 0, 0, width, height, null); // 绘制切割后的图
				g.dispose();
				// 输出为文件
				ImageIO.write(tag, "JPEG", file);
				return file;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args)
	{
		try
		{
			ImageUtils.saveImageAsJpg(new FileInputStream("d://aaaa.jpg"), "d://new.png", 200, 200, true);
			System.out.println("completed");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
