import java.util.ArrayList;
import java.util.Scanner;

enum Classificacao {
    CONFIAVEL, DUVIDOSA, FALSA
}

class Noticia {
    private String texto;
    private Classificacao classificacao;

    public Noticia(String texto, Classificacao classificacao) {
        this.texto = texto;
        this.classificacao = classificacao;
    }

    public String getTexto() {
        return texto;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    @Override
    public String toString() {
        return "Texto: " + texto + "\nClassificacao: " + classificacao;
    }
}

public class Sistema {

    private static final int LIMITE_TEXTO_CURTO = 10;

    private ArrayList<Noticia> listaNoticias = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    private boolean validarString(String texto) {
        return texto != null && !texto.strip().isEmpty();
    }

    private Classificacao converterClassificacao(String valor) {
        try {
            return Classificacao.valueOf(valor.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                "Classificação inválida. Use CONFIAVEL, DUVIDOSA ou FALSA."
            );
        }
    }

    private int calcularPontuacaoDeRisco(String texto) {
        int pontuacao = 0;
        if (!texto.contains("FONTE"))            pontuacao++;
        if (texto.contains("!!!"))               pontuacao++;
        if (texto.contains("URGENTE"))           pontuacao++;
        if (texto.length() < LIMITE_TEXTO_CURTO) pontuacao++;
        return pontuacao;
    }

    private Classificacao classificarPorPontuacao(int pontuacao) {
        if (pontuacao == 0) return Classificacao.CONFIAVEL;
        if (pontuacao == 1) return Classificacao.DUVIDOSA;
        return Classificacao.FALSA;
    }

    public Classificacao classificarNoticia(String textoNoticia) {
        int pontuacao = calcularPontuacaoDeRisco(textoNoticia);
        return classificarPorPontuacao(pontuacao);
    }

    public void adicionarNoticia(String texto, String classificacao) {
        if (!validarString(texto)) {
            throw new IllegalArgumentException("Texto da notícia inválido.");
        }

        Classificacao classificacaoFinal;

        if (!validarString(classificacao)) {
            classificacaoFinal = Classificacao.DUVIDOSA;
        } else {
            classificacaoFinal = converterClassificacao(classificacao);
        }

        listaNoticias.add(new Noticia(texto, classificacaoFinal));
    }

    private String lerTexto() {
        System.out.print("Digite o texto: ");
        return sc.nextLine();
    }

    private String lerClassificacao() {
        System.out.print("Digite a classificacao: ");
        return sc.nextLine();
    }

    public void adicionarNoticiaManualmente(Scanner sc) {
        String texto = lerTexto();
        String classificacao = lerClassificacao();

        try {
            adicionarNoticia(texto, validarString(classificacao) ? classificacao : null);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void adicionarNoticiaAutomaticamente(Scanner sc) {
        String texto = lerTexto();
        Classificacao classificacao = classificarNoticia(texto);
        adicionarNoticia(texto, classificacao.name());
    }

    public void listarNoticias() {
        if (listaNoticias.isEmpty()) {
            System.out.println("Nenhuma notícia cadastrada.");
            return;
        }
        for (Noticia noticia : listaNoticias) {
            System.out.println(noticia);
            System.out.println("\n--------------------");
        }
    }

    private void exibirOpcoes() {
        System.out.println("1 - adicionar manual");
        System.out.println("2 - adicionar automatico");
        System.out.println("3 - listar");
        System.out.println("4 - sair");
    }

    private boolean processarOpcao(String operacao) {
        if (operacao.equals("1")) {
            adicionarNoticiaManualmente(sc);
        } else if (operacao.equals("2")) {
            adicionarNoticiaAutomaticamente(sc);
        } else if (operacao.equals("3")) {
            listarNoticias();
        } else if (operacao.equals("4")) {
            return false;
        } else {
            System.out.println("errado");
        }
        return true;
    }

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