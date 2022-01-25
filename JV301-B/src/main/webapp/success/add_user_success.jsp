<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/common.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/success.css'/>"/>
</head>
<body>
    <header id="header">
       <%@ include file="/incl/header.jsp" %>
    </header>
    <section id="success_container">
       <div id="success_wrap">
           <div>
               <h2>${user.userName } 님</h2>
               <h3>환영합니다</h3>
               <a href="../index.jsp">홈으로</a>
               <a href="../login/login.jsp">로그인</a>
           </div>
           <p>golchos<span>bank</span></p>
       </div>
    </section>
    <footer id="footer">
       <%@ include file="/incl/footer.jsp" %>
    </footer>
</body>
</html>