package golchos.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import golchos.domain.User;
import golchos.service.UserService;
import golchos.util.DataValidation;

/**
 * Servlet implementation class EditUserInfoServlet
 */
@WebServlet("/user/edit_user_info.do")
public class EditUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService;
	
	public void init() {
		userService = new UserService();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//request값 넘겨받기
		String passwd = request.getParameter("passwd");
		String email = request.getParameter("email");
		
		String addr1 = request.getParameter("addr1")+"/";
		String addr2 = request.getParameter("addr2")+"/";
		String addr3 = request.getParameter("addr3");
		String addr = DataValidation.attached(addr1, addr2, addr3);
		
		String phnum = request.getParameter("phone");
		
		//데이터 유효성검사
		List<String> errorMsgs = new ArrayList<>();
		request.setAttribute("errorMsgs", errorMsgs);
		
		if(DataValidation.isEmpty(passwd)) {
			errorMsgs.add(DataValidation.requiredMsg("passwd"));
		}
		if(DataValidation.isEmpty(email)) {
			errorMsgs.add(DataValidation.requiredMsg("email"));
		}
		if(DataValidation.isEmpty(addr)) {
			errorMsgs.add(DataValidation.requiredMsg("address"));
		}
		if(DataValidation.isEmpty(phnum)) {
			errorMsgs.add(DataValidation.requiredMsg("phone_number"));
		}
		
		if(!errorMsgs.isEmpty()) {//비어있다면=에러가 없음(!true) false//비어있지않다면=무언가 에러가 있음(!false) true 
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;						//add_user.jsp로 보내고 그곳에서 에러메시지를 띄우는 것이 사용자에게 편리함
		}
		
		//수정후 페이지에 넘겨줄 값
		request.setAttribute("ssn1", request.getParameter("ssn1"));
		request.setAttribute("ssn2", request.getParameter("ssn2"));
		
		request.setAttribute("addr1", request.getParameter("addr1"));
		request.setAttribute("addr2", request.getParameter("addr2"));
		request.setAttribute("addr3", request.getParameter("addr3"));
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		
		userService.updateUser(user, passwd, email, addr, phnum);
		
		session.setAttribute("user", user);
		request.getRequestDispatcher("user_info.jsp").forward(request, response);
	}

}
