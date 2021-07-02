<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@include file="../includes/header.jsp" %>
<link rel="stylesheet" href="/resources/dist/css/attach.css"/>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Read</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>            
            <div class="row">
                <div class="col-lg-12">
                	<div class="panel panel-default">
                        <div class="panel-heading">
                           Board Read Page
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                			<form action="" role="form">
                				<div class="form-group">
                					<label>Bno</label>
                					<input class="form-control" name="bno" readonly="readonly" value="${vo.bno}">                				
                				</div> 
                				<div class="form-group">
                					<label>Title</label>
                					<input class="form-control" name="title" readonly="readonly" value="${vo.title}">                				
                				</div>  
                				<div class="form-group">
                					<label>Content</label>
                					<textarea class="form-control" rows="3" name="content" readonly="readonly" >${vo.content}</textarea>               				
                				</div> 
                				<div class="form-group">
                					<label>Writer</label>
                					<input class="form-control" name="writer" readonly="readonly" value="${vo.writer}">                				
                				</div>
                				
                				<%-- 로그인한 사용자와 작성자가 동일할 때 modify 버튼 보여주기 --%>
                				<sec:authentication property="principal" var="info"/>
                				<sec:authorize access="isAuthenticated()">
                					<c:if test="${info.username == vo.writer}"><%-- 작성자, 로그인 아이디 동일한지 여부 확인 --%>
		                				<button type="button" class="btn btn-default">Modify</button>
                					</c:if>
                				</sec:authorize>
                				
                						
                				<button type="button" class="btn btn-info">List</button>          			
                			</form>
                		</div>
                	</div>
                </div>
            </div>
<div class="bigPictureWrapper">
	<div class="bigPicture"></div>
</div>
<%-- 첨부 파일 목록 보여주기 --%>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<i class="fa fas fa-file"></i> Files
			</div>
			<div class="panel-body">
				<div class="uploadResult">
					<ul></ul>
				</div>
			</div>
		</div>
	</div>
</div>
<%-- 댓글 리스트 보여주는 부분 --%>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<i class="fa fa-comments fa-fw"></i>
				Reply
				
				<sec:authorize access="isAuthenticated()">
					<button id="addReplyBtn" class="btn btn-primary btn-xs pull-right">New Reply</button>
				</sec:authorize>
				
				
			</div>
			<div class="panel-body">
				<ul class="chat">
					<li class="left clearfix" data-rno='1'>
						<div>
							<div class="header">
								<strong class="primary-font">user00</strong>
								<small class="pull-right text-muted">2021-06-15 14:25</small>
								<p>Good Job!</p>
							</div>
						</div>
					</li>
				</ul>
			</div>
			<div class="panel-footer"></div><!-- 댓글 페이지 나누기 -->
		</div>
	</div><!-- col-lg-12 close -->
</div><!-- row close -->


<%-- 댓글 작성 폼 --%>
<div class="modal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <h5 class="modal-title">Reply</h5>
      </div>
      <div class="modal-body">
   	   <div class="form-group">
   	   		<label for="">댓글 내용</label>
   	   		<input type="text" name="reply" class="form-control" value="댓글내용"/>
   	   </div>
   	   <div class="form-group">
   	   		<label for="">작성자</label>
   	   		<input type="text" name="replyer" class="form-control" value="작성자" readonly/>
   	   </div>
   	   <div class="form-group">
   	   		<label for="">작성일</label>
   	   		<input type="text" name="replyDate" class="form-control" value="작성일"/>
   	   </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-success" id="modalRegisterBtn">등록</button>
        <button type="button" class="btn btn-warning" id="modalModifyBtn">수정</button>
        <button type="button" class="btn btn-danger" id="modalRemoveBtn">삭제</button>
        <button type="button" class="btn btn-primary" id="modalCloseBtn" data-dismiss="modal">종료</button>
      </div>
    </div>
  </div>
</div>
<%-- 페이지 나누기를 위해 필요한 값 --%>
<form action="" id="operForm">
	<input type="hidden" name="type" value="${cri.type}" />
	<input type="hidden" name="keyword" value="${cri.keyword}" />
	<input type="hidden" name="pageNum" value="${cri.pageNum}" />
	<input type="hidden" name="amount" value="${cri.amount}" />
	<input type="hidden" name = "bno" value="${vo.bno}"/>
</form>
<script>
	let bno = ${vo.bno};
	
	var replyer = null;
	<sec:authorize access = "isAuthenticated()">
		replyer = '<sec:authentication property="principal.username"/>';
	</sec:authorize>
	
	var csrfHeaderName = "${_csrf.headerName}";
	var csrfTokenValue = "${_csrf.token}";
</script>
<script src="/resources/js/reply.js"></script>
<script src="/resources/js/read.js"></script>
<%@include file="../includes/footer.jsp" %>       