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


</script>