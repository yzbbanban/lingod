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
@TableName("fa_satapush")
public class FaSatapush implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 产线id--设备号
     */
    private String device;
    /**
     * 批次号
     */
    private String batch;
    /**
     * 合格数
     */
    private Integer pass;
    /**
     * 不良品数量
     */
    private Integer fail;
    private Integer sequence;
    /**
     * 0表示正常，1表示需要呼叫处理，2表示处理成功
     */
    private Integer result;
    @TableField("start_time")
    private Date startTime;
    @TableField("end_time")
    private Date endTime;


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

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "FaSatapush{" +
        "id=" + id +
        ", device=" + device +
        ", batch=" + batch +
        ", pass=" + pass +
        ", fail=" + fail +
        ", sequence=" + sequence +
        ", result=" + result +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        "}";
    }
}
