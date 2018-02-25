package com.ddsh.purchase.service.api.data;


/**
 * 采购单指定的未失效询价单列表
 * @ClassName: InquiryListRespData
 * @author lishibang
 * @date 2018年2月18日 上午9:42:24
 * @version v1.0.0
 * 
 */
public class InquiryListRespData {
	private String id;
	/**
	 * 询价单ID
	 * @Fields inquiryId
	 */
	private String inquiryId;
	/**
	 * 询价单名称
	 * @Fields name
	 */
	private String name;
	/**
	 * 询价单编码
	 * @Fields code
	 */
	private String code;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInquiryId() {
		return inquiryId;
	}

	public void setInquiryId(String inquiryId) {
		this.inquiryId = inquiryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
