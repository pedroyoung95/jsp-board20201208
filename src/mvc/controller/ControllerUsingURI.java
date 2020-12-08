package mvc.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.command.NullHandler;

/**
 * Servlet implementation class ControllerUsingURI
 */

public class ControllerUsingURI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String prefix = "/WEB-INF/view/";
    private String suffix = ".jsp";
    private Map<String, CommandHandler> map;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerUsingURI() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	//가장 먼저 시작되는 init메소드안에서 properties에 있는 값들을 읽어오는 과정
    	map = new HashMap<>();
    	
    	ServletContext application = getServletContext();
		String filePath = application.getRealPath("/WEB-INF/commandHandlerURI.properties");
		
		try (FileReader fr = new FileReader(filePath);) {
			Properties properties = new Properties();
			properties.load(fr);
			
			Set<Object> keys = properties.keySet();
			for(Object key : keys) {
				Object value = properties.get(key);
				String className = (String) value;
				
				try {
					Class c = Class.forName(className);
					Object o = c.newInstance();
					CommandHandler handler = (CommandHandler) o;
					map.put((String) key, handler);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String root = request.getContextPath();		
		String command = "";
		if(uri.startsWith(root)) {
			command = uri.substring(root.length());
		}
		
		CommandHandler handler = map.get(command);
		
		if(handler == null) {
			handler = new NullHandler();
		}
		
//		int b = 0;
//		while((b = fr.read()) != -1) {
//			System.out.print((char) b);
//		} 파일이 잘 읽히는지 while문으로 확인 함
		
//		if(command.equals("/join.do")) {
//			handler = new JoinHandler();
//		} else if(command.equals("/login.do")) {
//			handler = new LoginHandler();
//		} else if(command.equals("/logout.do")) {
//			handler = new LogoutHandler();
//		} else {
//			handler = new NullHandler();
//		}
		
		String view = null;
		try {
			view = handler.process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(prefix + view + suffix).forward(request, response);
		
	}

}