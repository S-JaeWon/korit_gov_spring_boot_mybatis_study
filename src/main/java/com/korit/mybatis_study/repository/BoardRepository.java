package com.korit.mybatis_study.repository;

import com.korit.mybatis_study.entity.Board;
import com.korit.mybatis_study.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BoardRepository {

    @Autowired
    private BoardMapper boardMapper;

    public Optional<Board> searchByTitle(String title) {
        return boardMapper.searchByTitle(title);
    }

    public Optional<Board> addBoard(Board board) {
        try {
            boardMapper.addBoard(board);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
        return Optional.of(board);
    }

    public List<Board> getBoardAll() {
        return boardMapper.getBoardAll();
    }

    public Optional<Board> getBoard(Integer boardId) {
        return boardMapper.getBoard(boardId);
    }

    public int editBoard(Board board) {
        return boardMapper.editBoard(board);
    }

    public int deleteBoard(Integer boardId) {
        return boardMapper.deleteBoard(boardId);
    }
}
