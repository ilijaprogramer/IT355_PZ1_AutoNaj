package com.autonaj.autonaj.model;

public class Klijent {

    private Long id;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;

    public Klijent() {
    }

    public Klijent(Long id, String ime, String prezime, String email, String telefon) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
