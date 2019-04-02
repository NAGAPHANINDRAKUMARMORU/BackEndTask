package com.vedantu.responses;

public class DeleteReposRes {
	String ErrorMsg= "Repositories deleted succecfully ";

	  
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
