package com.kpttech.pagepojo;

import java.util.Date;

public class Department extends PageModelBase{
	
	private String cid;

    /**
     * 部门名称
     */
    private String cdepartmentname;

    private Integer bdepartmentenable;

    private Date tdepartmentaddtime;

    private Date tdepartmentupdatetime;

    private String mdepartmentremark;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCdepartmentname() {
		return cdepartmentname;
	}

	public void setCdepartmentname(String cdepartmentname) {
		this.cdepartmentname = cdepartmentname;
	}

	public Integer getBdepartmentenable() {
		return bdepartmentenable;
	}

	public void setBdepartmentenable(Integer bdepartmentenable) {
		this.bdepartmentenable = bdepartmentenable;
	}

	public Date getTdepartmentaddtime() {
		return tdepartmentaddtime;
	}

	public void setTdepartmentaddtime(Date tdepartmentaddtime) {
		this.tdepartmentaddtime = tdepartmentaddtime;
	}

	public Date getTdepartmentupdatetime() {
		return tdepartmentupdatetime;
	}

	public void setTdepartmentupdatetime(Date tdepartmentupdatetime) {
		this.tdepartmentupdatetime = tdepartmentupdatetime;
	}

	public String getMdepartmentremark() {
		return mdepartmentremark;
	}

	public void setMdepartmentremark(String mdepartmentremark) {
		this.mdepartmentremark = mdepartmentremark;
	}
    
    


}
