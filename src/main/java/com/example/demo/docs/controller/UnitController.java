package com.example.demo.docs.controller;

import com.example.demo.docs.model.Boards;
import com.example.demo.docs.model.UnitsTable;
import com.example.demo.docs.repository.BoardsRepository;
import com.example.demo.docs.repository.UnitsTableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UnitController {
    @Autowired
    UnitsTableRepository unitsTableRepository;

    @Autowired
    BoardsRepository boardsRepository;

    @Autowired
    MyService service;

    Logger logger = LoggerFactory.getLogger(UnitController.class);

    //todo: поразмыслить над тем, чтобы данные передавать внутри самого сервера
    //todo: т.е. напирмер через @Service классы. Так можно создать отдельный поток для данных
    //todo: в данном классе похоже theBoardId передается.
    //todo: недостаток этого способа в том, что если переходишь на страницу ссылкой "назад" пропуская
    //todo: форму где считывается из параметров запроса boardId то он становится 0 и появляется ошибка

    private int theBoardId;
    private int UID;
    private int DelId;

    //Возвращает список блоков на одном борту в виде таблице html
    @GetMapping("/getunitsList")
    public String getUnitForm(@RequestParam(name = "boardId") int theId, Model model) {
        model.addAttribute("boardId", theId);
        model.addAttribute("link", "/addNewUnit?boardId=355");
        Boards board = boardsRepository.findById(theId);
        List<UnitsTable> unitsTable = board.getUnitsTables();
        model.addAttribute("units", unitsTable);
        theBoardId = theId;
        System.out.println("the id  = " + theBoardId);
        return "unitsList";
    }

    @GetMapping("/addNewUnit")
    public String getAddNewUnitForm(Model model) {
        model.addAttribute("newUnit", new UnitsTable());
        model.addAttribute("boardId", theBoardId);

        return "addNewUnit";
    }

    @GetMapping("/updateUnit")
    public String getUpdateUnitForm(@RequestParam(name = "unitId") int unitId, Model model) {

        UnitsTable ut = unitsTableRepository.findOne(unitId);
        UID = unitId;
        model.addAttribute("unit", ut);

        myLogger(unitId, ut, ""); //вывод на консоль

        return "updateUnit";
    }

    //todo: почему то он добавляет новую запись а не апдейтит старую.

    @PostMapping("/updateUnit")
    public String postUpdateUnitForm(@ModelAttribute UnitsTable utNew) {


        //todo: рефактор все ниже в сервис
        //получаем индекс записи unitId (из гет маппинга выше) в базе данных
        // int id = utNew.getId();
        int id = UID;
        logger.info(" id = {}", id + "");
        logger.info(" theBoardId = {}", theBoardId + "");
        logger.info(utNew.toString());

        Boards board = boardsRepository.findById(theBoardId);
        logger.info("board = {}", board);
        List<UnitsTable> list = board.getUnitsTables();
        logger.info("list = {}", list);

        for (UnitsTable ut : list)
            logger.info(ut.toString());

        //получаем запись unitId из списка, привязанного к борту с айди theBoardId
        UnitsTable utOld = findById(list, id).get(0);  //нужная мне таблица

        //запрашиваем номер данной записи в списке
        int theId = list.indexOf(utOld);

        //изменяем запись utOld
        utOld.setNote(utNew.getNote());
        utOld.setReleaseDate(utNew.getReleaseDate());
        utOld.setUnitType(utNew.getUnitType());

        //перезаписываем в лист обновленные данный в тот же номер записи
        list.set(theId, utOld);

        //передаем список экземпляру класса борт
        board.setUnitsTables(list);

        //сохраняем борт в бд
        boardsRepository.save(board);

        return "redirect:/getunitsList?boardId=" + theBoardId;
    }

    @PostMapping("/addNewUnit")
    public String postAddNewUnitForm(@ModelAttribute UnitsTable unitsTable) {

        Boards board = boardsRepository.findById(theBoardId);
        board.add(unitsTable);
        boardsRepository.save(board);

        return "redirect:/getunitsList?boardId=" + theBoardId;
    }


    //todo: исследовать возможность использовния модели с большим количеством аттрибутов и параметров
    @GetMapping("/deleteUnit")
    public String getUnitDeletePage(@RequestParam(name = "unitId") int unitId,
                                    @RequestParam(name = "boardId") int boardId,
                                    Model model) {
        model.addAttribute("unit", unitsTableRepository.findOne(unitId));
        model.addAttribute("boardId", boardId);
        theBoardId = boardId;
        DelId = unitId;
        return "deleteUnit";
    }

    //todo:разобраться с удалением не работает корректно
    //почему то не передается сюда theBoardId
    @PostMapping("/deleteUnit")
    public String deleteUnit(@ModelAttribute UnitsTable unit, Model model) {

        //Todo: удаление реализовать так же как update

        //передать 2 реквест параметра айди блока и айди борта

        //получить борт из бд
        Boards theBoard = boardsRepository.findById(theBoardId);

        //получить список блоков
        List<UnitsTable> utList = theBoard.getUnitsTables();
        logger.info(utList+"");
        logger.info(" list size is {} before delete", utList.size());

        //самописным методом взять блок
        UnitsTable ut = findById(utList, DelId).get(0);
        logger.info("this is ut={}" ,ut);
        utList.remove(ut);

       /* //получить его айди в списке
       int currentUtId = utList.indexOf(ut);
        logger.info("this is currentId={}" ,currentUtId);
        //удалить блок из списка
        utList.remove(currentUtId);

*/      logger.info(" list size is {} after delete", utList.size());
        //передать это список в борт
        theBoard.setUnitsTables(utList);
        logger.info(utList+"");
        //сохранить борт в базе данных
        boardsRepository.save(theBoard);



        return "redirect:/getunitsList?boardId=" + theBoardId;
    }


    /**
     * Вот так в thymeleaf оформляются параметры
     * <a data-th-href="@{'/updateUnit'(paramA=${paramA},paramB=${paramB})}">update</a>
     * на выходе это /updateUnit?paramA=${paramA}&paramB=${paramB}
     * делают так из-за экранирования амперсанда в html
     */

    @GetMapping("reqparams")
    public String myRequestParams(
            @RequestParam("paramA") String paramA,
            @RequestParam("paramB") String paramB,

            Model model) {
        //String paramA="a";
        // String paramB="b";
        String paramC = "c";
        String paramD = "d";
        String paramE = "e";
        String paramF = "f";
        model.addAttribute("paramA", paramA);
        model.addAttribute("paramB", paramB);
        model.addAttribute("paramC", paramC);
        model.addAttribute("paramD", paramD);
        model.addAttribute("paramE", paramE);
        model.addAttribute("paramF", paramF);


        return "my_req_params";
    }


    //метод на java8  и на библиотеке потоков для поиска нужного значения в массиве
    public static List<UnitsTable> findById(List<UnitsTable> list, int id) {
        List<UnitsTable> result = list.stream()
                .filter(item -> (item.getId() == id))
                .collect(Collectors.toList());

        return result;
    }

    public void myLogger(Object a, Object b, Object c) {
        logger.info("пришел  логгер");
        logger.info("пришел  логгер");
        logger.info("пришел  логгер");
        logger.info("пришел  логгер");
        logger.info(a + "");
        logger.info(b + "");
        logger.info(c + "");
        logger.info("ушел  логгер");
        logger.info("ушел  логгер");
        logger.info("ушел  логгер");
        logger.info("ушел  логгер");
        logger.info("ушел  логгер");
    }

    public static void main(String[] args) {
        List<UnitsTable> list = new ArrayList<>();
        list.add(new UnitsTable("a", "b","c"));
        list.add(new UnitsTable("c", "d","e"));
        list.add(new UnitsTable("f", "g","h"));
        list.add(new UnitsTable("i", "j","k"));
       // list.add(new UnitsTable("l", "m","n"));
        UnitsTable a = new UnitsTable("l", "m","n");
        list.add(a);
        int index = list.indexOf(a);
        list.remove(index);
        for (UnitsTable ut: list)
            System.out.println(ut);
    }
}
