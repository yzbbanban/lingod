package com.as.lingod.domain;

import com.baomidou.mybatisplus.enums.IdType;

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
 * @since 2019-12-28
 */
@TableName("fa_satatime")
public class FaSatatime implements Serializable {

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
    private Integer pass;
    private Integer fail;
    private Date createDate;
    /**
     * 是否导出
     */
    @TableField("is_out")
    private Boolean isOut;


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

    public Boolean getOut() {
        return isOut;
    }

    public void setOut(Boolean out) {
        isOut = out;
    }

    @Override
    public String toString() {
        return "FaSatatime{" +
                "id=" + id +
                ", device=" + device +
                ", batch=" + batch +
                ", pass=" + pass +
                ", fail=" + fail +
                ", createDate=" + createDate +
                ", isOut=" + isOut +
                "}";
    }
}
