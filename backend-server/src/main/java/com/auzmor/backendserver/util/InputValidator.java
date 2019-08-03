package com.auzmor.backendserver.util;

import com.auzmor.backendserver.request.BackendAPIRequest;
import com.auzmor.backendserver.response.BackendAPIResponse;

public class InputValidator {
	
	public static BackendAPIResponse validator(BackendAPIRequest request) {

		BackendAPIResponse response = new BackendAPIResponse();
		response.setMessage("");
		if (request.getFrom() == null) {
			response.setError("from is missing");
		} else if (request.getTo() == null) {
			response.setError("to is missing");
		} else if (request.getText() == null) {
			response.setError("text is missing");
		} else {
			if (!isFromAndToParameterValid(request.getFrom())) {
				response.setError("from is invalid");
			} else if (!isFromAndToParameterValid(request.getTo())) {
				response.setError("to is invalid");
			} else if (!isTextValid(request.getText())) {
				response.setError("text is invalid");
			}
		}

		return response;

	}
	
	private static boolean isFromAndToParameterValid(String parameter) {
		if (parameter.length() >= 6 && parameter.length() <= 16) {
			return true;
		}
		return false;
	}

	private static boolean isTextValid(String parameter) {
		if (parameter.length() >= 1 && parameter.length() <= 120) {
			return true;
		}
		return false;
	}

}
