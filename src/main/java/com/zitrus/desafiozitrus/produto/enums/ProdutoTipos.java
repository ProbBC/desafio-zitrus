package com.zitrus.desafiozitrus.produto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProdutoTipos {
    ELETRONICO("ELETRONICO"),
    ELETRODOMESTICO("ELETRODOMESTICO"),
    MOVEL("MOVEL");

    private final String code;

    private ProdutoTipos(String code) {
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


