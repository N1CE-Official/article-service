package ra.ws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ra.ws.model.Article;


public interface ArticleRepository extends CrudRepository<Article,Integer> {
	
	@SuppressWarnings("unchecked")
	Article save(Article article);   
	Article findArticleByIdArticle(Integer idArticle); 
	void deleteArticleByIdArticle(Integer idArticle);
    List<Article> findAll();  
    
    //Esempio HQL
    @Query("SELECT a FROM Article a WHERE a.idArticle = :id")
    public List<Article> findAllGeneric(@Param("id") Integer id);


}