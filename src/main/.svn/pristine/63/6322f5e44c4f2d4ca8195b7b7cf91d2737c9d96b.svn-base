<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body> --%>
<input type="hidden" value="${sessionIndustryType}" id="isproduction">
<footer id="footer">
	Copyright &copy; 2017 Technogreen Environmental Solutions


	<ul class="f-menu">
		<%-- <% <?php

				$role = $_SESSION['role'];
				if($role == "admin"){echo '<li><a href="../YWRtaW4=/adminhomepage.php">Home</a></li>';}
				if($role == "Environmental Officer"){echo '<li><a href="../b2ZmaWNlcg==/Officer.php">Home</a></li>';}
				if($role == "Management"){echo '<li><a href="../dGhpcmRfcGFydHk=/managementhomepage.php">Home</a></li>';}
				if($role == "Third Party"){echo '<li><a href="../bWFuYWdlbWVudA==/Third_party_homepage.php">Home</a></li>';}

				?>%> --%>
		<!--  <li><a href="#">Reports</a></li>
                <li><a href="#">Support</a></li>
                <li><a href="#">Contact</a></li> -->
	</ul>

</footer>

<!-- Page Loader -->
<div class="page-loader">
	<div class="preloader pls-blue">
		<!-- <img src='img/loader_data.webp' height='100'>

                <p>Please wait...</p> -->
	</div>
</div>
<!-- Javascript Libraries -->
<script
	src="vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<script src="vendors/bower_components/flot/jquery.flot.js"></script>
<script src="vendors/bower_components/flot/jquery.flot.resize.js"></script>
<script src="vendors/bower_components/flot.curvedlines/curvedLines.js"></script>
<script src="vendors/sparklines/jquery.sparkline.min.js"></script>
<script
	src="vendors/bower_components/jquery.easy-pie-chart/dist/jquery.easypiechart.min.js"></script>

<script src="vendors/bower_components/moment/min/moment.min.js"></script>
<script
	src="vendors/bower_components/fullcalendar/dist/fullcalendar.min.js"></script>
<script
	src="vendors/bower_components/simpleWeather/jquery.simpleWeather.min.js"></script>
<script src="vendors/bower_components/Waves/dist/waves.min.js"></script>
<script src="vendors/bootstrap-growl/bootstrap-growl.min.js"></script>
<script
	src="vendors/bower_components/bootstrap-sweetalert/lib/sweet-alert.min.js"></script>
<script
	src="vendors/bower_components/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>

<script
	src="vendors/bower_components/bootstrap-select/dist/js/bootstrap-select.js"></script>
<script
	src="vendors/bower_components/nouislider/distribute/jquery.nouislider.all.min.js"></script>
<script
	src="vendors/bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>
<script
	src="vendors/bower_components/typeahead.js/dist/typeahead.bundle.min.js"></script>
<script src="vendors/summernote/dist/summernote-updated.min.js"></script>
<script src="vendors/bower_components/chosen/chosen.jquery.min.js"></script>
<script src="vendors/fileinput/fileinput.min.js"></script>
<script src="vendors/input-mask/input-mask.min.js"></script>
<script src="vendors/farbtastic/farbtastic.min.js"></script>

<!-- Placeholder for IE9 -->
<!--[if IE 9 ]>
            <script src="../vendors/bower_components/jquery-placeholder/jquery.placeholder.min.js"></script>
        <![endif]-->

<script src="js/functions.js"></script>
<script src="js/demo.js"></script>
<script type="text/javascript">
            /*
             * Notifications
             */
            function notify(from, align, icon, type, animIn, animOut, warnings, message, timeOut){
                $.growl({
                    icon: icon,
                    title: warnings,
                    message: message,
                    url: ''
                },{
                        element: 'body',
                        type: type,
                        allow_dismiss: true,
                        placement: {
                                from: from,
                                align: align
                        },
                        offset: {
                            x: 20,
                            y: 85
                        },
                        spacing: 10,
                        z_index: 1031,
                        delay: timeOut,
                        timer: 1000,
                        url_target: '_blank',
                        mouse_over: false,
                        animate: {
                                enter: animIn,
                                exit: animOut
                        },
                        icon_type: 'class',
                        template: '<div data-growl="container" class="alert" role="alert">' +
                                        '<button type="button" class="close" data-growl="dismiss">' +
                                            '<span aria-hidden="true">&times;</span>' +
                                            '<span class="sr-only">Close</span>' +
                                        '</button>' +
                                        '<span data-growl="icon"></span>' +
                                        '<span data-growl="title"></span>' +
                                        '<span data-growl="message"></span>' +
                                        '<a href="#" data-growl="url"></a>' +
                                    '</div>'
                });
            };
            
            $('.notifications > div > .btn').click(function(e){
                e.preventDefault();
                var nFrom = $(this).attr('data-from');
                var nAlign = $(this).attr('data-align');
                var nIcons = $(this).attr('data-icon');
                var nType = $(this).attr('data-type');
                var nAnimIn = $(this).attr('data-animation-in');
                var nAnimOut = $(this).attr('data-animation-out');
                
                notify(nFrom, nAlign, nIcons, nType, nAnimIn, nAnimOut);
            });


            /*
             * Dialogs
             */

            //Basic
            $('#sa-basic').click(function(){
                swal("Here's a message!");
            });

            //A title with a text under
            $('#sa-title').click(function(){
                swal("Here's a message!", "",{timer: 2000})
            });

            //Success Message
            $('#sa-success').click(function(){
                swal("Saved successfully!", "", "success",{timer: 20000})
            });

            //Warning Message
            $('#sa-warning').click(function(){
                swal({   
                    title: "Are you sure?",   
                    text: "You will not be able to recover this imaginary file!",   
                    type: "warning",   
                    showCancelButton: true,   
                    confirmButtonColor: "#DD6B55",   
                    confirmButtonText: "Yes, delete it!",   
                    closeOnConfirm: false 
                }, function(){   
                    swal("Deleted!", "Your imaginary file has been deleted.", "success"); 
                });
            });
            
            //Parameter
            $('#sa-params').click(function(){
                swal({   
                    title: "Are you sure?",   
                    text: "You will not be able to recover this imaginary file!",   
                    type: "warning",   
                    showCancelButton: true,   
                    confirmButtonColor: "#DD6B55",   
                    confirmButtonText: "Yes, delete it!",   
                    cancelButtonText: "No, cancel plx!",   
                    closeOnConfirm: false,   
                    closeOnCancel: false 
                }, function(isConfirm){   
                    if (isConfirm) {     
                        swal("Deleted!", "Your imaginary file has been deleted.", "success");   
                    } else {     
                        swal("Cancelled", "Your imaginary file is safe :)", "error");   
                    } 
                });
            });

            //Custom Image
            $('#sa-image').click(function(){
                swal({   
                    title: "Sweet!",   
                    text: "Here's a custom image.",   
                    imageUrl: "img/thumbs-up.png" 
                });
            });

            //Auto Close Timer
            $('#sa-close').click(function(){
                 swal({   
                    text: "Saved successfully!",   
                    timer: 2000,   
                    showConfirmButton: false 
                });
            });

        </script>


<!-- </body>

Mirrored from byrushan.com/projects/ma/1-5-2/jquery/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 01 Mar 2016 06:49:12 GMT
</html> -->
