package com.example.demo.docs.repository;

import com.example.demo.docs.model.UnitsTable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UnitsTableRepository extends CrudRepository<UnitsTable, Integer> {

    void deleteById(int id);
   // void deleteAll();
   List<UnitsTable> findAll();
    List<UnitsTable> findAllByBoardId(int boardId);



}
