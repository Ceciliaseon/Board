package ms.boardProject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardVO {

    /**
     * 게시글 NO, 제목, 작성자, 내용, 작성일, 수정일
     */

    private long bno;
    private String title;
    private String writer;
    private String content;
    private String regAt;

    private int hasFile; // 첨부파일 여부

}
