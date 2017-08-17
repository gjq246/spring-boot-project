package com.kpttech.pojo;

import java.util.Date;
import javax.persistence.*;

public class Tdepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String cid;

    /**
     * 部门名称
     */
    private String cdepartmentname;

    private Integer bdepartmentenable;

    private Date tdepartmentaddtime;

    private Date tdepartmentupdatetime;

    private String mdepartmentremark;

    /**
     * @return cid
     */
    public String getCid() {
        return cid;
    }

    /**
     * @param cid
     */
    public void setCid(String cid) {
        this.cid = cid;
    }

    /**
     * 获取部门名称
     *
     * @return cdepartmentname - 部门名称
     */
    public String getCdepartmentname() {
        return cdepartmentname;
    }

    /**
     * 设置部门名称
     *
     * @param cdepartmentname 部门名称
     */
    public void setCdepartmentname(String cdepartmentname) {
        this.cdepartmentname = cdepartmentname;
    }

    /**
     * @return bdepartmentenable
     */
    public Integer getBdepartmentenable() {
        return bdepartmentenable;
    }

    /**
     * @param bdepartmentenable
     */
    public void setBdepartmentenable(Integer bdepartmentenable) {
        this.bdepartmentenable = bdepartmentenable;
    }

    /**
     * @return tdepartmentaddtime
     */
    public Date getTdepartmentaddtime() {
        return tdepartmentaddtime;
    }

    /**
     * @param tdepartmentaddtime
     */
    public void setTdepartmentaddtime(Date tdepartmentaddtime) {
        this.tdepartmentaddtime = tdepartmentaddtime;
    }

    /**
     * @return tdepartmentupdatetime
     */
    public Date getTdepartmentupdatetime() {
        return tdepartmentupdatetime;
    }

    /**
     * @param tdepartmentupdatetime
     */
    public void setTdepartmentupdatetime(Date tdepartmentupdatetime) {
        this.tdepartmentupdatetime = tdepartmentupdatetime;
    }

    /**
     * @return mdepartmentremark
     */
    public String getMdepartmentremark() {
        return mdepartmentremark;
    }

    /**
     * @param mdepartmentremark
     */
    public void setMdepartmentremark(String mdepartmentremark) {
        this.mdepartmentremark = mdepartmentremark;
    }
}