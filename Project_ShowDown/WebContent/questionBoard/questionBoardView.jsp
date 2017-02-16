<%@page import="com.showdown.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../sesstionCheck.jsp"%>


<h1 class="h1class">질문 게시판</h1>
<div class="container" style="text-align: left">


		<div id="boardinfo">
			<div id="infos">
				<ul>
					<li>글번호:</li>
					<li>${questboard.questboardnum}</li>
				</ul>
				<ul>
					<li>작성자:</li>
					<li>${questboard.nickname}</li>
				</ul>
				<ul>
					<li>등록일:</li>
					<li><fmt:formatDate value="${questboard.questdate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></li>
				</ul>
				<ul>
					<li>조회수:</li>
					<li>${questboard.hit }</li>
				</ul>
			</div>
		</div>

		<div id="boardhead">
			<div>${questboard.questboardtitle}</div>
		</div>
		<div id="boardcontent">
			<div>${questboard.questboardcontent}</div>
		</div>
	</div>
	
	<br> <Br>
	

	<c:set var="user" value="${loginUser.usernum }" />
	<!-- 로그인한 사람 정보 세션 -->
	<c:set var="board" value="${questboard.usernum }" />
	<!-- 게시판 글 정보 세션 -->

<a class="btn btn-default pull-left" href="DO?command=questionboard">목록</a>
	<c:choose>
		<c:when test="${user == board}">
			<!-- 로그인한 사람정보와 작성글 정보가 일치하면 아래메뉴 보임-->
			<a class="btn btn-default pull-right"
				href="DO?command=questBoard_delete&num=${questboard.questboardnum }">글 삭제</a>
			<a class="btn btn-default pull-right"
				href="DO?command=questBoard_modify_form&num=${questboard.questboardnum }">글 수정</a>
		</c:when>
		<c:otherwise>
			<a class="btn btn-default pull-right" href="DO?command=questionboard">뒤로</a>
		</c:otherwise>
	</c:choose>
	<%-- 답글은 사용하지 않음 
	<a class="btn btn-default pull-right"
		href="DO?command=writeboardreply&num=${qeustboard.questboardnum }">답글</a> --%>


</div> <!-- 헤더쪽에 wrap --> 

<hr>

<div class="col-sm-12">
	<div class="container" style="text-align: left">
		<c:if test="${!empty commentList}">
			<c:forEach items="${commentList}" var="commentList">
				<!-- 게시판내용 정보를 가져와서 반복문 돌림 -->
				
				<div class="replytr">
				           <!-- 답글 들여쓰기!! -->
							<c:forEach var="i" begin="1" end="${commentList.re_level }">
								<c:if test="${commentList.re_level != 0}">
									<img src='images/reply_icon2.jpg' />
								</c:if>
							</c:forEach> 
				
					<b>${commentList.nickname} </b> 
					
					<span>&nbsp;( <fmt:formatDate value="${commentList.regdate}" pattern="yyyy-MM-dd HH:mm:ss" />)
					</span>
					
						<c:if test="${commentList.usernum == user}">
							<!-- 로그인한 사람정보와 댓글 정보가 일치하면 삭제 보임-->
							<span class="pull-right">
								<a href="javascript:0;" onclick="BtnReply(this,${commentList.questcommentnum},${commentList.ref },${commentList.re_step },${commentList.re_level } )">댓글</a>
								&nbsp;&nbsp;
								<a href="DO?command=deletequestboardreply&commentnum=${commentList.questcommentnum}&num=${questboard.questboardnum}">삭제</a>
							</span>
							<br>
						</c:if>
					
				</div>

				<div class="replywindow">
					<div>${commentList.content} </div>
				</div>
				<div></div>
			</c:forEach>
		</c:if>


		<form name="questionboard" method="post" action="DO?command=questBoardRepleWrite">
			<table class="table table-striped">
				<tr>
					<th style="text-align: left">댓글
				</tr>
				
				<br>

				<tr>
					<td><textarea cols="10" rows="2" name="questboardcomment" class="form-control"></textarea></td>
				</tr>
				<tr>
					<td><input type="submit" class="btn btn-default pull-right"
						value="리플 등록"></td>
				</tr>
			</table>
			<input type="hidden" name="num" value="${questboard.questboardnum}">
			<input type="hidden" name="usernum" value="${loginUser.usernum} ">
		</form>
	</div>

</div>

<script>
function BtnReply(e,num,reff,stepp,levell){
	$('.replyBoardForm').remove();
	var comment = num;
	var ref = reff;
	var step = stepp;
	var level = levell;
	
	$(e).parent().parent().next().next().html('<form name="questionboard" method="post" class="replyBoardForm" action="DO?command=deepQuestBoardRepleWrite">'
			+ '<table class="table table-striped">'
			+ 	'<tr>'
			+ 		'<th style="text-align: left">댓글'
			+	'</tr>'
			+	'<tr>'
			+		'<td><textarea cols="10" rows="2" name="questboardcomment" class="form-control"></textarea></td>'
			+	'</tr>'
			+	'<tr>'
			+		'<td><input type="submit" class="btn btn-default pull-right" value="리플 등록"></td>'
			+	'</tr>'
			+ '</table>'
			+ '<input type="hidden" name="num" value="${questboard.questboardnum}">'
			+ '<input type="hidden" name="comment" id="comment" value="">'
			+ '<input type="hidden" name="ref" id="ref" value="">'
			+ '<input type="hidden" name="step" id="step" value="">'
			+ '<input type="hidden" name="level" id="level" value="">'
			+ '<input type="hidden" name="usernum" value="${loginUser.usernum} ">'
			+ '</form>');
	document.getElementById("comment").value = comment;
	document.getElementById("ref").value = ref;
	document.getElementById("step").value = step;
	document.getElementById("level").value = level;
	console.log(comment);
};

</script>




</div>
<%@ include file="../footer.jsp"%>