(()=>{
	document.addEventListener('DOMContentLoaded',e=>{
		
		const list = document.querySelector(".tag__list");
		const tag = document.querySelector(".tag__wrapper");
		const tagForms = Array.from(tag.querySelectorAll("form"));
		const tagsReturn = Array.from(document.querySelectorAll(".tag__return"));
		const header = document.querySelector(".resource__header");
		const weave = document.querySelector(".resource__wave-path");
		const resource = document.querySelector(".resource");
		
		list.addEventListener('click',ee=>{
			if(ee.target.classList.contains("tag__item") ||
					ee.target.classList.contains("tag__link")){
				tag.style.transform = `translateX(${ee.target.dataset.num*-100}%)`;
				header.innerHTML = `<p class="resource__title" style="animation:fromTopToBottom 250ms 1 linear;">${ee.target.dataset.title}</p>`;
				resource.style = `--bg--dinamic:${ee.target.dataset.color}`;
				restart();
			}
		});
		tagsReturn.forEach(tagR=>{
			tagR.addEventListener('click',e=>{
				tag.style.transform = `translateX(0%)`;
				header.innerHTML = `<p class="resource__title" style="animation:fromTopToBottom 250ms 1 linear;">Seleccione una opción</p>`;
			});
		});
		
		const restart = ()=>{
			tagForms.forEach(form=>{
				form.reset();
			});
			let tagReturnIcon = Array.from(tag.querySelectorAll(".tag__return--hidden"));
			let iconClearNameFile = Array.from(tag.querySelectorAll(".tag__file-delete"));
			tagReturnIcon.forEach(icon=>{
				icon.classList.remove("tag__return--hidden");
			});
			iconClearNameFile.forEach(icon=>{
				icon.classList.remove("tag__file-delete--active");
			});
		};
		
	});
})();