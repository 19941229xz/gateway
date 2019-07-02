package com.example.gateway.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class HttpResp implements Serializable {
	
	
	private String code;  // 状态码 100200错误   100300正确
	
	
	private String msg;  // 相应的提示信息
	
	
	private Object content;  // 内容


	public static HttpResp success(Object content){
		HttpResp rp=new HttpResp();
		rp.setCode("10300");
		rp.setMsg("ok");
		rp.setContent(content);
		return rp;
	}

	public static HttpResp fail(){
		HttpResp rp=new HttpResp();
		rp.setCode("10200");
		rp.setMsg("fail");
		rp.setContent(null);
		return rp;
	}

	

}
