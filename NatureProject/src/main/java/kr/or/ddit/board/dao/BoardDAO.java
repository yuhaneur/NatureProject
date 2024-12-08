package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.BoardFileVO;
import kr.or.ddit.vo.BoardVO;

@Mapper
public interface BoardDAO {
	public List<BoardVO> selectBoardList();
	
	public BoardVO selectBoard(String boardCode);
	
	public int insertBoard(BoardVO boardVO);
	
	public int updateBoard(BoardVO boardVO);
	
	public int deleteBoard(String boardCode);
	
	public int insertBoardFile(BoardFileVO boardFileVO);
}
