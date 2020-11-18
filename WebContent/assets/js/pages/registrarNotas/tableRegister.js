class TableRegister {
	constructor(container, dataHeaders={courses, grades, ciclo, idSecCurPro}, type="alumno"){
		this.$container = container;
		this.headers =  dataHeaders;
		this.type = type;
		this.updateData();
	}
	
	createTable () {
		const table = document.createElement("table");
		table.classList.add("grid__table","grid__table--large");
		table.border = "";
		table.classList.add("grid__table--hover");
		this.table =  table;
		this.$container.appendChild(table);
		this.drawHeader();
		this.drawBody();
		this.handleClick();
	}
	
	async updateData(){
		let load = loader();
		this.$container.innerHTML = "";
		try{
			const [{data:dataAlumnos},{data:dataPeriodos}] = await Promise.all([
				API.ALUMNO.NOTAS.get({
					idCiclo: this.headers.ciclo.id,
					idSecCurPro: this.headers.idSecCurPro,
					action: "listarMapNotes"
				}),
				API.PERIODO.get(this.headers.ciclo.id, this.headers.idSecCurPro)
			]);
			this.dataAlumnos = dataAlumnos || [];
			this.dataPeriodo =  dataPeriodos || [];
			this.createTable();
		}catch(e){
			console.log(e);
			this.$container.innerHTML = "<p style='text-align:center'>Puede que no exista registros.</p>";
		}finally{
			load.remove();
		}
	}
	
	handleClick(){
		this.table.addEventListener('click',async e=>{
			if(e.target.tagName === "BUTTON" && e.target.classList.contains("je-btn")){
				const load = loader();
				try{
					const idPeriodoNotas = e.target.dataset.idperiodonotas ;
					const idciclo = e.target.dataset.idciclo ;
					const {data} = await API.ALUMNO.NOTAS.get({
						idPeriodoNotas: idPeriodoNotas,
						idSecCurPro: this.headers.courses.idSecCurPro,
						action: "listar"
					});
					showModalTableRegister(data, this.headers.courses.idSecCurPro, idPeriodoNotas, idciclo, this);
				}catch(e){
					console.log(e);
				}finally{
					load.remove();					
				}
			}
		});
	}
	
	drawHeader() {
		const thead = document.createElement("thead");
		
		const schoolTr = document.createElement("tr");
		const teacherTr = document.createElement("tr");
		const periodosTr = document.createElement("tr");
		const notasColumnsTr = document.createElement("tr");
		
		thead.classList.add("grid__thead");
		schoolTr.classList.add("grid__row");
		teacherTr.classList.add("grid__row");
		periodosTr.classList.add("grid__row");
		notasColumnsTr.classList.add("grid__row");
		
		const periodoskeys = Object.keys(this.dataPeriodo);
		let countAllColumns = 0;
		
		const fragmentPeriodos = document.createDocumentFragment();
		const fragmentColumnsPeridos = document.createDocumentFragment();
		
		fragmentColumnsPeridos.appendChild(this.createCol("th",[{attr:"class",val:"grid__col"},{attr:"width",val:"40"},{attr:"colspan",val:"1"}],`Nº`));
		fragmentColumnsPeridos.appendChild(this.createCol("th",[{attr:"class",val:"grid__col"},{attr:"width",val:"400"},{attr:"colspan",val:"3"}],`Apellidos y nombres`));
		
		periodoskeys.forEach((k)=>{
			const per = this.dataPeriodo[k];
			countAllColumns += per.length;
			fragmentPeriodos.appendChild(this.createCol("th",[{attr:"class",val:"grid__col grid__col--color-blue"},{attr:"colspan",val:per.length},{attr:"rowspan",val:"1"}],
				`
					${k}
				`
			));					
			per.forEach((p, index) => {
				fragmentColumnsPeridos.append(this.createCol("th",[{attr:"class",val:"grid__col"},{attr:"width",val:"65"},{attr:"rowspan",val:"1"}],
					`
						${index != per.length -1  ? `<button class="je-btn je-btn--short" data-idPeriodoNotas=${p.idPeriodoNotas}  data-idCiclo=${p.periodo.ciclo.idCiclo}>${p.notas.descNotas}</button>`:p.notas.descNotas}
					`
				));
			});
		});
	
		fragmentPeriodos.appendChild(this.createCol("th",[{attr:"class",val:"grid__col grid__col--color-blue"},{attr:"width",val:"40"},{attr:"colspan",val:"1"},{attr:"rowspan",val:"2"}],`
			PR F
		`));
		
		periodosTr.appendChild(fragmentPeriodos);
		
		notasColumnsTr.appendChild(fragmentColumnsPeridos);
		
		schoolTr.appendChild(this.createCol("th",[{attr:"class",val:"grid__col"},{attr:"colspan",val:"4"},{attr:"rowspan",val: "3"}],
			`
				<div class="registro-evaluaciones__school">
					<img src="assets/img/static/logoColegio.jpg" class="registro-evaluaciones__logo">
					<span class="registro-evaluaciones__school-name">Escuela Privada Uribe School</span>
				</div>
			`
		));
		
		schoolTr.appendChild(this.createCol("th",[{attr:"class",val:"grid__col"},{attr:"colspan",val:countAllColumns  + 1},{attr:"rowspan",val: 1}],
			`
				Registro auxiliar de evaluación - ${this.headers.ciclo.value} - 2020 ${this.headers.grades.value} - ${this.headers.courses.descCurso})
			`
		));
		
		teacherTr.appendChild(this.createCol("th",[{attr:"class",val:"grid__col"},{attr:"colspan",val:countAllColumns + 1},{attr:"rowspan",val: 1}],
			`
				Profesor: ${this.headers.courses.profesor} - Curso: ${this.headers.courses.descCurso}
			`
		));
		
		thead.appendChild(schoolTr);
		thead.appendChild(teacherTr);
		thead.appendChild(periodosTr);
		thead.appendChild(notasColumnsTr);
		
		this.table.append(thead);
	}
	
	createCol(type, attrs=[{attr, val}], content) {
		const el = document.createElement(type);
		attrs.forEach(at=>el.setAttribute(at.attr, at.val));
		el.innerHTML = content;
		return el;
	}
	
	drawBody(){
		const tbody = document.createElement("tbody");
		const namesStudents = Object.keys(this.dataAlumnos);//notes
		
		const fragment = document.createDocumentFragment();
		
		let count = 1;
		
		namesStudents.forEach(n =>  {
			const notes = this.dataAlumnos[n];
			const tr = document.createElement("tr");
			const fragmentCol = document.createDocumentFragment();
			
			//add enum and name(curse - name)
			fragmentCol.append(this.createCol("td",[{attr:"class",val:"grid__col"},{attr:"width",val:"30"},{attr:"colspan",val:"1"}],count++));
			fragmentCol.append(this.createCol("td",[{attr:"class",val:"grid__col"},{attr:"width",val:"200"},{attr:"colspan",val:"3"}], n));
			
			notes.forEach(nn => {
				fragmentCol.append(this.createCol("td",[{attr:"class",val:`grid__col  ${nn.descNotas === "PROM." ? " grid__col--color-blue":""}`},{attr:"width",val:"40"},{attr:"colspan",val:"1"}],nn.nota || "..."));
			});
			
			fragmentCol.append(this.createCol("td",[{attr:"class",val:"grid__col grid__col--color-blue"},{attr:"width",val:"40"},{attr:"colspan",val:"1"}],"..."));
			
			tr.appendChild(fragmentCol);
			fragment.append(tr);
		});
		
		tbody.append(fragment);
		this.table.appendChild(tbody);
	}
	
}
