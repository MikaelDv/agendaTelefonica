package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static String user = System.getenv("FIAP_ORACLE_USER");
    private static String password = System.getenv("FIAP_ORACLE_PASSWORD");

    public static Connection obterConnection() {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", user, password);
        } catch (SQLException erro) {
            erro.printStackTrace();
        }
        return conexao;
    }
}
