package com.zitrus.desafiozitrus.movimentoestoque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentoEstoqueRepository extends JpaRepository<MovimentoEstoque, Long> {
}
