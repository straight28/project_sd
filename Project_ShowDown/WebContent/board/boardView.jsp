<%@page import="com.showdown.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- 경로 확인 용 -->
<link rel="stylesheet" href="../css/bootstrap.css">
<script src="../js/jquery-3.1.1.js"></script>
<script src="../js/bootstrap.js"></script>
<link href="../css/test.css" rel="stylesheet">




<h1 class="h1class">유저 게시판</h1>



<div class="container" style="text-align: left" >

	<div id="wrap">
		
		<div id="boardinfo">
			<div id="infos">

				<ul>
					<li>작성자:</li>
					<li>${oneboard.usernum}</li>
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


</div>




































</div>
<%@ include file="../footer.jsp"%>