package com.example.demo.docs.logic;

import com.example.demo.docs.model.Boards;
import com.example.demo.docs.model.UnitsTable;
import com.example.demo.docs.repository.BoardsRepository;
import com.example.demo.docs.repository.UnitsTableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestBean implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(TestBean.class);

    @Autowired
    UnitsTableRepository unitsTableRepository;
    @Autowired
    BoardsRepository boardsRepository;

    @Override
    public void run(String... strings) throws Exception {

      //  checkLists();
        logger.info("Стоп.");
    }


    //метод для проверки как правильно изменять дочернюю таблицу
    public void checkLists(){
        int id = 345;
        int unitId = 1167;

        //получаем борт по его айди
        Boards board = boardsRepository.findById(id);

        //получаем список блоков с борта
        List<UnitsTable> list = board.getUnitsTables();

        //находим блок в списке с нужным нам айди
        List<UnitsTable> result = list.stream()
                .filter(item -> (item.getId()== unitId))
                .collect(Collectors.toList());
        UnitsTable ut = result.get(0); //у меня один такой элемент всего
        System.out.println(ut);

        //получаем его порядковый номер в списке
        int indexOfUt  = list.indexOf(ut);
        System.out.println(indexOfUt);

        //изменяем информацию о блоке
        ut.setNote("this is updated note");

        //сохраняем блок в список с тем же айди что и раньше
        list.set(indexOfUt, ut);

        //сохраняем список в борт?

        //сохраняем борт в базу данных

        board.setUnitsTables(list);
        boardsRepository.save(board);


    }

    private void addNewUnits() {
        Boards board = boardsRepository.findById(30);
        board.add(new UnitsTable("a", "b", "c"));
        board.setBoardNumber(8888808l);
        boardsRepository.delete(30);
        boardsRepository.save(board);
    }

    private void setNewBoards() {
        boardsRepository.deleteAll();
        for (int i = 0; i < 100; i++) {
            Boards board = new Boards((long) i, (long) i + 200002200l);
            for (int j = 0; j < 10; j++) {
                board.add(new UnitsTable("type" + i, "date" + i, "note" + i));
            }
            boardsRepository.save(board);

        }
    }

    private void populate() {
      /*  int numberOfUnitsAtBoard = 10;
        int numberOfBoards = 10;
        String blockName = "Block №";
        String note = "note №";

       long a =  boardsRepository.count();
        System.out.println(a);
       List<UnitsTable> unitsTables = new ArrayList<>();
        for (int i = 0; i < numberOfUnitsAtBoard; i++) {
            unitsTables.add(new UnitsTable(blockName+i, i+i+1+" "+i+2+i+4+" "+i+1*2+i*1*2,note+i));

        }

        for (int i = 0; i < numberOfBoards; i++) {
            Boards board = new Boards( (long)2+i*1, (long)2+i+2000004);
            for (UnitsTable unitsTable: unitsTables){
                board.add(unitsTable);
            }
            boardsRepository.save(board);
        }
*/


        UnitsTable unitsTable1 = new UnitsTable("block11", "date11", "note1");
        UnitsTable unitsTable2 = new UnitsTable("block2", "date2", "note2");
        UnitsTable unitsTable3 = new UnitsTable("block3", "date3", "note3");
        UnitsTable unitsTable4 = new UnitsTable("block4", "date4", "note4");
        UnitsTable unitsTable5 = new UnitsTable("block5", "date5", "note5");

        UnitsTable unitsTable11 = new UnitsTable("block11", "date11", "note1");
        UnitsTable unitsTable22 = new UnitsTable("block2", "date2", "note2");
        UnitsTable unitsTable33 = new UnitsTable("block3", "date3", "note3");
        UnitsTable unitsTable44 = new UnitsTable("block4", "date4", "note4");
        UnitsTable unitsTable55 = new UnitsTable("block5", "date5", "note5");

        Boards board = new Boards(34, 30000022);
        Boards board1 = new Boards(35, 30000023);

        board.add(unitsTable1);
        board.add(unitsTable2);
        board.add(unitsTable3);
        board.add(unitsTable4);
        board.add(unitsTable5);
        boardsRepository.save(board);


        board1.add(unitsTable11);
        board1.add(unitsTable22);
        board1.add(unitsTable33);
        board1.add(unitsTable44);
        board1.add(unitsTable55);
        boardsRepository.save(board1);


    }
}
