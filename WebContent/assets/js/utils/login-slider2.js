"use-strict";

function SliderLogin({container, images, time = 2000}){
	this.$parent = document.querySelector(container);
	this.$info = document.createElement("p");
	this.$sliderContainer = null;
	this.$currentItem = null;
	this.interval = null;
	
	function init(){
		generateLoginSlider();
		startAnimation();
	}
	
	function startAnimation() {
		let direction = 1;
		let currentItem = 0;
		const $sliderList = this.$sliderContainer.querySelector(".login-slider__list");
		const childs = Array.from($sliderList.children);
		let $desktop = this.$sliderContainer.querySelector(".login-slider__desktop");
		const countImage = childs.length;
		
		const mov = () => {
			if(currentItem === countImage-1){
				direction = -1;
			}else if(currentItem === 0){
				direction = 1;
			}
			currentItem += direction;
			//$info.textContent = `${currentItem + 1} de ${countImage}`;
			$sliderList.style.transform = `translateY(-${currentItem * 100}%)`;
			//childs.forEach(c => c.classList.remove("login-slider__item--active"));
			if($currentItem){
				$currentItem.classList.remove("login-slider__item--active");
			}
			$currentItem = childs[currentItem];
			$currentItem.classList.add("login-slider__item--active");
			setTimeout(() => {
				$desktop.style.backgroundImage = `url(${images[currentItem]})`;
				//void $desktop.offsetWidth; //reflujo
				//clonando
				$desktop.style.animation = "scale 1.5s 2 ease alternate";
				$desktop.style.backgroundImage = `url(${images[currentItem]})`;
				const clone =  $desktop.cloneNode(true);
				console.log($desktop);
				$desktop.parentNode.replaceChild(clone, $desktop);
				$desktop = clone;
			}, 1000);
		}
		
		$desktop.style.backgroundImage = `url(${images[0]})`;
		
		this.interval = setInterval(mov, time); 
	}
	
	function generateLoginSlider(){
		const div = document.createElement("div");
		div.classList.add("login-slider");
		const list = generateImagesListContainer(this.images);
		div.appendChild(list);
		div.appendChild(generateDesktop());
		this.$sliderContainer = div;
		
		this.$parent.appendChild(this.$sliderContainer);
	};
	
	function generateDesktop(){
		const div = document.createElement("div");
		div.classList.add("login-slider__desktop");
		return div;
	}

	function generateImagesListContainer(){
		const list = document.createElement("ul");
		list.setAttribute("class","login-slider__list");
		const fragment = document.createDocumentFragment();
		images.forEach(image => fragment.appendChild(generateRow(image)));
		list.appendChild(fragment);
		return list;
	};

	const generateRow = (urlImage) => {
		const row = document.createElement("li");
		row.classList.add("login-slider__item");
		row.style.backgroundImage = `url(${urlImage})`;
		return row;
	};

	init();
	
	return this;
	
}
/*
const sliderLogin = (container, images, time=2000) => {
	
	const $container = document.querySelector(container); //parent element
	
	const {sliderContainer, sliderList} = generateLoginSlider(images);
	
	$container.appendChild(sliderContainer);
	
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
	
const generateLoginSlider = (images) => {
	const div = documnet.createElement("div");
	div.classList.add("login-slider");
	const list = generateImagesListContainer(images);
	div.appendChild(list);
	return {
		sliderContainer: div,
		sliderList: list
	};
};

const generateImagesListContainer = (images) => {
	const list = document.createElemnt("ul");
	list.setAttribute("class","login-slider__list");
	const fragment = document.createDocumentFragment();
	images.forEach(image => fragment.appendChild(generateRow(image)));
	list.appendChild(fragment);
	return list;
};

const generateRow = (urlImage) => {
	const row = document.createElement("li");
	row.classList.add("login-slider__item");
	row.style.backgroundImage = `url(${urlImage})`;
	return div;
};
*/