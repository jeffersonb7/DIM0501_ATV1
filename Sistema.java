import java.util.ArrayList;
import java.util.Scanner;

class Noticia {
    String texto;
    String classificacao;
}

public class Sistema {

    static ArrayList<Noticia> listaNoticias = new ArrayList<>();

    public static void adicionarNoticia(String texto, String classificacao) {
        // TODO: extrair validação para outra função
        if (texto == null || texto.isEmpty()) {
            System.out.println("erro");
            return;
        }

        Noticia noticia = new Noticia();
        noticia.texto = texto;

        if (classificacao == null || classificacao.isEmpty()) {
            noticia.classificacao = "duvidosa";
        } else {
            // Possível fonte de bugs pois não garante enquadramento da classificação
            noticia.classificacao = classificacao;
        }

        listaNoticias.add(noticia);
    }

    public static void listarNoticias() {
        for (Noticia noticia : listaNoticias) {
            System.out.println("Texto: " + noticia.texto);
            System.out.println("Classificacao: " + noticia.classificacao);
            System.out.println("-------------------");
        }
    }

    public static String classificarNoticia(String textoNoticia) {
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
            return "confiavel";
        } else if (score == 1) {
            return "duvidosa";
        } else {
            return "falsa";
        }
    }

    public static void adicionarNoticiaManualmente(Scanner sc) {
        System.out.print("Digite o texto: ");
        String texto = sc.nextLine();

        System.out.print("Digite classificacao: ");
        String classificacao = sc.nextLine();

        if (classificacao.isEmpty()) {
            adicionarNoticia(texto, null);
        } else {
            adicionarNoticia(texto, classificacao);
        }
    }

    public static void adicionarNoticiaAutomaticamente(Scanner sc) {
        System.out.print("Digite o texto: ");
        String texto = sc.nextLine();

        String classificacao = classificarNoticia(texto);
        adicionarNoticia(texto, classificacao);
    }

    public static void exibirMenu() {
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
        exibirMenu();
    }
}
