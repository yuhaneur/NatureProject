package kr.or.ddit.board.service;

import java.io.IOException;
import java.util.List;

import kr.or.ddit.vo.BoardVO;

public interface BoardService {
	public List<BoardVO> selectBoardList();
	
	public BoardVO selectBoard(String boardCode);
	
	public int insertBoard(BoardVO boardVO)throws IllegalStateException, IOException;
	
	public int updateBoard(BoardVO boardVO);
	
	public int deleteBoard(String boardCode);
}
