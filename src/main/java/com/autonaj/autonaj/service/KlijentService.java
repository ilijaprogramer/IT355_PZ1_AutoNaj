package com.autonaj.autonaj.service;

import com.autonaj.autonaj.model.Klijent;
import com.autonaj.autonaj.storage.DataStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KlijentService {

    private final DataStore dataStore;

    public KlijentService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public List<Klijent> findAll() {
        return dataStore.getKlijenti();
    }

    public Optional<Klijent> findById(Long id) {
        return dataStore.getKlijenti().stream()
                .filter(klijent -> klijent.getId().equals(id))
                .findFirst();
    }

    public Klijent create(Klijent klijent) {
        klijent.setId(sledeciId());
        dataStore.getKlijenti().add(klijent);
        return klijent;
    }

    public Optional<Klijent> update(Long id, Klijent izmenjeniKlijent) {
        return findById(id).map(klijent -> {
            klijent.setIme(izmenjeniKlijent.getIme());
            klijent.setPrezime(izmenjeniKlijent.getPrezime());
            klijent.setEmail(izmenjeniKlijent.getEmail());
            klijent.setTelefon(izmenjeniKlijent.getTelefon());
            return klijent;
        });
    }

    public boolean delete(Long id) {
        return dataStore.getKlijenti().removeIf(klijent -> klijent.getId().equals(id));
    }

    private Long sledeciId() {
        return dataStore.getKlijenti().stream()
                .mapToLong(Klijent::getId)
                .max()
                .orElse(0L) + 1;
    }
}
