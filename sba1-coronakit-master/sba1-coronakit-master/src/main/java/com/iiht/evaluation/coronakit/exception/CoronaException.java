package com.iiht.evaluation.coronakit.exception;

public class CoronaException extends Exception {
	private String errMsg="";

	public CoronaException(String errMsg) {
		this.errMsg = errMsg;
		
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	@Override
	public String toString() {
		return "CoronaException [errMsg=" + errMsg + "]";
	}
	
	
	

}
