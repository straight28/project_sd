<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>	
	
	
<h3> 첫 번째 페이지에 오신걸 환영합니다 </h3>

<table>
<tr>
<th style="padding-left:20px">
<button>																							<%-- member_Join은 회원가입.  --%>																
<span class="glyphicon glyphicon-ok-sign" style="color:#000000; font-size:15px" onclick="location='DO?command=member_Join_Form'"> 회원가입 </span>
</button>
</th>

<th style="padding-left:20px">
<button>
<span class="glyphicon glyphicon-ok-sign" style="color:#000000; font-size:15px" onclick="location='DO?command=member_Login_Form'"> 로그인 </span>
</button> 
</th>
</tr>
</table>

 

<%@ include file="../footer.jsp" %>