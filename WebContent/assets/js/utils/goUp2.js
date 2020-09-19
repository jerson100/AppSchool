const goUp = (tam=140) => {
	const container = document.createElement("div");
	container.setAttribute("class","goUp");
	container.innerHTML = `
		<i class="goUp__icon fas fa-angle-up"></i>
	`;
	document.addEventListener('scroll',e=>{
		if(document.documentElement.scrollTop > tam){
			container.classList.add("goUp--active");
		}else{
			container.classList.remove("goUp--active");
		}
	});
	
	document.body.appendChild(container);
	
	container.addEventListener('click',()=>{
		document.documentElement.scrollIntoView({
			behavior: 'smooth'
		});
	});
	
};

goUp(140);