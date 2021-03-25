package com.person.norma.basiccommon.core;

/**
 * @Author： norma
 * @Description：API接口统一返回代码
 * @Date：Create in 16:16 2020/12/21
 * @Modified By：
 */
public enum ResultCode {
    /** 接口成功 **/
    SUCCESS(1,"成功"),

    /** 调用接口不存在返回 **/
    NOT_EXISTED(404, "{}接口不存在"),

    /** 调用异常返回 **/
    ERROR(500, "error"),

    /** 调用服务超时返回 **/
    TIMEOUT(504, "{}服务超时"),

    /** 调用服务缺少参数返回 **/
    MISSING_PARAM(505, "缺少参数{}"),

    /** 调用失败返回 **/
    FAILURE(1000, "failure"),

    /** 系统异常 **/
    EXCEPTION(-1,"系统异常"),

    /** 参数错误：10001-19999 **/
    PARAM_IS_INVALID(10001, "{}参数无效"),
    PARAM_IS_BLANK(10002, "{}参数为空"),
    PARAM_TYPE_BIND_ERROR(10003, "{}参数类型错误"),
    PARAM_NOT_COMPLETE(10004, "{}参数缺失"),

    /** 用户错误：20001-29999 **/
    USER_NOT_LOGGED_IN(20001, "用户{}未登录"),
    USER_LOGIN_ERROR(20002, "账号{}不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(20003, "账号{}已被禁用"),
    USER_NOT_EXIST(20004, "用户{}不存在"),
    USER_HAS_EXISTED(20005, "用户{}已存在"),
    USER_NOT_MAIN_GROUP(20006,"用户{}未设置主岗位"),
    USER_IS_EXPIRE(20007,"用户{}已过期"),
    USER_IS_NOT_VALID_DATE(20008,"用户{}不在有效期内"),
    USER_IS_NOT_VALID(20009,"用户{}无效"),
    USER_ACCOUNT_LOCKED(20010, "账号{}已被锁定"),
    USER_CREDENTIALS_EXPIRED(20011,"用户{}凭证已过期"),

    /** 业务错误：30001-39999 **/
    SPECIFIED_QUESTIONED_USER_NOT_EXIST(30001, "{}业务出现问题"),

    /** 系统错误：40001-49999 **/
    SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),

    /** 数据错误：50001-59999 **/
    RESULE_DATA_NONE(50001, "{}数据未找到"),
    DATA_IS_WRONG(50002, "{}数据有误"),
    DATA_ALREADY_EXISTED(50003, "{}数据已存在"),
    DATA_IS_UNIQUE(50004,"{}违反数据唯一性校验"),

    /** 接口错误：60001-69999 **/
    INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统{}接口调用异常"),
    INTERFACE_OUTTER_INVOKE_ERROR(60002, "外部系统{}接口调用异常"),
    INTERFACE_FORBID_VISIT(60003, "该{}接口禁止访问"),
    INTERFACE_ADDRESS_INVALID(60004, "接口地址{}无效"),
    INTERFACE_REQUEST_TIMEOUT(60005, "{}接口请求超时"),
    INTERFACE_EXCEED_LOAD(60006, "{}接口负载过高"),
    INTERFACE_RESPONSE_REPEAT(60007, "{}重复请求"),

    /** 权限错误：70001-79999 **/
    PERMISSION_NO_ACCESS(70001, "{}无访问权限"),
    PERMISSION_NO_TOKEN(70002,"token无效"),
    PERMISSION_ERROR(70003,"访问认证的资源{}时，用户{}没有相关凭证：未登录、token失效");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String message() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String getMessage(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.message;
            }
        }
        return name;
    }

    public static Integer getCode(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }
}
