<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% request.setCharacterEncoding("UTF-8");%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Doodle Dak</title>

<%-- 부트스트랩을 위한 주소와 링크 시작 --%>
		<link rel="stylesheet" href="css/bootstrap.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<!-- javascript -->
		<script src="js/jquery-3.1.1.js"></script>
		<script src="js/bootstrap.js"></script>
<!-- css -->
		<link href="css/test.css" rel="stylesheet">  
		
</head>
<body>



<div class="container-fluid" style="text-align: center">
    <div class="row">
        <div class="headerDiv">
            <div class="col-sm-3">

                <!-- 로고 들어가는 곳 시작 -->
                <div id="logowrap">
                    <a href="DO?command=index">
                        <img src="image/logo.png" class="logo" alt="showdown 로고 이미지" title="showdown 로고 이미지">
                    </a>
                </div>
                <!-- 로고 들어가는 곳 끝 -->
            </div>
            <div class="col-sm-9">
                <div id="header_menu">
                <c:choose>
                <c:when test="${!empty sessionScope.loginUser.userid}">
                    <ul>
                        <li>
                            ${sessionScope.loginUser.nickname}(${sessionScope.loginUser.userid})님 접속중 
                        </li>
                        <li>
                             <a href="DO?command=mymenu_form">내 메뉴</a>
                        </li>
                        <li>
                            <a href="DO?command=logout">로그아웃</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </li>
                    </ul>
				</c:when>
				<c:otherwise>
					<ul>
                        <li>
                            <a href="#">도움말</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </li>
                    </ul>
				</c:otherwise>					
				</c:choose>
                </div>
                <div class="clear"></div> <!-- headerDiv 에서  header_menu부분이 float으로 인해 무시되는 부분이 있어서 추가함 -->
            </div>

				<p id="firstdoor">두들두들탁<span style="color: black"> 에 오신것을 환영합니다!</span></p>

            
            <div class="col-sm-12">
                <div id="header_Nav">
                    <ul>
                        <li>
                            <a href="#">Doodle tak 이란</a>
                        </li>
                        <li>
                            <a href="#">유저 게시판</a>
                        </li>
                        <li>
                            <a href="#">질문 게시판</a>
                        </li>
                        <li>
                            <a href="#">다른 서비스</a>
                        </li>
                    </ul>

                </div>
            </div>
        </div>
    </div>
<hr style="border-color: #be6dff">
		<br>
