package article.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
	
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM article";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(stmt);
		}
	}
	
	public List<Article> select(Connection conn, int pageNum, int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//oracle 커리
		//paging 함수가 없기 때문에 돌아가는 방법으로 쿼리를 작성해야 함
		String sql = "SELECT * FROM( "
						+ "SELECT article_no, writer_id, writer_name, title, regdate, moddate, read_cnt, "
						+ "ROW_NUMBER() OVER(ORDER BY article_no DESC)rn FROM article) "
						+ "WHERE rn BETWEEN ? AND ?";
//		my sql 쿼리
//		String sql = "SELECT * FROM article "
//						+ "ORDER BY article_no DESC "
//						+ "LIMIT ?, ?"; -> 시작 row_num(zerobase, 0부터 시작), 갯수 가 각각 ?에 갑으로 들어감
		
		try {
			pstmt = conn.prepareStatement(sql);
			//위 sql문에 ?에 값을 넣을 때 있는 그대로 넣었을 때 1쪽부터 몇 개 이런 식으로 의도대로 나오지 않음
			//따라서 다음과 같이 숫자를 계산해서 할당해야 함
			pstmt.setInt(1, (pageNum - 1) * size + 1);
			pstmt.setInt(2, pageNum * size);
			
			rs = pstmt.executeQuery();
			
			List<Article> result = new ArrayList<>();
			while(rs.next()) {
				result.add(convertArticle(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	private Article convertArticle(ResultSet rs) throws SQLException {
		return new Article(rs.getInt("article_no"), 
				new Writer(
						rs.getString("writer_id"),
						rs.getString("writer_name")), 
				rs.getString("title"), 
				toDate(rs.getTimestamp("regdate")),
				toDate(rs.getTimestamp("moddate")),
				rs.getInt("read_cnt"));
	}
	
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
}
