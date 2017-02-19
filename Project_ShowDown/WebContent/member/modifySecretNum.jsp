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
		<form name="ModifyForm" role="form" method="post" action="DO?command=updatepass">
		<h1 class="h1class">비밀번호 변경</h1>
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
					<td> ${loginUser.userid }</td>
				</tr>
				<tr>
					<td class="tbold">기존 비밀번호</td>
					<td> <input type="password" name="exuserpass"></td>
				</tr>
				<tr>
					<td class="tbold">변경 비밀번호</td>
					<td><input type="password" name="userpass"> &nbsp;&nbsp;* 기존 비밀번호로 변경 할 수 없습니다.</td>
				</tr>
				<tr>
					<td class="tbold">변경 비밀번호 확인</td>
					<td><input type="password" name="reuserpass"></td>
				</tr>
			</table>
		</div>
		<div class="col-md-12">

			<div class="member_login_menu">
					<input type="submit"class="btn btn-default" style="color: #271b65;" value="비밀번호 변경"> 
					<br><br>
					<h4><span style="color:red; font-size:1.2em">${message}</span></h4>
			</div>
		</div>
		
		
		<input type="hidden" name="userid" value="${loginUser.userid}">
		<input type="hidden" name="usernum" value="${loginUser.usernum}">
	</form>
	</div>
</div>

<!-- container-fluid 영역 -->


























</div>
<%@ include file="../footer.jsp"%>