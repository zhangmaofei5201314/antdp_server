package com.donbala.util;

/**
 * @CLassName: MessageNotice
 * @Program: springbootdemo
 * @Author: wangran
 * @Date: 2019/7/4-20:51
 * @Description: todo
 **/
public class MessageNotice {
    private String flag ;//1 : 成功；0: 失败
    private String message ;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
