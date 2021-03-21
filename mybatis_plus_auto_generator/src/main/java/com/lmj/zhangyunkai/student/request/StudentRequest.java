package com.lmj.zhangyunkai.student.request;


import com.lmj.zhangyunkai.base.BaseRequest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author auto
 * @since 2021-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Student对象", description="")
public class StudentRequest extends BaseRequest {


    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Integer age;

    @ApiModelProperty(value = "班级")
    private String clzss;

    private String remarks;


}
