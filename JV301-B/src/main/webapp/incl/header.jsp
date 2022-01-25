<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div id="top_menu">
        <h1>
            <a href="/banking/index.jsp">golchos<span class="bank_bolded">bank</span></a>
        </h1>
        <c:choose>
        	<c:when test="${empty user }">
	        	<div class="login_button_wrap">
	        		<a href="/banking/login/login.jsp" class="login_button">Login</a>
	        	</div>
        	</c:when>
        	<c:otherwise>
        		<div class="welcome_wrap">
        			<a href="/banking/user/user_info.do" class="login_welcome">${user.userName }</a> <span>&nbsp;님 환영합니다.</span>
        			<a href="/banking/login/logout.do" class="logout_button">logout</a>
        		</div>
        	</c:otherwise>
        </c:choose>
    </div>
</body>
</html>