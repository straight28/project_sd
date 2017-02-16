<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<script src="js/jquery-3.1.1.js"></script>  
<script>

$(document).ready(function(){
	$("#nopopup").click(function(){
		var now = new Date();
		var val = "";
		now.setDate(now.getDate());
		
		if(this.checked == true){
			val ="N"; ///7일간 팝업 안함
		}else{
			val ="Y"; ///7일간 팝업 함
		}
		setCookie("showCookies", val, 7);
		
	});
});
function setCookie(cname, cvalue, days){
	var d = new Date();
	//만료날짜 설정
	d.setTime(d.getTime()+ (days * 24 * 60 * 60 * 1000));
	var expires="expires="+d.toGMTString();
	document.cookie = cname+"="+cvalue+";"+expires;
	
	window.close();
	
};

</script>
<meta charset="UTF-8">
<title>팝업 창</title>
</head>
<body>
<input type="checkbox" id="nopopup">7일간 보이지 않기
</body>
</html>