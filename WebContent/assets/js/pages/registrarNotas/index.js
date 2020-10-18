'use-strict';
((document, window, undefinied)=>{
	document.addEventListener('DOMContentLoaded',e=>{
		const $grados = document.getElementById("grados");
		const $cursos = document.getElementById("cursos");
		const $formRegistroEvaluaciones = document.getElementById("registro-evaluaciones__form");
		const $bimestres = document.getElementById("bimestres");
		const $tableRegister = document.getElementById("table-register");
		
		$formRegistroEvaluaciones.addEventListener('change',async (evt)=>{
			const {name, value} = evt.target;
			const load = loader();
			switch(name){
				case "curso":
					await handleChangeCurso();
					break;
				case "grado":
					await handleChangeGrado();					
					break;
			}
			load.remove();
			restartValues();
		});
		
		const restartValues = () => {
			if($grados.value === 'none'){
				$bimestres.classList.add("hidden");
				$tableRegister.classList.add("hidden");
				$cursos.innerHTML = '<option value="none">Seleccione un curso...</option>';
			}else if($cursos.value === "none"){
				$bimestres.classList.add("hidden");
				$tableRegister.classList.add("hidden");
			}else{
				$bimestres.classList.remove("hidden");
				$tableRegister.classList.remove("hidden");
			}
		};
		
		const handleChangeGrado = async () => {
			try{
				const {cursos, message, estado} = await API.getCursos($grados.value);
				if(estado){
					addGrades(cursos);							
				}else{
					console.log("message: " + message);
				}				
			}catch(e){console.log(e)}
		};
		
		const handleChangeCurso = async () => {
			if($grados.value !== 'none' && 
			   $cursos.value !== 'none'){
				//hacemos la petición para obtener los bimestres...
				try{
					
				}catch(e){console.log(e)};				
			}
		};
		
		const addGrades = (grades=[]) => {
			if(! grades instanceof Array){
				throw new Error("grades debe de ser un array");
			}else{
				const fragment = document.createDocumentFragment();
				grades.forEach(g => {
					const {idSecCurPro, descCurso, profesor, icono} = g;
					const option = document.createElement("option");
					option.setAttribute("value", idSecCurPro);
					option.textContent = descCurso;
					fragment.appendChild(option);
				});
				$cursos.innerHTML = `<option value="none">Seleccione un curso...</option>`;
				$cursos.appendChild(fragment);
			}
		};
		
	});	
})(document, window);

