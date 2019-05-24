package com.dhr.store.util;

/**
 * @author Mr DU 处理json结果
 */
public class StoreResult {

	private int status;
	private String msg;
	private Object object;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public StoreResult() {
		super();
	}

	public StoreResult(int status, String msg, Object object) {
		this.status = status;
		this.msg = msg;
		this.object = object;
	}

	public StoreResult(String msg) {
		this.msg = msg;
		this.status = 200;
		this.object = null;
	}

	public StoreResult(int status, String msg) {
		this.msg = msg;
		this.status = status;
		this.object = null;
	}

	public static StoreResult ok() {
		return new StoreResult();
	}

	public static StoreResult ok(Object object) {
		return new StoreResult(200, "成功", object);
	}

	public static StoreResult ok(String msg) {
		return new StoreResult(msg);
	}

	public static StoreResult bad(String msg) {
		return new StoreResult(400, msg);
	}
}
