<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>


<div class="row">
	<div class="bodyDiv">
		<hr>
		<br>
		<div class="col-md-12">


			<form name="joinForm" role="form" method="post" class="form"
				action="DO">
				<INPUT TYPE="hidden" name="command" value="member_Join">


				<div class="form-group">
					<label for="userid">이름</label> <input type="text" name="userid"
						class="form-control" placeholder="이름"> <span
						class="help-block">&nbsp;* 영문자와 숫자만 가능</span>
				</div>

				<div class="form-group">
					<label for="userpass">비밀번호</label> <input type="password"
						name="userpass" class="form-control" placeholder="비밀번호 (10자이내) "
						required>
				</div>

				<div class="form-group">
					<label for="reuserpass">비밀번호 확인</label> <input type="password"
						name="reuserpass" class="form-control" placeholder="비밀번호  확인 "
						required>
				</div>

				<div class="form-group">
					<label for="nickname">유저 닉네임</label> <input type="text"
						name="nickname" class="form-control" placeholder="유저 닉네임 (10자이내) "
						required>

				</div>

				<div class="form-group">
					<label for="email">이메일</label> <input type="email" name="email"
						class="form-control" placeholder="email  * 필수 입력" required>
				</div>


				<div class="member_login_menu">
					<input type="submit" class="btn btn-default"
						style="color: #271b65;" value="회원가입"> <input type="reset"
						class="btn btn-default" style="color: #271b65;" value="취소">
					<input type="button" class="btn btn-default "
						style="color: #271b65;" value="로그인"
						onclick="location='DO?command=member_Login_Form'">

				</div>


			</form>

		</div>
	</div>
</div>

<!-- container-fluid 영역 -->




<%@ include file="../footer.jsp"%>