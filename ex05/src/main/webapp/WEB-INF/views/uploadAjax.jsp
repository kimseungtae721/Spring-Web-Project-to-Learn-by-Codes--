<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>

<div class="uploadDiv">
	<input type="file" name="uploadFile" multiple>
	
	<button id="uploadBtn">upload</button>
</div>



<script>
$(document).ready(function(){
	$("#uploadBtn").on("click", function(e){

	var formData = new FormData();
	
	var inputFile = $("input[name='uploadFile']");

	var files = inputFile[0].files;

	console.log(files);

	for(var i=0; i< files.length; i++){

		formData.append("uploadFile", files[i]);
		
	}

	$.ajax({
		url: "/uploadAjaxAction",
		processData: false,
		contentType: false,
		data : formData,
		type : "post",
		success: function(result){
			alert("uploaded");
		} 
	}); //$ajax
});
});
</script>  
</body>
</html>