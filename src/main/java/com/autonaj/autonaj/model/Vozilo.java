package com.autonaj.autonaj.model;

public class Vozilo {

    private Long id;
    private String model;
    private String registracija;
    private double cenaPoDanu;
    private boolean dostupno;
    private Marka marka;
    private Kategorija kategorija;

    public Vozilo() {
    }

    public Vozilo(Long id, String model, String registracija, double cenaPoDanu, boolean dostupno,
                  Marka marka, Kategorija kategorija) {
        this.id = id;
        this.model = model;
        this.registracija = registracija;
        this.cenaPoDanu = cenaPoDanu;
        this.dostupno = dostupno;
        this.marka = marka;
        this.kategorija = kategorija;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistracija() {
        return registracija;
    }

    public void setRegistracija(String registracija) {
        this.registracija = registracija;
    }

    public double getCenaPoDanu() {
        return cenaPoDanu;
    }

    public void setCenaPoDanu(double cenaPoDanu) {
        this.cenaPoDanu = cenaPoDanu;
    }

    public boolean isDostupno() {
        return dostupno;
    }

    public void setDostupno(boolean dostupno) {
        this.dostupno = dostupno;
    }

    public Marka getMarka() {
        return marka;
    }

    public void setMarka(Marka marka) {
        this.marka = marka;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }
}
