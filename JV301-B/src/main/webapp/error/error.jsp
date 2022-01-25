<%@page import="java.io.PrintWriter"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href='<c:url value="/css/common.css"/>'/>
<link rel="stylesheet" type="text/css" href='<c:url value="/css/error.css"/>'/>
</head>
<body>
<div id="header">
	<%@ include file="/incl/header.jsp" %>
</div>
	<section id="error_container">		
		<div id="error_wrap">
			<div class="error_emoji"></div>
			<h2>오류가 발생했습니다</h2>
			<c:if test="${not empty errorMsgs}">
    			<c:forEach var="errorMsgs" items="${errorMsgs}">
            		<li>${errorMsgs}</li>
         		</c:forEach>
    		</c:if>
    		<div>
				<button class="back_button" onclick="history.back()">이전페이지로 이동</button>
			</div>
		</div>		
	</section>
<div id="footer">
	<%@ include file="/incl/footer.jsp" %>
</div>
</body>
</html>