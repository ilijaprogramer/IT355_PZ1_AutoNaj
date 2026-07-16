package com.autonaj.autonaj.controller;

import com.autonaj.autonaj.model.Kategorija;
import com.autonaj.autonaj.service.KategorijaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kategorije")
public class KategorijaController {

    private final KategorijaService kategorijaService;

    public KategorijaController(KategorijaService kategorijaService) {
        this.kategorijaService = kategorijaService;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("kategorije", kategorijaService.findAll());
        return "kategorija/lista";
    }

    @GetMapping("/novo")
    public String formaZaDodavanje(Model model) {
        model.addAttribute("kategorija", new Kategorija());
        return "kategorija/forma";
    }

    @GetMapping("/izmeni/{id}")
    public String formaZaIzmenu(@PathVariable Long id, Model model) {
        model.addAttribute("kategorija", kategorijaService.findById(id).orElseThrow());
        return "kategorija/forma";
    }

    @PostMapping("/sacuvaj")
    public String sacuvaj(@ModelAttribute Kategorija kategorija) {
        if (kategorija.getId() == null) {
            kategorijaService.create(kategorija);
        } else {
            kategorijaService.update(kategorija.getId(), kategorija);
        }
        return "redirect:/kategorije";
    }

    @GetMapping("/obrisi/{id}")
    public String obrisi(@PathVariable Long id) {
        kategorijaService.delete(id);
        return "redirect:/kategorije";
    }
}
