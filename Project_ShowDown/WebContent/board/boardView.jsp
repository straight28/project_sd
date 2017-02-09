<%@page import="com.showdown.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../sesstionCheck.jsp"%>
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
<div class="container" style="text-align: left">
	<div id="wrap">
		<div id="boardinfo">
			<div id="infos">
				<ul>
					<li>글번호:</li>
					<li>${oneboard.boardnum}</li>
				</ul>
				<ul>
					<li>작성자:</li>
					<li>${oneboard.nickname}</li>
				</ul>
				<ul>
					<li>등록일:</li>
					<li><fmt:formatDate value="${oneboard.boarddate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></li>
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
	
	<br> <Br>

	<c:set var="user" value="${loginUser.usernum }" />
	<!-- 로그인한 사람 정보 세션 -->
	<c:set var="board" value="${oneboard.usernum }" />
	<!-- 게시판 글 정보 세션 -->


	<a class="btn btn-default pull-left" href="DO?command=userboard">목록</a>
	<c:choose>
		<c:when test="${user == board}">
			<!-- 로그인한 사람정보와 작성글 정보가 일치하면 아래메뉴 보임-->
			<a class="btn btn-default pull-right"
				href="DO?command=board_delete&num=${oneboard.boardnum }">글 삭제</a>
			<a class="btn btn-default pull-right"
				href="DO?command=board_modify_form&num=${oneboard.boardnum }">글
				수정</a>
		</c:when>
		<c:otherwise>
			<a class="btn btn-default pull-right" href="DO?command=userboard">뒤로</a>
		</c:otherwise>
	</c:choose>
	<a class="btn btn-default pull-right"
		href="DO?command=writeboardreply&num=${oneboard.boardnum }">답글</a>
</div>

<hr>

<div class="col-sm-12">
	<div class="container" style="text-align: left">
		<c:if test="${!empty commentList}">
			<c:forEach items="${commentList}" var="commentList">
				<!-- 게시판들 정보를 가져와서 반복문 돌림 -->
				<div class="replytr">
					<b>${commentList.nickname}</b> 
					<span>&nbsp;( <fmt:formatDate value="${commentList.regdate}" pattern="yyyy-MM-dd HH:mm:ss" />)
					</span>

					<c:choose>
						<c:when test="${commentList.usernum == user}">
							<!-- 로그인한 사람정보와 댓글 정보가 일치하면 삭제 보임-->
							<span class="pull-right"><a
								href="DO?command=deletereplyinboard&commentnum=${commentList.commentnum}&num=${oneboard.boardnum}">삭제</a></span>
						</c:when>
						<c:otherwise>
							<!-- 일치하지 않으면 아무것도 나타나지 않음 -->
						</c:otherwise>
					</c:choose>
				</div>

				<div class="replywindow">
					<div>${commentList.content}</div>
				</div>
				
			</c:forEach>
		</c:if>

		<form name="userboard" method="post" action="DO?command=repleWrite">
			<table class="table table-striped">
				<tr>
					<th style="text-align: left">댓글
				</tr>
				
				<br>

				<tr>
					<td><textarea cols="10" rows="2" name="boardcomment" class="form-control"></textarea></td>
				</tr>
				<tr>
					<td><input type="submit" class="btn btn-default pull-right"
						value="리플 등록"></td>
				</tr>
			</table>
			<input type="hidden" name="num" value="${oneboard.boardnum}">
			<input type="hidden" name="usernum" value="${loginUser.usernum} ">
		</form>
	</div>

</div>







</div>
<%@ include file="../footer.jsp"%>