package com.kpttech.pagepojo;

import java.io.Serializable;

public class PageModelBase implements Serializable{
	
	private int draw;		
		
	private int start;
		
	private int length;
	private int page;
	private String orderProperty;//排序字段名
	private String orderDir;//排序方式

    private String ids;

	public String getOrderProperty() {
		return orderProperty;
	}	

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setOrderProperty(String orderProperty) {
		this.orderProperty = orderProperty;
	}

	public String getOrderDir() {
		return orderDir;
	}

	public void setOrderDir(String orderDir) {
		this.orderDir = orderDir;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	

}
