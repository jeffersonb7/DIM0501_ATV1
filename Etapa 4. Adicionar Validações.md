# Etapa 4. Adicionar Validações

## 1. Problemas de Validação no Código Original

| Linha | Problema | Consequência |
|-------|----------|--------------|
| 44 | `txt.contains("FONTE")` sem verificar `txt != null` | `NullPointerException` em tempo de execução |
| 66–78 | `addManual()` sem tratamento de exceção | Erros silenciosos ou crash do programa |
| 80–86 | `addAuto()` sem tratamento de exceção | Idem |
| 107–109 | Opção de menu inválida exibe apenas `"errado"` | Sem orientação ao usuário |

---

## 2. Validações Implementadas

### 2.1 Validação de String Genérica — `validarString()`

**Onde:** `SistemaDeNoticias.java`

```java
private boolean validarString(String texto) {
    return texto != null && !texto.strip().isEmpty();
}
```

Usada internamente para verificar texto e classificação antes de qualquer processamento.

---

### 2.2 Validação do Texto da Notícia — `adicionarNoticia()`

**Onde:** `SistemaDeNoticias.java`

**Antes:** sem validação — a notícia era adicionada mesmo com texto vazio.

```java
// antigo.java — sem verificação de texto vazio
if (a != null && !a.equals("")) {
    // ...
} else {
    System.out.println("erro"); // sem exceção, fluxo não interrompido adequadamente
}
```

**Depois:** lança exceção com mensagem clara.

```java
public void adicionarNoticia(String texto, String classificacao) {
    if (!validarString(texto)) {
        throw new IllegalArgumentException("Texto da notícia inválido.");
    }
    // ...
}
```

---

### 2.3 Validação de Classificação — `converterClassificacao()`

**Onde:** `SistemaDeNoticias.java`

**Antes:** não havia conversão tipada — qualquer string era aceita como classificação.

**Depois:** tenta converter para o enum e lança exceção se o valor for inválido.

```java
private Classificacao converterClassificacao(String valor) {
    try {
        return Classificacao.valueOf(valor.toUpperCase());
    } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException(
            "Classificação inválida. Use CONFIAVEL, DUVIDOSA ou FALSA."
        );
    }
}
```

---

### 2.4 Validação de Nulo na Análise de Risco — `calcularPontuacaoDeRisco()`

**Onde:** `SistemaDeNoticias.java`

**Problema identificado:** o método chamava `texto.contains(...)` sem verificar se `texto` era nulo, o que causaria `NullPointerException` se chamado externamente com argumento nulo.

**Correção adicionada:**

```java
private int calcularPontuacaoDeRisco(String texto) {
    if (texto == null) {
        throw new IllegalArgumentException("Texto para análise não pode ser nulo.");
    }
    // ...
}
```

---

### 2.5 Captura de Exceção na Interface — `adicionarNoticiaManualmente()`

**Onde:** `Sistema.java`

**Antes:** qualquer erro em `addManual()` ou `addAuto()` propagava sem tratamento.

**Depois:** a camada de interface captura a exceção e exibe a mensagem ao usuário sem encerrar o programa.

```java
try {
    servico.adicionarNoticia(texto, classificacao.isBlank() ? null : classificacao);
} catch (IllegalArgumentException e) {
    System.out.println(e.getMessage());
}
```

---

### 2.6 Verificação de Lista Vazia — `listarNoticias()`

**Onde:** `SistemaDeNoticias.java`

**Antes:** listar sem noticias não exibia nada — comportamento silencioso e confuso.

**Depois:**

```java
if (listaNoticias.isEmpty()) {
    System.out.println("Nenhuma notícia cadastrada.");
    return;
}
```

---

### 2.7 Classificação Padrão para Entrada Vazia

**Onde:** `SistemaDeNoticias.java` — `adicionarNoticia()`

Quando o usuário não informa classificação, o sistema assume `DUVIDOSA` como padrão (comportamento preservado do original, agora explícito via enum).

```java
if (!validarString(classificacao)) {
    classificacaoFinal = Classificacao.DUVIDOSA;
}
```

---

## 3. Resumo das Validações

| Validação | Onde | Tipo |
|-----------|------|------|
| Texto nulo ou vazio | `adicionarNoticia()` | Exceção com mensagem |
| Classificação inválida | `converterClassificacao()` | Exceção com mensagem |
| Texto nulo antes de `contains()` | `calcularPontuacaoDeRisco()` | Exceção com mensagem |
| Lista vazia ao listar | `listarNoticias()` | Mensagem informativa |
| Entrada vazia de classificação | `adicionarNoticia()` | Padrão `DUVIDOSA` |
| Exceção na interface | `adicionarNoticiaManualmente()` | Captura + exibe mensagem |
