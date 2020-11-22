const showModalMessage = (message, time, automatic_close=true, position = {x:'left',y:'top'},animate = {open:'animate_default_open 2s 1 ease',close:'animate_default_close 2s 1 ease'}) => {
	 
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
								<p style="font-size: 13px;padding: 14px;line-height: 1.4;margin: 0;">${message}</p>
						    </div>       
		        	</div>
		        `,
		        automatic_close: automatic_close,
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
           			<div style="display:flex;align-items:center;margin-bottom: .5rem;">
						<label style="margin: 0 .5rem 0 0;font-size:14px;">Replicar * </label>
						<input style="margin: 0;width:20px;height:20px;" name="replicartodos" type="checkbox" id="replicar_todos">           				
           			</div>
					<span>* Si marca replicar, afectará a las demás secciones del mismo grado.</span>
	            </div>
			</div>
		`;
	}else{
		checkC = `<p class="padding-left:20px;padding-right: 20px;">${body}</p>`;
	}
	const modal = new BigModal({
		"header": `
			<div class="flex spacebetween">
				<span style="font-size: 15px;margin: 0;font-weight:bold;padding-bottom:.5rem;
				">${title}</span>
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
		"max_width": "500px",
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

