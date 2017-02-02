<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- 경로 확인 용 -->
<link rel="stylesheet" href="../css/bootstrap.css">
<link href="../css/test.css" rel="stylesheet"> 
<script src="../js/jquery-3.1.1.js"></script>
<script src="../js/bootstrap.js"></script>

<h1 class="h1class">유저 게시판</h1>
<div class="container" >
<table class="table table-hover">
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
    <!-- <tr style="border-bottom: 1px solid #DDDDDD;">
        <td>1</td>
        <td>이건 제목 제목 제목 입니다.</td>
        <td>운영자</td>
        <td>2016.12.02</td>
        <td>234</td>
    </tr>

    <tr style="border-bottom: 1px solid #DDDDDD;">
        <td>2</td>
        <td>부트스트랩 트스트랩 스트랩 트랩</td>
        <td>운영자</td>
        <td>2016.12.02</td>
        <td>234</td>
    </tr>

    <tr style="border-bottom: 1px solid #DDDDDD;">
        <td>3</td>
        <td>난 정말 자바를 공부한 적이 없다구요</td>
        <td>운영자</td>
        <td>2016.12.02</td>
        <td>234</td>
    </tr> -->
	
	<c:if test="${!empty loginUser }">
	<c:forEach items="${boardList}" var="List">
    <tr style="border-bottom: 1px solid #DDDDDD;">
		
        <td>${List.boardnum}</td>
        <td><a href="DO?boardnum=${List.boardnum}"></a>${List.boardtitle}</td>
        <td>${List.usernum}</td>
        <td>${List.boarddate}</td>
        <td>${List.hit}</td>
    </tr>
	</c:forEach>
	</c:if>
	<c:if test="${empty loginUser}">
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