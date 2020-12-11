package article.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import article.model.Article;
import article.model.Writer;
import jdbc.JdbcUtil;

public class ArticleDao {
	
	public Article insert(Connection conn, Article article) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO article "
						+ "(writer_id, writer_name, title, regdate, moddate, read_cnt) "
						+ "VALUES(?, ?, ?, SYSDATE, SYSDATE, 0)";
		try {
			pstmt = conn.prepareStatement(sql, new int[] {1});
			
			pstmt.setString(1, article.getWriter().getId());
			pstmt.setString(2, article.getWriter().getName());
			pstmt.setString(3, article.getTitle());
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt == 1) {
				rs = pstmt.getGeneratedKeys();
				int key = 0;
				if(rs.next()) {
					key = rs.getInt(1);
				}
				return new Article(key, 
						article.getWriter(), 
						article.getTitle(), 
						article.getRegDate(),
						article.getModifiedDate(),
						0);
			} else {
				return null;
			}			
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}
	
	private Timestamp toTimeStamp(Date date) {
		return new Timestamp(date.getTime());
	}
}
