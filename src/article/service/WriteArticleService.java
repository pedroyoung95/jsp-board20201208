package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.management.RuntimeErrorException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import article.model.ArticleContent;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class WriteArticleService {
	
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	
	public Integer write(WriteRequest req) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Article article = toArticle(req); //WriteRequest객체를 Article로 변환하기 위한 작업
			Article savedArticle = articleDao.insert(conn, article); //insert메소드는 두 번째 파라미터로 Article타입을 받기 때문
			
			if(savedArticle == null) {
				throw new RuntimeException("fail to insert article");
			}
			ArticleContent content = new ArticleContent(savedArticle.getNumber(), req.getContent());
			ArticleContent savedContent = contentDao.insert(conn, content);
			if(savedContent == null) {
				throw new RuntimeException("fail to insert article_content");
			}
			
			conn.commit();
			
			return savedArticle.getNumber();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	private Article toArticle(WriteRequest req) {
		Date now = new Date();
		return new Article(null, req.getWriter(), req.getTitle(), null, null, 0);
	}
}
