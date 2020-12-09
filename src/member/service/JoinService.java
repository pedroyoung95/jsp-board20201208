package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDao;
import member.model.Member;

public class JoinService {
	
	private MemberDao memberDao = new MemberDao();
	
	public void join(JoinRequest joinReq) {
		Connection con = null;
		try {
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			
			Member m = memberDao.selectById(con, joinReq.getId());
			
			if(m != null) {
				JdbcUtil.rollback(con);
				throw new DuplicateIdException();
				//사용자 정의 예외 클래스를 정의해서, 겹치는 id가 있으면 예외로 넘어감
			}
			
			Member member = new Member();
			member.setId(joinReq.getId());
			member.setName(joinReq.getName());
			member.setPassword(joinReq.getPassword());
			
			memberDao.insert(con, member);
			
			con.commit();
		} catch(SQLException e) {
			JdbcUtil.rollback(con);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(con);
		}
	}
}
