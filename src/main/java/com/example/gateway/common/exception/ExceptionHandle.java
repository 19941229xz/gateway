package com.example.gateway.common.exception;


import com.example.gateway.common.HttpResp;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public HttpResp handleEx(Exception e){
        HttpResp rp = new HttpResp();
        if(e instanceof UnauthorizedException){
            rp.setContent(null);
            rp.setMsg("没有权限");
            rp.setCode("401");
            return rp;
        }else if(e instanceof MethodArgumentNotValidException){
            rp.setContent(null);
            rp.setMsg(((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage());
            rp.setCode("402");
        }else {
            rp.setCode("500");
            rp.setMsg("服务器开小差了");
            rp.setContent(null);

        }
        return rp;

    }
}
