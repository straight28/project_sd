<%@page import="com.showdown.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberDto loginUser = (MemberDto) session.getAttribute("loginUser");
 if(loginUser == null){
%>
<script type="text/javascript">
alert("로그인이 필요합니다. ");
location.href="index.jsp";
</script>	 
 <%
 }
 %>