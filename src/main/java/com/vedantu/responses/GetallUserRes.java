package com.vedantu.responses;

import java.util.List;

import com.vedantu.models.User;

public class GetallUserRes {
	List<User> allusers;
	
	String ErrorMsg= "get all users succecfully ";

	  
	Boolean ReposeCreated = Boolean.FALSE;


	public List<User> getAllusers() {
		return allusers;
	}


	public void setAllusers(List<User> allusers) {
		this.allusers = allusers;
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
