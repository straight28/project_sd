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

		<form name="Form" role="form" method="post"
			action="DO?command=userinfomodify">
			<h1 class="h1class">내 정보 변경</h1>
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
						<td class="help">${loginUser.userid } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ** 아이디는 변경이 불가합니다.</td>
					</tr>
					<tr>
						<td class="tbold">닉네임</td>
						<td><input type="text" name="nickname" id="nickname" placeholder="유저 닉네임 (10자이내) " required></td>
					</tr>
					<tr>
						<td class="tbold">이메일</td>
						<td><input type="email" name="email" id="email"  placeholder="email " required></td>
					</tr>
				</table>

				<div class="col-md-12">
					<div class="member_login_menu">
						<input type="submit" class="btn btn-default" style="color: #271b65;" value="기본 정보 변경">
					</div>
				</div>
			</div>
						<input type="hidden" name="usernum" value="${loginUser.usernum}">
		</form>


	</div>
</div>



</div>
<%@ include file="../footer.jsp"%>