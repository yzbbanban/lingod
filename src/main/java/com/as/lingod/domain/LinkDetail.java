package com.as.lingod.domain;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ban123
 * @since 2019-12-29
 */
@TableName("link_detail")
public class LinkDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 区间产量
     */
    private Integer areaPass;
    /**
     * 区间标准产出
     */
    private Integer areaSPass;
    /**
     * 区间团队效率
     */
    private BigDecimal areaEff;
    /**
     * 时开始
     */
    private Date createDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAreaPass() {
        return areaPass;
    }

    public void setAreaPass(Integer areaPass) {
        this.areaPass = areaPass;
    }

    public Integer getAreaSPass() {
        return areaSPass;
    }

    public void setAreaSPass(Integer areaSPass) {
        this.areaSPass = areaSPass;
    }

    public BigDecimal getAreaEff() {
        return areaEff;
    }

    public void setAreaEff(BigDecimal areaEff) {
        this.areaEff = areaEff;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "LinkDetail{" +
        "id=" + id +
        ", areaPass=" + areaPass +
        ", areaSPass=" + areaSPass +
        ", areaEff=" + areaEff +
        ", createDate=" + createDate +
        "}";
    }
}
