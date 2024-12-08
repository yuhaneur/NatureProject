package kr.or.ddit.vo;

import lombok.Data;

@Data
public class NoticeVO {

	private String noticeCode; 
	private String noticeSj; 
	private String noticeCn; 
	private int noticeViews; 
	private String noticeWriteDate; 
	private String noticeWriter; 
	private String noticeYn;
}
