
package br.eduardo.trabpetdog.services;

import br.eduardo.trabpetdog.domain.Pelagem;
import br.eduardo.trabpetdog.exceptions.NegocioException;
import br.eduardo.trabpetdog.repositories.PelagemRepository;
import java.sql.SQLException;
import java.util.ArrayList;

public class PelagemService {

    public Pelagem insert(Pelagem pelagem) throws SQLException, 
            NegocioException {
        validate(pelagem);
        PelagemRepository pelagemRepository = new PelagemRepository();
        pelagem = pelagemRepository.insert(pelagem);
        return pelagem;
    }

    public Pelagem edit(Pelagem pelagem) throws SQLException, 
            NegocioException {
        validate(pelagem);
        validateUpdate(pelagem);
        PelagemRepository pelagemRepository = new PelagemRepository();
        pelagem = pelagemRepository.update(pelagem);
        return pelagem;
    }

    public Pelagem findById(int id) throws SQLException {
        PelagemRepository pelagemRepository = new PelagemRepository();
        return pelagemRepository.findById(id);
    }

    public ArrayList<Pelagem> findAll() throws SQLException {
        PelagemRepository pelagemRepository = new PelagemRepository();
        return pelagemRepository.findAll();
    }

    public void delete(int id) throws SQLException {
        PelagemRepository pelagemRepository = new PelagemRepository();
        pelagemRepository.delete(id);
    }

    private void validate(Pelagem pelagem) throws NegocioException {
        if (pelagem.getDescricao() == null || pelagem.getDescricao().isBlank()){
            throw new NegocioException("A descrição da Pelagem deve ser "
                    + "informada.");
        }
        if (pelagem.getDescricao().length() <= 3) {
            throw new NegocioException("A descrição da Pelagem deve "
                    + "possuir 4 ou mais caracteres.");
        }
        if (pelagem.getDescricao().length() > 60) {
            throw new NegocioException("A descrição da Pelagem não deve"
                    + " possuir mais do que 60 caracteres.");
        }
    }

    private void validateUpdate(Pelagem pelagem) throws NegocioException {
        if (pelagem.getId() == 0) {
            throw new NegocioException("Informe um Código Válido para"
                    + " atualização da pelagem");
        }
    }
}
