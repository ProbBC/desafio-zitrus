package com.zitrus.desafiozitrus.produto;

import com.zitrus.desafiozitrus.movimentoestoque.MovimentoEstoque;
import com.zitrus.desafiozitrus.produto.enums.ProdutoTipos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(columnDefinition = "ENUM('ELETRONICO', 'ELETRODOMESTICO', 'MOVEL')", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProdutoTipos tipoProduto;

    @Column(nullable = false)
    private Double valorFornecedor;

    //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(columnDefinition = "integer default 0", nullable = false, insertable = false)
    @Range(min = 0)
    private Integer qtdEstoque = 0;

    //@JsonIgnore
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<MovimentoEstoque> movimentosEstoque = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Produto produto = (Produto) o;
        return id != null && Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", tipoProduto=" + tipoProduto +
                ", valorFornecedor=" + valorFornecedor +
                ", qtdEstoque=" + qtdEstoque +
                '}';
    }
}
