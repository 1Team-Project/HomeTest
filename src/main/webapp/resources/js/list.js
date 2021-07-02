/**
 * 
 */

$(function(){
	
	checkModal(result);
	
	history.replaceState({},null,null);
		
	function checkModal(result){
		if(result === '' || history.state){
			return;
		}
		
		if(parseInt(result)>0){
			$(".modal-body").html("게시글 "+parseInt(result)+"번이 등록되었습니다.");
		}
		
	$("#myModal").modal("show");
	
	}
	
	
	//하단의 페이지 나누기 번호 클릭시
	
	var actionForm = $("#actionForm");
	
	
	$(".paginate_button a").click(function(e){
		e.preventDefault(); // a 속성 중지
		
		console.log($(".paginate_button a").data());
		
		//actionForm의 안에 pageNum의 값을 사용자가 선택한 번호로 변경
		
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		
		//actionForm을 보내기
		actionForm.submit();
		
	})
	
	//게시물 수 변경
	$("#amount").change(function(){
		//사용자가 선택한 게시물 수 가져오기
		let amount = $(this).val();
		
		//actionForm의 안에 amount의 값을 사용자가 선택한 번호로 변경
		actionForm.find("input[name='amount']").val(amount);
		
		//actionForm을 보내기
		actionForm.submit();
	})
	
	$(".move").click(function(e){
		e.preventDefault();
		
		actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'>");
		actionForm.attr('action','read');
		actionForm.submit();
		
	})
	
	//검색과 관련
	$("#buttonType").click(function(){
		
		let searchForm = $("#searchForm");
		
		var type = $("select[name='type']").val();
		
		var keyword = $("input[name='keyword']").val();
		
		if(type === ''){
			alert("검색 기준을 다시 한번 확인해 주세요!");
			$("select[name='type']").focus();
			return false;
		}
		if(keyword === ''){
			alert("검색어를 확인해 주세요!");
			$("input[name='keyword']").focus();
			return false;
		}	
		
		searchForm.find("input[name='pageNum']").val("1");
		
		searchForm.submit();
		
	})
	
})