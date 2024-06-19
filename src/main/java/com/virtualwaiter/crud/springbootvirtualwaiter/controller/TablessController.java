package com.virtualwaiter.crud.springbootvirtualwaiter.controller;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.virtualwaiter.crud.springbootvirtualwaiter.models.Tables;
import com.virtualwaiter.crud.springbootvirtualwaiter.models.TablesDto;
import com.virtualwaiter.crud.springbootvirtualwaiter.services.TablessRepo;

import jakarta.validation.Valid;

@Controller
@RequestMapping("tabless")

public class TablessController {

    @Autowired
    private TablessRepo repo;

    @GetMapping({"","/"})
    public String toList(Model model) {

        List<Tables> tabless = repo.findAll();
        model.addAttribute("tabless", tabless);
        return "tabless/index";
    }

    @GetMapping("/create")
    public String createPage(Model model, @RequestParam(value = "success", required = false) String success) {
        TablesDto tablesDto = new TablesDto();
        model.addAttribute(	"tablesDto", tablesDto);
        if (success != null) {
            model.addAttribute("success", true);
        }
        return "tabless/create";
    }

    @PostMapping("/create")
    public String createTables(
            @Valid @ModelAttribute TablesDto tablesDto,
            BindingResult result) {
        if(result.hasErrors()) {
            return "tabless/create";
        }

        Tables tables = new Tables();
        tables.setNumber(tablesDto.getNumber());
        tables.setCapacity(tablesDto.getCapacity());
        tables.setStatus(tablesDto.getStatus());

        repo.save(tables);
        return "redirect:/tabless/create?success=true";
    }
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable("id") int id, Model model) {
        Optional<Tables> tablesOpt = repo.findById(id);
        if (tablesOpt.isPresent()) {
            Tables tables = tablesOpt.get();
            TablesDto tablesDto = new TablesDto();
            tablesDto.setNumber(tables.getNumber());
            tablesDto.setCapacity(tables.getCapacity());
            tablesDto.setStatus(tables.getStatus());
            model.addAttribute("tablesDto", tablesDto);
            model.addAttribute("tablesId", id);
            return "tabless/edit";
        } else {
            throw new NoSuchElementException("tables not found");
        }

    }
    @PostMapping("/edit/{id}")
    public String editTables(@PathVariable("id") int id, @Valid @ModelAttribute TablesDto tablesDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tablesId", id);
            return "tabless/edit";
        }

        Optional<Tables> tablesOpt = repo.findById(id);
        if (tablesOpt.isPresent()) {
            Tables tables = tablesOpt.get();
            tables.setNumber(tablesDto.getNumber());
            tables.setCapacity(tablesDto.getCapacity());
            tables.setStatus(tablesDto.getStatus());
            repo.save(tables);
        }else {
            throw new NoSuchElementException("Tables not found");
        }
        return "redirect:/tabless";
    }

    @GetMapping("/delete/{id}")
    public String deleteTables(@PathVariable("id") int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new NoSuchElementException("Tables not found");
        }
        return "redirect:/tabless";
    }
}