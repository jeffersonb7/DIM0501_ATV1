# Etapa 2. Refatorar Nomes e Funções

## 1. Renomeações Aplicadas

| Elemento | Antes (`antigo.java`) | Depois (`Sistema.java`) | Motivo |
|----------|-----------------------|-------------------------|--------|
| Classe | `D` | `Noticia` | Nome expressa o domínio do sistema |
| Atributo | `t` | `texto` | Elimina abreviação sem contexto |
| Atributo | `c` | `classificacao` | Elimina abreviação sem contexto |
| Coleção | `data` | `listaNoticias` | Descreve o conteúdo armazenado |
| Método | `f(String a, String b)` | `adicionarNoticia(String texto, String classificacao)` | Nome e parâmetros expressam a intenção |
| Método | `func2()` | `listarNoticias()` | Nome genérico substituído por verbo + substantivo |
| Método | `analisar(String txt)` | `classificarNoticia(String textoNoticia)` | Mais específico ao domínio |
| Método | `addManual(Scanner sc)` | `adicionarNoticiaManualmente()` | Português claro; Scanner removido (redundante) |
| Método | `addAuto(Scanner sc)` | `adicionarNoticiaAutomaticamente()` | Português claro; Scanner removido (redundante) |
| Método | `menu()` | `exibirMenu()` | Verbo + substantivo descrevem a ação |
| Variável local | `op` | `operacao` | Elimina abreviação |
| Variável local | `t` (em `addManual`) | `texto` | Sem ambiguidade |
| Variável local | `c` (em `addManual`) | `classificacao` | Sem ambiguidade |
| Tipo novo | — | `enum Classificacao` | Substitui magic strings por tipo seguro |

---

## 2. Funções Grandes Quebradas

### 2.1 `analisar()` → 3 funções com responsabilidade única

**Antes:**
```java
public static String analisar(String txt) {
    int score = 0;
    if (!txt.contains("FONTE")) { score = score + 1; }
    if (txt.contains("!!!"))    { score = score + 1; }
    if (txt.contains("URGENTE")){ score = score + 1; }
    if (txt.length() < 10)      { score = score + 1; }
    if (score == 0)      return "confiavel";
    else if (score == 1) return "duvidosa";
    else                 return "falsa";
}
```

**Depois:**
```java
// Calcula a pontuação
private int calcularPontuacaoDeRisco(String texto) { ... }

// Mapeia pontuação para classificação
private Classificacao classificarPorPontuacao(int pontuacao) { ... }

// Ponto de entrada público (orquestra as duas anteriores)
public Classificacao classificarNoticia(String textoNoticia) { ... }
```

### 2.2 `menu()` → 3 funções com responsabilidade única

**Antes:** um único método `menu()` cuidava do loop, exibição de opções e roteamento.

**Depois:**
```java
private void exibirOpcoes()              { ... } // apenas imprime o menu
private boolean processarOpcao(String o) { ... } // apenas roteia a opção
public void exibirMenu()                 { ... } // orquestra loop + leitura
```

---

## 3. Eliminação de Magic Strings

**Antes:** classificações como strings literais espalhadas pelo código:
```java
d.c = "duvidosa";
return "confiavel";
return "duvidosa";
return "falsa";
```

**Depois:** enum `Classificacao` garante tipo-segurança e centralização:
```java
enum Classificacao { CONFIAVEL, DUVIDOSA, FALSA }
```

---

## 4. Eliminação de Magic Number

| Antes | Depois |
|-------|--------|
| `txt.length() < 10` | `texto.length() < LIMITE_TEXTO_CURTO` (constante nomeada) |

---

## 5. Encapsulamento

**Antes:** atributos públicos na classe `D`:
```java
class D {
    String t;
    String c;
}
```

**Depois:** atributos privados com construtor e getters na classe `Noticia`:
```java
public class Noticia {
    private String texto;
    private Classificacao classificacao;

    public Noticia(String texto, Classificacao classificacao) { ... }
    public String getTexto() { ... }
    public Classificacao getClassificacao() { ... }
}
```

---

## 6. Remoção de `static` Desnecessário

**Antes:** todos os métodos e a coleção eram `static`, misturando estado global com lógica:
```java
static ArrayList<D> data = new ArrayList<>();
public static void f(...) { ... }
```

**Depois:** instância única gerencia o estado, tornando o código testável e reutilizável:
```java
private ArrayList<Noticia> listaNoticias = new ArrayList<>();
public void adicionarNoticia(...) { ... }
```
