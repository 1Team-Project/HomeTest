/**
 * 
 */

$(function() {
	
	//업로드 되는 파일의 종류와 크기 제한
	function checkExtension(fileName, fileSize){
		
		var regex = new RegExp("(.*?)\.(txt|jpg|gif|png|bmp)");
		
		var maxSize = 10242880; //5mb
		
		if(fileSize>maxSize){
			alert("파일 사이즈 초과");
			return false;
		}
		if(!regex.test(fileName)){
			alert("해당 종류의 파일은 업로드 할 수 없습니다.");
			return false;
		}
		return true;
	}
	
	
	$("input[type='file']").change(function() {
		
		var files=$("input[name='uploadFile']")[0].files;
		console.log(files);
		
		//첨부파일을 formData 로 만들어 전송
		var formData = new FormData();
		for(var i=0; i<files.length; i++){
			if(!checkExtension(files[i].name,files[i].size)){
				return false;
			}
			formData.append("uploadFile",files[i]);
		}
		
		$.ajax({
			url:'/uploadAjax',
			type:'post',
			processData:false,
			contentType:false,
			beforeSend:function(xhr){
				xhr.setRequestHeader(csrfHeaderName,csrfTokenValue);
			},
			data:formData,
			success:function(result){
				console.log(result);
				showUploadedFile(result);
				$("input[name='uploadFile']").val("");
			},
			error:function(xhr, status, error){
				console.log("에러");
			}
		})
	})//uploadBtn 종료
	
	function showUploadedFile(uploadResultArr){
		var str = "";
		var uploadResult = $(".uploadResult ul");
		
		$(uploadResultArr).each(function(i,obj){
			
			if(obj.fileType){ //image
				//썸네일 이미지 경로 링크
				var fileCallPath = encodeURIComponent(obj.uploadPath+"\\s_"+obj.uuid+"_"+obj.fileName);
			
				//원본이미지
				var originPath = obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName;
				originPath = originPath.replace(new RegExp(/\\/g),"/");
			
				str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"'";
				str += " data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
				str += "<a href=\"javascript:showImage(\'"+originPath+"\')\">";
				str += "<span>"+obj.fileName+" </span>"
				str += "<button type='button' class='btn btn-warning btn-circle btn-sm' data-file='"+fileCallPath+"' data-type='image'>"
				str += "<i class='fa fa-times'></i></button><br>";
				str += "<img src='/display?fileName="+fileCallPath+"'></a>";
				str += "</li>";
				
				
			}else{
				//일반파일
				var fileCallPath = encodeURIComponent(obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName);

				str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"'";
				str += " data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
				str += "<span>"+obj.fileName+" </span>"
				str += "<button type='button' class='btn btn-warning btn-circle btn-sm' data-file='"+fileCallPath+"' data-type='file'>"
				str += "<i class='fa fa-times'></i></button><br>";
				str += "<a href='/download?fileName="+fileCallPath+"'>";
				str += "<img src='/resources/img/attach.png'></a>";
				str += "</li>";
			}
			
		})
		
		uploadResult.append(str);
	}
	
	$("button[type='submit']").click(function(e){
		e.preventDefault();
		
		var str = "";
		$(".uploadResult ul li").each(function(idx,obj){
			var job = $(obj);
			//수집된 정보를 hidden태그로 작성
			str += "<input type='hidden' name='attachList["+idx+"].uuid' value='"+job.data("uuid")+"'>";
			str += "<input type='hidden' name='attachList["+idx+"].uploadPath' value='"+job.data("path")+"'>";
			str += "<input type='hidden' name='attachList["+idx+"].fileName' value='"+job.data("filename")+"'>";
			str += "<input type='hidden' name='attachList["+idx+"].fileType' value='"+job.data("type")+"'>";
			
		})
		console.log(str);
		
		//게시글 등록 폼 가져오기
		let getForm = $("form[action='register']");
		
		//수집된 내용 폼에 추가하기
		getForm.append(str);
		
		//폼 전송하기
		getForm.submit();
	})
	
	// X 버튼 클릭시 동작
	$(".uploadResult").on("click","button",function(){
		//목록에 있는 첨부파일 삭제, 서버 폴더 첨부파일 삭제
		var targetFile = $(this).data("file");
		var type = $(this).data("type");
		
		//li 태그 가져오기
		var targetLi = $(this).closest("li");
		
		$.ajax({
			url:'/deleteFile',
			beforeSend:function(xhr){
				xhr.setRequestHeader(csrfHeaderName,csrfTokenValue);
			},
			data:{
				fileName:targetFile,
				type:type
			},
			type:'post',
			success:function(result){
				console.log(result);
				targetLi.remove();
			}	
			
		})
	})
	
	
})