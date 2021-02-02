<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<div class="bigPictureWrapper">
	<div class="bigPicture">
	</div>
</div>
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
	width: 100px;
}

.uploadResult ul li span {
	color: white;
}

.bigPictureWrapper {
	position: absolute;
	display: none;
	justify-content: center;
	align-items: center;
	top: 0%;
	width: 100%;
	height: 100%;
	background: rgba(255,255,255,0.5);
}
.bigPicture{
	position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
	}
.bigPicture img {
	width: 600px;	
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

//원본 이미지 띄워주기
function showImage(fileCallPath) {
	//alert(fileCallPath);

	$(".bigPictureWrapper").css("display","flex").show();
	
	$(".bigPicture")
	.html("<img src='/display?fileName="+encodeURI(fileCallPath)+"'>") 
	.animate({width: '100%', height: '100%'}, 1000);
}

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
	
	//<ul> 태그내에 파일 이름 출력(파일다운로드)
	var uploadResult = $(".uploadResult ul");
	
	function showUploadedFile(uploadResultArr){
		var str = "";		
		
		$(uploadResultArr).each(function(i, obj) {

		if(!obj.image){
			//	str += "<li><img src='/resources/img/attach.png'>" + obj.fileName + "</li>";
			var fileCallPath = encodeURIComponent(obj.uploadPath+ "/" + obj.uuid + "_" + obj.fileName);
			str += "<li><a href='/download?fileName="+fileCallPath+"'>" 
					+ "<img src='/resources/img/attach.png'>"
					+ obj.fileName + "</a></li>" 
					+"<span data-file=\'"+fileCallPath+"\' data-type='file'> x </span>" + "<div></li>"
			console.log(str);
		}
		else{
			var fileCallPath = encodeURIComponent( obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
			
			var originPath = obj.uploadPath+ "\\" + obj.uuid + "_" + obj.fileName;
			
			originPath = originPath.replace(new RegExp(/\\/g),"/");
			
			str += "<li><a href=\"javascript:showImage(\'"+originPath+"\')\"><img src='/display?fileName="+fileCallPath+"'></a>" 
					+"<span data-file=\'"+fileCallPath+"\' data-type='image'> x </span>" + "<li>";
		}
	});
		uploadResult.append(str);
}
	
	$(".uploadResult").on("click","span", function(e) {
		
		var targetFile = $(this).data("file");
		var type = $(this).data("type");
		console.log(targetFile);
		
		$.ajax({
			url : "/deleteFile",
			data : {fileName: targetFile, type: type},
			dataType: "text",
			type : "post",
				success: function(result) {
					alert(result);
				}
		}); //ajax end
	});
	
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
	
	// 원본이미지 다시 사라지도록 하는 이벤트
	$(".bigPictureWrapper").on("click", function(e) {
		
		$(".bigPicture").animate({width:'0%', height: '0%'}, 1000);
		setTimeout(function() {
			$('.bigPictureWrapper').hide();
		}, 1000);
	});
	
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