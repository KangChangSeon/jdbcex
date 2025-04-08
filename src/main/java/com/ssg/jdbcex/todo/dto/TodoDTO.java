package com.ssg.jdbcex.todo.dto;

import java.time.LocalDateTime;

public class TodoDTO {
    private Long tno;
    private String title;
    private LocalDateTime duedate;
    private boolean finished;

    public long getTno() {
        return tno;
    }

    public void setTno(long tno) {
        this.tno = tno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDuedate() {
        return duedate;
    }

    public void setDuedate(LocalDateTime duedate) {
        this.duedate = duedate;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "TodoDTO{" +
                "tno=" + tno +
                ", title='" + title + '\'' +
                ", duedate=" + duedate +
                ", finished=" + finished +
                '}';
    }
}
