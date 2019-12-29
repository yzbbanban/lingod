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
 * @since 2019-12-28
 */
@TableName("processing_pool")
public class ProcessingPool implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 设备号
     */
    private String device;
    /**
     * 批次
     */
    private String batch;
    /**
     * 批次理论效率
     */
    private String efficiency;
    /**
     * 批次人数
     */
    private Integer people;
    /**
     * 产线id
     */
    @TableField("link_id")
    private Integer linkId;
    private Integer pass;
    private Integer fail;
    private Date createDate;
    /**
     * 是否处理过
     */
    @TableField("is_deal")
    private Boolean isDeal;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getDeal() {
        return isDeal;
    }

    public void setDeal(Boolean isDeal) {
        this.isDeal = isDeal;
    }

    @Override
    public String toString() {
        return "ProcessingPool{" +
        "id=" + id +
        ", device=" + device +
        ", batch=" + batch +
        ", efficiency=" + efficiency +
        ", people=" + people +
        ", linkId=" + linkId +
        ", pass=" + pass +
        ", fail=" + fail +
        ", createDate=" + createDate +
        ", isDeal=" + isDeal +
        "}";
    }
}
