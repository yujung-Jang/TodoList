package com.yujung.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class TodoDto {

    @Getter
    @AllArgsConstructor
    public static class Post {
        private int id;

        @NotBlank(message = "title을 입력하세요.")
        private String title;

        private int order;
        private boolean completed;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        @Setter
        private int id;
        private String title;
        private int order;
        private boolean completed;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private int id;
        private String title;
        private int order;
        private boolean completed;
    }
}
