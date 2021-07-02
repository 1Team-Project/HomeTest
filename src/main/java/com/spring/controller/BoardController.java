package com.spring.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.domain.AttachFileDTO;
import com.spring.domain.BoardVO;
import com.spring.domain.Criteria;
import com.spring.domain.PageVO;
import com.spring.service.BoardService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/list")
	public void list(Model model, Criteria cri) {
		
		log.info("전체 리스트 조회");
		List<BoardVO> list = service.list(cri);
		int total = service.total(cri);
		
		
		model.addAttribute("list", list);
		PageVO pageVo = new PageVO(cri, total);
		model.addAttribute("pageVO", pageVo);
		
	}
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/register")
	public void register() {
		
		log.info("레지스터 조회");
		
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/register")
	public String registerPost(BoardVO vo, RedirectAttributes rttr) {
		
		log.info("글 작성  "+vo);
		
		//첨부 파일 확인
//		if(vo.getAttachList()!=null) {
//			vo.getAttachList().forEach(attach -> log.info(""+attach));
//		} 
//		
//		return "redirect:register";
		
		if(service.insert(vo)) {
			log.info("글 작성 요청 : "+vo.getBno()+" /// "+vo.getAttachList());
			rttr.addFlashAttribute("result",vo.getBno());
			return "redirect:list"; // redirect:/board/list
		}else {
			return "redirect:register";
		}
		
	}
	@GetMapping({"/read", "/modify"})
	public void read(int bno,@ModelAttribute("cri") Criteria cri, Model model) {
		
		log.info("글 하나 불러오기"+cri);
		BoardVO vo = service.read(bno);
		model.addAttribute("vo", vo);

		
	}
	@PreAuthorize("principal.username == #vo.writer")
	@PostMapping("/modify")
	public String modifyPost(BoardVO vo, Criteria cri, RedirectAttributes rttr) {
		
		log.info("글 수정"+cri);

		//첨부 파일 확인
		if(vo.getAttachList()!=null) {
			vo.getAttachList().forEach(attach -> log.info(""+attach));
		}
		
		if(service.update(vo)) {
			rttr.addFlashAttribute("result", "성공");
			
			rttr.addAttribute("type",cri.getType());
			rttr.addAttribute("keyword",cri.getKeyword());
			rttr.addAttribute("pageNum",cri.getPageNum());
			rttr.addAttribute("amount",cri.getAmount());
			
			return "redirect:list"; // redirect:/board/list
		}else {
			return "redirect:modify?bno="+vo.getBno()+"&pageNum="+cri.getPageNum()+"&amount="+cri.getAmount();
		}
	}
	@PreAuthorize("principal.username == #writer")
	@PostMapping("/remove")
	public String remove(int bno, String writer, Criteria cri, RedirectAttributes rttr) {
		
		log.info("삭제 요청   "+bno);
		
		//서버에 저장된 첨부파일 삭제
		//1. bno에 해당되는 첨부파일 목록 알아내기
		List<AttachFileDTO> attachList = service.getAttachList(bno);
		
		//게시글 삭제 + 첨부파일 삭제
		if(service.delete(bno)) {

			//2. 폴더 파일 삭제
			deleteFiles(attachList);
			
			rttr.addFlashAttribute("result", "성공");
			
			rttr.addAttribute("type",cri.getType());
			rttr.addAttribute("keyword",cri.getKeyword());
			rttr.addAttribute("pageNum",cri.getPageNum());
			rttr.addAttribute("amount",cri.getAmount());
			return "redirect:list";
		}else {
			return "redirect:modify?bno="+bno+"&pageNum="+cri.getPageNum()+"&amount="+cri.getAmount();
		}
		
		
	}
	
	//첨부물 가져오기
	@GetMapping("/getAttachList")
	public ResponseEntity<List<AttachFileDTO>> getAttachList(int bno){
		log.info("첨부물 가져오기 "+bno);
		
		return new ResponseEntity<List<AttachFileDTO>>(service.getAttachList(bno),HttpStatus.OK);
	}
	
	private void deleteFiles(List<AttachFileDTO> attachList) {
		log.info("첨부파일 삭제 요청 "+attachList);
		
		if(attachList == null || attachList.size()<=0) {
			return;
		}
		
		for(AttachFileDTO dto:attachList) {
			Path path = Paths.get("d:\\upload\\",dto.getUploadPath()+"\\"+dto.getUuid()+"_"+dto.getFileName());
			try {
				Files.deleteIfExists(path);
				
				if(Files.probeContentType(path).startsWith("image")){
					Path thumbnail = Paths.get("d:\\upload\\",
							dto.getUploadPath()+"\\s_"+dto.getUuid()+"_"+dto.getFileName());
					Files.delete(thumbnail);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
