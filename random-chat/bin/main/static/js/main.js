$(document).ready(function(){
	var setConnected = false;
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
	var userId = "";
	$(document).on('click', '#login', function(e) {
		userId = $("#userId").val()
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
	var roomId = "";
	$(document).on('click', '#ranChatStart', function(e) {
		$.get("/ran-chat-start",{userId:userId},
				function(data){
					roomId=data;
				});
		var socket = new SockJS('/random-chat-start');
	    stompClient = Stomp.over(socket);
	    stompClient.connect({}, function (frame) {
	    	setConnected = true;
	        stompClient.subscribe('/topic/chat/'+roomId, function (chat) {
	        	showChat(JSON.parse(chat.body));
	        });
	    });
	});
	
	var sendChat = function(userId, message) {
		stompClient.send("/app/chat/"+roomId, {}, JSON.stringify({'userId': userId, 'message': message}));
		$("#chatAreaFooter").val("");
	}
	
	var showChat = function (chat) {
			$("#chatAreaBody").append("<div class='div_msg_send'>" + chat.userId + " : " + chat.message + "</div>");
	}
	$(document).on("keydown","#chatAreaFooter",function(key){
		if(key.keyCode == 13){
			if(!event.shiftKey){
				event.preventDefault();
				sendChat(userId, $("#chatAreaFooter").val())
				
			}
			
		}
		
		
	})
	
})

	
	