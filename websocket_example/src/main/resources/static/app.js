var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    //var socket = new SockJS('/gs-guide-websocket');
    var socket = new SockJS('/safekorea-trans-push');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
//        setConnected(true);
//        console.log('Connected: ' + frame);
//        stompClient.subscribe('/topic/greetings', function (greeting) {
//            showGreeting(JSON.parse(greeting.body).content);
//        });
//        stompClient.subscribe('/topic/chat', function (chat) {
//        	showChat(JSON.parse(chat.body));
//        });
        stompClient.subscribe('/visitKorea/push', function (push) {
        	//document.title = "[user] hello world";
        	alert("행정안전부로 부터 번역 요청이 들어왔습니다.");
        });
    });
}
function blink() {
	 if(window.blinkOn) { window.title = "New Message!"; window.blinkOn = false; }
	 else { window.title = "Normal Title"; window.blinkOn = true; }
	}
function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}
function sendChat() {
	stompClient.send("/app/chat", {}, JSON.stringify({'name': $("#name").val(), 'message': $("#message").val()}));
}
function showChat(chat) {
  $("#greetings").append("<tr><td>" + chat.name + " : " + chat.message + "</td></tr>");
}
$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
    $( "#chatSend" ).click(function(){ sendChat(); });
});