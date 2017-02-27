<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<div class="row">
	<div class="bodyDiv">
		
		
				<h1 class="h1class"> 회원 가입 </h1>
		
		<div class="col-md-12">

			<form name="joinForm" role="form" method="post" class="form-horizontal"
                      action="DO?command=member_Join">
                    


                    <div class="form-group">
                        <label for="userid" class="col-xs-2 col-lg-2 control-label">아이디</label>
                        <div class="col-xs-10 col-lg-10">
                        <input type="text" name="userid" id="userid" class="form-control" placeholder="이름" required>
                            <span class="help-block">&nbsp;* 영문자, 숫자 4~10자리</span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="userpass" class="col-xs-2 col-lg-2 control-label">비밀번호</label>
                        <div class="col-xs-10 col-lg-10">
                        <input type="password" name="userpass" id="userpass" class="form-control"
                               placeholder="비밀번호 (영문자, 숫자 6~10자리) " required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="reuserpass" class="col-xs-2 col-lg-2 control-label">비밀번호 확인</label>
                        <div class="col-xs-10 col-lg-10">
                        <input type="password" name="reuserpass" id="reuserpass"
                               class="form-control" placeholder="비밀번호  확인 " required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="nickname" class="col-xs-2 col-lg-2 control-label">유저 닉네임</label>
                        <div class="col-xs-10 col-lg-10">
                        <input type="text" name="nickname" id="nickname" class="form-control"
                               placeholder="유저 닉네임 (한글 4~10자 이내) " required>
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
						style="color: #271b65;" value="회원가입" onclick="check();"> 
				</div>
			</form>
			
			
		</div>
	</div>
</div>

<!-- container-fluid 영역 -->



</div>
<%@ include file="../footer.jsp"%>