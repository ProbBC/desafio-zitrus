package com.zitrus.desafiozitrus.movimentoestoque.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MovimentoEstoqueTipos {
    ENTRADA("ENTRADA"),
    SAIDA("SAIDA");

    private final String code;

    private MovimentoEstoqueTipos(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @JsonValue
    public String toLower() {
        return this.toString().toLowerCase();
    }
}
