package com.vedantu.daos;


import com.vedantu.models.User;


import java.util.List;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.vedantu.utils.LogFactory;

@Service
public class UserDAO extends AbstractMongoDAO{
	
	 	@Autowired
	    private LogFactory logFactory;

	 	
	    @SuppressWarnings("static-access")
	    private Logger logger = logFactory.getLogger(UserDAO.class);

	    
	    @Autowired
	    private MongoClientFactory mongoClientFactory;

	    
	    @Override
	    protected MongoOperations getMongoOperations() {
	        return mongoClientFactory.getMongoOperations();
	    }

	    
	    
	    public UserDAO() {
	        super();
	    }
	    
//Create row in mongodb	    
	    public void create(User user) {
	        try {
	            saveEntity(user);
	        } catch (Exception ex) {
	            throw new RuntimeException(
	                    "ContentInfoUpdateError : Error updating the content info " + user, ex);
	        }
	    }
	
//Update the elements in table corresponding id number;
	    public void update(User user) {
	        try {
	            saveEntity(user);
	        } catch (Exception ex) {
	            throw new RuntimeException(
	                    "ContentInfoUpdateError : Error updating the content info " + user, ex);
	        }
	    }
	    
//delete the elements in table corresponding id number;
	    public void delete(User user) {
	        try {
	        	deleteEntityById(user.getId(),User.class);
	        } catch (Exception ex) {
	            throw new RuntimeException(
	                    "ContentInfoUpdateError : Error updating the content info " + user, ex);
	        }
	    }
	    
//get the details about corresponding id number;

	    public User getById(String id) {
	    	User challenge = null;
	        try {
	            challenge = (User) getEntityById(id, User.class);
	        } catch (Exception ex) {
	            throw new RuntimeException("ContentInfoFetchError : Error fetch the content info with id " + id, ex);
	        }
	        return challenge;
	    }
	    
//get the details about corresponding multiple id numbers;
	    public List<User> getStudyEntryItems(List<String> userIds) {
	        Query query;
	        query = new Query();
	        query.addCriteria(Criteria.where("userIds").in(userIds));
	        return runQuery(query, User.class);
	    }

//get the details about corresponding UserId number;
	    public User getStudyEntryItem(String userId) {
	        Query query = new Query();
	        query.addCriteria(Criteria.where(userId).is(userId));
	        logger.info(query);
	        return findOne(query, User.class);
	    }
	    
	    
	    //all users data
	    public List<User> getAll() {
	       	return getAllCustomers(User.class);
	     
	       }
	    
	    
}


