  
RestClient = function(url){

	var baseUrl = window.location.origin + url;
		
	this.getAll = function(call) { 
		$.ajax({
			 url: baseUrl,
			 headers: {
				 "Authorization": "Basic " + btoa("User" + ":" + "aaaa"),
  		         "Accept": "application/json",
  		         "Content-Type": "application/json; charset=utf-8"
  		         }
	 }).then(function(data) {
		 call.getAll(data);
	 });
	}

	this.get = function(id, call){
		$.ajax({
			 url: baseUrl+"/"+id,
			 headers: {
				 "Authorization": "Basic " + btoa("User" + ":" + "aaaa"),
 		         "Accept": "application/json",
 		         "Content-Type": "application/json; charset=utf-8"
			 	 }
	 }).then(function(data) {
		 call.get(data);
	 });
	}
	
	this.del = function(id, call){
		$.ajax({
			 url: baseUrl+"/"+id,
			 type: 'DELETE',
			 headers: {
				 "Authorization": "Basic " + btoa("User" + ":" + "aaaa"),
 		         "Accept": "application/json",
 		         "Content-Type": "application/json; charset=utf-8"
			     }
	 }).then(function(data) {
		 call.del(data);
	 });
	}

	this.post = function(obj, call){
		$.ajax({
			 url: baseUrl,
			 type: 'POST',
			 data: JSON.stringify(obj),
			 headers: {
				 "Authorization": "Basic " + btoa("User" + ":" + "aaaa"),
 		         "Accept": "application/json",
 		         "Content-Type": "application/json; charset=utf-8"
			     }
	 }).then(function(data) {
		 call.post(data);
	 });
	}
	
	this.put = function(obj, call){
		$.ajax({
			 url: baseUrl+"/"+id,
			 type: 'PUT',
			 data: JSON.stringify(obj),
			 headers: {
				 "Authorization": "Basic " + btoa("User" + ":" + "aaaa"),
 		         "Accept": "application/json",
 		         "Content-Type": "application/json; charset=utf-8"
			     }
	 }).then(function(data) {
		 call.put(data);
	 });
	}

	this.PrintData = function(){
		this.getAll = function(data){
			console.log(data);}
		this.get = function(data){
			console.log(data);}
		this.post = function(data){
			console.log(data);}
		this.put = function(data){
			console.log(data);}
		this.del = function(data){
			console.log(data);}
	}
	
}
