<ul class="breadcrumb">
	<li>
		<i class="ace-icon fa fa-home home-icon"></i>
	</li>
	<li>
		<a href="cursos" style="font-size: 17px;">${param.title}</a>
	</li>
</ul>
<div class="nav-search" style="margin: 7.5px 0px 0 22px; top: 0; width: 500px;">
	<span id="GradoDeAlumno" class="animateText active">
		<c:out value="${requestScope.grado}"></c:out>
	</span> 
	<span class="teacher animateText" id="tutorDeAlumno">
		<c:out value="${requestScope.tutor}"></c:out>
	</span>
</div>