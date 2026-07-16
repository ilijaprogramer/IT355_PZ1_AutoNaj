package com.autonaj.autonaj.controller;

import com.autonaj.autonaj.model.Vozilo;
import com.autonaj.autonaj.service.KategorijaService;
import com.autonaj.autonaj.service.MarkaService;
import com.autonaj.autonaj.service.VoziloService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/vozila")
public class VoziloController {

    private final VoziloService voziloService;
    private final MarkaService markaService;
    private final KategorijaService kategorijaService;

    public VoziloController(VoziloService voziloService, MarkaService markaService,
                             KategorijaService kategorijaService) {
        this.voziloService = voziloService;
        this.markaService = markaService;
        this.kategorijaService = kategorijaService;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("vozila", voziloService.findAll());
        return "vozilo/lista";
    }

    @GetMapping("/novo")
    public String formaZaDodavanje(Model model) {
        model.addAttribute("vozilo", new Vozilo());
        dodajListeZaDropdown(model);
        return "vozilo/forma";
    }

    @GetMapping("/izmeni/{id}")
    public String formaZaIzmenu(@PathVariable Long id, Model model) {
        model.addAttribute("vozilo", voziloService.findById(id).orElseThrow());
        dodajListeZaDropdown(model);
        return "vozilo/forma";
    }

    @PostMapping("/sacuvaj")
    public String sacuvaj(@ModelAttribute Vozilo vozilo,
                           @RequestParam Long markaId,
                           @RequestParam Long kategorijaId) {
        vozilo.setMarka(markaService.findById(markaId).orElseThrow());
        vozilo.setKategorija(kategorijaService.findById(kategorijaId).orElseThrow());
        if (vozilo.getId() == null) {
            voziloService.create(vozilo);
        } else {
            voziloService.update(vozilo.getId(), vozilo);
        }
        return "redirect:/vozila";
    }

    @GetMapping("/obrisi/{id}")
    public String obrisi(@PathVariable Long id) {
        voziloService.delete(id);
        return "redirect:/vozila";
    }

    private void dodajListeZaDropdown(Model model) {
        model.addAttribute("marke", markaService.findAll());
        model.addAttribute("kategorije", kategorijaService.findAll());
    }
}
