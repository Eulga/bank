<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/common.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/add_user.css'/>"/>

</head>
<body>
    <header id="header">
       <%@ include file="/incl/header.jsp" %>
    </header>
        <section id="join_container">
        <div>
            <h2>회원가입</h2><br>
            <form action="add_user.do" method="post">
                <p>아이디</p><input type="text" name="userId" placeholder="영문과 숫자만 (6~14자)" maxlength="14" size="40">
                <p>비밀번호</p><input type="password" name="passwd" placeholder="영문, 숫자, 특수기호 조합 (8~14자)"maxlength="10" class="passwd" size="43">
                <p>이름</p><input type="text" name="userName" >
                <p>주민등록번호</p><input type="text" name="ssn1" maxlength="6"> - <input type="text" name="ssn2" maxlength="7">
                <p>이메일</p><input type="text" name="email" class="email" size="30">
                <p>우편번호</p><input type="text" name="addr1" size="20">
                <p>주소</p><input type="text" name="addr2" size="60">
                <p>상세주소</p><input type="text" name="addr3" size="60">
                <p>휴대전화</p><input type="text" name="phone" maxlength="11" size="30"><br>
                <button type="submit" class="submit">회원가입</button>
            </form>
        </div>
     </section>
    <footer id="footer">
       <%@ include file="/incl/footer.jsp" %>
    </footer>
</body>
</html>