'use-strict';
((window,document,undefined)=>{
	
	document.addEventListener("DOMContentLoaded", function(event) {
		
		const $frmLogin = document.getElementById("form-login"),
			  $iconView = $frmLogin.querySelector(".input-icon__view .input-icon__icon"),
			  $inputPass = $frmLogin.querySelector("input[type='password'");
		
		$frmLogin[0].focus();
		
		$frmLogin.addEventListener("submit",async e=>{
	    	
	    	//solo por ahora
	        e.preventDefault();
	    	
	        verifyEmpty();
	        
	        await login();
	        
	    });
	    
	    const verifyEmpty = () => {
	    	
	    	let status = true;
	    	
	    	if($frmLogin[0].value === ''){
	    		
	    		$frmLogin[0].parentElement
	    				   .classList
	    				   .add("input-icon--error");
	    		
	    		status = false;
	    		
	    	}
	    	
	    	if($frmLogin[2].value === ''){
	    		
	    		$frmLogin[2].parentElement
				   .classList
				   .add("input-icon--error");
	    		
	    		status = false;
	    		
	    	}
	    	
	    	$frmLogin[3].disabled = !status;
	    	
	    };
	    
	    const validateNumberAndLetter = (event) => {
	    	//console.log(event)
	    	if(event.target.value.length >= 20){
	    		event.preventDefault();
	    	}
	    	/*if(/^[^0-9|a-z]$/gi.test(event.key) && ){
	    		event.preventDefault();
	    	}*/
	    }
	    
	    const addListener = () => {
	    	
	    	$frmLogin[0].addEventListener('keyup',(ev)=>{
	    		$frmLogin[0].parentElement
				   .classList
				   .remove("input-icon--error");
	    		console.log(ev.target.value.length);
	    		verifyEmpty();
	    	});
	    	
	    	$frmLogin[2].addEventListener('keyup',(ev)=>{
	    		$frmLogin[2].parentElement
				   .classList
				   .remove("input-icon--error");
	    		verifyEmpty();
	    	});
	    	
	    	$frmLogin[0].addEventListener('keydown',validateNumberAndLetter);
	    	$frmLogin[2].addEventListener('keydown',validateNumberAndLetter);
	    	
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
	    		console.log(e);
	    	}finally{
	    		load.remove();
	    		$frmLogin[3].disabled = false;
	    	}
	    };
	   
	    addListener();
		
	});
    
})(window,document);

