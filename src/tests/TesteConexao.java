package tests;

import dao.ConnectionFactory;
import dao.ContatoDAO;
import enums.TipoContatoEnum;
import models.Contato;

import java.util.Scanner;

public class TesteConexao {
    public static void main(String[] args) {
        System.out.println("Teste de conexão com banco de dados");
        if(ConnectionFactory.obterConnection() == null){
            System.out.println("Erro ao estabelecer conexão");
        } else {
            System.out.println("Conexão estabelecida com sucesso!");
        }
        Scanner leitor = new Scanner(System.in);
        Contato Mikas = new Contato();

        System.out.print("Digite o ID:\t");
        int codigo = leitor.nextInt();
        Mikas.setCodigo(codigo);
        System.out.print("Qual o nome:\t");
        Mikas.setNome(leitor.nextLine());
        System.out.print("Digite o email:\t");
        String email = leitor.nextLine();
        Mikas.setEmail(email);
        System.out.print("Digite o número de telefone:\t");
        String numero = leitor.nextLine();
        Mikas.setTelefone(numero);
        System.out.print("Qual o instagram do contato:\t");
        String insta = leitor.nextLine();
        Mikas.setInstagram(insta);
        System.out.println("Qual o tipo do contato?");
        System.out.println("1 - Familiar\t 2 - Amigo\t 3 - Profissional");
        int op = leitor.nextInt();
        if (op == 1) {
            Mikas.setTipoContato(TipoContatoEnum.FAMILIAR);
        } else if (op == 2) {
            Mikas.setTipoContato(TipoContatoEnum.AMIGO);
        } else if (op == 3) {
            Mikas.setTipoContato(TipoContatoEnum.PROFISSIONAL);
        } else {
            System.out.println("ERRO - OPÇÃO INVÁLIDA");
        }


        ContatoDAO contatoDao = new ContatoDAO();

        contatoDao.cadastrarContato(Mikas);
        contatoDao.lerContatos();
    }
}
