package golchos.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import golchos.domain.Account;
import golchos.exception.NotFoundAccountException;
import golchos.service.AccountService;
import golchos.util.DataValidation;

@WebServlet("/account/dnw.do")
public class DepositAndWithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountService accService;

	@Override
	public void init() {
		accService = new AccountService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accnum = request.getParameter("accnum");
		
		List<String> errorMsgs = new ArrayList<>();
		request.setAttribute("errorMsgs", errorMsgs);
		
		Account acc = null;
		
		try {
			acc = accService.findAccountByAccnumber(accnum);
		} catch (NotFoundAccountException e) {
			e.printStackTrace();
			errorMsgs.add(e.getMessage());
			request.getRequestDispatcher("/error/error.jsp").forward(request, response);
			return;
		}
		
		request.setAttribute("account", acc);
		request.getRequestDispatcher("depositandwithdraw.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String accnum = request.getParameter("accnum");
		String dnwbalance_str = request.getParameter("dnwbalance");
		String dnw = request.getParameter("dnw");
		
		List<String> errorMsgs = new ArrayList<>();
		request.setAttribute("errorMsgs", errorMsgs);
		
		if(DataValidation.isEmpty(accnum)) {
			errorMsgs.add(DataValidation.requiredMsg("���¹�ȣ"));
		}
		if(DataValidation.isEmpty(dnwbalance_str)) {
			errorMsgs.add(DataValidation.requiredMsg("�Ա�/��� �ݾ�"));
		}
		if(DataValidation.isEmpty(dnw)) {
			errorMsgs.add(DataValidation.requiredMsg("����ݼ���"));
		}
		
		if(!errorMsgs.isEmpty()) {//����ִٸ�=������ ����(!true) false//��������ʴٸ�=���� ������ ����(!false) true 
			request.getRequestDispatcher("/error/error.jsp").forward(request, response);
			return;
		}

		double dnwbalance = Double.parseDouble(dnwbalance_str);
		
		Account acc = null;
		
		try {
			acc = accService.findAccountByAccnumber(accnum);
			accService.depositAndwitdraw(acc, dnwbalance, dnw);
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.add(e.getMessage());
			request.getRequestDispatcher("/error/error.jsp").forward(request, response);
			return;
		}
		
		if(dnw.equals("deposit")) {
			dnw = "�Ա�";
		} else {
			dnw = "���";
		}
		
		request.setAttribute("dnw", dnw);
		request.getRequestDispatcher("/success/dnw_success.jsp").forward(request, response);
	}
}
