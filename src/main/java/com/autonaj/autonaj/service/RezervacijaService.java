package com.autonaj.autonaj.service;

import com.autonaj.autonaj.model.Rezervacija;
import com.autonaj.autonaj.storage.DataStore;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class RezervacijaService {

    private final DataStore dataStore;

    public RezervacijaService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public List<Rezervacija> findAll() {
        return dataStore.getRezervacije();
    }

    public Optional<Rezervacija> findById(Long id) {
        return dataStore.getRezervacije().stream()
                .filter(rezervacija -> rezervacija.getId().equals(id))
                .findFirst();
    }

    public Rezervacija create(Rezervacija rezervacija) {
        rezervacija.setId(sledeciId());
        rezervacija.setUkupnaCena(izracunajUkupnuCenu(rezervacija));
        dataStore.getRezervacije().add(rezervacija);
        return rezervacija;
    }

    public Optional<Rezervacija> update(Long id, Rezervacija izmenjenaRezervacija) {
        return findById(id).map(rezervacija -> {
            rezervacija.setVozilo(izmenjenaRezervacija.getVozilo());
            rezervacija.setKlijent(izmenjenaRezervacija.getKlijent());
            rezervacija.setDatumOd(izmenjenaRezervacija.getDatumOd());
            rezervacija.setDatumDo(izmenjenaRezervacija.getDatumDo());
            rezervacija.setUkupnaCena(izracunajUkupnuCenu(rezervacija));
            return rezervacija;
        });
    }

    public boolean delete(Long id) {
        return dataStore.getRezervacije().removeIf(rezervacija -> rezervacija.getId().equals(id));
    }

    private double izracunajUkupnuCenu(Rezervacija rezervacija) {
        long brojDana = ChronoUnit.DAYS.between(rezervacija.getDatumOd(), rezervacija.getDatumDo());
        return rezervacija.getVozilo().getCenaPoDanu() * brojDana;
    }

    private Long sledeciId() {
        return dataStore.getRezervacije().stream()
                .mapToLong(Rezervacija::getId)
                .max()
                .orElse(0L) + 1;
    }
}
