<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Horario</title>
		<meta name="description" content="Horario de clases" />
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
		
		<link rel="stylesheet" href="assets/css/pages/horario.css">
		
	</head>
	<body class="no-skin">
		<jsp:include page="../includes/header.jsp"></jsp:include>
		<div class="main-container" id="main-container">
		
		<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>
		<jsp:include page="../includes/sidebar.jsp">
			<jsp:param value="3" name="foco"/>
		</jsp:include>
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
						</ul><!-- /.breadcrumb -->
					</div>
					<div class="banner-container">
						<div class="je-container">
							<div class="je-item" style="position:relative;">
								<h1 style="text-align:center">Horario</h1>
								<div style="position: absolute;right: 1rem;top: 50%;transform: translateY(-50%);">
									<label>Mostrar todo</label>
									<input type="checkbox" id="mostrarTodo">
								</div>
							</div>
							<div class="je-item">
								<div class="days-container" id="days-container">
									<c:forEach items="${requestScope.horario}" var="day">
										<div class="day-item">
											<span class="name-day">${day.key==1?"Lunes":day.key==2?"Martes":day.key==3?"Miércoles":day.key==4?"Jueves":"Viernes"}</span>
											<div class="day-content">
												<c:forEach items="${day.value}" var="data">
													<div class="separator"></div>
													<div class="class-container">
														  <p class="bold">Curso:</p>
														  <div class="name-course">
													         <p>${data.descCurso}</p>
													      </div>
													      <div class="hour-course">
													         <p class="bold">Hora:</p>
													         <p>${data.horaIni} - ${data.horaFin}</p>
													      </div>
													      <div class="name-teacher">
													         <p class="bold"><c:out value="${sessionScope.usuarioSesion.idPerfil == 2 ? 'Sección:':'Docente:'}"></c:out></p>
													         <p>${data.mensaje}</p>
													      </div>
													</div>
												</c:forEach>
											 </div>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
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
			<script src="https://kit.fontawesome.com/56e0c4d4ed.js" crossorigin="anonymous"></script>
			<script src="assets/js/generic/close.js"></script>
			<script src="assets/js/pages/horario.js"></script>
	</body>
</html>