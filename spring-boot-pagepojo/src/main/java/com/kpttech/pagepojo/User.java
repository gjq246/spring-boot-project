package com.kpttech.pagepojo;

import java.math.BigDecimal;
import java.util.Date;

public class User extends PageModelBase{
	
	private String cid;

    /**
     * 用户名
     */
    private String cusername;

    /**
     * 年龄
     */
    private Integer iuserage;

    /**
     * 是否党员
     */
    private Integer busercommunist;

    /**
     * 分数测试
     */
    private BigDecimal fuserscore;

    /**
     * 性别
     */
    private String suersex;

    /**
     * 自我介绍
     */
    private String ruserintroduce;

    /**
     * 生日
     */
    private Date duserbirthday;

    private Integer buserenable;

    private Date tuseraddtime;

    private Date tuserupdatetime;

    private String muserremark;

    /**
     * 用户头像
     */
    private String uuserphoto;
    
    /**
     * 部门名称
     */
    private String cdepartmentname;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCusername() {
		return cusername;
	}

	public void setCusername(String cusername) {
		this.cusername = cusername;
	}

	public Integer getIuserage() {
		return iuserage;
	}

	public void setIuserage(Integer iuserage) {
		this.iuserage = iuserage;
	}

	public Integer getBusercommunist() {
		return busercommunist;
	}

	public void setBusercommunist(Integer busercommunist) {
		this.busercommunist = busercommunist;
	}

	public BigDecimal getFuserscore() {
		return fuserscore;
	}

	public void setFuserscore(BigDecimal fuserscore) {
		this.fuserscore = fuserscore;
	}

	public String getSuersex() {
		return suersex;
	}

	public void setSuersex(String suersex) {
		this.suersex = suersex;
	}

	public String getRuserintroduce() {
		return ruserintroduce;
	}

	public void setRuserintroduce(String ruserintroduce) {
		this.ruserintroduce = ruserintroduce;
	}

	public Date getDuserbirthday() {
		return duserbirthday;
	}

	public void setDuserbirthday(Date duserbirthday) {
		this.duserbirthday = duserbirthday;
	}

	public Integer getBuserenable() {
		return buserenable;
	}

	public void setBuserenable(Integer buserenable) {
		this.buserenable = buserenable;
	}

	public Date getTuseraddtime() {
		return tuseraddtime;
	}

	public void setTuseraddtime(Date tuseraddtime) {
		this.tuseraddtime = tuseraddtime;
	}

	public Date getTuserupdatetime() {
		return tuserupdatetime;
	}

	public void setTuserupdatetime(Date tuserupdatetime) {
		this.tuserupdatetime = tuserupdatetime;
	}

	public String getMuserremark() {
		return muserremark;
	}

	public void setMuserremark(String muserremark) {
		this.muserremark = muserremark;
	}

	public String getUuserphoto() {
		return uuserphoto;
	}

	public void setUuserphoto(String uuserphoto) {
		this.uuserphoto = uuserphoto;
	}

	public String getCdepartmentname() {
		return cdepartmentname;
	}

	public void setCdepartmentname(String cdepartmentname) {
		this.cdepartmentname = cdepartmentname;
	}
    
	
    

}
