
package br.eduardo.trabpetdog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cachorro {
    private int id;
    private String nome;
    private Raca raca;
    private Pelagem pelagem;
    private Cor cor;
    private Double tamanho;
    private boolean stPerfume;
    private Date dtNascimento;
}
