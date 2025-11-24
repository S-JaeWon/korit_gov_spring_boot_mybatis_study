package com.korit.mybatis_study.controller;

import com.korit.mybatis_study.dto.Request.AddBoardReqDto;
import com.korit.mybatis_study.dto.Request.EditBoardReqDto;
import com.korit.mybatis_study.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/add")
    public ResponseEntity<?> addBoard(@RequestBody AddBoardReqDto addBoardReqDto) {
        return ResponseEntity.ok(boardService.addBoard(addBoardReqDto));
    }

    @GetMapping("/all")
    public ResponseEntity<?> boardListAll() {
        return ResponseEntity.ok(boardService.getBoardAll());
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<?> boardList(@PathVariable Integer boardId) {
        return ResponseEntity.ok(boardService.getBoard(boardId));
    }

    @PostMapping("/update")
    public ResponseEntity<?> editBoard(@RequestBody EditBoardReqDto editBoardReqDto) {
        return ResponseEntity.ok(boardService.editBoard(editBoardReqDto));
    }

    @PostMapping("/remove")
    public ResponseEntity<?> deleteBoard(@RequestParam Integer boardId) {
        return ResponseEntity.ok(boardService.deleteBoard(boardId));
    }

}
