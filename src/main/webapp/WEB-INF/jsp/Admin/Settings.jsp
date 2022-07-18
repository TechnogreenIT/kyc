<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- include common css start-->
<jsp:include page="../NewCommon/common-css.jsp" />
<!-- include common css end-->
</head>
</head>
<body data-ma-theme="blue">
	<main class="main">
		<div class="page-loader">
			<div class="page-loader__spinner">
				<svg viewBox="25 25 50 50">
                  <circle cx="50" cy="50" r="20" fill="none"
						stroke-width="2" stroke-miterlimit="10" />
               </svg>
			</div>
		</div>
		<jsp:include page="../NewCommon/common-header.jsp" />
		<!-- include menus here start-->
		<c:if test="${userRole == 'admin'}">
			<jsp:include page="../NewCommon/admin-menus.jsp" />
		</c:if>
		<c:if test="${userRole == 'Environmental Officer'}">
			<jsp:include page="../NewCommon/env-menus.jsp" />
		</c:if>
		<c:if test="${userRole == 'Management'}">
			<jsp:include
				page="../CommonWebpages/MenusWebpages/ManagementMenus.jsp" />
		</c:if>
		<!-- include menus here end-->

		<section class="content content--full">
			<!-- inner container div start -->
			<div class="content__inner grey lighten-4">
				<!-- breadcrumb start-->
				<div class="breadcrumb_container ml-4">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">${userRole}</a></li>
						<li class="breadcrumb-item active"><a href="#">Profile
								Settings</a></li>
					</ol>
				</div>
				<!-- breadcrumb end -->
				<div class="content__inner content__inner--sm">
					<div class="card-body">
						<!-- KYC settings card start-->
						<div class="card">
							<div class="card-body">
								<h4 class="card-body__title mb-4">Manage Your KYC settings</h4>
								<br>
								<div class="row">
									<label class="col-sm-3 col-form-label">1) Introductory
										Video</label>
									<div class="col-sm-6">
										<!-- Cyan -->
										<div class="form-group">
											<div class="btn-group btn-group-toggle" data-toggle="buttons">

												<c:if
													test="${settingData.getIntroductoryVideoStatus() =='ON'}">
													<label class="btn active"> <input type="radio"
														name="introVideo" id="option1" autocomplete="off"
														value="ON" checked=""> On
													</label>
													<label class="btn"> <input type="radio"
														name="introVideo" id="option2" autocomplete="off"
														value="OFF"> Off
													</label>
												</c:if>

												<c:if
													test="${settingData.getIntroductoryVideoStatus() =='OFF'}">
													<label class="btn"> <input type="radio"
														name="introVideo" id="option1" autocomplete="off"
														value="ON"> On
													</label>
													<label class="btn active"> <input type="radio"
														name="introVideo" id="option2" autocomplete="off"
														value="OFF" checked=""> Off
													</label>
												</c:if>

												<!-- <label class="btn">
                                    <input type="radio" name="introVideo" id="option1" autocomplete="off" value="ON" checked=""> On
                                    </label>
                                    <label class="btn active">
                                    <input type="radio" name="introVideo" id="option2" autocomplete="off" value="OFF"> Off
                                    </label> -->
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- KYC settings card end-->

						<!-- Mail settings card start-->
						<div class="card" style="pointer-events: none;">
							<div class="card-body">
								<h4 class="card-body__title mb-4">Manage Your KYC settings</h4>
								<br>
								<!-- Receive our newsletter -->
								<div class="row">
									<label class="col-sm-6 col-form-label">1) KYC Update
										mails </label>
									<div class="col-sm-6">
										<div class="form-group">
											<div class="btn-group btn-group-toggle" data-toggle="buttons">
												<label class="btn active"> <input type="radio"
													name="options3" id="option13" autocomplete="off" checked="">
													On
												</label> <label class="btn"> <input type="radio"
													name="options3" id="option23" autocomplete="off">
													Off
												</label>
											</div>
										</div>
									</div>
								</div>

								<!-- Receive our newsletter -->
								<div class="row">
									<label class="col-sm-6 col-form-label">2) Receive our
										newsletter </label>
									<div class="col-sm-6">
										<div class="form-group">
											<div class="btn-group btn-group-toggle" data-toggle="buttons">
												<label class="btn active"> <input type="radio"
													name="options1" id="option1" autocomplete="off" checked="">
													On
												</label> <label class="btn"> <input type="radio"
													name="options1" id="option2" autocomplete="off">
													Off
												</label>
											</div>
										</div>
									</div>
								</div>

								<!-- Environmental news in KYC -->
								<div class="row">
									<label class="col-sm-6 col-form-label">3) Environmental
										news in KYC </label>
									<div class="col-sm-6">
										<div class="form-group">
											<div class="btn-group btn-group-toggle" data-toggle="buttons">
												<label class="btn"> <input type="radio"
													name="options2" id="option11" autocomplete="off" checked="">
													On
												</label> <label class="btn active"> <input type="radio"
													name="options2" id="option22" autocomplete="off">
													Off
												</label>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- Mail settings card end-->

					</div>
				</div>
				<!-- include common footer start-->
				<jsp:include page="../NewCommon/common-footer.jsp" />
				<!-- include common footer end-->

			</div>
			<!-- inner container div start -->
		</section>
	</main>
	<!-- include common css start-->
	<jsp:include page="../NewCommon/common-javascript.jsp" />
	<!-- include common css end-->

	<script>
    $(document).ready(function() {

    });

    $("input[name='introVideo']").change(function() {
      var introVideoData = $('input[name=introVideo]:checked').val();
      changeStatus("Introductory Video", introVideoData);
    });

    function changeStatus(title, status) {
      var url = "ajax-changeStatus";
      $.ajax({
        url: url,
        data: {
          title: title,
          status: status
        },
        type: "POST",

        success: function(data, textStatus, jqXHR) {
          console.log("ajax-changeStatus success :" + data);

        },
        error: function(jqXHR, textStatus, errorThrown) {
          console.log('jqXHR:');
          console.log(jqXHR);
          console.log('textStatus:');
          console.log(textStatus);
          console.log('errorThrown:');
          console.log(errorThrown);
        }
      });
    }
  </script>
</body>
</html>