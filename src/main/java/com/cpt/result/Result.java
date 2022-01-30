package com.cpt.result;

import com.cpt.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class Result {

    public Integer code;

    public String msg;

    public Object data;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result success(Integer code, String msg, Object data){

        Result result=new Result();

        result.setCode(code);

        result.setMsg(msg);

        result.setData(data);

        return result;

    }

    public static Result success(Integer code, String msg){

        Result result=new Result();

        result.setCode(code);

        result.setMsg(msg);


        return result;

    }


    public static Result fail(Integer code,String msg){
        Result result=new Result();

        result.setCode(code);

        result.setMsg(msg);
        return result;
    }
}

