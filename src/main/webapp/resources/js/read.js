/**
 * read.jsp 에서 사용할 스크립트
 */

function showImage(fileCallPath){
	$(".bigPictureWrapper").css("display","flex").show();
	
	$(".bigPicture").html("<img src='/display?fileName="+fileCallPath+"'>").animate({width:'100%', height:'100%'},1000);
}

$(function(){
	
	//첨부 파일 가져오기
	$.getJSON({
		url:'getAttachList',
		data:{
			bno:bno
		},
		success:function(data){
			console.log(data);
			
			var str="";
			$(data).each(function(i,obj){
				
				if(obj.fileType){ //image
				
				//썸네일 이미지 경로 링크
				var fileCallPath = encodeURIComponent(obj.uploadPath+"\\s_"+obj.uuid+"_"+obj.fileName);
			
				str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"'";
				str += " data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
				str += "<img src='/display?fileName="+fileCallPath+"'>";
				str += "</li>";
				
				
			}else{
				//일반파일

				str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"'";
				str += " data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
				str += "<span>"+obj.fileName+" </span><br>"
				str += "<img src='/resources/img/attach.png'>";
				str += "</li>";
			}
				
			})
			$(".uploadResult ul").html(str);
		}
		
	})//getJSON 종료
	
	
	$(".uploadResult").on("click","li",function(){
		var liObj = $(this);
		
		var path = encodeURIComponent(liObj.data("path")+"/"+liObj.data("uuid")+"_"+liObj.data("filename"));
		
		if(liObj.data("type")){
			showImage(path.replace(new RegExp(/\\g/),"/"));
			console.log("image");
		}else{
			self.location="/download?fileName="+path;
		}
	})
	
	
	
	//확대된 사진 영역 없애기
	$(".bigPictureWrapper").click(function(){
		//원본 사진 줄이기
		$(".bigPicture").animate({width:'0%', height:'0%'},1000);
		
		//확대된 영역 없애기
		setTimeout(function(){
			$(".bigPictureWrapper").hide();
		},1000);
	})
	
	
	//operForm 가져온 후 전송하기
	var operForm = $("#operForm");
	
	//modify 버튼 클릭시 get방식 /board/modify
	$(".btn-default").click(function(){
		operForm.attr('action', '/board/modify');
		operForm.submit();
	})
	
	//List버튼 클릭시 get /board/list
	$(".btn-info").click(function(){
		operForm.find("input[name='bno']").remove();
		operForm.attr('action', '/board/list');
		operForm.submit();
	})
	
	
	//댓글 작업
	
	//댓글이 보여질 영역 가져오기
	let replyUl = $(".chat");
	showList(1);
	
	
	//모달 영역 가져오기
	let modal = $(".modal");
	
	$("#addReplyBtn").click(function(){
		modal.modal("show");
	})
	
	
	//작성날짜, 수정, 삭제버튼 제거한 후 모달 보여주기
	//모달 영역 안에 있는 input 가져오기
	var modalReply = modal.find("input[name='reply']");
	var modalReplyer = modal.find("input[name='replyer']");
	var modalDate = modal.find("input[name='replyDate']");
	
	//모달 영역 안에 있는 버튼 가져오기
	var modalModifyBtn = $("#modalModifyBtn");
	var modalRemoveBtn = $("#modalRemoveBtn");
	var modalRegisterBtn = $("#modalRegisterBtn");
		
	$("#addReplyBtn").click(function(){
		
		//input 안의 value 제거
		modal.find("input").val("");
		
		//작성날짜 제거한 후 모달 보여주기
		modalDate.closest("div").hide();
		
		//로그인 사용자
		modalReplyer.val(replyer);
		
		//수정,삭제버튼 제거
		modal.find("button[id!='modalCloseBtn']").hide();
		modalRegisterBtn.show();
	})
	
	//댓글 삽입 - bno, reply(댓글 내용), replyer(작성자)
	$("#modalRegisterBtn").click(function(){
		
		var reply = {
			bno:bno,
			reply:modalReply.val(),
			replyer:modalReplyer.val()
			};
		
		replyService.add(reply,function(result){

/*			if(result){
				alert(result);
			}*/
			
			modal.find("input").val("");
			modal.modal("hide");
			showList(-1);
			
		})
	})//add종료
	
	//댓글 목록 가져오기
	function showList(page){
		
	replyService.getList({bno:bno,page:page||1},function(total,data){
		console.log(data);
		console.log(total);
		
		if(page == -1){
			//마지막 페이지 계산
			pageNum = Math.ceil(total/10.0);
			showList(pageNum);
			return;
		}
		//댓글이 없는 경우
		if(data==null || data.length==0){
			replyUl.html("");
			return;
		}
		
		//댓글이 있는 경우
		var str="";
		for(var i=0,len=data.length||0;i<len;i++){
					str += "<li class='left clearfix' data-rno='"+data[i].rno+"'>";
					str += "<div>"
					str += "<div class='header'>"
					str += "<strong class='primary-font'>"+data[i].replyer+"</strong>"
					str += "<small class='pull-right text-muted'>"+replyService.displayTime(data[i].replydate)+"</small>"
					str += "<p>"+data[i].reply+"</p>"
					str += "</div>"
					str += "</div>"
					str += "</li>"
		}
		replyUl.html(str);
		showReplyPage(total);
			
		})
	
	}
	
	//페이지 나누기
	
	//댓글 페이지 영역 가져오기
	var replyPageFooter = $(".panel-footer");
	var pageNum = 1;
	function showReplyPage(total){
		
		//마지막 페이지 계산
		var endPage = Math.ceil(pageNum/10.0)*10;
		
		//시작 페이지 계산
		var startPage = endPage - 9;
		
		//이전 버튼
		var prev = startPage != 1;
		
		//다음 버튼
		var next = false;
		
		if(endPage * 10 >= total){
			endPage = Math.ceil(total/10.0);
		}
		
		if(endPage * 10 < total){
			next = true;
		}
		
		var str="<ul class = 'pagination pull-right'>";
		if(prev){
			str += "<li class='page-item'><a class='page-link' href='"+(startPage-1)+"'>Prev</a></li>";
		}
		for(var i=startPage;i<=endPage;i++){
			var active = pageNum == i?"active":"";
			str += "<li class='page-item "+active+"'>";
			str += "<a class='page-link' href='"+i+"'>"+i;
			str += "</a></li>";
		}
		if(next){
			str += "<li class='page-item'><a class='page-link' href='"+(endPage+1)+"'>Next</a></li>";
		}
		
		str+="</ul>";
		replyPageFooter.html(str);
		
	}
	
	//댓글 페이지 번호 클릭시
	replyPageFooter.on("click","li a",function(e){
		e.preventDefault(); // a 태그의 동작 막기
		
		pageNum = $(this).attr("href");
		showList(pageNum);
		
	})
	
	
	
	//댓글 삭제
	$("#modalRemoveBtn").click(function(){
		
		//로그인 여부 확인
		if(!replyer){
			alert("로그인 한 후 삭제가 가능합니다.");
			modal.modal("hide");
			return;
		}
		
		//현재 모달창에 있는 작성자와 로그인 사용자가 같은지 확인
		
		//현재 모달창 작성자 가져오기
		var oriReplyer = modalReplyer.val();
		//비교
		if(oriReplyer != replyer){
			alert("자신의 댓글만 삭제가 가능합니다.");
			modal.modal("hide");
			return;
		}
		
		
		replyService.remove(modal.data("rno"),oriReplyer,function(result){
			
			//alert(result);

			modal.modal("hide");
			showList(pageNum);
			
		});
	})
	
	//댓글 수정
	$("#modalModifyBtn").click(function(){
		
		//로그인 여부 확인
		if(!replyer){
			alert("로그인 한 후 수정이 가능합니다.");
			modal.modal("hide");
			return;
		}
		
		//현재 모달창에 있는 작성자와 로그인 사용자가 같은지 확인
		
		//현재 모달창 작성자 가져오기
		var oriReplyer = modalReplyer.val();
		//비교
		if(oriReplyer != replyer){
			alert("자신의 댓글만 수정이 가능합니다.");
			modal.modal("hide");
			return;
		}
		
		var reply = {
			rno:modal.data("rno"),
			replyer:modalReplyer.val(),
			reply:modalReply.val()
			};
		
		replyService.update(reply,function(result){
/*			if(result){
				alert(result);
			}*/
			
			modal.modal("hide");
			showList(pageNum);
			
		});
	})
	
	
	//이벤트 위임 : li 태그는 나중에 생기는 요소이기 때문에 ul에 먼저 이벤트를 건 후
	//				li에 넘겨주는 방식
	$(replyUl).on("click","li",function(){
		
		//클릭된 li요소의 rno 가져오기
		var rno = $(this).data("rno");
		
		//댓글 하나 가져오기
		replyService.get(rno,function(data){
			console.log(data);
			
			//댓글 모달 창에 보여주기
			modalReply.val(data.reply);
			modalReplyer.val(data.replyer);
			modalDate.val(replyService.displayTime(data.replydate)).prop("readonly","readonly");
			//rno값 필수로 담기 (PK)
			modal.data("rno",data.rno);
		
			//작성날짜 영역 보여주기 => 등록 후 댓글 보는 작업을 진행할 수도 있기 때문
			modalDate.closest("div").show();
			modal.find('button').show();
			modal.find("button[id='modalRegisterBtn']").hide();

		
			modal.modal("show");
		
		})
		
	})
	
	
	
	
	
})