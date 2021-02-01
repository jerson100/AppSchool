<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="sidebar" class="sidebar responsive sidebar-fixed"
	data-sidebar="true" data-sidebar-scroll="true"
	data-sidebar-hover="true">
	<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
				</script>
	<ul class="nav nav-list" style="top: 0px;">
		<li class="open ${param.foco == '1' ? 'active':''}"><a
			href="inicio" style="display: flex; align-items: center;"> <i
				class="menu-icon fa fa-home" style="font-weight: 700"></i> <span
				class="menu-text"> Inicio </span>
		</a> <b class="arrow"></b></li>

		<c:if
			test="${sessionScope.usuarioSesion.idPerfil == 1 or sessionScope.usuarioSesion.idPerfil == 2
						               or sessionScope.usuarioSesion.idPerfil == 3}">
			<li class="open ${param.foco == '2' ? 'active':''}"><a
				href="cursos" style="display: flex; align-items: center;"> <i
					class="menu-icon fa fa-book" style="font-weight: 700"></i> <span
					class="menu-text"> Mis cursos </span>
			</a> <b class="arrow"></b></li>
		</c:if>
		<c:if
			test="${sessionScope.usuarioSesion.idPerfil == 1 or sessionScope.usuarioSesion.idPerfil == 2}">
			<li class="open ${param.foco == '3' ? 'active':''}"><a
				href="horario" style="display: flex; align-items: center;"> <i
					class="menu-icon fa fa-calendar" style="font-weight: 700"></i> <span
					class="menu-text"> Mi horario </span>
			</a> <b class="arrow"></b></li>
		</c:if>
		<c:if test="${sessionScope.usuarioSesion.idPerfil == 3}">
			<li class="open ${param.foco == '7' ? 'active':''}"><a
				href="mantenimientoNoticias"
				style="display: flex; align-items: center;"> <i
					class="menu-icon far fa-clipboard" style="font-weight: 700"></i> <span
					class="menu-text"> Mantenimiento Noticias</span>
			</a> <b class="arrow"></b>
			<li class="open ${param.foco == '8' ? 'active':''}"><a
				href="zonaEstudiante/misNotas"
				style="display: flex; align-items: center;"> <i
					class="menu-icon far fa-clipboard" style="font-weight: 700"></i> <span
					class="menu-text"> Mantenimiento Imágenes</span>
			</a> <b class="arrow"></b>
		</c:if>
		<c:if test="${sessionScope.usuarioSesion.idPerfil == 1}">
			<li class="open ${param.foco == '5' ? 'active':''}"><a
				href="zonaEstudiante/misNotas"
				style="display: flex; align-items: center;"> <i
					class="menu-icon far fa-clipboard" style="font-weight: 700"></i> <span
					class="menu-text"> Mis Notas </span>
			</a> <b class="arrow"></b></li>
		</c:if>
		<c:if
			test="${sessionScope.usuarioSesion.idPerfil == 2 or sessionScope.usuarioSesion.idPerfil == 3}">
			<li class="open ${param.foco == '6' ? 'active':''}"><a
				href="zonaDocente/registrarNotas"
				style="display: flex; align-items: center;"> <i
					class="menu-icon far fa-clipboard" style="font-weight: 700"></i> <span
					class="menu-text"> Registro de evaluaciones</span>
			</a> <b class="arrow"></b></li>
		</c:if>
	</ul>
	<!-- /.nav-list -->

	<!-- #section:basics/sidebar.layout.minimize -->
	<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
		<i class="ace-icon fa fa-angle-double-left"
			data-icon1="ace-icon fa fa-angle-double-left"
			data-icon2="ace-icon fa fa-angle-double-right"></i>
	</div>

	<!-- /section:basics/sidebar.layout.minimize -->
	<script type="text/javascript">
				try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
		</script>
</div>