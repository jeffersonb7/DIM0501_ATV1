import java.util.ArrayList;
import java.util.Scanner;

class D {
    String t;
    String c;
}

public class Sistema {

    static ArrayList<D> data = new ArrayList<>();

    // função que faz tudo
    public static void f(String a, String b) {
        // adiciona coisa
        if (a != null && !a.equals("")) {
            D d = new D();
            d.t = a;

            if (b == null || b.equals("")) {
                d.c = "duvidosa";
            } else {
                d.c = b;
            }

            data.add(d);
        } else {
            System.out.println("erro");
        }
    }

    public static void func2() {
        // lista tudo
        for (int i = 0; i < data.size(); i++) {
            System.out.println("Texto: " + data.get(i).t);
            System.out.println("Classificacao: " + data.get(i).c);
            System.out.println("-------------------");
        }
    }

    public static String analisar(String txt) {
        int score = 0;

        if (!txt.contains("FONTE")) {
            score = score + 1;
        }
        if (txt.contains("!!!")) {
            score = score + 1;
        }
        if (txt.contains("URGENTE")) {
            score = score + 1;
        }
        if (txt.length() < 10) {
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

    public static void addManual(Scanner sc) {
        System.out.print("Digite o texto: ");
        String t = sc.nextLine();

        System.out.print("Digite classificacao: ");
        String c = sc.nextLine();

        if (c.equals("")) {
            f(t, null);
        } else {
            f(t, c);
        }
    }

    public static void addAuto(Scanner sc) {
        System.out.print("Digite o texto: ");
        String t = sc.nextLine();

        String c = analisar(t);
        f(t, c);
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1 - adicionar manual");
            System.out.println("2 - adicionar automatico");
            System.out.println("3 - listar");
            System.out.println("4 - sair");

            String op = sc.nextLine();

            if (op.equals("1")) {
                addManual(sc);
            } else if (op.equals("2")) {
                addAuto(sc);
            } else if (op.equals("3")) {
                func2();
            } else if (op.equals("4")) {
                break;
            } else {
                System.out.println("errado");
            }
        }

        sc.close();
    }

    // inicia programa
    public static void main(String[] args) {
        menu();
    }
}
