package kr.or.ddit.vo;

import lombok.Data;

@Data
public class UpdateCommentVO {
	private String updateCommCode;
	private int commentNo;
	private String updateCommCn;
	private String updateCommTime;
}
