<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>


<h1> 회원 로그인 </h1>
<form method="post" action="DO?command=member_Login">

    <fieldset>
        
            <label>아이디</label>
            <input type="text" name="userid"><br>

            <label>비밀번호</label>
            <input type="password" name="userpass"><br>
    </fieldset>
	<br>
    <div >
        <input type="submit" value="로그인">
        <input type="button" value="회원가입" onclick="location='DO?command=member_Join_Form'">
        <input type="button" value="아이디 비밀번호 찾기" onclick="location='DO?command=find_id_form'">
    </div>
</form>


<%@ include file="../footer.jsp" %>


