var myApp = new Framework7({
	animateNavBackIcon:true
});
var mainView = myApp.addView('.view-main', {
    domCache: true 
});

mainView.router.load({pageName: 'about'});
//var type = window.location.hash.substr(1);

	UsernameFB = ""; 
	$(document).ready(function(){
		function getUserData() {
			FB.api('/me', function(response) {
				UsernameFB = response.name;
				$('#getArticles').removeClass('cached');
			});
		}
		 
		window.fbAsyncInit = function() {
			//SDK loaded, initialize it
			FB.init({
		      appId      : '1815303495353730',
		      cookie     : true,
		      xfbml      : true,
		      version    : 'v2.8'
		    });
		 
			//check user session and refresh it
			FB.getLoginStatus(function(response) {
				if (response.status === 'connected') {
					//user is authorized
					getUserData();
				} else {
					setInterval(function(){
						$('.spinner').hide();
						$('#fb-button').show()
					}, 1500)
				}
			});
		};
		 
		//load the JavaScript SDK
		(function(d, s, id){
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id)) {return;}
			js = d.createElement(s); js.id = id;
			js.src = "//connect.facebook.com/en_US/sdk.js";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
		 
		//add event listener to login button
		document.getElementById('fb-button').addEventListener('click', function() {
			//do the login
			FB.login(function(response) {
				if (response.authResponse) {
					//user just authorized your app
					getUserData();
				}
			}, {scope: 'email,public_profile', return_scopes: true});
		}, false);
	});