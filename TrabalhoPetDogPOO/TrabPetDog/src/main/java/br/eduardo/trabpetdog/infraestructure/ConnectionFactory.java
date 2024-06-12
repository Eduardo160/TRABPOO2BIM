
package br.eduardo.trabpetdog.infraestructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/PetDog-POO", //driver:banco:caminho do banco
                "postgres", //usuario do banco
                "40854085" // senha do banco
        );
    }
}
