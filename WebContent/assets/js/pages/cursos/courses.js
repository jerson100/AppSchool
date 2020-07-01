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
			  $modalHeader = document.querySelector(".resource__header"),
			  $tagFileName = document.querySelector(".tag__file-name"),
			  $tagSections = Array.from($modalOptions.querySelectorAll(".tag__section")),
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
		 * Limpiamos las selecciones de los tags
		 * */
		const clearTagSection = () => {
			let tags = Array.from($modalOptions.querySelector(".tag__section"));
			tags.forEach(tag=>{
				tag.classList.remove("tag__section--active");
			});
		};
		
		/*
		 * Oyente de las interacciones de los clientes
		 * en la ventana modal, ya sea para que agreguen, modifiquen 
		 * contenidos, clases o tareas.
		 * */
		const modalListener = () => {
			if($addCharacteristic){
				$addCharacteristic.addEventListener('click',e=>{//cuando pulsa en el ícono +
					$modalOptions.classList.add("mod--active");
					ELEMENTS.removeClass($tagSections,["tag__section--active"]);
					$tagSections[0].classList.add("tag__section--active");
					$tagFileName.textContent = "";
					$modalHeader.innerHTML = `
						<p class="resource__title" style="animation:fromTopToBottom 250ms 1 linear;">Seleccione una opción</p>					
					`;
				});
			}
			if($modalOptionsClose){
				$modalOptionsClose.addEventListener('click',e=>{//cuando da click en x
					$modalOptions.classList.remove("mod--active");//cerramos el modal
					ELEMENTS.removeClass($tagSections,["tag__section--active"]);
					$tagSections[0].classList.add("tag__section--active");
					$tagFileName.textContent = "";
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
							}
						}else{
							let filesNames = Array.from($modalOptions.querySelectorAll(".tag__file-name"));
							$modalOptions.querySelector(".tag__input-number").disabled = true;
							filesNames.forEach(it => {
								it.textContent = "";
							});
						}
					}else{
						if(e.target.tagName === "I"){
							if(e.target.classList.contains("tag__file-delete")){
								e.preventDefault();
								let parent = e.target.parentElement;
								parent.querySelector(".tag__input").value = "";
								parent.querySelector(".tag__file-name").textContent = "";
							}
						}
					}
				});
				const $formWk = $modalOptions.querySelector(".tag__section--work form");
				const inputButton = $formWk.querySelector(".tag__file-button");
				const fileName = $formWk.querySelector(".tag__file-name");
				const inputFile = fileName.nextElementSibling;
				//Agregamos el evento change para los files, analiza el tipo de extensión válido
				ELEMENTS.INPUT_FILE.changeUpdloadFile(inputButton,inputFile,fileName);
				
				$formWk[6].addEventListener('change',e=>{
					if(!e.target.checked){
						e.target.nextElementSibling.value = "";
					}
					e.target.nextElementSibling.disabled = !e.target.checked;
				});
				
			}
		};
		/*
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
					msg = "Ocurrió algo inesperado";
				}
			}finally{
				mod.remove();
				//if(mensaje!=''){
					showModalMessage(mensaje,3000);
				//}
			}
		};
		*/
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
					msg = "Ocurriò algo inesperado";
				}
			}finally{
				if(validations){
					$form.reset();
					load.remove();
					$tagFileName.textContent = "";
					flagUpdateClass = false;
					flagUpdateContent = false;
					flagUpdateWork = false;
					showModalMessage(msg,5000);
					ELEMENTS.removeClass($tagSections,["tag__section--active"]);
					$tagSections[0].classList.add("tag__section--active");
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

		/*
		 * Método para GENERAR las 3 tablas 
		 * */
		const addTables = () => {
			let tipoPerfil = localStorage.getItem("idPerfil");
			tableContent = new DataTable({
	            table: '[data-table="tableContent"] table',
	            options: {
                	row: {
                		bgcolor: "#358bbc1c" 
                	}         
                },
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
                options: {
                	row: {
                		bgcolor: "#358bbc1c" 
                	}         
                },
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
			
			let columnsWork = [
				{
                    name: 'Título',
                    var: 'descTrabajo',
                    type: 'text'
                },
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
                    name: 'Prórroga (Días)',
                    var: 'diasLimite',
                    type: 'text'
                },
                {
                    name: 'Doc. Profesor',
                    var: 'link',
                    type: 'icon',
                    settings:{
                    	colorvar: 'color',
                    	icon: 'icono'
                    }
                }
			];
			if(tipoPerfil == 1){
				columnsWork.push({
                    name: 'Mi trabajo',
                    var: 'link2',
                    type: 'icon',
                    settings:{
                    	colorvar: 'color2',
                    	icon: 'icono2'
                    }
                });
				columnsWork.push({
                	name: 'Nota',
                	var: 'notas',
                	type: 'text'
                });
			}
			tableWork = new DataTable({
                table: '[data-table="tableWork"] table',
                options: {
                	row: {
                		disabled : true,
                		var: 'flagLimite2'
                	}         
                },
                columns: columnsWork,
                rowsCount: 10,
                navigator: true,
                actions: {
                    options: tipoPerfil == 1 ? 'view upload remove' : 'view edit remove',
                    edit: (data,objDataTable) => {
                    	if(data.flagLimite1 < 0){
                			showModalMessage("No puede actualizar este trabajo.",5000);
                		}else{
                			showModalUpdate('tableWork',data);
                		}
                    },
                    remove: function (data) {
                    	if(tipoPerfil == 1){
                    		if(data.idTraAlu === 0){
                    			showModalMessage("No tiene trabajo que eliminar",5000);
                    		}else{
                    			showModalDelete("Eliminar archivo",async (replicarTodos) =>{
                        			let load = loader(), msg = "No se pudo eliminar la clase", response;
                        			try{
                        				response = await API.getData(`alumnosTrabajo?idTraAlu=${data.idTraAlu}&rutaArchivo=${data.rutaArchivo2}&nota=${data.notas}`,{
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
                        				if(response.estado){
                        					await setDataTable('tableWork');                    					
                        				}
                        				return msg;
                        			}
                        		},false,"¿Está seguro que desea eliminar su archivo?"); 
                    		}  
                    	}else{
                    		if(data.flagLimite1 < 0){
                    			showModalMessage("No puede eliminar este trabajo.",5000);
                    		}else{
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
                    		}
                    	}
                    },
                    upload: function(data){
                    	createModalUploadAlumn(data);
                    },
                    view: function (data) {
                    	if(tipoPerfil != 1){
                    		showModalListenerStudents(data);                    		
                    	}else{
                    		showModalComent(data.comentario);
                    	}
                    }
                }
            });
		};
		
		/*Método para mostar el modal de comentario de los docentes*/
		const showModalComent = (comentario) => {
			const modal = document.createElement("div");
			modal.setAttribute("class","mod mod--active");
			modal.innerHTML = `
				<div class="mod__inner">
					<div class="comment-teacher">
					    <i class="comment-teacher__close mod__close mod__close--dark fas fa-close"></i>
						<h2 class="comment-teacher__title">Comentario del docente:</h2>
						<p class="comment-teacher__text">
							${comentario?comentario:'El docente aún no asigna un comentario'}
						</p>
					</div>
				</div>
			`;
			document.body.appendChild(modal);
			const modClose = modal.querySelector(".mod__close");
			modClose.addEventListener("click",e=>{
				modal.remove();
			});
		};
		
		/*Nos permite mostrar el modal, en la cuál listamos todos
		 * los estudiantes y así los docentes puedan subir sus notas.*/
		const showModalListenerStudents = async (data) => {
			const list = await getListNoteStudent(data.idTrabajo);
			drawModalWorkStudents(list,data.idTrabajo);
		};
		
		/*obtenemos la lista de estudiantes con sus respectivas notas, comentarios, etc*/
		const getListNoteStudent = async (idTrabajo) => {
			const formData = new FormData();
			let response,load,list=[];
			try{
				load = loader();
				response = await API.getData(`alumnosTrabajo?idTrabajo=${idTrabajo}`);
				if(response.estado){
					list = response.list;					
				}
			}catch(error){
				if(error.status === 401 || error.status === 403){
					location.href = "login";
				}else{
					console.log(error);
				}
			}finally{
				load.remove();
			}
			return list;
		};
		
		/*Pintamos el modal de los trabajos de los estudiantes..*/
		const drawModalWorkStudents = (list,idTrabajo) => {
			const modal = createModalListStudent();
			document.body.appendChild(modal);
			const modalData = modal.querySelector(".data-table__body");
			const close = modal.querySelector(".files-student__close");
			updateRowStudentNote(modalData,list);
			close.addEventListener('click',e=>{
				modal.remove();
			});
			modalData.addEventListener('click',async e=>{
				if(e.target.tagName === 'I' &&
				   e.target.classList.contains('data-table__save')){
					let load = loader();
					const row = e.target.parentElement.parentElement.parentElement;
					const notaT = row.querySelector(".data-table__input--work").value;
					const comentario = row.querySelector(".data-table__input--comentario").value;
					const response = await saveStudentNote(e.target.dataset.idcuenta,e.target.dataset.idtraalu,idTrabajo,comentario,notaT);
					load.remove();
					if(response.estado){
						//actualizamos los datos
						const list2 = await getListNoteStudent(idTrabajo);;
						updateRowStudentNote(modalData,list2);
					}
					showModalMessage(response.mensaje,3000);
				}
			});
			
		};
		
		/*Método que permite actualizar Y pintar en pantalla, las filas de los estudiantes*/
		const updateRowStudentNote = (container, list) => {
			container.innerHTML = "";
     		let fragment = document.createDocumentFragment();
			let numeration = 0;
			list.forEach(item=>{
				let rowItem = document.createElement("div");
				rowItem.setAttribute("class","data-table__row data-table__row-body data-table__row-border");
				if(item.link && item.link != "") {   
					rowItem.classList.add("data-table__row--upload");
				}
				rowItem.innerHTML = `
						<div class="data-table__col data-table__col--w50 data-table__col--numeration">
							<span class="data-table__text">${++numeration}</span>
						</div>
						<div class="data-table__col">
							<span class="data-table__text">${item.alumno}</span>
						</div>
						<div class="data-table__col data-table__col--ultimaModificacion data-table__col--hidden-m">
							<span class="data-table__text">${item.ultimaModificacion}</span>
						</div>
						<div class="data-table__col data-table__col--w50">
							<span class="data-table__text">
								<a class="data-table__link" href="${item.link}" target="_blank">
									<i class="data-table__icon ${item.icono}" style="color:${item.color2}"></i>
								</a>
							</span>
						</div>								
						<div class="data-table__col data-table__col--w50">
							<input class="data-table__input data-table__input--work" value=${item.notaTrabajo}>
						</div>	
						<div class="data-table__col">
							<input class="data-table__input data-table__input--comentario" value="${item.comentario}">
						</div>
						<div class="data-table__col data-table__col--w50 data-table__col--save">
							<span class="data-table__text">
								<i class="data-table__icon data-table__icon data-table__save fas fa-save" data-idCuenta="${item.idCuenta}" data-idTraAlu="${item.idTraAlu}"></i>
							</span>
						</div>	
				`;
				fragment.appendChild(rowItem);
			});
			container.appendChild(fragment);
		};
		
		/*Método que nos permite guardar la toda la row(data) del estudiante*/
		const saveStudentNote = async (idCuenta,idTraAlu,idTrabajo,comentario,notaTrabajo) => {
			let mensaje = "";
			let estado = false;
			const formDat = new FormData();
			formDat.append("idCuenta",idCuenta);
			formDat.append("idTraAlu",idTraAlu);
			formDat.append("idTrabajo",idTrabajo);
			formDat.append("action","actualizarNota");
			formDat.append("comentario",comentario);
			formDat.append("notaTrabajo",notaTrabajo);
			try{
				const response = await API.getData("alumnosTrabajo",{
					method: 'POST',
					body: formDat
				});
				mensaje = response.mensaje;
				estado = response.estado;
			}catch(error){
				if(error.status === 401){
					location.href = "login";
				}else if(error.status === 403){
					mensaje = "Usted no tiene privilegios suficientes";
				}else if(error.status === 404){
					mensaje  = "Ocurrió algo inesperado";
				}
			}finally{
				return {mensaje,estado};
			}
		};
		
		/*Creamos el modal de listado de alumnos, para que el docente suba las notas.
		 *Aquí principalmente creamos el header.*/
		const createModalListStudent = () => {
			const modalContainer = document.createElement("div");
			modalContainer.innerHTML = `
				<div class="mod mod--active mod--filesStudent">
					<div class="mod__inner mod__inner--pd-1">
						<div class="files-student">
							<i class="files-student__close mod__close mod__close--dark fas fa-close"></i>
							<h2 class="files-student__title">
								Ficha de alumnos
							</h2>
							<div class="files-student__body je-scroll">
								<div class="data-table je-scroll">
									<div class="data-table__header">
										<div class="data-table__row data-table__row-border">
											<div class="data-table__col data-table__col--header data-table__col--numeration data-table__col--w50">
												<span class="data-table__text">Nº</span>
											</div>
											<div class="data-table__col data-table__col--header">
												<span class="data-table__text">Alumno</span>
											</div>
											<div class="data-table__col data-table__col--header data-table__col--ultimaModificacion data-table__col--hidden-m">
												<span class="data-table__text">Fecha de registro</span>
											</div>
											<div class="data-table__col data-table__col--header data-table__col--w50">
												<span class="data-table__text">Doc. Alumno</span>
											</div>
											<div class="data-table__col data-table__col--header data-table__col--w50">
												<span class="data-table__text">Nota</span>
											</div>
											<div class="data-table__col data-table__col--header">
												<span class="data-table__text">Comentario</span>
											</div>
											<div class="data-table__col data-table__col--header data-table__col--w50 data-table__col--save">
												<span class="data-table__text"></span>
											</div>
										</div>
									</div>
									<div class="data-table__body">
										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			`;
			return modalContainer;
		};
		
		/*Modal que permite al estudiante actualizar su archivo*/
		const createModalUploadAlumn = async (data) => {
			const containerUploadAl = document.createElement("div");
			containerUploadAl.innerHTML = `
				<div class="mod mod--active mod--upload-student">
					<div class="mod__inner mod__inner--pd-1">
						<div class="upload-student">
							<i class="upload-student__close mod__close mod__close--dark fas fa-close"></i>
							<h2 class="upload-student__title">
								Subir Archivo
							</h2>
							<div class="upload-student__body">
								<form class="upload-student__form">
									<div class="upload-student__group">
										<div class="upload-btn">
											<button class="upload-btn__button je-btn je-btn--fill" type="button">
												<i class="upload-btn__icon fas fa-upload"></i>
											</button>
											<span class="upload-btn__button-name">${data.nombreArchivo2}</span>
											<input class="upload-btn__input" style="display:none;" type="file" name="file">
										</div>
									</div>
									<input type="hidden" name="action" value="subirArchivo">
									<input type="hidden" name="rutaArchivo" value="${data.rutaArchivo2}">
									<input type="hidden" name="nombreArchivoAnterior" value="${data.nombreArchivo2}">
									<input type="hidden" name="idTraAlu" value="${data.idTraAlu}">
									<input type="hidden" name="idTrabajo" value="${data.idTrabajo}">
									<div class="upload-student__group upload-student__buttons">
										<input type="submit" class="updload-student-save je-btn je-btn--smaller" value="Cargar archivo">
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			`;
			document.body.appendChild(containerUploadAl);
			containerUploadAl.addEventListener('click',function(e){
				if(e.target.classList.contains("upload-student__close")){
					this.remove();
				}
			});
			const inputButton = containerUploadAl.querySelector(".upload-btn__button"),
				  fileName = containerUploadAl.querySelector(".upload-btn__button").nextElementSibling,
				  inputFile = fileName.nextElementSibling,
				  inputForm = containerUploadAl.querySelector(".upload-student__form");
		    let rutaArchivoAnterior,nombreArchivoAnterior;
			ELEMENTS.INPUT_FILE.changeUpdloadFile(inputButton,inputFile,fileName);
			let mensaje = "";
			inputForm.addEventListener('submit',async ee=>{
				ee.preventDefault();
				let formDat = new FormData(inputForm);
				let load = loader();
				formDat.append("nuevoNombreArchivo",fileName.textContent);
				try{
					if(fileName.textContent != ""){
						const response = await API.getData('alumnosTrabajo',{
							method: 'POST',
							body: formDat
						})
						mensaje = response.mensaje;
						if(response.estado){
							containerUploadAl.remove();
							await setDataTable('tableWork');
						}						
					}else{
						mensaje = "Seleccione un archivo para subir";
					}
				}catch(Error){
					if(e.status === 401){
						location.href = "login";
					}else if(e.status === 403){
						mensaje = "Usted no tiene privilegios suficientes";
					}else if(e.status === 404){
						mensaje = "Ocurrió algo inesperado";
					}
				}finally{
					load.remove();
					showModalMessage(mensaje,3000);
				}
			});
		};
		
		/*Para validar las url*/
		const validateUrl = (txt) => {
			return /^(?:https|http|ftp):(?:\\\\|\/\/).+$/.test(txt);
		};
		
		/*Para cambiar el título del modal, esto depende de la opción seleccionada
		 * sea agregar contenido, clases, trabajos.*/
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
		
		/*
		 * Método que nos permite mostrar el modal, para que los docentes puedan,
		 * actualizar sea el contenido, clase, trabajo.
		 * */
		const showModalUpdate = async (tableName,data) => {
			$modalOptions.classList.add("mod--active");
			changeTitleModal("Actualizando");
			ELEMENTS.removeClass($tagSections,["tag__section--active"]);
			let $form;
			switch(tableName){
				case 'tableContent':
					$form = $modalOptions.querySelector(".tag__section--content .tag__form");
					$form[0].value = data.descContenido;
					$form[1].value = data.link;
					$form[3].value = data.idContenido;
					$form[5].value = data.codContenido;
					$form.parentElement.classList.add("tag__section--active");
					flagUpdateContent = true;
					break;
				case 'tableClass':
				    $form = $modalOptions.querySelector(".tag__section--class .tag__form");
					$form[0].value = data.descClase;
					$form[1].value = data.link;
					$form[2].value = data.fechaClase;
					$form[3].value = data.horaClase;
					$form[5].value = data.idClass;
					$form[6].value = data.codClase;
					$form.parentElement.classList.add("tag__section--active");
					flagUpdateClass = true;
					break;
				case 'tableWork':
					$form = $modalOptions.querySelector(".tag__section--work .tag__form");
					$form.querySelector(".tag__file-name").textContent = data.nombreArchivo;
					$form[0].value = data.descTrabajo;
					$form[3].value = data.fechaIni;
					$form[5].value = data.fechaFin;
					$form[6].checked = data.flagLimite;
					$form[7].disabled = !data.flagLimite;
					$form[7].value = data.diasLimite;
					$form[8].checked = data.replicar_todos;
					$form[9].value = data.idTrabajo;
					$form[10].value = data.codTrabajo;
					$form[11].value = data.rutaArchivo;
					$form[12].value = data.nombreArchivo;
					$form.querySelector(".tag__file-delete").classList.add("tag__file-delete--active");
					flagUpdateWork = true;
					break;
			}	
			$form.parentElement.classList.add("tag__section--active");
			if($form){
				$form.parentElement.firstElementChild.classList.add("tag__return--hidden");
			}
		};
		
		/*Método que nos permite limpiar la data, sea de las tablas, además de ocultar
		 *el contenedor de tablas y limpiar el nombre del docente.*/
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
		 * Validaciones en el lado del cliente de los formularios
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
			}if(form[6].checked){
				if(form[7].value === ''){
					mensaje = "Especifique la prórroga";
				}else if(form[7].value === '0'){
					mensaje = "La prórroga debe ser mayor a 0";
				}
			}else{
				let fechI = new Date(form[3].value.trim().replace("T"," ")).getTime();
				let fechF = new Date(form[5].value.trim().replace("T"," ")).getTime();
				if(fechI > fechF){
					mensaje = "La fecha inicial no puede ser mayor que la fecha fin";
				}
			}
			return mensaje;
		};
		
		const validateMinDateInputs = () => {
			const $inputsDateTime = Array.from($modalOptions.querySelectorAll("input[type=datetime-local]"));
			const $inputDate = Array.from($modalOptions.querySelectorAll("input[type=date]")); 
			const date = new Date(),
			      agnoMonthDayWork = ELEMENTS.INPUT_DATE.getDateFormat(date),
			      agnoMonthDayClass = ELEMENTS.INPUT_DATE.getDateFormat(date,false);
			$inputsDateTime.forEach(input=>{
				input.min = agnoMonthDayWork;
			});
			$inputDate.forEach(input=>{
				input.min = agnoMonthDayClass;
			})
		};
		
		const init = () => {
			addTables();
			addListeners();
			validateMinDateInputs();
		}
		
		init();
		
	});
})(window,document);