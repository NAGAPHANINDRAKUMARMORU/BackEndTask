package com.vedantu.controllers;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.vedantu.exception.VException;
import com.vedantu.managers.UserManager;
import com.vedantu.requests.SignupReq;
import com.vedantu.responses.ChangePasswordRes;
import com.vedantu.responses.CreateUserRes;
import com.vedantu.responses.GetallUserRes;
import com.vedantu.responses.UserDetailsRes;
import com.vedantu.utils.LogFactory;

import java.io.IOException;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("user")
public class UserController {
	
	 @Autowired
     private LogFactory logFactory;

	 @SuppressWarnings("static-access")
	 private Logger logger = logFactory.getLogger(UserController.class);
	    
	@Autowired
	UserManager userManager;
	
	
//create account	
	@RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CreateUserRes signup(@RequestBody SignupReq temp) throws VException, Exception {		
		return userManager.createuser(temp);
	}
	
	
//get all users in users list	
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetallUserRes getallUsers() throws Exception {
		return  userManager.getallUsers();
    }

	
/* get user by using email	
	@RequestMapping(value = "/getUserDetailsByEmail/{email}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User getuserDetailsByEmailbrowser(@PathVariable("email") String email) throws Exception {	
		return userManager.getUserDetailsByEmail(email);
	}*/
	 
	
//get user by using email	
	@RequestMapping(value = "/getUserDetailsByEmail", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserDetailsRes getuserDetailsByEmail(@RequestBody SignupReq temp/*@RequestParam(value = "email") String email*/) throws Exception {
		return userManager.getUserDetailsByEmail(temp.getEmail());
	}

	
//change the user password
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ChangePasswordRes changePassword(@RequestBody SignupReq temp/*@RequestParam(value = "email") String email*/) throws Exception {		
		return userManager.changePassword(temp);
	}
	
	
	
//generate ProfilePic path
	@RequestMapping(value = "/profilePicSave", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ModelAndView profilePicSave(@RequestParam CommonsMultipartFile file, HttpSession session) throws Exception {
		
		return userManager.profilePicSave(file,session);
	}
	

	
//getThelocationsaveimage	
	@RequestMapping(value = "/image", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public String getImage(@RequestParam(value = "imageName") String imageName,HttpSession session) throws IOException {
     
        return userManager.getImage(imageName,session) ;
    }

	
	
}
