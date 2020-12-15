package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import article.model.ArticleContent;
import jdbc.ConnectionProvider;

public class ReadArticleService {
	
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	
	public ArticleData getArticle(int articleNum, boolean increaseReadCount) {
		try(Connection conn = ConnectionProvider.getConnection();) {
			Article article = articleDao.selectById(conn, articleNum);
			if(article == null) {
				throw new ArticleNotFoundException();
			}
			ArticleContent content = contentDao.selectById(conn, articleNum);
			if(content == null) {
				throw new ArticleContentNotFoundException();
			}
			if(increaseReadCount) {
				articleDao.increaseReadCount(conn, articleNum); //increaseReadCount파라미터의 값이 true일 때만 조회수 증가
			}
			return new ArticleData(article, content);
		} catch(SQLException e) {
			throw new RuntimeException();
		}
	}
}
