package ra.ws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table (name = "ARTICLE")
public class Article implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idArticle;
	private String article;
	private String description;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "ID_ARTICLE")
	public Integer getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(Integer idArticle) {
		this.idArticle = idArticle;
	}

	@Column (name = "ARTICLE")
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}

	@Column (name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Article [idArticle=");
		builder.append(idArticle);
		builder.append(", article=");
		builder.append(article);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}


}
