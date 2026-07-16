package com.autonaj.autonaj.controller;

import com.autonaj.autonaj.model.Klijent;
import com.autonaj.autonaj.service.KlijentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/klijenti")
public class KlijentController {

    private final KlijentService klijentService;

    public KlijentController(KlijentService klijentService) {
        this.klijentService = klijentService;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("klijenti", klijentService.findAll());
        return "klijent/lista";
    }

    @GetMapping("/novo")
    public String formaZaDodavanje(Model model) {
        model.addAttribute("klijent", new Klijent());
        return "klijent/forma";
    }

    @GetMapping("/izmeni/{id}")
    public String formaZaIzmenu(@PathVariable Long id, Model model) {
        model.addAttribute("klijent", klijentService.findById(id).orElseThrow());
        return "klijent/forma";
    }

    @PostMapping("/sacuvaj")
    public String sacuvaj(@ModelAttribute Klijent klijent) {
        if (klijent.getId() == null) {
            klijentService.create(klijent);
        } else {
            klijentService.update(klijent.getId(), klijent);
        }
        return "redirect:/klijenti";
    }

    @GetMapping("/obrisi/{id}")
    public String obrisi(@PathVariable Long id) {
        klijentService.delete(id);
        return "redirect:/klijenti";
    }
}
