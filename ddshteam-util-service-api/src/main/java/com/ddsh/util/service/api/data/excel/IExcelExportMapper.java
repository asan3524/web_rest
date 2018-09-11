package com.ddsh.util.service.api.data.excel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class IExcelExportMapper  implements Serializable  {
	
	private Map<Position, Object> header=new HashMap<Position, Object>();
	
	private Map<Object, ArrayList<Object>> data=new HashMap<Object, ArrayList<Object>>();

	public class Position
	{
		//行
		int row;
		//列
		int cel;
		
		public int getRow() {
			return row;
		}
		public void setRow(int row) {
			this.row = row;
		}
		public int getCel() {
			return cel;
		}
		public void setCel(int cel) {
			this.cel = cel;
		}
		
	}

	public Map<Position, Object> getHeader() {
		return header;
	}

	public Map<Object, ArrayList<Object>> getData() {
		return data;
	}
	
	
}
