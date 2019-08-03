package com.auzmor.backendserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auzmor.backendserver.request.BackendAPIRequest;
import com.auzmor.backendserver.response.BackendAPIResponse;
import com.auzmor.backendserver.service.BackendServerService;
import com.auzmor.backendserver.util.InputValidator;

@RestController
@RequestMapping("/api")
public class BackendServerController {
	
	@Autowired
	private BackendServerService backendServerService;
	
	@PostMapping("/inbound/sms")
	public ResponseEntity<BackendAPIResponse> inBoundSMS(@RequestHeader(value = "Authorization") String auth,
			@RequestBody BackendAPIRequest request) {
		
		BackendAPIResponse response = new BackendAPIResponse();
		if (request == null) {
			response.setError("unknown failure");
			response.setMessage("");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else if (InputValidator.validator(request).getError() != null) {
			return ResponseEntity.status(HttpStatus.OK).body(InputValidator.validator(request));
		} else {

			response = backendServerService.inboundSMS(request, auth);
		}

		return ResponseEntity.ok(response);
	}

	@PostMapping("/outbound/sms")
	public ResponseEntity<BackendAPIResponse> outBoundSMS(@RequestBody BackendAPIRequest request) {
		
		BackendAPIResponse response = new BackendAPIResponse();
		if (request == null) {
			response.setError("unknown failure");
			response.setMessage("");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else if (InputValidator.validator(request).getError() != null) {
			return ResponseEntity.status(HttpStatus.OK).body(InputValidator.validator(request));
		}else {
			
		}
		
		return null;
	}
	
	

}
