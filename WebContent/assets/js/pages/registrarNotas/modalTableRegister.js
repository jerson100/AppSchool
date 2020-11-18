const temple = () => {
	 return `
		<div class="je-modal__document je-modal__document--small je-modal__document--vertical">
			<div class="je-modal__content">
				<div class="je-modal__header">
					<p class="je-modal__title">Agregar notas</p>
				</div>
				<div class="je-modal__body">
					<div class="grid je-scroll">
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
				<div class="je-modal__footer">
					<button class="je-btn je-btn--smaller" id="guardarNota">Guardar Nota</button>
					<button class="je-btn je-btn--smaller" id="close">Cancelar</button>
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

const showModalTableRegister = (alumnos=[], idSecCurPro, idPeriodoNotas, idCiclo, table) => {
	
	const prevContainerModal = document.querySelector(".je-modal__document");
	
	if(prevContainerModal){
		prevContainerModal.remove();
	}
	
	const containerModal = document.createElement("div");
	containerModal.classList.add("je-modal","je-scroll");
	containerModal.innerHTML = temple();
	
	const gridtbody = containerModal.querySelector(".grid__tbody");
	gridtbody.appendChild(drawRow(alumnos));
	
	document.body.appendChild(containerModal);
	
	const $guardarNota = document.getElementById("guardarNota");
	const $close = document.getElementById("close");
	
	$guardarNota.addEventListener("click",async e=>{
		const inputs = Array.from(containerModal.querySelectorAll("[data-idRegistroNota]"));
		const values = inputs.map(i=>{
			return ({
				idRegistroNota: i.dataset.idregistronota !== "null" ? parseInt(i.dataset.idregistronota) : null,
						idSecCur: parseInt(idSecCurPro),
						idCuenta: parseInt(i.dataset.idcuenta),
						nota: i.value,
						idPeriodoNotas
					});
		} 
		);
		const load = loader();
		try{
			const {message} = await API.ALUMNO.NOTAS.post({
				method: "POST",
				"content-type":"application/json",
				body: JSON.stringify(values)
			});
			console.log(message);
			containerModal.remove();
			table.updateData();
		}catch(e){
			console.log(e);
		}finally{
			load.remove();
		}
	});
	
	$close.addEventListener("click",e=>{
		containerModal.remove();
	});
	
};