<%@page import="com.showdown.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<%
MemberDto mDTO = (MemberDto)session.getAttribute("loginUser");

%>
<!-- 경로 확인 용 -->
<link rel="stylesheet" href="../css/bootstrap.css">
<link href="../css/test.css" rel="stylesheet"> 
<script src="../js/jquery-3.1.1.js"></script>
<script src="../js/bootstrap.js"></script>
<h1 class="h1class">유저 게시판</h1>
<div class="container">
    <table class="table table-striped">

        <div class="col-sm-12">
            <form method="post" action="DO">
			<input type="hidden" name="command" value="board_write">
                <tbody>
                <tr>
                    <th>제목</th>
                    <td><input type="text" placeholder="제목을 입력하세요. " name="boardtitle" class="form-control"/></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td><textarea cols="10" rows="10" placeholder="내용을 입력하세요. " name="boardcontent"
                                  class="form-control"></textarea></td>
                </tr>
                <tr>
                    <td><a class="btn btn-default pull-left" href="DO?command=userboard">목록</a></td>
                    <td><input type="submit" class="btn btn-default pull-right" value="등록" onclick="#"> </td>
                </tr>
                </tbody>
                <input type="hidden" name="usernum" value=<%=mDTO.getUsernum()%>>
            </form>
        </div>
    </table>
</div>






















</div>
<%@ include file="../footer.jsp"%>