<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<% String q = (String)request.getAttribute("q"); %>

<script>
$(document).ready(function(){
	//팝업 띄우기
	//window.open (url,id,option);
	///팝업창 열기 옵션 값을 가져옴
	var show = getCookie("showCookies");
	if(show != "N"){
		window.open("popup.jsp","","width=400,height=500");
	};
	
	
	///저장된 쿠키 조회
	var cookie_userid= getCookie("userid");
	if(cookie_userid != ""){
		//저장된 쿠키가 있으면 userid 태그에 쿠키값을 입력하고 체크박스를 체크상태로 설정
		$("#userid").val(cookie_userid);
		$("#idSave").attr("checked",true);
	};
	
	
	//로그인 버튼 클릭
	$("#btnLogin").click(function(){
		if($("#idSave").is(":checked")){ //체크상태
			saveCookie($("#userid").val());
		}else{ //체크상태가 아님
			saveCookie("");
		}
	});
	//아이디 저장 클릭
	$("#idSave").click(function(){
		//태그 .is(":속성") 속성값
		//태그 .attr("속성") html 요소
		//태그 .prop javascript 요소
		if($("#idSave").is(":checked")){
			if(confirm("아이디를 저장하시겠습니까?")){
				$("#idSave").prop("checked",true);
			}else{
				$("#idSave").prop("checked", false);
			}
		}
	});
});

function saveCookie(id){
	if(id != ""){
		//setCookie
		setCookie("userid", id, 7); //7일
	}else{
		setCookie("userid", id, -1); //-1은 삭제
	}
};
///setCookie (쿠기변수명, 쿠키값, 유효날짜)
function setCookie(name, value, days){
	var today = new Date(); //날짜 객체
	//쿠키의 유효기간 설정
	//today.getDate() 오늘 날짜
	today.setDate(today.getDate()+ days);
	
	//쿠키 변수명 = 쿠키값; path=저장경로; expires=만료일자 / 아래 형식을 따라야함
	///userid= 값; path=/; expires=날짜;
	///toGMTString() 표준시간
	document.cookie = name+"="+value+";path=/Project_ShowDown;expires="+today.toGMTString()+";";
	alert(document.cookie);
};
///저장된 쿠키를 검사하는 함수
function getCookie(cname){
	var cookie= document.cookie+";";
	
	var idx = cookie.indexOf(cname,0);
	var val ="";
	if(idx != -1){
		///substring (start, length )
		/// 쿠키 userid = kkk; 에서 아래 코드는 kkk를 뽑아내는 함수
		cookie = cookie.substring(idx, cookie.length);
		begin = cookie.indexOf("=",0)+1;
		end = cookie.indexOf(";",begin);
		val = cookie.substring(begin,end)
	};
	return val;
};


</script>
 

<div class="container-fluid" >
<div class="row">
		<div class="loginDiv">

			<h1 class="h1class">회원 로그인</h1>
			<form name="loginForm" role="form" method="post" class="form-inline" action="DO?command=member_Login">
				
				
				<div class="col-md-12">

					<label for="userid" class="sr-only"></label> 
					<input type="text"	class="form-control" placeholder="아이디" id="userid" name="userid"> 
					<label	for="userpass" class="sr-only"></label> 
					<input type="password"	class="form-control" placeholder="비밀번호" name="userpass">
				</div>
				<div class="col-md-12">
				<input type="checkbox" id="idSave">아이디 저장	
				</div>
				<div class="col-md-12">
				
					<div class="member_login_menu">
						<input type="submit" class="btn btn-default" style="color: #271b65;" id="btnLogin" value="로그인"> 
						<input type="button" class="btn btn-default" style="color: #271b65;" value="회원가입" 	onclick="location='DO?command=member_Join_Form'"> 
						<input type="button" class="btn btn-default" style="color: #271b65;" value="아이디 비밀번호 찾기" onclick="location='DO?command=find_id_form'">
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


