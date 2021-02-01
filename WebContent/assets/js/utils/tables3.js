class DataTable {

    constructor(obj) {
        let $ob = this;
        this.DEFAULT = {
            rowsCountView : obj.rowsCount,
            navigator : null,
            data : [],
            dataSearch : [],
            navigator : obj.navigator,
            options: obj.options
        }
        
        this.table = document.querySelector(obj.table);
        this.columns = obj.columns;
        this.actions = obj.actions;
        this.init();
    }

    init() {
        this.createTable();
        this.addListeners();
    }

    createTable() {
		
        const tableParent = this.table.parentElement;
        const tableContainer = document.createElement("div");
        const tableWrapper = document.createElement("div");

        tableContainer.setAttribute("class","table-data-container");
        tableWrapper.setAttribute("class","table-data__wrapper je-scroll");

        tableContainer.appendChild(tableWrapper);

        const table = document.createElement("table");
        table.setAttribute("class","table-data__table");

        const thead = document.createElement("thead");
        thead.setAttribute("class","table-data__headers");
        let aux = "<th class='table-data__column table-data__header'>N°</th>"; 
        this.columns.forEach(column=>{
            aux += `<th class="table-data__column table-data__header">${column.name}</th>`;
        });
        if(this.actions && this.actions.options){
        	if(this.actions.options.length > 0){
        		aux += `<th class="table-data__column table-data__header">Acciones</th>`;	
        	}        	
        }
        thead.innerHTML = `
            <tr class="table-data__row table-data__row--border">${aux}</tr>   
        `;

        const tbody = document.createElement("tbody");
        tbody.setAttribute("class","table-data__body");

        table.appendChild(thead);
        table.appendChild(tbody);

        this.table.remove();

        this.table = table;

        tableWrapper.appendChild(this.table);

        tableParent.appendChild(tableContainer);

        this.DEFAULT.navigator = new Navigator(this.DEFAULT.rowsCountView,this);

    }

    addListeners() {

        this.table.parentElement.addEventListener('click', e => {

            if (e.target.classList.contains('table-data__edit')) {
                this.edit(this.DEFAULT.data[e.target.parentElement
                                  .parentElement
                                  .parentElement
                                  .parentElement
                                  .firstElementChild.textContent-1]);
            } else if (e.target.classList.contains('table-data__remove')) {
            	this.remove(this.DEFAULT.data[e.target.parentElement
                    .parentElement
                    .parentElement
                    .parentElement
                    .firstElementChild.textContent-1]);
            } else if (e.target.classList.contains('table-data__viewDetails')) {
                this.view(this.DEFAULT.data[e.target.parentElement
                    .parentElement
                    .parentElement
                    .parentElement
                    .firstElementChild.textContent-1]);
            } else if(e.target.classList.contains("table-data__upload")){
            	this.upload(this.DEFAULT.data[e.target.parentElement
                    .parentElement
                    .parentElement
                    .parentElement
                    .firstElementChild.textContent-1]);
            }

        });

    }

    updateData(ArrayObjetos) {

        this.DEFAULT.data = ArrayObjetos;
        
        this.DEFAULT.navigator.restart(this.DEFAULT.data.length);

        this.updaloadTable(0,Math.min(this.DEFAULT.navigator.rowsCountView-1,this.DEFAULT.data.length-1));

    }

    clear() {
        this.table.querySelector(".table-data__body").innerHTML = '';
        this.DEFAULT.data = [];
        this.DEFAULT.navigator.restart(0);
    }

    updaloadTable(ini,fin) {

        let tbody = this.table.querySelector(".table-data__body");

        tbody.innerHTML = '';

        if (tbody) {

            let fragment = document.createDocumentFragment();

            while( ini <= fin){

                fragment.appendChild(this.createRow(ini + 1, this.DEFAULT.data[ini]));

                ini++;

            }

            tbody.innerHTML = '';
            tbody.appendChild(fragment);

        }

    }

    createRow(numeration, obj) {

        let row = document.createElement("tr");

        row.setAttribute("class", "table-data__row");
        
        if(this.DEFAULT.options && 
           this.DEFAULT.options.row){
	    	if(this.DEFAULT.options.row.disabled &&
	    	   obj[this.DEFAULT.options.row.var] < 0){
	    		   row.classList.add("table-data__row--disabled");
	    	}
	    	
	    	if(this.DEFAULT.options.row.bgcolor && numeration % 2 == 0){
	    		row.style.backgroundColor = this.DEFAULT.options.row.bgcolor;
    	    }
        }
        
        row.innerHTML += `
			<td class="table-data__column">${numeration}</td>
		`;
        
        this.columns.forEach((metaColumn) => {

            if (metaColumn.type) {

                switch (metaColumn.type) {

                    case 'text':

                        row.innerHTML += `
							<td class="table-data__column">${obj[metaColumn.var]}</td>
						`;

                        break;

                    case 'link':

                        row.innerHTML += `
							<td class="table-data__column">
								<a class="table-data__link" href="${obj[metaColumn.var]}" target="_blank">${obj[metaColumn.var]}</a>
							</td>
						`;

                        break;

                    case 'img':
                        row.innerHTML += `
							<td class="table-data__column">
								<a class="table-data__link" href="${obj[metaColumn.var]}" target="_blank">
									<img class="table-data__extension" src="${obj['img']}" title="imagen de extensión">
								</a>
							</td>
						`;
                        break;
                    case 'icon':
                    	if(metaColumn.settings){
                    		row.innerHTML += `
    							<td class="table-data__column table-data__column--pd-tb-0">
    								<a class="table-data__link" href="${obj[metaColumn.var]}" target="_blank">
    								    <i style="color:${obj[metaColumn.settings.colorvar]}" class="table-data__extension ${obj[metaColumn.settings.icon]}"></i>
    								</a>
    							</td>
    						`;
                    	}else{
                    		row.innerHTML += `
    							<td class="table-data__column table-data__column--pd-tb-0">
    								<a class="table-data__link" href="${obj[metaColumn.var]}" target="_blank">
    								    <i class="table-data__extension ${obj['img']}"></i>
    								</a>
    							</td>
    						`;
                    	}
                    	break;

                }

            }

        });

        if(this.actions && this.actions.options && this.actions.options.length > 0){
        	
        	let actionText = "";
        	
        	this.actions.options.split(" ").forEach(action => {
                if (action === 'view') {
                    actionText += `<i class="table-data__action-icon table-data__viewDetails fas fa-eye"></i>`;
                } else if (action === 'edit') {
                    actionText += `<i class="table-data__action-icon table-data__edit fas fa-edit"></i>`;
                } else if (action === 'remove') {
                    actionText += `<i class="table-data__action-icon table-data__remove fas fa-trash-alt"></i>`;
                } else if (action === 'upload') {
                	actionText += `<i class="table-data__action-icon table-data__upload fas fa-upload"></i>`;
                }
            });

            row.innerHTML += `
    			<td class="table-data__column table-data__column--action">
                    <div class="table-data__toogle-container">
                        <div class="table-data__actions">
                        	${actionText}
                        </div>
    				</div>
    			</td>
    		`;
        	
        }
        
        return row;

    }

    edit(obj) {
        this.actions.edit(obj,this);
    }

    remove(obj) {
        this.actions.remove(obj,this);
    }

    view(obj) {
        this.actions.view(obj,this);
    }
    
    upload(obj){
    	this.actions.upload(obj,this);
    }
        
}

