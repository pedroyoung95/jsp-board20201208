package article.service;

import java.sql.Connection;
import java.util.Date;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import jdbc.ConnectionProvider;

public class WriteAritcleService {
	
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	
	public Integer write(WriterRequest req) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Article article = toArticle(req);
			
			conn.commit();
			
			return savedArticle.getNumber();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private Article toArticle(WriterRequest req) {
		Date now = new Date();
		return new Article(null, req.getWriter(), req.getTitle(), now, now, 0);
	}
}
