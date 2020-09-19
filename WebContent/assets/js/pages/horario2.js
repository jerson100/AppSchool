((window,document,undefinied)=>{
	
	document.addEventListener("DOMContentLoaded", function(event) {
		
		let daysContainer = document.getElementById("days-container"),
		day = Array.from(daysContainer.querySelectorAll(".day-item")),
		mostrarTodo = document.getElementById("mostrarTodo");
	
		daysContainer.addEventListener("click",e=>{
			if(e.target.classList.contains("name-day")){
				e.target.parentElement.classList.toggle("active");
			}
		});
		
		mostrarTodo.addEventListener('click',async e=>{
			let checked = mostrarTodo.checked;
			for(let course of day){
				await showDay(()=>{
					if(checked){
						course.classList.add("active");
						
					}else{
						course.classList.remove("active");
					}
				});
				course.scrollIntoView({block: "center", behavior: "smooth"});
			}
		});
	
		const printDays = async ()=>{
			for(let course of day){
				await showDay(()=>{
					/*course.style.transform = "translate(0,0) rotate(360deg)";*/
					course.style.transform = "scale(1) rotate(360deg)";
				}); 
			};
			mostrarTodo.click();
		};
	
		const showDay = (callback) =>{
			return new Promise((resolve)=>{
				setTimeout(() => {
					callback();
					resolve();
				}, 500);
			});
		};
	
		printDays();
		
	});
	
})(window,document);
