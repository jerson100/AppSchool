'use-strict';
((window,document,undefined)=>{
	
	document.addEventListener("DOMContentLoaded", function(event) {
		
		let btnClose = document.getElementById("cerrarSesion");
		console.log(btnClose);
		btnClose.addEventListener('click',async e=>{
			
			let dat = new FormData();
			
			dat.append("action","cerrarSesion");
			
			try{
			
				let response  = await fetch("login",{
					"method": 'POST',
					"body" : dat
				});
				
				let json = await response.json();
				
				location.href = "login";
			
			}catch(e){
				
				location.href="login";
				
			}
			
		});
		
	});
	
})(window,document);