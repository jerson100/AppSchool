<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head lang="es">
<meta charset="utf-8">
<title>Pagina Principal</title>
<meta name="description"
	content="Página principal de la escuela Uribe School" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	<link rel="stylesheet" href="assets/css/theme/color3.css">
<link rel="stylesheet"
	href="assets/css/base/base3.css">
	
<link rel="shortcut icon" href="assets/img/static/logoColegio.jpg"
	type="image/x-icon">
<!-- Inicio nav -->
<link rel="stylesheet" href="assets/css/utils/template/bootstrap.css" />
<link rel="stylesheet" href="assets/css/utils/template/font-awesome.css" />

<!-- text fonts -->
<link rel="stylesheet" href="assets/css/utils/template/ace-fonts.css" />

<!-- ace styles -->
<link rel="stylesheet" href="assets/css/utils/template/ace.css"
	class="ace-main-stylesheet" id="main-ace-style" />

<link rel="stylesheet" href="assets/css/layout/layout3.css">



<link rel="stylesheet" href="assets/css/components/button3.css">

<link rel="stylesheet" href="assets/css/components/scroll3.css">

<link rel="stylesheet" href="assets/js/utils/modal/je-modal-style3.css">

<script src="assets/js/utils/template/ace-extra.js"></script>

<link rel="stylesheet"
	href="assets/css/utils/librerias/glide/glide.core.min.css">
<link rel="stylesheet"
	href="assets/css/utils/librerias/glide/glide.theme.min.css">

<link rel="stylesheet" href="assets/css/pages/inicio3.css">



</head>
<body class="no-skin je-scroll">

	<jsp:include page="../includes/header.jsp"></jsp:include>

	<div class="main-container" id="main-container">

		<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>
		<jsp:include page="../includes/sidebar.jsp">
			<jsp:param value="1" name="foco" />
		</jsp:include>
		<!-- /section:basics/sidebar -->
		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i></li>
				</ul>
				<!-- /.breadcrumb -->

			</div>
				<div class="inicio">
					<div class="inicio__content je-container je-container--xxl">
						<div class="inicio__left">
							<section class="school-images">
							 	<div class="school-images__content bordered">
									<div class="banner-container">
				<div style="margin: auto;">
					<div class="banner-inner je-mb-1"">
							<div class="glide">
								<div class="glide__track" data-glide-el="track">
									<ul class="glide__slides">
										<li class="glide__slide"><img
											src="assets/img/pag-inicio/foto-1.jpg"> <!--<img src="https://uribestorage.blob.core.windows.net/recursos/aula-virtual/pag-principal/foto-1.jpg">-->
										</li>
										<li class="glide__slide"><img
											src="assets/img/pag-inicio/foto-2.jpg"></li>
										<li class="glide__slide"><img
											src="assets/img/pag-inicio/foto-3.jpg"></li>
										<li class="glide__slide"><img
											src="assets/img/pag-inicio/foto-4.jpg"></li>
										<li class="glide__slide"><img
											src="assets/img/pag-inicio/foto-5.jpg"></li>
										<li class="glide__slide"><img
											src="assets/img/pag-inicio/foto-6.jpg"></li>
										<li class="glide__slide"><img
											src="assets/img/pag-inicio/foto-7.jpg"></li>
										<li class="glide__slide"><img
											src="assets/img/pag-inicio/foto-8.jpg"></li>
										<li class="glide__slide"><img
											src="assets/img/pag-inicio/foto-9.jpg"></li>
									</ul>
								</div>
								<div class="glide__arrows" data-glide-el="controls">
									<button class="glide__arrow glide__arrow--left dark-school"
										data-glide-dir="<">
										<i class="fas fa-step-backward"></i>
									</button>
									<button class="glide__arrow glide__arrow--right dark-school"
										data-glide-dir=">">
										<i class="fas fa-step-forward"></i>
									</button>
								</div>
							</div>
					</div>
					<div class="details-container je-mb-1">
						<div class="flex center container-button">
							<button class="je-btn dark-school">
								<a href="cursos" style="color: white; text-decoration: none">Ir
									a mis cursos</a>
							</button>
						</div>
					</div>
				</div>
			</div>
							  	</div>
							</section>
							<section class="notices">
								<div class="notices__content bordered">
									<h1 style="margin-top: 0">Noticias</h1>
									
									<div class="glide2">
										<div class="glide__track" data-glide-el="track">
											<ul class="glide__slides">
												<li class="glide__slide">
													<article class="notice bordered">
														<img src="assets/img/pag-inicio/foto-9.jpg">
														<h1 class="notice__title">Notice 1</h1>
														<p class="notice__description">Descripción de la noticia, Descripción de la noticia, Descripción de la noticia, Descripción de la noticia.</p>
														<a class="notice__link">ver más</a>
													</article>		
												</li>
											</ul>
										</div>
										<div class="glide__arrows" data-glide-el="controls">
											<button class="glide__arrow glide__arrow--left dark-school"
												data-glide-dir="<">
												<i class="fas fa-step-backward"></i>
											</button>
											<button class="glide__arrow glide__arrow--right dark-school"
												data-glide-dir=">">
												<i class="fas fa-step-forward"></i>
											</button>
										</div>
									</div>
								</div>
							</section>
						</div>					
						<div class="inicio__right">
							<section class="reminders">
							 	<div class="reminders__content bordered">
									<h1 class="reminders__title">Recordatorio</h1>
									<ul class="reminders__list je-scroll">
										<c:forEach var="i" begin="1" end="10" >
											<li class="reminders__item">
												<div class="reminder">
													<p class="reminder__course">
														Matemàtica II
													</p>
													<p class="reminder__title">
														Resolver las ecuaciones diferenciales
													</p>
													<time class="reminder__date">15/01/2021 a las 18:25 pm</time>
												</div>
											</li>
										</c:forEach>
									</ul>
							  	</div>
							</section>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.main-content -->
		

	</div>

	<%@include file="../includes/footer.jsp"%>
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
	<script src="assets/js/utils/librerias/glide.js"></script>
	<script>
	new Glide('.glide', {
		  type: 'carousel',
		  startAt: 0,
		  perView: 1,
		  autoplay:3000
		}).mount();
	
	new Glide('.glide2', {
		type: 'carousel',
		  perView: 3,
		  focusAt: 'center',
		  breakpoints: {
		    800: {
		      perView: 2
		    },
		    480: {
		      perView: 1
		    }
		  }
		}).mount();
	</script>
	<script src="https://kit.fontawesome.com/56e0c4d4ed.js"
		crossorigin="anonymous"></script>
	<script src="assets/js/generic/close3.js"></script>
</body>
</html>