package com.vedantu.managers;


import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.vedantu.daos.ReposDAO;
import com.vedantu.daos.UserDAO;
import com.vedantu.exception.VException;
import com.vedantu.models.Repos;
import com.vedantu.models.User;
import com.vedantu.requests.SignupReq;
import com.vedantu.responses.ChangePasswordRes;
import com.vedantu.responses.CreateUserRes;
import com.vedantu.responses.GetallUserRes;
import com.vedantu.responses.UserDetailsRes;

import java.util.regex.Matcher;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;

@Service
public class UserManager {
	
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private ReposDAO reposDAO;


	/*@Autowired
    public PasswordEncoder passwordEncoder;*/
	
	public static final String EMPTY = "";
	
	private static final String UPLOAD_DIRECTORY ="/"; 
	
	
	
	
	
	
	
	
	
	
		
	public CreateUserRes createuser(SignupReq temp) {
		User newuserdata = new User();
		
		Repos newreposdata = new Repos();
		
		CreateUserRes createUserRes = new CreateUserRes();
		
		if (null == temp.getCompany() || EMPTY.equals(temp.getCompany().trim())) {
			createUserRes.setErrorMsg("company name is empty");
			return createUserRes;
		}
		else {
			newuserdata.setEmail(temp.getEmail());
		}
				
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(temp.getEmail());
		
		User userdata = userDAO.getEntityByemail(temp.getEmail(), User.class);
		
				
		if (matcher.matches()) {
			if(userdata == null) {
				newuserdata.setEmail(temp.getEmail());
				newreposdata.setEmail(temp.getEmail());
			}
			else {
				createUserRes.setErrorMsg("email alredy existed");
				return createUserRes;
			}
		   }
		else {
			createUserRes.setErrorMsg("Invalid email");
			return createUserRes;
		}
		
		//String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
		 String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[@#$%]).{6,20})";
		Pattern pattern_p = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher_p = pattern_p.matcher(temp.getPassword());
		
		if(!matcher.matches()){
			createUserRes.setErrorMsg("password must contains one alphabet, one number and one special character ");
			return createUserRes;
		}
		
		
		if (null == temp.getPassword() || EMPTY.equals(temp.getPassword().trim())) {
			createUserRes.setErrorMsg("password is empty");
			return createUserRes;
		}
		else {
			 /*String hashedPassword = passwordEncoder.encode(user.getPassword());*/
			newuserdata.setPassword(temp.getPassword());
		}

				
		if (null == temp.getLocation() || EMPTY.equals(temp.getLocation().trim())) {
			createUserRes.setErrorMsg("location is empty");
			return createUserRes;
		}
		else {
			newuserdata.setLocation(temp.getLocation());
		}

		
		if (null == temp.getName() || EMPTY.equals(temp.getName().trim())) {
			createUserRes.setErrorMsg("name is empty");
			return createUserRes;
		}
		else {
			newuserdata.setName(temp.getName());
		}
		
		userDAO.create(newuserdata);
	
		newreposdata.setReposList(null);
		
		reposDAO.create(newreposdata);
				
		createUserRes.setUserDetailCreated(true);
		createUserRes.setUser(newuserdata);
		
		return createUserRes;
	}
	
	
	
	
	
	
	
	
	
	
	
	public GetallUserRes getallUsers() {
		
		GetallUserRes getallUserRes =new GetallUserRes();		
		List<User> userslist = userDAO.getAll();
		getallUserRes.setAllusers(userslist);
		if (userslist.isEmpty()) {
			getallUserRes.setErrorMsg("There is no user in user list");
			return getallUserRes;
		}
		getallUserRes.setReposeCreated(true);
		return getallUserRes;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public UserDetailsRes getUserDetailsByEmail(String email) {
		UserDetailsRes userDetailsRes = new UserDetailsRes();
				
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			userDetailsRes.setErrorMsg("Invalid email");
			return userDetailsRes;
		}
		
		User userdata = userDAO.getEntityByemail(email, User.class);
		
		if(userdata == null) {
			userDetailsRes.setErrorMsg("email does not exits");
			return userDetailsRes;
		}
		
		userDetailsRes.setUser(userdata);
		
		userDetailsRes.setReposeCreated(true);
		return userDetailsRes;
			
	}
	
	
	
	
	
	
	
	
	
	
	
	public ChangePasswordRes changePassword(SignupReq signupReq) {
		ChangePasswordRes changePasswordRes=new  ChangePasswordRes();
		
		if (null == signupReq.getPassword() || EMPTY.equals(signupReq.getPassword().trim())) {
			changePasswordRes.setErrorMsg("user password is empty");
			return changePasswordRes;
		}
		
		if (null == signupReq.getNewpassword() || EMPTY.equals(signupReq.getNewpassword().trim())) {
			changePasswordRes.setErrorMsg("user new password is empty");
			return changePasswordRes;
		}
		
		UserDetailsRes userdata= getUserDetailsByEmail(signupReq.getEmail());
		
		if(!userdata.getReposeCreated()) {
			changePasswordRes.setErrorMsg(userdata.getErrorMsg());
			 return changePasswordRes;
		}
		
		User userdatauser=userdata.getUser();
		if(userdatauser.getPassword().equals(signupReq.getPassword())) {
			User updateuserdata = userDAO.getEntityByemail(signupReq.getEmail(), User.class);
			updateuserdata.setPassword(signupReq.getNewpassword());
			userDAO.update(updateuserdata);
			changePasswordRes.setReposeCreated(true);
			return changePasswordRes;
			
		}else {
			changePasswordRes.setErrorMsg("user password does not match");
			return changePasswordRes;
		}
	
	}
	
	
	
	
	
	
	
	
	
	
		
	public ModelAndView profilePicSave(CommonsMultipartFile file, HttpSession session) throws VException,Exception {
		
		 ServletContext context = session.getServletContext();  
		    String path = context.getRealPath(UPLOAD_DIRECTORY);  
		    String filename = file.getOriginalFilename();  
		  
//		    String pa="/home/vipl037/usersProfilePics/"; //this path is hard coded
		    System.out.println(path+" "+filename);        
		   
		    
		    byte[] bytes = file.getBytes();  
		   //location where the profile is save
		    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(new File(path + File.separator + filename)));  
		    stream.write(bytes);  
		    stream.flush();  
		    stream.close(); 
		    
	return new ModelAndView("uploadform","filesuccess","File successfully saved!"); 	
	}
	
	
	
	
	
	
	
	
	
	
	
	 public String getImage(String imageName,HttpSession session) throws IOException {
		 
		 ServletContext context = session.getServletContext(); 
 		 String path = context.getRealPath(UPLOAD_DIRECTORY);  

         return path + imageName+".jpg";
	
	 }
	
	     
		 
	
	
	
	
}
