package com.forkd.forkd_backend.controller.uEngagePojos;

public class TrackTaskCallbackResponse {

	private boolean status;
	private String message;
	
	public TrackTaskCallbackResponse(boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
