package tests;

import dao.ContatoDao;
import enums.TipoContatoEnum;
import models.Contato;

public class TesteAlterarContato {
    public static void main(String[] args) {
        Contato contato = new Contato();
        ContatoDao dao = new ContatoDao();

        contato.setCodigo(1);
        contato.setTipoContato(TipoContatoEnum.FAMILIAR);
        contato.setEmail("jujubalindinha2511@gmail.com");
        contato.setNome("Julliana Karol Gomes Destefani");
        contato.setInstagram("@_g_jux");
        contato.setTelefone("11933379924");

//        dao.alterarContato(contato);
        dao.deletarContato(1102);
    }
}
