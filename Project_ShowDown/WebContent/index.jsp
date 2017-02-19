<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>	
	
<script>
$(document).ready(function(){
	//팝업 띄우기
	//window.open (url,id,option);
	///팝업창 열기 옵션 값을 가져옴
	var show = getCookie("showCookies");
	if(show != "N"){
		window.open("popup.jsp","","width=420,height=550, top=300, left=600, resizable=no");
	};
});

///저장된 쿠키를 검사하는 함수
function getCookie(cname){
	var cookie= document.cookie+";";
	//console.log(cookie);
	var idx = cookie.indexOf(cname,0);
	var val ="";
	if(idx != -1){
		///substring (start, length )
		/// 쿠키 userid = kkk; 에서 아래 코드는 kkk를 뽑아내는 함수
		cookie = cookie.substring(idx, cookie.length);
		begin = cookie.indexOf("=",0)+1;
		end = cookie.indexOf(";",begin);
		val = cookie.substring(begin,end)
	};
	return val;
};


</script>

    <div class="row">
        <div class="bodyDiv">
            <br>
                <div class="indexmenu">
                    <div class="menucontent">
                        <div class="content">
                            <div class="col-sm-12">
                            <h2 class="content-title">서비스를 이용하시려면 회원 가입을 하셔야 합니다.</h2>
                            </div>
                            <div class="col-sm-12">
                            <p class="content-read"> 당신은 누군가와 소중한 것을 만들고, 시작하고, 사용할 수 있습니다.
                                <br>그리고 또, 이곳에서 함께 경험할 수 있습니다.
                            </p>
                            </div>
                            <div class="col-sm-12">
                            <a href="DO?command=member_Join_Form" class="btn btn-primary">회원가입</a>
                            <a href="DO?command=member_Login_Form&q=0" class="btn btn-default">로그인</a>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
    </div>
 <!-- container-fluid 영역 -->

</div>
<%@ include file="../footer.jsp" %>