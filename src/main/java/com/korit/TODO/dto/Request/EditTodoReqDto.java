package com.korit.TODO.dto.Request;

import com.korit.TODO.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditTodoReqDto {
    private Integer todoId;
    private String title;
    private String content;

    public Todo toEntity() {
        return Todo.builder()
                .todoId(todoId)
                .title(title)
                .content(content)
                .build();
    }
}
