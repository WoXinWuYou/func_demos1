package com.lmj.zhangyunkai.base;

public class Group {
    public interface Add{}    // 新增操作
    public interface Update{} // 更新操作
    public interface Id{}     // 删除和详情
    public interface Login{}  // 登录
    public interface updatePassword{}
    public interface SmsCode{}
    public interface CheckSmsCode{}
    public interface Relock{} // 延长锁定
    public interface OrderConfirm{} // 订单确认
}
