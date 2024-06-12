
package br.eduardo.trabpetdog.services;

import br.eduardo.trabpetdog.domain.Raca;
import br.eduardo.trabpetdog.exceptions.NegocioException;
import br.eduardo.trabpetdog.repositories.RacaRepository;
import java.sql.SQLException;
import java.util.ArrayList;

public class RacaService {
    public Raca insert(Raca raca) throws SQLException, NegocioException {
        validate(raca);
        RacaRepository racaRepository = new RacaRepository();
        raca = racaRepository.insert(raca);
        return raca;
    }

    public Raca edit(Raca raca) throws SQLException, NegocioException {
        validate(raca);
        validateUpdate(raca);
        RacaRepository racaRepository = new RacaRepository();
        raca = racaRepository.update(raca);
        return raca;
    }

    public Raca findById(int id) throws SQLException {
        RacaRepository racaRepository = new RacaRepository();
        return racaRepository.findById(id);
    }

    public ArrayList<Raca> findAll() throws SQLException {
        RacaRepository racaRepository = new RacaRepository();
        return racaRepository.findAll();
    }

    public void delete(int id) throws SQLException {
        RacaRepository racaRepository = new RacaRepository();
        racaRepository.delete(id);
    }

    private void validate(Raca raca) throws NegocioException {
        if (raca.getDescricao() == null || raca.getDescricao().isBlank()) {
            throw new NegocioException("A descrição da Raça deve ser "
                    + "informada.");
        }
        if (raca.getDescricao().length() <= 4) {
            throw new NegocioException("A descrição da Raça deve possuir"
                    + " 4 ou mais caracteres.");
        }
        if (raca.getDescricao().length() > 60) {
            throw new NegocioException("A descrição da Raça não deve"
                    + " possuir mais do que 60 caracteres.");
        }
    }

    private void validateUpdate(Raca raca) throws NegocioException {
        if (raca.getId() == 0) {
            throw new NegocioException("Informe um Código Válido para"
                    + " atualização da raça");
        }
    }
}
