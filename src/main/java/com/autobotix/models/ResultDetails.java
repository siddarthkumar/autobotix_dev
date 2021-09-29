package com.autobotix.models;

public class ResultDetails {
private String result;
private String message;


public ResultDetails(String result, String message) {
	super();
	this.result = result;
	this.message = message;
}


public ResultDetails() {
	super();
	// TODO Auto-generated constructor stub
}


public String getResult() {
	return result;
}


public void setResult(String result) {
	this.result = result;
}


public String getMessage() {
	return message;
}


public void setMessage(String message) {
	this.message = message;
}


@Override
public String toString() {
	return "ResultDetails [result=" + result + ", message=" + message + "]";
}



}
