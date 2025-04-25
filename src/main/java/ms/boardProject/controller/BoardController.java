package ms.boardProject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.boardProject.domain.BoardDTO;
import ms.boardProject.domain.BoardVO;
import ms.boardProject.domain.FileVO;
import ms.boardProject.service.BoardService;
import ms.handler.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {

    private final BoardService boardService;
    private final FileHandler fileHandler;

    @GetMapping("/register")
    public String register(){
        return "/board/register";
    }

    @PostMapping("/register")
    public String insert(BoardVO boardVO,
                         @RequestParam(name = "file", required = false) MultipartFile[] files){

        //파일 핸들러처리
        List<FileVO> flist = null;
        if(files[0].getSize() > 0){

            //핸들러 호출
            flist = fileHandler.uploadFiles(files);
            boardVO.setHasFile(flist.size());
            log.info(">>> files >> {}", files);
        }
        BoardDTO boardDTO = new BoardDTO(boardVO, flist);
        boardService.insert(boardDTO);
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String boardList(Model model){
        List<BoardVO> list = boardService.getList();
        model.addAttribute("list",   list);
        return "/board/list";
    }

    @GetMapping({"/detail", "/modify"})
    public void detail(Model model, @RequestParam("bno") int bno){
        log.info(">>> detail Controller 호출");
        BoardDTO bdto  = boardService.getDetali(bno);
        log.info(">>> bdto = {}", bdto);
        model.addAttribute("bdto", bdto);
    }

    @PostMapping("/modify")
    public String modify(BoardVO boardVO){
        boardService.update(boardVO);
        return "redirect:/board/detail?bno=" + boardVO.getBno();
    }

    @GetMapping("remove")
    public String remove(@RequestParam("bno") int bno){
        boardService.remove(bno);
        return "redirect:/board/list";
    }


}
