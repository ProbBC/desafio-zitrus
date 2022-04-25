package com.zitrus.desafiozitrus.produto.DTO;

import com.zitrus.desafiozitrus.produto.enums.ProdutoTipos;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProdutoPorTipoDTO {
    private Long id;
    private String descricao;
    private ProdutoTipos tipoProduto;
    private Double valorFornecedor;
    private Integer qtdDisponivel;
    private Integer qtdSaida;
}
