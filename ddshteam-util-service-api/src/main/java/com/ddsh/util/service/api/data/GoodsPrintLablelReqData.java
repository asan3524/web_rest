package com.ddsh.util.service.api.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GoodsPrintLablelReqData implements Serializable{
	public String name;
	public String brand;
	public String type;
	public String norm;
	public String color;
	public String code;
	public String insotretime;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
 
	public String getNorm() {
		return norm;
	}
	public void setNorm(String norm) {
		this.norm = norm;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getInsotretime() {
		return insotretime;
	}
	public void setInsotretime(String insotretime) {
		this.insotretime = insotretime;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

 
}
