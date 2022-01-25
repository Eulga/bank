<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/common.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/login.css'/>"/>
</head>
<body>
	<div id="header">
		 <%@ include file="/incl/header.jsp" %>
	</div>	
	<section id="login_container">
		<div id="login_wrap">
			<form action="login.do" method="post">
				<h2>Login</h2>
				<input type="text" name="userId" placeholder="아이디" value="${cookie.saveId.value }" class="login_info"/><br>
				<input type="password" name="passwd" placeholder="비밀번호" class="login_info"/><br>
				<button type="submit" class="login-button">로그인</button><br>
				<c:choose>
					<c:when test="${empty cookie.saveId}">
						<p>ID저장&nbsp;&nbsp;</p><input type="checkbox" name="saveId" class="save_id">
					</c:when>
					<c:otherwise>
						<p>ID저장&nbsp;&nbsp;</p> <input type="checkbox" name="saveId" checked> 
					</c:otherwise>
				</c:choose>
			</form>
			<p>방문이 처음이신가요?&nbsp;&nbsp;&nbsp;&nbsp;</p><a href="/banking/user/add_user.jsp">회원가입</a><br>
			<c:if test="${not empty cookie.loginerror }">
				<p class="loginError">아이디나 비밀번호를 잘못 입력하셨습니다</p>
			</c:if>
		</div>		
	</section>	
	<div id="footer">
      <%@ include file="/incl/footer.jsp" %>
   </div>
	</body>
</html>