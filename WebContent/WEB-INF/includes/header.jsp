
<!-- #section:basics/navbar.layout -->
<div
	id="navbar"
	class="navbar navbar-default ace-save-state navbar-fixed-top">
	<script type="text/javascript">
		try {
			ace.settings.check('navbar', 'fixed')
		} catch (e) {
		}
	</script>

	<div class="navbar-container" id="navbar-container">
		<button
			type="button"
			class="navbar-toggle menu-toggler pull-left"
			id="menu-toggler"
			data-target="#sidebar">
			<span class="sr-only">Toggle sidebar</span> 
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>

		<!-- /section:basics/sidebar.mobile.toggle -->
		<div class="navbar-header pull-left">
			<a href="inicio" class="navbar-brand"> 
				<small style="display: flex; align-items: center;"> 
					<img src="assets/img/static/logoColegio.jpg" alt="Logo del colegio Uribe School"
						style="height: 2.7rem"> <span style="margin-left: 1rem;">
						</span>URIBE SCHOOL</span>
				</small>
			</a>
		</div>

		<!-- #section:basics/navbar.dropdown -->
		<div class="navbar-buttons navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">
				
				<!-- #section:basics/navbar.user_menu -->
				<li class="light-blue">
					<a data-toggle="dropdown" href="#"  class="dropdown-toggle"> <img
						class="nav-user-photo"
						src="assets/img/static/defaultUser.png"
						alt="Jason's Photo" /> <span class="user-info"> <small>Bienvenido,</small>
							${sessionScope.usuarioSesion.nombres}
						</span> <i class="ace-icon fa fa-caret-down"></i>
					</a>

					<ul	class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
						<!--<li>
							<a href="#"> <i class="ace-icon fa fa-cog"></i>
								Configuración
							</a>
						</li>
						<li>
							<a href="profile.html"> <i
								class="ace-icon fa fa-user"></i> Perfil
							</a>
						</li>

						<li class="divider"></li>
						-->
						<li>
							<a id="cerrarSesion" style="cursor:pointer;"> <i
								class="ace-icon fa fa-power-off"></i> Cerrar Sesión
							</a>
						</li>
					</ul>
				</li>
			
			</ul>
		</div>
	</div>
</div>