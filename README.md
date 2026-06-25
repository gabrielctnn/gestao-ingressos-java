# 🎟️ Atividade de Fixação: Sistema de Gestão de Ingressos com Java Collections

> 🎯 **Projeto de Estudo:** Código desenvolvido exclusivamente para fins de consolidação prática e domínio do **Java Collections Framework** e da API de **Java Streams**.

Este é um sistema de console desenvolvido para aplicar, em um cenário do mundo real (venda e controle de ingressos), os mecanismos de armazenamento e busca em memória, indo além das listas tradicionais e explorando o potencial das estruturas de chaves e valores.

---

## 🎯 Objetivo do Projeto

O propósito central desta atividade foi exercitar a manipulação avançada de coleções em Java. O foco principal foi entender como utilizar tabelas de espelhamento (`Map`) para buscas rápidas e como realizar a transição dessas estruturas para coleções iteráveis (`Set` via `entrySet`) para aplicar filtros, somas e transformações através de Programação Funcional (Streams).

---

## 🚀 Funcionalidades do Sistema

O sistema simula o gerenciamento de uma bilheteria através de um menu interativo:
1. **Cadastrar Convidado:** Registra o participante mapeando seu CPF diretamente como chave. O valor do ingresso é calculado de forma automatizada.
2. **Buscar Convidado por CPF:** Localização instantânea do cadastro do cliente na memória.
3. **Relatório VIP:** Uma listagem filtrada que exibe a ficha completa apenas de quem adquiriu ingressos do tipo VIP.
4. **Fechamento do Caixa:** Consolida o faturamento total em tempo real e lista o detalhamento de consumo de cada cliente.
5. **Listar Convidados:** Exibe todos os registros atualmente salvos no mapa.

---

## 🛠️ Itens de Collections e Conceitos Aplicados

Para cumprir os requisitos de performance e organização do exercício, foram aplicados:
* **`Map<String, Convidado>` (`HashMap`):** Utilizado para garantir buscas eficientes com complexidade de tempo constante $O(1)$ através do CPF.
* **`entrySet()` e `values()`:** Métodos essenciais do `Map` utilizados para expor os dados em formatos colecionáveis (`Set` e `Collection`), permitindo a abertura de fluxos de dados (Streams).
* **Java Streams (`filter` e `anyMatch`):** Aplicação de pipelines para filtrar dados com base em atributos de objetos internos e validar a existência de elementos sem a necessidade de loops `for` tradicionais.
* **Stream Reduction (`map` e `reduce`):** Extração de atributos específicos (`BigDecimal`) para realizar operações matemáticas de soma em lote no fechamento do caixa.
* **Cláusulas de Guarda (Guard Clauses):** Uso estratégico de estruturas `if` com retornos imediatos (`return`) para validação de mapas vazios, tornando o código limpo e dispensando blocos `else` redundantes.
* **`BigDecimal`:** Uso da classe correta para manipulação de valores monetários, evitando problemas de precisão de ponto flutuante (`double`).

---

## 📊 Regra de Negócio (Precificação dos Ingressos)

O sistema adota um valor base de **R$ 50,00** e aplica acréscimos utilizando herança lógica com um `Enum`:

| Tipo de Ingresso | Regra de Cálculo | Valor Final |
| :--- | :--- | :--- |
| **PISTA** | Valor Base Puro | R$ 50,00 |
| **VIP** | Valor Base + 50% | R$ 75,00 |
| **CAMAROTE** | Valor Base + 100% | R$ 100,00 |

---

## 📁 Estrutura das Classes

```text
ShowManager/
├── src/
│   ├── Convidado.java       # Classe modelo com encapsulamento e regras de negócio
│   ├── Main.java            # Classe principal com o menu de console e gerenciamento do fluxo
│   └── TipoIngresso.java    # Enumeração dos setores disponíveis (PISTA, VIP, CAMAROTE)
├── .gitignore               # Arquivo de configuração para ignorar pastas do IntelliJ (Ex: .idea, out)
└── README.md                # Documentação oficial do projeto

