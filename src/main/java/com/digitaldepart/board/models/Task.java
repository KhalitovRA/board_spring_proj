package com.digitaldepart.board.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author", nullable = false, length = 100)
    String author;

    @Column(name="executor", nullable = false, length = 100)
    String executor;

    @Column(name="release_start", columnDefinition="TIMESTAMP")
    private LocalDateTime release_start;

    @Column(name="release_end", columnDefinition="TIMESTAMP")
    private LocalDateTime release_end;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId
    private Board board;

//    @Enumerated(EnumType.STRING)
//    @Column(name="resourceType")
//    private TaskStatus type;

    private enum TaskStatus {
        BACKLOG, IN_PROGRESS, DONE
    }
//  TODO: продумать смену статуса задачи
    public Task(String author, String executor, LocalDateTime release_start, LocalDateTime release_end, Board board) {
        this.author = author;
        this.executor = executor;
        this.release_start = release_start;
        this.release_end = release_end;
        this.board = board;
    }

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public LocalDateTime getRelease_start() {
        return release_start;
    }

    public void setRelease_start(LocalDateTime release_start) {
        this.release_start = release_start;
    }

    public LocalDateTime getRelease_end() {
        return release_end;
    }

    public void setRelease_end(LocalDateTime release_end) {
        this.release_end = release_end;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

//    public TaskStatus getType() {
//        return type;
//    }

//    public void setType(TaskStatus type) {
//        this.type = type;
//    }

    public Task(Board board) {
        this.board = board;
    }

}
