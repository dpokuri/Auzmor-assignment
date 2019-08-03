package com.auzmor.backendserver.util;

import java.security.NoSuchAlgorithmException;

public class BcryptHashingExample {
	
	public static void main(String[] args) throws NoSuchAlgorithmException
    {
		String temp = "$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi";
		
		String  originalPassword = "password";
        String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
        
        System.out.println(generatedSecuredPasswordHash);
        
        System.out.println(generatedSecuredPasswordHash);
         
        boolean matched = BCrypt.checkpw(originalPassword, temp);
        System.out.println(matched);
        
       
        
        if(generatedSecuredPasswordHash.contentEquals(temp)) {
        	System.out.println("Both are same");
        }
        
        
//        String pw = "{bcrypt}$2a$12$OZuBRPdbL/p/oKsnpE9vzOgSadTeFL7RQicwiihvpHoHFhLpB5.ja";
//        
//        String modified = pw.substring(9);
//        System.out.println("Modified one :"+modified);
        
    }

	

}
