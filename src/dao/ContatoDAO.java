package dao;
// é a classe responsável por manipular os dados no banco de dados
// implementando o CRUD

import enums.TipoContatoEnum;
import models.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ContatoDAO {
    private Connection conexao;
    public void cadastrarContato(Contato contato){
        conexao = ConnectionFactory.obterConnection();
        PreparedStatement comandoSQL = null;
        String sql = "insert into tbl_contato(ID_CONTATO, NOME_CONTATO, CELULAR_CONTATO, EMAIL_CONTATO," +
                " INSTAGRAM, TIPO) values (?,?,?,?,?,?)";
        try {
            comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setInt(1, contato.getCodigo());
            comandoSQL.setString(2, contato.getNome());
            comandoSQL.setString(3, contato.getTelefone());
            comandoSQL.setString(4, contato.getEmail());
            comandoSQL.setString(5, contato.getInstagram());
            comandoSQL.setString(6, contato.getTipoContato().toString());
            comandoSQL.executeUpdate();

            comandoSQL.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void lerContatos() {
        conexao = ConnectionFactory.obterConnection();
        PreparedStatement comandoSQL = null;
        String sql = "select * from tbl_contato";
        try {
            comandoSQL = conexao.prepareStatement(sql);
            ResultSet resultado = comandoSQL.executeQuery();

            System.out.println("_____________________________________________");
            while (resultado.next()) {
                int codigo = resultado.getInt("ID_CONTATO");
                String nome = resultado.getString("NOME_CONTATO");
                System.out.println("ID: " + codigo + "\nNOME: " + nome );
                System.out.println("_____________________________________________");
            }

            comandoSQL.close();
            conexao.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Contato buscarPorId(int id) throws SQLException {
        conexao = ConnectionFactory.obterConnection();
        PreparedStatement comandoSQL = null;

        Contato contato = new Contato();
        try {
            String sql = "SELECT * FROM TBL_CONTATO WHERE ID_CONTATO = ?";
            comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setInt(1, id);
            ResultSet rs = comandoSQL.executeQuery();
            if (rs.next()) {
                contato.setCodigo(rs.getInt(1));
                contato.setNome(rs.getString(2));
                contato.setTelefone(rs.getString(3));
                contato.setEmail(rs.getString(4));
                contato.setInstagram(rs.getString(5));
                contato.setTipoContato(TipoContatoEnum.valueOf(rs.getString(6)));
            } else {
                System.out.println("NO DATA FOUND");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            comandoSQL.close();
            conexao.close();
        }
        return contato;
    }
}
