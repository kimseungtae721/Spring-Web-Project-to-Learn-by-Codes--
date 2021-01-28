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
	
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize = 5242880; //5MB

	function checkExtension(fileName, fileSize){

		if(fileSize > maxSize){
			alert("파일사이즈 초과");
			return false;
		}

		if(regex.test(fileName)){
			alert("해당 종류의 파일은 업로드 불가")
			return false;
		}

		return true;
	}

	//uploadBtn 클릭시
	$("#uploadBtn").on("click", function(e){

	var formData = new FormData();
	
	var inputFile = $("input[name='uploadFile']");

	var files = inputFile[0].files;

	console.log(files);

	for(var i = 0; i < files.length; i++){

		//파일 이름 , 사이즈 검증
		if(!checkExtension(files[i].name, files[i].size) ){
				return false;
		}
		
		formData.append("uploadFile", files[i]);
		
	}

	$.ajax({  
		url: "/uploadAjaxAction",
		processData: false,
		contentType: false,
		data : formData,
		type : "post",
		dataType:"json",
		success: function(result){

			console.log(result);
		
		} 
	}); //$ajax

	
	});
});
</script>  
</body>
</html>