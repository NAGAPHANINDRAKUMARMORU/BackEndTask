package com.vedantu.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vedantu.daos.ReposDAO;
import com.vedantu.models.Repos;
import com.vedantu.models.ReposList;
import com.vedantu.requests.ReposReq;
import com.vedantu.responses.CreateReposRes;
import com.vedantu.responses.DeleteReposRes;
import com.vedantu.responses.EditReposNameRes;
import com.vedantu.responses.GetAllReposRes;
import com.vedantu.responses.GetReposRes;
import com.vedantu.responses.UpdateReposRes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ReposManager {
	
	
	@Autowired
	private ReposDAO reposDAO;
	
	public static final String EMPTY = "";
	
	
	
	
	
	
	
	
	
	
		
	public CreateReposRes addRepos(ReposReq reposReq) {
		CreateReposRes createReposRes = new CreateReposRes();
		Repos reposdata = reposDAO.getEntityByemail(reposReq.getEmail(), Repos.class);
		
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(reposReq.getEmail());
		if (!matcher.matches()) {
			createReposRes.setErrorMsg("Invalid email");
			return createReposRes;
		}
		if(reposdata == null) {
			createReposRes.setErrorMsg("email does not exits");
			return createReposRes;
		}
		
		List<ReposList> reposlist= reposdata.getReposList();
		
		if(reposlist==null) {
			List<ReposList> newreposlist = new ArrayList<ReposList>();
			if (null == reposReq.getName() || EMPTY.equals(reposReq.getName().trim())) {
				createReposRes.setErrorMsg("Repository name is empty");
				return createReposRes;
			}else {
				newreposlist.add(new ReposList(reposReq.getName(),reposReq.getDescription(),reposReq.getLanguage()));
				reposdata.setReposList(newreposlist);
			}
		}else {
			for(ReposList reposlistitem :reposlist) {
				if(reposlistitem.getName().equals(reposReq.getName())) {
					createReposRes.setErrorMsg("Repository name alredy existed");
					return createReposRes;
				}
			}
			reposlist.add(new ReposList(reposReq.getName(),reposReq.getDescription(),reposReq.getLanguage()));
			reposdata.setReposList(reposlist);
		}
		
		reposDAO.create(reposdata);
		createReposRes.setReposeCreated(true);
		return createReposRes;
	}
	
	
	
	
	
	
	
	
	
	
	
	public DeleteReposRes deleteRepos(ReposReq reposReq) {
		DeleteReposRes deleteReposRes = new DeleteReposRes();
		
		if (null == reposReq.getName() || EMPTY.equals(reposReq.getName().trim())) {
			deleteReposRes.setErrorMsg("Repository name is empty");
			return deleteReposRes;
		}
		
		if (null == reposReq.getEmail() || EMPTY.equals(reposReq.getEmail().trim())) {
			deleteReposRes.setErrorMsg("Repository email is empty");
			return deleteReposRes;
		}
		
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(reposReq.getEmail());
		if (!matcher.matches()) {
			deleteReposRes.setErrorMsg("Invalid email");
			return deleteReposRes;
		}
		
		Repos reposdata = reposDAO.getEntityByemail(reposReq.getEmail(), Repos.class);
		
		
		if(reposdata == null) {
			deleteReposRes.setErrorMsg("email does not exits");
			return deleteReposRes;
		}
		List<ReposList> reposlist= reposdata.getReposList();
		List<ReposList> reposlisttemp= new ArrayList<ReposList>();
		
		boolean testing=true;
		
		if(reposlist==null || reposlist.isEmpty()) {
			deleteReposRes.setErrorMsg("repository list is empty");
			return deleteReposRes;
		}
		else {
			for(ReposList reposlistitem :reposlist) {
				if(!reposlistitem.getName().equals(reposReq.getName())) {
					reposlisttemp.add(reposlistitem);
				}else {
					testing=false;
				}
			}
		}
		
		if(testing) {
			deleteReposRes.setErrorMsg("repository list doesn't contain given name "+reposReq.getName());
			return deleteReposRes;
		}
				
		reposdata.setReposList(reposlisttemp);
		reposDAO.update(reposdata); 
		
		deleteReposRes.setReposeCreated(true);
		return deleteReposRes;
	}
	
	
	
	
	
	
	
	
	
	
		
	public UpdateReposRes updateRepos(ReposReq reposReq) {
		UpdateReposRes updateReposRes =new UpdateReposRes();
		
		if (null == reposReq.getName() || EMPTY.equals(reposReq.getName().trim())) {
			updateReposRes.setErrorMsg("Repository name is empty");
			return updateReposRes;
		}
		
		if (null == reposReq.getEmail() || EMPTY.equals(reposReq.getEmail().trim())) {
			updateReposRes.setErrorMsg("Repository email is empty");
			return updateReposRes;
		}
		
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(reposReq.getEmail());
		if (!matcher.matches()) {
			updateReposRes.setErrorMsg("Invalid email");
			return updateReposRes;
		}
		
		Repos reposdata = reposDAO.getEntityByemail(reposReq.getEmail(), Repos.class);
		
		
		if(reposdata == null) {
			updateReposRes.setErrorMsg("email does not exits");
			return updateReposRes;
		}
		
		List<ReposList> reposlist= reposdata.getReposList();
		List<ReposList> reposlisttemp= new ArrayList<ReposList>();
		ReposList reposUpdateItem	= new ReposList();	
		
		boolean testing=true;
		if(reposlist==null || reposlist.isEmpty()) {
			updateReposRes.setErrorMsg("repository list is empty");
			return updateReposRes;
		}
		else {
			for(ReposList reposlistitem :reposlist) {
				if(!reposlistitem.getName().equals(reposReq.getName())) {
					reposlisttemp.add(reposlistitem);
				}else {
					testing=false;
					reposUpdateItem.setName(reposReq.getName());
					
					if( reposReq.getDescription()==null) {
						reposUpdateItem.setDescription(reposlistitem.getDescription());
					}
					else {
						reposUpdateItem.setDescription(reposReq.getDescription());
					}
					
					if( reposReq.getLanguage()==null ) {
						reposUpdateItem.setLanguage(reposlistitem.getLanguage());
					}
					else {
						reposUpdateItem.setLanguage(reposReq.getLanguage());
					}
					
					reposlisttemp.add(reposUpdateItem);
				}
			}
		}
		
		if(testing) {
			updateReposRes.setErrorMsg("repository list doesn't contain given name "+reposReq.getName());
			return updateReposRes;
		}	
		
		reposdata.setReposList(reposlisttemp);
		reposDAO.update(reposdata); 
		
		updateReposRes.setReposeCreated(true);
		return updateReposRes;
	}
	
	
	
	
	
	
	
	
	
	
	
	public GetAllReposRes getAllRepos(ReposReq reposReq) {
		GetAllReposRes getAllReposRes=new GetAllReposRes();
		if (null == reposReq.getEmail() || EMPTY.equals(reposReq.getEmail().trim())) {
			getAllReposRes.setErrorMsg("Repository email is empty");
			return getAllReposRes;
		}
		
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(reposReq.getEmail());
		if (!matcher.matches()) {
			getAllReposRes.setErrorMsg("Invalid email");
			return getAllReposRes;
		}
		
		Repos reposdata = reposDAO.getEntityByemail(reposReq.getEmail(), Repos.class);
		
		
		if(reposdata == null) {
			getAllReposRes.setErrorMsg("email does not exits");
			return getAllReposRes;
		}
		
		
		List<ReposList> reposlist= reposdata.getReposList();
		
		if(reposlist==null || reposlist.isEmpty()) {
			getAllReposRes.setErrorMsg("repository list is empty");
			return getAllReposRes;
		}
		else {
			getAllReposRes.setReposList(reposlist);	
		}
		
		getAllReposRes.setReposeCreated(true);
		return getAllReposRes;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public GetReposRes getRepos(ReposReq reposReq) {
		GetReposRes getReposRes =new GetReposRes();
		
		
		if (null == reposReq.getName() || EMPTY.equals(reposReq.getName().trim())) {
			getReposRes.setErrorMsg("Repository name is empty");
			return getReposRes;
		}
		
		if (null == reposReq.getEmail() || EMPTY.equals(reposReq.getEmail().trim())) {
			getReposRes.setErrorMsg("Repository email is empty");
			return getReposRes;
		}
				
		GetAllReposRes reposs=getAllRepos(reposReq);
		
		getReposRes.setErrorMsg(reposs.getErrorMsg());
		getReposRes.setReposeCreated(false);
		
		
		List<ReposList> reposlist= reposs.getReposList();
		

		boolean testing=true;
		
		if(reposlist==null || reposlist.isEmpty()) {
			getReposRes.setErrorMsg(reposs.getErrorMsg());
			return getReposRes;
		}
		else {
			for(ReposList reposlistitem :reposlist) {
				if(reposlistitem.getName().equals(reposReq.getName())) {
					getReposRes.setReposList(reposlistitem);
					testing=false;
				}
			}
			
		}
		
		if(testing) {
			getReposRes.setErrorMsg("repository name not exist in list");
			return getReposRes;
		}
			
		getReposRes.setReposeCreated(true);
		return getReposRes;
	}
	
	
	
	
	
	
	
	
	
	
	public EditReposNameRes editReposName(ReposReq reposReq) {
		EditReposNameRes editReposNameRes = new EditReposNameRes();
		
		if (null == reposReq.getNewname() || EMPTY.equals(reposReq.getNewname().trim())) {
			editReposNameRes.setErrorMsg("Repository newname is empty");
			return editReposNameRes;
		}
		
		 GetReposRes reposs=  getRepos(reposReq);
		 if(!reposs.getReposeCreated()) {
			 editReposNameRes.setErrorMsg(reposs.getErrorMsg());
			 return editReposNameRes;
		 }
		 
		 Repos reposdata = reposDAO.getEntityByemail(reposReq.getEmail(), Repos.class);
		 
		 List<ReposList> reposlist= reposdata.getReposList();
		 List<ReposList> reposlisttemp= new ArrayList<ReposList>();
		 for(ReposList reposlistitem :reposlist) {
			 if(!reposlistitem.getName().equals(reposReq.getName())) {
				 reposlisttemp.add(reposlistitem);
			 }else {
				 reposlistitem.setName(reposReq.getNewname());
				 reposlisttemp.add(reposlistitem);
			 }
		 }
		 
		 
		reposdata.setReposList(reposlisttemp);
		reposDAO.update(reposdata);
		 		
		editReposNameRes.setReposeCreated(true);
		return editReposNameRes;
	}
	
	
	
	
	
	
	
	
	
}
