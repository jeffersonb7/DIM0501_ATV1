# Etapa 5. Melhorar Documentação

## 1. Problemas de Documentação no Código Original

| Linha | Comentário | Problema |
|-------|-----------|----------|
| 13 | `// função que faz tudo` | Vago e não agrega informação |
| 15 | `// adiciona coisa` | Impreciso e redundante |
| 33 | `// lista tudo` | Óbvio pelo nome da função |
| 115 | `// inicia programa` | Óbvio pelo método `main` |

Além disso, o código original não possuía nenhuma documentação formal (JavaDoc) nas classes ou métodos.

---

## 2. Ações Realizadas

### 2.1 Remoção de Comentários Inúteis

Todos os comentários redundantes ou vagos foram removidos. Após a refatoração de nomes (Etapa 2), o próprio código passou a se documentar:

```java
// Antes — comentário necessário para compensar nome ruim
// lista tudo
public static void func2() { ... }

// Depois — nome explícito, comentário desnecessário
public void listarNoticias() { ... }
```

### 2.2 Adição de JavaDoc

JavaDoc foi adicionado a todas as classes e métodos relevantes nos quatro arquivos do projeto.

**Critérios aplicados:**
- Classes públicas: descrição do papel no sistema
- Métodos públicos: `@param`, `@return` e `@throws` quando aplicável
- Métodos privados não-triviais: comentário de uma linha descrevendo o `porquê` ou a invariante

**Exemplos aplicados:**

```java
/**
 * Calcula a pontuação de risco de um texto com base em indicadores de desinformação.
 * Cada indicador presente soma 1 ponto ao total.
 *
 * @throws IllegalArgumentException se o texto for nulo
 */
private int calcularPontuacaoDeRisco(String texto) { ... }
```

```java
/**
 * Adiciona uma notícia ao sistema.
 * Se a classificação não for informada, aplica {@link Classificacao#DUVIDOSA} por padrão.
 *
 * @param texto         conteúdo da notícia
 * @param classificacao string com a classificação (pode ser nula ou vazia)
 * @throws IllegalArgumentException se o texto for inválido ou a classificação for desconhecida
 */
public void adicionarNoticia(String texto, String classificacao) { ... }
```

---

## 3. Arquivo README.md

Foi criado o arquivo `README.md` na raiz do projeto com:
- Descrição do sistema
- Instruções de compilação e execução
- Estrutura do projeto e papel de cada arquivo

---

## 4. Resumo das Mudanças de Documentação

| Ação | Quantidade |
|------|-----------|
| Comentários inúteis removidos | 4 |
| Classes com JavaDoc adicionado | 4 (`Classificacao`, `Noticia`, `SistemaDeNoticias`, `Sistema`) |
| Métodos públicos documentados com JavaDoc | 8 |
| Métodos privados com comentário de linha | 4 |
| Constantes documentadas | 1 (`LIMITE_TEXTO_CURTO`) |
| Arquivo README criado | 1 |
