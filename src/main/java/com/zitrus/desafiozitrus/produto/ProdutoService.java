package com.zitrus.desafiozitrus.produto;

import com.zitrus.desafiozitrus.mapper.EntityMapper;
import com.zitrus.desafiozitrus.movimentoestoque.MovimentoEstoque;
import com.zitrus.desafiozitrus.movimentoestoque.enums.MovimentoEstoqueTipos;
import com.zitrus.desafiozitrus.produto.DTO.LucroPorProdutoDTO;
import com.zitrus.desafiozitrus.produto.DTO.ProdutoPorTipoDTO;
import com.zitrus.desafiozitrus.produto.enums.ProdutoTipos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EntityMapper mapper;

    public Produto findById(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    @Transactional
    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Transactional
    public Produto update(Long id, Produto produto) {
        Produto produtoToUpdate = this.findById(id);
        if (produtoToUpdate != null) {
            produtoToUpdate.setDescricao(produto.getDescricao());
            produtoToUpdate.setTipoProduto(produto.getTipoProduto());
            produtoToUpdate.setValorFornecedor(produto.getValorFornecedor());
            Produto updated = this.save(produtoToUpdate);
            return updated;
        }
        return null;
    }

    @Transactional
    public Boolean deleteById(Long id) {
        if (produtoRepository.findById(id).isPresent()) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Integer calculaQtdSaida(Produto produto) {
        Set<MovimentoEstoque> movimentos = produto.getMovimentosEstoque();

        Integer qtdSaida = 0;
        for (MovimentoEstoque movimento : movimentos) {
            if (movimento.getTipoMovimentacao() == MovimentoEstoqueTipos.SAIDA) {
                qtdSaida += movimento.getQtdMovimentada();
            }
        }
        return qtdSaida;
    }

    public Double calculaLucroProduto(Produto produto) {
        Set<MovimentoEstoque> movimentos = produto.getMovimentosEstoque();

        Double lucro = 0.0;
        for (MovimentoEstoque movimento : movimentos) {
            lucro += (movimento.getValorVenda() - produto.getValorFornecedor());
        }
        return lucro;
    }

    public List<ProdutoPorTipoDTO> findByType(ProdutoTipos tipoProduto) {
        List<ProdutoPorTipoDTO> produtosPorTipo = new ArrayList<>();
        List<Produto> produtos = produtoRepository.findByTipoProduto(tipoProduto);

        produtos.forEach(produto -> {
            ProdutoPorTipoDTO produtoPorTipo = mapper.produtoToProdutoPorTipoDTO(produto);
            produtoPorTipo.setQtdSaida(this.calculaQtdSaida(produto));
            produtosPorTipo.add(produtoPorTipo);
        });

        return produtosPorTipo;
    }

    public LucroPorProdutoDTO findLucroPorProduto(Long id) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        if (produto == null) {
            return null;
        }
        LucroPorProdutoDTO lucroProduto = mapper.produtoToLucroProdutoDTO(produto);
        lucroProduto.setQtdSaida(this.calculaQtdSaida(produto));
        lucroProduto.setLucro(this.calculaLucroProduto(produto));
        return lucroProduto;
    }
}
