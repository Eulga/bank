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
			<form action="user_info.do" method="post">
				<p>아이디</p><input type="text" name="userId" value="${user.userId}" maxlength="14" size="40" class="readonly" readonly>
                <p>비밀번호</p><input type="password" name="passwd" value="${user.passwd}" maxlength="10" size="43" class="readonly" readonly>
                <p>이름</p><input type="text" name="userName" value="${user.userName}" class="readonly" readonly>
                <p>주민등록번호</p><input type="text" name="ssn1" value="${ssn1}" maxlength="6" class="readonly" readonly> - <input type="text" name="ssn2" value="${ssn2}" maxlength="7" class="readonly" readonly>
                <p>이메일</p><input type="text" name="email" value="${user.email}" size="30" class="readonly" readonly>
                <p>우편번호</p><input type="text" name="addr1" value="${addr1}" size="5" class="readonly" readonly>
                <p>주소</p><input type="text" name="addr2" value="${addr2}" size="60" class="readonly" readonly>
                <p>상세주소</p><input type="text" name="addr3" value="${addr3}" size="60" class="readonly" readonly>
                <p>휴대전화</p><input type="text" name="phone" value="${user.phnum}" maxlength="11" size="30" class="readonly" readonly><br>
                <div class="submit">
                	<input type="submit" value="수정하기" class="submit"/>
                </div>
			</form>
    	</div>
    </section>	
	<footer id="footer">
       <%@ include file="/incl/footer.jsp" %>
    </footer>
</body>
</html>