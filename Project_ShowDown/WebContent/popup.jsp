<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/bootstrap.js"></script>
<script src="js/jquery-3.1.1.js"></script>
<script>
	$(document).ready(function() {
		$("#nopopup").click(function() {
			var now = new Date();
			var val = "";
			now.setDate(now.getDate());

			if (this.checked == true) {
				val = "N"; ///7일간 팝업 안함
			} else {
				val = "Y"; ///7일간 팝업 함
			}
			setCookie("showCookies", val, 7);

		});
	});
	function setCookie(cname, cvalue, days) {
		var d = new Date();
		//만료날짜 설정
		d.setTime(d.getTime() + (days * 24 * 60 * 60 * 1000));
		var expires = "expires=" + d.toGMTString();
		document.cookie = cname + "=" + cvalue + ";" + expires;

		window.close();

	};
</script>
<meta charset="UTF-8">
<title>팝업 창</title>
</head>
<body>
	<div style="margin:auto;">
		<h2 style="text-align: center">Cyber Security</h2>
		<img src='images/cybersecurity.jpg' alt="cyber_security"
			title="cyber_security"> <br>
		<br>
		<p style="width: 90%; margin: auto;">
			우리 사이트는 <span style="color: red; font-weight: bold;">개인 정보 보호</span>를
			위해 주민등록번호와 같은 민감정보는 받지 않습니다.
		</p>
		<br>
		<br>
		<br>
		<div class="pull-right">
			<input type="checkbox" id="nopopup">7일간 보이지 않기
		</div>
	</div>
</body>
</html>