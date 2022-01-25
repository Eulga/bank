<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/common.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/depositandwithdraw.css'/>"/>
</head>
<body>
	<header id="header">
       <%@ include file="/incl/header.jsp" %>
    </header>
	    <section id="both_container">
        <div id="both_wrap">
           <p>계좌번호</p>
           <form action="dnw.do" method="post">
           	  <input type="text" name="accnum" class="accnum" value="${account.accNumber }" readonly>
              <p class="form_text">잔액</p><input type="text" class="cost" value="<fmt:formatNumber value="${account.balance }" type="currency" currencySymbol="￦" />원" readonly><br>
              <c:if test="${account eq 'C' }">
              	<p class="form_text">초과한도</p><input type="text"  class="cost" value="<fmt:formatNumber value="${account.overamount }" type="currency" currencySymbol="￦" />원"><br>
              </c:if>
              <input type="radio" name="dnw" checked="checked" value="deposit">입금
              <input type="radio" name="dnw" value="withdraw">출금<br>
              <input type="text" name="dnwbalance" placeholder="금액을 입력하세요" class="cost"><br>
              <input type="submit" value="확인" class="submit">
           </form>
        </div>
     </section>
    <footer id="footer">
       <%@ include file="/incl/footer.jsp" %>
    </footer>
</body>
</html>