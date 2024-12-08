package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.PathContainer.Separator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.board.dao.BoardDAO;
import kr.or.ddit.vo.BoardFileVO;
import kr.or.ddit.vo.BoardVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	@Value("#{appInfo.fileSavePath}")
	private File saveFolder;
	
	private final BoardDAO dao;
	
	@Override
	public List<BoardVO> selectBoardList() {
		List<BoardVO> boardList =  dao.selectBoardList();
		return boardList;
	}

	@Override
	public BoardVO selectBoard(String boardCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertBoard(BoardVO boardVO) throws IllegalStateException, IOException {
		List<MultipartFile> files =  boardVO.getBoardFile();
		if(files.size()>0) {
			for(MultipartFile file : files) {
				String contentType = file.getContentType();
				// 컨텐트타입 검증 할꺼임
				 
				String fileName = file.getOriginalFilename();
				String uuid = UUID.randomUUID().toString();
				String saveFileName = uuid+fileName;
				File saveFile = new File(saveFolder, saveFileName);
				System.out.println("여기까지왔니 ?");
				file.transferTo(saveFile);
				
			}
		}
		int cnt = dao.insertBoard(boardVO);
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO boardVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(String boardCode) {
		// TODO Auto-generated method stub
		return 0;
	}

}
