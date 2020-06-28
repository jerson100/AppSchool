'use-strict';
((window,document,undefinied)=>{
	let loadIni = loader();
	document.addEventListener("DOMContentLoaded", function(event) {
		loadIni.remove();
		clickCourse();
		addListenerActions();
	});
	
	let coursesContainer = document.getElementById("coursesContainer");
	let gradoContainer = document.getElementById("gradoContainer");
	
	/********************Table*************************/
	let tableContenidoContainer = document.getElementById("tableContainer")
	let tableContenido = document.getElementById("tablethemes");
	let tableContenido_tbody = tableContenido.querySelector("tbody");
	let inputSearch = document.getElementById("inputSearch");
	
	//variables para mantener en memoria 
	let contenidos = [];
	let themesSearch = [];
	
	//Variables necesarias para la navegación en tabla
	
	let pageInit = 1;
	let pageCount = 1;
	let rowsCount = 10;
	
	//Botones de navegación - Tabla
	
	let firstBtn = document.getElementById("firstBtn");
	let previousBtn = document.getElementById("previousBtn");
	let nextBtn = document.getElementById("nextBtn");
	let lastBtn = document.getElementById("lastBtn");
	
	//información de la tabla
	
	let infoNavPage = document.getElementById("info-nav__pages");
	let infoNavCount = document.getElementById("info-nav__count");
	
	//Botones para agregar y actualizar las filas de la tabla
	
	let btnAddTheme = document.getElementById("addThemeBtn");
	let btnUpdateTheme = document.getElementById("btnUpdateTheme");
	
	//Para mostrar mensajes - Curso 
	let messageContainer = document.getElementById("msg");
	
	let actionsContainer =document.getElementById("actionscontainer");
	
	//ID del curso que seleccionamos
	let idCourseSelect;
	let nameTeacher;
	
	let profesorSeleccionado = document.getElementById("nombreProfesorSeleccionado");
	
	const clearVarTable = ()=>{
		pageInit = 1;
		pageCount = 1;
		rowsCount = 10;
	};
	
	//Nos devuelve el contenido del curso seleccionado
	const getContenido = async (idSecCurPro) => {
		let l  = loader();
		try{
			let d = await fetch(`cursos?action=listar&idSecCurPro=${idSecCurPro}`);
			if(!d.ok){
				location.href = "login";
			}else{
				let json = await d.json();
				return json;
			}
		}catch (e) {
			throw e;
		}finally{
			l.remove();
		}
	};
	
	const clickCourse = ()=>{
		if(coursesContainer){
			let courseItems,idSecCurPro,parentElement;
			coursesContainer.addEventListener('click',async e=>{
				if(e.target.tagName == 'I' || e.target.classList.contains("text-course-name")){
					courseItems = Array.from(coursesContainer.querySelectorAll(".icon-course"));
					if(actionsContainer){
						actionsContainer.style.display  = "block";
					}
					parentElement = e.target.parentElement.parentElement;
					idSecCurPro = parentElement.dataset.idseccurpro;
					nameTeacher = parentElement.dataset.profesor
					courseItems.forEach((it)=>{it.classList.remove("selected");});
					if(e.target.classList.contains("text-course-name")){
						parentElement.firstElementChild.classList.add("selected");
					}else{
						e.target.parentElement.classList.add("class","selected");
					}
					idCourseSelect = idSecCurPro; //almacenamos el id del curso seleccionado
					clearVarTable();//limipiamos la tabla
					await updateTable(idSecCurPro);
					tableContenidoContainer.classList.add("active");
					tableContenidoContainer.scrollIntoView({block: "start", behavior: "smooth"});
					tableContenidoContainer.style.animation = "opac 1s 1 ease"
					setTimeout(() => {
						tableContenidoContainer.style.animation = "";
					}, 1000);
				}
			});
		}
	};
	
	const updateTable = async (idSecCurPro,profesor="")=>{
		if(profesorSeleccionado){
			profesorSeleccionado.textContent = `Docente: ${nameTeacher}`;
		}
		try{
			let data = await getContenido(idSecCurPro);
			contenidos = data.contenidos;
			themesSearch = contenidos;
			infoNavCount.textContent = contenidos.length;
			if(contenidos.length == 0){
				tableContenidoContainer.style.display = "none";
				messageContainer.style.display = "block";
				messageContainer.textContent = data.mensaje;
			}else{
				messageContainer.style.display = "none";
				tableContenidoContainer.style.display = "block";
				updateDataTable();
			}
		}catch(e){
			location.href="login";
		}
	};
	
	const updateDataTable = ()=>{
		tableContenido_tbody.innerHTML = "";
		tableContenido_tbody.appendChild(getRows());
		pageCount = getCountPages();
	};
	
	const getRows = ()=> {
		let fragment = document.createDocumentFragment();
		if(themesSearch){
			let tr,theme;
			let rowIni = (pageInit - 1) * rowsCount;
			let rowFin = rowIni + rowsCount;
			infoNavPage.textContent = `${themesSearch.length == 0 ? 0 : rowIni + 1} - ${rowFin > themesSearch.length ? themesSearch.length : rowFin}`;
			let count = 0;
			let the = document.getElementById("thead");
			let countCols = the.querySelectorAll("th").length;
			let cad = "";
			for(let i = rowIni; i < themesSearch.length && i < rowFin; i++){
				theme = themesSearch[i];
				tr = document.createElement("tr");
				if(countCols == 4){//para que no se muestre el botón eliminar and actualizar
					cad = `
						<td style="width: 40px">
							<div class="actions-r-d">
								<span class="edit-row">
									<i class="fas fa-edit" data-idTheme="${theme.idContenido}" data-codContenido="${theme.codContenido}"></i>
								</span>
								<span class="close-row">
									<i class="fas fa-trash-alt" data-idTheme="${theme.idContenido}" data-codContenido="${theme.codContenido}"></i>
								</span>
							</div>
						</td>
						
					`;		
				}else{
					cad = "";
				}
				tr.innerHTML = `
					<td>${++count}</td>
					<td>${theme.descContenido}</td>
					<td><a href="${theme.link}" target="_blank">${theme.link}</a></td>
					${cad}
				`;
				fragment.appendChild(tr);
			}
		}
		return fragment;
	};
	
	const searchThemes = (text) =>{
		let sThem = [];
		text = text.toLowerCase();
		contenidos.forEach((theme)=>{
			if(theme.descContenido.toLowerCase().indexOf(text)>=0){
				sThem.push(theme);
			}
		});
		return sThem;
	};
	
	const addListenerActions = ()=>{
		addListenerInputSearch();
		if(gradoContainer){
			addListenerClickGrado();
		}
		addListenerClickTable();
		firstBtn.addEventListener('click',e=>{
			pageInit = 1;
			updateDataTable();
		});
		previousBtn.addEventListener('click',e=>{
			if(pageInit > 1){
				pageInit--;
				updateDataTable();
			}
		});
		nextBtn.addEventListener('click',e=>{
			if(pageInit < getCountPages()){
				pageInit ++;
				updateDataTable();
			}
		});
		lastBtn.addEventListener('click',e=>{
			pageInit = pageCount;
			updateDataTable();
		});
		if(btnAddTheme){
			btnAddTheme.addEventListener('click',e=>{
				showModalActualizar("agregar");
			});
		}
		if(btnUpdateTheme){
			btnUpdateTheme.addEventListener('click',async e=>{
				let lo = loader();
				await updateTable(idCourseSelect);
				lo.remove();
			});
		}
	};
	
	const addListenerClickTable = () =>{
		tableContenido_tbody.addEventListener('click',/*async*/ e=>{
			target = e.target;
			if(e.target.tagName == 'I'){
				e.preventDefault();
				let idContenido = e.target.dataset.idtheme;
				let codContenido = e.target.dataset.codcontenido;
				/*try{*/
					if(e.target.classList.contains("fa-trash-alt")){//close
						showModalEliminar(idContenido);
					}else{//update
						let parent = e.target.parentElement.parentElement.parentElement.parentElement;
						let childs = Array.from(parent.children);
						
						showModalActualizar("actualizar",idContenido,childs[1].textContent,
											childs[2].firstElementChild.textContent,codContenido);
					}
				/*}catch(e){
					console.log(e);
					if(e.status == 401){
						location.href = "login";
					}else{
						alert("ERROR");
					}
				}*/
			}
		});
		
	};
	
	const showModalActualizar = (accion,idContenido=0,descContenido="",link="",codContenido="")=>{
		
		let title = accion == 'actualizar' ? "Actualizar":"Agregar"; 
		
		let modal  = new BigModal({
			"header": `
				<div class="flex spacebetween">
					<span class="title-modal">${title} contenido</span>
				</div>
			`,
			"body" : `
				<div class="theme-form-container">
                	<form id="m-form-theme" name="frm_contenido">
                		<div class="je-row-theme flex">
				           <div class="je-input-group">
				               <label>Contenido:</label>
				               <input type="text" name="descContenido" value="${descContenido}" required>
				           </div>
				           <input type="hidden" name="action" value="${accion}Contenido">
				           <input type="hidden" name="idContenido" value="${idContenido}">
				           <div class="je-input-group">
					           <label>Link:</label>
					           <input name="link" type="text" value="${link}" required>
				           </div>
				           <div class="container-checks">
				           		<div class="group-col">
					           		<label>Replicar a todos</label>
									<input name="replicartodos" type="checkbox">
					            </div>
					            <!--<div class="group-col">
					           		<label>Replicar a las secciones asignadas</label>
									<input name="replicarsolo" type="checkbox">
					            </div>-->
					            <input type="hidden" name="codContenido" value="${codContenido}">
					            <input type="hidden" name="idSecCurPro" value="${idCourseSelect}">
				           </div>
				     	</div>
                	</form>
                </div>
			`,
			"footer": `
				<div class="themes-buttons-container">
					<button type="submit" class="je-btn small auto" id="btnAction" style="margin-right:1rem">${title}</button>
					<button class="je-btn small auto" id="btnCancelar">Cancelar</button>
				</div>
			`,
			"animation_open": "movetoY_open 1s 0s 1 ease",
			"animation_close": "movetoY_close 1s 0s 1 ease both",
			"max_width": "1024px",
			"width":"100%",
			"time_close" : 1200,
			"automatic_close":false
		});
		modal.open();
		let frm = modal.modal_container.querySelector("#m-form-theme");
		let btnAction = modal.modal_container.querySelector("#btnAction");
		let btnCancel = modal.modal_container.querySelector("#btnCancelar");
		btnAction.addEventListener('click',async function(e){
			if(frm[0].value.trim() == '' || frm[3].value.trim() == ''){
				/*alert("Llene todos los campos...");*/
				showModalMessage("Llene todos los campos",5000);
			}else if(!validateUrl(frm[3].value.trim())){
				/*alert("El link es incorrecto");*/
				showModalMessage("El link es incorrecto",5000);
			}else{
				try{
					let json = await actualizarContenido(frm);
					modal.closeAnimation();
					//alert(json.mensaje);
					showModalMessage(json.mensaje,5000);
					await updateTable(idCourseSelect);
				}catch(e){
					modal.closeAnimation();
					if(e.status == 403){
						showModalMessage("No tiene privilegios para realizar esta acción",5000);
						//alert("No tiene privilegios para realizar esta acción");
					}else if(e.status == 401){
						location.href = "login";
					}else{
						//alert(`No se pudo ${title} el contenido`);
						showModalMessage(`No se pudo ${title} el contenido`,5000);
					}
				}
			}
		});
		btnCancel.addEventListener('click',function(e){
			modal.closeAnimation();
		});
	};
	
	const validateUrl = (txt) => {
		return /^(?:https|http|ftp):(?:\\\\|\/\/).+$/.test(txt);
	};
	
	const showModalEliminar = (idContenido)=>{
		let modal  = new BigModal({
			"header": `
				<div class="flex spacebetween">
					<span style="padding: 1rem 0;font-size: 16px;">Está seguro que desea eliminar este contenido?</span>
				</div>
			`,
			"body":`
				<div class="container-checks">
		           		<div class="group-col">
			           		<label>Replicar a todos</label>
							<input name="replicartodos" type="checkbox" id="replicar_todos">
			            </div>
			            <!--<div class="group-col">
			           		<label>Replicar a las secciones asignadas</label>
							<input name="replicarsolo" type="checkbox">
			            </div>-->
		           </div>
			`,
			"footer": `
				<div class="themes-buttons-container">
					<button type="submit" class="je-btn small auto dark-red" id="btnEliminar" style="margin-right:1rem">Eliminar</button>
					<button class="je-btn small auto" id="btnCancelar">Cancelar</button>
				</div>
			`,
			"animation_open": "movetoY_open 1s 0s 1 ease",
			"animation_close": "movetoY_close 1s 0s 1 ease both",
			"max_width": "450px",
			"time_close" : 500,
			"automatic_close":false
		});
		
		modal.open();
		let btnEliminar = modal.modal_container.querySelector("#btnEliminar"),
			btnCancel = modal.modal_container.querySelector("#btnCancelar"),
			replicarTodos = modal.modal_container.querySelector("#replicar_todos");
		btnEliminar.addEventListener('click',async function(e){
			try{
				let json = await eliminarContenido(idContenido,replicarTodos.checked ? "on" : "off");
				modal.closeAnimation();
				//alert(json.mensaje);//debemos reemplazar esto por un modal :)
				showModalMessage(json.mensaje,5000);
				await updateTable(idCourseSelect);
			}catch(e){
				modal.closeAnimation();
				if(e.status == 403){
					//alert("No tiene privilegios para realizar esta acción");
					showModalMessage("No tiene privilegios para realizar esta acción",5000);
				}else if(e.status == 401){
					location.href = "login";
				}else{
					showModalMessage("No se pudo eliminar el contenido",5000);
					//alert("No se pudo eliminar el contenido");
				}
			}
		});
		btnCancel.addEventListener('click',function(e){
			modal.closeAnimation();
		});
	};
	
	const actualizarContenido = async (frm)=>{
		let load = loader();
		let respuesta = await fetch("tema",{
			method : "POST",
			body : new FormData(frm)
		});
		if(!respuesta.ok){
			load.remove();
			throw respuesta;
		}else{
			let json = await respuesta.json();
			load.remove();
			return json;	
		}
	};
	
	const eliminarContenido = async (idContenido,replicartodos) => {
		let load = loader();
		let respuesta = await fetch(`tema?idContenido=${idContenido}&replicartodos=${replicartodos}`,{
			method:'DELETE'
		});
		if(!respuesta.ok){
			load.remove();
			throw respuesta;
		}else{
			let json = await respuesta.json();
			load.remove();
			return json;
		}
	};
	
	const addListenerClickGrado = () =>{
		gradoContainer.addEventListener('click',async e=>{
				let idSecGraNiv = e.target.parentElement.dataset.idsecgraniv;
					if(idSecGraNiv){
						if(actionsContainer){
							actionsContainer.style.display = "none";
						}
						clearVarTable();
						messageContainer.style.display = 'none';
						tableContenidoContainer.style.display = "none";
						clearSelectionSeccion();
						e.target.parentElement.classList.add("selected");
						let l = loader();
						try{
							let data = await getCursos(idSecGraNiv);
							if(!data.ok){
								if(data.status == 401 || data.status == 403){
									location.href = "login";
								}
							}else{
								let json = await data.json();
								drawCursos(json.cursos);
							}
						}catch(e){
							location.href = "login";
						}finally{
							l.remove();
						}
					}
		});
	};
	
	const clearSelectionSeccion = () =>{
		let d = Array.from(gradoContainer.children);
		d.forEach(e=>{
			e.classList.remove("selected");
		});
	};
	
	const getCursos = async (idSecGraNiv)=>{
		return await fetch(`cursos?idSecGraNiv=${idSecGraNiv}&action=getCursos`,{
				method : "GET"
			});
	};
	
	const drawCursos = (cursos) => {
		if(cursos || cursos.length!=0){
			coursesContainer.innerHTML = "";
			let aux = "",fragment = document.createDocumentFragment(),div;
			cursos.forEach((curso)=>{
				div = document.createElement("div");
				div.classList.add("wrapper-course");
				div.innerHTML += `
					<div class="item-course">
						<div class="icon-course">
							<i data-idSecCurPro="${curso.idSecCurPro}" 
							   data-profesor="${curso.profesor}"
							   class="menu-icon ${curso.icono}"></i>
						</div>
						<span data-profesor="${curso.profesor}"
							  class="menu-icon ${curso.icono}"
							  class="text-course-name">${curso.descCurso}</span>
					</div>
				`;
				fragment.appendChild(div);
			});
			coursesContainer.appendChild(fragment);
		}else{
			coursesContainer.innerHTML = `<p style="padding: 2rem 0;font-size: 18px;">Para este sección no tiene asignado cursos</p>`;
		}
	};

	const addListenerInputSearch = () => {
		inputSearch.addEventListener('keyup',function(e){
			if(this.value == ''){
				themesSearch = contenidos;
			}else{
				themesSearch = searchThemes(this.value.trim());
			}
			pageInit = 1;
			pageCount = getCountPages();
			updateDataTable();
		});
	};
	
	const getCountPages = ()=>{
		let countRow = themesSearch.length,
			countRowsXpage =  rowsCount;
		return countRow < countRowsXpage ? 1 :
		   parseInt(countRow / countRowsXpage) + 
		   parseInt(countRow % countRowsXpage > 0 ? 1 :0);
	};
	
})(window,document);