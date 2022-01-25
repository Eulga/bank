<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GOLCHOS BANK</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/common.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/index.css'/>"/>
</head>
<body>
    <header id="header">
       <%@ include file="/incl/header.jsp" %>
    </header>
        <section id="main_container">
        <div id="main_wrap">
            <div id="text_wrap">
                <p>이미 모두의 은행</p>
                <p>golchos<span>bank</span></p>
                <p>한 사람, 한 사람을 위해 시작한 은행이<br>
                    더 많은 사람들이 찾는 모두의 은행이 되었습니다.</p>
            </div>
            <div id="main_menu">
                 <div class="add_acc">
                    <a href="/banking/account/add_checkingaccount.do">당좌계좌</a>
                    <a href="/banking/account/add_savingsaccount.do">예금계좌</a>
                    <p>계좌개설</p>
                    <p>예금계좌 및 당좌계좌 개설이 가능합니다</p>
                </div>
                <div>
                    <a href="/banking/account/account_info.do">조회하기</a>
                    <p>계좌조회</p>
                    <p>계좌 조회 및 입/출금 서비스를 이용할 수 있습니다</p>
                </div>
                <div>
                    <a href="#">바로가기</a>
                    <p>인기상품</p>
                    <p>다양한 서비스에서 활용중인 인기상품 API를 소개합니다</p>
                </div>
                <div>
                    <a href="#">바로가기</a>
                    <p>자주묻는 질문</p>
                    <p>golchosbank와 함께 하기 전 궁금했던 질문들을 모았습니다</p>
                </div>
            </div>
        </div>
    </section>
    <footer id="footer">
       <%@ include file="/incl/footer.jsp" %>
    </footer>
</body>
</html>