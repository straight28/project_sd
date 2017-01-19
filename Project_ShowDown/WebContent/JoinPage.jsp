<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>	
	
	

<form name ="joinForm" method="post" action="DO">
<INPUT TYPE="hidden" name="command" value="member_Join"> 
	<%-- hidden을 통해 command 값을  설정--%>
	<%-- member_Join은 회원가입.  --%>	
<table>


<tr>
<th >유저아이디</th>
<td><input type="text" name="userid"> 
<button>
<span class="glyphicon glyphicon-ok-sign" style="color:#000000; font-size:15px" onclick="#"> 중복검사 </span>
</button>
</td>
</tr>

<tr>
<th>비밀번호</th>
<td><input type="password" name="userpass"> * 필수 입력</td>
</tr>

<tr>
<th>비밀번호 확인</th>
<td><input type="password" name="reuserpass"> * 필수 입력</td>
</tr>

<tr>
<th>유저 닉네임</th>
<td><input type="text" name="nickname">
<button>
<span class="glyphicon glyphicon-ok-sign" style="color:#000000; font-size:15px" onclick="#"> 중복검사 </span>
</button> <%-- db에서 닉네임 찾아서 일치여부 검사, 유저id와 동일가능 --%>


</td>
</tr>

<tr>
<th>이메일</th>
<td><input type="email" name="email"> * 필수 입력</td>
</tr>

</table>
<div id="buttons">
<input type="submit" value="회원가입" onclick="#"> <%-- 유효성검사 javaScript --%>
<input type="reset" value="취소">
<input type="button" value="로그인" onclick="location='DO?command=member_Login_Form'">

</div>
 </form>

<%@ include file="../footer.jsp" %>