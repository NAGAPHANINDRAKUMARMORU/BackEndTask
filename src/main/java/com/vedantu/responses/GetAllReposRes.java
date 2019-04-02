package com.vedantu.responses;


import java.util.List;

import com.vedantu.models.ReposList;

public class GetAllReposRes {
	List<ReposList> reposList;
	String ErrorMsg= "Repositories get succecfully ";

	  
	Boolean ReposeCreated = Boolean.FALSE;


	public List<ReposList> getReposList() {
		return reposList;
	}


	public void setReposList(List<ReposList> reposList) {
		this.reposList = reposList;
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
