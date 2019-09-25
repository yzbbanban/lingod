package com.as.lingod.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ban123
 * @since 2019-09-25
 */
@TableName("fa_main")
public class FaMain implements Serializable {

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
    private String banbie;


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

    public String getBanbie() {
        return banbie;
    }

    public void setBanbie(String banbie) {
        this.banbie = banbie;
    }

    @Override
    public String toString() {
        return "FaMain{" +
        "id=" + id +
        ", name=" + name +
        ", banbie=" + banbie +
        "}";
    }
}
