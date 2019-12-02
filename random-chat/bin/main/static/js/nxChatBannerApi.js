$(document).ready(function(){
		/*var innerHeight = $(window).innerHeight();
		var innerWidth = $(window).innerWidth();
		var scroll_bottom = $(window).scrollTop();
		 */
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
//banner element append
/*
 * <body>
 * 	<div id="chat_banner>
 * 		<div id="chat_banner_head>채팅 상담</div>
 * 		<div id="chat_banner_body></div>
 * 		<div id="chat_banner_foot>
 * 			<textarea name="air_type" wrap="VIRTUAL" placeholder="메시지를 입력 후 엔터를 쳐주세요."></textarea>
 * 		</div>	
 * 	</div>
 * </body>
 */
$("body").append("<div id='chat_banner'></div>");
$("#chat_banner").append("<div id='chat_banner_head'>채팅 상담</div>")
				 .append("<div id='chat_banner_body' align='right' word-wrap='break-word'></div>")
				 .append("<div id='chat_banner_foot'>" +
						 "<textarea name='air_type' wrap='VIRTUAL' placeholder='채팅이 연결되지 않았습니다.' readonly='readonly'>" +
						 "</textarea>" +
				 		 "</div>");
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//banner css setting
/*
 * <style>
 * 	#chat_banner{
 * 					border:1px solid green
 * 	}
 * 	#chat_banner *{
 * 					
 * 	}
 * 	#chat_banner_head{
 * 				
 * 	}
 * 	#chat_banner_body{
 * 		
 * 	}
 * 	#chat_banner_foot{
 * 		
 * 	}
 * 	#chat_banner_foot textarea{
 * 		
 * 	}
 * </style>
 */
$("#chat_banner").css("border","1px solid green")
				 .css("margin","0px")
				 .css("padding","0px")
				 .css("width","200px")
				 .css("height","200px")
				 .css("position","absolute")
				 .css("right","50px")
				 .css("bottom","50px");
var chat_banner_width=$("#chat_banner").width();

$("#chat_banner").children().css("font-size","12px")
							.css("margin","0")
							.css("padding","0");

$("#chat_banner_head").css("border","1px solid pink")
					  .css("height","10%")
					  .css("width",chat_banner_width)
					  .css("position","absolute");
var chat_banner_head_height=$("#chat_banner_head").height();

$("#chat_banner_body").css("border","1px solid blue")
					  .css("width",chat_banner_width)
					  .css("height","70%")
					  .css("position","absolute")
					  .css("overflow-y","auto")
					  .css("overflow-x","hidden")
					  .css("top",chat_banner_head_height);
var chat_banner_body_height=$("#chat_banner_body").height();

$("#chat_banner_foot").css("border","1px solid red")
					  .css("position","absolute")
					  .css("width",chat_banner_width)
					  .css("height","20%")
					  .css("top",chat_banner_head_height+chat_banner_body_height);
$("#chat_banner_foot textarea").css("resize","none")
							   .css("width","98%")
							   .css("font-size","10px")
							   .css("border","0")
							   .css("outline","none")
							   .css("background","clear");
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//banner auto scroll setting

//banner top position
var chat_banner_posit = $("#chat_banner").position().top;
//banner fixed animate
$(window).scroll(function(){
	 //scroll top position
	 var top_posit = $(window).scrollTop();
	 var chat_banner_height = $("#chat_banner").height();
	 $("#chat_banner").animate({top : chat_banner_posit+top_posit},{queue : false, duration : 500});
});
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//chat message send setting
$("#chat_banner_foot textarea").on("keydown",function(key){
	if(key.keyCode == 13){
		if(!event.shiftKey){
			event.preventDefault();
			$("#chat_banner_body").append("<div class='div_msg_send'>"+$("#chat_banner_foot textarea").val()+"</div>");
			$(".div_msg_send").last().css("border","1px solid yellow")
									 .css("padding","0")
									 .css("margin","0")
									 .css("margin-left","10%");
			console.log("carrey : "+$("#chat_banner_foot textarea").val());
			nxWebApi.Message_C($("#chat_banner_foot textarea").val());
			$("#chat_banner_foot textarea").val("");
			
		}
		
	}
	$('#chat_banner_body').scrollTop($('#chat_banner_body').prop('scrollHeight'));
});
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//nxWebApi callback function setting
function init(){
	nxWebApi.SetCallBack(CallbackChat);
	nxWebApi.SetSIPCallBack(CallbackChat);
}
init();
var from_dn;
//chat message receive setting
function CallbackChat(callBackData){
	var result_type;
	var result_data;
	 try{
		 result_type=JSON.parse(callBackData).Type;
		 result_data=JSON.parse(callBackData).Data;
   }catch(SyntaxError){
   }
   
   if(callBackData!=null || callBackData!=undefined){
	  
	   switch(result_type){
	   case "Message" : 
		   $("#chat_banner_body").append("<div class='div_msg_receive'>"+result_data.content+"</div>");
		   $(".div_msg_receive").last().css("border","1px solid yellow")
									   .css("padding","0")
									   .css("margin","0")
									   .css("text-align","left")
									   .css("margin-right","10%");
		   break;   
	   case "Ringing":
		   if(result_data.media=="audio"){
			   $("#chat_banner_body").append("<div class='div_msg_receive'>["+result_data.dn+" 상담원과 전화 연결중입니다...]"+"</div>");
		   }
		   $(".div_msg_receive").last().css("border","1px solid yellow")
									   .css("padding","0")
									   .css("margin","0")
		   from_dn=result_data.dn;
		   break;
	   case "Receive":
		   if(result_data.media=="audio"){
			   $("#chat_banner_body").append("<div class='div_msg_receive'>["+result_data.dn+" 고객님으로부터 전화 연결이 요청되었습니다.]"+"</div>");
		   }
		   $(".div_msg_receive").last().css("border","1px solid yellow")
		   							   .css("padding","0")
		   							   .css("margin","0")
		   break;
	   case "InCall":
		   if(result_data.media=="message"){
			   $("#chat_banner_body").append("<div class='div_msg_receive'>["+result_data.dn+" 님과 채팅이 시작되었습니다.]"+"</div>");
			   $(".div_msg_receive").last().css("text-align","center")
			   $("#chat_banner_foot textarea").attr("placeholder","메시지를 입력 후 엔터를 쳐주세요.")
			   						 .removeAttr("readonly");
		   }else if(result_data.media=="audio"){
			   if(result_data.dn!=from_dn){
				   $("#chat_banner_body").append("<div class='div_msg_receive'>["+result_data.dn+" 고객님과 전화 연결이 시작되었습니다.]"+"</div>");
			   }else{
				   $("#chat_banner_body").append("<div class='div_msg_receive'>["+result_data.dn+" 상담원과 전화 연결이 시작되었습니다.]"+"</div>");
			   }
			   $(".div_msg_receive").last().css("margin-left","10%");
		   }
		   $(".div_msg_receive").last().css("border","1px solid yellow")
									   .css("padding","0")
									   .css("margin","0")
		   break;
	   case "Clear":
		   if(result_data.media=="message"){
			   $("#chat_banner_body").append("<div class='div_msg_receive'>["+result_data.dn+" 님과 채팅이 종료 되었습니다.]"+"</div>");
			   $(".div_msg_receive").last().css("text-align","center");
		   }else if(result_data.media=="audio"){
			   if(result_data.dn!=from_dn){
				   $("#chat_banner_body").append("<div class='div_msg_receive'>["+result_data.dn+" 고객님과 전화 연결이 종료되었습니다.]"+"</div>");
			   }else{
				   $("#chat_banner_body").append("<div class='div_msg_receive'>["+result_data.dn+" 상담원과 전화 연결이 종료되었습니다.]"+"</div>");
			   }
			   
		   }  
		   $(".div_msg_receive").last().css("border","1px solid yellow")
		   							   .css("padding","0")
		   							   .css("margin","0")
		   							   .css("margin-left","10%");
		   break;
	   }
	   $('#chat_banner_body').scrollTop($('#chat_banner_body').prop('scrollHeight'));
   }
   
  /* //nxWebApi 콜백 함수로부터 메시지 이벤트가 수신될 경우 해당 메시지를 뿌려줌
   if(callResult!=null && callResult.Type=="Message"){

	   $("#chat_banner_body").append("<div class='div_msg_receive'>"+callResult.Data.content+"</div>");
	   $(".div_msg_receive").css("border","1px solid yellow")
							.css("padding","0")
							.css("margin","0")
							.css("text-align","left")
							.css("margin-right","10%");
	   $('#chat_banner_body').scrollTop($('#chat_banner_body').prop('scrollHeight'));
   }
   
   //상담원과의 전화 상태를 채팅 메시지 창에 뿌려줌
   if(callResult!=null & callResult.Type=="Receive"){
	   
   }*/
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
});

