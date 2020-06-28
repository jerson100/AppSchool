<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head lang="es">
	<meta charset="utf-8">
	<title>Mantenimiento</title>
	<meta name="description" content="Mantenimiento de la plataforma Aula Virtual Uribe's School" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
	<link rel="shortcut icon" href="assets/img/static/logoColegio.jpg" type="image/x-icon">
	<!-- Inicio nav -->
	<link rel="stylesheet" href="assets/css/utils/template/bootstrap.css"/>
	<link rel="stylesheet" href="assets/css/utils/template/font-awesome.css"/>
	
	<!-- text fonts -->
	<link rel="stylesheet" href="assets/css/utils/template/ace-fonts.css" />

	<!-- ace styles -->
	<link rel="stylesheet" href="assets/css/utils/template/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
  
  	<link rel="stylesheet" href="assets/css/layout/layout.css">
  	
  	<link rel="stylesheet" href="assets/css/theme/color.css">
  
  	<link rel="stylesheet" href="assets/css/components/button.css">
	
	<link rel="stylesheet" href="assets/js/utils/modal/je-modal-style.css">

	<!--[if lte IE 9]>
		<link rel="stylesheet" href="../assets/css/ace-part2.css" class="ace-main-stylesheet" />
	<![endif]-->

	<!--[if lte IE 9]>
		 <link rel="stylesheet" href="../assets/css/ace-ie.css" />
	<![endif]-->

	<!-- inline styles related to this page -->

	<!-- ace settings handler -->
	<script src="assets/js/utils/template/ace-extra.js"></script>
	<!-- fin nav -->
	
</head>
<body class="no-skin">
	<jsp:include page="../includes/header.jsp"></jsp:include>
	
	<div class="main-container" id="main-container">
		
		<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>
		<%@include file="../includes/sidebar.jsp" %>
		<!-- /section:basics/sidebar -->
			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
							</li>

							<li>
								<a href="inicio" style="font-size:17px;">Mantenimiento</a>
							</li>
						</ul><!-- /.breadcrumb -->

					</div>
				<div class="je-container">
					<div class="je-item">
						<h1>Mantenimiento</h1>
					</div>
				</div>
			</div><!-- /.main-content -->

	</div>
	
	<%@include file="../includes/footer.jsp" %>
			<!-- #section:basics/sidebar -->

	<script type="text/javascript">
			window.jQuery || document.write("<script src='assets/js/utils/template/jquery.js'>"+"<"+"/script>");
	</script>			
	<script src="assets/js/utils/template/jquery.js"></script>
	<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/utils/template/jquery.mobile.custom.js'>"+"<"+"/script>");
	</script>
	
	<script src="assets/js/utils/template/bootstrap.js"></script>
	<script src="assets/js/utils/template/ace/elements.scroller.js"></script>
	<script src="assets/js/utils/template/ace/ace.js"></script>
	<script src="assets/js/utils/template/ace/ace.ajax-content.js"></script>
	<script src="assets/js/utils/template/ace/ace.touch-drag.js"></script>
	<script src="assets/js/utils/template/ace/ace.sidebar.js"></script>
	<script src="assets/js/utils/ajax.js"></script>
	<script src="assets/js/utils/modal/je-modal.js"></script>
	<script src="assets/js/generic/close.js"></script>
</body>
</html>