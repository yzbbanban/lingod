package com.as.lingod.common.util;


import com.as.lingod.domain.PageParamDTO;

/**
 * Created by brander on 2019/1/15
 */
public class PageParamUtil {

    public static <T extends PageParamDTO> T setPageParam(T pageParam) {
        if (pageParam == null
                || pageParam.getPageNo() == null
                || pageParam.getPageSize() == null) {
            try {
                pageParam = (T) PageParamDTO.class.newInstance();
                pageParam.setPageNo(0);
                pageParam.setPageSize(50);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }


        int pageNo = pageParam.getPageNo();
        int pageSize = pageParam.getPageSize();

        if (pageNo == 0) {
            pageNo = 1;
        }

        int start = (pageNo - 1) * pageSize;
        pageParam.setPageNo(start);

        return pageParam;
    }
}
