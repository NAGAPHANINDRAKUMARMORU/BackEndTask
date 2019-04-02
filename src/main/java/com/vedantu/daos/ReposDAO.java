package com.vedantu.daos;

import com.vedantu.models.Repos;


import java.util.Collection;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.vedantu.utils.LogFactory;

@Service
public class ReposDAO extends AbstractMongoDAO{
	
	 	@Autowired
	    private LogFactory logFactory;

	 	
	    @SuppressWarnings("static-access")
	    private Logger logger = logFactory.getLogger(ReposDAO.class);

	    
	    @Autowired
	    private MongoClientFactory mongoClientFactory;

	    
	    @Override
	    protected MongoOperations getMongoOperations() {
	        return mongoClientFactory.getMongoOperations();
	    }

	    
	    
	    public ReposDAO() {
	        super();
	    }
	    
//Create row in mongodb	    
	    public void create(Repos repos) {
	        try {
	            saveEntity(repos);
	        } catch (Exception ex) {
	            throw new RuntimeException(
	                    "ContentInfoUpdateError : Error updating the content info " + repos, ex);
	        }
	    }
	
//Update the elements in table corresponding id number;
	    public void update(Repos repos) {
	        try {
	            saveEntity(repos);
	        } catch (Exception ex) {
	            throw new RuntimeException(
	                    "ContentInfoUpdateError : Error updating the content info " + repos, ex);
	        }
	    }
	    
//delete the elements in table corresponding id number;
	    public void delete(Repos repos) {
	        try {
	        	deleteEntityById(repos.getId(),Repos.class);
	        } catch (Exception ex) {
	            throw new RuntimeException(
	                    "ContentInfoUpdateError : Error updating the content info " + repos, ex);
	        }
	    }
	    
//get the details about corresponding id number;

	    public Repos getById(String id) {
	    	Repos challenge = null;
	        try {
	            challenge = (Repos) getEntityById(id, Repos.class);
	        } catch (Exception ex) {
	            throw new RuntimeException("ContentInfoFetchError : Error fetch the content info with id " + id, ex);
	        }
	        return challenge;
	    }
	    
//get the details about corresponding multiple id numbers;
	    public List<Repos> getStudyEntryItems(List<String> userIds) {
	        Query query;
	        query = new Query();
	        query.addCriteria(Criteria.where("userIds").in(userIds));
	        return runQuery(query, Repos.class);
	    }

//get the details about corresponding UserId number;
	    public Repos getStudyEntryItem(String userId) {
	        Query query = new Query();
	        query.addCriteria(Criteria.where(userId).is(userId));
	        logger.info(query);
	        return findOne(query, Repos.class);
	    }
	    
	    
	    //all repos data
	    public Collection<Repos> getAll() {
	       	return getAllCustomers(Repos.class);
	     
	       }
	    
	    
}


