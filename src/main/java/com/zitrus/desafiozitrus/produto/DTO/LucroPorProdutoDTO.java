package com.zitrus.desafiozitrus.produto.DTO;

import com.zitrus.desafiozitrus.produto.enums.ProdutoTipos;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LucroPorProdutoDTO {
    private Long id;
    private String descricao;
    private ProdutoTipos tipoProduto;
    private Integer qtdSaida;
    private Double lucro;
}
