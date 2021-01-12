<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">상세보기 페이지</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
							상세보기 페이지
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
				
					<div class="form-group">
						<label>Bno</label>
						<input class="form-control" name="bno" readonly="readonly" value='<c:out value="${board.bno}"/>'>
					</div>
				
					<div class="form-group">
						<label>Title</label>
						<input class="form-control" name="title" readonly="readonly" value='<c:out value="${board.title}"/>'>
					</div>
					
					<div class="form-group">
						<label>Content</label>
					<textarea class="form-control col-sm-5" rows="5" name="content" readonly="readonly"><c:out value="${board.content}"/></textarea>
					</div>

				
					<div class="form-group">
						<label>Writer</label>
						 <input class="form-control" name="writer" readonly="readonly" value='<c:out value="${board.writer}"/>'>
					</div>
						<button data-oper='modify' class="btn btn-default" >Modify</button>
						<button data-oper='list' class="btn btn-info" >List</button>
                        </div>
                        
                        <form id='operForm' action="/board/modify" method="get">
                        <input type='hidden' id='bno' name='bno' value='<c:out value="${board.bno }"/>'>
                         <input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum }"/>'>
                          <input type='hidden' name='amount' value='<c:out value="${cri.amount }"/>'>
                          <input type='hidden' name='keyword' value='<c:out value="${cri.keyword }"/>'>
                          <input type='hidden' name='type' value='<c:out value="${cri.type }"/>'>
                          
                        </form>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
     <%@include file="../includes/footer.jsp" %>

<!-- 모듈패턴 연결 -->
<script type="text/javascript" src="/resources/js/reply.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	
	console.log(replyService);

});
</script>

<script>
	console.log("=======")
	console.log("등록js test")

	var bnoValue = '<c:out value="${board.bno}"/>';
	
	//댓글등록
	replyService.add({reply:"js test3",replyer:"tester3",bno:bnoValue},
	function(result){
		alert("result:" + result);
	}
);

	//댓글목록
	replyService.getList({bno:bnoValue, page:1}, function(list){

		for(var i = 0, len = list.length||0; i< len; i++) {
		console.log(list[i]);
	}	
});	

	//댓글 삭제
	replyService.remove(24, function(count){

		console.log(count);

		if(count === "success"){
			alert("삭제완료~~!");
		}
	}, function(err){
			alert("오류....");
});

	//댓글 수정
	replyService.update({rno : 100, bno : bnoValue, reply : "수정~수~정수~정"},
		function(result){
		alert("수정 완료~");
		});

	//댓글 조회
	replyService.get(20, function(data){
		console.log(data + "됫나?");
	});
</script>

<script type="text/javascript">
$(document).ready(function(){

	var operForm = $("#operForm");

	$("button[data-oper='modify']").on("click",function(e){
	operForm.attr("action","/board/modify").submit();
	});

	$("button[data-oper='list']").on("click",function(e){
	operForm.find("#bno").remove();
	operForm.attr("action","/board/list")
	operForm.submit();
		});

	
});
</script>

