$(document).ready(function(){
	//회원가입 페이지
	$(document).on('click', '#signUp', function(e) {
		var html=
			'<div class="wrapper fadeInDown">'+
				'<div id="loginContent">'+
					"<h2 class='inactive underlineHover' id='signIn'> Sign In </h2>"+
					"<h2 class='active' id='signUp'>Sign Up </h2>"+
					"<form id='regiForm'>"+
						"<input type='text' id='login' class='fadeIn second' name='login' placeholder='아이디'>"+
						"<input type='password' id='password' class='fadeIn third' name='login' placeholder='패스워드'>"+
						"<input type='button' class='fadeIn second submitButton' id='regi' value='완료'>"+
					"</form>"+
				"</div>"+
			"</div>"
		$("body").html(html);
	});
	
	//회원가입 제출
	$(document).on('click', '#regi', function(e) {
		
	});
	
	//로그인 페이지
	$(document).on('click', '#signIn', function(e) {
		var html=
			'<div class="wrapper fadeInDown">'+
				'<div id="loginContent">'+
					'<h2 class="active" id="signIn"> Sign In </h2>'+
					'<h2 class="inactive underlineHover" id="signUp">Sign Up </h2>'+
						'<div class="fadeIn first">랜덤 채팅에 오신 것을 환영합니다.'+
						'</div>'+
					'<form id="loginForm">'+
					'<input type="text" id="login" class="fadeIn second" name="login" placeholder="아이디">'+
					'<input type="password" id="password" class="fadeIn third" name="login" placeholder="패스워드">'+
					'<input type="button" class="fadeIn second submitButton" id="login" value="로그인">'+
					'</form>'+
				"</div>"+
			'</div>'
		$("body").html(html);
	});
	
	//로그인 시도 후 메인페이지
	$(document).on('click', '#login', function(e) {
		//$.get();
		var html=
			'<div class="wrapper fadeInDown">'+
				'<div id="mainContent">'+
					'<div id="welcomeArea">안녕하세요. OO님'+
						'<input type="button" id="ranChatStart" value="랜덤채팅시작">'+
					'</div>'+
					'<div id="friendListArea">'+
					'</div>'+
					'<div id="chatArea">'+
						'<textarea></textarea>'+
						'<input type="button" id="messageSend" value="전송">'+
					'</div>'+
				"</div>"+
			'</div>'
		$("body").html(html);
	});
})

	
	