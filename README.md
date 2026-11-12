# Folha de Pagamento em Java

Sistema de folha de pagamento em Java para aplicar os conceitos de Programação Orientada a Objetos (POO), com foco em encapsulamento, construtores, herança e sobrescrita.

## Objetivo do projeto

O projeto simula a gestão de pagamentos de diferentes tipos de funcionários em uma empresa, organizando a lógica em classes e métodos reutilizáveis. A ideia é representar regras de negócio reais de forma clara e prática, mostrando como a orientação a objetos facilita a manutenção, expansão e organização do sistema.

## Estrutura do projeto

A estrutura principal do sistema está na pasta `src/` e contém as seguintes classes:

- `Funcionario.java`
  - Classe base do sistema.
  - Representa um funcionário genérico.
  - Guarda atributos comuns como nome, matrícula, salário-base e demais informações do colaborador.
  - Aplica encapsulamento para proteger os dados com acesso controlado.
  - Possui construtores e métodos reutilizáveis para uso nas subclasses.

- `FuncionarioPJ.java`
  - Classe que herda de `Funcionario`.
  - Representa um trabalhador contratado como Pessoa Jurídica (PJ/MEI).
  - Implementa uma regra específica de cálculo com valor fixo de DAS.
  - Diferencia o regime PJ das regras aplicadas ao CLT.

- `Professor.java`
  - Classe que também herda de `Funcionario`.
  - Representa um professor com cálculo específico por hora-aula.
  - Utiliza sobrescrita para adaptar a lógica de remuneração da classe base.
  - Considera o valor por aula, a quantidade de aulas e o DSR (Descanso Semanal Remunerado).

- `Main.java`
  - Classe principal da aplicação.
  - Responsável por instanciar os objetos, chamar os cálculos e exibir os resultados na tela.
  - Serve como ponto de entrada do programa Java.

## Regras de negócio e cálculos implementados

### 1. Funcionário CLT

O sistema calcula o salário de um colaborador CLT com base em regras de pagamento e tributos:

- INSS calculado de forma progressiva conforme a faixa salarial;
- IRRF calculado sobre a base de cálculo após o desconto do INSS;
- FGTS calculado como 8% do salário bruto;
- salário líquido obtido pela fórmula:

```java
salarioLiquido = salarioBruto - INSS - IRRF;
```

Essas regras demonstram o uso de encapsulamento e organização de lógica em classes, mantendo o cálculo tributário separado da representação dos dados do funcionário.

### 2. Funcionário PJ/MEI

Para o regime PJ/MEI, o cálculo é diferente do modelo CLT:

- considera um valor fixo de DAS;
- não aplica as regras de INSS, IRRF e FGTS do regime CLT;
- representa uma forma simplificada de contratação com tributação fixa.

### 3. Professor

A classe `Professor` possui uma regra de remuneração específica:

- cálculo com base no valor da hora-aula;
- multiplicação pela quantidade de aulas ministradas;
- inclusão do DSR no valor final;
- lógica diferenciada em relação ao CLT e ao PJ.

Essa parte do projeto é um bom exemplo de sobrescrita, pois a classe filha redefine o comportamento de cálculo para atender à regra específica da categoria.

## Como compilar e executar

Abra o terminal na pasta raiz do projeto:

```bash
cd "c:\Users\gabri\Projetos Java\TESTES DE CODS\FOLHADEPAGAMENTO"
```

### Compilar os arquivos Java

```bash
javac -d out src/*.java
```

### Executar o programa

```bash
java -cp out Main
```

Esses comandos geram a pasta `out/` com os arquivos compilados e iniciam a aplicação Java pelo arquivo `Main.java`.

## Observações finais

Este projeto demonstra de forma prática como a POO pode ser usada para modelar regras reais de negócio em Java. O sistema reúne:

- encapsulamento para proteger atributos e controlar acesso;
- construtores para inicialização dos objetos;
- herança para reaproveitamento de código;
- sobrescrita para adaptação de cálculos específicos.



## Ficha de Testes

### Cálculo Manual do Daniel
* **Horas-aula mensais:** 20 × 4,5 = 90 horas
* **Remuneração das aulas:** R$ 50,00 × 90 = R$ 4.500,00
* **DSR didático:** R$ 4.500,00 ÷ 6 = R$ 750,00
* **Bruto:** R$ 4.500,00 + R$ 750,00 = R$ 5.250,00
* **INSS:**
  * Faixa 1: 1.621,00 × 7,5% = 121,575
  * Faixa 2: (2.902,84 - 1.621,00) × 9% = 1.281,84 × 9% = 115,3656
  * Faixa 3: (4.354,27 - 2.902,84) × 12% = 1.451,43 × 12% = 174,1716
  * Faixa 4: (5.250,00 - 4.354,27) × 14% = 895,73 × 14% = 125,4022
  * Soma: 121,575 + 115,3656 + 174,1716 + 125,4022 = 536,5144 → **R$ 536,51**
* **IRRF:**
  * Dedução: maior entre (536,51 e 607,20) = 607,20
  * Base IR: 5.250,00 - 607,20 = 4.642,80 (Faixa 22,5%, dedução 675,49)
  * Imposto inicial: (4.642,80 × 0,225) - 675,49 = 1.044,63 - 675,49 = R$ 369,14
  * Redução: 978,62 - (0,133145 × 5.250,00) = 978,62 - 699,01125 = 279,60875 → R$ 279,61
  * IRRF final: 369,14 - 279,61 = **R$ 89,53**
