<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
.uploadResult {
	width: 100%;
	background-color: gray;
}

.uploadResult ul{
	display: flex;
	flex-flow: row;
	justify-content: center;
	align-items: center;
}

.uploadResult ul li{
	list-style: none;
	padding: 10px;
}

.uploadResult ul li img{
	width: 200px;
}
</style>
<body>


<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>

<div class="uploadDiv">
	<input type="file" name="uploadFile" multiple>
	
	<button id="uploadBtn">upload</button> 
</div>

<div class="uploadResult">
	<ul>
	
	</ul>
</div>


<script>
$(document).ready(function(){
	
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize = 5242880; //5MB
	
	//파일 이름,사이즈 검증
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
	
	//<ul> 태그내에 파일 이름 출력
	var uploadResult = $(".uploadResult ul");
	
	function showUploadedFile(uploadResultArr){
		var str = "";		
		
		$(uploadResultArr).each(function(i, obj) {
			
			if(!obj.image){
				str += "<li><img src='/resources/img/attach.png'>" + obj.fileName + "</li>";
			}else{
				
				var fileCallPath = encodeURIComponent( obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
				
				str += "<li><img src='/display?fileName="+fileCallPath+"'></li>";
			}

			
		});
		
		uploadResult.append(str);
	}
	

	var cloneObj = $(".uploadDiv").clone();
	
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

			showUploadedFile(result);
			
			$(".uploadDiv").html(cloneObj.html());
			
		} 
	}); //$ajax

	
	});
});
</script>  
</body>
</html>