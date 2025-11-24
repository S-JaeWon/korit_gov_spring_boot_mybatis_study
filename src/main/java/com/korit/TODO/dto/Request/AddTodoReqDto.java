package com.korit.TODO.dto.Request;

import com.korit.TODO.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddTodoReqDto {
    private String title;
    private String content;

    public Todo toEntity() {
        return Todo.builder()
                .title(title)
                .content(content)
                .build();
    }
}
