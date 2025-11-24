package com.korit.TODO.service;

import com.korit.TODO.dto.Request.AddTodoReqDto;
import com.korit.TODO.dto.Request.EditTodoReqDto;
import com.korit.TODO.entity.Todo;
import com.korit.TODO.repository.TodoRepository;
import com.korit.mybatis_study.dto.Request.EditBoardReqDto;
import com.korit.mybatis_study.dto.Response.ApiRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public ApiRespDto<?> addTodo (AddTodoReqDto addTodoReqDto) {
        Optional<Todo> getTitle = todoRepository.getTitle(addTodoReqDto.getTitle());

        if (getTitle.isPresent()) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("제목이 중복 되었습니다.")
                    .build();
        }

        Optional<Todo> todo = todoRepository.addTodo(addTodoReqDto.toEntity());

        if (todo.isEmpty()) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("todo 추가 중 오류 발생")
                    .build();
        }

        return ApiRespDto.builder()
                .status("success")
                .message("todo 추가 완료")
                .data(todo.get())
                .build();
    }

    public ApiRespDto<?> getTodoAll() {
        return ApiRespDto.builder()
                .status("success")
                .message("전체 조회 완료")
                .data(todoRepository.getTodoAll())
                .build();
    }

    public ApiRespDto<?> getTodo(Integer todoId) {
        Optional<Todo> todo = todoRepository.getTodo(todoId);

        if (todo.isEmpty()) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("해당 게시물이 존재하지 않습니다.")
                    .build();
        }

        return ApiRespDto.builder()
                .status("success")
                .message("게시글 조회 완료")
                .data(todo.get())
                .build();
    }

    public ApiRespDto<?> editTodo(EditTodoReqDto editTodoReqDto) {
        Optional<Todo> todo = todoRepository.getTodo(editTodoReqDto.getTodoId());

        if (todo.isEmpty()) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("해당 게시물이 존재하지 않습니다.")
                    .build();
        }

        int result = todoRepository.editTodo(editTodoReqDto.toEntity());

        if (result != 1) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("게시물 수정 중 오류 발생")
                    .build();
        }

        return ApiRespDto.builder()
                .status("success")
                .message("게시물 수정 완료")
                .build();
    }


}
