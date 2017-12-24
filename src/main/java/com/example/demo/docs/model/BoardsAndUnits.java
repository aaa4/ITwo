package com.example.demo.docs.model;

import java.util.List;

/**
 * Этот гибридный класс создан для того
 * чтобы передавать посредством него данные
 * в modelAttribute поле модели
 * библиотеки spring mvc
 *
 */


public class BoardsAndUnits {

    private Boards boards;
    private UnitsTable unitsTable;


    //constructors & getters &setters

    public BoardsAndUnits() {
    }

    public BoardsAndUnits(Boards boards, UnitsTable unitsTable) {
        this.boards = boards;
        this.unitsTable = unitsTable;
    }

    public Boards getBoards() {
        return boards;
    }

    public void setBoards(Boards boards) {
        this.boards = boards;
    }

    public UnitsTable getUnitsTable() {
        return unitsTable;
    }

    public void setUnitsTable(UnitsTable unitsTable) {
        this.unitsTable = unitsTable;
    }
}
