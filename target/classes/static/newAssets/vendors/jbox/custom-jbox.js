$(document).ready(function () {
	makeToolTip();
});

function makeToolTip(){
	// tooltip
	new jBox('Tooltip', {
		attach : '.mantooltip',
		getTitle : 'data-jbox-title',
		getContent : 'data-jbox-content'
	});
}
 
 function graphModal(divId) {
	var myModal = new jBox('Modal', {
		title : "",
		animation : 'pulse',
		width : 1200,
		content : $('#' + divId)
	});
	myModal.open();
}

//accepts msg , color and notification timeout
function jBoxBottomRightNotice(msg, color, time) {
	new jBox('Notice', {
		content : msg,
		color : color,
		autoClose : time,
		attributes : {
			x : 'right',
			y : 'bottom'
		}
	});
}

//accepts msg , color and notification timeout
function jBoxBottomRightBigNotice(title,msg, color, time) {
	new jBox('Notice', {
	    attributes: {
	      x: 'right',
	      y: 'bottom'
	    },
	    animation: {
	      open: 'slide',
	      close: 'zoomIn'
	    },
	    maxWidth: 500,
	    delayOnHover: true,
	    showCountdown: true,
	    closeButton: true,
	    autoClose : time,
	    color: color,
	    title: title,
	    content: msg
	  });
}
function jBoxFancyNotice(title, msg, color, time) {
	new jBox('Notice', {
		theme : 'NoticeFancy',
		attributes : {
			x : 'left',
			y : 'bottom'
		},
		color : color,
		title : title,
		content : msg,
		audio : 'newAssets/vendors/jbox/audio/success5',
		volume : 80,
		animation : {
			open : 'slide:bottom',
			close : 'slide:left'
		},
		delayOnHover : true,
		showCountdown : true,
		closeButton : true,
		autoClose : time
	});
}
