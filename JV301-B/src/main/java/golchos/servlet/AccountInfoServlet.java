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

import golchos.domain.Account;
import golchos.domain.User;
import golchos.service.AccountService;

@WebServlet("/account/account_info.do")
public class AccountInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private AccountService accService;
	
	public void init() {
		accService = new AccountService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		if(user == null) {
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
			return;
		}
		
		List<String> errorMsgs = new ArrayList<>();
		request.setAttribute("errorMsgs", errorMsgs);
		
		List<Account> acclist = null;
		try {
			acclist = accService.getAllAccount(user);
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.add(e.getMessage());
			request.getRequestDispatcher("/error/error.jsp").forward(request, response);
			return;
		}
		
		request.setAttribute("acclist", acclist);
		request.getRequestDispatcher("/account/account_info.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
