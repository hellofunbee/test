package com.jingu.IOT.response;

public class IOTResult {
	private boolean success;//操作成功是否
	private String msg;//提示信息
	private Object object;//返回值
	private int state; // 	返回状态码 0 代表代码进行到了最后一步
	
	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public IOTResult(boolean success, String msg, Object object, int state) {
		this.success = success;
		this.msg = msg;
		this.object = object;
		this.state = state;
	}
	public IOTResult() {
	}
}
