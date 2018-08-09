package com.ddsh.util.service.api.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExcelImportRespData  implements Serializable {
	
	Boolean status;
	
	List<String> messages;

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<String> getMessages() {
		if(messages==null)
		{
			messages=new ArrayList<String>();
		}
		return messages;
	}

	
	
}
