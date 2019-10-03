package com.as.lingod.domain.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author ban
 * @since 2019-09-25
 */
public class FaProductLingoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 版别
     */
    private String edition;
    /**
     * 建立日期
     */
    private Date ctime;
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
     * 理论UPH
     */
    private BigDecimal xuph;
    /**
     * UPH标准
     */
    private BigDecimal xuphs;
    /**
     * 日产能
     */
    private BigDecimal production;
    /**
     * 无宽放POH
     */
    private BigDecimal iepoh;
    /**
     * 标准POH
     */
    private BigDecimal iepohs;
    /**
     * 利用率
     */
    private BigDecimal availa;
    /**
     * 宽放率
     */
    private String totalallowance;
    /**
     * STD CT
     */
    private String totalstdct;


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

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getPurect() {
        return purect;
    }

    public void setPurect(String purect) {
        this.purect = purect;
    }

    public Integer getProtype() {
        return protype;
    }

    public void setProtype(Integer protype) {
        this.protype = protype;
    }

    public String getAllowance() {
        return allowance;
    }

    public void setAllowance(String allowance) {
        this.allowance = allowance;
    }

    public String getStdct() {
        return stdct;
    }

    public void setStdct(String stdct) {
        this.stdct = stdct;
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

    public BigDecimal getXuph() {
        return xuph;
    }

    public void setXuph(BigDecimal xuph) {
        this.xuph = xuph;
    }

    public BigDecimal getXuphs() {
        return xuphs;
    }

    public void setXuphs(BigDecimal xuphs) {
        this.xuphs = xuphs;
    }


    public BigDecimal getProduction() {
        return production;
    }

    public void setProduction(BigDecimal production) {
        this.production = production;
    }

    public BigDecimal getIepoh() {
        return iepoh;
    }

    public void setIepoh(BigDecimal iepoh) {
        this.iepoh = iepoh;
    }

    public BigDecimal getIepohs() {
        return iepohs;
    }

    public void setIepohs(BigDecimal iepohs) {
        this.iepohs = iepohs;
    }

    public BigDecimal getAvaila() {
        return availa;
    }

    public void setAvaila(BigDecimal availa) {
        this.availa = availa;
    }

    public String getTotalallowance() {
        return totalallowance;
    }

    public void setTotalallowance(String totalallowance) {
        this.totalallowance = totalallowance;
    }

    public String getTotalstdct() {
        return totalstdct;
    }

    public void setTotalstdct(String totalstdct) {
        this.totalstdct = totalstdct;
    }

    @Override
    public String toString() {
        return "FaProductLingoVO{" +
                "id=" + id +
                ", edition='" + edition + '\'' +
                ", ctime=" + ctime +
                ", purect='" + purect + '\'' +
                ", protype=" + protype +
                ", allowance='" + allowance + '\'' +
                ", stdct='" + stdct + '\'' +
                ", xuhaolist='" + xuhaolist + '\'' +
                ", usercount=" + usercount +
                ", peocount=" + peocount +
                ", xuph=" + xuph +
                ", xuphs=" + xuphs +
                ", production=" + production +
                ", iepoh=" + iepoh +
                ", iepohs=" + iepohs +
                ", availa=" + availa +
                ", totalallowance='" + totalallowance + '\'' +
                ", totalstdct='" + totalstdct + '\'' +
                '}';
    }
}
