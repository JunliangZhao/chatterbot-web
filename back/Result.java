package com.example.common.lang;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.Data;

import java.io.Serializable;
@Data
public class Result implements Serializable {
    private int code;//200表示正常，非200表示异常
    private String msg;
    private Object data;

    public static Result success(int code,String msg,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
    public static Result fail(int code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
