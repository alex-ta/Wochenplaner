TableWrapper = function(e){
	
	var element = e;
	var rest
	var headers;
	var data;
	var name;
	
	this.init = function(baseUrl){
		this.rest=new RestClient(baseUrl);
		this.rest.getAll(this);
	}
	
	this.createTable = function (data){
		
		$.fn.dataTable.ext.search.push(
			    function( settings, data, dataIndex ) {
			        console.log(data);
			 
			        if (1 > 1) {
			            return true;
			        }
			        return false;
			    });
		
		this.name = "datatab";
		this.data = data;
		this.headers = Object.keys(data[0]);
		var table = "<table id='hiddentable'>";
		table += this.createHeader();
		table += this.createData();
		table += this.createFooter();
		table += "</table>";
		console.log(table);
		element.innerHTML = table;
		$("#hiddentable").DataTable();
		
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
	
	this.createTh = function(){
		var header = "";
		for (var i = 0; i<this.headers.length; i++){
			header += "<th>"+this.toCapital(this.headers[i])+"</th>";
		}
		header += "<th>Action Update</th>";
		header += "<th>Action Delete</th>";
		return header;
	};
	
	this.createData = function(){
		var tbody = "<tbody>";
		for (var r = 0; r < this.data.length; r++){
			tbody += "<tr>"
			for(var i = 0;  i < this.headers.length; i++){
				tbody += "<td>"+this.data[r][this.headers[i]]+"</td>";
			}
			tbody += "<th><Button>Update</Button></th>";
			tbody += "<th><Button onclick='this.deleteItem'>Delete</Button></th>";
			tbody +="</tr>";
		}
		tbody +="</tbody>";
		return tbody;
	}
	
	this.toCapital = function(word){
		return word.replace(/\w/, c => c.toUpperCase());
	}
	
	this.getAll = function(data){
		this.createTable(data);
	}
	
	this.deleteItem = function(data){
		console.log(data)
	}
};
