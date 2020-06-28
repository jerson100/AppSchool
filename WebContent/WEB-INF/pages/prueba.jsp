<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form id="prueba">
		<input type="text" name="p">
		<label>name:</label>
		<input type="text" name="values[]">
		<label>name:</label>
		<input type="text" name="values[]">
		<label>name:</label>
		<label>name:</label>
		<input type="text" name="values[]">
		<input type="submit" value="ok">
	</form>
	<script>
	let data = {
			name: 'jerson',
			nums: [1,2,3,4,5],
			id: 10
	};
	prueba.addEventListener('submit',e=>{
		e.preventDefault();
		fetch('prueba',{
			method: 'POST', // or 'PUT'
			  body: JSON.stringify(data), // data can be `string` or {object}!
			  headers:{
			    'Content-Type': 'application/json'
			  }
		}).then(response=>{
			console.log(response);
		}).catch(e=>{
			console.log(e);
		});
		
	});
		
	</script>
</body>
</html>