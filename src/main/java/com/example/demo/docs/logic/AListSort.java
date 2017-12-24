package com.example.demo.docs.logic;


import com.example.demo.docs.model.Boards;
import com.example.demo.docs.model.UnitsTable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AListSort<T> {

    private static List<UnitsTable> unitsTables;
    private static List<UnitsTable> result;

    public AListSort(){

    }



    public void printList(List<T> list){
        for (T t: list)
            System.out.println(t);
        System.out.println();
    }

    public static void main(String[] args) {
        AListSort aListSort = new AListSort();
        aListSort.setUnitsTables(preSet());
        System.out.println("Сгенерированная таблица");
        aListSort.printList(aListSort.getUnitsTables());

        List<UnitsTable> list = aListSort.getUnitsTables();
        int id = 2;

        //получаем запись с нужным айди из листа (id != порядковый номер в листе)
        UnitsTable ut = findById(list, id).get(0); //запись с id = 2


        //получаем номер записи в листе
        int index =list.indexOf(ut);
        System.out.println("indexOd(ut) = " +index);
        System.out.println(list.indexOf(ut));

        //изменяем некоторые поля записи
        ut.setNote("Updated note");

        //сохраняем запись обратно в лист с тем же айди
        list.set(index,ut);

        //смотрим на готовый лист
        aListSort.printList(list);

        //Теперь это все можно повторить в юнит контроллере










        //сохраняем запись обратно в лист с тем же айди





       /* List<UnitsTable> theList  = preSet();

        for (UnitsTable ut : theList)
            System.out.println(ut);
        System.out.println();
        UnitsTable utById = findById(theList,4).get(0);
        int idx = theList.indexOf(utById);
        System.out.println(idx);
        System.out.println(utById);
        utById.setNote("asdasdasd");
        theList.set(idx,utById);
        System.out.println();
        for (UnitsTable ut : theList)
            System.out.println(ut);*/


       // System.out.println(utById);


    }

  /*  UnitsTable ut = new UnitsTable();
    List<UnitsTable> list = new ArrayList<>();
    List<UnitsTable> result = list.stream()
            .filter(item -> item.getId()== 2)
            .collect(Collectors.toList());
*/

  public static List<UnitsTable> findById(List<UnitsTable> list, int id){
      List<UnitsTable> result = list.stream()
                .filter(item -> (item.getId()== id))
                .collect(Collectors.toList());

      return result;
  }

    public static List<UnitsTable> preSet(){
        int size = 10;
        List<UnitsTable> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {

            UnitsTable ut = new UnitsTable();
            ut.setId(i);
            ut.setBoard(new Boards(i*3+1,i*100+20+1));
            ut.setUnitType("unit"+i*1);
            ut.setReleaseDate("Date"+i);
            ut.setNote("note"+i);
            list.add(ut);
        }
        return list;
    }

    public static List<UnitsTable> getUnitsTables() {
        return unitsTables;
    }

    public static void setUnitsTables(List<UnitsTable> unitsTables) {
        AListSort.unitsTables = unitsTables;
    }

    public static List<UnitsTable> getResult() {
        return result;
    }

    public static void setResult(List<UnitsTable> result) {
        AListSort.result = result;
    }
}
