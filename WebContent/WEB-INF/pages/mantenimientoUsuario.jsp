<%@ page
	language="java"
	contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Mantenimiento de los alumnos</title>
<meta
	name="description"
	content="Mantenimiento de los alumnos" />
<meta
	name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<link
	rel="shortcut icon"
	href="assets/img/static/logoColegio.jpg"
	type="image/x-icon">
<!-- Inicio nav -->
<link
	rel="stylesheet"
	href="assets/css/utils/template/bootstrap.css" />
<link
	rel="stylesheet"
	href="assets/css/utils/template/font-awesome.css" />

<!-- text fonts -->
<link
	rel="stylesheet"
	href="assets/css/utils/template/ace-fonts.css" />



<link
	rel="stylesheet"
	href="assets/css/layout/layout.css">

<link
	rel="stylesheet"
	href="assets/css/theme/color.css">

<link
	rel="stylesheet"
	href="assets/css/components/button.css">

<link
	rel="stylesheet"
	href="assets/js/utils/modal/je-modal-style.css">

<link
	rel="stylesheet"
	href="assets/css/components/form.css">

<link
	rel="stylesheet"
	href="assets/css/pages/mantenimientoAlumno.css">
	<link rel="stylesheet" href="assets/js/utils/librerias/jqgrid/jquery-ui.css">
	<link rel="stylesheet" href="assets/js/utils/librerias/jqgrid/ui.jqgrid.css">
	
	<!-- ace styles -->
<link
	rel="stylesheet"
	href="assets/css/utils/template/ace.css"
	class="ace-main-stylesheet"
	id="main-ace-style" />

