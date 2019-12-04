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
						var mainObject = new Object();
						mainObject.userId = userId;
						mainObject.friendsList = data.friendsList;
						$("body").html(mainPage(mainObject));
						
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
	var userId1 = "";
	var userId2 = "";
	var friendsId = "";
	socketConnectionFunc = function(){
		var socket = new SockJS('/random-chat-start');
	    stompClient = Stomp.over(socket);
	    stompClient.connect({}, function (frame) {
	    	setConnected = true;
	        stompClient.subscribe('/topic/chat/'+roomId, function (chat) {
	        	showChat(JSON.parse(chat.body));
	        });
	        stompClient.subscribe('/topic/friend-add/'+roomId, function (chat) {
	        	console.warn(JSON.parse(chat.body));
	        	friendsId = JSON.parse(chat.body).userId;
	        	friendAddAgree = JSON.parse(chat.body).message;
	        	console.log(userId);
	        	console.log(friendsId);
	        	console.log(friendAddAgree);
	        	if(userId!=friendsId && friendAddAgree==null){
	        		isConfirm = window.confirm('상대방으로부터 친구 요청이 왔습니다. 수락하시겠습니까?');
	        		if(isConfirm==true){
	        			stompClient.send("/app/friend-add/"+roomId, {}, JSON.stringify({'userId': userId, 'message': "ok", "friendsId":friendsId}));
	        			$("#friendListArea").append("<div ondblclick='goToFriendsChat(\""+roomId+"\");'>"+friendsId+"</div>")
	        		}else{
	        			stompClient.send("/app/friend-add/"+roomId, {}, JSON.stringify({'userId': userId, 'message': "no"}));
	        		}
	        	}else if(friendAddAgree!=null && userId!=friendsId){
	        		if(friendAddAgree=='ok'){
	        			$("#friendListArea").append("<div ondblclick='goToFriendsChat(\""+roomId+"\");'>"+friendsId+"</div>")
	        			$("#chatAreaBody").append("<div class='div_msg_send'>친구 요청이 수락되었습니다.</div>");
	        		}else{
	        			$("#chatAreaBody").append("<div class='div_msg_send'>친구 요청이 거부되었습니다.</div>");
	        		}
	        	}
	        });
	        stompClient.subscribe('/topic/hello/'+roomId, function (chat) {
	        	var userId1 = JSON.parse(chat.body).userId1;
	        	var userId2 = JSON.parse(chat.body).userId2;
	        	if(userId1==userId){
	        		friendsId = userId2;
	        		showHello(userId2);
	        	}
	        });
	    });
	}
	$(document).on('click', '#ranChatStart', function(e) {
		$("#chatAreaBody").html("");
		$("#chatAreaBody").append("<div class='div_msg_send'>랜덤 채팅 연결중입니다...</div>");
		$.get("/ran-chat-start",{userId:userId},
				function(data){
					roomId=data.chatId;
					userId1=data.userId1;
					userId2=data.userId2;
					if(userId1!="" && userId1!=userId){
						friendsId = userId1;
						showHello(userId1);
					}
				}
		);
		socketConnectionFunc();
	});
	
	var sendChat = function(userId, message) {
		stompClient.send("/app/chat/"+roomId, {}, JSON.stringify({'userId': userId, 'message': message}));
		$("#chatAreaFooter").val("");
	}
	
	var randomChatSuccess = function(userId, message) {
		stompClient.send("/app/chat/"+roomId, {}, JSON.stringify({'userId': userId, 'message': message}));
		$("#chatAreaFooter").val("");
	}
	
	var showChat = function (chat) {
		$("#chatAreaBody").append("<div class='div_msg_send'>" + chat.userId + " : " + chat.message + "</div>");
	}
	
	var showHello = function (connectedUserId) {
		$("#chatAreaBody").append("<div class='div_msg_send'>" + connectedUserId + "님과 연결되었습니다.</div>");
	}
	
	var sendBye = function(){
		
	}
//	friendAddAgree = function(){
//		if(confirm('상대방으로부터 친구 요청이 왔습니다. 수락하시겠습니까?')){
//			stompClient.send("/app/friend-add/"+roomId, {}, JSON.stringify({'userId': userId, 'message': message}));
//			$("#friendListArea").append("<div ondblclick='goToFriendsChat(\""+roomId+"\");'>"+friendsId+"</div>")
//		}
//	}
	$(document).on("keydown","#chatAreaFooter",function(key){
		if(key.keyCode == 13){
			if(!event.shiftKey){
				event.preventDefault();
				sendChat(userId, $("#chatAreaFooter").val())
			}
		}
	});
	var friendsRequest = false;
	$(document).on("click","#friendAdd",function(){
		jQuery.ajaxSettings.traditional = true;
		$.ajaxSetup({
			contentType:"application/json; charset=UTF-8",
			global:false
		})
		var friends = new Object();
		friends.userId = userId;
		friends.chatId = roomId;
		friends.friendsId = friendsId;
		console.warn(friends);
		$("#chatAreaBody").append("<div class='div_msg_send'>친구 요청을 하는 중입니다...</div>");
		friendsRequest = true;
		stompClient.send("/app/friend-add/"+roomId, {}, JSON.stringify({'userId': userId}));
//		$.post("/friend-add",JSON.stringify(friends),
//				function(data){
//					if(data=='success'){
//						$("#friendListArea").append("<div ondblclick='goToFriendsChat(\""+roomId+"\");'>"+friendsId+"</div>")
//					}
//				}
//		);
	});
	
	goToFriendsChat = function(chatId){
		
		$.get("/friends-chat-reopen/"+chatId,
				function(data){
					console.warn(data);
					var html="";
					for(var i=0;i<data.length;i++){
						var msgObj = data[i];
						html+="<div class='div_msg_send'>" + msgObj.userId + " : " + msgObj.message + "</div>"
					}
					$("#chatAreaBody").html(html);
				}
		)
		roomId = chatId;
		socketConnectionFunc();
	}
	
	$(document).on("click","#logOut",function(){
		$("body").html(loginPage);
		var roomId = "";
		var userId1 = "";
		var userId2 = "";
		var friendsId = "";
		var userId = "";
		 if (stompClient !== null) {
		        stompClient.disconnect();
		    }
	});

	$(document).on("click","#chatOut",function(){
		
	});
})

	
	