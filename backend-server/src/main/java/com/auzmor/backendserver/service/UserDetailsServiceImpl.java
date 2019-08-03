package com.auzmor.backendserver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auzmor.backendserver.model.Account;
import com.auzmor.backendserver.repository.AccountRepository;
import com.auzmor.backendserver.util.BCrypt;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Account account = accountRepository.findByUserName(userName);
		User user = buildUserForAuthentication(account);
		UserDetails userDetails = (UserDetails) user;
		return userDetails;

	}
	

	private User buildUserForAuthentication(Account acount) {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		authorities.add(new SimpleGrantedAuthority("ADMIN"));
		
		String generatedSecuredPasswordHash = BCrypt.hashpw(acount.getAuthId(), BCrypt.gensalt(12));
		
		
		User user = new User(acount.getUserName(), generatedSecuredPasswordHash, true, true, true, true, authorities);
		return user;
	}

}
