package ra.ws.storedProcedure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ra.ws.bean.Type;
import ra.ws.utils.Constants;

@Repository
public class RepositoryImpl {
	
	//http://roufid.com/3-ways-to-call-a-stored-procedure-with-hibernate-jpa-2-1/s	
	
	//https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/create-stored-procedure-query.html
	
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	static final Logger logger = LoggerFactory.getLogger(RepositoryImpl.class); 
	
	
	public Map<String, Object> storedProcedureOutString(String procedureName, List<Type> parametersIN) throws Exception {
		return storedProcedure(procedureName, Constants.OUT_STRING, parametersIN, null);		
	}
	
	public Map<String, Object> storedProcedureOutInteger(String procedureName, List<Type> parametersIN) throws Exception {
		return storedProcedure(procedureName, Constants.OUT_INTEGER, parametersIN, null);		
	}
	
	@SuppressWarnings("rawtypes")
	public Map<String, Object> storedProcedureOutCursorMapping(String procedureName, List<Type> parametersIN, Class type) throws Exception {
		return storedProcedure(procedureName, Constants.OUT_CURSOR, parametersIN, type);		
	}
    
	public Map<String, Object> storedProcedureOutCursor(String procedureName, List<Type> parametersIN) throws Exception {
		return storedProcedure(procedureName, Constants.OUT_CURSOR, parametersIN, null);		
	}
	
    @SuppressWarnings("rawtypes")
	public Map<String, Object> storedProcedure(String procedureName, String sp, List<Type> parametersIN, Class typeClass) throws Exception {
    		logger.debug("Start - storedProcedure");	
    	 	Map<String, Object> map;
    	 	StoredProcedureQuery procedureQuery;
    	 	EntityManager entityManager = null;
    	 	
    	 	try {
    	 		logger.debug("Procedure: " + procedureName);
	    		entityManager = entityManagerFactory.createEntityManager();	
	    		if(typeClass == null)
	    			procedureQuery = entityManager.createStoredProcedureQuery(procedureName);
	    		else
	    			procedureQuery = entityManager.createStoredProcedureQuery(procedureName, typeClass);
	    	    
	    	    //Regitration parameters input
	    	    for(Type type:parametersIN) {
	    	    	if(type.getValue() instanceof String)
	    	    		procedureQuery.registerStoredProcedureParameter(type.getName(), String.class, ParameterMode.IN);//Vale anche per il clob.. passiamo un json
	    	    	if(type.getValue() instanceof Integer)
	    	    		procedureQuery.registerStoredProcedureParameter(type.getName(), Integer.class, ParameterMode.IN);
	    	    	if(type.getValue() instanceof List)
	    	    		procedureQuery.registerStoredProcedureParameter(type.getName(), List.class, ParameterMode.IN);//Vale anche per XARRAY??? da provare
	    	    }
	    	    if(sp.equals(Constants.OUT_STRING))
	    	    	procedureQuery.registerStoredProcedureParameter(Constants.SQL_RESULT_OUT, String.class, ParameterMode.OUT);
	    	    else  if(sp.equals(Constants.OUT_INTEGER))
	    	    	procedureQuery.registerStoredProcedureParameter(Constants.SQL_RESULT_OUT, Integer.class, ParameterMode.OUT);
	    	    else  if(sp.equals(Constants.OUT_CURSOR))
	    	    	procedureQuery.registerStoredProcedureParameter(Constants.SQL_RESULT_OUT, void.class, ParameterMode.REF_CURSOR);
	    	    
	    	    procedureQuery.registerStoredProcedureParameter(Constants.SQL_ERROR_CODE_PARAM_OUT, Integer.class, ParameterMode.OUT);
	    	    procedureQuery.registerStoredProcedureParameter(Constants.SQL_ERROR_DESCR_PARAM_OUT, String.class, ParameterMode.OUT);
	    	    procedureQuery.registerStoredProcedureParameter(Constants.SQL_ERROR_MSG_PARAM_OUT, String.class, ParameterMode.OUT);    	    	    	    
	    	    
	    	    //Set value input
	    	    for(Type type:parametersIN) {
	    	    	procedureQuery.setParameter(type.getName(), type.getValue());
	    	    	logger.debug("Parameter input:" + type.toString());
	    	    }
	    	    entityManager.getTransaction().begin();
	    	    procedureQuery.execute();
	    	    
	    	    map = new HashMap<String, Object>();	    	    
	    	    if(sp.equals(Constants.OUT_STRING))
	    	    	map.put(Constants.SQL_RESULT_OUT, procedureQuery.getOutputParameterValue(Constants.SQL_RESULT_OUT));
	    	    else  if(sp.equals(Constants.OUT_INTEGER))
	    	    	map.put(Constants.SQL_RESULT_OUT, procedureQuery.getOutputParameterValue(Constants.SQL_RESULT_OUT));
	    	    else  if(sp.equals(Constants.OUT_CURSOR))
	    	    	map.put(Constants.SQL_RESULT_OUT, procedureQuery.getResultList());
	    	    map.put(Constants.SQL_ERROR_CODE_PARAM_OUT, procedureQuery.getOutputParameterValue(Constants.SQL_ERROR_CODE_PARAM_OUT));
	    	    map.put(Constants.SQL_ERROR_DESCR_PARAM_OUT, procedureQuery.getOutputParameterValue(Constants.SQL_ERROR_DESCR_PARAM_OUT));
	    	    map.put(Constants.SQL_ERROR_MSG_PARAM_OUT, procedureQuery.getOutputParameterValue(Constants.SQL_ERROR_MSG_PARAM_OUT));
	    	    if(resultVerify(map))entityManager.getTransaction().commit();
    	 	} catch (Exception e) {
    	 		if(entityManager!=null)entityManager.getTransaction().rollback();
    			logger.error("The error is: ", e);
    			throw e;
    		} finally {
    			logger.debug("End - storedProcedure");
    		}
    	    return map;
    }

    protected boolean resultVerify(Map<String, Object> outputSP) throws Exception { 
		boolean result = true;
		Integer errorCode = (Integer) outputSP.get(Constants.SQL_ERROR_CODE_PARAM_OUT);
		String errorDescr = (String) outputSP.get(Constants.SQL_ERROR_DESCR_PARAM_OUT);
		String errorMsg = (String) outputSP.get(Constants.SQL_ERROR_MSG_PARAM_OUT);		
		
		if((errorCode != null && errorCode != 0) || errorDescr != null || errorMsg != null) {
			result = false;
			throw new Exception("Error resultVerify - errorCode: " + errorCode + ", errorDescr: " + errorDescr + ",  errorMsg: " + errorMsg);
		}		
		
		return result;
	}	

}