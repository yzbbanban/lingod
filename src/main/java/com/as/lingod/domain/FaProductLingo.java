package com.as.lingod.domain;

import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
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
     * 建立日期
     */
    private BigDecimal ctime;
    /**
     * PURE CT
     */
    private String purect;
    /**
     * 产品类型：1 成品 2 车缝成品
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
    private BigDecimal production;
    /**
     * 负载
     */
    private BigDecimal load;
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

    public BigDecimal getCtime() {
        return ctime;
    }

    public void setCtime(BigDecimal ctime) {
        this.ctime = ctime;
    }

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

    public BigDecimal getProduction() {
        return production;
    }

    public void setProduction(BigDecimal production) {
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

    public BigDecimal getLoad() {
        return load;
    }

    public void setLoad(BigDecimal load) {
        this.load = load;
    }

    @Override
    public String toString() {
        return "FaProductLingo{" +
                "id=" + id +
                ", edition='" + edition + '\'' +
                ", calcId=" + calcId +
                ", ctime=" + ctime +
                ", purect='" + purect + '\'' +
                ", protype=" + protype +
                ", allowance='" + allowance + '\'' +
                ", stdct='" + stdct + '\'' +
                ", production=" + production +
                ", load=" + load +
                ", xuhaolist='" + xuhaolist + '\'' +
                ", usercount=" + usercount +
                ", peocount=" + peocount +
                '}';
    }
}
