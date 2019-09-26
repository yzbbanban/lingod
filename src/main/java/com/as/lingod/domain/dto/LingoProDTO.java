package com.as.lingod.domain.dto;

/**
 * Created by brander on 2019/9/26
 */
public class LingoProDTO {
    private Integer peocount;
    private Integer protype;
    private Integer edition;

    public Integer getPeocount() {
        return peocount;
    }

    public void setPeocount(Integer peocount) {
        this.peocount = peocount;
    }

    public Integer getProtype() {
        return protype;
    }

    public void setProtype(Integer protype) {
        this.protype = protype;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    @Override
    public String toString() {
        return "LingoProDTO{" +
                "peocount=" + peocount +
                ", protype=" + protype +
                ", edition=" + edition +
                '}';
    }
}
