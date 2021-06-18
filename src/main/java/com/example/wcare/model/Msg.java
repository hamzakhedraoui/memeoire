package com.example.wcare.model;

public class Msg {
    private String msg;
    public Msg(String msg){
        this.msg = msg;
    }
    public Msg(){}

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
