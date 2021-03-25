package com.person.norma.basiccommon.core;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@JsonIgnoreProperties({"internalId", "secretKey"})
@XmlRootElement
@Data
public class Result<T> {
    @ApiModelProperty(value = "返回值编码")
    private Integer code = ResultCode.SUCCESS.code();
    @ApiModelProperty(value = "提示信息")
    private String msg;
    @ApiModelProperty(value = "返回对象")
    private T obj;

    /**
     * HTTP响应状态码 {@link HttpStatus}
     */
    @ApiModelProperty(value = "HTTP响应状态码 ")
    private Integer status;

    /**
     * Feign 调用解析不到ok的字段， 加一个set函数
     */
    private boolean ok = false;

    /**
     * Feign 调用解析不到failure的字段， 加一个set函数
     */
    private boolean failure = false;

    /**
     * HTTP响应状态码的英文提示
     */
    @ApiModelProperty(value = "HTTP响应状态码的英文提示")
    private String error;


    /**
     * 调用接口路径
     */
    @ApiModelProperty(value = "调用接口路径")
    private String path;

    /**
     * 异常的名字
     */
    @ApiModelProperty(value = "主键")
    private String exception;


    /**
     * 时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private Date timestamp;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static Result failure(ResultCode resultCode, Throwable e, HttpStatus httpStatus, Object errors) {
        Result result = Result.failure(resultCode, e, httpStatus);
        result.setObj(errors);
        return result;
    }

    public static Result failure(ResultCode resultCode, Throwable e, HttpStatus httpStatus) {
        Result r = new Result();
        r.setCode(resultCode.code());
        r.setMsg(resultCode.message().replaceAll("\\{}", ""));
        r.setStatus(httpStatus.value());
        r.setError(httpStatus.getReasonPhrase());
        r.setException(e.getClass().getName());
        HttpServletRequest request = RequestContextUtil.getRequest();
        if (request != null) {
            r.setPath(request.getRequestURI());
        }
        r.setTimestamp(new Date());
        return r;
    }

    public static Result failure(BusinessException e) {
        BusinessExceptionEnum ee = BusinessExceptionEnum.getByEClass(e.getClass());
        if (ee != null) {
            return Result.failure(ee.getResultCode(), e, ee.getHttpStatus(), e.getData());
        }

        Result defaultErrorResult = Result.failure(e.getResultCode() == null ? ResultCode.SYSTEM_INNER_ERROR : e.getResultCode(), e, HttpStatus.INTERNAL_SERVER_ERROR, e.getData());
        if (StrUtil.isNotEmpty(e.getMessage())) {
            defaultErrorResult.setMsg(e.getMessage());
        }
        return defaultErrorResult;
    }


    public static Result failure() {
        return failure("未知异常，请联系管理员");
    }

    public static Result failure(String msg) {
        return failure(ResultCode.ERROR, msg);
    }

    public static Result failure(ResultCode resultCode, String msg) {

        return failure(resultCode, msg, null);
    }

    public static Result failure(ResultCode resultCode, String msg, Object obj) {
        Result r = new Result();
        r.setMsg(msg);
        r.setCode(resultCode.code());
        r.setObj(obj);
        r.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        r.setTimestamp(new Date());

        return r;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public static Result ok() {
        return new Result();
    }

    public static Result ok(Object obj) {
        Result r = new Result();

        r.setObj(obj);
        r.setTimestamp(new Date());

        return r;
    }

    public boolean isOk() {
        return this.code == ResultCode.SUCCESS.code().intValue();
    }

    public boolean isFailure() {
        return !this.isOk();
    }

}
