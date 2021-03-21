package com.lmj.zhangyunkai.student.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * VO
 * </p>
 *
 * @author auto
 * @since 2021-03-21
 */
@Data
@ApiModel(value="StudentVO", description="")
public class StudentVO {


    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Integer age;

    @ApiModelProperty(value = "班级")
    private String clzss;

    private String remarks;


}
