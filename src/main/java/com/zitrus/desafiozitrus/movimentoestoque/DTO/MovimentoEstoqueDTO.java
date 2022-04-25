package com.zitrus.desafiozitrus.movimentoestoque.DTO;

import com.zitrus.desafiozitrus.movimentoestoque.enums.MovimentoEstoqueTipos;
import com.zitrus.desafiozitrus.produto.DTO.ProdutoDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MovimentoEstoqueDTO {
    private Long id;

    @NotNull
    private ProdutoDTO produto;

    @NotNull
    private MovimentoEstoqueTipos tipoMovimentacao;

    @NotNull
    @Min(0)
    private Double valorVenda;

    @PastOrPresent
    private LocalDateTime dataVenda;

    @NotNull
    @Min(1)
    private Integer qtdMovimentada;

}
