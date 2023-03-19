package com.digitaldepart.board.repo;

import com.digitaldepart.board.models.Board;
import com.digitaldepart.board.models.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findByBoard(Board board);
}
