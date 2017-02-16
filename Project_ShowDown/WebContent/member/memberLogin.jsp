<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<% String q = (String)request.getAttribute("q"); %>
<!--
경로 확인 용 
<link rel="stylesheet" href="../css/bootstrap.css">
<script src="../js/jquery-3.1.1.js"></script>
<script src="../js/bootstrap.js"></script>
<link href="../css/test.css" rel="stylesheet"> 
	 -->
 

<div class="container-fluid" >
<div class="row">
		<div class="loginDiv">

			<h1 class="h1class">회원 로그인</h1>
			<form name="loginForm" role="form" method="post" class="form-inline"
				action="DO?command=member_Login">
				<div class="col-md-12">

					<label for="userid" class="sr-only"></label> <input type="text"
						class="form-control" placeholder="아이디" name="userid"> <label
						for="userpass" class="sr-only"></label> <input type="password"
						class="form-control" placeholder="비밀번호" name="userpass">

				</div>
				<div class="col-md-12">
					<div class="member_login_menu">
						<input type="submit" class="btn btn-default"
							style="color: #271b65;" value="로그인"> <input type="button"
							class="btn btn-default" style="color: #271b65;" value="회원가입"
							onclick="location='DO?command=member_Join_Form'"> <input
							type="button" class="btn btn-default" style="color: #271b65;"
							value="아이디 비밀번호 찾기" onclick="location='DO?command=find_id_form'">
					</div>
					<h4><span style="color:red; font-size:1.2em">${message}</span></h4>
				</div>
			<img src='images/matcha.jpg' />
			<input type="hidden" name="q" value=<%=q%>>
			</form>
		</div>
	</div>
</div>

</div>
<%@ include file="../footer.jsp"%>


