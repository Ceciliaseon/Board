package ms.boardProject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileVO {

    private String uuid; //파일고유이름
    private String saveDir; //파일저장경로
    private String fileName; //파일이름
    private int fileType; //파일타입
    private int bno; //게시글번호
    private Long fileSize; //파일크기
    private String regDate; //등록일

}
