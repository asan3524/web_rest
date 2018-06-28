package com.ddsh.util.service.util.labelprinter.weiwen;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class View {
	/**
	 * 文字内容
	 * @Fields TextString
	 */
	@XmlElement(name="TextString")
	public String textString;

	/**
	 * 数据源开始行
	 * @Fields DataSourceStartNum
	 */
	@XmlElement(name="DataSourceStartNum")
	public String dataSourceStartNum;

	/**
	 * 是否显示线
	 * @Fields ViewIsShowLine
	 */
	@XmlElement(name="ViewIsShowLine")
	public String viewIsShowLine;

	/**
	 * 等比缩放
	 * @Fields ViewScaleMultiple
	 */
	@XmlElement(name="ViewScaleMultiple")
	public String viewScaleMultiple;

	/**
	 * 视频编号
	 * @Fields ViewID
	 */
	@XmlElement(name="ViewID")
	public String viewID;

	/**
	 * 提示信息 
	 * @Fields HintString
	 */
	@XmlElement(name="HintString")
	public String hintString;

	/**
	 * 字号名称
	 * @Fields TextSizeName
	 */
	@XmlElement(name="TextSizeName")
	public String textSizeName;

	/**
	 * 二维码显示类型
	 * @Fields CodeStringShowType
	 */
	@XmlElement(name="CodeStringShowType")
	public String codeStringShowType;

	/**
	 * 视图是否选中
	 * @Fields ViewIsSelected
	 */
	@XmlElement(name="ViewIsSelected")
	public String viewIsSelected;

	/**
	 * 内容上边距
	 * @Fields ViewContentTop
	 */
	@XmlElement(name="ViewContentTop")
	public String viewContentTop;

	/**
	 * 视图中心点横坐标
	 * @Fields ViewCenterPointX
	 */
	@XmlElement(name="ViewCenterPointX")
	public String viewCenterPointX;

	/**
	 * 视图中心点纵坐标
	 * @Fields ViewCenterPointY
	 */
	@XmlElement(name="ViewCenterPointY")
	public String viewCenterPointY;

	/**
	 * 二维码内容
	 * @Fields CodeString
	 */
	@XmlElement(name="CodeString")
	public String codeString;

	/**
	 * excel 数据源列
	 * @Fields DataSourceColNum
	 */
	@XmlElement(name="DataSourceColNum")
	public String dataSourceColNum;

	/**
	 * excel 数据源选择行
	 * @Fields DataSourceSelectedNum
	 */
	@XmlElement(name="DataSourceSelectedNum")
	public String dataSourceSelectedNum;

	/**
	 * 是否斜体
	 * @Fields isItalic
	 */
	@XmlElement(name="isItalic")
	public String isItalic;

	/**
	 * 是否下划线
	 * @Fields isUnderLine
	 */
	@XmlElement(name="isUnderLine")
	public String isUnderLine;

	/**
	 * 数据源是否存在标题
	 * @Fields DataSourceHasTitle
	 */
	@XmlElement(name="DataSourceHasTitle")
	public String dataSourceHasTitle;

	/**
	 * 字休
	 * @Fields ViewFontName
	 */
	@XmlElement(name="ViewFontName")
	public String viewFontName;

	/**
	 * 视频内容左边距
	 * @Fields ViewContentLeft
	 */
	@XmlElement(name="ViewContentLeft")
	public String viewContentLeft;

	/**
	 * 字号
	 * @Fields TextSize
	 */
	@XmlElement(name="TextSize")
	public String textSize;

	/**
	 * 视频是否锁定 
	 * @Fields ViewIsLocked
	 */
	@XmlElement(name="ViewIsLocked")
	public String viewIsLocked;

	/**
	 *数据源sheet数
	 * @Fields DataSourceSheetNum
	 */
	@XmlElement(name="DataSourceSheetNum")
	public String dataSourceSheetNum;

	/**
	 * 文字对齐
	 * @Fields TextAlign
	 */
	@XmlElement(name="TextAlign")
	public String textAlign;

	/**
	 * 字符串列
	 * @Fields TextStringList
	 */
	@XmlElement(name="TextStringList")
	public String textStringList;

	/**
	 * view度数
	 * @Fields ViewDegree
	 */
	@XmlElement(name="ViewDegree")
	public String viewDegree;

	/**
	 * view 类型
	 * @Fields ViewType
	 */
	@XmlElement(name="ViewType")
	public String viewType;

	/**
	 * excel数据源结束行
	 * @Fields DataSourceEndNum
	 */
	@XmlElement(name="DataSourceEndNum")
	public String dataSourceEndNum;

	/**
	 * 图形数据
	 * @Fields graphicImage
	 */
	@XmlElement(name="GraphicImage")
	public String graphicImage;

	/**
	 * 内容始宽
	 * @Fields ViewStartWidth
	 */
	@XmlElement(name="ViewStartWidth")
	public int viewStartWidth;

	/**
	 * 内容始高
	 * @Fields ViewStartHeight
	 */
	@XmlElement(name="ViewStartHeight")
	public String viewStartHeight;

	/**
	 * 是否加粗
	 * @Fields isBold
	 */
	@XmlElement(name="isBold")
	public String isBold;

	/**
	 * 数据源路径
	 * @Fields DataSourcePath
	 */
	@XmlElement(name="DataSourcePath")
	public String dataSourcePath;

	/**
	 * 是否是删除线
	 * @Fields isDeleteLine
	 */
	@XmlElement(name="isDeleteLine")
	public String isDeleteLine;

	@XmlTransient
	public String getTextString() {
		return textString;
	}

	public void setTextString(String textString) {
		this.textString = textString;
	}

	@XmlTransient
	public String getDataSourceStartNum() {
		return dataSourceStartNum;
	}

	public void setDataSourceStartNum(String dataSourceStartNum) {
		this.dataSourceStartNum = dataSourceStartNum;
	}

	@XmlTransient
	public String getViewIsShowLine() {
		return viewIsShowLine;
	}

	public void setViewIsShowLine(String viewIsShowLine) {
		this.viewIsShowLine = viewIsShowLine;
	}

	@XmlTransient
	public String getViewScaleMultiple() {
		return viewScaleMultiple;
	}
	
	public double getViewScale() {
		return Double.valueOf(viewScaleMultiple);
	}

	public void setViewScaleMultiple(String viewScaleMultiple) {
		this.viewScaleMultiple = viewScaleMultiple;
	}

	@XmlTransient
	public String getViewID() {
		return viewID;
	}

	public void setViewID(String viewID) {
		this.viewID = viewID;
	}

	@XmlTransient
	public String getHintString() {
		return hintString;
	}

	public void setHintString(String hintString) {
		this.hintString = hintString;
	}

	@XmlTransient
	public String getTextSizeName() {
		return textSizeName;
	}

	public void setTextSizeName(String textSizeName) {
		this.textSizeName = textSizeName;
	}

	@XmlTransient
	public String getCodeStringShowType() {
		return codeStringShowType;
	}

	public void setCodeStringShowType(String codeStringShowType) {
		this.codeStringShowType = codeStringShowType;
	}

	@XmlTransient
	public String getViewIsSelected() {
		return viewIsSelected;
	}

	public void setViewIsSelected(String viewIsSelected) {
		this.viewIsSelected = viewIsSelected;
	}

	@XmlTransient
	public String getViewContentTop() {
		return viewContentTop;
	}

	public void setViewContentTop(String viewContentTop) {
		this.viewContentTop = viewContentTop;
	}

	@XmlTransient
	public String getViewCenterPointX() {
		return viewCenterPointX;
	}

	public void setViewCenterPointX(String viewCenterPointX) {
		this.viewCenterPointX = viewCenterPointX;
	}

	@XmlTransient
	public String getViewCenterPointY() {
		return viewCenterPointY;
	}

	public void setViewCenterPointY(String viewCenterPointY) {
		this.viewCenterPointY = viewCenterPointY;
	}

	@XmlTransient
	public String getCodeString() {
		return codeString;
	}

	public void setCodeString(String codeString) {
		this.codeString = codeString;
	}

	@XmlTransient
	public String getDataSourceColNum() {
		return dataSourceColNum;
	}

	public void setDataSourceColNum(String dataSourceColNum) {
		this.dataSourceColNum = dataSourceColNum;
	}

	@XmlTransient
	public String getDataSourceSelectedNum() {
		return dataSourceSelectedNum;
	}

	public void setDataSourceSelectedNum(String dataSourceSelectedNum) {
		this.dataSourceSelectedNum = dataSourceSelectedNum;
	}

	@XmlTransient
	public String getIsItalic() {
		return isItalic;
	}

	public void setIsItalic(String isItalic) {
		this.isItalic = isItalic;
	}

	@XmlTransient
	public String getIsUnderLine() {
		return isUnderLine;
	}

	public void setIsUnderLine(String isUnderLine) {
		this.isUnderLine = isUnderLine;
	}

	@XmlTransient
	public String getDataSourceHasTitle() {
		return dataSourceHasTitle;
	}

	public void setDataSourceHasTitle(String dataSourceHasTitle) {
		this.dataSourceHasTitle = dataSourceHasTitle;
	}

	@XmlTransient
	public String getViewFontName() {
		return viewFontName;
	}

	public void setViewFontName(String viewFontName) {
		this.viewFontName = viewFontName;
	}

	@XmlTransient
	public String getViewContentLeft() {
		return viewContentLeft;
	}

	public void setViewContentLeft(String viewContentLeft) {
		this.viewContentLeft = viewContentLeft;
	}

	@XmlTransient
	public String getTextSize() {
		return textSize;
	}

	public void setTextSize(String textSize) {
		this.textSize = textSize;
	}

	@XmlTransient
	public String getViewIsLocked() {
		return viewIsLocked;
	}

	public void setViewIsLocked(String viewIsLocked) {
		this.viewIsLocked = viewIsLocked;
	}

	@XmlTransient
	public String getDataSourceSheetNum() {
		return dataSourceSheetNum;
	}

	public void setDataSourceSheetNum(String dataSourceSheetNum) {
		this.dataSourceSheetNum = dataSourceSheetNum;
	}

	@XmlTransient
	public String getTextAlign() {
		return textAlign;
	}

	public void setTextAlign(String textAlign) {
		this.textAlign = textAlign;
	}

	@XmlTransient
	public String getTextStringList() {
		return textStringList;
	}

	public void setTextStringList(String textStringList) {
		this.textStringList = textStringList;
	}

	@XmlTransient
	public String getViewDegree() {
		return viewDegree;
	}

	public void setViewDegree(String viewDegree) {
		this.viewDegree = viewDegree;
	}

	@XmlTransient
	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	@XmlTransient
	public String getDataSourceEndNum() {
		return dataSourceEndNum;
	}

	public void setDataSourceEndNum(String dataSourceEndNum) {
		this.dataSourceEndNum = dataSourceEndNum;
	}

	@XmlTransient
	public String getGraphicImage() {
		return graphicImage;
	}

	public void setGraphicImage(String graphicImage) {
		this.graphicImage = graphicImage;
	}

	@XmlTransient
	public int getViewStartWidth() {
		return viewStartWidth;
	}

	public void setViewStartWidth(int viewStartWidth) {
		this.viewStartWidth = viewStartWidth;
	}

	@XmlTransient
	public String getViewStartHeight() {
		return viewStartHeight;
	}

	public void setViewStartHeight(String viewStartHeight) {
		this.viewStartHeight = viewStartHeight;
	}

	@XmlTransient
	public String getIsBold() {
		return isBold;
	}

	public void setIsBold(String isBold) {
		this.isBold = isBold;
	}

	@XmlTransient
	public String getDataSourcePath() {
		return dataSourcePath;
	}

	public void setDataSourcePath(String dataSourcePath) {
		this.dataSourcePath = dataSourcePath;
	}

	@XmlTransient
	public String getIsDeleteLine() {
		return isDeleteLine;
	}

	public void setIsDeleteLine(String isDeleteLine) {
		this.isDeleteLine = isDeleteLine;
	}

	public int getRealValue(int value) {
		int rtvalue = (int) (value /Double.valueOf(getViewScaleMultiple()));
		return rtvalue;
	}
	
	public int getRealValue(String value) {
		double rtvalue = Double.valueOf(value) /Double.valueOf(getViewScaleMultiple());
		return (int) rtvalue;
	}
}