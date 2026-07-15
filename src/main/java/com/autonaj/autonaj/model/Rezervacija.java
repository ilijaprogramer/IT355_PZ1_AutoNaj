package com.autonaj.autonaj.model;

import java.time.LocalDate;

public class Rezervacija {

    private Long id;
    private Vozilo vozilo;
    private Klijent klijent;
    private LocalDate datumOd;
    private LocalDate datumDo;
    private double ukupnaCena;

    public Rezervacija() {
    }

    public Rezervacija(Long id, Vozilo vozilo, Klijent klijent, LocalDate datumOd, LocalDate datumDo,
                        double ukupnaCena) {
        this.id = id;
        this.vozilo = vozilo;
        this.klijent = klijent;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.ukupnaCena = ukupnaCena;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vozilo getVozilo() {
        return vozilo;
    }

    public void setVozilo(Vozilo vozilo) {
        this.vozilo = vozilo;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public LocalDate getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(LocalDate datumOd) {
        this.datumOd = datumOd;
    }

    public LocalDate getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(LocalDate datumDo) {
        this.datumDo = datumDo;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }
}
