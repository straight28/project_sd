<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>


<div class="row">
	<div class="bodyDiv">
		<hr style="border-color: #be6dff">
		<br>
		<div class="col-md-12">


			<form name="joinForm" role="form" method="post" class="form-horizontal"
                      action="DO?command=member_Join">
                    


                    <div class="form-group">
                        <label for="userid" class="col-xs-2 col-lg-2 control-label">아이디</label>
                        <div class="col-xs-10 col-lg-10">
                        <input type="text" name="userid" class="form-control" placeholder="이름" required>
                            <span class="help-block">&nbsp;* 영문자와 숫자만 가능</span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="userpass" class="col-xs-2 col-lg-2 control-label">비밀번호</label>
                        <div class="col-xs-10 col-lg-10">
                        <input type="password" name="userpass" class="form-control"
                               placeholder="비밀번호 (10자이내) " required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="reuserpass" class="col-xs-2 col-lg-2 control-label">비밀번호 확인</label>
                        <div class="col-xs-10 col-lg-10">
                        <input type="password" name="reuserpass"
                               class="form-control" placeholder="비밀번호  확인 " required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="nickname" class="col-xs-2 col-lg-2 control-label">유저 닉네임</label>
                        <div class="col-xs-10 col-lg-10">
                        <input type="text" name="nickname" class="form-control"
                               placeholder="유저 닉네임 (10자이내) " required>
                        </div>

                    </div>

                    <div class="form-group">
                        <label for="email" class="col-xs-2 col-lg-2 control-label">이메일</label>
                        <div class="col-xs-10 col-lg-10">
                        <input type="email" name="email" class="form-control"
                               placeholder="email " required>
                        </div>
                    </div>


				<div class="member_login_menu">
					<input type="submit" class="btn btn-default"
						style="color: #271b65;" value="회원가입"> 
						

				</div>


			</form>

		</div>
	</div>
</div>

<!-- container-fluid 영역 -->




<%@ include file="../footer.jsp"%>