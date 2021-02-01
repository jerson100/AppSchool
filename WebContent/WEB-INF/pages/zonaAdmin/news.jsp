<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Mantenimiento - noticias</title>
		<meta name="description" content="Mantenimiento de noticias" />
		<meta name="viewport"
			content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<link rel="shortcut icon" href="assets/img/static/logoColegio.jpg"
			type="image/x-icon">
		<!-- Inicio nav -->
	
		<link rel="stylesheet" href="assets/css/theme/color3.css">
		<link rel="stylesheet" href="assets/css/base/base3.css">
	
		<link rel="stylesheet" href="assets/css/utils/template/bootstrap.css" />
		<link rel="stylesheet" href="assets/css/utils/template/font-awesome.css" />
	
		<!-- text fonts -->
		<link rel="stylesheet" href="assets/css/utils/template/ace-fonts.css" />
	
		<!-- ace styles -->
		<link rel="stylesheet" href="assets/css/utils/template/ace.css"
		class="ace-main-stylesheet" id="main-ace-style" />
	<!--
		<link rel="stylesheet" href="assets/css/base/base.css">
	  -->
		
		<link rel="stylesheet" href="assets/css/layout/layout3.css">
		
		<link rel="stylesheet" href="assets/css/components/button3.css">
		
		<link rel="stylesheet" href="assets/css/components/scroll3.css">
		
		<link rel="stylesheet" href="assets/css/utils/animation3.css">
		
		<link rel="stylesheet" href="assets/js/utils/modal/je-modal-style3.css">
		
		<link rel="stylesheet" href="assets/css/components/loader3.css">
		
		<link rel="stylesheet" href="assets/css/pages/courses/resourses3.css">
		
		<link rel="stylesheet" href="assets/css/components/modal3.css">
	
		<link rel="stylesheet" href="assets/css/components/goUp3.css">
		
		<link rel="stylesheet" href="assets/css/components/list.css">
		
		<link rel="stylesheet" href="assets/css/pages/news.css">

	</head>
	<body class="no-skin je-scroll">
		<jsp:include page="../../includes/header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try {
					ace.settings.check('main-container', 'fixed')
				} catch (e) {
				}
			</script>
			<jsp:include page="../../includes/sidebar.jsp">
				<jsp:param value="7" name="foco" />
			</jsp:include>
			<!-- /section:basics/sidebar -->
			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed')
						} catch (e) {
						}
					</script>
					<jsp:include page="../../includes/breadcrumb.jsp">
						<jsp:param name="title" value="Mantenimiento de noticias" />
						<jsp:param value="zonaAdmin/mantenimientoNoticias" name="href" />
					</jsp:include>
				</div>
				<div>
				
				<div class="je-container">			
					<div class="je-item">
						<div class="news">
							<div class="news__header">							
								<h1 class="news__title">Mantenimiento de las noticias</h1>
								<button class="btn btn-primary">Agregar Noticas</button>
							</div>
							<div class="news__content">
								<c:forEach begin="1" end="3">
									<div class="news__item">
										<article class="new">
											<div class="new__image">
												<img class="new__src" src="https://astelus.com/wp-content/viajes/Lago-Moraine-Parque-Nacional-Banff-Alberta-Canada.jpg"  alt="d">
												<div class="new__actions">
													<button class="btn btn-primary">x</button>
													<button class="btn btn-primary">i</button>
												</div>
											</div>
											<div class="new__content">
												<ul class="je-list je-list--description je-list--dir-vertical">
													<li class="je-list__item">
														<div class="je-list__left">
															<span class="je-list__icon">i </span>
															<span class="je-list__text">
																<b>Título:</b>
															</span>
														</div>
														<div class="je-list__right">
															<h1>Nuevas estrategias para el desarrollo de los niños...</h1>
														</div>
													</li>
													<li class="je-list__item">
														<div class="je-list__left">
															<span class="je-list__icon">i </span>
															<span class="je-list__text">
																<b>Descripción:</b>
															</span>
														</div>
														<div class="je-list__right">
															<p>Alguna descripción</p>
														</div>
													</li>
													<li class="je-list__item">
														<div class="je-list__left">
															<span class="je-list__icon">i </span>
															<span class="je-list__text">
																<b>Link:</b>
															</span>
														</div>
														<div class="je-list__right">
															<a href="">my favorite link</a>
														</div>
													</li>
												</ul>
											</div>
										</article>
									</div>
								</c:forEach>
							</div>
						</div>					
					</div>
				</div>		
			</div>
		</div>
		<script src="assets/js/utils/template/jquery.js"></script>
		<script type="text/javascript">
			if ('ontouchstart' in document.documentElement)
				document
						.write("<script src='assets/js/utils/template/jquery.mobile.custom.js'>"
								+ "<"+"/script>");
		</script>
		<script src="https://kit.fontawesome.com/56e0c4d4ed.js"
		crossorigin="anonymous"></script>
		<script src="assets/js/utils/template/bootstrap.js"></script>
		<script src="assets/js/utils/template/ace/elements.scroller.js"></script>
		<script src="assets/js/utils/template/ace/ace.js"></script>
		<script src="assets/js/utils/template/ace/ace.ajax-content.js"></script>
		<script src="assets/js/utils/template/ace/ace.touch-drag.js"></script>
		<script src="assets/js/utils/template/ace/ace.sidebar.js"></script>
		<script src="assets/js/utils/Api3.js"></script>
		<script src="assets/js/utils/modal/je-modal3.js"></script>
		<script src="assets/js/utils/modal/modalMessage3.js"></script>
		<script src="assets/js/generic/loader3.js"></script>
		<script src="assets/js/utils/element3.js"></script>
		<script src="assets/js/generic/close3.js"></script>
		<script src="assets/js/pages/cursos/tags3.js"></script>
		<script src="assets/js/utils/goUp3.js"></script>
	</body>
</html>