package kr.or.ddit.vo;

import lombok.Data;

@Data
public class CommentVO {
	private int commentNo; 
	private String boardCode ; 
	private String commentCN ; 
	private String commentWriteDate; 
	private String commentWriter; 
	private int topCommentNo; 
	private String commentYn;

}
