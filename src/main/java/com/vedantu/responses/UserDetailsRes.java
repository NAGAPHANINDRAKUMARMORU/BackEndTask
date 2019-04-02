package com.vedantu.responses;

import com.vedantu.models.User;

public class UserDetailsRes {
	
	User user;
	
	String ErrorMsg= "get the user details using email succecfully ";

	  
	  Boolean ReposeCreated = Boolean.FALSE;


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getErrorMsg() {
		return ErrorMsg;
	}


	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}


	public Boolean getReposeCreated() {
		return ReposeCreated;
	}


	public void setReposeCreated(Boolean reposeCreated) {
		ReposeCreated = reposeCreated;
	}

	  
}
