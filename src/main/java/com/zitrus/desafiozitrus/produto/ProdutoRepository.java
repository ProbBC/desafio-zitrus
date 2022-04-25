package com.zitrus.desafiozitrus.produto;

import com.zitrus.desafiozitrus.produto.enums.ProdutoTipos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByTipoProduto(ProdutoTipos tipoProduto);
}
