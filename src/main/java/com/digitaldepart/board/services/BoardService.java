package com.digitaldepart.board.services;

import com.digitaldepart.board.models.Board;
import com.digitaldepart.board.repo.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

//    public List<Board> findAll() {
//        boardRep.find
//    }

    public Board findOne(long id) {
        Optional<Board> board = boardRepository.findById(id);
        return board.orElse(null);
    }
}
