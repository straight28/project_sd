<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>	
	
	

<form name ="joinForm" method="post" action="DO">
<INPUT TYPE="hidden" name="command" value="member_Join"> 
	<%-- hidden을 통해 command 값을  설정--%>
	<%-- member_Join은 회원가입.  --%>	
<table>


<tr>
<th ><label for="userid">유저아이디</label></th>
<td><input type="text" name="userid" placeholder="영문자만 가능" required> <!-- required는 필수입력을 뜻함, 입력되지 않으면 전송되지 않음 -->
<button>
<span class="glyphicon glyphicon-ok-sign" style="color:#000000; font-size:15px" onclick="#"> 중복검사 </span>
</button>
</td>
</tr>

<tr>
<th><label for="userpass">비밀번호</label></th>
<td><input type="password" name="userpass" placeholder="10자 이상 가능 " required> * 필수 입력</td>
</tr>

<tr>
<th><label for="reuserpass">비밀번호 확인</label></th>
<td><input type="password" name="reuserpass" required> * 필수 입력</td>
</tr>

<tr>
<th><label for="nickname">유저 닉네임</label></th>
<td><input type="text" name="nickname" placeholder="10자 내에서 입력가능" required>
<button>
<span class="glyphicon glyphicon-ok-sign" style="color:#000000; font-size:15px" onclick="#"> 중복검사 </span>
</button> <%-- db에서 닉네임 찾아서 일치여부 검사, 유저id와 동일가능 --%>


</td>
</tr>

<tr>
<th><label for="email">이메일</label></th>
<td><input type="email" name="email" required> * 필수 입력</td>
</tr>

</table>
<div id="buttons">
<input type="submit" value="회원가입" onclick="#"> <%-- 유효성검사 javaScript --%>
<input type="reset" value="취소">
<input type="button" value="로그인" onclick="location='DO?command=member_Login_Form'">

</div>
 </form>

<%@ include file="../footer.jsp" %>