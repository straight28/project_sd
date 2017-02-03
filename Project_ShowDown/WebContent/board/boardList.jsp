<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- 경로 확인 용
<link rel="stylesheet" href="../css/test.css"> 
<link rel="stylesheet" href="../css/bootstrap.css">
<script src="../js/jquery-3.1.1.js"></script>
<script src="../js/bootstrap.js"></script>
 -->
<h1 class="h1class">유저 게시판</h1>
<div class="container" >
<table class="table table-hover">
<colgroup>
            <col class="col1"><col class="col2"><col class="col3"><col class="col4"><col class="col5">
        </colgroup>
    <thead>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>날짜</th>
        <th>조회</th>
    </tr>
    </thead>

    <tbody>
    
	
	<c:if test="${!empty loginUser}">
	<c:forEach items="${boardList}" var="List"> <!-- 게시판들 정보를 가져와서 반복문 돌림 -->
    <tr style="border-bottom: 1px solid #DDDDDD;">
		
        <td>${List.boardnum}</td>
        <td style="text-align:left"><a href="DO?command=board_view&num=${List.boardnum}">${List.boardtitle}</a></td>
        <td>${List.nickname}</td>
        <td>${List.boarddate}</td>
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

    <a class="btn btn-default pull-left" href="#">검색</a> <!-- 검색기능 넣을 곳 -->
    <a class="btn btn-default pull-right" href="DO?command=board_Write_Form">글쓰기</a>

    <hr>


    <div class="text-center">
    <ul class="pagination">
        <li><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">5</a></li>
    </ul>

</div>
</div>



</div>
<%@ include file="../footer.jsp"%>