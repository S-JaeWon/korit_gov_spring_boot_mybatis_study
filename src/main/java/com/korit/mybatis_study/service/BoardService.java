package com.korit.mybatis_study.service;

import com.korit.mybatis_study.dto.Request.AddBoardReqDto;
import com.korit.mybatis_study.dto.Request.EditBoardReqDto;
import com.korit.mybatis_study.dto.Response.ApiRespDto;
import com.korit.mybatis_study.entity.Board;
import com.korit.mybatis_study.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public ApiRespDto<?> addBoard(AddBoardReqDto addBoardReqDto) {
        Optional<Board> searchTitle =  boardRepository.searchByTitle(addBoardReqDto.getTitle());
        if(searchTitle.isPresent()) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("제목이 중복되었습니다.")
                    .build();
        }

        Optional<Board> board = boardRepository.addBoard(addBoardReqDto.toEntity());

        if(board.isEmpty()) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("추가하는데 오류 발생")
                    .build();
        }

        return ApiRespDto.builder()
                .status("success")
                .message("게시물 추가 완료")
                .data(board.get())
                .build();
    }

    public ApiRespDto<?> getBoardAll() {
        return ApiRespDto.builder()
                .status("success")
                .message("전체 조회 완료")
                .data(boardRepository.getBoardAll())
                .build();
    }

    public ApiRespDto<?> getBoard(Integer boardId) {
        Optional<Board> board = boardRepository.getBoard(boardId);

        if (board.isEmpty()) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("해당 게시물이 존재하지 않습니다.")
                    .build();
        }

        return ApiRespDto.builder()
                .status("success")
                .message("게시글 조회 완료")
                .data(board.get())
                .build();
    }

    public ApiRespDto<?> editBoard(EditBoardReqDto editBoardReqDto) {
        Optional<Board> board = boardRepository.getBoard(editBoardReqDto.getBoardId());

        if (board.isEmpty()) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("해당 게시물이 존재하지 않습니다.")
                    .build();
        }

        int result = boardRepository.editBoard(editBoardReqDto.toEntity());

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

    public ApiRespDto<?> deleteBoard(Integer boardId) {
        Optional<Board> board = boardRepository.getBoard(boardId);

        if (board.isEmpty()) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("해당 게시물이 존재하지 않습니다.")
                    .build();
        }

        int result = boardRepository.deleteBoard(board.get().getBoardId());

        if (result != 1) {
            return ApiRespDto.builder()
                    .status("failed")
                    .message("게시글 삭제 중 오류 발생")
                    .build();
        }

        return ApiRespDto.builder()
                .status("success")
                .message("게시글 삭제 완료")
                .build();
    }
}
