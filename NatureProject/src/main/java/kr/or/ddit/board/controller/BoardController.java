package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping()
	private String boardMainPage(Model model) {
		System.out.println("보드메인");
		List<BoardVO> boardList =  boardService.selectBoardList();
		model.addAttribute("boardList", boardList);
		return "board/boardMain";
	}
	
	@GetMapping("/insert")
	private String boardInsertPage() {
		return "board/boardInsert";
	}
	
	@PostMapping("/insert")
	@ResponseBody
	private Map<String, String> boardInsert(@ModelAttribute BoardVO boardVO) throws IllegalStateException, IOException{
		Map<String, String> resultMap = new HashMap<String, String>();
		int cnt = boardService.insertBoard(boardVO);
		log.info("boardVO :  {}" , boardVO);
		return resultMap;
	}
}
