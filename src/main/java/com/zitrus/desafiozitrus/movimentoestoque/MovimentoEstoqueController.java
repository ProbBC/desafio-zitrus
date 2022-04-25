package com.zitrus.desafiozitrus.movimentoestoque;

import com.zitrus.desafiozitrus.mapper.EntityMapper;
import com.zitrus.desafiozitrus.movimentoestoque.DTO.MovimentoEstoqueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/movimento-estoque"})
public class MovimentoEstoqueController {

    @Autowired
    private EntityMapper mapper;

    @Autowired
    private MovimentoEstoqueService movimentoEstoqueService;

    @GetMapping
    public List findAll() {
        return movimentoEstoqueService.findAll().stream()
                .map(mapper::movimentoEstoqueToMovimentoEstoqueDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id) {
        MovimentoEstoque movimentoEstoque = movimentoEstoqueService.findById((id));
        MovimentoEstoqueDTO movimentoEstoqueDTO = mapper.movimentoEstoqueToMovimentoEstoqueDTO(movimentoEstoque);
        if (movimentoEstoque != null) {
            return ResponseEntity.ok().body(movimentoEstoqueDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public MovimentoEstoqueDTO create(@Valid @RequestBody MovimentoEstoqueDTO movimentoEstoqueDTO) {
        MovimentoEstoque movimentoEstoque = movimentoEstoqueService.save(
                mapper.movimentoEstoqueDTOToMovimentoEstoque(movimentoEstoqueDTO)
        );
        return mapper.movimentoEstoqueToMovimentoEstoqueDTO(movimentoEstoque);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        if (movimentoEstoqueService.deleteById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
