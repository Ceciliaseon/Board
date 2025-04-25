package ms.boardProject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.boardProject.domain.BoardDTO;
import ms.boardProject.domain.BoardVO;
import ms.boardProject.domain.FileVO;
import ms.boardProject.repository.BoardDAO;
import ms.boardProject.repository.FileDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardDAO boardDAO;
    private final FileDAO fileDAO;

    @Override
    public int insert(BoardDTO boardDTO) {
        int isOk = boardDAO.insert(boardDTO.getBvo());

        //파일처리
        if(boardDTO.getFlist() == null){ //파일이 없을때
            return isOk;
        } else { //파일이 있을때
            if(isOk > 0 && boardDTO.getFlist().size() > 0){
                int bno = boardDAO.selectBno(); //bno 찾아오기
                for(FileVO fileVO : boardDTO.getFlist()){
                    fileVO.setBno(bno); //bno 세팅
                    log.info(">>> fileVO = {}", fileVO);
                    // 파일 bno 저장
                    isOk *= fileDAO.insertFile(fileVO); //파일 insert
                }
            }
        }
        return isOk;
    }

    @Override
    public List<BoardVO> getList() {

        log.info(">>> getList() 호출");

        return boardDAO.getList();
    }

    @Override
    public BoardDTO getDetali(int bno) {
        log.info(">>> getDetail() 호출");
        log.info("bno = {}", bno);

        BoardDTO bdto = new BoardDTO();
        BoardVO bvo = boardDAO.getDetail(bno);
        bdto.setBvo(bvo);
        bdto.setFlist(fileDAO.getFileList(bno));
        return bdto;
    }

    @Override
    public void update(BoardVO boardVO) {
        log.info(">>> update() 호출");
        log.info("제목 = {}", boardVO.getTitle());
        log.info("내용 = {}", boardVO.getContent());
        boardDAO.update(boardVO);
    }

    @Override
    public void remove(int bno) {
        boardDAO.remove(bno);
    }


}
