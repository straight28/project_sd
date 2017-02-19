<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<style>
.table .myinfo_col1 {
	width: 130px;
}

.table .myinfo_col2 {
	width: 380px;
}

.tbold {
	font-weight: bold;
}
</style>

<div class="row">
	<div class="bodyDiv">
		<h1 class="h1class">내 정보 조회</h1>
		<div class="col-md-12">
			<table class="table table-bordered">
				<colgroup>
					<col class="myinfo_col1">
					<col class="myinfo_col2">

				</colgroup>
				<thead>
					<tr class="success">
						<th>정 보</th>
						<th>내 용</th>
					</tr>
				</thead>

				<tr>
					<td class="tbold">아이디</td>
					<td>${userinfo.userid }</td>
				</tr>
				<tr>
					<td class="tbold">닉네임</td>
					<td>${userinfo.nickname }</td>
				</tr>
				<tr>
					<td class="tbold">이메일</td>
					<td>${userinfo.email }</td>
				</tr>
			</table>
		</div>
		<div class="col-md-12">

			<div class="member_login_menu">
					<input type="button" class="btn btn-default" style="color: #271b65;" value="기본 정보수정" onclick="location='DO?command=ModifyMyInfo'">  
				
					<input type="button"class="btn btn-default" style="color: #271b65;" value="비밀번호 변경"
					onclick="location='DO?command=find_id_form'"> 
					
					<input type="button" class="btn btn-default" style="color: #271b65;"
					value="회원탈퇴" onclick="location='DO?command=member_Join_Form'">
			</div>
		</div>

	</div>
</div>

<!-- container-fluid 영역 -->




</div>
<%@ include file="../footer.jsp"%>