package com.lmj.zhangyunkai.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统用户信息表
 * </p>
 *
 * @author luguanghui
 * @since 2020-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TSysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 账号
     */
    private String account;

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 性别
     */
    private String sex;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 状态：正常，锁定，注销
     */
    private String status;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String updateUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 实有人口id
     */
    private String arcPersonId;


    public static final String ID = "id";

    public static final String ACCOUNT = "account";

    public static final String NAME = "name";

    public static final String PASSWORD = "password";

    public static final String NICKNAME = "nickname";

    public static final String USER_TYPE = "user_type";

    public static final String SEX = "sex";

    public static final String PHONE = "phone";

    public static final String STATUS = "status";

    public static final String CREATOR = "creator";

    public static final String LAST_LOGIN_TIME = "last_login_time";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_USER = "update_user";

    public static final String UPDATE_TIME = "update_time";

    public static final String REMARK = "remark";

    public static final String ARC_PERSON_ID = "arc_person_id";

}
