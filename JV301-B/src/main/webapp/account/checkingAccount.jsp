<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/common.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/checkingAccount.css'/>"/>
</head>
<body>
	<header id="header">
		<%@ include file="/incl/header.jsp" %>
	</header>
	<section id="checking_container">
		<div id="checking_wrap">
			<h2>Checking Account</h2>
			<div>
				<form action= "add_checkingaccount.do" method="post">
					<p>이름</p> 
					<input type="text" name="userName" value="${user.userName }" readonly class="user_input"><br>
					<p>입금액</p> 
					<input type="text" name="balance" class="user_input"><br>
					<p>초과 인출</p> 
					5,000<input type="radio" value="5000" name="overdraft" class="inter_button">
					20,000<input type="radio" value="20000" name="overdraft" class="inter_button">
					50,000<input type="radio" value="50000" name="overdraft" class="inter_button"><br>
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