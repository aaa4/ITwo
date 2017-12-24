package com.example.demo.docs.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Boards {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    long boardNumber;
    long serialNumber;

    //Один борт на много блоков
    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER, cascade = CascadeType.ALL ,orphanRemoval = true)
    List<UnitsTable> unitsTables;

    public Boards() {
    }

    public Boards(long boardNumber, long serialNumber, List<UnitsTable> unitsTables) {
        this.boardNumber = boardNumber;
        this.serialNumber = serialNumber;
        this.unitsTables = unitsTables;
    }



    public Boards(long boardNumber, long serialNumber) {
        this.boardNumber = boardNumber;
        this.serialNumber = serialNumber;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getBoardNumber() {
        return boardNumber;
    }

    public void setBoardNumber(long boardNumber) {
        this.boardNumber = boardNumber;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public List<UnitsTable> getUnitsTables() {
        return unitsTables;
    }

    public void setUnitsTables(List<UnitsTable> unitsTables) {
        this.unitsTables = unitsTables;
    }

    @Override
    public String toString() {
        return "Boards{" +
                "id=" + id +
                ", boardNumber=" + boardNumber +
                ", serialNumber=" + serialNumber +
                '}';
    }

    public void add(UnitsTable unitsTable){
        if (unitsTables == null){
            unitsTables = new ArrayList();
        }

        unitsTables.add(unitsTable);
        unitsTable.setBoard(this);
    }


}
