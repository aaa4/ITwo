package com.example.demo.docs.model;


import javax.persistence.*;


@Entity
public class UnitsTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String unitType;
    String releaseDate; //дату оставляю строкой тк в ней иногда запись "бп" бывает
    String note;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
            /*(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)*/
    @JoinColumn(name = "boards_idx")
    private Boards board;

    public UnitsTable() {
    }

    public UnitsTable(String unitType, String releaseDate, String note) {
        this.unitType = unitType;
        this.releaseDate = releaseDate;
        this.note = note;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boards getBoard() {
        return board;
    }

    public void setBoard(Boards board) {
        this.board = board;
    }


    @Override
    public String toString() {
        return "UnitsTable{" +
                "id=" + id +
                ", unitType='" + unitType + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
