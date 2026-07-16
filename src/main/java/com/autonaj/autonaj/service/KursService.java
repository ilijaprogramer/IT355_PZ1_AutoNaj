package com.autonaj.autonaj.service;

import com.autonaj.autonaj.service.dto.KursOdgovor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class KursService {

    private static final Logger log = LoggerFactory.getLogger(KursService.class);
    private static final String KURS_API_URL = "https://kurs.resenje.org/api/v1/currencies/eur/rates/today";
    private static final double FALLBACK_KURS = 117.0;

    private final RestClient restClient;
    private volatile double poslednjiPoznatiKurs = FALLBACK_KURS;

    public KursService() {
        this.restClient = RestClient.create();
    }

    public double dobaviKurs() {
        try {
            KursOdgovor odgovor = restClient.get()
                    .uri(KURS_API_URL)
                    .retrieve()
                    .body(KursOdgovor.class);

            if (odgovor == null || odgovor.exchange_middle() <= 0) {
                log.warn("Kurs API je vratio prazan ili nevazeci odgovor, koristim poslednji poznati kurs: {}", poslednjiPoznatiKurs);
                return poslednjiPoznatiKurs;
            }

            poslednjiPoznatiKurs = odgovor.exchange_middle();
            return poslednjiPoznatiKurs;
        } catch (Exception e) {
            log.error("Greska prilikom pozivanja Kurs API-ja, koristim poslednji poznati kurs: {}", poslednjiPoznatiKurs, e);
            return poslednjiPoznatiKurs;
        }
    }

    public double konvertujURSDuEUR(double iznosRSD) {
        return konvertujURSDuEUR(iznosRSD, dobaviKurs());
    }

    public double konvertujURSDuEUR(double iznosRSD, double kurs) {
        if (kurs <= 0) {
            return 0;
        }
        return BigDecimal.valueOf(iznosRSD / kurs)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
