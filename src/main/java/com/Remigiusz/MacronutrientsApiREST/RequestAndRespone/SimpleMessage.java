package com.Remigiusz.MacronutrientsApiREST.Exceptions;

public class SimpleMessage {
	private String message;

	public SimpleMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
