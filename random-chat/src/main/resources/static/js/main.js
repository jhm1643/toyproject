$(document).ready(function(){
	
	//회원가입 페이지
	$(document).on('click', '#signUp', function(e) {
		$("body").html(signUpPage);
	});
	
	//회원가입 제출
	$(document).on('click', '#regi', function(e) {
		$.post("/regi",
				{
					userId:$("#userId").val(),
					userPassword:$("#userPass").val()
				},
			function(data){
					if(data.code==200){
						alert("등록이 완료 되었습니다.");
						$("body").html(loginPage);
					}else if(data.code==201){
						alert("이미 등록된 아아디 입니다.");
					}
			}).fail(function(jqXHR){
				alert("회원 가입 실패 [code : "+jqXHR.code+" ]");
			});
	});
	
	//로그인 페이지
	$(document).on('click', '#signIn', function(e) {
		$("body").html(loginPage);
	});
	
	//로그인 시도 후 메인페이지
	$(document).on('click', '#login', function(e) {
		var userId = $("#userId").val()
		$.post("/main",
				{
					userId : userId,
					userPassword : $("#userPassword").val()
				},
				function(data){
					if(data.code==200){
						$("body").html(mainPage(userId));
					}else if(data.code==202){
						alert("아이디 또는 패스워드가 잘못되었습니다.");
					}else if(data.code==203){
					}
				}).fail(function(jqXHR){
					alert("로그인 실패 [code : "+jqXHR+" ]");
				})
	});
	
	//랜덤채팅 시작
	$(document).on('click', '#ranChatStart', function(e) {
		var socket = new SockJS('/random-chat-start');
	    stompClient = Stomp.over(socket);
	    stompClient.connect({}, function (frame) {
	        setConnected(true);
	        console.log('Connected: ' + frame);
//	        stompClient.subscribe('/topic/greetings', function (greeting) {
//	            showGreeting(JSON.parse(greeting.body).content);
//	        });
	        stompClient.subscribe('/topic/chat', function (chat) {
	        	showChat(JSON.parse(chat.body));
	        });
	        stompClient.subscribe('/topic/push', function (push) {
	        	alert("행정안전부로 부터 번역 요청이 들어왔습니다.");
	        });
	    });
	});
})

	
	