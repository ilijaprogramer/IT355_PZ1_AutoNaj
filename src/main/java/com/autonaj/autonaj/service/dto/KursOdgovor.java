package com.autonaj.autonaj.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record KursOdgovor(String code, String date, double exchange_middle) {
}
