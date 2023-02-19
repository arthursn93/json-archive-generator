package com.automatizador.json.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ContatoDomain implements Serializable {
    private String name;
    private Integer idade;
    private String endereço;
    private String nacionalidade;
    private String sexo;
    private ProdutoDomain produtoDomain;

}
