package com.auzmor.backendserver.response;

public class BackendAPIResponse {

	private String message;
	private String error;

	public BackendAPIResponse() {
		
	}

	public BackendAPIResponse(String message, String error) {

		this.message = message;
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ResponseObject [message=" + message + ", error=" + error + "]";
	}

}
