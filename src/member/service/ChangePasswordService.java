package member.service;

import java.sql.Connection;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDao;
import member.model.Member;

public class ChangePasswordService {
	
	private MemberDao memberDao = new MemberDao();
	
	public void changePassword(String userId, String curPwd, String newPwd) {
		Connection con = null;
		
		try {
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			
			//해당 멤버가 있는지 확인하는 if문
			Member member = memberDao.selectById(con, userId);
			if(member == null) {
				throw new MemberNotFoundException();
			}
			//해당 암호가 맞는지 확인하는 if문
			if(!member.matchPassword(curPwd)) {
				throw new InvalidPasswordException();
			}
			//멤버가 있음이 확인되면 암호변경 과정이 이뤄짐
			member.changePassword(newPwd);
			memberDao.update(con, member);
			con.commit();
		} catch (Exception e) {
			JdbcUtil.rollback(con);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(con);			
		}
	}
}
