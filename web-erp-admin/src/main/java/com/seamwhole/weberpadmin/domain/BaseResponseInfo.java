package com.seamwhole.weberpadmin.domain;

public class BaseResponseInfo {
	public int code;
	public Object data;
	
	public BaseResponseInfo() {
		code = 400;
		data = null;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
