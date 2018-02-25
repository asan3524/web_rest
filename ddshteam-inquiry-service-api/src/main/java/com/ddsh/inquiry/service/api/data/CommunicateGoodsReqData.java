package com.ddsh.inquiry.service.api.data;

/**
 * 详情沟通时物资对象
 * @ClassName: CommunicateGoodsReqData
 * @author lishibang
 * @date 2018年2月15日 下午2:07:35
 * @version v1.0.0
 * 
 */
public class CommunicateGoodsReqData extends InitiateGoodsReqData {
	/**
	 * 主键ID
	 * @Fields id
	 */
	private String id;

	/**
	 * 更换或新购
	 * @Fields replace
	 */
	private int replace;
	/**
	 * 样式规格
	 * @Fields norm
	 */
	private String norm;
	/**
	 * 数量
	 * @Fields quantity
	 */
	private int quantity;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getReplace() {
		return replace;
	}

	public void setReplace(int replace) {
		this.replace = replace;
	}

	public String getNorm() {
		return norm;
	}

	public void setNorm(String norm) {
		this.norm = norm;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
