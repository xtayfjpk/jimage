package com.xtayfjpk.jimage.utils;

public class ReadResult <T> {
	private T result;
	private int length;
	private boolean success = true;
	private String message;
	
	public ReadResult(T result, int length) {
		this.result = result;
		this.length = length;
	}
	
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
