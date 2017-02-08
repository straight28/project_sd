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
	MemberDto mDTO = (MemberDto) session.getAttribute("loginUser");
%>



<h1 class="h1class">유저 게시판</h1>
<div class="col-sm-12" style="text-align:left">
	<div class="container">
		<form name="userboard" method="post" action="DO">
			<input type="hidden" name="command" value="board_write">
			<table class="table table-striped">

				<tbody>
					<tr>
						<th>제목</th>
						<td><input type="text" placeholder="제목을 입력하세요. "
							name="boardtitle" id="boardtitle" class="form-control" /></td>
					</tr>
					<tr>
						<th>내용</th>
						 <td><textarea cols="10" rows="10" 	placeholder="내용을 입력하세요." name="boardcontent" id="summernote" class="form-control"></textarea></td>  
						
							
								
					</tr>
					<tr>
						<td><a class="btn btn-default pull-left"
							href="DO?command=userboard">목록</a></td>
						<td><input type="submit" class="btn btn-default pull-right"
							value="등록" id="btnSave"></td>
					</tr>
				</tbody>
			</table>
			<input type="hidden" name="usernum" value=<%=mDTO.getUsernum()%>>
		</form>
	</div>
</div>














<script> 
$(document).ready(function () {
    $('#summernote').summernote({
    	 height: 200,
         placeholder : "게시판 내용을 입력하세요."
    });
});

</script>







</div>
<%@ include file="../footer.jsp"%>