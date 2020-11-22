<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.animateText {
	opacity: 0;
	transition: opacity 1s;
	position: absolute;
	right: 0px;
	font-size: 17px
}

.animateText.active {
	opacity: 1;
}

.teacher {
	display: none;
}

@media screen and (min-width: 700px) {
	.teacher {
		display: inline;
	}
}
</style>
<ul class="breadcrumb">
	<li><i class="ace-icon fa fa-home home-icon"></i></li>
	<li><a href="${param.href}" style="font-size: 17px;">${param.title}</a>
	</li>
</ul>
<div class="nav-search"
	style="margin: 7.5px 0px 0 22px; top: 0; width: 500px;">
	<span id="GradoDeAlumno" class="animateText active"> <c:out
			value="${sessionScope.usuarioSesion.grado}"></c:out>
	</span> <span class="teacher animateText" id="tutorDeAlumno"> <c:out
			value="${sessionScope.usuarioSesion.tutor}"></c:out>
	</span>
</div>