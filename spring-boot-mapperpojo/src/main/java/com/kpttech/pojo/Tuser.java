package com.kpttech.pojo;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

public class Tuser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * 获取用户名
     *
     * @return cusername - 用户名
     */
    public String getCusername() {
        return cusername;
    }

    /**
     * 设置用户名
     *
     * @param cusername 用户名
     */
    public void setCusername(String cusername) {
        this.cusername = cusername;
    }

    /**
     * 获取年龄
     *
     * @return iuserage - 年龄
     */
    public Integer getIuserage() {
        return iuserage;
    }

    /**
     * 设置年龄
     *
     * @param iuserage 年龄
     */
    public void setIuserage(Integer iuserage) {
        this.iuserage = iuserage;
    }

    /**
     * 获取是否党员
     *
     * @return busercommunist - 是否党员
     */
    public Integer getBusercommunist() {
        return busercommunist;
    }

    /**
     * 设置是否党员
     *
     * @param busercommunist 是否党员
     */
    public void setBusercommunist(Integer busercommunist) {
        this.busercommunist = busercommunist;
    }

    /**
     * 获取分数测试
     *
     * @return fuserscore - 分数测试
     */
    public BigDecimal getFuserscore() {
        return fuserscore;
    }

    /**
     * 设置分数测试
     *
     * @param fuserscore 分数测试
     */
    public void setFuserscore(BigDecimal fuserscore) {
        this.fuserscore = fuserscore;
    }

    /**
     * 获取性别
     *
     * @return suersex - 性别
     */
    public String getSuersex() {
        return suersex;
    }

    /**
     * 设置性别
     *
     * @param suersex 性别
     */
    public void setSuersex(String suersex) {
        this.suersex = suersex;
    }

    /**
     * 获取自我介绍
     *
     * @return ruserintroduce - 自我介绍
     */
    public String getRuserintroduce() {
        return ruserintroduce;
    }

    /**
     * 设置自我介绍
     *
     * @param ruserintroduce 自我介绍
     */
    public void setRuserintroduce(String ruserintroduce) {
        this.ruserintroduce = ruserintroduce;
    }

    /**
     * 获取生日
     *
     * @return duserbirthday - 生日
     */
    public Date getDuserbirthday() {
        return duserbirthday;
    }

    /**
     * 设置生日
     *
     * @param duserbirthday 生日
     */
    public void setDuserbirthday(Date duserbirthday) {
        this.duserbirthday = duserbirthday;
    }

    /**
     * @return buserenable
     */
    public Integer getBuserenable() {
        return buserenable;
    }

    /**
     * @param buserenable
     */
    public void setBuserenable(Integer buserenable) {
        this.buserenable = buserenable;
    }

    /**
     * @return tuseraddtime
     */
    public Date getTuseraddtime() {
        return tuseraddtime;
    }

    /**
     * @param tuseraddtime
     */
    public void setTuseraddtime(Date tuseraddtime) {
        this.tuseraddtime = tuseraddtime;
    }

    /**
     * @return tuserupdatetime
     */
    public Date getTuserupdatetime() {
        return tuserupdatetime;
    }

    /**
     * @param tuserupdatetime
     */
    public void setTuserupdatetime(Date tuserupdatetime) {
        this.tuserupdatetime = tuserupdatetime;
    }

    /**
     * @return muserremark
     */
    public String getMuserremark() {
        return muserremark;
    }

    /**
     * @param muserremark
     */
    public void setMuserremark(String muserremark) {
        this.muserremark = muserremark;
    }

    /**
     * 获取用户头像
     *
     * @return uuserphoto - 用户头像
     */
    public String getUuserphoto() {
        return uuserphoto;
    }

    /**
     * 设置用户头像
     *
     * @param uuserphoto 用户头像
     */
    public void setUuserphoto(String uuserphoto) {
        this.uuserphoto = uuserphoto;
    }
}