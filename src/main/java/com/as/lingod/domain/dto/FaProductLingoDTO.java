package com.as.lingod.domain.dto;

import com.as.lingod.domain.FaProductLingo;
import com.as.lingod.domain.FaProductLingoCalc;

import java.util.List;

/**
 * Created by brander on 2019/9/25
 *
 * @author ban
 */
public class FaProductLingoDTO {
    private List<FaProductLingo> faProductLingo;

    private FaProductLingoCalc faProductLingoCalc;

    public List<FaProductLingo> getFaProductLingo() {
        return faProductLingo;
    }

    public void setFaProductLingo(List<FaProductLingo> faProductLingo) {
        this.faProductLingo = faProductLingo;
    }

    public FaProductLingoCalc getFaProductLingoCalc() {
        return faProductLingoCalc;
    }

    public void setFaProductLingoCalc(FaProductLingoCalc faProductLingoCalc) {
        this.faProductLingoCalc = faProductLingoCalc;
    }

    @Override
    public String toString() {
        return "FaProductLingoDTO{" +
                "faProductLingo=" + faProductLingo +
                ", faProductLingoCalc=" + faProductLingoCalc +
                '}';
    }
}
