package com.digitaldepart.board.controllers;

import com.digitaldepart.board.models.Board;
import com.digitaldepart.board.models.Task;
import com.digitaldepart.board.repo.BoardRepository;
import com.digitaldepart.board.repo.TaskRepository;
import com.digitaldepart.board.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("/projects")
public class ProjectsController {
    private final BoardRepository boardRepository;
    private final TaskRepository taskRepository;

    private final BoardService boardService;


    @Autowired
    public ProjectsController(BoardRepository boardRepository, TaskRepository taskRepository, BoardService boardService) {
        this.boardRepository = boardRepository;
        this.taskRepository = taskRepository;
        this.boardService = boardService;
    }

    @GetMapping("/projects")
    public String board(Model model){
        Iterable<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "main-page";
    }

    @GetMapping("/projects/add")
    public String boardAdd(Model model) {
        return "projects";
    }

    @PostMapping("/projects/add")
    public String addBoard(@RequestParam String title, Model model) {
        Board board = new Board(title);
        boardRepository.save(board);
        model.addAttribute("board", board);
        return "projects";
    }

    @GetMapping("/projects/{id}")
    public String projectDetails(@PathVariable(value = "id") long project_id, Model model) {
//        Optional<Board> board = boardRepository.findById(project_id);
        Board board = boardService.findOne(project_id);
        List<Task> tasks = taskRepository.findByBoard(board);
//        ArrayList<Board> res = new ArrayList<>();
//        board.ifPresent(res::add);
        model.addAttribute("board", board);
        model.addAttribute("tasks", tasks);
        return "project-details";
    }
//    TODO: добавить swagger
}
