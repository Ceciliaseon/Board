package ms.boardProject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private BoardVO bvo;
    private List<FileVO> flist;


}
