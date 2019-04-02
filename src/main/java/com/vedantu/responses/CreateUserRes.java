package com.vedantu.responses;

import com.vedantu.models.User;

public class CreateUserRes {
	
	  String ErrorMsg= "No error succecfully user created";
	
	 User user;
	
	 Boolean userDetailCreated = Boolean.FALSE;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getUserDetailCreated() {
		return userDetailCreated;
	}

	public void setUserDetailCreated(Boolean userDetailCreated) {
		this.userDetailCreated = userDetailCreated;
	}

	public String getErrorMsg() {
		return ErrorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}
	 
	 
}
