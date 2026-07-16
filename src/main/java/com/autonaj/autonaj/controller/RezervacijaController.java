package com.autonaj.autonaj.controller;

import com.autonaj.autonaj.model.Rezervacija;
import com.autonaj.autonaj.service.KlijentService;
import com.autonaj.autonaj.service.RezervacijaService;
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
@RequestMapping("/rezervacije")
public class RezervacijaController {

    private final RezervacijaService rezervacijaService;
    private final VoziloService voziloService;
    private final KlijentService klijentService;

    public RezervacijaController(RezervacijaService rezervacijaService, VoziloService voziloService,
                                  KlijentService klijentService) {
        this.rezervacijaService = rezervacijaService;
        this.voziloService = voziloService;
        this.klijentService = klijentService;
    }

    @GetMapping
    public String lista(Model model) {
        model.addAttribute("rezervacije", rezervacijaService.findAll());
        return "rezervacija/lista";
    }

    @GetMapping("/novo")
    public String formaZaDodavanje(Model model) {
        model.addAttribute("rezervacija", new Rezervacija());
        dodajListeZaDropdown(model);
        return "rezervacija/forma";
    }

    @GetMapping("/izmeni/{id}")
    public String formaZaIzmenu(@PathVariable Long id, Model model) {
        model.addAttribute("rezervacija", rezervacijaService.findById(id).orElseThrow());
        dodajListeZaDropdown(model);
        return "rezervacija/forma";
    }

    @PostMapping("/sacuvaj")
    public String sacuvaj(@ModelAttribute Rezervacija rezervacija,
                           @RequestParam Long voziloId,
                           @RequestParam Long klijentId) {
        rezervacija.setVozilo(voziloService.findById(voziloId).orElseThrow());
        rezervacija.setKlijent(klijentService.findById(klijentId).orElseThrow());
        if (rezervacija.getId() == null) {
            rezervacijaService.create(rezervacija);
        } else {
            rezervacijaService.update(rezervacija.getId(), rezervacija);
        }
        return "redirect:/rezervacije";
    }

    @GetMapping("/obrisi/{id}")
    public String obrisi(@PathVariable Long id) {
        rezervacijaService.delete(id);
        return "redirect:/rezervacije";
    }

    private void dodajListeZaDropdown(Model model) {
        model.addAttribute("vozila", voziloService.findAll());
        model.addAttribute("klijenti", klijentService.findAll());
    }
}
