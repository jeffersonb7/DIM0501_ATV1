import java.util.ArrayList;

/**
 * Serviço responsável pela lógica de negócio do sistema de monitoramento de notícias.
 * Gerencia o cadastro, a classificação automática e a listagem de notícias.
 */
public class SistemaDeNoticias {

    /** Comprimento mínimo esperado de um texto confiável. */
    private static final int LIMITE_TEXTO_CURTO = 10;

    private ArrayList<Noticia> listaNoticias = new ArrayList<>();

    /** Retorna true se a string for não-nula e não-vazia após remoção de espaços. */
    private boolean validarString(String texto) {
        return texto != null && !texto.strip().isEmpty();
    }

    /**
     * Converte uma string para o enum {@link Classificacao} correspondente.
     *
     * @throws IllegalArgumentException se o valor não corresponder a nenhuma classificação válida
     */
    private Classificacao converterClassificacao(String valor) {
        try {
            return Classificacao.valueOf(valor.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                "Classificação inválida. Use CONFIAVEL, DUVIDOSA ou FALSA."
            );
        }
    }

    /**
     * Calcula a pontuação de risco de um texto com base em indicadores de desinformação.
     * Cada indicador presente soma 1 ponto ao total.
     *
     * @throws IllegalArgumentException se o texto for nulo
     */
    private int calcularPontuacaoDeRisco(String texto) {
        if (texto == null) {
            throw new IllegalArgumentException("Texto para análise não pode ser nulo.");
        }
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

    /**
     * Classifica automaticamente uma notícia com base em sua pontuação de risco.
     *
     * @param textoNoticia texto a ser analisado
     * @return classificação de credibilidade calculada
     */
    public Classificacao classificarNoticia(String textoNoticia) {
        int pontuacao = calcularPontuacaoDeRisco(textoNoticia);
        return classificarPorPontuacao(pontuacao);
    }

    /**
     * Adiciona uma notícia ao sistema.
     * Se a classificação não for informada, aplica {@link Classificacao#DUVIDOSA} por padrão.
     *
     * @param texto         conteúdo da notícia
     * @param classificacao string com a classificação (pode ser nula ou vazia)
     * @throws IllegalArgumentException se o texto for inválido ou a classificação for desconhecida
     */
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

    /**
     * Exibe todas as notícias cadastradas.
     * Caso nenhuma tenha sido cadastrada, exibe uma mensagem informativa.
     */
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
}
