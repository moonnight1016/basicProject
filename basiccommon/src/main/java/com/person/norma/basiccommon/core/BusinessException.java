package com.person.norma.basiccommon.core;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;

/**
 * @Author： norma
 * @Description：业务异常类
 * @Date：Create in 16:16 2020/12/21
 * @Modified By：
 */
@Data
public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = 194906846739586856L;

    protected int code;

    protected String message;

    protected ResultCode resultCode;

    protected Object data;

    public BusinessException() {
        BusinessExceptionEnum exceptionEnum = BusinessExceptionEnum.getByEClass(this.getClass());
        if (exceptionEnum != null) {
            resultCode = exceptionEnum.getResultCode();
            code = exceptionEnum.getResultCode().code();
            message = exceptionEnum.getResultCode().message().replaceAll("\\{}","");
        }

    }

    public BusinessException(String message) {
        this();
        this.message = message;
    }

    public BusinessException(String format, Object... objects) {
        this();
        if(ObjectUtil.isNull(objects)){
            this.message = format.replaceAll("\\{}","");
        }else {
            this.message = StrUtil.format(format,objects);
        }
    }

    public BusinessException(ResultCode resultCode, Object data) {
        this(resultCode);
        this.data = data;
    }

    public BusinessException(ResultCode resultCode,String format,Object... objects){
        this(resultCode);
        if(StrUtil.isNotBlank(format)){
            if(ObjectUtil.isNull(objects)){
                this.message = format.replaceAll("\\{}","");
            }else {
                this.message = StrUtil.format(format,objects);
            }
        }else{
            if(ObjectUtil.isNull(objects)){
                this.message = resultCode.message().replaceAll("\\{}","");
            }else {
                this.message = StrUtil.format(resultCode.message(),objects);
            }
        }
    }

    public BusinessException(ResultCode resultCode) {
        this.resultCode = resultCode;
        this.code = resultCode.code();
        this.message = resultCode.message().replaceAll("\\{}","");
    }
}
