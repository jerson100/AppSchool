function tab(element,actions=[{href,callback}]){
	let $element = document.querySelector(element);
	let $sections = Array.from($element.querySelectorAll("[data-table]"));
	let $elements = Array.from($element.querySelectorAll(".tabs__item"));
	$elements.forEach(tab=>{
		let tag;
		$elements.forEach(tag=>{
			tag.addEventListener('click',e=>{
				let sectionHref = tag.lastElementChild.hash; 
				actions.forEach(action=>{
					if(action.href == sectionHref){
						remClass($elements,"tabs__item--active");
						tag.classList.add("tabs__item--active");
						remClass($sections,"tabs__section--active");
						$element.querySelector(`data-${sectionHref.replace("#","")}`).classList.add("tabs__section--active");
						action.callback();
					}
				});
			});
		});
	})
}

const remClass = (elements,classs) => elements.forEach(el=>el.classList.remove(classs));