class Navigator {

    constructor(rowsCountView,objDataTable) {
        this.currentPage = 1;
        this.countPage = 1;
        this.rowsCountView = rowsCountView ? rowsCountView : 0;
        this.countData = 0;
        this.createNavigator(objDataTable.table.parentElement);
        this.addListener(objDataTable);
        this.disabledButtons();
    }

    createNavigator(container) {
        if (!container) return;
        let navigator = document.createElement("div");
        navigator.setAttribute("class", "table-data__navigator");
        navigator.innerHTML = `
            <div class="nav-table flex flex--center">
                <ul class="nav-table__list flex">
                    <li class="nav-table__item">
                        <i class="nav-table__icon nav-table__icon--first fas fa-fast-backward"></i>
                    </li>
                    <li class="nav-table__item">
                        <i class="nav-table__icon nav-table__icon--previous fas fa-step-backward"></i>
                    </li>
                    <li class="nav-table__item">
                        <i class="nav-table__icon nav-table__icon--next fas fa-step-forward"></i>
                    </li>
                    <li class="nav-table__item">
                        <i class="nav-table__icon nav-table__icon--last fas fa-fast-forward"></i>
                    </li>
                </ul>
                <div class="nav-table__info">
                    <span class="nav-table__limit info-nav__pages"></span>
                    <span class="nav-table__separator info-nav__separator">de</span>
                    <span class="nav-table__count info-nav__count"></span>
                </div>
            </div>
        `;
        this.navigator = navigator;
        container.appendChild(navigator);
        this.updateInfo({initial:0,final:0});
    }

