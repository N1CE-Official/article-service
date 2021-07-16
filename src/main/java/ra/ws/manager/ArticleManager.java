package ra.ws.manager;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ra.ws.model.Article;
import ra.ws.repository.ArticleRepository;
import ra.ws.storedProcedure.ArticleRepositoryImpl;

@Service
@Transactional
public class ArticleManager {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private ArticleRepositoryImpl articleRepositoryImpl;

	private static final Logger logger = LoggerFactory.getLogger(ArticleManager.class);    

	public void article(Article article) throws Exception {
		logger.debug("Start - article");

		try {
			articleRepository.save(article);
		} catch (Exception e) {
			logger.error("The error is: ", e);
			throw e;
		} finally {
			logger.debug("End - article");
		}
	}

	public Article getArticle(Integer idArticle) {
		logger.debug("Start - getArticle - idArticle: {}", idArticle);
		Article article;

		try {
			article = articleRepository.findArticleByIdArticle(idArticle);
		} catch (Exception e) {
			logger.error("The error is: ", e);
			throw e;
		} finally {
			logger.debug("End - getArticle");
		}

		return article;
	}
	
	public void deleteArticle(Integer id) {
		logger.debug("Start - deleteUser - id: {}", id);

		try {
			articleRepository.deleteArticleByIdArticle(id);
		} catch (Exception e) {
			logger.error("The error is: ", e);
			throw e;
		} finally {
			logger.debug("End - deleteUser");
		}
	}

	public List<Article> articleList() throws Exception {
		logger.debug("Start - articleList");
		List<Article> list = new ArrayList<Article>();

		try {
			list = articleRepository.findAll();	
//			list = articleRepository.findAllGeneric(1);
		} catch (Exception e) {
			logger.error("The error is: ", e);
			throw e;
		} finally {
			logger.debug("End - articleList");
		}
		return list;
	}

	public Integer getArticleMaxSP() throws Exception {
		logger.debug("Start - getArticleMaxSP");
		Integer result = null;

		try {
			result = articleRepositoryImpl.pGetMaxArticle();			
		} catch (Exception e) {
			logger.error("The error is: ", e);
			throw e;
		} finally {
			logger.debug("End - getArticleMaxSP");
		}
		return result;
	}
	
	public String getArticleSP(Integer idArticolo) throws Exception {
		logger.debug("Start - getArticleSP");
		String result = null;

		try {			
			result  = articleRepositoryImpl.pGetOutArticle(idArticolo);
					
		} catch (Exception e) {
			logger.error("The error is: ", e);
			throw e;
		} finally {
			logger.debug("End - getArticleSP");
		}
		return result;
	}

	public List<Article> articleListSP() throws Exception {
		logger.debug("Start - articleListSP");
		List<Article> list = new ArrayList<Article>();

		try {
			list = articleRepositoryImpl.pGetArticleList();
		} catch (Exception e) {
			logger.error("The error is: ", e);
			throw e;
		} finally {
			logger.debug("End - articleListSP");
		}
		return list;
	}
	
	public List<Article> articleListSPGeneric() throws Exception {
		logger.debug("Start - articleListSPGeneric");
		List<Article> list = new ArrayList<Article>();

		try {
			list = articleRepositoryImpl.pGetList();	
		} catch (Exception e) {
			logger.error("The error is: ", e);
			throw e;
		} finally {
			logger.debug("End - articleListSPGeneric");
		}
		return list;
	}
	
	public String articleSP(Article article) throws Exception {
		logger.debug("Start - articleList");
		String result = null;

		try {
			result = articleRepositoryImpl.pSetValArticle(article);
		} catch (Exception e) {
			logger.error("The error is: ", e);
			throw e;
		} finally {
			logger.debug("End - articleList");
		}
		return result;
	}
	
	
}
