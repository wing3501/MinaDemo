package com.tccv.core.ali.oss;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * 此类描述的是： oss上传文件的工具类.
 *
 * @author: chenshanben
 * @version: 2016年3月1日 下午2:50:07
 */
public class OssImgUtil
{
	
	/**
	 * Scale.
	 *
	 * @param is the is
	 * @param result the result
	 * @param height the height
	 * @param width the width
	 * @param bb 是否补白
	 * @return the file
	 */
	public final static File scale(InputStream is, String result, int height, int width, boolean bb)
	{
		try
		{
			double ratio = 0.0; // 缩放比例
			BufferedImage bi = ImageIO.read(is);
			Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);
			// 计算比例
			if ((bi.getHeight() > height) || (bi.getWidth() > width))
			{
				if (bi.getHeight() > bi.getWidth())
				{
					ratio = (new Integer(height)).doubleValue() / bi.getHeight();
				}
				else
				{
					ratio = (new Integer(width)).doubleValue() / bi.getWidth();
				}
				AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
				itemp = op.filter(bi, null);
			}
			if (bb)
			{// 补白
				BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = image.createGraphics();
				g.setColor(Color.white);
				g.fillRect(0, 0, width, height);
				if (width == itemp.getWidth(null))
					g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null),
							itemp.getHeight(null), Color.white, null);
				else
					g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null),
							itemp.getHeight(null), Color.white, null);
				g.dispose();
				itemp = image;
			}
			File file = new File(result);
			ImageIO.write((BufferedImage) itemp, "JPEG", file);
			return file;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
