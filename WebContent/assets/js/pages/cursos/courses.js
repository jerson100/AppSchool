((window,document,undefinied)=>{
	document.addEventListener('DOMContentLoaded',evt=>{
		'use-strict';
		const dom = document,
			  $gradoContainer = dom.getElementById("gradoContainer"),
			  $coursesContainer = dom.getElementById("coursesContainer"),
			  $tablesContainer = dom.querySelector(".tables-container"),
			  $addCharacteristic = dom.getElementById("addCharacteristic"),
			  $modalOptions = dom.querySelector(".mod--resource");
			  $modalOptionsClose = dom.querySelector(".mod--resource .mod__close"),
			  $nombreProfesorSeleccionado = dom.getElementById("nombreProfesorSeleccionado"),
			  $modalHeader = document.querySelector(".resource__header");
			  tableContent=null,tableClass=null,tableWork=null,idSecCurPro=null;
			  
			  /*BOOLEANS*/
		let flagUpdateContent = false,
			flagUpdateClass = false,
			flagUpdateWork = false;
		
		/*
		 * Este método nos permite actualizar la data de las tablas.
		 * @Param tableName -> Nombre de la tabla a actualizar.
		 * */
		const setDataTable = async (tableName) => {
			let load = loader(),
				data,
				dataArray,
				tableReference;
			try{
				switch(tableName){
					case 'tableContent':
						tableReference = tableContent;
						data = await API.getData(`cursos?action=listar&idSecCurPro=${idSecCurPro}`);
						dataArray = data.estado ? data.contenidos : [];
						break;
					case 'tableClass':
						tableReference = tableClass;
						data = await API.getData(`clases?idSecCurPro=${idSecCurPro}`);
						dataArray = data.estado ? data.clases : [];
						break;
					case 'tableWork':
						tableReference = tableWork;
						data = await API.getData(`trabajo?idSecCurPro=${idSecCurPro}`);
						dataArray = data.estado ? data.trabajos : [];
						break;
				}
				tableReference.updateData(dataArray);
				tableReference.table.scrollIntoView({block: "start", behavior: "smooth"});
			}catch(e){
				if(e.status==401||e.status==403){
					location.href = 'login';
				}else{
					console.log("Error: "+e);
				}
			}finally{
				load.remove();
			}
		};
			
		/*
		 * Agregamos los oyentes, sean las interacciones
		 * que realiza el cliente...
		 * */
		const addListeners = () => {
			gradeListener();
			courseListener();
			modalListener();
			tab('.tabs',[
				{
					"href" : "tableContent",
					"callback" : function(){
						setDataTable('tableContent');
					}
				},
				{
					"href" : "tableClass",
					"callback" : function(){
						setDataTable('tableClass');
					}
				},
				{
					"href" : "tableWork",
					"callback" : function(){
						setDataTable('tableWork');
					}
				}
			]);
		};
		
		/*
		 * Oyente de las interacciones de los clientes
		 * en la ventana modal, ya sea para que agreguen, modifiquen 
		 * contenidos, clases o tareas.
		 * */
		const modalListener = () => {
			if($addCharacteristic){
				$addCharacteristic.addEventListener('click',e=>{//cuando pulsa en el ícono +
					$modalOptions.classList.add("mod--active");//abrimos el modal
					$modalHeader.innerHTML = `
						<p class="resource__title" style="animation:fromTopToBottom 250ms 1 linear;">Seleccione una opción</p>					
					`;
				});
			}
			if($modalOptionsClose){
				$modalOptionsClose.addEventListener('click',e=>{//cuando da click en x
					$modalOptions.classList.remove("mod--active");//cerramos el modal
					$modalOptions.querySelector(".tag__wrapper").style.transform = "translateX(0%)";
					flagUpdateContent = false;
					flagUpdateClass = false;
					flagUpdateWork = false;
				});
			}
			if($modalOptions){
				$modalOptions.addEventListener('click',e=>{//cuando pulsa en cualquier input del modal.
					if(e.target.tagName === 'BUTTON'){
						if(e.target.type != 'reset'){
							e.preventDefault();
							if(e.target.matches(".tag__section--content .tag__btn--first")){//add
								if(flagUpdateContent){//si el flag está activo quiere decir que estamos actualizando el contenido
									actionsButtonsModal('Content','actualizarContenido',idSecCurPro);
								}else{
									actionsButtonsModal('Content','agregarContenido',idSecCurPro);
								}
							}else if(e.target.matches(".tag__section--class .tag__btn--first")){//add	
								if(flagUpdateClass){
									actionsButtonsModal('Class','actualizarClase', idSecCurPro);
								}else{
									actionsButtonsModal('Class','agregarClase', idSecCurPro);
								}
							}else if(e.target.matches(".tag__section--work .tag__btn--first")){//add
								if(flagUpdateWork){
									actionsButtonsModal('Work','actualizarTrabajo', idSecCurPro);
								}else{
									actionsButtonsModal('Work','agregarTrabajo', idSecCurPro);
								}
							}else if(e.target.matches(".tag__section--work .tag__file-button")){
								e.target.nextElementSibling.nextElementSibling.click();
							}
						}else{
							console.log("reset");
						}
					}else{
						if(e.target.tagName === "I"){
							if(e.target.classList.contains("fa-upload")){
								e.preventDefault();
								e.target.parentElement.nextElementSibling.nextElementSibling.click();
							}else if(e.target.classList.contains("tag__file-delete")){
								e.preventDefault();
								let parent = e.target.parentElement;
								parent.querySelector(".tag__input").value = "";
								parent.querySelector(".tag__file-name").textContent = "";
								/*let $frmWork = parent.parentElement.parentElement;*/
								/*$frmWork[12].value = "true";*/
								
							}
						}
					}
					/*else if(e.target.tagName === "I" && e.target.classList.contains("fa-upload")){
					}else if(e.target.tagName)*/
				});
				const $formWk = $modalOptions.querySelector(".tag__section--work form");
				const namaeFileWork = $formWk.querySelector(".tag__file-name");
				$formWk[2].addEventListener('change',async e=>{
					//console.log("change detected");
					if(e.target.files.length != 0){
						namaeFileWork.textContent = e.target.files[0].name;
						//Verificamos el tipo de extensión
						await verificarExtension($formWk);		
					}else{
						namaeFileWork.textContent = '';
					}
				});
				
			}
		};
		
		const viewModalLoaderVerifyExtension = () => {
			const modalContainer = document.createElement("div");
			modalContainer.setAttribute("class","mod mod--active mod--verifyExtension mod--bg-op2");
			modalContainer.setAttribute("style","z-index:100000");
			modalContainer.innerHTML = `
				<div class="mod__inner">
					<div class="verifyExtension">
						<img class="verifyExtension__img" src="assets/img/static/logoColegio.jpg">
						<span class="verifyExtension__title">Analizando archivo
							<div class="load-point">
								<span class="load-point__item"></span>
								<span class="load-point__item"></span>
								<span class="load-point__item"></span>
							</div>
						</span>
					</div>
				</div>
			`;
			document.body.appendChild(modalContainer);
			return modalContainer;
		};
		
		const verificarExtension = async (form) => {
			let fomrData = new FormData(form);
			let mod = viewModalLoaderVerifyExtension();
			fomrData.set("action","validar");
			let mensaje = "";
			try{
				const response = await API.getData('tipoArchivo',{
					method: 'POST',
					body: fomrData
				});
				if(response.estado){
					mensaje = "Archivo válido";
				}else{
					mensaje = response.mensaje;
					form[1].nextElementSibling.textContent = '';
					form[2].value = "";
				}
			}catch(error){
				if(error.status === 401 || error.status === 403){
					location.href = "login";
				}else if(error.status === 404){
					//console.log("Se produjo un error");
					msg = "Ocurrió algo inesperado";
				}
			}finally{
				mod.remove();
				//if(mensaje!=''){
					showModalMessage(mensaje,3000);
				//}
			}
		};
		
		const actionsButtonsModal = async (name,action,idseccurpro=0) => {
			const $form = dom.querySelector(`.tag__section--${name.toLowerCase()} form`),
				  load = loader();
			let data,formData,msg='',validations=true,url;
			try{
				$form[4].value = `${action}`; //establecemos la acción en un input oculto
				formData = new FormData($form);
				formData.append("idSecCurPro",idseccurpro);//establecemos el id del curso seleccionado
				switch(name){
					case 'Content':
						msg = validateContent($form);
						url = 'tema';
						break;
					case 'Class':
						msg = validateClass($form);
						url = 'clases';
						break;
					case 'Work':
						msg = validateWork($form);
						formData.append("nuevoNombreArchivo",$form.querySelector(".tag__file-name").textContent);
						url = 'trabajo';
						break;
				}
				if(msg === ''){
					data = await API.getData(url,{
						method: "POST",
						body: formData
					});
					msg = data.mensaje;							
				}else{
					validations = false;
				}
			}catch(e){
				if(e.status == 401 || e.status === 403){
					location.href = "login";
				}else{
					//console.log(e);
					msg = "Ocurriò algo inesperado";
				}
			}finally{
				console.log(msg);
				if(validations){
					$form.reset();
					load.remove();
					flagUpdateClass = false;
					flagUpdateContent = false;
					flagUpdateWork = false;
					showModalMessage(msg,5000);
					$modalOptions.querySelector(".tag__wrapper")
					.style.transform = "translateX(0%)";
					if(action.replace(/(?:contenido|clase|trabajo)/gi,"") === 'actualizar'){//ocultamos el modal
						$modalOptions.classList.remove("mod--active");
					}
					await setDataTable(`table${name}`);//actualizamos la tabla
				}else{
					showModalMessage(msg,5000);
					load.remove();
				}
			}
		};
		
		/*
		 * Mètodo que nos permite seleccionar un curso
		 * */
		const courseListener = () => {
			if($coursesContainer){
				$coursesContainer.addEventListener('click',e=>{
					if(e.target.tagName === 'I' || e.target.tagName === 'SPAN'){
						if($nombreProfesorSeleccionado){
							$nombreProfesorSeleccionado.textContent = `Docente: ${e.target.dataset.profesor}`;							
						}
						//removeClass(),"selected");
						ELEMENTS.removeClass(Array.from($coursesContainer.querySelectorAll(".icon-course")),["selected"]);
						if(e.target.tagName === 'I'){
							e.target.parentElement.classList.add("selected");
						}else{
							e.target.parentElement.firstElementChild.classList.add("selected");
						}
						$tablesContainer.classList.add("table-container--visible");
						idSecCurPro = e.target.dataset.idseccurpro; 
						
						//setDataTable('tableContent');//establecemos nuevamente ala data de la table
						let $tapSelection = document.querySelector(".tabs__item--active .tabs__link");
						setDataTable($tapSelection.hash.replace("#",""));
					}			
				});
			}
		};
		
		/*
		 * Este método nos permitirá saber que sección está seleccionando el usuario
		 * 
		 * */
		const gradeListener = ()=>{
			
			if($gradoContainer){
				
				const grades = Array.from($gradoContainer.children); 
				
				/*Cuando seleccionamos un grado y/o sección*/
				$gradoContainer.addEventListener('click',async e=>{
					
					if(!e.target.classList.contains("grado-container")){
						
						//eliminamos todos grados seleccionados
						//removeClass(grades,"selected");
						ELEMENTS.removeClass(grades,["selected"]);
						
						//seleccionamos el grado pulsado y lo pintamos
						if(e.target.tagName === 'SPAN'){
							e.target.parentElement.classList.add("selected");
						}else{
							e.target.classList.add("selected");
						}
						
						//obtenemos el idSecGraNiv para listar todos los cursos 
						//de esa sección - grado
						let idSecGraNiv = e.target.dataset.idsecgraniv;
						
						let load = loader();
						
						try{
							
							//consultamos al server y obtenemos todos los cursos de la sección seleccionada
							
							const data = await API.getData(`cursos?idSecGraNiv=${idSecGraNiv}&action=getCursos`);
							
							drawCourses(data);//pintamos los cursos en pantalla
							
							dom.documentElement.scrollIntoView({block: "start", behavior: "smooth"});
							
						}catch(e){
							
							if(e.status == 404){
								
								console.log("error");
								
							}else if(e.status === 401 || e.status === 403){
							
								location.href = "login";
								
							}
							
						}finally{
							
							load.remove();
							
							lastClearData();
							
						}
						
					}
					
				});
			}
			
		}
		
		/*
		 * Este método nos permite pintar los cursos en
		 * pantalla.
		 * @param data -> Es un objeto json
		 * que contiene un atributo estado
		 * que nos dice si obtenemos alguna información
		 * y otra cursos, que es un arreglo de objetos
		 * */
		const drawCourses = (data) => {
			if($coursesContainer){
				$coursesContainer.innerHTML = '';//limpiamos los cursos previos
				/*$tablesContainer.display = 'none';*/
				if(data.estado){
					let courses = data.cursos;
					if(courses || courses.length != 0){
						let fragment = document.createDocumentFragment(),
						    div;
						courses.forEach(course=>{
							div = document.createElement("div");
							div.classList.add("wrapper-course");
							div.innerHTML += `
								<div class="item-course"">
								<div class="icon-course">
								<i data-idSecCurPro="${course.idSecCurPro}" data-profesor="${course.profesor}" class="menu-icon ${course.icono}"></i>
								</div>
								<span data-idSecCurPro="${course.idSecCurPro}" data-profesor="${course.profesor}" class="text-course-name">${course.descCurso}</span>
								</div>
								`;
							fragment.appendChild(div);
						});
						$coursesContainer.appendChild(fragment);
					}else{//si no hay cursos para esa sección, entonces imprimimos un mensaje
						$coursesContainer.innerHTML = `<p style="padding: 2rem 0;font-size: 18px;">Para esta sección no tiene asignado cursos</p>`;
					}									
				}else{
					$coursesContainer.innerHTML = `<p style="padding: 2rem 0;font-size: 18px;">Para esta sección no tiene asignado cursos</p>`;
				}
			}
		};
		
		const tab = (element,actions=[{href,callback}]) => {
			let $element = document.querySelector(element),
				$sections = Array.from($element.querySelectorAll("[data-table]")),
				$elements = Array.from($element.querySelectorAll(".tabs__item"));
			let tag,sectionHref;
			$elements.forEach(tag=>{
				tag.addEventListener('click',e=>{
					e.preventDefault();
					sectionHref = tag.lastElementChild.hash.replace("#","");
					ELEMENTS.removeClass($elements,["tabs__item--active"]);
					//removeClass($elements,"tabs__item--active");
					tag.classList.add("tabs__item--active");
					ELEMENTS.removeClass($sections,["tabs__section--active"]);
					//removeClass($sections,"tabs__section--active");
					try{
						$element.querySelector(`[data-table=${sectionHref}]`).classList.add("tabs__section--active");
					}catch(e){}
					actions.forEach(action=>{
						if(action.href == sectionHref){
							action.callback();
						}
					});
				});
			 })
		}

		const addTables = () => {
			let tipoPerfil = localStorage.getItem("idPerfil");
			tableContent = new DataTable({
	            table: '[data-table="tableContent"] table',
	            columns: [
	                {
	                    name: 'Contenido',
	                    var: 'descContenido',
	                    type: 'text'
	                },
	                {
	                    name: 'Link',
	                    var: 'link',
	                    type: 'link'
	                }
	            ],
	            rowsCount: 10,
	            navigator: true,
	            actions: {
	                options: tipoPerfil == 1 ? '' : 'edit remove',
	                edit: (data,objDataTable) => {
	                    showModalUpdate('tableContent',data);
	                },
	                remove: function (data,objDataTable) {
	                	showModalDelete("¿Está seguro que desea eliminar este contenido?",async (replicarTodos) =>{
	                		let load = loader(), msg = "No se pudo eliminar el contenido", response;
	                		try{
	                			response = await API.getData(`tema?idContenido=${data.idContenido}&replicartodos=${replicarTodos.checked ? "on" : "off"}`,{
	        						method:'DELETE'
	        					});
	                			msg = response.mensaje;
	                		}catch(e){
	                			if(e.status === 403){
	                				msg = "No tiene privilegios suficientes para realizar esta acción";
	                			}else if(e.status === 401){
	                				location.href = "login";
	                			}else if(e.status === 404){
	                				msg = "No se pudo llevar acabo la acción";
	                			}
	                		}finally{
	                			load.remove();
	                			await setDataTable('tableContent');
	                			return msg;
	                		}
	                	});
	                },
	                view: function (data) {
	                	
	                }
	            }
	        });
			
			tableClass= new DataTable({
                table: '[data-table="tableClass"] table',
                columns: [
                    {
                        name: 'Título',
                        var: 'descClase',
                        type: 'text'
                    },
                    {
                        name: 'Fecha',
                        var: 'fechaClaseS',
                        type: 'text'
                    },
                    {
                        name: 'Hora',
                        var: 'horaClaseS',
                        type: 'text'
                    },
                    {
                        name: 'Link',
                        var: 'link',
                        type: 'link'
                    }
                ],
                rowsCount: 10,
                navigator: true,
                actions: {
                    options: tipoPerfil == 1 ? '' : 'edit remove',
                    edit: (data,objDataTable) => {
                        showModalUpdate('tableClass',data);
                    },
                    remove: function (data) {
                    	showModalDelete("¿Está seguro que desea eliminar esta clase?",async (replicarTodos) =>{
	                		let load = loader(), msg = "No se pudo eliminar la clase", response;
	                		try{
	                			response = await API.getData(`clases?idClass=${data.idClass}&replicartodos=${replicarTodos.checked ? "on" : "off"}`,{
	        						method:'DELETE'
	        					});
	                			msg = response.mensaje;
	                		}catch(e){
	                			if(e.status === 403){
	                				msg = "No tiene privilegios suficientes para realizar esta acción";
	                			}else if(e.status === 401){
	                				location.href = "login";
	                			}else if(e.status === 404){
	                				msg = "No se pudo llevar acabo la acción";
	                			}
	                		}finally{
	                			load.remove();
	                			await setDataTable('tableClass');
	                			return msg;
	                		}
	                	});
                    },
                    view: function (data) {
                    	
                    }
                }
            });
			
			tableWork = new DataTable({
                table: '[data-table="tableWork"] table',
                options: {
                	row: {
                		disabled : true,
                		var: 'flagLimite2'
                	}         
                },
                columns: [
                    {
                        name: 'Título',
                        var: 'descTrabajo',
                        type: 'text'
                    },
                   /* {
                        name: 'Fecha inicio',
                        var: 'urlDescarga',
                        type: 'img'
                    },*/
                    {
                        name: 'Fecha Inicio',
                        var: 'fechaIniS',
                        type: 'text'
                    },
                    {
                        name: 'Fecha entrega',
                        var: 'fechaFinS',
                        type: 'text'
                    },
                    {
                        name: 'Fuera de tiempo',
                        var: 'diasLimite',
                        type: 'text'
                    },
                    {
                        name: 'Descarga',
                        var: 'link',
                        type: 'icon',
                        settings:{
                        	colorvar: 'color'
                        }
                    }
                ],
                rowsCount: 10,
                navigator: true,
                actions: {
                    options: tipoPerfil == 1 ? '' : 'view edit remove',
                    edit: (data,objDataTable) => {
                        console.log(data);
                        showModalUpdate('tableWork',data);
                    },
                    remove: function (data) {
                    	console.log(data);
                    	showModalDelete("¿Está seguro que desea eliminar el trabajo?",async (replicarTodos) =>{
	                		let load = loader(), msg = "No se pudo eliminar la clase", response;
	                		try{
	                			response = await API.getData(`trabajo?idTrabajo=${data.idTrabajo}&rutaArchivo=${data.rutaArchivo}&replicartodos=${replicarTodos.checked ? "on" : "off"}`,{
	        						method:'DELETE'
	        					});
	                			msg = response.mensaje;
	                		}catch(e){
	                			if(e.status === 403){
	                				msg = "No tiene privilegios suficientes para realizar esta acción";
	                			}else if(e.status === 401){
	                				location.href = "login";
	                			}else if(e.status === 404){
	                				msg = "No se pudo llevar acabo la acción";
	                			}
	                		}finally{
	                			load.remove();
	                			await setDataTable('tableWork');
	                			return msg;
	                		}
	                	});
                    },
                    view: function (data) {
    
                    }
                }
            });
		};
		
		const validateUrl = (txt) => {
			return /^(?:https|http|ftp):(?:\\\\|\/\/).+$/.test(txt);
		};
		
		const changeTitleModal = (title) => {
			$modalHeader.innerHTML = '';
			const elementTitle = document.createElement("p");
			elementTitle.setAttribute("class","resource__title");
			elementTitle.setAttribute("style","animation:fromTopToBottom 250ms 1 linear;")
			elementTitle.innerHTML = `
				${title}
				<div class="load-point">
					<span class="load-point__item"></span>
					<span class="load-point__item"></span>
					<span class="load-point__item"></span>
				</div>
			`;
			$modalHeader.appendChild(elementTitle);
		};
		
		const showModalUpdate = async (tableName,data) => {
			$modalOptions.classList.add("mod--active");
			changeTitleModal("Actualizando");
			let $form;
			switch(tableName){
				case 'tableContent':
					$modalOptions.querySelector(".tag__wrapper")
					.style.transform = "translateX(-100%)";
					$form = $modalOptions.querySelector(".tag__section--content .tag__form");
					$form[0].value = data.descContenido;
					$form[1].value = data.link;
					$form[3].value = data.idContenido;
					$form[5].value = data.codContenido;
					flagUpdateContent = true;
					break;
				case 'tableClass':
					$modalOptions.querySelector(".tag__wrapper")
					.style.transform = "translateX(-200%)";
				    $form = $modalOptions.querySelector(".tag__section--class .tag__form");
					/*console.log(data);*/
					$form[0].value = data.descClase;
					$form[1].value = data.link;
					$form[2].value = data.fechaClase;
					$form[3].value = data.horaClase;
					$form[5].value = data.idClass;
					$form[6].value = data.codClase;
					flagUpdateClass = true;
					break;
				case 'tableWork':
					$modalOptions.querySelector(".tag__wrapper")
					.style.transform = "translateX(-300%)";
					$form = $modalOptions.querySelector(".tag__section--work .tag__form");
					$form.querySelector(".tag__file-name").textContent = data.nombreArchivo;
					$form[0].value = data.descTrabajo;
					$form[3].value = data.fechaIni;
					$form[5].value = data.fechaFin;
					$form[6].checked = data.isFlagLimite;
					$form[7].value = data.diasLimite;
					$form[8].value = data.replicartodos;
					$form[9].value = data.idTrabajo;
					$form[10].value = data.codTrabajo;
					$form[11].value = data.rutaArchivo;
					/*$form[12].value = "false";*/
					$form.querySelector(".tag__file-delete").classList.add("tag__file-delete--active");
					flagUpdateWork = true;
					break;
			}	
			if($form){
				$form.parentElement.firstElementChild.classList.add("tag__return--hidden");
			}
		};
		
		const lastClearData = () => {
			if(tableContent){
				tableContent.clear();
				tableClass.clear();
				tableWork.clear();
				$tablesContainer.classList.remove("table-container--visible");
				if($nombreProfesorSeleccionado){
					$nombreProfesorSeleccionado.innerHTML = "";
				}
			}
		};
		
		/*
		 * 
		 * Validaciones en el lado del cliente de los formularios
		 * 
		 * */
		const validateContent = (form) => {
			let mensaje = "";
			if(form){
				if(form[0].value.trim() === ''){
					mensaje = "El nombre no puede estar vacío";
				}else if(form[1].value.trim() === ''){
					mensaje = "La url no puede estar vacía";
				}else if(!validateUrl(form[1].value)){
					mensaje = "La url no es válida";
				}
			}
			return mensaje;
		};
		
		const validateClass = (form) => {
			let mensaje = "";
			if(form){
				if(form[0].value.trim() === ''){
					mensaje = "El título no puede estar vacía";
				}else if(form[1].value.trim() === ''){
					mensaje = "El link no puede estar vacío";
				}else if(!validateUrl(form[1].value)){
					mensaje = "La url no es válida";
				}else if(form[2].value.trim() === ''){
					mensaje = "Por favor, ingrese una fecha";
				}else if(form[3].value.trim() === ''){
					mensaje = "Por favor ingrese una hora";
				}
			}
			return mensaje;
		};
		
		const validateWork = (form) => {
			let mensaje = "";
			if(form[0].value.trim() === ''){
				mensaje = "El nombre no puede estar vació";
			}/*else if(form[2].value.trim() === ''){
				mensaje = "Seleccione un archivo";
			}*/else if(form[3].value.trim() === ''){
				mensaje = "Ingrese la fecha de inicio";
			}else if(form[5].value.trim() === ''){
				mensaje = "Ingrese la fecha Fin";
			}if(form[6].checked && form[7].value === ''){
				mensaje = "Especifique los días de fuera de fecha";
			}
			return mensaje;
		};
		
		const init = () => {
			addTables();
			addListeners();
			validateMinDateInputs();
		}
		
		const validateMinDateInputs = () => {
			const $inputsDateTime = Array.from($modalOptions.querySelectorAll("input[type=datetime-local]"));
			const date = new Date(),
			      agnoMonthDay = d(date);
			$inputsDateTime.forEach(input=>{
				input.min = agnoMonthDay;
			});
		};
		
		const d = (date) => {
	        return (date.getFullYear().toString() + '-' 
	           + ("0" + (date.getMonth() + 1)).slice(-2) + '-' 
	           + ("0" + (date.getDate())).slice(-2))
	           + 'T' + date.toTimeString().slice(0,5);
	    }
		
		init();
		
	});
})(window,document);