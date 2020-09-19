function loader(parent){
	
	let el = document.createElement("div");
	
	if(parent){
		
		el.setAttribute("class","je-loader-container");
		
		parent.appendChild(parent);
		
	}else{
		
		el.setAttribute("class","je-loader-container full");
	
		document.body.appendChild(el);
		
	}
	
	el.innerHTML = `
		<div class="je-loader"></div>
	`;
	
	return el;
}