<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/common.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/savingAccount.css'/>"/>
</head>
<body>
	<header id="header">
		<%@ include file="/incl/header.jsp" %>
	</header>
	<section id="saving_container">
		<div id="saving_wrap">
			<h2>Saving Account</h2>
			<div>
				<form action="add_savingsaccount.do" method="post">
					<p>이름</p> 
					<input type="text" name="userName" value="${user.userName }" readonly class="user_input"><br>
					<p>입금액</p> 
					<input type="text" name="balance" class="user_input"><br>
					<p>이자율</p> 
					0.03<input type="radio" value="0.03" name="interestRate" class="inter_button">
					0.05<input type="radio" value="0.05" name="interestRate" class="inter_button">
					0.08<input type="radio" value="0.08" name="interestRate" class="inter_button"><br>
					<button type="submit">확인</button>
				</form>
			</div>
		</div>
	</section>
    <footer id="footer">
       <%@ include file="/incl/footer.jsp" %>
    </footer>
</body>
</html>