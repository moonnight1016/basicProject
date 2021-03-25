package com.person.norma.basiccommon.core;

/**
 * @Author： norma
 * @Description：数据已经存在异常
 * @Date：Create in 16:16 2020/12/21
 * @Modified By：
 */
public class DataConflictException extends BusinessException{
    private static final long serialVersionUID = 3721036867889297081L;

    public DataConflictException() {
        super();
    }

    public DataConflictException(Object data) {
        super.data = data;
    }

    public DataConflictException(ResultCode resultCode) {
        super(resultCode);
    }

    public DataConflictException(ResultCode resultCode, Object data) {
        super(resultCode, data);
    }

    public DataConflictException(String msg) {
        super(msg);
    }

    public DataConflictException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }

}
