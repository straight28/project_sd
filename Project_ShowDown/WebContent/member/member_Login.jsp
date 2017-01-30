<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- 경로 확인 용 -->
<link rel="stylesheet" href="../css/bootstrap.css">
<link href="../css/test.css" rel="stylesheet"> 
<script src="../js/jquery-3.1.1.js"></script>
<script src="../js/bootstrap.js"></script>
	
	<style>
        .col-sm-9 {
            background-color: #ff0005;
        }
        .col-sm-3 {
            background-color: #ff9106
        }
        .col-sm-12 {
             background-color: #fff207
         }
        .col-md-12 {
            background-color: #00fff5
        }
    </style>

<div class="container" style="text-align: center">
<div class="row">


<h1>회원 로그인</h1>
<form name="loginForm" role="form" method="post" class="form-inline" action="DO?command=member_Login">
	<div class="col-md-12">
	
		
		
			<label for="userid" class="sr-only"></label> 
			<input type="text" class="form-control" placeholder="아이디" name="userid">
		
			<label for="userpass" class="sr-only"></label> 
			<input type="password" class="form-control" placeholder="비밀번호"
				name="userpass">
		

	
	</div>
	<div class="member_login_menu">
		<input type="submit" class="btn btn-default" style="color: #271b65;"value="로그인"> 
			<input type="submit" class="btn btn-default" style="color: #271b65;" value="회원가입"
			onclick="location='DO?command=member_Join_Form'"> 
			<input type="submit" class="btn btn-default" style="color: #271b65;"
			value="아이디 비밀번호 찾기" onclick="location='DO?command=find_id_form'">
	</div>
</form>


</div>
</div>

<%@ include file="../footer.jsp"%>


