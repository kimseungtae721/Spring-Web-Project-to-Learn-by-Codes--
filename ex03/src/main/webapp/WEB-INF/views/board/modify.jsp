<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Read Page</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
							Board Read Page
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
				<form role="form" action="/board/modify" method="post">
				<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
				<input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
				<input type='hidden' name='type' value='<c:out value="${cri.type}"/>'>
				<input type='hidden' name='keyword' value='<c:out value="${cri.keyword}"/>'>
					<div class="form-group">
						<label>Bno</label>
						<input class="form-control" name="bno" readonly="readonly" value='<c:out value="${board.bno}"/>'>
					</div>
				
					<div class="form-group">
						<label>Title</label>
						<input class="form-control" name="title"  value='<c:out value="${board.title}"/>'>
					</div>
					
					<div class="form-group">
						<label>Content</label>
					<textarea class="form-control col-sm-5" rows="5" name="content" ><c:out value="${board.content}"/></textarea>
					</div>
				
					<div class="form-group">
						<label>Writer</label>
						 <input class="form-control" name="writer" readonly="readonly" value='<c:out value="${board.writer}"/>'>
					</div>

	 				<div class="form-group">
					<label>RegDate</label>
					<input type="hidden" class="form-control" name='regdate' value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${board.regdate }" />' readonly="readonly"/>
					</div>
					
					<div class="form-group">
					<label>Update Date</label>                     
					<input type="hidden" class="form-control" name='updateDate' value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${board.regdate }" />' readonly="readonly"/>
					</div>
	
					<button type="submit" data-oper='modify' class="btn btn-default">Modify</button> 
					<button type="submit" data-oper='remove' class="btn btn-danger">Remove</button>
					<button type="submit" data-oper='list' class="btn btn-info">List</button>
				</form>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
     <%@include file="../includes/footer.jsp" %>

<script type="text/javascript">
$(document).ready(function() {


	  var formObj = $("form");

	  $('button').on("click", function(e){
	    
	    e.preventDefault(); 
	    
	    var operation = $(this).data("oper");
	    
	    console.log(operation);
	    
	    if(operation === 'remove'){
	      formObj.attr("action", "/board/remove");
	    }else if(operation === 'list'){
	      //move to list 만일 사용자가 List 버튼을 클릭한다면 <form>태그에서 필요한 부분만 잠시 복사(clone)하고 <form>태그 내용은 다지운다 이후 필요한 태그를 추가해서 호출
	      formObj.attr("action", "/board/list").attr("method","get");
		  var pageNumTage = $("input[name='pageNum']").clone();
		  var amountTage = $("input[name='amount']").clone();

		  var keywordTag = $("input[name='keyword']").clone();
		  var typeTag = $("input[name='type']").clone();

		  formObj.empty();
		  formObj.append(pageNumTage);
		  formObj.append(amountTage);

		  formObj.append(keywordTag);
		  formObj.append(typeTag);
		    }
	    
	    formObj.submit();
	  });

});
</script>