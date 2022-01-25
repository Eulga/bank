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

@WebServlet("/account/add_savingsaccount.do")
public class AddSavingsAccountServlet extends HttpServlet {
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
		
		request.getRequestDispatcher("/account/savingsAccount.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		double balance = Double.parseDouble(request.getParameter("balance"));
		double interestRate = Double.parseDouble(request.getParameter("interestRate"));
		
		List<String> errorMsgs = new ArrayList<>();
		request.setAttribute("errorMsgs", errorMsgs);
		
		if(DataValidation.isEmpty(balance)) {
			errorMsgs.add(DataValidation.requiredMsg("balance"));
		}
		if(DataValidation.isEmpty(interestRate)) {
			errorMsgs.add(DataValidation.requiredMsg("interestRate"));
		}
		
		if(!errorMsgs.isEmpty()) {//비어있다면=에러가 없음(!true) false//비어있지않다면=무언가 에러가 있음(!false) true 
			request.getRequestDispatcher("/error/error.jsp").forward(request, response);
			return;
		}
		
		SavingsAccount acc = null;
		try {
			acc = (SavingsAccount)accService.addSavingsAccount(balance, interestRate, user);
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
