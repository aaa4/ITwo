package com.example.demo.docs.repository;

import com.example.demo.docs.model.Boards;
import com.example.demo.docs.model.UnitsTable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface BoardsRepository  extends CrudRepository<Boards, Integer>{

    List<Boards> findAll();
    Boards findById(int id);
    void deleteById(int id);


    void deleteByBoardNumber(int boardNumber);
}
