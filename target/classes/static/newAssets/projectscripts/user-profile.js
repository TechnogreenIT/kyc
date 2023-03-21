$(document).ready(function () {
	console.log(" User profile js....loaded.....");
});

function openFp() {
	$(".item-img").click();
}

$('.item-img').on('change', function() {
	imageId = $(this).data('id');
	tempFilename = $(this).val();
	$('#cancelCropBtn').data('id', imageId);
	readFile(this);
});

var $uploadCrop,
tempFilename,
rawImg,
imageId;
function readFile(input) {
	if (input.files && input.files[0]) {
	  var reader = new FileReader();
		reader.onload = function (e) {
			$('.upload-demo').addClass('ready');
			$('#cropImagePop').modal('show');
			rawImg = e.target.result;
		}
		reader.readAsDataURL(input.files[0]);
	}
	else {
		swal("Sorry - you're browser doesn't support the FileReader API");
	}
}

$uploadCrop = $('#upload-demo').croppie({
	viewport: {
		width: 200,
		height: 200,
	},
	enforceBoundary: false,
	enableExif: true
});
$('#cropImagePop').on('shown.bs.modal', function(){
	// alert('Shown pop');
	$uploadCrop.croppie('bind', {
		url: rawImg
	}).then(function(){
		console.log('jQuery bind complete');
	});
});

$('#cropImageBtn').on('click', function (ev) {
	$uploadCrop.croppie('result', {
		type: 'base64',
		format: 'jpeg',
		size: {width: 1000, height: 1000}
	}).then(function (resp) {
		$('#img-user-profile').attr('src', resp);
		saveUserProfile(resp);
		$('#cropImagePop').modal('hide');
	});
});

function saveUserProfile(resp){
	var form_data = new FormData();
	form_data.append('imageFile', resp);
	
	$.ajax({
		url: 'ajax-save-user-profile',
		data : form_data,
		processData : false,
		contentType : false,
		type: 'post',
		success: function (data) {
			if(data == "success"){
				$("#user-profile-pic").attr('src', resp);
				jBoxBottomRightBigNotice("Success", "Your Profile picture updated !!", "green", "3500");
			} else {
				jBoxBottomRightBigNotice("Error", "Oopss !! something went wrong", "red", "3500");
			}
		},
		error: function (jqXHR, textStatus, errorThrown) {
			jBoxBottomRightBigNotice("Error", "Oopss !! session expired /n", "red", "2000");
			window.setTimeout(window.location = "logout", 7000);
		}
	});

}