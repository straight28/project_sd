<%@page import="com.showdown.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="../sesstionCheck.jsp"%>

<%
	MemberDto mDTO = (MemberDto) session.getAttribute("loginUser");
%>


<script> 
$(document).ready(function () {
    $('#summernote').summernote({
    	 height: 400,
         placeholder : "사진이 대용량인 경우 업로드 되지 않을 수 있습니다."
    });
});
</script>


<h1 class="h1class">질문 게시판</h1>
<div class="col-sm-12" style="text-align:left">
	<div class="container">
		<form name="questboard" method="post" action="DO">
			<input type="hidden" name="command" value="questionBoard_write"> <!-- 보드 등록 -->
			<table class="table table-striped">

				<tbody>
					<tr>
						<th>제목</th>
						<td><input type="text" placeholder="제목을 입력하세요. "
							name="questboardtitle" id="questboardtitle" class="form-control" /></td>
					</tr>
					<tr>
						<th>내용</th>
						 <td><textarea cols="10" rows="10"  name="questboardcontent" id="summernote" class="form-control"></textarea></td>  
						
							
								
					</tr>
					<tr>
						<td><a class="btn btn-default pull-left"
							href="DO?command=questionboard">목록</a></td>
						<td><input type="submit" class="btn btn-default pull-right"
							value="등록" id="btnSave"></td>
					</tr>
				</tbody>
			</table>
			<input type="hidden" name="usernum" value=<%=mDTO.getUsernum()%>>
		</form>
	</div>
</div>







</div>
<%@ include file="../footer.jsp"%>