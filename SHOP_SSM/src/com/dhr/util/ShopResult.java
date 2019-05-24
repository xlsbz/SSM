package com.dhr.util;

/**
 * 返回包装对象
 * 
 * @author Mr DU
 *
 */
public class ShopResult {

	private Integer status;
	private String msg;
	private Object object;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
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

	public ShopResult(String msg, Object object) {
		super();
		this.status = 200;
		this.msg = msg;
		this.object = object;
	}

	public ShopResult() {
		super();
	}

	public ShopResult(String msg) {
		this.msg = msg;
		this.status = 200;
		this.object = null;
	}

	public static ShopResult ok(String msg, Object object) {
		return new ShopResult(msg, object);
	}

	public static ShopResult bad(String msg) {
		return new ShopResult(msg);
	}
}
