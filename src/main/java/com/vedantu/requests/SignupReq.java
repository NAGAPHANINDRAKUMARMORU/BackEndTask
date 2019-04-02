package com.vedantu.requests;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import com.vedantu.utils.ReqLimits;
import com.vedantu.utils.ReqLimitsErrorMsgs;




public class SignupReq extends AbstractFrontEndReq{
	
	 public static final String EMPTY = "";
	
	 @NotNull(message =  ReqLimitsErrorMsgs.RQD)
	 
	 
	 @Size(max = ReqLimits.NAME_TYPE_MAX, message = ReqLimitsErrorMsgs.MAX_NAME_TYPE)
	 private String email;
	 
	 @Size(max = ReqLimits.NAME_TYPE_MAX, message = ReqLimitsErrorMsgs.MAX_NAME_TYPE)
	 private String password;
	 
	 @Size(max = ReqLimits.NAME_TYPE_MAX, message = ReqLimitsErrorMsgs.MAX_NAME_TYPE)
	 private String newpassword;
	 
	 @Size(max = ReqLimits.NAME_TYPE_MAX, message = ReqLimitsErrorMsgs.MAX_NAME_TYPE)
	 private String name;
	 
	 @Size(max = ReqLimits.NAME_TYPE_MAX, message = ReqLimitsErrorMsgs.MAX_NAME_TYPE)
	 private String company;
	 
	 @Size(max = ReqLimits.NAME_TYPE_MAX, message = ReqLimitsErrorMsgs.MAX_NAME_TYPE)
	 private String location;
	
	
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	@Override
    protected List<String> collectVerificationErrors() {
		
		
		 List<String> errors = super.collectVerificationErrors();
		 
		 String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		 
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(email);
			
		 if (!matcher.matches()) {
	            errors.add("Invalid Email " /*+ User.Constants.EMAIL*/);
	        }
		
		 if (null == password || EMPTY.equals(password.trim())) {
             errors.add("Invalid " /*+ User.Constants.PASSWORD*/);
		 }
		 return errors;
	}
	
}
