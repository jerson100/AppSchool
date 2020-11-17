'use-strict';
((document, window, undefinied)=>{
	document.addEventListener('DOMContentLoaded',e=>{
		const $grades = document.getElementById("grades");
		const $courses = document.getElementById("courses");
		const $formRegistroEvaluaciones = document.getElementById("registro-evaluaciones__form");
		const $tagOne = document.querySelector(".tag-one-window");
		const $tagOneList = $tagOne.firstElementChild;
		const $tagData = $tagOne.lastElementChild;
		const $tableRegisterContainer = document.getElementById("tableRegister");
		
		//curso y grado seleccionados...
		let selections = {
			grades: null,
			courses: null
		};
		
		let courses = [];
		
		$tagOneList.addEventListener("click",async e=>{
			const {target} = e;
			if(target.tagName==="LI"){
				ELEMENTS.removeClass(Array.from($tagOneList.children),["tag-one-window__item--current"]);
				target.classList.add("tag-one-window__item--current");
				$tagData.classList.remove("hidden");
				$tableRegisterContainer.innerHTML =  "";
				let load = loader();
				try{
					const [{data:dataAlumnos},{data:dataPeriodos}] = await Promise.all([
						API.ALUMNO.NOTAS.get({
							idCiclo: target.dataset.id,
							idSecCurPro: selections["courses"].idSecCurPro,
							action: "listarMapNotes"
						}),
						API.PERIODO.get(target.dataset.id,selections["courses"].idSecCurPro)
					]);
					new TableRegister($tableRegisterContainer,dataAlumnos, dataPeriodos,
						{
							courses:selections["courses"], 
							grades:selections["grades"],
							ciclo:{value: target.textContent,id:target.dataset.id}
						}
					);
				}catch(e){
					console.log(e);
				}finally{
					load.remove();
				}
			}
		});
		
		$formRegistroEvaluaciones.addEventListener('change',async (evt)=>{
			const {name, value} = evt.target;
			switch(name){
				case "grades":
					selections[name]= {
						id: value,
						value: evt.target.children[evt.target.selectedIndex].innerHTML
					},
					await handleChangeGrado();
					break;
				case "courses":
					selections[name]= courses.find(c => c.idSecCurPro == value);
					handleChangeCourse();
					break;
			}
			restartValues();
		});
		
		const restartValues = () => {
			if($grades.value === 'none'){
				$tagOneList.classList.add("hidden");
				$courses.innerHTML = '<option value="none">Seleccione un curso...</option>';
			}else if($courses.value === "none"){
				$tagOneList.classList.add("hidden");
			}else{
				$tagOneList.classList.remove("hidden");
			}
			$tagData.classList.add("hidden");
		};
		
		const handleChangeCourse = () => {
			
		}
		
		const handleChangeGrado = async () => {
			const load = loader();
			try{
				const {cursos, message, estado} = await API.getCursos($grades.value);
				if(estado){
					courses =  cursos || [];
					drawCourses();
				}else{
					console.log("message: " + message);
				}				
			}catch(e){console.log(e)}
			load.remove();
		};
		
		const drawCourses = () => {
			const fragment = document.createDocumentFragment();
			courses.forEach(g => {
				const {idSecCurPro, descCurso} = g;
				const option = document.createElement("option");
				option.setAttribute("value", idSecCurPro);
				option.textContent = descCurso;
				fragment.appendChild(option);
			});
			$courses.innerHTML = `<option value="none">Seleccione un curso...</option>`;
			$courses.appendChild(fragment);
		};
		
	});	
	
})(document, window);

