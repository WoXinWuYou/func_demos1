package com.lmj.zhangyunkai.base;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * 通用请求
 * @author zhangyk
 */
public class BaseRequest implements Serializable {

    @ApiModelProperty(value = "当前页码")
    private int currPage = 1;

    @ApiModelProperty(value = "每页记录数")
    private int pageSize = 10;

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


}
