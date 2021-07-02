<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../includes/header.jsp" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<link rel="stylesheet" href="/resources/dist/css/attach.css"/>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Modify</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>            
            <div class="row">
                <div class="col-lg-12">
                	<div class="panel panel-default">
                        <div class="panel-heading">
                           Board Modify Page
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                			<form action="" method="post" role="form">
                				<div class="form-group">
                					<label>Bno</label>
                					<input class="form-control" name="bno" readonly="readonly" value="${vo.bno}">                				
                				</div> 
                				<div class="form-group">
                					<label>Title</label>
                					<input class="form-control" name="title" value="${vo.title}">              				
                				</div>  
                				<div class="form-group">
                					<label>Content</label>
                					<textarea class="form-control" rows="3" name="content">${vo.content}</textarea>               				
                				</div> 
                				<div class="form-group">
                					<label>Writer</label>
                					<input class="form-control" name="writer" readonly="readonly" value="${vo.writer}">                				
                				</div>  
                				<%-- spring security csrf값 추가 --%>
                				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                				
                				<%-- 로그인사용자와 작성자가 동일할 때 modify/remove 버튼 보이기 --%>
                				<sec:authentication property="principal" var="info"/>
                				<sec:authorize access="isAuthenticated()">
                					<c:if test="${info.username == vo.writer}"><%-- 작성자, 로그인 아이디 동일한지 여부 확인 --%>
    	    	        				<button type="submit" data-oper='modify' class="btn btn-default">Modify</button>              			
	    	            				<button type="submit" data-oper='remove' class="btn btn-danger">Remove</button>              			
                					</c:if>
                				</sec:authorize>
                				<button type="submit" data-oper='list' class="btn btn-info">List</button>              			
                			</form>
                		</div>
                	</div>
                </div>
            </div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<i class="fa fas fa-file"></i> Files
			</div>
			<div class="panel-body">
				<div class="form-group uploadDiv">
					<input type="file" name="uploadFile" id="" multiple/>
				</div>
				<div class="uploadResult">
					<ul></ul>
				</div>
			</div>
		</div>
	</div>
</div>
<%-- remove와 list를 위한 폼--%>
<form action="" id="operForm" method="POST">
	<input type="hidden" name="type" value="${cri.type}" />
	<input type="hidden" name="keyword" value="${cri.keyword}" />
	<input type="hidden" name="pageNum" value="${cri.pageNum}" />
	<input type="hidden" name="amount" value="${cri.amount}" />
	<input type="hidden" name = "bno" value="${vo.bno}"/>
	
	<%-- spring security csrf값 추가 --%>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type="hidden" name="writer" value="${vo.writer}"/>
</form>
<%-- 스크립트 --%>
<script>
	var csrfHeaderName = "${_csrf.headerName}";
	var csrfTokenValue = "${_csrf.token}";
	let bno = ${vo.bno};
</script>
<script src="/resources/js/modify.js"></script>
<%@include file="../includes/footer.jsp" %>       