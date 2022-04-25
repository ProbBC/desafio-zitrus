package com.zitrus.desafiozitrus.movimentoestoque;

import com.zitrus.desafiozitrus.movimentoestoque.enums.MovimentoEstoqueTipos;
import com.zitrus.desafiozitrus.produto.Produto;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MovimentoEstoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    @NotNull
    private Produto produto;

    @Column(columnDefinition = "ENUM('ENTRADA', 'SAIDA')")
    @Enumerated(EnumType.STRING)
    @NotNull
    private MovimentoEstoqueTipos tipoMovimentacao;

    @NotNull
    @Range(min = 0)
    private Double valorVenda;

    @CreationTimestamp
    @PastOrPresent
    private LocalDateTime dataVenda;

    @NotNull
    @Range(min = 1)
    private Integer qtdMovimentada;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MovimentoEstoque that = (MovimentoEstoque) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
