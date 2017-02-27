<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복체크</title>
<script type="text/javascript">
function idok(){
  opener.joinForm.userid.value="${userid}"; 
  window.close();
}
</script>
</head>
<body>

	<div id="wrap">
	
	<h1>ID 중복확인</h1>
	<form method="post" name=form action="DO?command=id_check_form">
	
	아이디 
	<input type="text" name="userid" value="">
	<input type="submit" value="찾기"	 class="submit"><br>
	
	<c:if test="${message == 1 }">
	<script type="text/javascript">
	opener.document.joinForm.userid.value="";
        </script>
	<br><br>
	 ${userid}는 이미 사용중인 아이디입니다.
      </c:if>
      <c:if test="${message==-1}">
      <br>
      <br>
        ${userid}는 사용 가능한 ID입니다.
        <input type="button" value="사용" class="cancel" onclick="idok()">
      </c:if>
	</form>
	</div>
</body>
</html>