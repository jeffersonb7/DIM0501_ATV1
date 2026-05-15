# Etapa 1. Identificar Problema

## 1. Legibilidade

| Linhas | Problema | Descrição |
|--------|----------|-----------|
| 1-2 | Imports adequados | Falta estrutura geral e espaçamento visual |
| 5-6, 18, 23, 35-36 | Acesso direto a atributos | Variáveis com nomes de uma letra (`t`, `c`, `op`) |
| 68, 71, 82, 97 | Nomes genéricos | Variáveis de uma letra dificultam compreensão |

---

## 2. Nomes Inadequados

| Linhas | Componente | Problema | Sugestão |
|--------|-----------|----------|----------|
| 4-7 | Classe `D` | Completamente inexpressiva | `Postagem`, `Mensagem`, `Dado` |
| 5-6 | Atributos `t`, `c` | Sem contexto | `texto`, `classificacao` |
| 13-14 | Método `f()` | Sem significado | `adicionarDado()`, `adicionarPostagem()` |
| 32 | Método `func2()` | Genérico | `listarTodos()`, `exibir()` |
| 41 | Método `analisar()` | Muito genérico | `analisarCredibilidade()`, `classificarTexto()` |

---

## 3. Falta de Validação

| Linhas | Problema | Detalhes |
|--------|----------|----------|
| 44 | Validação nula | `txt.contains()` sem verificar se `txt` é nulo |
| 66-78 | Sem tratamento | `addManual()` sem exceção handling |
| 80-86 | Sem tratamento | `addAuto()` sem exceção handling |
| 107-109 | Menu inválido | Entrada inválida sem feedback adequado |

---

## 4. Código Duplicado

| Linhas | Duplicação | Descrição |
|--------|-----------|-----------|
| 67-68, 81-82 | String repetida | `"Digite o texto:"` aparece em dois métodos |
| 73-77, 84-85 | Lógica similar | Ambos chamam `f()` com lógica parecida |
| 21, 60 | Magic string | `"duvidosa"` aparece em múltiplos lugares |
| 66-86 | Métodos similares | `addManual()` e `addAuto()` compartilham estrutura |

---

## 5. Comentários Inadequados ou Inúteis

| Linhas | Comentário | Motivo da Inadequação |
|--------|-----------|----------------------|
| 13 | `// função que faz tudo` | Vago e inútil - não descreve o propósito real |
| 15 | `// adiciona coisa` | Impreciso e sem valor agregado |
| 33 | `// lista tudo` | Óbvio pelo nome da função |
| 115 | `// inicia programa` | Óbvio pelo método `main` |

---

## 6. Code Smells Básicos

### 6.1 Uso Excessivo de `static`
| Linhas | Problema |
|--------|----------|
| 11 | `static ArrayList<D> data` — Mistura dados com lógica |
| 14, 32, 41, 66, 80, 88 | Métodos `static` sem encapsulamento |

**Consequência**: Difícil de testar, impossível reutilizar em múltiplas instâncias

### 6.2 Falta de Encapsulamento
| Linhas | Problema |
|--------|----------|
| 5-6 | Atributos públicos em classe `D` |

**Consequência**: Violação do princípio de encapsulamento; sem getters/setters

### 6.3 Magic Strings
| Linhas | Strings | Contexto |
|--------|---------|---------|
| 21, 44, 47, 50, 58, 60, 62 | `"duvidosa"`, `"confiavel"`, `"falsa"` | Classificações hardcoded |
| 45, 47, 49 | `"FONTE"`, `"!!!"`, `"URGENTE"` | Palavras-chave sem constantes |

### 6.4 Magic Numbers
| Linhas | Número | Problema |
|--------|--------|----------|
| 53 | `10` | `txt.length() < 10` — sem documentação |

---

## 7. Problemas de Organização

| Linhas | Problema | Descrição |
|--------|----------|-----------|
| 9-119 | Responsabilidade única violada | Toda lógica em uma classe |
| 4-7 | Classe modelo deficiente | Sem construtores, getters, setters |
| 11 | Dados e lógica misturados | ArrayList estática viola separação de conceitos |
| 88-109 | Interface acoplada à lógica | Método `menu()` mistura UI com negócio |

---