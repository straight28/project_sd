<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>닉네임 중복체크</title>
<script type="text/javascript">
function idok(){
  opener.joinForm.nickname.value="${nickname}"; 
  window.close();
}
</script>
</head>
<body>

	<div id="wrap">
	
	<h1>닉네임 중복확인</h1>
	<form method="post" name=form action="DO?command=nick_check_form">
	
	닉네임
	<input type="text" name="nickname" value="">
	<input type="submit" value="찾기"	 class="submit"><br>
	
	<c:if test="${message == 1 }">
	<script type="text/javascript">
	opener.document.joinForm.nickname.value="";
        </script>
	<br><br>
	 ${nickname}는 이미 사용중인 닉네임입니다.
      </c:if>
      <c:if test="${message==-1}"><br><br>
        ${nickname}는 사용 가능한 닉네임입니다.
        <input type="button" value="사용" class="cancel" onclick="idok()">
      </c:if>
	</form>
	</div>
</body>
</html>