    addListener(objDataTable) {

        this.navigator.addEventListener('click', e => {

            if(e.target.tagName === 'I'){
            
                if (e.target.classList.contains("nav-table__icon--first")) {
                    this.currentPage = 1;
                } else if (e.target.classList.contains("nav-table__icon--previous")) {
                    this.currentPage--;
                } else if (e.target.classList.contains("nav-table__icon--next")) {
                    this.currentPage++;
                } else if (e.target.classList.contains("nav-table__icon--last")) {
                    this.currentPage = this.countPage;
                }

                const {initial,final} = this.limits();        
                objDataTable.updaloadTable(initial,final);
                this.updateInfo(this.limits());

                this.disabledButtons();

            }

        });
    }

    updateInfo({initial,final}) {
        const infoPages = this.navigator.querySelector(".nav-table__limit");
        const infoCount = this.navigator.querySelector(".nav-table__count");
        infoCount.textContent = this.countData;
        /*infoPages.textContent = `${initial + 1} - ${final + 1}`;*/
        infoPages.textContent = this.countData == 0 ? '0 - 0': `${initial + 1} - ${final + 1}`;
    }

    restart(countData = 0){
        this.countData =  countData;
        this.currentPage = 1;
        this.countPage = Math.trunc(countData / this.rowsCountView) +
                         (countData % this.rowsCountView > 0 ? 1 : 0);
        this.updateInfo({initial:0,final:Math.min(countData-1,this.rowsCountView-1)});
        this.disabledButtons();
    }

    limits(){ 
        const initialIndexData = (this.currentPage - 1) * this.rowsCountView,
              finalIndex = initialIndexData + this.rowsCountView - 1; 
        return {
            initial: initialIndexData,
            final: finalIndex > this.countData - 1 ? this.countData - 1 : finalIndex
        }
    }

    disabledButtons(){
        let btns = Array.from(this.navigator.querySelectorAll(".nav-table__item .nav-table__icon"));
        if(this.countData == 0 || this.countPage <= 1){
            btns.forEach(btn=>{
                btn.classList.add("nav-table__icon--disabled");
            });
        }else{
            btns.forEach(btn=>{
                btn.classList.remove("nav-table__icon--disabled");btn.classList.remove("nav-table__icon--disabled");
            });
            if(this.currentPage == 1){
                btns[0].classList.add("nav-table__icon--disabled");
                btns[1].classList.add("nav-table__icon--disabled");
            }else if(this.currentPage === this.countPage){
                btns[2].classList.add("nav-table__icon--disabled");
                btns[3].classList.add("nav-table__icon--disabled");
            }
        }
    }

}