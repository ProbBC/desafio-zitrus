package com.zitrus.desafiozitrus.movimentoestoque.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.METHOD_NOT_ALLOWED, reason = "Não é possível editar uma movimentação.")
public class MovimentoEstoqueEditException extends RuntimeException {
}
