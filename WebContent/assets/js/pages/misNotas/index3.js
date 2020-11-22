'use-strict';
((document, window, undefinied)=>{
	document.addEventListener('DOMContentLoaded',e=>{
		const $tagOne = document.querySelector(".tag-one-window");
		const $tagOneList = $tagOne.firstElementChild;
		const $tagData = $tagOne.lastElementChild;
		const $tableRegisterContainer = document.getElementById("tableRegister");
		
		$tagOneList.addEventListener("click",async e=>{
			const {target} = e;
			if(target.tagName==="LI"){
				ELEMENTS.removeClass(Array.from($tagOneList.children),["tag-one-window__item--current","dark-negative"]);
				target.classList.add("tag-one-window__item--current","dark-negative");
				$tagData.classList.remove("hidden");
				new TableRegister($tableRegisterContainer,
					{
						ciclo:{value: target.textContent,id:target.dataset.id},
						idSecCurPro: 0
					},
					"alumno"
				);
			}
		});
		
	});	
	
})(document, window);
