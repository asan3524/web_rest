package com.ddsh.util.service.api.data;

import java.io.Serializable;

public class PathRespData  implements Serializable{
	
	String wordpath;
	
	String pdfpath;
	
	String modelpath;

	public String getWordpath() {
		return wordpath;
	}

	public void setWordpath(String wordpath) {
		this.wordpath = wordpath;
	}

	public String getPdfpath() {
		return pdfpath;
	}

	public void setPdfpath(String pdfpath) {
		this.pdfpath = pdfpath;
	}

	public String getModelpath() {
		return modelpath;
	}

	public void setModelpath(String modelpath) {
		this.modelpath = modelpath;
	}
	

}
