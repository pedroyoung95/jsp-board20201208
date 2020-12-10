package member.service;

import java.sql.Connection;

import auth.service.User;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDao;
import member.model.Member;

public class RemoveMemberService {
	
	private MemberDao memberDao = new MemberDao();
	
	public void removeMember(User user, String password) {
		Connection con = null;
		
		try {
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			
			//1. selectById로 DB에서 멤버 얻기
			Member member = memberDao.selectById(con, user.getId());
//			1.1. member가 없으면 MemberNotFoundException발생
			if(member == null) {
				throw new MemberNotFoundException();
			}
			//	1.2. password가 member의 password와 다르면 InvalidPasswordException발생
			if(!member.matchPassword(password)) {
				throw new InvalidPasswordException();
			}
			//멤버가 있음이 확인되면 회원 탈퇴가 이뤄짐
			memberDao.delete(con, member);
			con.commit();
		} catch (Exception e) {
			JdbcUtil.rollback(con);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(con);
		}
	}
}
