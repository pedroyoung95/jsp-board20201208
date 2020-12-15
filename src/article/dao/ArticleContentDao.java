package article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import article.model.ArticleContent;
import jdbc.JdbcUtil;

public class ArticleContentDao {
	
	public ArticleContent insert(Connection conn, ArticleContent content) throws SQLException {
		
		String sql ="INSERT INTO article_content "
						+ "(article_no, content) "
						+ "VALUES(?, ?)";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql);){
			
			pstmt.setLong(1, content.getNumber());
			pstmt.setString(2, content.getContent());
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt == 1) {
				return content;
			} else {
				return null;
			}
		} 
	}
	
	public ArticleContent selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM article_content WHERE article_no=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			ArticleContent content = null;
			if(rs.next()) {
				content = new ArticleContent(rs.getInt("article_no"), rs.getString("content"));
			}
			return content;
		} finally {
			JdbcUtil.close(pstmt);
		}		
	}
	
	//ArticleContentDao는 내용수정 쿼리만 실행(title수정 쿼리는 ArticleDao에서 실행)
	public int update(Connection conn, int no, String content) throws SQLException {
		String sql = "UPDATE article_content SET content=? "
						+ "WHERE article_no=?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, content);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}
	
	public void delete(Connection conn, int no) throws SQLException {
		String sql = "DELETE article_content WHERE article_no=?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}
	}
}
