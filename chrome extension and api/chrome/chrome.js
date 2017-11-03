$(document).ready(function() {
	$('.js-testPostMetaInlineSupplemental').append(' <div class="spinner">  <div class="rect1"></div>  <div class="rect2"></div>  <div class="rect3"></div>  <div class="rect4"></div>  <div class="rect5"></div></div>');
	song = "https://dl.dropboxusercontent.com/apitl/1/AAAjWJARYFz-_D4bnJKEODzXaKLBWSOl3zdYAqeUOlkN0zdpPT5k-R2JtF4z3xq3WxWLY-IkNytiUDvY8NDf8zaFUb0PLJ0A2QOrfc0IJphk8yn5KaQwWSqHcwKGGsoq88sO8LFjYaOZL3t3ResY9HVjmVatnKJjMHxnf0HHU8wWIBB4Nghxtlz8tJa3G0qAjndht7DH1isydIbDlOYCZl06PaekhT8Yv7xoIR9vyclxg9giEeX82VAmEbVdV_3vZ8WliDD7ANkJitgbgNEIiuxnRVwfOyraCLtiXnj7JfaQjg";
	function trig() {
		
		$('.js-testPostMetaInlineSupplemental').html(' <audio controls id="triggering"><source  src="'+song+'" type="audio/mpeg"></audio>');
	
	}
	setTimeout(trig, 5000);

	// $('.webdings-2').click(function(){
	// 		location.href = song;
	// });
	// con = 0;
	// var button = document.getElementById("triggero");
	// var audio = document.getElementById("triggering");

	// $('#triggero').click(function(){
	//   if(document.getElementById("triggero").paused){
	//     document.getElementById("triggering").play();
	//     document.getElementById("triggero").innerHTML = ";";
	//   } else {
	//     document.getElementById("triggering").pause();
	//     document.getElementById("triggero").innerHTML = "4";
	//   }
	// });

	// $.ajax({
 //    	headers: {'Access-Control-Allow-Origin': '*'},
 //        type: 'GET',
 //        url: 'https://crossorigin.me/http://14.141.173.170:5000',
 //        //crossDomain: true,
 //        data: {
 //        	'url': window.location.href
 //        },
 //        dataType: 'json',
 //        success: function (data) {
 //            song = data.url;
 //            $('.change-lg').html('<span class="middotDivider u-fontSize12"></span> <span class="webdings">4</span> <span class="webdings-2">â</span> <span class="webdings-3"><</span>');
 //            $('body').append('<audio controls autoplay="false" style="width:0px;height:0px;" ><source src="'+song+'" style="width:0px;height:0px;" type="audio/mpeg"></audio>');
 //        }
 //    });
});






