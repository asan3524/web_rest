package com.ddsh.util.service.util.labelprinter;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.ddsh.util.service.api.constant.UtilContants;
import com.ddsh.util.service.util.labelprinter.weiwen.LabelData;
import com.ddsh.util.service.util.labelprinter.weiwen.View;
import com.google.zxing.WriterException;

/**
 * 标签八绘制工具
 * @ClassName: LabelUtil
 * @author arpgate
 * @date 2018年6月2日 下午7:17:40
 * @version v1.0.0
 * 
 */
public class LabelUtil {

	LabelData labelData;

	public LabelUtil(LabelData labelData) {
		this.labelData = labelData;
	}

	public String drawLabel() throws IOException, WriterException {
		 File file = new File(UtilContants.Sysset.LABLE_PATH+labelData.getGoodsid()+".jpg");  
		int width = labelData.getRealValue(labelData.getWidth());
		int height = labelData.getRealValue(labelData.getHeight());
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
		graphics2D.setBackground(Color.WHITE);
		graphics2D.clearRect(0, 0, width, height);
		int rectop=0,recleft=0;
		graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.0f));
		
		for(View view:labelData.getViewList())
		{
			view.setViewScaleMultiple(String.valueOf(labelData.getViewScaleMultiple()));
			switch (Integer.valueOf(view.getViewType())) {
			case UtilContants.WWViewType.WW_STRING:
				drawString(graphics2D,view,rectop,recleft);
				break;
			case UtilContants.WWViewType.WW_LINE:
				drawLine(graphics2D,view);
				break;
			case UtilContants.WWViewType.WW_REC:
				rectop=view.getRealValue(view.getViewContentTop());
				recleft=view.getRealValue(view.getViewContentLeft());
				drawRect(graphics2D,view);
				break;
			case UtilContants.WWViewType.WW_CODEIMAGE:
				drawImage(graphics2D,view);
				break;
			}
		}
    	graphics2D.dispose();
		ImageIO.write(bufferedImage, "jpg", file);
		
		return file.getPath();
	}

	/**
     * 矩形
     * @Title: drawRect
     * @param g2d void
     * @see 
     * @throws
     * @author arpgate
     */
    private void drawRect(Graphics2D g2d,View view)
     {
         g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         g2d.setColor(Color.BLACK);
         
         int left=view.getRealValue(view.getViewContentLeft());
         int top=view.getRealValue(view.getViewContentTop());
         int width=view.getRealValue(view.getViewStartWidth());
         int height=view.getRealValue(view.getViewStartHeight());
         g2d.drawRect(left, top, width, height);
     }

	/**
	* 图片
	* @Title: drawImage
	* @param g2d void
	* @throws WriterException 
	* @see 
	* @throws
	* @author arpgate
	*/
	private void drawImage(Graphics2D g2d, View view) throws WriterException {
	      int left=view.getRealValue(view.getViewContentLeft());
	      int top=view.getRealValue(view.getViewContentTop());
	      int width=view.getRealValue(view.getViewStartWidth());
	      int height=view.getRealValue(view.getViewStartHeight());
	      String value=view.getCodeString();
	  	g2d.drawImage(CodeUtil.getCodeImage(value, width, height), left, top, width, height,null);
	}

	/**
	* 文字
	* @Title: drawString
	* @param g2d void
	* @see 
	* @throws
	* @author arpgate
	*/
	private void drawString(Graphics2D g2d, View view,int rectop,int recleft) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
	    //int left=view.getRealValue(view.getViewContentLeft());
	   // int top=view.getRealValue(view.getViewContentTop());

	     int width=view.getRealValue(view.getViewStartWidth());
	     int height=view.getRealValue(view.getViewStartHeight());
	     int left=view.getRealValue(view.getViewContentLeft())+recleft;
	     int top=view.getRealValue(view.getViewContentTop())+height;
	    String value=view.getTextString();
		Double textSize =Double.valueOf(view.getTextSize())*(view.getViewScale());

		boolean isItalic=Boolean.valueOf(view.getIsItalic());
		boolean isBold=Boolean.valueOf(view.getIsBold());
		int style=Font.PLAIN|Font.BOLD;
		if(isItalic)
		{
			style=style|Font.ITALIC;
		}
		
/*		if(isBold)
		{
			style=Font.BOLD;
		}*/
		g2d.setFont(new Font("宋体", style, textSize.intValue()));
		g2d.setColor(Color.BLACK);
		g2d.drawString(value, left, top);
	}
 

	/**
	 * 线段 / 折线
	 * @Title: drawLine
	 * @param g void
	 * @see 
	 * @throws
	 * @author arpgate
	 */
	private void drawLine(Graphics2D g2d, View view) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.BLACK);

	    int left=view.getRealValue(view.getViewContentLeft());
	    int top=view.getRealValue(view.getViewContentTop());
	    int width=view.getRealValue(view.getViewStartWidth());
	    int height=view.getRealValue(view.getViewStartHeight());
	    int ylen=view.getRealValue(view.getViewCenterPointY());

		BasicStroke basicStroke = new BasicStroke(height);
		g2d.setStroke(basicStroke);
		
		 Double degree=Double.valueOf(view.getViewDegree());
		 int dpree=degree.intValue()/90;
	    if(dpree%2==0)
	    {
		    int left2=left+width;
		    g2d.drawLine(left, top, left2, top);
	    }
	    else if(dpree%2!=0)
	    {
	    	  int top2=top+ylen+5;
			  g2d.drawLine(left, top, left, top2);
	    }
	    else
	    {
	    	  int top2=top+height;
	    	  int width2=left+width;
			  g2d.drawLine(left, top, width2, top2);
	    }
		
	}

}
