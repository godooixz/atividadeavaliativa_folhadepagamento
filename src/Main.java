public class Main {
    public static void main(String[] args) {
        System.out.println("=== DEMONSTRATIVO DE PAGAMENTO (5 OBJETOS PRINCIPAIS) ===");
        Funcionario ana = new Funcionario("Ana", 3000.00);
        Funcionario bruno = new Funcionario("Bruno", 6000.00);
        FuncionarioPJ carla = new FuncionarioPJ("Carla", 50.00, 120);
        Professor daniel = new Professor("Daniel", 50.00, 20);
        Professor elisa = new Professor("Elisa", 100.00, 20);

        exibirDemonstrativo(ana);
        exibirDemonstrativo(bruno);
        exibirDemonstrativo(carla);
        exibirDemonstrativo(daniel);
        exibirDemonstrativo(elisa);

        System.out.println("\n=== BATERIA DE TESTES OBRIGATÓRIOS ===");

        // teste 1: valores negativos
        System.out.println("\n[Teste 1] Rejeição de valores negativos:");
        testarExcecao(() -> new Funcionario("Teste", -100.00), "Salário negativo");
        testarExcecao(() -> new FuncionarioPJ("Teste", -50.00, 10), "Valor hora negativo");
        testarExcecao(() -> new FuncionarioPJ("Teste", 50.00, -10), "Horas trabalhadas negativas");

        // teste 2: CLT com salário zero
        System.out.println("\n[Teste 2] CLT com salário zero:");
        Funcionario cltZero = new Funcionario("CLT Zero", 0.00);
        exibirDemonstrativo(cltZero);

        // teste 3: PJ com hora 50 e zero horas
        System.out.println("\n[Teste 3] PJ com hora 50 e zero horas:");
        FuncionarioPJ pjZeroHoras = new FuncionarioPJ("PJ Zero Horas", 50.00, 0);
        exibirDemonstrativo(pjZeroHoras);

        // teste 4: teto do INSS (8.000, 10.500 e 12.000)
        System.out.println("\n[Teste 4] Teto do INSS (8.000 vs 10.500 vs 12.000):");
        Funcionario clt8k = new Funcionario("CLT 8k", 8000.00);
        Funcionario clt10k5 = new Funcionario("CLT 10.5k", 10500.00);
        Funcionario clt12k = new Funcionario("CLT 12k", 12000.00);
        System.out.printf("8.000,00 -> INSS: R$ %.2f%n", clt8k.calcularINSS());
        System.out.printf("10.500,00 -> INSS: R$ %.2f%n", clt10k5.calcularINSS());
        System.out.printf("12.000,00 -> INSS: R$ %.2f%n", clt12k.calcularINSS());

        // teste 5: isenção (5.000) e redução parcial (6.000) de IRRF
        System.out.println("\n[Teste 5] Isenção (5.000) e Redução Parcial (6.000) de IRRF:");
        Funcionario clt5k = new Funcionario("CLT 5k", 5000.00);
        System.out.printf("5.000,00 -> IRRF: R$ %.2f%n", clt5k.calcularIRRF());
        System.out.printf("6.000,00 -> IRRF: R$ %.2f%n", bruno.calcularIRRF());

        // teste 6: idempotência de calcularLiquido()
        System.out.println("\n[Teste 6] Chamar calcularLiquido() duas vezes no mesmo objeto (Bruno):");
        System.out.printf("1ª chamada: R$ %.2f | Bruto: R$ %.2f%n", bruno.calcularLiquido(), bruno.calcularBruto());
        System.out.printf("2ª chamada: R$ %.2f | Bruto: R$ %.2f%n", bruno.calcularLiquido(), bruno.calcularBruto());

        // teste 7: independência de objetos (Ana após Bruno)
        System.out.println("\n[Teste 7] Recalcular Ana depois de criar Bruno:");
        exibirDemonstrativo(ana);
    }

    private static void exibirDemonstrativo(Funcionario f) {
        String regime = f.isPJ() ? "PJ" : "CLT";
        System.out.printf(
            "Nome: %-13s | Regime: %-3s | Bruto: R$ %8.2f | INSS: R$ %6.2f | IRRF: R$ %7.2f | FGTS: R$ %6.2f | DAS: R$ %5.2f | Líquido: R$ %8.2f%n",
            f.getNome(), regime, f.calcularBruto(), f.calcularINSS(), f.calcularIRRF(), f.calcularFGTS(), f.calcularMEI(), f.calcularLiquido()
        );
    }

    private static void testarExcecao(Runnable acao, String descricao) {
        try {
            acao.run();
            System.out.println("FALHOU: " + descricao + " não lançou exceção.");
        } catch (IllegalArgumentException e) {
            System.out.println("PASSOU (" + descricao + "): " + e.getMessage());
        }
    }
}