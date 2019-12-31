package com.as.lingod.domain;

import com.baomidou.mybatisplus.enums.IdType;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author ban123
 * @since 2019-12-29
 */
@TableName("link_detail")
public class FaLinkDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * link id
     */
    @TableField("link_id")
    private Integer linkId;
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
    /**
     * 好品
     */
    private Integer pass;
    /**
     * 坏品
     */
    private Integer fail;
    /**
     * 组别
     */
    private String group;

    /**
     * 人数
     */
    private Integer people;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
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

    public Integer getPass() {
        return pass;
    }

    public void setPass(Integer pass) {
        this.pass = pass;
    }

    public Integer getFail() {
        return fail;
    }

    public void setFail(Integer fail) {
        this.fail = fail;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "FaLinkDetail{" +
                "id=" + id +
                ", linkId=" + linkId +
                ", areaPass=" + areaPass +
                ", areaSPass=" + areaSPass +
                ", areaEff=" + areaEff +
                ", createDate=" + createDate +
                ", pass=" + pass +
                ", fail=" + fail +
                ", group='" + group + '\'' +
                ", people=" + people +
                '}';
    }
}
