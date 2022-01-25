package golchos.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import golchos.domain.User;
import golchos.service.UserService;

@WebServlet("/user/user_info.do")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String[] ssn = user.getSsn().split("-");
		String [] addr = user.getAddr().split("/");
		
		String ssn1 = ssn[0];
		String ssn2 = ssn[1];
		
		String addr1 = addr[0];
		String addr2 = addr[1];
		String addr3 = addr[2];
		
		request.setAttribute("ssn1", ssn1);
		request.setAttribute("ssn2", ssn2);
		
		request.setAttribute("addr1", addr1);
		request.setAttribute("addr2", addr2);
		request.setAttribute("addr3", addr3);
		
		request.getRequestDispatcher("user_info.jsp").forward(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		request.setAttribute("ssn1", request.getParameter("ssn1"));
		request.setAttribute("ssn2", request.getParameter("ssn2"));
		
		request.setAttribute("addr1", request.getParameter("addr1"));
		request.setAttribute("addr2", request.getParameter("addr2"));
		request.setAttribute("addr3", request.getParameter("addr3"));
		
		request.getRequestDispatcher("edit_user_info.jsp").forward(request, response);
	}

}
