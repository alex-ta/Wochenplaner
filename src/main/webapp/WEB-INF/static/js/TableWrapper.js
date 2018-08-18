var users = ["A", "B", "C"];

var data = [
{"date":"1/3/2018","Verantwortlich morgens":"A","Vertretung morgens":"B","Verantwortlich mittags":"B","Vertretung mittags":"A","Verantwortlich abends":"A","vertretung abends":""},
{"date":"2/3/2018","Verantwortlich morgens":"B","Vertretung morgens":"A","Verantwortlich mittags":"C","Vertretung mittags":"B","Verantwortlich abends":"A","vertretung abends":""},
{"date":"3/3/2018","Verantwortlich morgens":"A","Vertretung morgens":"B","Verantwortlich mittags":"A","Vertretung mittags":"B","Verantwortlich abends":"A","vertretung abends":""},
{"date":"4/3/2018","Verantwortlich morgens":"A","Vertretung morgens":"B","Verantwortlich mittags":"C","Vertretung mittags":"B","Verantwortlich abends":"A","vertretung abends":""},
{"date":"5/3/2018","Verantwortlich morgens":"C","Vertretung morgens":"A","Verantwortlich mittags":"A","Vertretung mittags":"B","Verantwortlich abends":"A","vertretung abends":"A"},
{"date":"6/3/2018","Verantwortlich morgens":"A","Vertretung morgens":"B","Verantwortlich mittags":"C","Vertretung mittags":"B","Verantwortlich abends":"A","vertretung abends":""},
{"date":"7/3/2018","Verantwortlich morgens":"A","Vertretung morgens":"A","Verantwortlich mittags":"C","Vertretung mittags":"B","Verantwortlich abends":"A","vertretung abends":""},
{"date":"8/3/2018","Verantwortlich morgens":"B","Vertretung morgens":"A","Verantwortlich mittags":"C","Vertretung mittags":"B","Verantwortlich abends":"A","vertretung abends":"C"},
{"date":"9/3/2018","Verantwortlich morgens":"B","Vertretung morgens":"A","Verantwortlich mittags":"C","Vertretung mittags":"B","Verantwortlich abends":"A","vertretung abends":""},
{"date":"10/3/2018","Verantwortlich morgens":"B","Vertretung morgens":"B","Verantwortlich mittags":"A","Vertretung mittags":"B","Verantwortlich abends":"A","vertretung abends":"C"},
{"date":"11/3/2018","Verantwortlich morgens":"B","Vertretung morgens":"B","Verantwortlich mittags":"A","Vertretung mittags":"B","Verantwortlich abends":"A","vertretung abends":""},
{"date":"12/3/2018","Verantwortlich morgens":"A","Vertretung morgens":"C","Verantwortlich mittags":"C","Vertretung mittags":"B","Verantwortlich abends":"A","vertretung abends":""},
{"date":"13/3/2018","Verantwortlich morgens":"A","Vertretung morgens":"C","Verantwortlich mittags":"C","Vertretung mittags":"B","Verantwortlich abends":"A","vertretung abends":""},
{"date":"14/3/2018","Verantwortlich morgens":"A","Vertretung morgens":"C","Verantwortlich mittags":"C","Vertretung mittags":"B","Verantwortlich abends":"A","vertretung abends":""}
]


if (!Array.prototype.remove) {
  Array.prototype.remove = function(element) {
	var array = [];
	for (var i =0; i<this.length; i++){
		array.push(this[i]);
	}
	var index = array.indexOf(element);
	if (index > -1) {
	  array.splice(index, 1);
	}
	return array;
  }
}


