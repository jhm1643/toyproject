//회원가입 페이지
	var signUpPage = '<div class="wrapper fadeInDown">'+
						'<div id="loginContent">'+
							"<h2 class='inactive underlineHover' id='signIn'> Sign In </h2>"+
							"<h2 class='active' id='signUp'>Sign Up </h2>"+
								"<form id='regiForm'>"+
									"<input type='text' id='userId' class='fadeIn second' placeholder='아이디'>"+
									"<input type='password' id='userPass' class='fadeIn third' placeholder='패스워드'>"+
									"<input type='button' class='fadeIn second submitButton' id='regi' value='완료'>"+
								"</form>"+
						"</div>"+
					"</div>";
	
	var loginPage= '<div class="wrapper fadeInDown">'+
						'<div id="loginContent">'+
							'<h2 class="active" id="signIn"> Sign In </h2>'+
							'<h2 class="inactive underlineHover" id="signUp">Sign Up </h2>'+
								'<div class="fadeIn first">랜덤 채팅에 오신 것을 환영합니다.'+
								'</div>'+
							'<form id="loginForm">'+
							'<input type="text" id="userId" class="fadeIn second" placeholder="아이디">'+
							'<input type="password" id="userPassword" class="fadeIn third" placeholder="패스워드">'+
							'<input type="button" class="fadeIn second submitButton" id="login" value="로그인">'+
							'</form>'+
						"</div>"+
					'</div>';
	
	var mainPage = function(mainObject ){
		console.warn(mainObject.friendsList);
		var friendsList ="";
		for(var i=0;i<mainObject.friendsList.length;i++){
			var friendObject = mainObject.friendsList[i];
			friendsList+="<div ondblclick='goToFriendsChat(\""+friendObject.chatId+"\");'>"+friendObject.friendsId+"</div>";
		}
		var html = '<div class="wrapper fadeInDown">'+
		'<div id="mainContent">'+
			'<div id="welcomeArea">안녕하세요. '+mainObject.userId+'님'+
				'<input type="button" id="ranChatStart" value="랜덤채팅시작">'+
				'<input type="button" id="friendAdd" value="친구추가">'+
				'<input type="button" id="chatOut" value="채팅방 나가기">'+
				'<input type="button" id="logOut" value="로그아웃">'+
			'</div>'+
			'<div id="friendListArea">친구 목록'+
			friendsList+
			'</div>'+
			'<div id="chatArea">'+
				'<div id="chatAreaBody"></div>'+
				'<textarea id="chatAreaFooter"></textarea>'+
				'<input type="button" id="messageSend" value="전송">'+
			'</div>'+
		"</div>"+
		'</div>';
		return html;
	}
		