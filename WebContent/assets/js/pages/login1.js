'use-strict';
((window,document,undefined)=>{
	
	document.addEventListener("DOMContentLoaded", function(event) {
		
		const $frmLogin = document.getElementById("form-login"),
			  $iconView = $frmLogin.querySelector(".input-icon__view .input-icon__icon"),
			  $inputPass = $frmLogin.querySelector("input[type='password'");
		
		$frmLogin[0].focus();
		
		$frmLogin.addEventListener("submit",async e=>{
	        e.preventDefault();
	        await login();
	    });
	    
	    const verifyEmpty = () => {
	    	let status = true;
	    	if($frmLogin[0].value === ''){
	    		$frmLogin[0].parentElement
	    				   .classList
	    				   .add("input-icon--error");
	    		status = false;
	    	}else{
	    		$frmLogin[0].parentElement
				   .classList
				   .remove("input-icon--error");
	    	}
	    	
	    	if($frmLogin[2].value === ''){
	    		$frmLogin[2].parentElement
				   .classList
				   .add("input-icon--error");
	    		status = false;
	    	}else{
	    		$frmLogin[2].parentElement
				   .classList
				   .remove("input-icon--error");
	    	}
	    	$frmLogin[3].disabled = !status;
	    };
	    
	    const validateNumberAndLetter = (event) => {
	    	console.log(event);
	    	if(event.target.value.length >= 20 &&
	           event.keyCode != 8 && 
	           event.keyCode != 37 &&
	           event.keyCode != 39 &&
	           event.keyCode != 46 &&
	           event.keyCode != 17){
	    		event.preventDefault();
	    	}
	    }
	    
	    const addListener = () => {
	    	$frmLogin[0].addEventListener('keyup',verifyEmpty);
	    	$frmLogin[0].addEventListener('keydown',validateNumberAndLetter);
	    	$frmLogin[0].addEventListener('paste',(e)=>{
	    		if($frmLogin[0].value.length + e.clipboardData.getData('text').length >= 20){
	    			e.preventDefault();
	    		}
	    	});
	    	$frmLogin[2].addEventListener('keyup',verifyEmpty);
	    	$frmLogin[2].addEventListener('keydown',validateNumberAndLetter);
	    	$frmLogin[2].addEventListener('paste',(e)=>{
	    		if($frmLogin[2].value.length + e.clipboardData.getData('text').length >= 20){
	    			e.preventDefault();
	    		}
	    	});
	    	$iconView.addEventListener('click',e=>{
	    		$iconView.classList.toggle("fa-eye-slash");
	    		$iconView.classList.toggle("fa-eye");
	    		if($iconView.classList.contains("fa-eye-slash")){
	    			$inputPass.type="password";
	    		}else{
	    			$inputPass.type="text";
	    		}
	    	});
	    };
	    
	    const showMessage = (text) => {
	    	showModalMessage(text,5000,{
	    		x: 'right',
	    		y: 'bottom'
	    	},{
	    		open: 'movetoY_bottom_to_top 1s 1 ease',
	    		close: 'movetoY_top_to_bottom 5s 1 ease'
	    	});
	    };
	    
	    const login = async () => {
	    	let load = loader();
	    	$frmLogin[3].disabled = true;
	    	try{
	    		const data = await API.getData('login',{
	    			method: 'POST',
	    			body: new FormData($frmLogin)
	    		});
	    		if(data && data.estado){
	    			localStorage.setItem('idPerfil', data.idPerfil);
	 	            location.href = "inicio";
	    		}else{
		 	        showMessage(data.mensaje);
	    		}
	    	}catch(e){
	    	}finally{
	    		load.remove();
	    		$frmLogin[3].disabled = false;
	    	}
	    };
	   
	    addListener();
	    
	});
    
})(window,document);

