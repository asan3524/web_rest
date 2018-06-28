package com.ddsh.util.service.util.labelprinter.weiwen;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.ddsh.util.service.api.constant.UtilContants;
import com.ddsh.util.service.api.data.GoodsPrintLablelReqData;

@XmlRootElement
public class LabelData
{
	@XmlElement
	public String goodsid;
	
	/**
	 * 纸张宽度
	 * @Fields width
	 */
	@XmlElement
	public int width;
	
	/**
	 * 纸张高度
	 * @Fields height
	 */
	@XmlElement
	public int height;
	
	/**
	 * 同步缩放比例
	 * @Fields viewScaleMultiple
	 */
	@XmlElement
	public double viewScaleMultiple;
	/**
	 * 设备类型
	 * @Fields deviceType
	 */
	@XmlElement
	public String deviceType;
	/**
	 * 模块id
	 * @Fields id
	 */
	@XmlElement
	public int id;
	/**
	 * 类型
	 * @Fields type
	 */
	@XmlElement
	public String type;
	/**
	 * 名称
	 * @Fields name
	 */
	@XmlElement
	public String name;
	/**
	 * 直接打印
	 * @Fields direct
	 */
	@XmlElement
	public String direct;
	/**
	 * 是否ddf
	 * @Fields isDDF
	 */
	@XmlElement
	public String isDDF;
	/**
	 * ddf 长度
	 * @Fields ddfLength
	 */
	public String ddfLength;
	/**
	 * 打印背景
	 * @Fields printBackground
	 */
	@XmlElement
	public String printBackground;
	/**
	 * 是否双面打印
	 * @Fields multiple
	 */
	@XmlElement
	public String multiple;
	
	@XmlElementWrapper(name="viewList")
	@XmlElements(value = {@XmlElement(name="view", type=View.class)})
	public List<View> viewList;
	
	private Map<String, Integer> viewNameToIndex=new HashMap<String, Integer>();
	
	@XmlTransient
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	@XmlTransient
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@XmlTransient
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@XmlTransient
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlTransient
	public String getDirect() {
		return direct;
	}
	public void setDirect(String direct) {
		this.direct = direct;
	}
	@XmlTransient
	public String getIsDDF() {
		return isDDF;
	}
	public void setIsDDF(String isDDF) {
		this.isDDF = isDDF;
	}
	@XmlTransient
	public String getDdfLength() {
		return ddfLength;
	}
	public void setDdfLength(String ddfLength) {
		this.ddfLength = ddfLength;
	}
	@XmlTransient
	public String getPrintBackground() {
		return printBackground;
	}
	public void setPrintBackground(String printBackground) {
		this.printBackground = printBackground;
	}
	@XmlTransient
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	@XmlTransient
	public String getMultiple() {
		return multiple;
	}
	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}
	@XmlTransient
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	@XmlTransient
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	@XmlTransient
	public double getViewScaleMultiple() {
		return viewScaleMultiple;
	}
	public void setViewScaleMultiple(double viewScaleMultiple) {
		this.viewScaleMultiple = viewScaleMultiple;
	}
 
 
    public int  getRealValue(int value)
    {
    	double rtvalue=value/getViewScaleMultiple();
    	return (int) rtvalue;
    }
    
	public int getRealValue(String value) {
		double rtvalue = Double.valueOf(value) /Double.valueOf(getViewScaleMultiple());
		return (int) rtvalue;
	}

	@XmlTransient
	public List<View> getViewList() {
		return viewList;
	}
	public void setViewList(List<View> viewList) {
		this.viewList = viewList;
	}
	private void dealIndexToViewName(GoodsPrintLablelReqData data)
	{
		for(int i=0;i<viewList.size();i++)
		{
			View view=viewList.get(i);
			viewNameToIndex.put(view.getViewID(), i);
			if(Integer.valueOf(view.getViewType())==UtilContants.WWViewType.WW_CODEIMAGE)
			{
				view.setCodeString(data.getCode());
			}
		}
	}
	public void parseReqDataToLabelData(GoodsPrintLablelReqData data)
	{
		dealIndexToViewName(data);
		for(Field field:GoodsPrintLablelReqData.class.getDeclaredFields())
		{
			String value="";
			try {
				value = (String) field.get(data);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			View view=viewList.get(viewNameToIndex.get(field.getName()));
			view.setTextString(view.getHintString()+value);
		}
	}
	
}


