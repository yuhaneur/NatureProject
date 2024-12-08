package kr.or.ddit.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardVO {

	private String boardCode; 
	private String boardSj; 
	private String boardCn; 
	private int boardViews; 
	private String boardWriteDate; 
	private int boardSuggestionCnt;  
	private String boardWriter; 
	private String boardFileName; 
	private String boardFilePath;  
	private String boardYn ;
	
	private List<MultipartFile> boardFile;
}
