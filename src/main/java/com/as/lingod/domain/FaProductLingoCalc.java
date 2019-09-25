package com.as.lingod.domain;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ban
 * @since 2019-09-25
 */
@TableName("fa_product_lingo_calc")
public class FaProductLingoCalc implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 理论UPH
     */
    private BigDecimal xuph;
    /**
     * UPH标准
     */
    private BigDecimal xuphs;
    private BigDecimal rnak;
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
    private String allowance;
    /**
     * STD CT
     */
    private String stdct;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BigDecimal getRnak() {
        return rnak;
    }

    public void setRnak(BigDecimal rnak) {
        this.rnak = rnak;
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

    @Override
    public String toString() {
        return "FaProductLingoCalc{" +
        "id=" + id +
        ", xuph=" + xuph +
        ", xuphs=" + xuphs +
        ", rnak=" + rnak +
        ", production=" + production +
        ", iepoh=" + iepoh +
        ", iepohs=" + iepohs +
        ", availa=" + availa +
        ", allowance=" + allowance +
        ", stdct=" + stdct +
        "}";
    }
}
