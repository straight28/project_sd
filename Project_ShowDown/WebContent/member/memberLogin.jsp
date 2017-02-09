<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<!--
경로 확인 용 
<link rel="stylesheet" href="../css/bootstrap.css">
<script src="../js/jquery-3.1.1.js"></script>
<script src="../js/bootstrap.js"></script>
<link href="../css/test.css" rel="stylesheet"> 
	 -->
 
<%-- 코드 백업용
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
			</form>
			
			<div id="loginImg">			
			<img src='images/matcha.jpg' />
			</div>
		</div>
		
	</div>

</div>
 --%>
 
 

<div class="container-fluid" >
<div class="row">
		<div class="loginDiv">

			<h1 class="h1class">회원 로그인</h1>
			<form name="loginForm" role="form" method="post" class="form-inline" id="frm">
				<div class="col-md-12">

					<label for="userid" class="sr-only"></label> <input type="text"
						class="form-control" placeholder="아이디" id="userid"> <label
						for="userpass" class="sr-only"></label> <input type="password"
						class="form-control" placeholder="비밀번호" id="userpass">

				</div>
				<div class="col-md-12">
					<div class="member_login_menu">
						<input type="button" id="btnLogin" class="btn btn-default"
							style="color: #271b65;" value="로그인"> 
							<input type="button" class="btn btn-default" style="color: #271b65;" value="회원가입"
							onclick="location='DO?command=member_Join_Form'"> 
							<input type="button" class="btn btn-default" style="color: #271b65;"
							value="아이디 비밀번호 찾기" onclick="location='DO?command=find_id_form'">
					</div>
					<h4><span style="color:red; font-size:1.2em">${message}</span></h4>
					<div id="loginResult"></div>
				</div>
			</form>
			
			<div id="loginImg">			
			<img src='images/matcha.jpg' />
			</div>
		</div>
		
	</div>

</div>
 <script src="http://code.jquery.com/jquery-1.12.4.js"> </script>
    <script>
        $(document).ready(function(){
            $("#btnLogin").click(function(){
                var param = $("#frm").serialize();
                	
                    $.ajax({
                    type: "post",
                    url: "/DO?command=member_Login",
                    data: param,
                    success: function (data) {
                        //콜백함수
                        //data : 서버의 응답텍스트 (출력결과)
                        //id가 loginResult인 태그의 내부 html영역에 복사
                        $("#loginResult").html(data);
                    }
                })
            })
        })

    </script>
 




</div>
<%@ include file="../footer.jsp"%>


