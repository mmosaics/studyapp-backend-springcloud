package org.example.common.enums;

public enum LoginType {
    /**
     * 密码登录
     */
    PASSWORD_LOGIN_TYPE("Password"),
    /**
     * 验证码登录
     */
    CODE_LOGIN_TYPE("Code");

    private String type;

    LoginType(String type){
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

}
