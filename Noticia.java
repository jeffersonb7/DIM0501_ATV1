/**
 * Representa uma notícia com seu conteúdo textual e classificação de credibilidade.
 */
public class Noticia {

    private String texto;
    private Classificacao classificacao;

    /**
     * @param texto         conteúdo textual da notícia
     * @param classificacao nível de credibilidade atribuído
     */
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
