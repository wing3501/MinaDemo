/**
 * 
 */
package com.tccv.core.util.image;

/**
 * 此类描述的是： 配合cropperImg使用，储存图像的裁剪尺寸
 * 
 * @author: chenshanben
 * @version: 2016年6月2日 下午8:40:19
 */
public class CropperSize
{
	
	
	private Integer[] x;
	
	private Integer[] y;
	
	private Integer[] x2;
	
	private Integer[] y2;
	
	private Integer[] w;
	
	private Integer[] h;
	
	public Integer[] getX()
	{
		return x;
	}
	
	public void setX(Integer[] x)
	{
		this.x = x;
	}
	
	public Integer[] getY()
	{
		return y;
	}
	
	public void setY(Integer[] y)
	{
		this.y = y;
	}
	
	public Integer[] getX2()
	{
		return x2;
	}
	
	public void setX2(Integer[] x2)
	{
		this.x2 = x2;
	}
	
	public Integer[] getY2()
	{
		return y2;
	}
	
	public void setY2(Integer[] y2)
	{
		this.y2 = y2;
	}
	
	public Integer[] getW()
	{
		return w;
	}
	
	public void setW(Integer[] w)
	{
		this.w = w;
	}
	
	public Integer[] getH()
	{
		return h;
	}
	
	public void setH(Integer[] h)
	{
		this.h = h;
	}
	
	public CropperSize()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
}
