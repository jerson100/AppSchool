const ELEMENTS = {
		nodeType: {
			ELEMENT_NODE : 1,
			TEXT_NODE : 3,
			DOCUMENT_FRAGMENT_NODE: 11
		},
		removeClass : function(elements, classSelectors){
			if(elements && classSelectors){
				elements.forEach(element => {
					if(element.nodeType === this.nodeType.ELEMENT_NODE){
						classSelectors.forEach(selector=>{
							element.classList.remove(selector);
						});
					}
				});
			}
		}
}