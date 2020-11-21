<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head lang="es">
		<meta charset="utf-8">
		<base href="../">
		<meta name="description" content="Mis notas" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<title>Mis notas</title>
		<link rel="shortcut icon" href="assets/img/static/logoColegio.jpg"
			type="image/x-icon">
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
		<link rel="stylesheet" href="assets/css/layout/layout2.css">
		<link rel="stylesheet" href="assets/css/theme/color2.css">
		<link rel="stylesheet" href="assets/css/components/button2.css">
		<link rel="stylesheet" href="assets/css/components/scroll2.css">
		<link rel="stylesheet" href="assets/css/utils/animation2.css">
		<link rel="stylesheet" href="assets/css/components/table2.css">
		<link rel="stylesheet" href="assets/js/utils/modal/je-modal-style2.css">
		<link rel="stylesheet" href="assets/css/components/modal.css">
		<link rel="stylesheet" href="assets/css/components/loader2.css">
		<link rel="stylesheet" href="assets/css/pages/courses/resourses2.css">
		<link rel="stylesheet" href="assets/css/components/modal2.css">
		<!--<link rel="stylesheet" href="assets/css/utils/tables2.css">-->
		<link rel="stylesheet" href="assets/css/components/goUp2.css">
		<link rel="stylesheet" href="assets/css/pages/registroEvaluaciones.css">
		
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
				<jsp:param value="5" name="foco" />
			</jsp:include>
			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed')
						} catch (e) {
						}
					</script>
					<jsp:include page="../../includes/breadcrumb.jsp">
						<jsp:param value="Mis notas" name="title"/>
						<jsp:param value="zonaEstudiante/misNotas" name="href"/>
					</jsp:include>
				</div>
				<div class="registro-evaluaciones">
					<h1 class="registro-evaluaciones__title">Mis notas</h1>
					<!-- <form class="registro-evaluaciones__form" id="registro-evaluaciones__form">
						<div class="registro-evaluaciones__group">
							<label>Grado</label>
							<select name="grades" id="grades" disabled=true>
								<option value="none">Seleccione un grado...</option>
								<option value="1" selected>${seccion.grado}>"Option"</option>
							</select>
						</div>
						<div class="registro-evaluaciones__group">
							<label>Curso</label>
							<select name="courses" id="courses">
								<option value="none">Seleccione un curso...</option>
								<c:forEach items="${requestScope.cursos}" var="curse">
									<option 
										data-profesor="${curse.profesor}"
										data-descCurso="${curse.descCurso}"
										data-idSecCurPro="${curse.idSecCurPro}"
										value="${curse.idSecCurPro}">${curse.descCurso}</option>
								</c:forEach>
							</select>
						</div>
					</form>-->
					<div class="registro-evaluaciones__tags">
						<div class="tag-one-window">
							<ul class="tag-one-window__list" id="bimestres">
							<c:if test="${requestScope.ciclos != null}">
								<c:forEach var="item" items="${requestScope.ciclos}">
									<li class="tag-one-window__item je-btn je-btn--smaller" data-id="${item.idCiclo}">
										${item.descCiclo}
									</li>
								</c:forEach>
							</c:if>
							</ul>
							<div class="tag-one-window__data hidden">
								<div class="grid je-scroll" id="tableRegister" style="max-height: calc(56vh);"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@include file="../../includes/footer.jsp"%>
		<script type="text/javascript">
			window.jQuery
					|| document
							.write("<script src='assets/js/utils/template/jquery.js'>"
									+ "<"+"/script>");
		</script>
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
		<script src="assets/js/utils/Api2.js"></script>
		<script src="assets/js/utils/modal/je-modal2.js"></script>
		<script src="assets/js/utils/modal/modalMessage2.js"></script>
		<script src="assets/js/pages/cursos/gradoAnimacion2.js"></script>
		<script src="assets/js/generic/loader2.js"></script>
		<script src="assets/js/utils/element2.js"></script>
		<script src="assets/js/generic/close2.js"></script>
		<script src="assets/js/pages/cursos/tags2.js"></script>
		<script src="assets/js/utils/goUp2.js"></script>
		<script src="assets/js/pages/registrarNotas/modalTableRegister.js"></script>
		<script src="assets/js/pages/registrarNotas/tableRegister.js"></script>
		<script src="assets/js/pages/misNotas/index.js"></script>
	</body>
</html>