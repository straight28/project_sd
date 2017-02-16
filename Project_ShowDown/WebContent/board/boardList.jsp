<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<jsp:useBean id="now" class="java.util.Date"/> <!-- 오늘날짜 확인을 위한 usebean -->
<fmt:formatDate value="${now }" pattern="yyyy-MM-dd" var="today"/> <!-- 오늘날짜 확인을 위한 jstl -->


<h1 class="h1class">자유 게시판</h1>
<div class="container">
	<table class="table table-hover">
		<colgroup>
			<col class="col1">
			<col class="col2">
			<col class="col3">
			<col class="col4">
			<col class="col5">
		</colgroup>
		<thead>
			<tr >
				<th style="text-align: center">번호</th>
				<th>제목</th>
				<th style="text-align: center">작성자</th>
				<th style="text-align: center">날짜</th>
				<th style="text-align: center">조회</th>
			</tr>
		</thead>

		<tbody class="boardList" >
			<!--
			<!-- 중요 !!!! 페이지 넘버가 역순 순서대로 feat.기대형
			<!-- 이 코드 덕분에 게시글 번호가 역순순서대로 시작  시작-->
			<c:set var="firstcount" value="${page.totalBoard - (page.curPage-1)*10}" />

			<c:if test="${!empty loginUser}">
				<c:forEach items="${boardList}" var="List">
					<!-- 게시판들 정보를 가져와서 반복문 돌림 -->

					<tr style="border-bottom: 1px solid #DDDDDD;">
						<td>${firstcount} 
						<c:set var="firstcount" value="${firstcount-1}" />
						</td>
						
						<!--
						<!--
						<!--중요 !!!! 페이지 넘버가 역순 순서대로 feat.기대형 끝-->
						<td style="text-align: left" >
							<!-- 답변들여쓰기 --> 
							<c:forEach var="i" begin="1" end="${List.re_level }">
								<c:if test="${List.re_level != 0}">
									<img src='images/reply_icon1.gif' />
								</c:if>
							</c:forEach> 
							<a href="DO?command=board_view&num=${List.boardnum}">
							${List.boardtitle}
							</a>

							<!-- 댓글이 있으면 갯수 보여주고 없으면 공백처리 --> 
							<c:if test="${List.totalcomment > 0}">
								<span style="color: #204040"> [${List.totalcomment}] </span>
							</c:if>
							<!-- 오늘 글 올렸다면 new 표시 뜸 --> 
							<c:if test="${List.shortdate == today}">
							<img src='images/new.jpg' />
							</c:if>
						</td>

						<td>${List.nickname}</td>
						<td><fmt:formatDate value="${List.boarddate}"
								pattern="yyyy-MM-dd" /></td>
						<td>${List.hit}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty boardList}">
				<tr style="border-bottom: 1px solid #DDDDDD;">
					<td colspan="5">등록된 게시물이 없습니다.</td>
				</tr>
			</c:if>


		</tbody>
	</table>


	<form name="searchform" method="post" action="DO?command=searchkeyword">
		<select name="search_option" class="form-control pull-left" style="width: 90px">
			<c:if test="${search_option != 'nickname'}">
				<option value="boardtitle" selected>제목</option>
				<option value="nickname" >작성자</option>
			</c:if>
			<c:if test="${search_option == 'nickname'}">
				<option value="boardtitle" >제목</option>
				<!-- 제목에 셀렉트 고정 -->
				<option value="nickname" selected>작성자</option>
			</c:if>

		</select> <input type="text" name="keyword" value="${keyword }"
			class="form-control pull-left" style="width: 120px"> 
			<input type="submit" value="검색" class="btn btn-default pull-left" id="btnSearch"> 
			<a class="btn btn-default pull-right" href="DO?command=board_Write_Form">
			글쓰기</a>
	</form>


	<hr>
	<br>


	<div class="text-center">
		<ul class="pagination">

			<!-- 처음 -->
			<c:if test="${page.curBlock > 1}">
				<li><a href="javascript:list('1')">처음</a></li>
			</c:if>
			<!-- 이전 -->
			<c:if test="${page.curBlock > 1}">
				<li><a href="javascript:list('${page.prevPage }')">이전</a></li>
			</c:if>
			<!-- 페이징 -->
			<c:forEach var="num" begin="${page.blockStart }" end="${page.blockEnd }">
				<c:choose>
					<c:when test="${num == page.curPage }">
						<li><a href="javascript:list('${num }')"><span
								style="color: red">${num }</span></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:list('${num }')">${num }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<!-- 다음 -->
			<c:if test="${page.curBlock <= page.totBlock}">
				<li><a href="javascript:list('${page.nextPage }')">다음</a></li>
			</c:if>
			<!-- 끝 -->
			<c:if test="${page.curPage < page.totPage}">
				<li><a href="javascript:list('${page.totPage }')">끝</a></li>
			</c:if>
		</ul>

	</div>
</div>



</div>
</div>
<%@ include file="../footer.jsp"%>