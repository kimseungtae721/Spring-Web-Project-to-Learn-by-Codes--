<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            DataTables Advanced Tables
                            <button id='regBtn' type="button" class="btn btn-xs pull-right" >
                            등록
                            </button>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" >
                                <thead>
                                    <tr>
                                        <th>Bno</th>
                                        <th>Title</th>
                                        <th>Writer</th>
                                        <th>RegDate</th>
                                        <th>UpdateDate</th>
                
                                    </tr>
                                </thead>
                                <tbody>
                                   <c:forEach items="${list}" var="board">
                                    <tr class="odd gradeX">
										<td><c:out value="${board.bno }" /></td>
                                        <td><a class='move' href='<c:out value="${board.bno}"/>'> <c:out value="${board.title }" /></a></td>
										<td><c:out value="${board.writer }" /></td>
										<td><fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd"/></td>
										<td><fmt:formatDate value="${board.updateDate}" pattern="yyyy-MM-dd"/></td>
                                    </tr>
                             </c:forEach>
                            </table>
                            <!-- /.table-responsive -->
                            <h2>${pageMaker}</h2>
					
				<div class='pull-right'>
					<ul class="pagination">

						<c:if test="${pageMaker.prev}">
							<li class="paginate_button previous"><a
								href="${pageMaker.startPage -1}">Previous</a></li>
						</c:if>

						<c:forEach var="num" begin="${pageMaker.startPage}"
							end="${pageMaker.endPage}">
							<li class="paginate_button  ${pageMaker.cri.pageNum == num ? "active":""} ">
								<a href="${num}">${num}</a>
							</li>
						</c:forEach>

						<c:if test="${pageMaker.next}">
							<li class="paginate_button next"><a
								href="${pageMaker.endPage +1 }">Next</a></li>
						</c:if>


					</ul>
				</div>
						
						
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
	  <form id='actionForm' action="/board/list" method='get'>
		  <input type='hidden' name='pageNum' value= '${pageMaker.cri.pageNum}'>
		  <input type='hidden' name='amount' value= '${pageMaker.cri.amount}'>  
	  </form>
  
  
  
    <!-- modal 추가  -->        
   <div id="myModal" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>Modal body text goes here.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>
     <!-- modal 끝 -->       
<script type="text/javascript">
$(document).ready(function(){

	var result = '<c:out value="${result}"/>';

	checkModal(result);     //controller에서 받아온 bno값을 변경

	history.replaceState({},null,null);  //window.history 객체를 사용해 뒤로가기시 데이터 초기화 
	//모달창 메서드
	function checkModal(result){

		if(result ==='' || history.state)	
			return;
		if(parseInt(result) > 0) 	{
			$(".modal-body").html("게시글" + parseInt(result)+ "번이 등록 되었습니다");   
		}else{
			$(".modal-body").html("처리 완료");   
			}

		$("#myModal").modal("show");      
		}
	
		$("#regBtn").on("click",function(){
			self.location = "/board/register";
});


	    var actionForm = $("#actionForm");
	    
	    $(".paginate_button a").on("click", function(e) {
	          
	        e.preventDefault(); //기본 동작 제한    

			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
			actionForm.submit();
					
		    });

	    $(".move").on("click", function(e){

	    	e.preventDefault(); //기본 동작 제한
			actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'>");
			actionForm.attr("action","/board/get");
			actionForm.submit();
			}
	    );
});
</script>
     <%@include file="../includes/footer.jsp" %>