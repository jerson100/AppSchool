<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head lang="es">
		<meta charset="utf-8">
		<base href="/AppColegio2020/" target="_blank">
		<meta name="description" content="Mis cursos" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<title>Evaluaciones Parciales</title>
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
		<link rel="stylesheet" href="assets/css/components/modal.css">
		<link rel="stylesheet" href="assets/css/components/loader2.css">
		<link rel="stylesheet" href="assets/css/pages/courses/resourses2.css">
		<link rel="stylesheet" href="assets/css/components/modal2.css">
		<link rel="stylesheet" href="assets/css/utils/tables2.css">
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
				<jsp:param value="2" name="foco" />
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
						<jsp:param value="Registrar Notas" name="title"/>
					</jsp:include>
				</div>
				<div class="registro-evaluaciones">
					<h1 class="registro-evaluaciones__title">Registro de notas</h1>
					<form class="registro-evaluaciones__form" id="registro-evaluaciones__form">
						<div class="registro-evaluaciones__group">
							<label>Grado</label>
							<select name="grado" id="grados">
								<option value="none">Seleccione un grado...</option>
								<c:forEach items="${requestScope.secciones}" var="seccion">
									<option value="${seccion.idSecGraNiv}">${seccion.grado} ${seccion.nivel}</option>
								</c:forEach>
							</select>
						</div>
						<div class="registro-evaluaciones__group">
							<label>Curso</label>
							<select name="curso" id="cursos">
								<option value="none">Seleccione un curso...</option>
							</select>
						</div>
					</form>
					<div class="registro-evaluaciones__tags">
						<div class="tag-one-window">
							<ul class="tag-one-window__list" id="bimestres">
								<li class="tag-one-window__item je-btn je-btn--smaller">
									<span class="tag__text">I Trimestre</span>
								</li>
								<li class="tag-one-window__item je-btn je-btn--smaller">
									<span class="tag__text">II Trimestre</span>
								</li>
								<li class="tag-one-window__item je-btn je-btn--smaller">
									<span class="tag__text">III Trimestre</span>
								</li>
								<li class="tag-one-window__item je-btn je-btn--smaller">
									<span class="tag__text">IV Trimestre</span>
								</li>
								<li class="tag-one-window__item je-btn je-btn--smaller">
									<span class="tag__text">V Trimestre</span>
								</li>
							</ul>
							<div class="tag-one-window__data">
								<div class="grid je-scroll">
									<table class="grid__table grid__table--large" id="table-register" border>
										<thead class="grid__thead">
											<tr class="grid__col">
												<th colspan="4" rowspan="4"> 
													<div class="registro-evaluaciones__school">
														<img src="assets/img/static/logoColegio.jpg" class="registro-evaluaciones__logo">
														<span class="registro-evaluaciones__school-name">Escuela Privada Uribe School</span>
													</div>
												</th>
											</tr>
											<tr class="grid__row">
												<th class="grid__col" colspan="26" rowspan="1">
													Registro auxiliar de evaluación - Segundo Trimestre - 2020 (Grado /Año - Sección - Inicial)
												</th>
											</tr>
											<tr class="grid__row">
												<th class="grid__col" colspan="13"  rowspan="1">Profesor: Juan Manuel de l torre</td>
												<th class="grid__col" colspan="13" rowspan="1">Curso: Matemática</td>
											</tr>
											<tr class="grid__row">
												<th class="grid__col grid__col--color-blue" colspan="7" rowspan="1">Julio</th>
												<th class="grid__col" colspan="1" width="30"></th>
												<th class="grid__col grid__col--color-blue" colspan="7" rowspan="1">Agosto</th>
												<th class="grid__col" colspan="1"  width="30"></th>
												<th class="grid__col grid__col--color-blue" colspan="7" rowspan="1">Septiembre</th>
												<th class="grid__col" colspan="1"  width="30"></th>
												<th class="grid__col grid__col--color-blue" colspan="1" rowspan="2">PR F</th>
											</tr>
											<tr class="grid__row">
												<th class="grid__col" colspan="1"  width="40">Nº</th>
												<th class="grid__col" colspan="3"   width="400">Apellidos y Nombres</th>
												<th class="grid__col grid__col--note" colspan="1" width="40"> </th>
												<th class="grid__col grid__col--note" colspan="1" width="40"> </th>
												<th class="grid__col grid__col--note" colspan="1" width="40">PT</th>
												<th class="grid__col grid__col--note" colspan="1" width="40">PT</th>
												<th class="grid__col grid__col--note" colspan="1" width="40">PT</th>
												<th class="grid__col grid__col--note" colspan="1" width="40">EX</th>
												<th class="grid__col grid__col--note grid__col--color-blue" width="40" colspan="1">PR</th>
												<th class="grid__col grid__col--note" width="40" colspan="1"></th>
												<th class="grid__col grid__col--note" colspan="1" width="40"></th>
												<th class="grid__col grid__col--note" colspan="1" width="40"></th>
												<th class="grid__col grid__col--note" colspan="1" width="40">PT</th>
												<th class="grid__col grid__col--note" colspan="1" width="40">PT</th>
												<th class="grid__col grid__col--note" colspan="1" width="40">PT</th>
												<th class="grid__col grid__col--note" colspan="1" width="40">EX</th>
												<th class="grid__col grid__col--note grid__col--color-blue" colspan="1" width="40">PR</th>
												<th class="grid__col grid__col--note" colspan="1" width="40"></th>
												<th class="grid__col grid__col--note" colspan="1" width="40"></th>
												<th class="grid__col grid__col--note" colspan="1" width="40"></th>
												<th class="grid__col grid__col--note" colspan="1" width="40">PT</th>
												<th class="grid__col grid__col--note" colspan="1" width="40">PT</th>
												<th class="grid__col grid__col--note" colspan="1" width="40">PT</th>
												<th class="grid__col grid__col--note" colspan="1" width="40">EX</th>
												<th class="grid__col grid__col--note grid__col--color-blue" colspan="1" width="40">PR</th>
												<th class="grid__col grid__col--note" colspan="1" width="40"> </th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="i" begin="1" end="11">											
												<tr class="grid__row">
													<td class="grid__col" width="30">${i}</td>
													<td class="grid__col" colspan="3" width="200">Juan Manuel de los Santois</td>
													<td class="grid__col" width="40"></td>
													<td class="grid__col" width="40"></td>
													<td class="grid__col" width="40">15</td>
													<td class="grid__col" width="40">10</td>
													<td class="grid__col" width="40">16</td>
													<td class="grid__col" width="40"></td>
													<td class="grid__col grid__col--color-blue"  width="40"></td>
													<td class="grid__col" width="40"></td>
													<td class="grid__col" width="40"></td>
													<td class="grid__col" width="40"></td>
													<td class="grid__col"  width="40"></td>
													<td class="grid__col" width="40">11</td>
													<td class="grid__col" width="40">12</td>
													<td class="grid__col" width="40">10</td>
													<td class="grid__col grid__col--color-blue" width="40"></td>
													<td class="grid__col" width="40"></td>
													<td class="grid__col" width="40"></td>
													<td class="grid__col" width="40"></td>
													<td class="grid__col" width="40"></td>
													<td class="grid__col" width="40"></td>
													<td class="grid__col" width="40"></td>
													<td class="grid__col" width="40"></td>
													<td class="grid__col grid__col--color-blue" width="40"></td>
													<td class="grid__col" width="40"></td>
													<td class="grid__col grid__col--color-blue" width="40"></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>	
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--
		 <div class="je-modal je-scroll">
			<div class="je-modal__document je-modal__document--small">
				<div class="je-modal__content">
					<div class="je-modal__header">
						<p class="je-modal__title">Agregar notas</p>
					</div>
					<div class="je-modal__body">
						<div class="grid je-scroll">
							<table class="grid__table" border>
								<thead class="grid__thead">
									<tr class="grid__row">
										<th class="grid__col" width="40">Nº</th>
										<th class="grid__col" >Alumno</th>
										<th class="grid__col grid__col--color-blue" width="60">Nota</th>
									</tr>
								</thead>
								<tbody class="grid__tbody">
									<c:forEach var="i" begin="1" end="20">
										<tr class="grid__row">
											<td class="grid__col" width="40">
												1
											</td>
											<td class="grid__col">
												Ramírez Ortiz, Jerson Omar.
											</td>
											<td class="grid__col grid__col--color-blue" width="60">
												<input class="input" type="text" style="width: 40px">
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>-->
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
		<script src="assets/js/pages/registrarNotas/index.js"></script>
	</body>
</html>