package ms.boardProject.repository;

import ms.boardProject.domain.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileDAO {

    int insertFile(FileVO fileVO);

    List<FileVO> getFileList(int bno);
}
