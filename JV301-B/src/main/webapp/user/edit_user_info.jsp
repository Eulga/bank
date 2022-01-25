<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/common.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/user_info.css'/>"/>
</head>
<body>
	<header id="header">
       <%@ include file="/incl/header.jsp" %>
    </header>
    <section id="user_info_container">
    	<div id="user_info_wrap">
    		<h2>회원정보</h2>
			<form action="edit_user_info.do" method="post">
				<p>아이디</p><input type="text" name="userId" value="${user.userId}" maxlength="14" size="40" class="readonly" readonly>
                <p>비밀번호</p><input type="password" name="passwd" value="${user.passwd}" maxlength="10" class="passwd" size="43">
                <p>이름</p><input type="text" name="userName" value="${user.userName}" class="readonly" readonly>
                <p>주민등록번호</p><input type="text" name="ssn1" value="${ssn1}" maxlength="6" class="readonly" readonly> - <input type="text" name="ssn2" value="${ssn2}" class="readonly" readonly maxlength="7">
                <p>이메일</p><input type="text" name="email" value="${user.email}" class="email" size="30" >
                <p>우편번호</p><input type="text" name="addr1" value="${addr1}" size="5" >
                <p>주소</p><input type="text" name="addr2" value="${addr2}" size="60" >
                <p>상세주소</p><input type="text" name="addr3" value="${addr3}" size="60" >
                <p>휴대전화</p><input type="text" name="phone" value="${user.phnum}" maxlength="11" size="30" ><br>
                <button type="submit" class="submit">저장</button>
			</form>
    	</div>
    </section>	
	<footer id="footer">
       <%@ include file="/incl/footer.jsp" %>
    </footer>
</body>
</html>