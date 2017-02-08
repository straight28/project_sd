<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% request.setCharacterEncoding("UTF-8");%>    

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Doodle Tak</title>

<%-- 부트스트랩을 위한 주소와 링크 시작 --%>
		<link rel="stylesheet" href="css/bootstrap.css"> 
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<!-- javascript -->
		<script src="js/jquery-3.1.1.js"></script>  
	 	<!-- <script src="js/bootstrap.js"></script> -->
		<script src="js/test.js"></script>  
<!-- css -->
 	<link href="css/test.css" rel="stylesheet">   
		
		<!--  섬머노트 -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>
		
 
		
</head>
<body>
<!-- <body oncontextmenu="return false" ondragstart="return false" onselectstart="return false"> -->
<!-- 마우스 오른쪽 버튼 비활성화 
<body oncontextmenu="return false">
-->
<!-- 드래그 금지 
<body ondragstart="return false">
-->
<!-- 블럭 금지 
<body onselectstart="return false">
-->

<div class="container" style="text-align: center">
    <div class="row">
        <div class="headerDiv">
            <div class="col-sm-3">

                <!-- 로고 들어가는 곳 시작 -->
                <div id="logowrap">
                    <a href="DO?command=index">
                        <img src="images/logo.png" class="logo" alt="showdown 로고이미지" title="showdown 로고 이미지">
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
                            <a href="#">Doodle Tak이란</a>
                        </li>
                        <li>
                            <a href="DO?command=userboard">유저 게시판</a>
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
