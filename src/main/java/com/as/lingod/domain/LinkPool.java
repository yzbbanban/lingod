package com.as.lingod.domain;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
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
@TableName("link_pool")
public class LinkPool implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 不良率
     */
    @TableField("defective_rate")
    private String defectiveRate;
    /**
     * 团队绩效
     */
    @TableField("team_performance")
    private String teamPerformance;
    private Date createDate;
    /**
     * 产线id
     */
    @TableField("link_id")
    private Integer linkId;
    /**
     * 线别
     */
    private String xianbie;
    /**
     * 好品总数
     */
    private Integer totalPass;
    /**
     * 坏品总数
     */
    private Integer totalFail;
    /**
     * 总数
     */
    private Integer total;
    /**
     * 区间总数
     */
    private Integer areaTotal;
    /**
     * 区间坏品
     */
    private Integer areaFail;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDefectiveRate() {
        return defectiveRate;
    }

    public void setDefectiveRate(String defectiveRate) {
        this.defectiveRate = defectiveRate;
    }

    public String getTeamPerformance() {
        return teamPerformance;
    }

    public void setTeamPerformance(String teamPerformance) {
        this.teamPerformance = teamPerformance;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getXianbie() {
        return xianbie;
    }

    public void setXianbie(String xianbie) {
        this.xianbie = xianbie;
    }

    public Integer getTotalPass() {
        return totalPass;
    }

    public void setTotalPass(Integer totalPass) {
        this.totalPass = totalPass;
    }

    public Integer getTotalFail() {
        return totalFail;
    }

    public void setTotalFail(Integer totalFail) {
        this.totalFail = totalFail;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getAreaTotal() {
        return areaTotal;
    }

    public void setAreaTotal(Integer areaTotal) {
        this.areaTotal = areaTotal;
    }

    public Integer getAreaFail() {
        return areaFail;
    }

    public void setAreaFail(Integer areaFail) {
        this.areaFail = areaFail;
    }

    @Override
    public String toString() {
        return "LinkPool{" +
        "id=" + id +
        ", defectiveRate=" + defectiveRate +
        ", teamPerformance=" + teamPerformance +
        ", createDate=" + createDate +
        ", linkId=" + linkId +
        ", xianbie=" + xianbie +
        ", totalPass=" + totalPass +
        ", totalFail=" + totalFail +
        ", total=" + total +
        ", areaTotal=" + areaTotal +
        ", areaFail=" + areaFail +
        "}";
    }
}
