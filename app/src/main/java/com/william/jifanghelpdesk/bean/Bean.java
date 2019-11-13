package com.william.jifanghelpdesk.bean;

public class Bean {
    @Override
    public String toString() {
        return "Bean{" +
                "res_code=" + res_code +
                ", err_code=" + err_code +
                ", err_msg='" + err_msg + '\'' +
                '}';
    }

    public int getRes_code() {
        return res_code;
    }

    public void setRes_code(int res_code) {
        this.res_code = res_code;
    }

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    private int res_code;
    private int err_code;
    private String err_msg;
}
