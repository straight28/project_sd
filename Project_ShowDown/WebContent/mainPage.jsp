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
                <img src="images/pexels-photo-89860.jpeg" alt="First slide"alt="Second slide" title="Second slide">
                </a>
            </div>
            <div class="item">
            	<a href="DO?command=userboard">
                <img src="images/pexels-photo-316465.jpeg" alt="Second slide" title="Second slide">
            	</a>
            </div>
            <div class="item">
               	<a href="DO?command=userboard">
                <img src="images/black-and-white-code-programming-tech-79290.jpeg" alt="Third slide" title="Third slide">
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
<br>
<br>
<!-- jQuery (부트스트랩의 자바 플러그인 필수적) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script>
    $('.carousel').carousel()
</script>




</div> 
<%@ include file="../footer.jsp" %>