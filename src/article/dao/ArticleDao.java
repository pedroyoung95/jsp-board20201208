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
			//article_no, regdate, moddate칼럼의 값은 DB에서 생성되는 값임(java에서 생성된 값을 DB에 넣지 않음)
			//DB에서 생성된 값을 받기 위해 preparedStatment메소드 중 두 번째 파라미터로, 칼럼명을 배열로 받는 메소드를 사용
			pstmt = conn.prepareStatement(sql, new String[] {"article_no", "regdate", "moddate"});
			
			pstmt.setString(1, article.getWriter().getId());
			pstmt.setString(2, article.getWriter().getName());
			pstmt.setString(3, article.getTitle());
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt == 1) {
				rs = pstmt.getGeneratedKeys(); //DB에서 생성되는 값을 받기 위해 getGenereatedKeys()메소드 사용
				int key = 0;
				Date regDate = null;
				Date modDate = null;
				if(rs.next()) {
					//preparedStatement(sql, String[] column_name)메소드의 두 번째 파라미터에 적힌 칼럼 순대로 1,2,3번 칼럼
					//DB에서 생성된 각 칼럼의 값을 변수에 담아서 그걸 Article객체에 저장
					key = rs.getInt(1);
					regDate = rs.getTimestamp(2);
					modDate = rs.getTimestamp(3);
				}
				return new Article(key, 
						article.getWriter(), 
						article.getTitle(), 
						regDate,
						modDate,
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
