package com.auzmor.backendserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @NotNull
	@Column(name="auth_id", unique = true)
	private String authId;
	
    @NotNull
	@Column(name="username", unique = true)
	private String userName;

	public Account() {
		
	}

	public Account(Long id, String authId, String userName) {
		this.id = id;
		this.authId = authId;
		this.userName = userName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", auth_id=" + authId + ", userName=" + userName + "]";
	}

}
