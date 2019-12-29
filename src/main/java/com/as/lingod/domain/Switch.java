package com.as.lingod.domain;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ban123
 * @since 2019-12-28
 */
public class Switch implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 0 开启  1 关闭
     */
    @TableField("is_processing_pool")
    private Boolean isProcessingPool;
    /**
     * 0 开启  1 关闭
     */
    @TableField("is_link_pool")
    private Boolean isLinkPool;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getProcessingPool() {
        return isProcessingPool;
    }

    public void setProcessingPool(Boolean isProcessingPool) {
        this.isProcessingPool = isProcessingPool;
    }

    public Boolean getLinkPool() {
        return isLinkPool;
    }

    public void setLinkPool(Boolean isLinkPool) {
        this.isLinkPool = isLinkPool;
    }

    @Override
    public String toString() {
        return "Switch{" +
        "id=" + id +
        ", isProcessingPool=" + isProcessingPool +
        ", isLinkPool=" + isLinkPool +
        "}";
    }
}
