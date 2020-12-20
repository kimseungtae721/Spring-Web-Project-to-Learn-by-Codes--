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
                                        <td><a href='/board/get?bno=<c:out value="${board.bno }"/>'> <c:out value="${board.title }" /></a></td>
										<td><c:out value="${board.writer }" /></td>
										<td><fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd"/></td>
										<td><fmt:formatDate value="${board.updateDate}" pattern="yyyy-MM-dd"/></td>
                                    </tr>
                             </c:forEach>
                            </table>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
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
		})
});
</script>
     <%@include file="../includes/footer.jsp" %>