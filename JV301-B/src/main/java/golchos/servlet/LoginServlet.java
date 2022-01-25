package golchos.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import golchos.dao.UserDao;
import golchos.domain.User;
import golchos.util.DataValidation;


@WebServlet("/login/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HttpSession session;
	Cookie cookie;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//1. 정보 받아오기
		String userId = request.getParameter("userId");
		String userPasswd = request.getParameter("passwd");
		
		//유효성 확인
		if(userId == null || (userId.length()) == 0) {
			cookie = new Cookie("loginerror", "loginerror");
			cookie.setMaxAge(4);
			response.addCookie(cookie);
			response.sendRedirect("login.jsp");
			return;
		}
		if(userPasswd == null || (userPasswd.length()) == 0) {
			cookie = new Cookie("loginerror", "loginerror");
			cookie.setMaxAge(4);
			response.addCookie(cookie);
			response.sendRedirect("login.jsp");
			return;
		}
		
		//2. id로 DB에서 정보 받아와서 비밀번호 확인
		UserDao userDao = new UserDao();
		User user = userDao.findUserByUserId(userId);
		String validPasswd = null;
		if(user == null) {
			cookie = new Cookie("loginerror", "loginerror");
			cookie.setMaxAge(4);
			response.addCookie(cookie);
			response.sendRedirect("login.jsp");
			return;
		}else {
			validPasswd = user.getPasswd(); 
		}
		
		if(!userPasswd.equals(validPasswd)) {
			//다시 이전 로그인화면으로 이동
			cookie = new Cookie("loginerror", "loginerror");
			cookie.setMaxAge(2);
			response.addCookie(cookie);
			response.sendRedirect("login.jsp");
			return;
		}
		
		//로그인id 저장여부 검사
		String saveId = request.getParameter("saveId");
		if(!DataValidation.isEmpty(saveId) && saveId.equals("on")) {
			Cookie cookie = new Cookie("saveId", userId);
			cookie.setMaxAge(60*30);
			response.addCookie(cookie);
		}
		
		//3. 새션생성
		session = request.getSession();
		
		//4. 시스템 정보 저장
		session.setAttribute("user", user);
		response.sendRedirect("/banking/index.jsp");
	}

}
