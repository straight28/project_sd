<%@page import="com.showdown.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- 경로 확인 용
<link rel="stylesheet" href="../css/test.css"> 
<link rel="stylesheet" href="../css/bootstrap.css">
<script src="../js/jquery-3.1.1.js"></script>
<script src="../js/bootstrap.js"></script>
 -->
<%
String num = request.getParameter("num");
%>


<h1 class="h1class">유저 게시판</h1>



<div class="container" style="text-align: left" >

	<div id="wrap">
		
		<div id="boardinfo">
			<div id="infos">

				<ul>
					<li>작성자:</li>
					<li>${oneboard.nickname}</li>
				</ul>
				<ul>
					<li>등록일:</li>
					<li>${oneboard.boarddate}</li>
				</ul>
				<ul>
					<li>조회수:</li>
					<li>${oneboard.hit }</li>
				</ul>
			</div>
		</div>
		
		<div id="boardhead">
			<div>${oneboard.boardtitle}</div>
		</div>
		<div id="boardcontent">
			<div>${oneboard.boardcontent}</div>
		</div>
	</div>
<br><Br>
	
	<c:set var="user" value="${loginUser.usernum }"/> <!-- 로그인한 사람 정보 세션 -->
	<c:set var="board" value="${oneboard.usernum }"/> <!-- 게시판 글 정보 세션 -->
	
    <a class="btn btn-default pull-left" href="DO?command=userboard" >목록</a>

	<c:choose>
    <c:when test="${user == board}"> <!-- 로그인한 사람정보와 작성글 정보가 일치하면 아래메뉴 보임-->
    <a class="btn btn-default pull-right" href="DO?command=board_delete&num=${oneboard.boardnum }" >글 삭제</a>
    <a class="btn btn-default pull-right" href="DO?command=board_modify_form&num=${oneboard.boardnum }" >글 수정</a>
	</c:when>
	<c:otherwise>
    <a class="btn btn-default pull-right" href="DO?command=userboard">뒤로</a>
	</c:otherwise>
	</c:choose>
		
</div>




































</div>
<%@ include file="../footer.jsp"%>