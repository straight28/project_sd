<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>



<div class="row">
	<div class="bodyDiv">
				<h1 class="h1class"> 내 정보 변경 </h1>
		<div class="col-md-12">

			<form name="joinForm" role="form" method="post" class="form-horizontal" action="DO?command=userinfomodify">
                    


                    <div class="form-group">
                        <label for="userid" class="col-xs-2 col-lg-2 control-label">아이디</label>
                        <div class="col-xs-10 col-lg-10">
                        <input type="text" name="userid" id="userid" class="form-control" value="${loginUser.userid }" disabled>
 							<input type="hidden" name="usernum" value="${loginUser.usernum}">								
                            <span class="help-block">&nbsp;* 아이디는 변경이 불가합니다.</span>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="nickname" class="col-xs-2 col-lg-2 control-label">유저 닉네임</label>
                        <div class="col-xs-10 col-lg-10">
                        <input type="text" name="nickname" id="nickname" class="form-control"
                               placeholder="유저 닉네임 (10자이내) " required>
                        </div>

                    </div>

                    <div class="form-group">
                        <label for="email" class="col-xs-2 col-lg-2 control-label">이메일</label>
                        <div class="col-xs-10 col-lg-10">
                        <input type="email" name="email" id="email"  class="form-control"
                               placeholder="email " required>
                        </div>
                    </div>


				<div class="member_login_menu">
					<input type="submit" class="btn btn-default"
						style="color: #271b65;" value="기본 정보 변경"> 
				</div>
			</form>
			
			
		</div>
	</div>
</div>



</div>
<%@ include file="../footer.jsp"%>