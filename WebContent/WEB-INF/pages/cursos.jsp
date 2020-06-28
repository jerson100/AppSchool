<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cursos</title>
<meta name="description" content="Mis cursos" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
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
<!--
	<link rel="stylesheet" href="assets/css/base/base.css">
  -->

<link rel="stylesheet" href="assets/css/layout/layout.css">

<link rel="stylesheet" href="assets/css/theme/color.css">

<link rel="stylesheet" href="assets/css/components/button.css">

<link rel="stylesheet" href="assets/css/components/scroll.css">

<link rel="stylesheet" href="assets/css/utils/animation.css">

<link rel="stylesheet" href="assets/css/pages/courses.css">

<link rel="stylesheet" href="assets/css/components/table.css">

<link rel="stylesheet" href="assets/js/utils/modal/je-modal-style.css">

<link rel="stylesheet" href="assets/css/components/loader.css">

<link rel="stylesheet" href="assets/css/pages/courses/resourses.css">

<link rel="stylesheet" href="assets/css/components/modal.css">

<link rel="stylesheet" href="assets/css/pages/courses/tabs.css">

<link rel="stylesheet" href="assets/css/utils/tables.css">

<link rel="stylesheet" href="assets/css/components/goUp.css">

</head>
<body class="no-skin je-scroll">
	<jsp:include page="../includes/header.jsp"></jsp:include>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>
		<jsp:include page="../includes/sidebar.jsp">
			<jsp:param value="2" name="foco" />
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
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i></li>
					<li><a href="cursos" style="font-size: 17px;">Mis cursos</a></li>
				</ul>
				<div class="nav-search"
					style="margin: 7.5px 0px 0 22px; top: 0; width: 500px;">
					<span id="GradoDeAlumno" class="animateText active"><c:out
							value="${requestScope.grado}"></c:out></span> <span
						class="teacher animateText" id="tutorDeAlumno"><c:out
							value="${requestScope.tutor}"></c:out></span>
				</div>
			</div>

			<div class="je-container wrapper-main" style="max-width: 100%;">

				<c:if
					test="${requestScope.tipoPerfil == 2 || requestScope.tipoPerfil == 3}">
					<div class="left-container">
						<div class="je-item left-wrapperr">
							<h2 class="title-grados title">Secciones</h2>
							<div class="grado-container je-scroll" id="gradoContainer">
								<c:forEach items="${requestScope.listaSeccion}" var="seccion">
									<div class="grado-item"
										data-idSecGraNiv="${seccion.idSecGraNiv}">
										<span class="grado-name" data-idSecGraNiv="${seccion.idSecGraNiv}"> ${seccion.grado}
											${seccion.nivel} </span>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</c:if>
				<div class="right-container" <c:if test="${requestScope.tipoPerfil==1}"><c:out value="style=width:100%;"></c:out></c:if>>				
					<div class="je-item">
						<h3 class="title" style="display: none">Cursos:</h3>
						<div class="courses-container" id="coursesContainer">
							<c:forEach var="curso" items="${requestScope.listaCursos}">
								<div class="wrapper-course">
									<div class="item-course">
										<div class="icon-course">
											<i data-profesor="${curso.profesor}"
											   data-idSecCurPro="${curso.idSecCurPro}" 
											   class="menu-icon ${curso.icono}"></i>
										</div>
										<span data-profesor="${curso.profesor}"
											  data-idSecCurPro="${curso.idSecCurPro}"
											  class="text-course-name">${curso.descCurso}</span>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="je-item">
						<div class="tables-container">
							<!-- <h4 class="title">Contenidos:</h4>-->
							<p id="msg"
								style="font-size: 30px; margin: 4rem 0; text-align: center;"></p>
							<c:if test="${requestScope.tipoPerfil != 2}">
								<div id="nombreProfesorSeleccionado"
									style="font-size: 14px; font-weight: 800; margin-bottom: 1rem">
								</div>
							</c:if>
							
								<c:if test="${requestScope.tipoPerfil != 1}">
									<div class="actions-container flex spacebetween center"
									id="actionscontainer"
									style="margin-bottom: 1rem;">
									<div class="action-crud">
										<ul class="action-list flex">
											<li><span
												class="je-btn dark-school icon smaller auto fas fa-plus"
												id="addCharacteristic" aria-hidden="true"></span></li>
											<li>
											<!--<span
												class="je-btn dark-school icon smaller auto fas fa-sync"
												id="btnUpdateTheme" aria-hidden="true"></span></li>-->
										</ul>
									</div>
								</div>
								</c:if>
								<div class="tabs">
									<ul class="tabs__list">
										<li class="tabs__item tabs__item--active">
											<i class="tabs__icon fas fa-chalkboard-teacher"></i>
											<a href="#tableContent" class="tabs__link">Contenidos</a>
										</li>
										<li class="tabs__item">
											<i class="tabs__icon fas fa-record-vinyl"></i>
											<a href="#tableClass" class="tabs__link">Clases en vivo</a>
										</li>
										<li class="tabs__item">
											<i class="tabs__icon fas fa-book"></i>
											<a href="#tableWork" class="tabs__link">Tareas y trabajos</a>
										</li>
									</ul>
									<div class="tabs__search">
										<input class="tabs__input-search" type="text" id="inputSearch">
										<span id="teclaP"></span> <i
											class="tabs__icon-search fa fa-search"></i>
									</div>
									<span class="tabs__separator"></span>
									<div class="tabs__section tabs__section--active" data-table="tableContent">
										<table class="table-data__table"></table>
									</div>
									<div class="tabs__section" data-table="tableClass">
										<table class="table-data__table"></table>
									</div>
									<div class="tabs__section" data-table="tableWork">
										<table class="table-data__table"></table>
									</div>
								</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Modal  - Resourse -->

		<div class="mod mod--resource">
			<div class="mod__inner">
				<div class="resource">
				    <!--<div class="resource__triang"></div>-->
					<i class="mod__close mod__close--light fas fa-close"></i>
					<div class="resource__header">
						<p class="resource__title">Seleccione una opción</p>
					</div>
					<div class="resource__body">
						<div class="resource__wave" style="overflow: hidden;">
							<svg viewBox="0 0 500 150" preserveAspectRatio="none"
								style="height: 100%; width: 100%;">
								<path
									d="M208.08,0.00 C73.08,104.11 236.73,62.66 200.80,150.00 L0.00,150.00 L0.00,0.00 Z"
									class="resource__wave-path"></path></svg>
						</div>
						<div class="tag">
							<div class="tag__wrapper">
								<div class="tag__inner">
									<ul class="tag__list">
										<li class="tag__item tag__item--red flex--center" data-num=1
											data-title="Agregar Contenido" data-color="#ff3c32"><a
											class="tag__link" href="#" data-num=1
											data-title="Agregar Contenido" data-color="#ff3c32">Contenido</a>
										</li>
										<li class="tag__item tag__item--blue flex--center" data-num=2
											data-title="Agregar Clases" data-color="#007bdf"><a
											class="tag__link" href="#" data-num=2
											data-title="Agregar Clases" data-color="#007bdf">Clases
												en vivo</a></li>
										<li class="tag__item tag__item--yellow flex--center"
											data-num=3 data-title="Agregar Tareas" data-color="#fba905">
											<a class="tag__link" href="#" data-num=3
											data-title="Agregar Tareas" data-color="#fba905">Tarea y
												Trabajos</a>
										</li>
									</ul>
								</div>
								<div class="tag__section tag__section--content">
									<i class="fas fa-arrow-left tag__return"></i>
									<form class="tag__form">
										<div class="tag__group">
											<label class="tag__label" for="descContenido">Nombre:</label> <input
												class="tag__input" type="text" name="descContenido" id="nombre" required>
										</div>
										<div class="tag__group">
											<label class="tag__label" for="link">Link:</label> <input
												class="tag__input" type="text" name="link" id="link" required>
										</div>
										<div class="tag__group">
											<label class="tag__label" for="replicartodos">Replicar:</label> <input
												class="tag__input" type="checkbox" name="replicartodos"
												id="replicar">
										</div>
									    <input type="hidden" name="idContenido">
									    <input type="hidden" name="action" value="agregarContenido">
									    <input type="hidden" name="codContenido">
										<div class="tag__buttons flex--center">
											<button
												class="tag__btn tag__btn--first je-btn je-btn--smaller"
												type="submit">Guardar</button>
											<button class="tag__btn tag__btn--last je-btn je-btn--smaller" type="reset">Limpiar</button>
										</div>
									</form>
								</div>
								<div class="tag__section tag__section--class">
									<i class="fas fa-arrow-left tag__return"></i>
									<form class="tag__form">
										<div class="tag__group">
											<label class="tag__label" for="descripcion">Título:</label> <input
												class="tag__input" type="text" name="descClase" id="descripcion" required>
										</div>
										<div class="tag__group">
											<label class="tag__label" for="link2">Link:</label> <input
												class="tag__input" type="text" name="link" id="link2" required>
										</div>
										<div class="tag__group">
											<label class="tag__label" for="fechaClase">Fecha:</label> <input
												class="tag__input" type="date" name="fechaClase" id="fechaClase" required>
										</div>
										<div class="tag__group">
											<label class="tag__label" for="horaClaseS">Hora:</label> <input
												class="tag__input" type="time" name="horaClase" id="horaClaseS" required>
										</div>
										<input type="hidden" name="action" value="agregarclase">
										<input type="hidden" name="idClass">
										<input type="hidden" name="codClase">
										<div class="tag__group">
											<label class="tag__label" for="replicartodos2">Replicar:</label> 
											<input class="tag__input" type="checkbox" name="replicartodos" id="replicartodos2">
										</div>
										<div class="tag__buttons flex--center">
											<button
												class="tag__btn tag__btn--first je-btn je-btn--smaller"
												type="submit">Guardar</button>
											<button class="tag__btn tag__btn--last je-btn je-btn--smaller" type="reset">Limpiar</button>
										</div>
									</form>
								</div>
								<div class="tag__section tag__section--work">
									<i class="fas fa-arrow-left tag__return"></i>
									<form class="tag__form">
										<div class="tag__group">
											<label class="tag__label" for="descTrabajo">Nombre:</label> <input
												class="tag__input" type="text" name="descTrabajo" id="descTrabajo">
										</div>
										<div class="tag__group">
											<label class="tag__label" for="file3">Archivo:</label> 
											<div class="tag__file">
												<button class="tag__file-button je-btn je-btn--fill" type="button">
													<i class="fas fa-upload"></i>
												</button>
												<span class="tag__file-name"></span>
												<input class="tag__input" style="display:none;" type="file" name="file" id="file3">
												<i class="tag__file-delete tag__file-delete--active fas fa-trash-alt"></i>
											</div>
										</div>
										<div class="tag__group">
											<label class="tag__label" for="fechaIni">Fecha
												Inicio:</label> <input class="tag__input" type="datetime-local"
												name="fechaIni" id="fechaIni">
										</div>
										<input type="hidden" name="action" value="agregarTrabajo">
										<div class="tag__group">
											<label class="tag__label" for="fechaFin">Fecha Fin:</label>
											<input class="tag__input" type="datetime-local"
												name="fechaFin" id="fechaFin">
										</div>
										<div class="tag__group">
											<label class="tag__label" for="isFlagLimite">Fuera de
												fecha:</label> <input class="tag__input" type="checkbox"
												name="isFlagLimite" id="isFlagLimite">
										</div>
										<div class="tag__group">
											<label class="tag__label" for="diasLimite">Días:</label> <input
												class="tag__input" type="number" name="diasLimite" id="diasLimite">
										</div>
										<div class="tag__group">
											<label class="tag__label" for="replicartodos3">Replicar:</label> 
											<input class="tag__input" type="checkbox" name="replicartodos" id="replicartodos3">
										</div>
										<input type="hidden" name="idTrabajo" value="">
										<input type="hidden" name="codTrabajo" value="">
										<input type="hidden" name="rutaArchivo" value="">
										<input type="hidden" name="eliminarArchivo" value="false">
										<div class="tag__buttons flex--center">
											<button
												class="tag__btn tag__btn--first je-btn je-btn--smaller"
												type="submit">Guardar</button>
											<button class="tag__btn tag__btn--last je-btn je-btn--smaller" type="reset">Limpiar</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
	
	<%@include file="../includes/footer.jsp"%>
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
	<script src="assets/js/utils/Api.js"></script>
	<script src="assets/js/utils/modal/je-modal.js"></script>
	<script src="assets/js/utils/modal/modalMessage.js"></script>
	<script src="assets/js/pages/cursos/gradoAnimacion.js"></script>
	<script src="assets/js/generic/loader.js"></script>
	<!-- <script src="assets/js/pages/cursos/cursos.js"></script>-->
	<script src="assets/js/utils/element.js"></script>
	<script src="assets/js/pages/cursos/courses.js"></script>
	<script src="assets/js/utils/tables.js"></script>
	<script src="assets/js/generic/close.js"></script>
	<script src="assets/js/pages/cursos/tags.js"></script>
	<script src="assets/js/utils/youtube.js"></script>
	<script src="assets/js/utils/goUp.js"></script>
</body>
</html>