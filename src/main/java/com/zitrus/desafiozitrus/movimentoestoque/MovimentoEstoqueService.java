package com.zitrus.desafiozitrus.movimentoestoque;

import com.zitrus.desafiozitrus.movimentoestoque.enums.MovimentoEstoqueTipos;
import com.zitrus.desafiozitrus.movimentoestoque.exception.EstoqueNegativoException;
import com.zitrus.desafiozitrus.movimentoestoque.exception.MovimentoEstoqueEditException;
import com.zitrus.desafiozitrus.produto.Produto;
import com.zitrus.desafiozitrus.produto.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class MovimentoEstoqueService {
    @Autowired
    private MovimentoEstoqueRepository movimentoEstoqueRepository;

    @Autowired
    private ProdutoService produtoService;

    public MovimentoEstoque findById(Long id) {
        return movimentoEstoqueRepository.findById(id).orElse(null);
    }

    public List<MovimentoEstoque> findAll() {
        return movimentoEstoqueRepository.findAll();
    }

    private void movimentarEstoque(MovimentoEstoque movimentoEstoque, Produto produto) {
        Set<MovimentoEstoque> movimentos = produto.getMovimentosEstoque();
        movimentos.add(movimentoEstoque);
        produto.setMovimentosEstoque(movimentos);

        if (movimentoEstoque.getTipoMovimentacao() == MovimentoEstoqueTipos.ENTRADA) {
            produto.setQtdEstoque(produto.getQtdEstoque() + movimentoEstoque.getQtdMovimentada());
        } else {
            produto.setQtdEstoque(produto.getQtdEstoque() - movimentoEstoque.getQtdMovimentada());
        }

        if (produto.getQtdEstoque() < 0) {
            produto.setQtdEstoque(0);
            throw new EstoqueNegativoException();
        }
    }

    @Transactional
    public MovimentoEstoque save(MovimentoEstoque movimentoEstoque) {
        if (movimentoEstoque.getId() == null || !movimentoEstoqueRepository.existsById(movimentoEstoque.getId())) {
            Produto produto = produtoService.findById(movimentoEstoque.getProduto().getId());
            if (produto != null) {
                movimentoEstoque.setProduto(produto);
                this.movimentarEstoque(movimentoEstoque, produto);
                return movimentoEstoqueRepository.save(movimentoEstoque);
            }
            throw new EntityNotFoundException("Produto não existe");
        }
        throw new MovimentoEstoqueEditException();
    }

    @Transactional
    public Boolean deleteById(Long id) {
        MovimentoEstoque movimentoEstoque = movimentoEstoqueRepository.findById(id).orElse(null);
        if (movimentoEstoque == null) {
            return false;
        }
        Produto produto = movimentoEstoque.getProduto();

        produto.getMovimentosEstoque().remove(movimentoEstoque);
        if (movimentoEstoque.getTipoMovimentacao() == MovimentoEstoqueTipos.ENTRADA) {
            produto.setQtdEstoque(produto.getQtdEstoque() - movimentoEstoque.getQtdMovimentada());
        } else {
            produto.setQtdEstoque(produto.getQtdEstoque() + movimentoEstoque.getQtdMovimentada());
        }

        if (produto.getQtdEstoque() < 0) {
            produto.setQtdEstoque(0);
            throw new EstoqueNegativoException();
        }

        produtoService.save(produto);
        movimentoEstoqueRepository.delete(movimentoEstoque);
        return true;
    }
}
