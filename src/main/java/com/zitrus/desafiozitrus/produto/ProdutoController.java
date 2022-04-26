package com.zitrus.desafiozitrus.produto;

import com.zitrus.desafiozitrus.mapper.EntityMapper;
import com.zitrus.desafiozitrus.produto.DTO.LucroPorProdutoDTO;
import com.zitrus.desafiozitrus.produto.DTO.ProdutoDTO;
import com.zitrus.desafiozitrus.produto.DTO.ProdutoPorTipoDTO;
import com.zitrus.desafiozitrus.produto.enums.ProdutoTipos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/produtos"})
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private EntityMapper mapper;

    @GetMapping
    public List findAll() {
        return produtoService.findAll().stream()
                .map(mapper::produtoToProdutoDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id) {
        Produto produto = produtoService.findById((id));
        ProdutoDTO produtoDTO = mapper.produtoToProdutoDTO(produto);
        if (produto != null) {
            return ResponseEntity.ok().body(produtoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = {"/tipo/{tipoProduto}"})
    public ResponseEntity findByType(@Valid @PathVariable String tipoProduto) {
        ProdutoTipos tipoProdutoEnum = ProdutoTipos.valueOf(tipoProduto.toUpperCase());
        List<ProdutoPorTipoDTO> produtos = produtoService.findByType(tipoProdutoEnum);
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping(path = {"/lucro/{id}"})
    public ResponseEntity findLucroPorProduto(@PathVariable Long id) {
        LucroPorProdutoDTO lucroPorProdutoDTO = produtoService.findLucroPorProduto(id);
        if (lucroPorProdutoDTO != null) {
            return ResponseEntity.ok().body(lucroPorProdutoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ProdutoDTO create(@Valid @RequestBody ProdutoDTO produtoDTO) {
        if (produtoDTO.getId() == null) {
            Produto produto = produtoService.save(mapper.produtoDTOToProduto(produtoDTO));
            return mapper.produtoToProdutoDTO(produto);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto já existe.");
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @Valid @RequestBody ProdutoDTO produtoDTO) {
        Produto updated = produtoService.update(id, mapper.produtoDTOToProduto(produtoDTO));
        if (updated != null) {
            return ResponseEntity.ok().body(mapper.produtoToProdutoDTO(updated));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        if (produtoService.deleteById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
