const sliderLogin = (container, time=2000) => {
	
	const $container = document.querySelector(container);
	const childs = Array.from($container.children);
	
	const $info = document.createElement("p");
	
	$info.style.position = "absolute";
	$info.style.right = "1rem";
	$info.style.bottom = "1rem";
	$info.style.color = "#fff";
	$info.style.fontSize = "23px";
	$info.style.textShadow = "#ff2419 0px 0px 4px";
	
	$container.parentElement.appendChild($info);
	$container.parentElement.style.position = "relative";
	
	let countImage = $container.children.length;
	
	let currentImage = 0;
	
	let direction = 1;
	
	$info.textContent = `1 de ${countImage}`;
	
	setInterval(() => {
		
		if(currentImage === countImage-1){
			
			direction = -1;
			
		}else if(currentImage === 0){
			
			direction = 1;
			
		}
		
		currentImage += direction;
		
		$info.textContent = `${currentImage + 1} de ${countImage}`;
		
		$container.style.transform = `translateY(-${currentImage * 100}%)`;
		
		setTimeout(() => {
			console.log("ok")
			console.log(childs[currentImage].style.backgroundImage);
			$container.parentElement.style.backgroundImage = `url(${childs[currentImage].style.backgroundImage})`;
		}, 500);
		
	}, time); 
	
};

