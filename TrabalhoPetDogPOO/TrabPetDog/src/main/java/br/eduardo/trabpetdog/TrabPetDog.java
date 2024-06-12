
package br.eduardo.trabpetdog;

import br.eduardo.trabpetdog.domain.Cachorro;
import br.eduardo.trabpetdog.domain.Cor;
import br.eduardo.trabpetdog.domain.Pelagem;
import br.eduardo.trabpetdog.domain.Raca;
import br.eduardo.trabpetdog.exceptions.NegocioException;
import br.eduardo.trabpetdog.services.CachorroService;
import br.eduardo.trabpetdog.services.CorService;
import br.eduardo.trabpetdog.services.PelagemService;
import br.eduardo.trabpetdog.services.RacaService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class TrabPetDog {
    public static void main(String[] args) {
        try {
            CorService corService = new CorService();
            RacaService racaService = new RacaService();
            PelagemService pelagemService = new PelagemService();
            CachorroService cachorroService = new CachorroService();

            Cor cor = new Cor();
            cor.setDescricao("Amarelo");
            corService.insert(cor);

            Raca raca = new Raca();
            raca.setDescricao("Pastor Belga Malinois");
            racaService.insert(raca);

            Pelagem pelagem = new Pelagem();
            pelagem.setDescricao("Liso");
            pelagemService.insert(pelagem);

            Cachorro cachorro = new Cachorro();
            cachorro.setNome("Madona");
            cachorro.setRaca(raca);
            cachorro.setPelagem(pelagem);
            cachorro.setCor(cor);
            cachorro.setTamanho(0.5);
            cachorro.setStPerfume(true);
            cachorro.setDtNascimento(java.sql.Date.valueOf
                ("2011-02-13"));

            cachorroService.insert(cachorro);

            ArrayList<Cachorro> cachorros = cachorroService.findAll();
            for (Cachorro c : cachorros) {
                System.out.println(c);
            }
            
        } catch (SQLException ex) {
            System.out.println("Ops, algo deu errado com o banco de dados\n" 
                    + ex.getMessage());
        } catch (NegocioException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Ops, algo deu errado contate o suporte\n" 
                    + ex.getMessage());
        }
    }
}
