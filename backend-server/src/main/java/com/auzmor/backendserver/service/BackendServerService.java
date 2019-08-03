package com.auzmor.backendserver.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auzmor.backendserver.cache.RedisConfiguration;
import com.auzmor.backendserver.model.Account;
import com.auzmor.backendserver.model.PhoneNumber;
import com.auzmor.backendserver.repository.PhoneNumberRepository;
import com.auzmor.backendserver.request.BackendAPIRequest;
import com.auzmor.backendserver.response.BackendAPIResponse;

@Service
public class BackendServerService {
	
	@Autowired
	private PhoneNumberRepository phoneNumberRepository;
	
	public BackendAPIResponse inboundSMS(BackendAPIRequest request, String auth) {
		
		BackendAPIResponse response = new BackendAPIResponse();
		PhoneNumber phoneNumber = phoneNumberRepository.findByNumber(request.getTo());
		if(phoneNumber == null) {
			response.setError("to parameter not found");
			response.setMessage("");
			return response;
		}else {
			
			Account account = phoneNumber.getAccount();
			if(account.getUserName().equals(getDecodeHeader(auth))) {
				
				response.setMessage("inbound sms ok");
				response.setError("");
				
				if(request.getText().equals("STOP") || request.getText().equals("STOP\n") 
						|| request.getText().equals("STOP\r") || request.getText().equals("STOP\r\n")) {
					RedisConfiguration.addData(request.getFrom()+"$"+request.getTo(), "STOP", 4*60*60);
				}
				
			}else {
				response.setMessage("");
				response.setError("to parameter not found");
			}
		}
		
		return response;
	}
	
	
	public BackendAPIResponse outboundSMS(BackendAPIRequest request, String auth) {

		BackendAPIResponse response = new BackendAPIResponse();

		String pair = request.getTo() + "$" + request.getFrom();
		String value = RedisConfiguration.getValue(pair);
		if (!value.equals("STOP")) {
			response.setError("sms from" + request.getFrom() + "to" + request.getTo() + "blocked by STOP request");
			response.setMessage(" ");
			return response;
		}

		PhoneNumber phoneNumber = phoneNumberRepository.findByNumber(request.getFrom());
		if (phoneNumber == null) {
			response.setError("from parameter not found");
			response.setMessage("");
			return response;
		} else {

			Account account = phoneNumber.getAccount();
			if (account.getUserName().equals(getDecodeHeader(auth))) {

				response.setMessage("inbound sms ok");
				response.setError("");

				String fromCount = RedisConfiguration.getValue(request.getFrom());
				if (fromCount == null || fromCount == "") {
					RedisConfiguration.addData(request.getFrom(), Integer.toString(1), 24 * 60 * 60);
				} else {

					long availableTTL = RedisConfiguration.getTTL(request.getFrom());
					if (availableTTL != -1 && (Integer.valueOf(fromCount) + 1 <= 50)) {
						RedisConfiguration.addData(request.getFrom(), String.valueOf(Integer.valueOf(fromCount) + 1));
					} else {
						response.setMessage("");
						response.setError("limit reached for from" + request.getFrom());
					}

				}

			}

		}

		return response;
	}
	
	
	private String getDecodeHeader(String header) {
		String encodedValue = header.substring(header.indexOf(" ")+1);
		Base64.Decoder decoder = Base64.getDecoder();
		String str = new String(decoder.decode(encodedValue));
		return str.substring(0, str.indexOf(":"));
	}

}
