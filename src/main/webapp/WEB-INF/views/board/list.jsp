<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board List</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Board List Page
                            <button id='regBtn' type='button' class='btn btn-xs pull-right' onclick="location.href='register'">Register New Board</button>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>번 호</th>
                                        <th>제 목</th>
                                        <th>작성자</th>
                                        <th>작성일</th>
                                        <th>수정일</th>
                                    </tr>									
                                </thead>
								<!-- 게시판 리스트 반복문 -->
								<tbody>
									<c:forEach var="vo" items="${list}">
										<tr>
											<td>${vo.bno}</td>
											<td><a href="${vo.bno}" class="move">${vo.title}</a> <strong>[${vo.replycnt}]</strong></td>
											<td>${vo.writer}</td>
											<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${vo.regdate}"/></td>
											<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${vo.updatedate}"/></td>
										</tr>
									</c:forEach>
								</tbody>
                            </table>
							<div class="row"> <!-- start search -->
                            	<div class="col-md-12">
                            	  <div class="col-md-8"><!--search Form-->
                            	  	<form action="" id="searchForm">
										<select name="type" id="" class="form-control">
											<option value="">----------</option>
											<option value="T" <c:out value="${pageVO.cri.type=='T'?'selected':''}"/>>제목</option>
											<option value="C" <c:out value="${pageVO.cri.type=='C'?'selected':''}"/>>내용</option>
											<option value="W" <c:out value="${pageVO.cri.type=='W'?'selected':''}"/>>작성자</option>
											<option value="TC" <c:out value="${pageVO.cri.type=='TC'?'selected':''}"/>>제목 or 내용</option>
											<option value="TCW" <c:out value="${pageVO.cri.type=='TCW'?'selected':''}"/>>제목 or 내용 or 작성자</option>
										</select>        
										<input type="hidden" name="amount" value="${pageVO.cri.amount}"/>
										<input type="hidden" name="pageNum" value="${pageVO.cri.pageNum}"/>
										<input type="text" name="keyword" id="" value="${pageVO.cri.keyword}"/>
										<button type="button" class="btn btn-default" id="buttonType">검색</button>                    	  	
                            	  	</form>
                            	   </div>
                            	   <div class="col-md-2 col-md-offset-2">
                            	   	<!--페이지 목록 갯수 지정하는 폼-->
                            	   	<select name="" id="amount" class="form-control">
                            	   		<option value="10" <c:out value="${pageVO.cri.amount==10?'selected':''}"/> >10</option>
                            	   		<option value="20" <c:out value="${pageVO.cri.amount==20?'selected':''}"/> >20</option>
                            	   		<option value="30" <c:out value="${pageVO.cri.amount==30?'selected':''}"/> >30</option>
                            	   		<option value="40" <c:out value="${pageVO.cri.amount==40?'selected':''}"/> >40</option>
                            	   	</select>
								  </div>
                             	 </div>                             	 
                      		 </div><!-- end search -->
                            <!-- start Pagination -->
                            <div class="text-center">
                            	<ul class="pagination">
                              		<c:if test="${pageVO.prev}">
                            			<li class="paginate_button previous"><a href="${pageVO.startPage-1}">Previous</a></li>
                            		</c:if>
                            		
                            		<c:forEach var="idx" begin="${pageVO.startPage}" end="${pageVO.endPage}">
                            			<li class="paginate_button ${pageVO.cri.pageNum==idx?'active':''}">
                            				<a href="${idx}">${idx}</a>
                            			</li>
                            		</c:forEach>
                            		
                            		
                            		<c:if test="${pageVO.next}">
                            			<li class="paginate_button next"><a href="${pageVO.endPage+1}">Next</a></li>
                            		</c:if>
                            	</ul>
                            </div>
                            <!-- end Pagination -->   
                            </div>
                            <!-- end panel-body -->
                        </div>
                        <!-- end panel -->
                    </div>                   
                </div>               
            <!-- /.row -->
<!-- 페이지 나누기 추가 -->
<form action="list" method="GET" id="actionForm">
	<input type="hidden" name="pageNum" value="${pageVO.cri.pageNum}" />
	<input type="hidden" name="amount" value="${pageVO.cri.amount}" />
	<input type="hidden" name="keyword" id="" value="${pageVO.cri.keyword}"/>
	<input type="hidden" name="type" id="" value="${pageVO.cri.type}"/>
</form>
            
<!-- 모달 추가 -->
<div class="modal" tabindex="-1" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">게시글 등록</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>처리가 완료되었습니다.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>
<!-- 스크립트 -->
<script>
	let result = '${result}';
</script>
<script src="/resources/js/list.js"></script>
<%@include file="../includes/footer.jsp" %>       