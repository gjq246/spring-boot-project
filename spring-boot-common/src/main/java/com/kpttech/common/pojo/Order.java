package com.kpttech.common.pojo;

public class Order {
	private String column;
	private String dir;
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	
	@Override
	public String toString() {
		return column + " " + dir;
	}
	
}
