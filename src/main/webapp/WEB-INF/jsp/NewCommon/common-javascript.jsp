<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <script id="godaddy-security-s" src="https://cdn.sucuri.net/badge/badge.js" data-s="237" data-i="763647f9924027cc9245942e80b7e0dc0531c7f9b9" data-p="l" data-c="d" data-t="g"></script> -->

<!-- Javascript -->
<script src="../newAssets/vendors/jquery/jquery.min.js"></script>
<script src="../newAssets/vendors/popper.js/popper.min.js"></script>
<script src="../newAssets/vendors/bootstrap/js/bootstrap.min.js"></script>
<script
	src="../newAssets/vendors/jquery-scrollbar/jquery.scrollbar.min.js"></script>
<script
	src="../newAssets/vendors/jquery-scrollLock/jquery-scrollLock.min.js"></script>
<script src="../newAssets/vendors/flot/jquery.flot.js"></script>
<script src="../newAssets/vendors/flot/jquery.flot.resize.js"></script>
<script src="../newAssets/vendors/flot.curvedlines/curvedLines.js"></script>
<script
	src="../newAssets/vendors/easy-pie-chart/jquery.easypiechart.min.js"></script>
<script src="../newAssets/vendors/sparkline/jquery.sparkline.min.js"></script>
<script src="../newAssets/vendors/moment/moment.min.js"></script>
<script src="../newAssets/vendors/fullcalendar/fullcalendar.min.js"></script>


<!-- Charts and maps-->
<!-- <script src="demo/js/flot-charts/curved-line.js"></script>
<script src="../newAssets/demo/js/flot-charts/dynamic.js"></script>
<script src="../newAssets/demo/js/flot-charts/line.js"></script>
<script src="../newAssets/demo/js/flot-charts/chart-tooltips.js"></script>
<script src="../newAssets/demo/js/other-charts.js"></script>

<!-- for todo -->
<script src="../newAssets/vendors/select2/js/select2.full.min.js"></script>

<!-- Date picker -->
<script src="../newAssets/vendors/flatpickr/flatpickr.min.js"></script>

<!-- fontawesome -->
<script src="../newAssets/vendors/fontawesome/js/all.min.js"></script>

<script
	src="../newAssets/vendors/bootstrap-notify/bootstrap-notify.min.js"></script>
<script src="../newAssets/vendors/sweetalert2/sweetalert2.all.min.js"></script>

<!-- MDB core JavaScript -->
<script src="../newAssets/vendors/mdbootstrap/mdb.min.js"></script>

<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/jquery.validate.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/additional-methods.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.1/dist/additional-methods.min.js"></script>

<!-- jBox -->
<script src="../newAssets/vendors/jbox/jBox.all.min.js"></script>
<script src="../newAssets/vendors/jbox/custom-jbox.js"></script>

<!-- Jquery UI -->
<script
	src="../newAssets/vendors/jquery-ui-1.12.1-custom/jquery-ui.min.js"></script>

<!--  jqueryblockUI library for ajax loader -->
<script src="../newAssets/vendors/jqueryBlockUI/jquery.blockUI.js"></script>

<!-- File picker -->
<script src="../newAssets/vendors/fileinput/fileinput.min.js"></script>

<!-- App functions and actions -->
<script src="../newAssets/js/app.min.js"></script>
<!-- <script src="../newAssets/js/inc/actions.js"></script> -->

<script src="../newAssets/projectscripts/kyc-theme.js"></script>

<script type="text/javascript"
	src="../newAssets/projectscripts/common-functions.js"></script>
<script type="text/javascript"
	src="../newAssets/vendors/bootstrap/js/bootstrap-show-modal.js"></script>
<!-- include menus here start-->
<c:if test="${userRole == 'Environmental Officer'}">
	<script src="../newAssets/projectscripts/officer-menu.js"></script>
</c:if>
<c:if test="${userRole == 'Management'}">
	<script src="../newAssets/projectscripts/man-menu.js"></script>
</c:if>


<script>

  var dontBlock = false;

  $(document).ajaxStart(function() {
    if (!dontBlock) $.blockUI({
      message: '<div class="lds-hourglass"></div>',
      css: {
        border: 'none',
        backgroundColor: 'none'
      }
    });
  }).ajaxStop($.unblockUI);

  function makeFullScreen() {
    var elem = document.body;
    launchIntoFullscreen(elem)
  }
  function launchIntoFullscreen(element) {

    let doc = document, elm = doc.documentElement;
    if (elm.requestFullscreen) {
      (!doc.fullscreenElement ? elm.requestFullscreen() : doc.exitFullscreen())
    } else if (elm.mozRequestFullScreen) {
      (!doc.mozFullScreen ? elm.mozRequestFullScreen() : doc
              .mozCancelFullScreen())
    } else if (elm.msRequestFullscreen) {
      (!doc.msFullscreenElement ? elm.msRequestFullscreen() : doc
              .msExitFullscreen())
    } else if (elm.webkitRequestFullscreen) {
      (!doc.webkitIsFullscreen ? elm.webkitRequestFullscreen() : doc
              .webkitCancelFullscreen())
    } else {
      console.log("Fullscreen support not detected.");
    }
  }
  function clearTheme() {
    localStorage.setItem('kyc-theme', 'blue');
    $('body').attr('data-ma-theme', 'blue');
  }
</script>