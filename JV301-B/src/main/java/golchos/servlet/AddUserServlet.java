package golchos.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import golchos.domain.User;
import golchos.service.UserService;
import golchos.util.DataValidation;


@WebServlet("/user/add_user.do")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService;
	
	public void init() {
		userService = new UserService();
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String userId = request.getParameter("userId");
		String passwd = request.getParameter("passwd");
		String userName = request.getParameter("userName");
		
		String ssn1 = request.getParameter("ssn1")+"-";
		String ssn2 = request.getParameter("ssn2");
		String ssn = DataValidation.attached(ssn1 , ssn2);
		
		String email = request.getParameter("email");
		
		String addr1 = request.getParameter("addr1")+"/";
		String addr2 = request.getParameter("addr2")+"/";
		String addr3 = request.getParameter("addr3");
		String addr = DataValidation.attached(addr1, addr2, addr3);
		
		String phnum = request.getParameter("phone");
		
		List<String> errorMsgs = new ArrayList<>();
		request.setAttribute("errorMsgs", errorMsgs);
		
		if(DataValidation.isEmpty(userId)) {
			errorMsgs.add(DataValidation.requiredMsg("userId"));
		}
		if(DataValidation.isEmpty(passwd)) {
			errorMsgs.add(DataValidation.requiredMsg("passwd"));
		}
		if(DataValidation.isEmpty(userName)) {
			errorMsgs.add(DataValidation.requiredMsg("userName"));
		}
		if(DataValidation.isEmpty(ssn)) {
			errorMsgs.add(DataValidation.requiredMsg("ssn"));
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
		
		if(!errorMsgs.isEmpty()) {//����ִٸ�=������ ����(!true) false//��������ʴٸ�=���� ������ ����(!false) true 
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;						//add_user.jsp�� ������ �װ����� �����޽����� ���� ���� ����ڿ��� ����
		}
		
		//3. ����Ͻ� ���۷��̼� ȣ���ϱ�
		try { 
			userService.addUser(userId, passwd, userName, ssn, email, addr, phnum);	
		}catch(Exception e) {
			e.printStackTrace();
			errorMsgs.add(e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return;
		}

		//4. Ŭ���̾�Ʈ �� �����ϱ�
		request.getRequestDispatcher("user/add_user_success.jsp").forward(request, response);
		return;
	}
}
