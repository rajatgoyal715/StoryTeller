
var cssId = 'StoryTeller-Fontawesome';  
if (!document.getElementById(cssId))
{
    var head  = document.getElementsByTagName('head')[0];
    var link  = document.createElement('link');
    link.id   = cssId;
    link.rel  = 'stylesheet';
    link.type = 'text/css';
    link.href = 'https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css';
    link.media = 'all';
    head.appendChild(link);
}

$(document).ready(function() {
	alert('lol');
});