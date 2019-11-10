package com.as.lingod.domain;

import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author ban
 * @since 2019-09-25
 */
@TableName("fa_product_lingo")
public class FaProductLingo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 版别
     */
    private String edition;
    /**
     * lingo计算结果关联
     */
    private Integer calcId;
    /**
     * CT
     */
//    private String ctime;
    private String name;
    private Integer nameId;
    /**
     * PURE CT
     */
    private String purect;
    /**
     * 产品类型：4 成品 5 车缝成品
     */
    private Integer protype;
    /**
     * 宽放率
     */
    private String allowance;
    /**
     * STD CT
     */
    private String stdct;
    /**
     * 产量
     */
    private String production;
    /**
     * 负载
     */
    private String load;
    /**
     * 序号
     */
    private String xuhaolist;
    /**
     * 工序使用人数
     */
    private Integer usercount;
    /**
     * 人数
     */
    private Integer peocount;

    /**
     * 合并难度系数
     */
    private String merhard;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNameId() {
        return nameId;
    }

    public void setNameId(Integer nameId) {
        this.nameId = nameId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Integer getCalcId() {
        return calcId;
    }

    public void setCalcId(Integer calcId) {
        this.calcId = calcId;
    }

//    public String getCtime() {
//        return ctime;
//    }

//    public void setCtime(String ctime) {
//        this.ctime = ctime;
//    }

    public String getPurect() {
        return purect;
    }

    public void setPurect(String purect) {
        this.purect = purect;
    }

    public String getAllowance() {
        return allowance;
    }

    public void setAllowance(String allowance) {
        this.allowance = allowance;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getStdct() {
        return stdct;
    }

    public void setStdct(String stdct) {
        this.stdct = stdct;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getProtype() {
        return protype;
    }

    public void setProtype(Integer protype) {
        this.protype = protype;
    }

    public String getXuhaolist() {
        return xuhaolist;
    }

    public void setXuhaolist(String xuhaolist) {
        this.xuhaolist = xuhaolist;
    }

    public Integer getUsercount() {
        return usercount;
    }

    public void setUsercount(Integer usercount) {
        this.usercount = usercount;
    }

    public Integer getPeocount() {
        return peocount;
    }

    public void setPeocount(Integer peocount) {
        this.peocount = peocount;
    }

    public String getLoad() {
        return load;
    }

    public void setLoad(String load) {
        this.load = load;
    }

    public String getMerhard() {
        return merhard;
    }

    public void setMerhard(String merhard) {
        this.merhard = merhard;
    }

    @Override
    public String toString() {
        return "FaProductLingo{" +
                "id=" + id +
                ", edition='" + edition + '\'' +
                ", calcId=" + calcId +
                ", name='" + name + '\'' +
                ", nameId=" + nameId +
                ", purect='" + purect + '\'' +
                ", protype=" + protype +
                ", allowance='" + allowance + '\'' +
                ", stdct='" + stdct + '\'' +
                ", production='" + production + '\'' +
                ", load='" + load + '\'' +
                ", xuhaolist='" + xuhaolist + '\'' +
                ", usercount=" + usercount +
                ", peocount=" + peocount +
                ", merhard='" + merhard + '\'' +
                '}';
    }
}