</head>
<body class="no-skin">
	<jsp:include page="../includes/header.jsp"></jsp:include>
	<div
		class="main-container"
		id="main-container">

		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>
		<jsp:include page="../includes/sidebar.jsp">
			<jsp:param value="4" name="foco"/>
		</jsp:include>
		<div class="main-content">
			<div
				class="breadcrumbs"
				id="breadcrumbs">
				<script type="text/javascript">
					try {
						ace.settings.check('breadcrumbs', 'fixed')
					} catch (e) {
					}
				</script>

				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i></li>
				</ul>

			</div>
			<div class="banner-container">
				<div class="je-container">
					<div class="je-item">
						<h1 style="text-align: center; padding: 3rem 0;">Mantenimiento
							de Estudiante</h1>
						<form action="" autocomplete="off" id="frmEstudiante">
							<div class="flex center" style="margin-bottom: 3rem;">
								<input class="je-btn smaller"
									type="button"
									value="Guardar" id="guardar">
									<input class="je-btn smaller"
									type="button"
									value="Eliminar" id="eliminar">
							</div>
							<div class="je-row-form">
								<div class="col-1">
									<div class="form-group">
										<label for="us">Usuario</label> <input
											type="text"
											name="us"
											id="us" autocomplete="new-password">
									</div>
								</div>
								<div class="col-2">
									<div class="form-group">
										<label for="pass">Contraseña</label> <input
											type="password"
											name="pass"
											id="pass" autocomplete="new-password">
									</div>
								</div>
								<div class="col-3">
									<div class="radio-container">
										<div class="form-group">
											<label for="alum">Alumno</label>
											<input type="radio" name="tipoPerfil" value="1" id="alum">
										</div>
										<div class="form-group">
											<label for="prof">Profesor</label>
											<input type="radio" name="tipoPerfil" value="2" id="prof">
										</div>
										<div class="form-group">
											<label for="inhabilitar">Inhabilitar</label> <input
												type="checkbox"
												name="inhabilitar"
												id="inhabilitar">
										</div>
									</div>
								</div>
							</div>
							<div class="je-row-form">
								<div class="col-1">
									<div class="form-group">
										<label for="searchDni">Dni: </label> <input
											type="text"
											name="dni" id="searchDni">
									</div>
								</div>
								<div class="col-70">
									<div class="form-group">
										<label for="nombres">Nombres: </label> <input
											type="text"
											name="nombres" id="nombres">
									</div>
								</div>
							</div>
							<div class="je-row-form">
								<div class="form-group">
									<label for="apellidpP">Apellido paterno: </label> <input
										type="text"
										name="apellidoP">
								</div>
								<div class="form-group">
									<label for="apellidoM">Apellido materno: </label> <input
										type="text"
										name="apellidoM">
								</div>
							</div>
							<div class="je-row-form">
								<div class="col-45">
									<div class="form-group">
										<label for="sexo">Sexo: </label> <select
											name="sexo"
											id="comboSexo">
										</select>
									</div>
								</div>
								<div class="col-45" id="seccion-container">
									<div class="form-group">
										<label for="seccion">Aula: </label> <select
											name="seccion"
											id="comboAula">
										</select>
									</div>
								</div>
							</div>
						</form>
						<div class="table-container" id="table-container"  style="display:none;">
							<div id="grid_container" align="center" style="width: 100%;overflow-x:auto;white-space:nowrap">
					            <table id="grillaRequerimiento" cellpadding="0" cellspacing="0"></table>
					            <div id="pager5"></div>
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

		<script src="assets/js/utils/template/bootstrap.js"></script>
		<script src="assets/js/utils/template/ace/elements.scroller.js"></script>
		<script src="assets/js/utils/template/ace/ace.js"></script>
		<script src="assets/js/utils/template/ace/ace.ajax-content.js"></script>
		<script src="assets/js/utils/template/ace/ace.touch-drag.js"></script>
		<script src="assets/js/utils/template/ace/ace.sidebar.js"></script>
		<script
			src="https://kit.fontawesome.com/56e0c4d4ed.js"
			crossorigin="anonymous"></script>
		<script src="assets/js/generic/close.js"></script>
		<script src="assets/js/utils/modal/je-modal.js"></script>
		<script src="assets/js/utils/librerias/jqgrid/jquery.jqGrid.src.js"></script>
		<script src="assets/js/utils/librerias/jqgrid/grid.locale-en.js"></script>
		<script src="assets/js/utils/librerias/jqgrid/grid.locale-es.js"></script>
		<script src="assets/js/pages/mantAlumno.js"></script>
		<script>
		
		let varDelLinea = [];
		let countNewRow = 1;
		
		let grid = $("#grillaRequerimiento"),
        pagerSelector = "#pager5",
        myAddButton = function (options) {
            grid.jqGrid('navButtonAdd', pagerSelector, options);
            grid.jqGrid('navButtonAdd', '#' + grid[0].id + "_toppager", options);
        },
        myDelButton = function (opciones) {
            grid.jqGrid ('navButtonAdd', pagerSelector, opciones);
            grid.jqGrid ('navButtonAdd', '#' + grid [0] .id + "_toppager", opciones);
        };
		
        $(window).on('resize.jqGrid',function(){
        	grid.jqGrid("setGridWidth",$("#table-container").width());
        });
        let lastsel;
		grid.jqGrid({
			datatype: "local",
			data: [],
		   	colNames:['Id','Aula', 'Curso'],
		   	colModel:[
		   		{name:'idSecCurPro',index:'idSecCurPro', width:60,editable:false,hidden:true},
		   		{name:'idSecGraNiv',index:'idSecGraNiv', width:200,editable:true,edittype:"select",formatter: 'select',editoptions:{value:"1:Primero;2:Segundo;3:Tercero"}},
		   		{name:'idCurso',index:'idCurso', width:200,editable:true}	
		   	],
		   	loadonce : true,
		   	emptyrecords: 'No hay resultados',
		   	search: true,
		   	pager: pagerSelector,
		   	loadtext: "Cargando...",
			rowNum:10,
		   	rowList:[10,20,30],
		    viewrecords: true,
		    rownumbers: true,
			height: "100%",
		    sortorder: "desc",
		    caption:"Lista de secciones",
		    cellEdit : true,
		    onSelectRow: function(id){
		    	if(id && id!==lastsel){ 
		    		$("#grillaRequerimiento").jqGrid('restoreRow',lastsel);
		    		$("#grillaRequerimiento").jqGrid('editRow',id,true);
		    		//grid.jqGrid('editRow',id);
					//grid.attr("disable",false);
		    		lastsel=id; 
		    	} 
		    },
		    cellsubmit: 'clientArray',
		    jsonReader : {
		    	root : 'rows',
		    	total : 'total',
		    	page: 'page',
		    	records: 'records',
		    	repeatitems: true,
		    	id: 'id'
		    }
		});
		
		$(window).triggerHandler('resize.jqGrid');
		
		//grid.jqGrid("filterToolbar",{searchOnEnter:false,operandTitle:'',searchOperators:false});
		grid.jqGrid("navGrid",pagerSelector,{add:false,edit:false,del:false,search:false,refresh:true, refreshicon:'ace-icon fa fa-refresh green'},
				{multipleSearch:true,overlay:false});
		
		myAddButton({
			caption : '',
			title: 'Agregar Fila',
			buttonicon : 'ace-icon fa fa-plus-circle purple',
			onClickButton: function(){
				let data = {idSecCurPro:0};
				let newId = 'jqg' + countNewRow;
				if(grid.addRowData(newId,data,'last')){
					grid.setSelection(newId);
				}
				grid.trigger('reloadGrid');
				++countNewRow;
			}
		});
		
		myDelButton({
			caption : '',
			title: 'Eliminar Fila',
			buttonicon : 'ace-icon fa fa-trash-o red',
			onClickButton: function(){
				let row_id = grid.jqGrid('getGridParam','selrow');
				if(row_id != null){
					let row_data = grid.jqGrid('getRowData',row_id);
					if(row_data.idSecCurPro == 0){
						grid.jqGrid('delRowData',row_id);
						grid.trigger('reloadGrid');
					}else{
						varDelLinea.push(row_data.idSecCurPro);
						//callback
						/*grid.jqGrid('delRowData',row_id);
						grid.trigger('reloadGrid');*/
					}
				}else{
					alert("Seleccione una fila para eliminar");
				}
			}
		});
		
		
		$("#guardar").click(()=>{
			
			console.log(grid.jqGrid("getGridParam","data"));
			
		});
		
		</script>
</body>
</html>