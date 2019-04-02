package com.vedantu.models;

import java.util.List;

public class Repos extends AbstractMongoStringIdEntity {
	
  
	private String email;
	
	private List<ReposList> reposList;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ReposList> getReposList() {
		return reposList;
	}

	public void setReposList(List<ReposList> reposList) {
		this.reposList = reposList;
	}
	
	
	

}
