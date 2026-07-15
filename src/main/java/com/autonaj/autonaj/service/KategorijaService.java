package com.autonaj.autonaj.service;

import com.autonaj.autonaj.model.Kategorija;
import com.autonaj.autonaj.storage.DataStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KategorijaService {

    private final DataStore dataStore;

    public KategorijaService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public List<Kategorija> findAll() {
        return dataStore.getKategorije();
    }

    public Optional<Kategorija> findById(Long id) {
        return dataStore.getKategorije().stream()
                .filter(kategorija -> kategorija.getId().equals(id))
                .findFirst();
    }

    public Kategorija create(Kategorija kategorija) {
        kategorija.setId(sledeciId());
        dataStore.getKategorije().add(kategorija);
        return kategorija;
    }

    public Optional<Kategorija> update(Long id, Kategorija izmenjenaKategorija) {
        return findById(id).map(kategorija -> {
            kategorija.setNaziv(izmenjenaKategorija.getNaziv());
            kategorija.setOpis(izmenjenaKategorija.getOpis());
            return kategorija;
        });
    }

    public boolean delete(Long id) {
        return dataStore.getKategorije().removeIf(kategorija -> kategorija.getId().equals(id));
    }

    private Long sledeciId() {
        return dataStore.getKategorije().stream()
                .mapToLong(Kategorija::getId)
                .max()
                .orElse(0L) + 1;
    }
}