* **FGTS:** 5.250,00 × 8% = **R$ 420,00**
* **DAS:** **R$ 0,00**
* **Líquido:** 5.250,00 - 536,51 - 89,53 = **R$ 4.623,96**

### Tabela de Evidências

| Teste | Resultado esperado | Resultado obtido | Passou? | Correção realizada |
| :--- | :--- | :--- | :--- | :--- |
| **Objeto Ana** (CLT, 3.000) | Bruto: 3.000,00 \| INSS: 248,60 \| IRRF: 0,00 \| FGTS: 240,00 \| DAS: 0,00 \| Líq: 2.751,40 | Bruto: 3.000,00 \| INSS: 248,60 \| IRRF: 0,00 \| FGTS: 240,00 \| DAS: 0,00 \| Líq: 2.751,40 | Sim | Nenhuma |
| **Objeto Bruno** (CLT, 6.000) | Bruto: 6.000,00 \| INSS: 641,51 \| IRRF: 385,10 \| FGTS: 480,00 \| DAS: 0,00 \| Líq: 4.973,39 | Bruto: 6.000,00 \| INSS: 641,51 \| IRRF: 385,10 \| FGTS: 480,00 \| DAS: 0,00 \| Líq: 4.973,39 | Sim | Nenhuma |
| **Objeto Carla** (PJ, 50/h, 120h) | Bruto: 6.000,00 \| INSS: 0,00 \| IRRF: 0,00 \| FGTS: 0,00 \| DAS: 86,05 \| Líq: 5.913,95 | Bruto: 6.000,00 \| INSS: 0,00 \| IRRF: 0,00 \| FGTS: 0,00 \| DAS: 86,05 \| Líq: 5.913,95 | Sim | Nenhuma |
| **Objeto Daniel** (Prof, 50/h, 20h) | Bruto: 5.250,00 \| INSS: 536,51 \| IRRF: 89,53 \| FGTS: 420,00 \| DAS: 0,00 \| Líq: 4.623,96 | Bruto: 5.250,00 \| INSS: 536,51 \| IRRF: 89,53 \| FGTS: 420,00 \| DAS: 0,00 \| Líq: 4.623,96 | Sim | Nenhuma |
| **Objeto Elisa** (Prof, 100/h, 20h) | Bruto: 10.500,00 \| INSS: 988,09 \| IRRF: 1.707,05 \| FGTS: 840,00 \| DAS: 0,00 \| Líq: 7.804,86 | Bruto: 10.500,00 \| INSS: 988,09 \| IRRF: 1.707,05 \| FGTS: 840,00 \| DAS: 0,00 \| Líq: 7.804,86 | Sim | Nenhuma |
| **1. Valores negativos** | Lançar `IllegalArgumentException` impedindo criação | Exceção lançada com mensagem explicativa | Sim | Nenhuma |
| **2. CLT salário zero** | Todos os valores (Bruto, INSS, IRRF, FGTS, DAS, Líquido) =

## Respostas da Reflexão

1. **Diferença entre classe e objeto:** A classe é o molde ou estrutura que define os atributos (dados) e métodos (comportamentos) que algo terá, como a classe `Funcionario`. O objeto é uma instância concreta criada em memória a partir desse molde com valores próprios, como os objetos `ana` e `bruno`[cite: 3, 6].
2. **Garantia do construtor e ausência de setter no regime:** O construtor garante que o objeto já nasça em um estado válido, obrigando o preenchimento de dados essenciais e bloqueando valores negativos logo na criação[cite: 1, 2, 6]. O atributo `pj` é `final` e não possui *setter* para garantir que o regime tributário de um funcionário seja imutável após a contratação, evitando inconsistências nos cálculos[cite: 1, 2, 6].
3. **FGTS no demonstrativo sem reduzir o líquido:** Pela legislação trabalhista, o FGTS (8%) é uma obrigação financeira exclusiva do empregador, depositada diretamente na conta vinculada do trabalhador[cite: 4, 6]. Ele aparece no demonstrativo apenas para conferência/transparência, mas não é descontado do salário do funcionário como o INSS ou o IRRF[cite: 4, 6].
4. **Método sobrescrito e comportamento no INSS:** O método sobrescrito foi o `calcularBruto()` (em `FuncionarioPJ` e novamente em `Professor`)[cite: 4, 5, 7]. Quando `calcularINSS()` (definido na classe mãe `Funcionario`) é chamado em um objeto `Professor`, o polimorfismo do Java faz com que a chamada interna a `calcularBruto()` execute automaticamente a versão sobrescrita do `Professor` (incluindo horas mensais e DSR), calculando o imposto sobre o valor correto[cite: 5, 6, 7].
5. **Problema de modelagem na herança e sugestão:** Na programação orientada a objetos, a herança representa uma relação *"é um"* (*is-a*). Dizer que um `Professor` (que é CLT) herda de `FuncionarioPJ` viola essa regra conceitual, pois um professor CLT não é um prestador de serviços PJ[cite: 5, 7]. Uma organização melhor seria criar uma classe intermediária abstrata chamada `FuncionarioHorista` (herdando de `Funcionario`) para armazenar `valorHora` e `horasTrabalhadas`, e fazer tanto `FuncionarioPJ` quanto `Professor` herdarem de `FuncionarioHorista`[cite: 4, 5, 7].



