package com.ddshteam.web.core.support;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页器
 *
 * @param <T>
 */
public class Pager<T> {

	private int pageNo = 1;      // 当前页(从第一页开始,数据库偏移量从零开始)
	private int pageSize = 10;   // 每页个数
	private int currentPageSize; // 当前页数量
	private int totalCount;      // 总记录数
	private int totalPages;      // 总页数
	private List<T> pageList;    // 当前页结果集

	public int getPageNo() {
		return pageNo;
	}

	// 1 <= pageNo <=  totalPages
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getCurrentPageSize() {
		return currentPageSize;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public List<T> getPageList() {
		return pageList;
	}

	public void setPageList(List<T> pageList) {
		if(null == pageList)
			this.pageList = new ArrayList<>();
		else
			this.pageList = pageList;
	}

	public Pager(int pageNo, int pageSize, int totalCount, List<T> pageList) {
		super();
		setPageSize(pageSize);
		setTotalCount(totalCount);
		setPageNo(pageNo);
		setPageList(pageList);
		
		//计算总页数
		this.totalPages = (this.totalCount % this.pageSize == 0) ? this.totalCount / this.pageSize
				: (int)Math.ceil((double)this.totalCount / this.pageSize);
		//计算当前页数量
		this.currentPageSize = this.pageList != null ? this.pageList.size() : 0;
		
		// 1 <= pageNo <=  totalPages
		if (pageNo < 1)
			this.pageNo = 1;
		else if (pageNo > totalPages)
			this.pageNo = totalPages;
		else
			this.pageNo = pageNo;
	}

	public Pager() {
		super();
	}
}
