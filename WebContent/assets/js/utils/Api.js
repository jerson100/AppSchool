const API = {
	/*
	 * Consula al servidor y nos trae información...
	 * params : {
	 * 	method
	 *  body
	 *  headers
	 * }
	 * */
	getData : async function(url,params={}){
		const response = await fetch(url,params);
		if(!response.ok){
			throw response;
		}else{
			return await response.json();
		}
	}
};