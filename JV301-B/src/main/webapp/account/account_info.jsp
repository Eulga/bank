<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/common.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/account_info.css'/>"/>
</head>
<body>
	<header id="header">
       <%@ include file="/incl/header.jsp" %>
    </header>
  	<section id="info_container">
      <div id="info_wrap">
      	<c:forEach var="account" items="${acclist }">
	      	<c:choose>
	      		<c:when test="${account eq 'S' }">
				    <div class="info saving">
				       <h3>
				          Saving Account
				       </h3>
				       <a href="dnw.do?accnum=${account.accNumber }">입금/출금</a>
				       <p class="acc_num">계좌번호 : ${account.accNumber }</p>
				       <p class="balance">잔액 : <fmt:formatNumber value="${account.balance }" type="currency" currencySymbol="￦" />원 </p>
				    </div>
	      		</c:when>
	      		<c:otherwise>
					<div class="info">
					   <h3>
					      Checking Account
					   </h3>
					   <a href="dnw.do?accnum=${account.accNumber }">입금/출금</a>
					   <p class="acc_num">계좌번호 : ${account.accNumber }</p>
					   <p class="overdraft">남은한도 : <fmt:formatNumber value="${account.overamount }" type="currency" currencySymbol="￦" />원</p>
					   <p class="balance">잔액 : <fmt:formatNumber value="${account.balance }" type="currency" currencySymbol="￦" />원 </p>
					</div>
	      		</c:otherwise>
	      	</c:choose>
      	</c:forEach>
      </div>
   	</section>
    <footer id="footer">
       <%@ include file="/incl/footer.jsp" %>
    </footer>
</body>
</html>