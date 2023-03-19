package com.digitaldepart.board.repo;

import com.digitaldepart.board.models.Board;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Long> {
}
