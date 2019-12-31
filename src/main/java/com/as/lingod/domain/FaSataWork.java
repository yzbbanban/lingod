package com.as.lingod.domain;

import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
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
@TableName("fa_sata_work")
public class FaSataWork implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 工单id
     */
    private Integer wid;
    /**
     * 创建时间
     */
    private Date ctime;
    /**
     * 1开线 2结批
     */
    private Integer status;

    /**
     * 标准效率
     */
    private String efficiency;

    /**
     * 人数
     */
    private Integer people;

    /**
     * 结批时间
     */
    private Date ftime;
    /**
     * 计数器数据进来的时间
     */
    private Date jtime;
    /**
     * 好品数
     */
    private Integer pass;
    /**
     * 坏品数
     */
    private Integer fail;
    /**
     * 设备号
     */
    private String device;
    /**
     * 分组
     */
    private String group;
    /**
     * 产线名
     */
    private String xianbie;

    /**
     * 是否处理过
     */
    private Boolean out;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getFtime() {
        return ftime;
    }

    public void setFtime(Date ftime) {
        this.ftime = ftime;
    }

    public Date getJtime() {
        return jtime;
    }

    public void setJtime(Date jtime) {
        this.jtime = jtime;
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

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getXianbie() {
        return xianbie;
    }

    public void setXianbie(String xianbie) {
        this.xianbie = xianbie;
    }

    public Boolean getOut() {
        return out;
    }

    public void setOut(Boolean out) {
        this.out = out;
    }

    @Override
    public String toString() {
        return "FaSataWork{" +
                "id=" + id +
                ", wid=" + wid +
                ", ctime=" + ctime +
                ", status=" + status +
                ", efficiency='" + efficiency + '\'' +
                ", people=" + people +
                ", ftime=" + ftime +
                ", jtime=" + jtime +
                ", pass=" + pass +
                ", fail=" + fail +
                ", device='" + device + '\'' +
                ", group='" + group + '\'' +
                ", xianbie='" + xianbie + '\'' +
                ", out=" + out +
                '}';
    }
}
