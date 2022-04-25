package com.zitrus.desafiozitrus.produto.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zitrus.desafiozitrus.produto.enums.ProdutoTipos;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class ProdutoDTO {
    private Long id;

    @NotEmpty
    private String descricao;

    @NotNull
    private ProdutoTipos tipoProduto;

    @NotNull
    @Min(0)
    private Double valorFornecedor;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Min(0)
    private Integer qtdEstoque = 0;

}
