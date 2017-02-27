
/* 다른 페이지로 넘어가는 자바스크립트 */
 function list(page){
	 location.href =
	"DO?command=userboard&curPage="+page;
 }
 
 function Questlist(page){
	 location.href =
	"DO?command=questionboard&curPage="+page;
 }

 
  function check(){
     ///  /정규표현식/
     /// [^0-9] 숫자가 아니라는 의미미
     ///^[0-9] 숫자로 시작한다는 의미

     ////아이디 체크
     var userid = document.getElementById("userid");
     if(userid.value==""){
         alert("아이디는 필수 입력입니다.");
         userid.focus();
         return false;
     }
     var exp1 = /^[A-Za-z0-9]{4,10}$/;
     //// 정규표현식.test(입력값)  입력값.match(정규표현식)
     if(!exp1.test(userid.value )){
         alert("아이디는 영문자, 숫자 4~10자리로 입력하세요");
         userid.focus();
         return false;
     }
     ///비밀번호
     var userpass = document.getElementById("userpass");
     if(userpass.value==""){
         alert("비밀번호는 필수 입력입니다.");
         userpass.focus();
         return false;
     }
     var exppass = /^[A-Za-z0-9]{6,10}$/;
     //// 정규표현식.test(입력값)  입력값.match(정규표현식)
     if(!exppass.test(userpass.value )){
         alert("비밀번호는 영문자, 숫자 6~10자리로 입력하세요");
         userpass.focus();
         return false;
     }

     ////비밀번호 확인
     var reuserpass = document.getElementById("reuserpass");
     if(reuserpass.value == ""){
         alert("비밀번호 확인을 하셔야합니다.");
         reuserpass.focus();
         return false;
     }

     if(document.joinForm.userpass.value != document.joinForm.reuserpass.value){
         alert("동일한 비밀번호가 아닙니다");
         reuserpass.focus();
         return false;
     }

     var nickname= document.getElementById("nickname");
     if(nickname.value == ""){
         alert("닉네임을 입력하세요.");
         nickname.focus();
         return false;
     }
     ///\x20 16진수 20=>10진수 32, 스페이스 1개
     var exp2 = /^[가-힣ㄱ-ㅎㅏ-ㅣ]{4,10}$/;
     if(!exp2.test(nickname.value)){
         alert("닉네임은 한글 4~10자 이내로 입력하세요");
         nickname.focus();
         return false;
     }

     var email = document.getElementById("email");
     //{2,}2글자이상
     var exp3=/^[a-z0-9]{2,}@[a-z0-9]{2,}\.[a-z]{2,}$/;
     if(!exp3.test(email.value)){
         alert("이메일 입력값이 없거나 형식이 잘못되었습니다.");
         email.focus();
         return false;
     }
     return true;
 }

 