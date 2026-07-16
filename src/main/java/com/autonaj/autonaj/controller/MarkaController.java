package com.autonaj.autonaj.controller;

import com.autonaj.autonaj.model.Marka;
import com.autonaj.autonaj.service.MarkaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/marke")
public class MarkaController {

    private final MarkaService markaService;

    public MarkaController(MarkaService markaService) {
        this.markaService = markaService;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("marke", markaService.findAll());
        return "marka/lista";
    }

    @GetMapping("/novo")
    public String formaZaDodavanje(Model model) {
        model.addAttribute("marka", new Marka());
        return "marka/forma";
    }

    @GetMapping("/izmeni/{id}")
    public String formaZaIzmenu(@PathVariable Long id, Model model) {
        model.addAttribute("marka", markaService.findById(id).orElseThrow());
        return "marka/forma";
    }

    @PostMapping("/sacuvaj")
    public String sacuvaj(@ModelAttribute Marka marka) {
        if (marka.getId() == null) {
            markaService.create(marka);
        } else {
            markaService.update(marka.getId(), marka);
        }
        return "redirect:/marke";
    }

    @GetMapping("/obrisi/{id}")
    public String obrisi(@PathVariable Long id) {
        markaService.delete(id);
        return "redirect:/marke";
    }
}
