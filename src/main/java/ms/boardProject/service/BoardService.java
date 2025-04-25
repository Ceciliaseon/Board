package ms.boardProject.service;

import ms.boardProject.domain.BoardDTO;
import ms.boardProject.domain.BoardVO;

import java.util.List;


public interface BoardService {

    int insert(BoardDTO boardDTO);

    List<BoardVO> getList();

    BoardDTO getDetali(int bno);

    void update(BoardVO boardVO);

    void remove(int bno);
}
