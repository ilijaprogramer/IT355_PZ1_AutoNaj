package com.autonaj.autonaj.service;

import com.autonaj.autonaj.model.Marka;
import com.autonaj.autonaj.storage.DataStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarkaService {

    private final DataStore dataStore;

    public MarkaService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public List<Marka> findAll() {
        return dataStore.getMarke();
    }

    public Optional<Marka> findById(Long id) {
        return dataStore.getMarke().stream()
                .filter(marka -> marka.getId().equals(id))
                .findFirst();
    }

    public Marka create(Marka marka) {
        marka.setId(sledeciId());
        dataStore.getMarke().add(marka);
        return marka;
    }

    public Optional<Marka> update(Long id, Marka izmenjenaMarka) {
        return findById(id).map(marka -> {
            marka.setNaziv(izmenjenaMarka.getNaziv());
            return marka;
        });
    }

    public boolean delete(Long id) {
        return dataStore.getMarke().removeIf(marka -> marka.getId().equals(id));
    }

    private Long sledeciId() {
        return dataStore.getMarke().stream()
                .mapToLong(Marka::getId)
                .max()
                .orElse(0L) + 1;
    }
}
