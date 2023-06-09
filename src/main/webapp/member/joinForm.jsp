<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ include file="../header.jsp"%>
<%@ include file="sub_image_menu.html"%>
<article>
	<form name="joinForm" method="post">
		<input type="hidden" name="command" value="join">
		
		<fieldset><legend>Basic Info</legend>
			<label>User ID</label>
			<input type="text" name="id" size="12"><input type="hidden" name="reid">
			<input type="button" value="중복체크" class="dup" onclick="idCheck()"><br>
			
			<label>Password</label><input type="password" name="pwd"><br>
			<label>Retype Password</label><input type="password" name="pwdCheck"><br>
			<label>Name</label><input type="text" name="name"><br>
			<label>Phone Number</label><input type="text" name="phone"><br>	
		</fieldset>
		
		<fieldset><legend>Optional</legend>
			<label>Zip Code</label><input type="text" name="zip_num" size="10">
			<input type="button" value="주소찾기" class="dup" onclick="post_zip()"><br>
			<label>Address</label><input type="text" name="address1" size="50"><br>
			<label>Details</label><input type="text" name="address2" size="25"><br>
			<label>E-mail</label><input type="text" name="email"><br>
		</fieldset>
		<div id="buttons">
			<input type="button" value="회원가입" class="submit" onclick="go_save()">
			<input type="reset" value="취소" class="cancel">
		</div><!--go_save()함수에서 action설정과 validation과 submit이 함께 진행됨  -->
	</form>
</article>

<%@ include file="../footer.jsp"%>