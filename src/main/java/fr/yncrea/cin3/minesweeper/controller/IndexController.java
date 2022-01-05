package fr.yncrea.cin3.minesweeper.controller;

import fr.yncrea.cin3.minesweeper.domain.Minefield;
import fr.yncrea.cin3.minesweeper.form.IndexForm;
import fr.yncrea.cin3.minesweeper.repository.IndexRepository;
import fr.yncrea.cin3.minesweeper.service.MinesweeperEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
public class IndexController {
    @Autowired
    private IndexRepository indexs;
    @GetMapping({"", "/"})
    public String index(Model model) {
        model.addAttribute("indexs", indexs.findAll());
        return "index";
    }
    //afficher le formulaire d’ajout
    @GetMapping({"/minesweeper/create", "/"})
    public String create(@PathVariable(required = false) UUID id, Model model/*, MinesweeperEngineService service*/) {
        var form = new IndexForm();
        model.addAttribute("form", form); //Création d’un objet vide comme formulaire

        if (id != null) {
            Minefield m = indexs.findById(id).orElseThrow(() -> new RuntimeException("Game not found"));
            form.setId(m.getId());
            form.setCount(m.getCount());
            form.setHeight(m.getHeight());
            form.setWidth(m.getWidth());
           // service.create(m.getHeight(),m.getWidth(),m.getCount());
        }
        return "create";
    }
    //traiter le formulaire (ajout en base), puis redirige vers la liste
    @PostMapping({"/minesweeper/create"})
    public String createGame(@Valid @ModelAttribute("form") IndexForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("form", form);
            return "create";
        }
        Minefield m = new Minefield();
        if (form.getId() != null) {
            m = indexs.findById(form.getId()).orElseThrow(() -> new RuntimeException("Game not found"));
        }
        m.setCount(form.getCount());
        m.setHeight(form.getHeight());
        m.setWidth(form.getWidth());
        indexs.save(m);
        return "redirect:/";
    }

    @PostMapping("/minesweeper/delete/{id}")
    public String delete(@PathVariable UUID id) {
        indexs.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/minesweeper/play/{id}")
    public String play(@PathVariable UUID id, Model model/*,MinesweeperEngineService service*/) {
        var index = indexs.findById(id).orElseThrow(() -> new RuntimeException("Meeting not found"));
        model.addAttribute("index", index);
        System.out.println(index.getCount());
        System.out.println(index.getHeight());
        System.out.println(index.getWidth());
        System.out.println(index.getId());
       // service.play(index,index.getWidth(),index.getHeight());

        return "play";
    }



}
