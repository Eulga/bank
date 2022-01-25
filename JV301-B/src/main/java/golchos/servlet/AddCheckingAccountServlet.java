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

import golchos.domain.*;
import golchos.service.AccountService;
import golchos.util.DataValidation;

@WebServlet("/account/add_checkingaccount.do")
public class AddCheckingAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AccountService accService;
	
	public void init() {
		accService = new AccountService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		if(user == null) {
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/account/checkingAccount.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		if(user == null) {
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
			return;
		}
		
		double balance = Double.parseDouble(request.getParameter("balance"));
		double overdraft = Double.parseDouble(request.getParameter("overdraft"));
		
		List<String> errorMsgs = new ArrayList<>();
		request.setAttribute("errorMsgs", errorMsgs);
		
		if(DataValidation.isEmpty(balance)) {
			errorMsgs.add(DataValidation.requiredMsg("balance"));
		}
		if(DataValidation.isEmpty(overdraft)) {
			errorMsgs.add(DataValidation.requiredMsg("overdraft"));
		}
		
		if(!errorMsgs.isEmpty()) {//비어있다면=에러가 없음(!true) false//비어있지않다면=무언가 에러가 있음(!false) true 
			request.getRequestDispatcher("/error/error.jsp").forward(request, response);
			return;
		}
		
		CheckingAccount acc = null;
		try {
			acc = (CheckingAccount)accService.addCheckingAccounts(balance, overdraft, user);
		}catch(Exception e) {
			e.printStackTrace();
			errorMsgs.add(e.getMessage());
			request.getRequestDispatcher("/error/error.jsp").forward(request, response);
			return;
		}
		
		//4. 클라이언트 뷰 선택하기
		request.setAttribute("account", acc);
		request.getRequestDispatcher("/success/add_acc_success.jsp").forward(request, response);		
	}

}
