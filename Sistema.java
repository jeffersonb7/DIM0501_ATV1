import java.util.ArrayList;
import java.util.Scanner;

enum Classificacao {
    CONFIAVEL, DUVIDOSA, FALSA
}

class Noticia {
    String texto;
    Classificacao classificacao;
}

public class Sistema {
    private ArrayList<Noticia> listaNoticias = new ArrayList<>();
    private boolean validarString(String texto) {
        return texto != null && !texto.strip().isEmpty();
    }
    public void adicionarNoticia(String texto, String classificacao) {
        if (!validarString(texto)) {
            System.out.println("erro");
            return;
        }

        Noticia noticia = new Noticia();
        noticia.texto = texto;

        if (!validarString(classificacao)) {
            noticia.classificacao = Classificacao.DUVIDOSA;
        } else {
            try {
                noticia.classificacao = Classificacao.valueOf(classificacao.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Classificação inválida. Use CONFIAVEL, DUVIDOSA ou FALSA.");
            }
        }

        listaNoticias.add(noticia);
    }

    public void listarNoticias() {
        for (Noticia noticia : listaNoticias) {
            System.out.println("Texto: " + noticia.texto);
            System.out.println("Classificacao: " + noticia.classificacao);
            System.out.println("-------------------");
        }
    }

    public Classificacao classificarNoticia(String textoNoticia) {
        int score = 0;
        if (!textoNoticia.contains("FONTE")) {
            score = score + 1;
        }
        if (textoNoticia.contains("!!!")) {
            score = score + 1;
        }
        if (textoNoticia.contains("URGENTE")) {
            score = score + 1;
        }
        if (textoNoticia.length() < 10) {
            score = score + 1;
        }

        if (score == 0) {
            return Classificacao.CONFIAVEL;
        } else if (score == 1) {
            return Classificacao.DUVIDOSA;
        } else {
            return Classificacao.FALSA;
        }
    }

    public void adicionarNoticiaManualmente(Scanner sc) {
        System.out.print("Digite o texto: ");
        String texto = sc.nextLine();

        System.out.print("Digite a classificacao: ");
        String classificacao = sc.nextLine();

        if (!validarString(classificacao)) {
            adicionarNoticia(texto, null);
        } else {
            try {
                adicionarNoticia(texto, classificacao);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
    }

    public void adicionarNoticiaAutomaticamente(Scanner sc) {
        System.out.print("Digite o texto: ");
        String texto = sc.nextLine();

        Classificacao classificacao = classificarNoticia(texto);
        adicionarNoticia(texto, classificacao.name());
    }

    public void exibirMenu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1 - adicionar manual");
            System.out.println("2 - adicionar automatico");
            System.out.println("3 - listar");
            System.out.println("4 - sair");

            String operacao = sc.nextLine();
            
            if (operacao.equals("1")) {
                adicionarNoticiaManualmente(sc);
            } else if (operacao.equals("2")) {
                adicionarNoticiaAutomaticamente(sc);
            } else if (operacao.equals("3")) {
                listarNoticias();
            } else if (operacao.equals("4")) {
                break;
            } else {
                System.out.println("errado");
            }
        }

        sc.close();
    }

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        sistema.exibirMenu();
    }
}
