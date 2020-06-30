const showModalMessage = (message,time, position = {x:'left',y:'top'},animate = {open:'animate_default_open 2s 1 ease',close:'animate_default_close 2s 1 ease'}) => {
	
	let modalPrev = document.querySelector(".je-modal-container_message");
	if(modalPrev){
		modalPrev.remove();
	}
	let modal = new MessageModal(
		    {
		        body : `
		            <div style="display: flex;">
						   <i class="fas fa-warning" aria-hidden="true" style="
						    flex: 0 0 50px;
						    padding: 1rem;
						    text-align: center;
						    display: flex;
						    color: orange;
						    align-items: center;
						    justify-content: center;"></i>
						    <div style="flex: 1 1 0;display: flex;align-items: center;">
								<p style="padding: 1rem;margin:0">${message}</p>
						    </div>       
		        	</div>
		        `,
		        automatic_close: true,
		        time_close: time,
		        timeout_modal: time,
		        position: {
		        	x: position.x,
		        	y: position.y
		        },
		        animation_open: animate.open,
		    	animation_close: animate.close
		    }
		);
	
	modal.open();
	
};

const showModalDelete = (title, callback, check=true,body="") => {
	let replicar  = "",checkC= "";
	if(check){
		checkC = `
			<div class="container-checks">
           		<div class="group-col">
	           		<label>Replicar a todos</label>
					<input name="replicartodos" type="checkbox" id="replicar_todos">
	            </div>
			</div>
		`;
	}else{
		checkC = `<p>${body}</p>`;
	}
	const modal = new BigModal({
		"header": `
			<div class="flex spacebetween">
				<span style="padding: 1rem 0;font-size: 16px;">${title}</span>
			</div>
		`,
		"body":`
			${checkC}
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
		replicarTodos = modal.modal_container.querySelector("#replicar_todos"),
		message;
	
	btnEliminar.addEventListener('click',async function(e){
		message = await callback(replicarTodos);
		console.log("respuesta: "+ message);
		modal.closeAnimation();
		showModalMessage(message,5000);
	});
	btnCancel.addEventListener('click',function(e){
		modal.closeAnimation();
	});
	
};

