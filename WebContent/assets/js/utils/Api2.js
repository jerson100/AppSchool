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
			if(response.status == 401){
				window.location = "login";
			}else{
				throw response;				
			}
		}else{
			return await response.json();
		}
	},
	getCursos: async function(idSecGraNiv){
		return await this.getData(`cursos?idSecGraNiv=${idSecGraNiv}&action=getCursos`)
	},
	CICLOS: {
		get: async function(){
			console.log(this);
		return;
		}
	},
	ALUMNO: {
		NOTAS:{
			//idCiclo=${idCiclo}&idSecCurPro=${idSecCurPro}&action=${action}
			//{action="listarMapNotes", ...rest}
			get: async function(params={}){
				let queryParams =  "";
				const keys = Object.keys(params);
				keys.forEach(k=>queryParams += `${k}=${params[k]}&`); 
				queryParams.replace(/&$/gi,"");
				return await API.getData(`alumno/notas${queryParams !== ""?"?":""}${queryParams}`);
			},
			post: async function(headers={}){
				return await API.getData(`alumno/notas`,headers);
			}
		}
	},
	PERIODO: {
		get: async function(idCiclo, idSecCurPro){
			return await API.getData(`periodoNotas?idCiclo=${idCiclo}&idSecCurPro=${idSecCurPro}`);
		}
	}
};