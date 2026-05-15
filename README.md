# Sistema de Monitoramento de Fake News e Qualidade da Informação

Disciplina: DIM0501 - Boas Práticas de Programação | UFRN | 2026.1

## Descrição

Sistema que permite inserir notícias, classificá-las como **confiável**, **duvidosa** ou **falsa**, e listar as notícias cadastradas. A classificação pode ser feita manualmente pelo usuário ou automaticamente com base em indicadores textuais de desinformação.

## Como Compilar e Executar

**Requisitos:** Java 11 ou superior.

```bash
# Compilar todos os arquivos
javac *.java

# Executar
java Sistema
```

## Estrutura do Projeto

```
projeto/
├── Classificacao.java      — Enum com os níveis de credibilidade (CONFIAVEL, DUVIDOSA, FALSA)
├── Noticia.java            — Modelo de dados de uma notícia
├── SistemaDeNoticias.java  — Lógica de negócio: classificação, cadastro e listagem
└── Sistema.java            — Interface de linha de comando e ponto de entrada (main)
```

## Funcionalidades

| Opção | Ação |
|-------|------|
| 1 | Adicionar notícia com classificação manual |
| 2 | Adicionar notícia com classificação automática |
| 3 | Listar todas as notícias cadastradas |
| 4 | Sair |

## Critérios de Classificação Automática

A pontuação de risco é calculada com base nos seguintes indicadores:

| Indicador | Pontos |
|-----------|--------|
| Ausência de "FONTE" no texto | +1 |
| Presença de "!!!" | +1 |
| Presença de "URGENTE" | +1 |
| Texto com menos de 10 caracteres | +1 |

- 0 pontos → `CONFIAVEL`
- 1 ponto → `DUVIDOSA`
- 2+ pontos → `FALSA`
