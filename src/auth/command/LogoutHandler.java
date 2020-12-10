package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;

public class LogoutHandler implements CommandHandler{
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession(false); 
		//getSession이 boolean값을 파라미터로 받을 때, false이면 세션을 얻고자 할 때 세션이 없으면 세션은 만들지 않음
		//파라미터가 없는 getSession메소드는 세션을 얻고자 할 때 세션이 없으면 새 세션을 만들어서 그 세션을 얻음
		if(session != null) {
			session.invalidate(); //session자체를 invalidate시킴으로써 로그인상태를 종료시킴
		}
		res.sendRedirect(req.getContextPath() + "/index.jsp");
		return null;
	}
}
