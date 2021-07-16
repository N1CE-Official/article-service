package ra.ws.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ra.ws.manager.ArticleManager;
import ra.ws.model.Article;
import ra.ws.utils.Constants;
import ra.ws.utils.RestURIConstants;
import ra.ws.utils.Utility;

@RestController
@EnableEurekaClient
public class ArticleController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleManager articleManager;

    @RequestMapping(value = RestURIConstants.POST_ARTICLE, method = RequestMethod.POST)
    public ResponseEntity<String> article(@RequestBody Article articleBean) {
        logger.debug("Start - article");
        
        try {
            Utility.checkParameters(new Object[]{articleBean.getArticle()});

            logger.debug("article - article {} ", articleBean.toString());                         
            articleManager.article(articleBean); 
            
        } catch (Exception e) {
            logger.error("The error is: ", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.debug("End - article");
        }
        return new ResponseEntity<>(Constants.RESULT_OK, HttpStatus.OK);
    }

   
    @RequestMapping(value = RestURIConstants.GET_ARTICLE_BY_ID, method = RequestMethod.GET)
    public ResponseEntity<Article> getArticle(@PathVariable("idArticle") Integer idArticle) {

        logger.debug("Start - getArticle");
        Article article;

        try {
            Utility.checkParameters(new Object[]{idArticle});
            Utility.checkInteger(idArticle);
            logger.debug("getArticle - idArticle {} ", idArticle);
            article = articleManager.getArticle(idArticle);
           
        } catch (Exception e) {
            logger.error("The error is: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.debug("End - getArticle");
        }
        return new ResponseEntity<>(article, HttpStatus.OK);
    }
    
    @RequestMapping(value = RestURIConstants.DELETE_ARTICLE_BY_ID, method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteArticle(@PathVariable("idArticle") Integer idArticle) {
        logger.debug("Start - deleteArticle");

        try {
            Utility.checkParameters(new Object[]{idArticle});
            Utility.checkInteger(idArticle);
            logger.debug("deleteArticle - idArticle {} ", idArticle);
            articleManager.deleteArticle(idArticle);
        } catch (Exception e) {
            logger.error("The error is: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.debug("End - deleteArticle");
        }
        return new ResponseEntity<>(Constants.RESULT_OK, HttpStatus.OK);
    }

//    @HystrixCommand(fallbackMethod = "articleListDefault")
    @RequestMapping(value = RestURIConstants.GET_ARTICLE_LIST, method = RequestMethod.GET)
    public ResponseEntity<List<Article>> articleList() {
        logger.debug("Start - articleList");
        List<Article> list;

        try {
            logger.debug("articleList");
            list = articleManager.articleList();
        } catch (Exception e) {
            logger.error("The error is: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.debug("End - articleList");
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @RequestMapping(value = RestURIConstants.POST_ARTICLE_SP, method = RequestMethod.POST)
    public ResponseEntity<String> articleSP(@RequestBody Article articleBean) {
        logger.debug("Start - articleSP");
        
        try {
            Utility.checkParameters(new Object[]{articleBean.getArticle()});

            logger.debug("articleSP - article {} ", articleBean.toString());                         
            articleManager.articleSP(articleBean); 
            
        } catch (Exception e) {
            logger.error("The error is: ", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.debug("End - articleSP");
        }
        return new ResponseEntity<>(Constants.RESULT_OK, HttpStatus.OK);
    }
    
    @RequestMapping(value = RestURIConstants.GET_ARTICLE_SP_LIST, method = RequestMethod.GET)
    public ResponseEntity<List<Article>> articleListSP() {
        logger.debug("Start - articleListSP");
        List<Article> list;

        try {
            logger.debug("articleListSP");
            list = articleManager.articleListSP();
           
            
        } catch (Exception e) {
            logger.error("The error is: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.debug("End - articleListSP");
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @RequestMapping(value = RestURIConstants.GET_ARTICLE_SP_GENERIC_LIST, method = RequestMethod.GET)
    public ResponseEntity<List<Article>> articleListSPGeneric() {
        logger.debug("Start - articleListSPGeneric");
        List<Article> list;

        try {
            logger.debug("articleListSP");
            list = articleManager.articleListSPGeneric();
           
            
        } catch (Exception e) {
            logger.error("The error is: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.debug("End - articleListSPGeneric");
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @RequestMapping(value = RestURIConstants.GET_ARTICLE_SP_BY_ID, method = RequestMethod.GET)
    public ResponseEntity<String> getArticleSP(@PathVariable("idArticle") Integer idArticle) {

        logger.debug("Start - getArticleSP");
        String result;

        try {
            Utility.checkParameters(new Object[]{idArticle});
            Utility.checkInteger(idArticle);
            logger.debug("getArticle - idArticle {} ", idArticle);
            result = articleManager.getArticleSP(idArticle);
           
        } catch (Exception e) {
            logger.error("The error is: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.debug("End - getArticleSP");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @RequestMapping(value = RestURIConstants.GET_ARTICLE_SP_MAX, method = RequestMethod.GET)
    public ResponseEntity<Integer> getArticleMaxSP() {

        logger.debug("Start - getArticleMaxSP");
        Integer result;

        try {
            logger.debug("getArticleMaxSP");
            result = articleManager.getArticleMaxSP();
           
        } catch (Exception e) {
            logger.error("The error is: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.debug("End - getArticleMaxSP");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    public ResponseEntity<List<Article>> articleListDefault() {
        logger.debug("Start - articleListDefault");
        List<Article> list;

        try {
            logger.debug("articleListDefault");
            list = new ArrayList<Article>();
        } catch (Exception e) {
            logger.error("The error is: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.debug("End - articleListDefault");
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    
    
}
