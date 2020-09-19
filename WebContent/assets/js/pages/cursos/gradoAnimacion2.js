'use-strict';
((window,document,undefinied)=>{

	document.addEventListener("DOMContentLoaded", function(event) {
	
		
		let GradoDeAlumno = document.getElementById("GradoDeAlumno");
		
		let tutorDeAlumno = document.getElementById("tutorDeAlumno");
		
		let c = 0;
		
		setInterval(() => {
			
			tutorDeAlumno.classList.toggle("active");
			GradoDeAlumno.classList.toggle("active");
			
		}, 5000);
		
		
	
	});
	
})(window,document);
	