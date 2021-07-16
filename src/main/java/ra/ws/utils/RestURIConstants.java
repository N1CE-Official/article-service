package ra.ws.utils;

public class RestURIConstants {

    private static final String BASE_AUTHENTICATED_URL = "/secure/";

    public static final String POST_ARTICLE = BASE_AUTHENTICATED_URL + "article";
    public static final String GET_ARTICLE_BY_ID = BASE_AUTHENTICATED_URL + "article/{idArticle}";
    public static final String DELETE_ARTICLE_BY_ID = BASE_AUTHENTICATED_URL + "article/{idArticle}";
    public static final String GET_ARTICLE_LIST = BASE_AUTHENTICATED_URL + "article/detail/articleList";
    
    public static final String POST_ARTICLE_SP = BASE_AUTHENTICATED_URL + "articleSP";
    public static final String GET_ARTICLE_SP_BY_ID = BASE_AUTHENTICATED_URL + "articleSP/{idArticle}";
    public static final String GET_ARTICLE_SP_LIST = BASE_AUTHENTICATED_URL + "articleSP/detail/articleList";
    public static final String GET_ARTICLE_SP_GENERIC_LIST = BASE_AUTHENTICATED_URL + "articleSP/detail/articleGenericList";
    public static final String GET_ARTICLE_SP_MAX = BASE_AUTHENTICATED_URL + "articleMaxSP";

}
