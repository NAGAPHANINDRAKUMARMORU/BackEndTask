package com.vedantu.controllers;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vedantu.exception.VException;
import com.vedantu.managers.ReposManager;
import com.vedantu.requests.ReposReq;
import com.vedantu.responses.CreateReposRes;
import com.vedantu.responses.DeleteReposRes;
import com.vedantu.responses.EditReposNameRes;
import com.vedantu.responses.GetAllReposRes;
import com.vedantu.responses.GetReposRes;
import com.vedantu.responses.UpdateReposRes;
import com.vedantu.utils.LogFactory;

@RestController
@RequestMapping("repos")
public class ReposController {

	 @Autowired
     private LogFactory logFactory;

	 @SuppressWarnings("static-access")
	 private Logger logger = logFactory.getLogger(ReposController.class);
	 
	 @Autowired
	 private ReposManager reposManager;
	 
	 
	 
//create Repositories
		@RequestMapping(value = "/createRepos", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public CreateReposRes createRepos(@RequestBody ReposReq reposReq) throws VException , Exception{
			
			return reposManager.addRepos(reposReq);
		}
		

//delete Repositories by using Repository name and email
		@RequestMapping(value = "/deleteRepos", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public DeleteReposRes deleteRepos(@RequestBody ReposReq reposReq) throws VException , Exception{
			
			return reposManager.deleteRepos(reposReq);
		}

//delete Repositories by using Repository name and email
		@RequestMapping(value = "/upadateRepos", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public UpdateReposRes updateRepos(@RequestBody ReposReq reposReq) throws VException , Exception{
			
			return reposManager.updateRepos(reposReq);
		}		
		
//get all repositories corresponding email
		 @RequestMapping(value = "/getAllRepos", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		 @ResponseBody
		 public  GetAllReposRes getAllRepos(@RequestBody ReposReq reposReq) throws VException , Exception{
		    		
			 return reposManager.getAllRepos(reposReq);
		 }


//get repository corresponding name and email
		 @RequestMapping(value = "/getRepos", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		 @ResponseBody
		 public  GetReposRes getRepos(@RequestBody ReposReq reposReq) throws VException , Exception{
		    		
			 return reposManager.getRepos(reposReq);
		 }
		 
//edit repository  name corresponding name and email
		 @RequestMapping(value = "/editReposName", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		 @ResponseBody
		 public  EditReposNameRes editReposName(@RequestBody ReposReq reposReq) throws VException , Exception{
		    		
			 return reposManager.editReposName(reposReq);
		 }
		 

}
