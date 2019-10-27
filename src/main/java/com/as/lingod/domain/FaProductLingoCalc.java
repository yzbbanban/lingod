package com.as.lingod.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * <p>
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
    private String name;
    private Integer nameId;

    private String protype;

    private String totalpeo;


    public Integer getNameId() {
        return nameId;
    }

    public void setNameId(Integer nameId) {
        this.nameId = nameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProtype() {
        return protype;
    }

    public void setProtype(String protype) {
        this.protype = protype;
    }

    public String getTotalpeo() {
        return totalpeo;
    }

    public void setTotalpeo(String totalpeo) {
        this.totalpeo = totalpeo;
    }

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


    public void setTotalallowance(String totalallowance) {
        this.totalallowance = totalallowance;
    }

    public String getTotalstdct() {
        return totalstdct;
    }

    public void setTotalstdct(String totalstdct) {
        this.totalstdct = totalstdct;
    }



    public String getTotalallowance() {
        return totalallowance;
    }

    @Override
    public String toString() {
        return "FaProductLingoCalc{" +
                "id=" + id +
                ", xuph=" + xuph +
                ", xuphs=" + xuphs +
                ", production=" + production +
                ", iepoh=" + iepoh +
                ", iepohs=" + iepohs +
                ", availa=" + availa +
                ", totalallowance='" + totalallowance + '\'' +
                ", totalstdct='" + totalstdct + '\'' +
                ", name='" + name + '\'' +
                ", nameId=" + nameId +
                ", protype='" + protype + '\'' +
                ", totalpeo='" + totalpeo + '\'' +
                '}';
    }
}
