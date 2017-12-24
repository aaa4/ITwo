package com.example.demo.docs.controller;


import com.example.demo.docs.model.Boards;
import com.example.demo.docs.model.BoardsAndUnits;
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

import java.util.List;

@Controller
public class FormController {

    private static final Logger logger = LoggerFactory.getLogger(FormController.class);

    @Autowired
    BoardsRepository boardsRepository;

    @Autowired
    UnitsTableRepository unitsTableRepository;

    @GetMapping("formOne")
    public String formOne(Model model) {
        model.addAttribute("board", new Boards());
        return "formOne";
    }

    @PostMapping("formOne")
    public String postFormOne(@ModelAttribute Boards boards) {
        return "boardsTable";
    }

    //таблица выдающая список бортов  //todo: после добавления этой формы в список бортов начались проблемы см список проблем внизу
    @GetMapping("boardsTable")
    public String boardsTable(Model model) {
        model.addAttribute("boards", boardsRepository.findAll());
        return "boardsTable";
    }


    //выдает список полей unitsTable для конекретного борта
    @GetMapping("unitTable")
    public String unitsTabl(@RequestParam(value = "id", required = false, defaultValue = "300") int id, Model model) {

        model.addAttribute("tables", unitsTableRepository.findAllByBoardId(id));
        return "unitTable";
    }

    //это полная таблица записей типа units_table из бд
    @GetMapping("unitsTable")
    public String getFullUnitsTable(Model model) {
        model.addAttribute("unit2", "this is unit 2");
        model.addAttribute("units", unitsTableRepository.findAll());
        return "unitsTable";
    }



    //для передачи нескольких параметров через форму
    // Можно создать кастомный класс полями которого
    //будут нужные мне классы
    @GetMapping("/formTwo")
    public String getFormTwo(Model model) {
        model.addAttribute("combo", new BoardsAndUnits());
        return "formTwo";
    }

    @PostMapping("/formTwo")
    public String postFormTwo(@ModelAttribute BoardsAndUnits boardsAndUnits) {
        if (boardsAndUnits != null) {
            Boards board = boardsAndUnits.getBoards();
            UnitsTable table = boardsAndUnits.getUnitsTable();
            logger.info(table.toString());
            if (table.equals(null))
                logger.info("NULL !!!!");
            logger.info("NULL !!!!");
            logger.info("NULL !!!!");
            logger.info("NULL !!!!");
            board.add(table);
            boardsRepository.save(board);

        }
        return "redirect:/boardsTable";
    }

    //todo: переработать метод выше с блоками и бортами.
    //todo: мне кажется он не слишком хорошо вписывается в создаваемую систему

    //добавляет борт без значения Id т.е. новый  борт гет
    @GetMapping("/addNewBoard")
    public String getWithoutIdBoardForm(Model model){
        model.addAttribute("board", new Boards());
        return "addNewBoard";
    }

    // пост формы
    @PostMapping("/addNewBoard")
    public String postWithoutIdBoardForm(@ModelAttribute Boards board){
        boardsRepository.save(board);
        System.out.println("done");
        return "redirect:/boardsTable";
    }

    //гет метод отображающий форму для внесения в базу данных отдельных бортов (без сопутствующих блоков) по id
    @GetMapping("/addBoards")
    public String getByIdBoardForm(@RequestParam(name = "id", required = false) int theId, Model model) {
        Boards theBoard = boardsRepository.findById(theId);
        model.addAttribute("board", theBoard);
        return "board-form";
    }

    @PostMapping("/addBoards")
    public String postByIdBoardForm(@ModelAttribute Boards boards){
        boardsRepository.save(boards);
        return "redirect:boardsTable";
    }

    @GetMapping("/deleteBoard")
    public String getDeleteBoardForm(@RequestParam(name = "boardId") int theId, Model model) {
        Boards theBoard = boardsRepository.findById(theId);
        model.addAttribute("board",theBoard);
        model.addAttribute("NumberAndSerial",theBoard.getBoardNumber()+"(" +theBoard.getSerialNumber()+")");
        return "deleteTable";
    }

    @PostMapping("/deleteBoard")
    public String postDeleteBoardFor(@ModelAttribute Boards board){
        boardsRepository.delete(board.getId());   //boardRepository.delete(board) криво работает
        return "redirect:/boardsTable";
    }




    /***
     * UPD 1 решено? Список проблем: 1. странно работает уже настроенный маппинг. Версий у меня две или криво настроен кеш thymeleaf
     * или Spring или есть проблемы с автоматическим маппингом. UPD в таких случаях проверять: data-th-action="@{}"
     * 2. нули в формах
     */


}
