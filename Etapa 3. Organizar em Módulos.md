# Etapa 3. Organizar em Módulos

## 1. Problema no Código Anterior

O arquivo `antigo.java` concentrava toda a responsabilidade em uma única classe (`Sistema`), violando o **Princípio da Responsabilidade Única**:

| Responsabilidade | Localização anterior |
|------------------|---------------------|
| Modelo de dados | Classe `D` (dentro de `Sistema.java`) |
| Lógica de negócio | Métodos `f()`, `func2()`, `analisar()` |
| Interface com o usuário | Métodos `menu()`, `addManual()`, `addAuto()` |
| Ponto de entrada | Método `main()` |

Após a Etapa 2, o `Sistema.java` refatorado agrupou enum, modelo e toda a lógica em um único arquivo. A Etapa 3 separa cada responsabilidade em seu próprio arquivo.

---

## 2. Estrutura Modular Adotada

```
projeto/
├── Classificacao.java      ← enum (suporte ao modelo)
├── Noticia.java            ← modelo (dados + toString)
├── SistemaDeNoticias.java  ← serviço (lógica de negócio)
└── Sistema.java            ← interface/menu + main
```

---

## 3. Responsabilidade de Cada Módulo

### `Classificacao.java` — Enum de Domínio
Centraliza os valores válidos de credibilidade, eliminando o uso de strings literais no restante do código.

```java
public enum Classificacao {
    CONFIAVEL, DUVIDOSA, FALSA
}
```

---

### `Noticia.java` — Modelo
Encapsula os dados de uma notícia. Não contém lógica de negócio.

```java
public class Noticia {
    private String texto;
    private Classificacao classificacao;
    // construtor, getters, toString
}
```

---

### `SistemaDeNoticias.java` — Serviço (Lógica de Negócio)
Contém toda a lógica de classificação, validação interna e gerenciamento da coleção. Não conhece `Scanner` nem exibe menus.

Métodos públicos:
- `adicionarNoticia(String texto, String classificacao)`
- `classificarNoticia(String textoNoticia)`
- `listarNoticias()`

Métodos privados (auxiliares):
- `validarString(String texto)`
- `converterClassificacao(String valor)`
- `calcularPontuacaoDeRisco(String texto)`
- `classificarPorPontuacao(int pontuacao)`

---

### `Sistema.java` — Interface + Main
Responsável exclusivamente pela interação com o usuário via terminal. Instancia `SistemaDeNoticias` e delega todas as operações a ele.

Métodos públicos:
- `adicionarNoticiaManualmente()`
- `adicionarNoticiaAutomaticamente()`
- `exibirMenu()`
- `main(String[] args)`

Métodos privados (auxiliares de I/O):
- `lerTexto()`
- `lerClassificacao()`
- `exibirOpcoes()`
- `processarOpcao(String operacao)`

---

## 4. Benefícios da Modularização

| Benefício | Descrição |
|-----------|-----------|
| **Manutenção** | Alterar a lógica de classificação não afeta a interface e vice-versa |
| **Legibilidade** | Cada arquivo tem escopo limitado e claro |
| **Reutilização** | `SistemaDeNoticias` pode ser usado por outra interface (ex.: web, API) sem alteração |
| **Testabilidade** | A lógica de negócio pode ser testada independentemente da entrada do usuário |
