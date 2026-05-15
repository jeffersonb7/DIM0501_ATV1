import java.util.Scanner;

/**
 * Interface de linha de comando do sistema de monitoramento de notícias.
 * Responsável pela interação com o usuário e pela delegação das operações
 * à camada de serviço {@link SistemaDeNoticias}.
 */
public class Sistema {

    private SistemaDeNoticias servico = new SistemaDeNoticias();
    private Scanner sc = new Scanner(System.in);

    private String lerTexto() {
        System.out.print("Digite o texto: ");
        return sc.nextLine();
    }

    private String lerClassificacao() {
        System.out.print("Digite a classificacao: ");
        return sc.nextLine();
    }

    /**
     * Solicita texto e classificação ao usuário e adiciona a notícia manualmente.
     * Exibe mensagem de erro caso a entrada seja inválida.
     */
    public void adicionarNoticiaManualmente() {
        String texto = lerTexto();
        String classificacao = lerClassificacao();

        try {
            servico.adicionarNoticia(texto, classificacao.isBlank() ? null : classificacao);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Solicita texto ao usuário e classifica a notícia automaticamente pelo serviço.
     */
    public void adicionarNoticiaAutomaticamente() {
        String texto = lerTexto();
        Classificacao classificacao = servico.classificarNoticia(texto);
        servico.adicionarNoticia(texto, classificacao.name());
    }

    private void exibirOpcoes() {
        System.out.println("1 - adicionar manual");
        System.out.println("2 - adicionar automatico");
        System.out.println("3 - listar");
        System.out.println("4 - sair");
    }

    private boolean processarOpcao(String operacao) {
        if (operacao.equals("1")) {
            adicionarNoticiaManualmente();
        } else if (operacao.equals("2")) {
            adicionarNoticiaAutomaticamente();
        } else if (operacao.equals("3")) {
            servico.listarNoticias();
        } else if (operacao.equals("4")) {
            return false;
        } else {
            System.out.println("Opcao invalida.");
        }
        return true;
    }

    /**
     * Exibe o menu principal e processa as operações até o usuário escolher sair.
     */
    public void exibirMenu() {
        boolean continuar = true;
        while (continuar) {
            exibirOpcoes();
            String operacao = sc.nextLine();
            continuar = processarOpcao(operacao);
        }
        sc.close();
    }

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        sistema.exibirMenu();
    }
}
