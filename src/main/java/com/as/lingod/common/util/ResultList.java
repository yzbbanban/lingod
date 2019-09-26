package com.as.lingod.common.util;

import java.util.List;

/**
 * Created by brander on 2019/1/14
 */

/**
 * 列表
 *
 * @param <T>
 */
public class ResultList<T> {
    /**
     * 数量
     */
    private Integer count;
    /**
     * 列表数据
     */
    private List<T> dataList;

    public ResultList() {
    }

    public ResultList(Integer count, List<T> list) {
        this.count = count;
        this.dataList = list;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "ResultList{" +
                "count=" + count +
                ", dataList=" + dataList +
                '}';
    }
}
