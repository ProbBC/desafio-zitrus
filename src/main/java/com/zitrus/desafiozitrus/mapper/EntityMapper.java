package com.zitrus.desafiozitrus.mapper;

import com.zitrus.desafiozitrus.movimentoestoque.MovimentoEstoque;
import com.zitrus.desafiozitrus.movimentoestoque.DTO.MovimentoEstoqueDTO;
import com.zitrus.desafiozitrus.produto.DTO.LucroPorProdutoDTO;
import com.zitrus.desafiozitrus.produto.Produto;
import com.zitrus.desafiozitrus.produto.DTO.ProdutoDTO;
import com.zitrus.desafiozitrus.produto.DTO.ProdutoPorTipoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    @Mapping(target = "qtdDisponivel", source = "qtdEstoque")
    ProdutoPorTipoDTO produtoToProdutoPorTipoDTO(Produto produto);
    LucroPorProdutoDTO produtoToLucroProdutoDTO(Produto produto);

    Produto produtoDTOToProduto(ProdutoDTO produtoDTO);
    ProdutoDTO produtoToProdutoDTO(Produto produto);

    MovimentoEstoque movimentoEstoqueDTOToMovimentoEstoque(MovimentoEstoqueDTO movimentoEstoqueDTO);
    MovimentoEstoqueDTO movimentoEstoqueToMovimentoEstoqueDTO(MovimentoEstoque movimentoEstoque);
}
