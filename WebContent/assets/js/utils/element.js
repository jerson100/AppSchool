const ELEMENTS = {
		nodeType: {
			ELEMENT_NODE : 1,
			TEXT_NODE : 3,
			DOCUMENT_FRAGMENT_NODE: 11
		},
		removeClass : function(elements, classSelectors){
			if(elements && classSelectors){
				elements.forEach(element => {
					if(element.nodeType === this.nodeType.ELEMENT_NODE){
						classSelectors.forEach(selector=>{
							element.classList.remove(selector);
						});
					}
				});
			}
		},
		INPUT_FILE:{
			changeUpdloadFile : function($button, $inputFile, $txtContainer) {
				if($inputFile && $txtContainer && $button){
					let rspt;
					$button.addEventListener('click',e=>{
						$inputFile.click();
					});
					$inputFile.addEventListener('change',async (e)=>{
						if($inputFile.files.length > 0){
							let mod = this.createModalVerify();
							setTimeout(async ()=>{
								rspt = await this.sendVerifyExtension($inputFile.files[0]);
								mod.remove();
								if(rspt != ''){
									$txtContainer.textContent = '';
									$inputFile.value = "";
								}else{
									rspt = "Archivo válido";
									$txtContainer.textContent = $inputFile.files[0].name;
								}
								showModalMessage(rspt,3000);
							}, 2000);
						}else{
							$txtContainer.textContent = "";
						}
					});
				}
			},
			sendVerifyExtension: async function(file){
				/*console.log(file);*/
				let fomrData = new FormData();
				fomrData.set("action","validar");
				fomrData.set("name",file.name);
				fomrData.set("size",file.size);
				let msg = "";
				try{
					const response = await API.getData('tipoArchivo',{
						method: 'POST',
						body: fomrData
					});
					if(response.estado){
						msg = "";
					}else{
						msg = response.mensaje;
					}
				}catch(error){
					if(error.status == 401){
						location.href = "login";
					}else if(error.status === 403){
						msg = "Usted no tiene privilegios para realizar esta acción";
					}else if(error.status === 404){
						msg  = "Ocurrió algo inesperado";
					}
				}finally{
					return msg;
				}
			},
			createModalVerify: function(){
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
			}
		},
		INPUT_DATE:
		{
			getDateFormat: function(date, time = true){
				let rspt = (date.getFullYear().toString() + '-' 
				           + ("0" + (date.getMonth() + 1)).slice(-2) + '-' 
				           + ("0" + (date.getDate())).slice(-2));
				if(time){
					rspt += 'T' + date.toTimeString().slice(0,5);
				}
				return rspt;
		    }
		}
}