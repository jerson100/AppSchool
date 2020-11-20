const temple = (title="Agregar notas") => {
	 return `
		<div class="j-modal__document j-modal__document--small j-modal__document--vertical">
			<div class="j-modal__content">
				<div class="j-modal__header">
					<p class="j-modal__title">${title}</p>
				</div>
				<div class="j-modal__body">
					<div class="grid je-scroll" style="max-height: 70vh;">
						<table class="grid__table grid__table--hover" border>
							<thead class="grid__thead">
								<tr class="grid__row">
									<th class="grid__col" width="40">Nº</th>
									<th class="grid__col" >Alumno</th>
									<th class="grid__col grid__col--color-blue" width="60">Nota</th>
								</tr>
							</thead>
							<tbody class="grid__tbody"></tbody>
						</table>
					</div>
				</div>
				<div class="j-modal__footer">
					<div style="text-align:center">
				    	<button class="je-btn je-btn--smaller" id="guardarNota">Guardar Nota</button>
						<button class="je-btn je-btn--smaller" id="close">Cancelar</button>
					</div>
				</div>
			</div>
		</div>
	`;
};

const drawRow = (alumnos) => {
	
	const fragment = document.createDocumentFragment();
	
	let c = 0;

	alumnos.forEach(al => {
			
		const tr = document.createElement("tr");
		
		tr.classList.add("grid__row");
		
		tr.innerHTML =  `
			<td class="grid__col" width="40">
				${++c}
			</td>
			<td class="grid__col">
				${al.alumno}
			</td>
			<td class="grid__col grid__col--color-blue" width="60">
				<input class="input" data-idCuenta=${al.idCuenta} data-idRegistroNota=${al.idRegistroNota ? al.idRegistroNota : "null"} type="text" style="width: 55px;text-align:center;" value=${al.nota ? al.nota:""}>
			</td>
		`;
		
		fragment.appendChild(tr);
		
	});
	
	return fragment;
	
};

const showModalTableRegister = (alumnos=[], idSecCurPro, idPeriodoNotas, idCiclo, table, title) => {
	
	const prevContainerModal = document.querySelector(".j-modal__document");
	
	if(prevContainerModal){
		prevContainerModal.remove();
	}
	
	const containerModal = document.createElement("div");
	containerModal.classList.add("j-modal","je-scroll");
	containerModal.innerHTML = temple(title);
	
	const gridtbody = containerModal.querySelector(".grid__tbody");
	gridtbody.appendChild(drawRow(alumnos));
	
	document.body.appendChild(containerModal);
	
	const $guardarNota = document.getElementById("guardarNota");
	const $close = document.getElementById("close");
	
	$guardarNota.addEventListener("click",async e=>{
		const inputs = Array.from(containerModal.querySelectorAll("[data-idRegistroNota]"));
		const values = [];
		let msg = "";
		for(let i = 0; i < inputs.length; i++){
			values.push(
				{
					idRegistroNota: inputs[i].dataset.idregistronota !== "null" ? parseInt(inputs[i].dataset.idregistronota) : null,
					idSecCur: parseInt(idSecCurPro),
					idCuenta: parseInt(inputs[i].dataset.idcuenta),
					nota: inputs[i].value,
					idPeriodoNotas
				}
			);
			if(!validarNota(inputs[i].value)){
				msg = `La nota ingresada para el alumno ${alumnos[i].alumno} no es válida`;
				break;
			}
		}
		if(msg===""){
			const load = loader();
			try{
				const {message} = await API.ALUMNO.NOTAS.post({
					method: "POST",
					"content-type":"application/json",
					body: JSON.stringify(values)
				});
				showModalMessage("Agregado satisfactoriamente.", 5000);
				containerModal.remove();
				table.updateData();
			}catch(e){
				showModalMessage("Ocurrió un error en el servidor", 5000);
			}finally{
				load.remove();
			}
		}else{
			showModalMessage(msg,5000, false);
		}
	});
	
	$close.addEventListener("click",e=>{
		containerModal.remove();
	});
	
};

const validarNota = (nota) => {
	return nota === "" || /^((\d|([0-2]0|([0-1]\d)))|(20[\.]00)|(((\d|([0-1]0|([0-1]\d))))[\.]\d{1,2}))$/.test(nota);
};