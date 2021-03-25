package com.person.norma.basiccommon.core;

/**
 * @Author： norma
 * @Description：用户未登录异常
 * @Date：Create in 16:16 2020/12/21
 * @Modified By：
 */
public class UserNotLoginException extends BusinessException{

    private static final long serialVersionUID = -1879503946782379204L;

    public UserNotLoginException() {
        super();
    }

    public UserNotLoginException(String msg) {
        super(msg);
    }

    public UserNotLoginException(String formatMsg, Object... objects) {
        super(formatMsg, objects);
    }
}
