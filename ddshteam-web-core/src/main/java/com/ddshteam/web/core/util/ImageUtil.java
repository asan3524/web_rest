package com.ddshteam.web.core.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 图片处理辅助类
 * 
 */
public final class ImageUtil {
	private ImageUtil() {
	}

	/**
	 * * 转换图片大小，不变形
	 * 
	 * @param img 图片文件
	 * @param width 图片宽
	 * @param height 图片高
	 */
	public static final void changeImge(File img, int width, int height) {
		try {
			Thumbnails.of(img).size(width, height).keepAspectRatio(false).toFile(img);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException("图片转换出错！", e);
		}
	}

	/**
	 * 根据比例缩放图片
	 * 
	 * @param orgImgFile 源图片路径
	 * @param scale 比例
	 * @param targetFile 缩放后的图片存放路径
	 * @throws IOException
	 */
	public static final void scale(BufferedImage orgImg, double scale, String targetFile) throws IOException {
		Thumbnails.of(orgImg).scale(scale).toFile(targetFile);
	}

	public static final void scale(String orgImgFile, double scale, String targetFile) throws IOException {
		Thumbnails.of(orgImgFile).scale(scale).toFile(targetFile);
	}

	/**
	 * 图片格式转换
	 * 
	 * @param orgImgFile
	 * @param width
	 * @param height
	 * @param suffixName
	 * @param targetFile
	 * @throws IOException
	 */
	public static final void format(String orgImgFile, int width, int height, String suffixName, String targetFile)
			throws IOException {
		Thumbnails.of(orgImgFile).size(width, height).outputFormat(suffixName).toFile(targetFile);
	}

	/**
	 * 根据宽度同比缩放
	 * 
	 * @param orgImg 源图片
	 * @param orgWidth 原始宽度
	 * @param targetWidth 缩放后的宽度
	 * @param targetFile 缩放后的图片存放路径
	 * @throws IOException
	 */
	public static final double scaleWidth(BufferedImage orgImg, int targetWidth, String targetFile) throws IOException {
		int orgWidth = orgImg.getWidth();
		// 计算宽度的缩放比例
		double scale = targetWidth * 1.00 / orgWidth;
		// 裁剪
		scale(orgImg, scale, targetFile);

		return scale;
	}

	public static final void scaleWidth(String orgImgFile, int targetWidth, String targetFile) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(new File(orgImgFile));
		scaleWidth(bufferedImage, targetWidth, targetFile);
	}

	/**
	 * 根据高度同比缩放
	 * 
	 * @param orgImgFile //源图片
	 * @param orgHeight //原始高度
	 * @param targetHeight //缩放后的高度
	 * @param targetFile //缩放后的图片存放地址
	 * @throws IOException
	 */
	public static final double scaleHeight(BufferedImage orgImg, int targetHeight, String targetFile) throws IOException {
		int orgHeight = orgImg.getHeight();
		double scale = targetHeight * 1.00 / orgHeight;
		scale(orgImg, scale, targetFile);
		return scale;
	}

	public static final void scaleHeight(String orgImgFile, int targetHeight, String targetFile) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(new File(orgImgFile));
		// int height = bufferedImage.getHeight();
		scaleHeight(bufferedImage, targetHeight, targetFile);
	}

	// 原始比例缩放
	public static final void scaleWidth(File file, Integer width) throws IOException {
		String fileName = file.getName();
		String filePath = file.getAbsolutePath();
		String postFix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
		// 缩放
		BufferedImage bufferedImg = ImageIO.read(file);
		String targetFile = filePath + "_s" + postFix;
		scaleWidth(bufferedImg, width, targetFile);
		String targetFile2 = filePath + "@" + width;
		new File(targetFile).renameTo(new File(targetFile2));
	}
	
	/** 
     * 获取图片宽度 
     * @param file  图片文件 
     * @return 宽度 
     */  
    public static int getImgWidth(File file) {  
        InputStream is = null;  
        BufferedImage src = null;  
        int ret = -1;  
        try {  
            is = new FileInputStream(file);  
            javax.imageio.ImageIO.setUseCache(false);
            src = javax.imageio.ImageIO.read(is);  
            ret = src.getWidth(null); // 得到源图宽  
            is.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return ret;  
    }  
    
    public static int getImgWidth(InputStream is) {  
        BufferedImage src = null;  
        int ret = -1;  
        try {  
        	javax.imageio.ImageIO.setUseCache(false);
            src = javax.imageio.ImageIO.read(is);  
            ret = src.getWidth(null); // 得到源图宽  
            is.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return ret;  
    }  
    
    /** 
     * 获取图片高度 
     * @param file  图片文件 
     * @return 高度 
     */  
    public static int getImgHeight(File file) {  
        InputStream is = null;  
        BufferedImage src = null;  
        int ret = -1;  
        try {  
            is = new FileInputStream(file);  
            javax.imageio.ImageIO.setUseCache(false);
            src = javax.imageio.ImageIO.read(is);  
            ret = src.getHeight(null); // 得到源图高  
            is.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return ret;  
    }  
    
    public static int getImgHeight(InputStream is) {  
        BufferedImage src = null;  
        int ret = -1;  
        try {  
        	javax.imageio.ImageIO.setUseCache(false);
            src = javax.imageio.ImageIO.read(is);  
            ret = src.getHeight(null); // 得到源图高  
            is.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return ret;  
    }  
    
    public static void main(String[] args) {  
        //String imagePath="F:\\images\\1.jpg";  
          
        //System.out.println("高"+ImagesUtil.GetPhotoHeight(imagePath));  
        //System.out.println("宽"+ImagesUtil.GetPhotoWidth(imagePath));  
          
         //String imageUrl="http://avatar.csdn.net/9/F/2/1_5iasp.jpg";   
         String url="F:/images/1.jpg";  
         /* Map<String,Integer> map=ImagesUtil.GetImageResolution(imageUrl); 
          if(map!=null){ 
              
              System.out.println(map.get("width")); 
              System.out.println(map.get("width")); 
          }else{ 
              System.out.println("图片路径或者格式不正确"); 
          }  */  
        File file=new File(url);  
        Integer i= getImgHeight(file);  
        System.out.println(i);  
    }  
}