TableWrapper = function(){
	
	var elementTable = document.getElementById("content");
	var elementStats = document.getElementById("container");
	var rest
	var headers;
	var data;
	var name;
	var table;
	
	this.init = function(baseUrl){
		this.rest=new RestClient(baseUrl);
		this.rest.getAll(this);
	};
	
	this.createTable = function (data){
		$.fn.dataTableExt.ofnSearch['html-input'] = function(value) {
			//console.log($(value).val())
			return $(value).val();
		};
	
		this.name = "datatab";
		this.data = data;
		this.headers = Object.keys(data[0]);
		var table = "<table id='hiddentable' class='table table-striped table-bordered'>";
		table += this.createHeader();
		table += this.createData();
		table += this.createFooter();
		table += "</table>";
		console.log(table);
		elementTable.innerHTML = table;
		var table = $("#hiddentable").DataTable( {
		"order": [[ 0, 'asc' ]],
		"columnDefs": [ 
			{"targets"  : 'no-sort',"orderable": false,},
			{"targets": [1, 2, 3, 4, 5, 6], "type": "html-input"}
			]
		});
		document.getElementById("hiddentable_filter").children[0].children[0].setAttribute("onfocusout","table.reset()")
		
//		// Apply the search
//		table.columns().every( function () {
//			var that = this;	
//			$( 'input', this.header()).on( 'keyup change', function () {
//				if ( that.search() !== this.value ) {
//					that.search( this.value ).draw();
//				}
//			} );
//		} );
//		
//		table.columns().every( function () {
//			var that = this;	
//			$( 'input', this.footer()).on( 'keyup change', function () {
//				if ( that.search() !== this.value ) {
//					that.search( this.value ).draw();
//				}
//			} );
//		} );
		this.table = table;
		this.reset();
		return table;
	};
	
	this.reset = function (){
		this.table.page(18).draw('page');
	}
	
	
	
	this.createHeader = function (){
		var header = "<thead><tr>";
		header += this.createTh();
		header +="</tr></thead>";
		return header;
	};
	
	this.createFooter = function (){
		var header = "<tfoot><tr>";
		header += this.createTh();
		header +="</tr></tfoot>";
		return header;
	};
	
	this.createThInput = function(){
		var header = "";
		for (var i = 0; i<this.headers.length; i++){
			header += "<th><input class='search' type='text' placeholder='"+this.toCapital(this.headers[i])+"'/></th>";
		}
		return header;
	};
	
	this.createTh = function(){
		var header = "";
		for (var i = 0; i<this.headers.length; i++){
			header += "<th>"+this.toCapital(this.headers[i].replace("_"," "))+"</th>";
		}
		return header;
	};
	
	this.createData = function(){
		var tbody = "<tbody>";
		for (var r = 0; r < this.data.length; r++){
			tbody += "<tr>"
			for(var i = 0;  i < this.headers.length; i++){
				if(this.headers[i] == "date"){
					tbody += this.createTd(r,i);
				}else{
					tbody += this.createSelect(r,i);
				}
			}
			tbody +="</tr>";
		}
		tbody +="</tbody>";
		return tbody;
	};

	this.createSelect = function(r,i){
		var tbody = "<td> <select name='"+this.headers[i]+"' style='width: 100%;' onchange='changeContent(this, "+r+")'>";
		var addSpace = true;
		if(this.data[r][this.headers[i]]){
			tbody += "<option value='"+this.data[r][this.headers[i]]+"'>"+this.data[r][this.headers[i]]+"</option>";
		} else {
			tbody += "<option value=' '>     </option>";
			addSpace = false;
		}
		var userData = users.remove(this.data[r][this.headers[i]]);
		for(var u = 0; u<userData.length; u++){
			tbody += "<option value='"+userData[u]+"'>"+userData[u]+"</option>";
		}
		if(addSpace){
			tbody += "<option value=' '>     </option>";
		}
		tbody +="</select></td>";
		return tbody;
	};
	
	this.createTd = function(r,i){
		return "<td>"+this.data[r][this.headers[i]]+"</td>";
	};
	
	this.toCapital = function(word){
		return word.replace(/\w/, c => c.toUpperCase());
	};
	
	this.getAll = function(data){
		this.createTable(data);
	};
	
	this.deleteItem = function(data){
		console.log(data)
	};
	
	this.createStats = function() {
		var rows = [[1,3,5],[2,4,6]];
		var headers = ["Verantwortlich in Tagen","Vertretungen in Tagen", "Fehltage"];
		var udata = {};
		var holidyRow = 0;
		console.log(users);
		for(var u = 0; u < users.length; u++){
				udata[users[u]] = [];
				for (var i = 0; i<=rows.length; i++){
					udata[users[u]][i] = 0;
				}
			}
		console.log(udata);
		var data = {};
		for(var i = 0; i<rows.length; i++){
			data[i] = [];
			for(var y = 0; y<rows[i].length; y++){
				console.log((rows[i][y]));
				data[i] = data[i].concat(this.getData(rows[i][y]));
			}
		}	
		for(var i = 0; i<rows.length; i++){
			for(var d = 0; d < data[i].length; d++){
				if(data[i][d].replace(/\s/g, '') !== ""){
					udata[data[i][d]][i] += 1;
					if(i == holidyRow && data[i+1][d].replace(/\s/g, '') !== ""){
						udata[data[i][d]][rows.length] += 1;
					}
				}
			}
		}
		console.log(udata);
		
		var data = "<table class='table table-bordered'><thead>";
		data += "<th>Name</th>"
		for(var i =0; i<headers.length; i++){
			data += "<th>"+headers[i]+"</th>";
		}
		data += "<th>Gesamt in Tagen</th></thead>";
		data += "<tbody>";
		for(var i = 0; i< users.length; i++){
			data += "<tr>";
			data += "<th>"+users[i]+"</th>";
			var gesamt = 0;
			for(var y = 0; y < udata[users[i]].length; y++){
				data += "<th>"+udata[users[i]][y]+"</th>";
				if(y < udata[users[i]].length-1) {
					gesamt += Number.parseInt(udata[users[i]][y]);
				} else {
					gesamt -= Number.parseInt(udata[users[i]][y]);
				}
			}
			data += "<th>"+gesamt+"</th>";
			data += "</tr>";
		}
		data +="</tbody></table>"
		elementStats.innerHTML = data;
	};
	
	this.getData = function(index){
		var dataXY = this.table.column(index).nodes();
		var content = [];
		for(var i =0; i<dataXY.length; i++){
			content.push(dataXY[i].children[0].value);
		}
		return content;
	}
};

function init(){
	table = new TableWrapper();
	table.createTable(data);
	table.createStats();
}

function changeContent(obj, id){
	var value = obj.value;
	var name = obj.name;
	var entry = data[id];
	entry[name] = value;
	console.log(name);
	console.log(value);
	console.log(id);
	console.log(entry);
	table.createStats()
}
