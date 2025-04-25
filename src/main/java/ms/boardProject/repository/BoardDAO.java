package ms.boardProject.repository;

import ms.boardProject.domain.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDAO {

    int insert(BoardVO boardVO);

    List<BoardVO> getList();

    BoardVO getDetail(int bno);

    void update(BoardVO boardVO);

    void remove(int bno);

    int selectBno();
}
