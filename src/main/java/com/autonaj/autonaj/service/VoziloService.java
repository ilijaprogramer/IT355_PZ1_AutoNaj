package com.autonaj.autonaj.service;

import com.autonaj.autonaj.model.Vozilo;
import com.autonaj.autonaj.storage.DataStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoziloService {

    private final DataStore dataStore;

    public VoziloService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public List<Vozilo> findAll() {
        return dataStore.getVozila();
    }

    public Optional<Vozilo> findById(Long id) {
        return dataStore.getVozila().stream()
                .filter(vozilo -> vozilo.getId().equals(id))
                .findFirst();
    }

    public Vozilo create(Vozilo vozilo) {
        vozilo.setId(sledeciId());
        dataStore.getVozila().add(vozilo);
        return vozilo;
    }

    public Optional<Vozilo> update(Long id, Vozilo izmenjenoVozilo) {
        return findById(id).map(vozilo -> {
            vozilo.setModel(izmenjenoVozilo.getModel());
            vozilo.setRegistracija(izmenjenoVozilo.getRegistracija());
            vozilo.setCenaPoDanu(izmenjenoVozilo.getCenaPoDanu());
            vozilo.setDostupno(izmenjenoVozilo.isDostupno());
            vozilo.setMarka(izmenjenoVozilo.getMarka());
            vozilo.setKategorija(izmenjenoVozilo.getKategorija());
            return vozilo;
        });
    }

    public boolean delete(Long id) {
        return dataStore.getVozila().removeIf(vozilo -> vozilo.getId().equals(id));
    }

    private Long sledeciId() {
        return dataStore.getVozila().stream()
                .mapToLong(Vozilo::getId)
                .max()
                .orElse(0L) + 1;
    }
}
