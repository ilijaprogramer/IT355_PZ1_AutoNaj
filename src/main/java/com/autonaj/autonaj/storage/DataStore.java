package com.autonaj.autonaj.storage;

import com.autonaj.autonaj.model.Kategorija;
import com.autonaj.autonaj.model.Klijent;
import com.autonaj.autonaj.model.Marka;
import com.autonaj.autonaj.model.Rezervacija;
import com.autonaj.autonaj.model.Vozilo;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataStore {

    private final List<Marka> marke = new ArrayList<>();
    private final List<Kategorija> kategorije = new ArrayList<>();
    private final List<Vozilo> vozila = new ArrayList<>();
    private final List<Klijent> klijenti = new ArrayList<>();
    private final List<Rezervacija> rezervacije = new ArrayList<>();

    @PostConstruct
    public void ucitajPocetnePodatke() {
        Marka vw = new Marka(1L, "Volkswagen");
        Marka toyota = new Marka(2L, "Toyota");
        Marka bmw = new Marka(3L, "BMW");
        marke.add(vw);
        marke.add(toyota);
        marke.add(bmw);

        Kategorija kompakt = new Kategorija(1L, "Kompakt", "Mala i ekonomicna vozila za grad");
        Kategorija suv = new Kategorija(2L, "SUV", "Vozila povisenog klirensa za sve terene");
        Kategorija limuzina = new Kategorija(3L, "Limuzina", "Prostrana vozila za duza putovanja");
        kategorije.add(kompakt);
        kategorije.add(suv);
        kategorije.add(limuzina);

        vozila.add(new Vozilo(1L, "Golf 8", "NS-123-AB", 35.0, true, vw, kompakt));
        vozila.add(new Vozilo(2L, "RAV4", "NS-456-CD", 55.0, true, toyota, suv));
        vozila.add(new Vozilo(3L, "Serija 5", "NS-789-EF", 80.0, false, bmw, limuzina));

        klijenti.add(new Klijent(1L, "Petar", "Petrovic", "petar.petrovic@example.com", "0601234567"));
        klijenti.add(new Klijent(2L, "Ana", "Anic", "ana.anic@example.com", "0657654321"));
    }

    public List<Marka> getMarke() {
        return marke;
    }

    public List<Kategorija> getKategorije() {
        return kategorije;
    }

    public List<Vozilo> getVozila() {
        return vozila;
    }

    public List<Klijent> getKlijenti() {
        return klijenti;
    }

    public List<Rezervacija> getRezervacije() {
        return rezervacije;
    }
}
