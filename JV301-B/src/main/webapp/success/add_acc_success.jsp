<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/common.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/add_acc_success.css'/>"/>
</head>
<body>
	<header id="header">
       <%@ include file="/incl/header.jsp" %>
    </header>
    <section id="accsuccess_container">
       <div id="accsuccess_wrap">
           <p>계좌개설을 완료했습니다</p>
           <a href="../index.jsp">홈으로 이동</a>   
       </div>
    </section>
    <footer id="footer">
       <%@ include file="/incl/footer.jsp" %>
    </footer>
</body>
</html>