package com.william.jifanghelpdesk.bean;

import java.util.List;

import javax.xml.transform.Result;

public class GsonBean {
    private int error_code;
    private String res_message;
    private List<Result> result; //result是数组所以用集合泛型的形式声明
    //构造
    GsonBean(int error_code,String res_message,List<Result> result)
    {
        this.error_code = error_code;
        this.res_message = res_message;
        this.result = result;
    }

    int getError_code()
    {
        return error_code;
    }

//    String getReason()
//    {
//        return reason;
//    }

//    List<Result> getResult()
//    {
//        return result;
//    }
//
//    @Override //一定要加个toString才会转化为String字符串的形式输出
//    public String toString()
//    {
//        return "result:"+result;
//    }
}
