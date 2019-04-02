package com.vedantu.responses;

public class ChangePasswordRes {
	
	 String ErrorMsg= "Password changed succecfully";

	  
	  Boolean ReposeCreated = Boolean.FALSE;


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
