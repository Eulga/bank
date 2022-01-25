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
		
		//1. ���� �޾ƿ���
		String userId = request.getParameter("userId");
		String userPasswd = request.getParameter("passwd");
		
		//��ȿ�� Ȯ��
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
		
		//2. id�� DB���� ���� �޾ƿͼ� ��й�ȣ Ȯ��
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
			//�ٽ� ���� �α���ȭ������ �̵�
			cookie = new Cookie("loginerror", "loginerror");
			cookie.setMaxAge(2);
			response.addCookie(cookie);
			response.sendRedirect("login.jsp");
			return;
		}
		
		//�α���id ���忩�� �˻�
		String saveId = request.getParameter("saveId");
		if(!DataValidation.isEmpty(saveId) && saveId.equals("on")) {
			Cookie cookie = new Cookie("saveId", userId);
			cookie.setMaxAge(60*30);
			response.addCookie(cookie);
		}
		
		//3. ���ǻ���
		session = request.getSession();
		
		//4. �ý��� ���� ����
		session.setAttribute("user", user);
		response.sendRedirect("/banking/index.jsp");
	}

}
