package com.as.lingod.domain;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
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
@TableName("fa_product")
public class FaProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 工序表名称
     */
    private String name;
    /**
     * 版别
     */
    private String edition;
    /**
     * 建立日期
     */
    private Date ctime;
    /**
     * 建立者
     */
    private String cpeople;
    /**
     * 流程名称
     */
    private String lname;
    /**
     * 流程代码
     */
    private String lcode;
    /**
     * 流程类别
     */
    private Integer ltype;
    /**
     * PURE CT
     */
    private String purect;
    /**
     * 宽放率
     */
    private String allowance;
    /**
     * STD CT
     */
    private String stdct;
    /**
     * 工序群组
     */
    private String group;
    /**
     * 难度系数
     */
    private Integer hard;
    /**
     * 技能等级
     */
    private String level;
    /**
     * 备注
     */
    private String memo;
    /**
     * 物料编号
     */
    private String materiel;
    /**
     * 序号
     */
    private Integer xuhao;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCpeople() {
        return cpeople;
    }

    public void setCpeople(String cpeople) {
        this.cpeople = cpeople;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLcode() {
        return lcode;
    }

    public void setLcode(String lcode) {
        this.lcode = lcode;
    }

    public Integer getLtype() {
        return ltype;
    }

    public void setLtype(Integer ltype) {
        this.ltype = ltype;
    }

    public String getPurect() {
        return purect;
    }

    public void setPurect(String purect) {
        this.purect = purect;
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getHard() {
        return hard;
    }

    public void setHard(Integer hard) {
        this.hard = hard;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMateriel() {
        return materiel;
    }

    public void setMateriel(String materiel) {
        this.materiel = materiel;
    }

    public Integer getXuhao() {
        return xuhao;
    }

    public void setXuhao(Integer xuhao) {
        this.xuhao = xuhao;
    }

    @Override
    public String toString() {
        return "FaProduct{" +
        "id=" + id +
        ", name=" + name +
        ", edition=" + edition +
        ", ctime=" + ctime +
        ", cpeople=" + cpeople +
        ", lname=" + lname +
        ", lcode=" + lcode +
        ", ltype=" + ltype +
        ", purect=" + purect +
        ", allowance=" + allowance +
        ", stdct=" + stdct +
        ", group=" + group +
        ", hard=" + hard +
        ", level=" + level +
        ", memo=" + memo +
        ", materiel=" + materiel +
        ", xuhao=" + xuhao +
        "}";
    }
}
