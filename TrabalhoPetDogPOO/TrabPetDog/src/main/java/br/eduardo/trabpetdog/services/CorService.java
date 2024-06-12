
package br.eduardo.trabpetdog.services;

import br.eduardo.trabpetdog.domain.Cor;
import br.eduardo.trabpetdog.exceptions.NegocioException;
import br.eduardo.trabpetdog.repositories.CorRepository;
import java.sql.SQLException;
import java.util.ArrayList;

public class CorService {

    public Cor insert(Cor cor) throws SQLException, NegocioException {
        validate(cor);
        CorRepository corRepository = new CorRepository();
        cor = corRepository.insert(cor);
        return cor;
    }

    public Cor edit(Cor cor) throws SQLException, NegocioException {
        validate(cor);
        validateUpdate(cor);
        CorRepository corRepository = new CorRepository();
        cor = corRepository.update(cor);
        return cor;
    }

    public Cor findById(int id) throws SQLException {
        CorRepository corRepository = new CorRepository();
        Cor cor = corRepository.findById(id);
        return cor;
    }

    public ArrayList<Cor> findAll() throws SQLException {
        CorRepository corRepository = new CorRepository();
        ArrayList<Cor> resultado = corRepository.findAll();
        return resultado;
    }

    private void validate(Cor cor) throws NegocioException {
        if (cor.getDescricao() == null) {
            throw new NegocioException("A descrição da Cor deve ser "
                    + "Informada.");
        }
        if (cor.getDescricao().isBlank()) {
            throw new NegocioException("A descrição da Cor "
                    + "deve ser Informada.");
        }
        if (cor.getDescricao().length() <= 4) {
            throw new NegocioException("A descrição da Cor "
                    + "deve possuir 4 "
                    + "ou mais caracteres.");
        }
        if (cor.getDescricao().length() > 60) {
            throw new NegocioException("A descrição da Cor "
                    + "não deve possuir "
                    + "mais do que 60 caracteres");
        }
    }

    private void validateUpdate(Cor cor) throws NegocioException {
        if (cor.getId() == 0) {
            throw new NegocioException("Informe um Código Válido "
                    + "para atualização da cor");
        }
    }

    public void delete(int id) throws SQLException {
        CorRepository corRepository = new CorRepository();
        corRepository.delete(id);
    }
}
