<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>


<form name="joinForm" role="form" method="post" class="form-inline"
	action="DO">
	<INPUT TYPE="hidden" name="command" value="member_Join">
	<%-- hidden을 통해 command 값을  설정--%>
	<%-- member_Join은 회원가입.  --%>

	<table>
		<tr>
			<th><label for="userid" class=sr-only></label></th>
			<td><input type="text" name="userid" class="form-control"
				placeholder="아이디" required> <!-- required는 필수입력을 뜻함, 입력되지 않으면 전송되지 않음 -->
				<span class="btn btn-default glyphicon glyphicon-ok-sign"
				style="color: #271b65; font-size: 14px" onclick="#"> 중복검사 </span> 
				<span class="help-block">&nbsp;* 영문자와 숫자만 가능</span></td>
		</tr>

		<tr>
			<th><label for="userpass" class=sr-only></label></th>
			<td><input type="password" name="userpass" class="form-control"
				placeholder="비밀번호 (10자이내) " required> * 필수 입력</td>
		</tr>

		<tr>
			<th><label for="reuserpass" class=sr-only></label></th>
			<td><input type="password" name="reuserpass"
				class="form-control" placeholder="비밀번호  확인" required> * 필수	입력</td>
		</tr>

		<tr>
			<th><label for="nickname" class=sr-only></label></th>
			<td><input type="text" name="nickname" class="form-control"
				placeholder="유저 닉네임 (10자이내)" required> 
				<span class="btn btn-default glyphicon glyphicon-ok-sign"
				style="color: #271b65; font-size: 14px" onclick="#"> 중복검사 </span> <%-- db에서 닉네임 찾아서 일치여부 검사, 유저id와 동일가능 --%>
			</td>
		</tr>

		<tr>
			<th><label for="email" class=sr-only></label></th>
			<td><input type="email" name="email" class="form-control"
				placeholder="email" required> * 필수 입력</td>
		</tr>
	</table>
	
	<div class="inputwindow">
		<input type="submit" class="btn btn-default" style="color: #271b65;"
			value="회원가입" onclick="#"> 
			<input type="reset"	class="btn btn-default" style="color: #271b65;" value="취소"> 
			<input type="button" class="btn btn-default" style="color: #271b65;"
			value="로그인" onclick="location='DO?command=member_Login_Form'">

	</div>
</form>

<%@ include file="../footer.jsp"%>