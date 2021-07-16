package ra.ws.storedProcedure;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import ra.ws.bean.Type;
import ra.ws.model.Article;
import ra.ws.utils.Constants;

@Repository
public class ArticleRepositoryImpl extends RepositoryImpl{
	
	static final Logger logger = LoggerFactory.getLogger(ArticleRepositoryImpl.class); 	
	
	public Integer pGetMaxArticle() throws Exception {
		logger.debug("Start - pGetMaxArticle");	
		Integer result = null;	
		try {						
			logger.debug("Request for operation pGetMaxArticle");
			Map<String, Object> outputSP = storedProcedureOutInteger("PKG_ARTICLE.P_GET_MAX_ARTICLE", new ArrayList<Type>());
			result = (Integer) outputSP.get(Constants.SQL_RESULT_OUT);
			logger.debug("Response for operation  pGetMaxArticle");
		} catch (Exception e) {
			logger.debug("Generic Exception was thrown while performing query .Error: {}", e.getMessage());
			logger.error("Stack trace is: ", e);
			throw e;
		}finally {
			logger.debug("End - pGetMaxArticle");
		}
		return result;
	}
	
	
	@SuppressWarnings({ "unchecked" })
	public List<Article> pGetArticleList() throws Exception{
		logger.debug("Start - pGetArticleList");	
		List<Article> list = null;	
		try {						
			logger.debug("Request for operation pGetArticleList");
			Map<String, Object> outputSP = storedProcedureOutCursorMapping("PKG_ARTICLE.P_GET_ARTICLE", new ArrayList<Type>(), Article.class);
			list = (List<Article>) outputSP.get(Constants.SQL_RESULT_OUT);
			logger.debug("Response for operation  pGetArticleList");
		} catch (Exception e) {
			logger.debug("Generic Exception was thrown while performing query .Error: {}", e.getMessage());
			logger.error("Stack trace is: ", e);
			throw e;
		}finally {
			logger.debug("End - pGetArticleList");
		}
		return list;
	}
	
	@SuppressWarnings({ "rawtypes" })
	public List<Article> pGetList() throws Exception{
		logger.debug("Start - pGetList");
		List result = null;
		List<Article> list = null;
		Article bean = null;
		try {						
			logger.debug("Request for operation pGetArticleList");
			Map<String, Object> outputSP = storedProcedureOutCursor("PKG_ARTICLE.P_GET_ARTICLE", new ArrayList<Type>());
			result = (List) outputSP.get(Constants.SQL_RESULT_OUT);
			list = new ArrayList<Article>();
			for(Object objList:result) {
				bean = new Article();
				Object[] obj = (Object[]) objList;
				bean.setIdArticle(((BigDecimal)obj[0]).intValue());
				bean.setArticle((String)obj[1]);
				bean.setDescription((String)obj[2]);
				list.add(bean);
			}
			logger.debug("Response for operation  pGetList");
		} catch (Exception e) {
			logger.debug("Generic Exception was thrown while performing query .Error: {}", e.getMessage());
			logger.error("Stack trace is: ", e);
			throw e;
		}finally {
			logger.debug("End - pGetArticleList");
		}
		return list;
	}
	
//	@SuppressWarnings({ "rawtypes" })
//	public List<Article> pGetList() throws Exception{
//		logger.debug("Start - pGetList");
//		List result = null;
//		List<Article> list = null;
//		Article bean = null;
//		try {						
//			logger.debug("Request for operation pGetArticleList");
//			List<Type> parametersIN = new ArrayList<Type>();
//			parametersIN.add(new Type("pi_name", "APPLICATION1"));
//			Map<String, Object> outputSP = storedProcedureOutCursor("PKG_DMT.P_GET_APPLICATION", parametersIN);
//			result = (List) outputSP.get(Constants.SQL_RESULT_OUT);
//			list = new ArrayList<Article>();
//			for(Object objList:result) {
//				bean = new Article();
//				Object[] obj = (Object[]) objList;
//				bean.setIdArticle(((BigDecimal)obj[0]).intValue());
//				bean.setArticle((String)obj[1]);
//				bean.setDescription((String)obj[2]);
//				list.add(bean);
//			}
//			logger.debug("Response for operation  pGetList");
//		} catch (Exception e) {
//			logger.debug("Generic Exception was thrown while performing query .Error: {}", e.getMessage());
//			logger.error("Stack trace is: ", e);
//			throw e;
//		}finally {
//			logger.debug("End - pGetArticleList");
//		}
//		return list;
//	}
	
	
	
	public String pGetOutArticle(Integer idArticolo) throws Exception {
		logger.debug("Start - pGetOutArticle");	
		String result = null;	
		try {						
			logger.debug("Request for operation pGetOutArticle");
			List<Type> parametersIN = new ArrayList<Type>();
			parametersIN.add(new Type("pi_id_article", idArticolo));
			Map<String, Object> outputSP = storedProcedureOutString("PKG_ARTICLE.P_GET_ARTICLE_BY_ID", parametersIN);
			result = (String) outputSP.get(Constants.SQL_RESULT_OUT);
			logger.debug("Response for operation  pGetOutArticle");
		} catch (Exception e) {
			logger.debug("Generic Exception was thrown while performing query .Error: {}", e.getMessage());
			logger.error("Stack trace is: ", e);
			throw e;
		}finally {
			logger.debug("End - pGetOutArticle");
		}
		return result;
	}
	
	
	public String pSetValArticle(Article article) throws Exception {
		logger.debug("Start - pSetValArticle");	
		String result = "";	
		try {						
			logger.debug("Request for operation pSetValArticle");	
			List<Type> parametersIN = new ArrayList<Type>();
			parametersIN.add(new Type("pi_article", article.getArticle()));
			parametersIN.add(new Type("pi_desc_article", article.getDescription()));
			Map<String, Object> outputSP = storedProcedureOutString("PKG_ARTICLE.P_SET_VAL_AND_OUT_VARCHAR_AND_PO_NULL", parametersIN);
			result = (String) outputSP.get(Constants.SQL_RESULT_OUT);
			logger.debug("Response for operation  pSetValArticle");
		} catch (Exception e) {
			logger.debug("Generic Exception was thrown while performing query .Error: {}", e.getMessage());
			logger.error("Stack trace is: ", e);
			throw e;
		}finally {
			logger.debug("End - pSetValArticle");
		}
		return result;
	}
	
	
	
}