function loginCheck(){
	if( document.loginFrm.id.value==""){
		alert("아이디는 필수입력사항입니다");
		document.frm.userid.focus();
		return false;
	}
	if( document.loginFrm.pwd.value==""){
		alert("비밀번호는 필수입력사항입니다");
		document.frm.pwd.focus();
		return false;
	}
	return true;
}


function go_next(){
	// 자바스크립트에서 jsp 페이지 내의 radio 버튼을 바라볼때 
	// 같은 name의 okon태그가 여러개라면 name값에 의한 배열로 인식되어 사용
	
	// 동의함 버튼 : okon[0], 동의안함 버튼:okon[1]
	if( document.contractFrm.okon[1].checked==true){
		//동의안함이 선택되었다면,
		alert("회원약관에 동의하셔야 회원가입이 가능합니다.");
	}else{
		document.contractFrm.action='shop.do?command=joinForm';
		document.contractFrm.submit();
		// contractFrm 폼에 action도 없고 submit버튼도 없음. 
		// 스크립트에서 (스크립트 명령만으로) action값을 설정하고 submit메서드로의 이동까지 실행
		
		// 이 방법을 쓰면 좋은점은 버튼을 여러 개 생성하여 action을 선택하고 진행하는 여러 경로를 생성할 수 있음
		// 즉, 하나의 폼에서 각각의 버튼에 따라 진행해야하는 경로가 다를때 사용하면 좋은 방법
	}
}
				
function idCheck(){
	if( document.joinForm.id.value==""){
		alert("아이디를 입력하고 중복체크를 진행하세요");
		document.joinForm.id.focus();
		return;
	}
	var url = "shop.do?command=idCheckForm&id=" + document.joinForm.id.value;
	var opt = "toolbar=no, menubar=no, scrollbars=no, resizable=no, width=600, height=250";
	window.open(url,"IdCheck", opt);
}

function idok(userid){
	opener.joinForm.id.value=userid;
	opener.joinForm.reid.value=userid;
	self.close();	
}


function post_zip(){
	var url = "shop.do?command=findZipNum";
	var opt = "toolbar=no, menubar=no, scrollbars=no, resizable=no, width=550,";
	opt= opt+" height=300, top=300,left=300";
	window.open(url,"우편번호 찾기", opt);
}


function result(zip_num,sido,gugun,dong){
	//함수 호출 형태 - result( '123-123','서울시','서대문구','대현동')
	opener.document.joinForm.zip_num.value=zip_num;
	opener.document.joinForm.address1.value=sido+" "+gugun+" "+dong;
	self.close();		
}

function go_save(){
	if( document.joinForm.id.value==""){
		alert("아이디를 입력하세요");
		document.joinForm.id.focus();
	}else if(document.joinForm.reid.value != document.joinForm.id.value){
		alert("아이디 중복확인을 하지 않았습니다");
		document.joinForm.reid.focus();
	}else if(document.joinForm.pwd.value==""){
		alert("비밀번호를 입력하세요");
		document.joinForm.pwd.focus();
	}else if(document.joinForm.pwd.value != document.joinForm.pwdCheck.value){
		alert("비밀번호와 비밀번호 확인이 일치하지 않습니다");
		document.joinForm.pwd.focus();
	}else if(document.joinForm.name.value==""){
		alert("이름을 입력하세요");
		document.joinForm.name.focus();
	}else if(document.joinForm.phone.value==""){
		alert("전화번호를 입력하세요");
		document.joinForm.phone.focus();		
	}else{
		document.joinForm.action="shop.do";
		document.joinForm.submit();
	}
}

function go_update(){
	if( document.joinForm.pwd.value==""){
		alert("비밀번호는 필수입력사항입니다");
		document.joinForm.pwd.focus();
	}else if(document.joinForm.pwd.value != document.joinForm.pwdCheck.value){
		alert("비밀번호와 비밀번호 확인이 일치하지 않습니다");
		document.joinForm.pwd.focus();
	}else if(document.joinForm.name.value==""){
		alert("이름을 입력하세요");
		document.joinForm.name.focus();
	}else if(document.joinForm.phone.value==""){
		alert("전화번호를 입력하세요");
		document.joinForm.phone.focus();		
	}else{
		document.joinForm.action="shop.do";
		document.joinForm.submit();
	}
	
}


function withdrawalConfirm(){
	
	var a = confirm("정말로 탈퇴하시겠습니까?");
	if(a){
		location.href='shop.do?command=deleteMember';
	}
}
