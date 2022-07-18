<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="./img/faviconkyc.png" type="image/gif"
	sizes="12x12">
<title>KYCTracker</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="demo/js/canvasjs.min.js"></script>
<style>
body {
	margin: 0;
	background: #000;
}

video {
	position: fixed;
	top: 50%;
	left: 50%;
	min-width: 100%;
	min-height: 100%;
	width: auto;
	height: auto;
	z-index: -100;
	transform: translateX(-50%) translateY(-50%);
	background: url('//demosthenes.info/assets/images/polina.jpg') no-repeat;
	background-size: cover;
	transition: 1s opacity;
}

.stopfade {
	opacity: .5;
}

#polina {
	font-family: Agenda-Light, Agenda Light, Agenda, Arial Narrow,
		sans-serif;
	font-weight: 100;
	background: rgba(0, 0, 0, 0.3);
	color: white;
	padding: 2rem;
	width: 33%;
	margin: 2rem;
	float: right;
	font-size: 1.2rem;
}

h1 {
	font-size: 3rem;
	text-transform: uppercase;
	margin-top: 0;
	letter-spacing: .3rem;
}

#polina button {
	display: block;
	width: 80%;
	padding: .4rem;
	border: none;
	margin: 1rem auto;
	font-size: 1.3rem;
	background: rgba(255, 255, 255, 0.23);
	color: #fff;
	border-radius: 3px;
	cursor: pointer;
	transition: .3s background;
}

#polina button:hover {
	background: rgba(0, 0, 0, 0.5);
}

a {
	display: inline-block;
	color: #fff;
	text-decoration: none;
	background: rgba(0, 0, 0, 0.5);
	padding: .5rem;
	transition: .6s background;
}

a:hover {
	background: rgba(0, 0, 0, 0.9);
}

@media screen and (max-width: 500px) {
	div {
		width: 70%;
	}
}

@media screen and (max-device-width: 800px) {
	html {
		background: url(//demosthenes.info/assets/images/polina.jpg) #000
			no-repeat center center fixed;
	}
	#bgvid {
		display: none;
	}
}
</style>

<script>
	var vid = document.getElementById("bgvid");
	var pauseButton = document.querySelector("#polina button");

	function vidFade() {
		vid.classList.add("stopfade");
	}

	vid.addEventListener('ended', function() {
		// only functional if "loop" is removed 
		vid.pause();
		// to capture IE10
		vidFade();
	});

	pauseButton.addEventListener("click", function() {
		vid.classList.toggle("stopfade");
		if (vid.paused) {
			vid.play();
			pauseButton.innerHTML = "Pause";
		} else {
			vid.pause();
			pauseButton.innerHTML = "Paused";
		}
	});
</script>
</head>
<body>

	<video id="bgvid" playsinline autoplay> <source
		src="newAssets/LogoVideo/intro.mp4" type="video/mp4"></video>

	<!--<playsinline autoplay muted loop-->
	<script type="text/javascript">
		$(document).ready(function() {
			// Handler for .ready() called.
			window.setTimeout(function() {
				location.href = "category";
			}, 10000);
		});
	</script>

</body>
</html>