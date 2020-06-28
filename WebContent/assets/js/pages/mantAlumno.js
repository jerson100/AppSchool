'use-strict';
((window,document,undefinied)=>{
	
	let radioAlumno = document.getElementById("alum");
	let radioProfesor = document.getElementById("prof");
	let table = document.getElementById("table-container");
	let aulasContainer = document.getElementById("seccion-container");
	let comboAula = document.getElementById("comboAula");
	let comboSexo = document.getElementById("comboSexo");
	
	const addListener = () =>{
		radioAlumno.addEventListener("change",()=>{
			table.style.display = "none";
			aulasContainer.style.display = "block";
		});
		radioProfesor.addEventListener("change",()=>{
			table.style.display = "block";
			aulasContainer.style.display = "none";
		});
	};
	
	const loadData = async () =>{
		try{
			let sexos = await load('sexo');
			let aulas = await load('aula');
			fillSexo(sexos.sexos);
			fillAulas(aulas.aulas);
		}catch(error){
			console.log(error);
		}
	};
	
	const load = async (url)=>{
		let response = await fetch(url);
		if(!response.ok){
			throw response;
		}else{
			let json = await response.json();
			return json;
		}
	};
	
	const fillSexo = (sexos)=>{
		comboSexo.innerHTML = '';
		let fragment = document.createDocumentFragment(),
			option;
		option = document.createElement("option");
		option.textContent = "--SELECCIONE--";
		option.value = 0;
		comboSexo.appendChild(option);
		sexos.forEach(sexo=>{
			option = document.createElement("option");
			option.textContent = sexo.descSexo;
			option.value = sexo.idSexo;
			fragment.appendChild(option);
		});
		comboSexo.appendChild(fragment);
	};
	
	const fillAulas = (aulas)=>{
		comboAula.innerHTML = '';
		let fragment = document.createDocumentFragment(),
			option;
		option = document.createElement("option");
		option.textContent = "--SELECCIONE--";
		option.value = 0;
		comboAula.appendChild(option);
		aulas.forEach(aula=>{
			option = document.createElement("option");
			option.textContent = aula.mensaje;
			option.value = aula.idSecGraNiv;
			fragment.appendChild(option);
		});
		comboAula.appendChild(fragment);
	};
	
	const init = ()=>{
		
		addListener();
		
		loadData();
		
	};
	
	init();
	
})(window,document);