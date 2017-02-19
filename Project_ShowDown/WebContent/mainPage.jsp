<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>	
	
	<!-- 이 부트스트랩 부분은 추가적인 보충 공부가 필요함 -->
	<!-- 이 부트스트랩 부분은 추가적인 보충 공부가 필요함 -->
	<!-- 이 부트스트랩 부분은 추가적인 보충 공부가 필요함 -->
	
<div class="row">
    <h2> Doodle show  </h2>
    <hr>
    <div id="carousel-example-generic" class="carousel slide" style="margin:50px;"> <!-- 마진을 줘서 이미지를 조금 작게 만듬 -->
        <!-- 이미지 수만큼 인디케이터를 만들어 줘야함 -->
        <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        </ol>
        <!-- 이미지 들 -->
        <div class="carousel-inner">
        
            <div class="item active">
           	 <a href="DO?command=userboard">
                <img class="img-circle" src="images/black-and-white-code-programming-tech-79290.jpeg" alt="First slide" title="First slide" >
                </a>
            </div>
            <div class="item">
            	<a href="DO?command=userboard">
                <img class="img-circle" src="images/pexels-photo-89860.jpeg" alt="Second slide" title="Second slide">
            	</a>
            </div>
            <div class="item">
               	<a href="DO?command=userboard">
               	<img class="img-circle" src="images/pexels-photo-316465.jpeg" alt="Third slide" title="Third slide">
            	</a>
            </div>
        </div>
        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
            <span class="icon-prev"></span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
            <span class="icon-next"></span>
        </a>
    </div>
    </div>

<hr>

<div id="doodle">
<H3> Doodle 이란 ?</H3>
<br><br>
<p>
  영어사전에서 Doodle을 찾아보면 <span style="font-weight:bold; color:#FC3521;"> 뭔가를 끄적이다. 낙서하다 </span>라는 의미의 뜻이라고 나옵니다. 이런 단순한 단어가 유명해진 것은 구글 두들 때문입니다. </p>

<p> 특히나 구글 두들의 수석 디자이너가 한국인 이였던 덕분에 다른나라보다는 한국의 명절, 공휴일 등에 대해 보다 자세하고 톡톡튀는 디자인을 보여줬습니다.</p> 
<p> 한국에 맞게 로고를 바꾼 것이죠. 이처럼 각 나라의 특별한 날에 기발한 디자인으로 그날을 기념함으로써 구글 두들은 전세계로 이미지를 확장하게 됩니다.</p>  
<br>
<p> <img src="images/googledoodle1.jpg" alt="goodledoodle1" title="goodledoodle1"> <img src="images/googledoodle2.jpg" alt="goodledoodle2" title="goodledoodle2"> </p> 
<br>

<br><br>
<H3> Doodle 그 이상 </H3>
<br><br>
<p> doodle은 쉽게 말하면 공책이 끄적거린 낙서를 말합니다. 수업시간이나 업무 중 공상에 빠지거나, 예술가들이 창작을 위해 사용하기도 합니다. </p>
<p> 또 낙서를 통해 공상을 하고, 뇌에 휴식과 활력을 공급하기 때문에 기억력 향상에 도움이 된다는 연구도 있습니다.<span style="font-size:10px">  -What does doodling do? Andrade,J.(2010)</span></p>
<p> 어찌보면 doodle은 누군가와 소통의 창구가 될 수도 있습니다. 나 자신이나 타인 혹은 사람이 아니더라도 어떤 재능과의 소통이 될 수도 있겠죠. </p>
<p> 가끔식 머리속 답답한 것를 풀어내거나  창의적인 무언가를 표출하고 싶다면 </p>
<br>
<br>
<H3> Doodle 해 보세요 !! </H3>
<br>
<hr>
<br>
<br>
</div>


<!-- jQuery (부트스트랩의 자바 플러그인 필수적) 
없어도 됨 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<script>
    $('.carousel').carousel();
</script>
-->



</div> 
<%@ include file="../footer.jsp" %>