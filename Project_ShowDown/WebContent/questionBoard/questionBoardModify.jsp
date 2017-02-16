<%@page import="com.showdown.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<script> 
$(document).ready(function () {
    $('#summernote').summernote({
    	 height: 300,
         placeholder : "사진이 대용량인 경우 업로드 되지 않을 수 있습니다."
    });
});
</script>


<h1 class="h1class">질문 게시판</h1>
        <div class="col-sm-12" style="text-align:left">
<div class="container">
            <form method="post" action="DO">
			<input type="hidden" name="command" value="questboard_update">
			<table class="table table-striped">

				<tbody>
					<tr>
						<th>제목</th>
						<td><input type="text" name="questboardtitle" class="form-control"
							value="${questionboard.questboardtitle }" disabled="disabled" /></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea cols="10" rows="10" name="questboardcontent"
								class="form-control" id="summernote">${questionboard.questboardcontent }</textarea></td>
					</tr>
					<tr>
						<td><a class="btn btn-default pull-left"
							href="DO?command=questionboard">목록</a></td>
						<td><input type="submit" class="btn btn-default pull-right"
							value="수정"></td>
					</tr>
				</tbody>
			</table>
			<input type="hidden" name="usernum" value="${loginUser.usernum }">
                <input type="hidden" name="questboardnum" value="${questionboard.questboardnum}">
            </form>
        </div>
</div>


</div>
<%@ include file="../footer.jsp"%>