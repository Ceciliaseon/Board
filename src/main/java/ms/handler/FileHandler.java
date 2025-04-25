package ms.handler;

import lombok.extern.slf4j.Slf4j;
import ms.boardProject.domain.FileVO;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class FileHandler {

    //파일 저장되는 경로
    private final String UP_DIR = "C:\\Users\\mangs\\Desktop\\_myProject\\_java\\_fileUpload";

    public List<FileVO> uploadFiles(MultipartFile[] files){

        List<FileVO> flist = new ArrayList<>();

        LocalDate date = LocalDate.now();
        String today = date.toString();
        log.info(">> today >> {}", today);

        today = today.replace("-", File.separator);

        File folders = new File(UP_DIR, today);
        //폴더 생성
        if(!folders.exists()){
            folders.mkdirs();
        }

        for(MultipartFile file : files){
            FileVO fileVO = new FileVO();
            fileVO.setSaveDir(today);
            fileVO.setFileSize(file.getSize());

            String originalFileName = file.getOriginalFilename();
            String fileName = originalFileName.substring
                    (originalFileName.lastIndexOf(File.separator)+1);
            fileVO.setFileName(fileName);

            //UUID 생성
            UUID uuid = UUID.randomUUID();
            String uuidStr = uuid.toString();
            fileVO.setUuid(uuidStr);

            //디스크 저장
            String fullFileName = uuidStr+"_"+fileName; // <- 파일이름(전체)
            // 파일이름 > 아예 겹치지 않도록 유니크한 이름을 만들기 위해 today + uuid
            File storeFile = new File(folders, fullFileName);

            // 파일 저장
            try{
                file.transferTo(storeFile);
                log.info(">> file >> {}", storeFile.getAbsolutePath());

                if(isImageFile(storeFile)){
                    fileVO.setFileType(1);

                    //썸네일
                    File thumNail = new File(folders, uuidStr+"_th_"+fileName);
                    Thumbnails.of(storeFile).size(100, 100)
                            .toFile(thumNail);
                }
            }catch(Exception e){
                log.info(">> file error");
                e.printStackTrace();
            }
            // list에 추가
            flist.add(fileVO);
        }
        return flist;
    }

    //이미지 파일이 맞는지 확인
    public boolean isImageFile(File storeFile) throws Exception{
        String mimeType = new Tika().detect(storeFile);
        return mimeType.startsWith("image")? true : false;
    }

}
