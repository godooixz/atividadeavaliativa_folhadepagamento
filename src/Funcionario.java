public class Funcionario {
    private String nome;
    private double salarioMensal;
    private final boolean pj;

    public Funcionario(String nome, double salarioMensal) {
        this(nome, salarioMensal, false);
    }

    protected Funcionario(String nome, double salarioMensal, boolean pj) {
        if (nome == null || nome.isBlank() || salarioMensal < 0) {
            throw new IllegalArgumentException("Nome e salário inválidos.");
        }
        this.nome = nome;
        this.salarioMensal = salarioMensal;
        this.pj = pj;
    }

    public String getNome() {
        return nome;
    }

    public double getSalarioMensal() {
        return salarioMensal;
    }

    public boolean isPJ() {
        return pj;
    }

    protected double arredondar(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }

    public double calcularBruto() {
        return salarioMensal;
    }

    public double calcularINSS() {
        if (isPJ()) {
            return 0.0;
        }
        double bruto = calcularBruto();
        if (bruto <= 0) {
            return 0.0;
        }

        double totalINSS = 0.0;

        
        double faixa1 = Math.min(bruto, 1621.00); // faixa 1: até 1.621,00 (7,5%)
        totalINSS += faixa1 * 0.075;

        
        if (bruto > 1621.00) { // faixa 2: acima de 1.621,00 até 2.902,84 (9%)
            double faixa2 = Math.min(bruto, 2902.84) - 1621.00;
            totalINSS += faixa2 * 0.09;
        }

        
        if (bruto > 2902.84) { // faixa 3: acima de 2.902,84 até 4.354,27 (12%)
            double faixa3 = Math.min(bruto, 4354.27) - 2902.84;
            totalINSS += faixa3 * 0.12;
        }

        
        if (bruto > 4354.27) { // faixa 4: acima de 4.354,27 até 8.475,55 (14%)
            double faixa4 = Math.min(bruto, 8475.55) - 4354.27;
            totalINSS += faixa4 * 0.14;
        }

        return arredondar(totalINSS);
    }

    public double calcularIRRF() {
        if (isPJ()) {
            return 0.0;
        }
        double bruto = calcularBruto();
        if (bruto <= 5000.00) {
            return 0.0;
        }

        double inss = calcularINSS();
        double deducao = Math.max(inss, 607.20);
        double baseIR = Math.max(0.0, bruto - deducao);

        double impostoInicial = 0.0;
        if (baseIR > 4664.68) {
            impostoInicial = (baseIR * 0.275) - 908.73;
        } else if (baseIR > 3751.05) {
            impostoInicial = (baseIR * 0.225) - 675.49;
        } else if (baseIR > 2826.65) {
            impostoInicial = (baseIR * 0.15) - 394.16;
        } else if (baseIR > 2428.80) {
            impostoInicial = (baseIR * 0.075) - 182.16;
        }

        impostoInicial = arredondar(Math.max(0.0, impostoInicial));

        double reducao = 0.0;
        if (bruto > 5000.00 && bruto <= 7350.00) {
            reducao = arredondar(Math.max(0.0, 978.62 - (0.133145 * bruto)));
        }

        return arredondar(Math.max(0.0, impostoInicial - reducao));
    }

    public double calcularFGTS() {
        if (isPJ()) {
            return 0.0;
        }
        return arredondar(calcularBruto() * 0.08);
    }

    public double calcularMEI() {
        if (isPJ()) {
            return arredondar(86.05);
        }
        return 0.0;
    }

    public double calcularLiquido() {
        double bruto = calcularBruto();
        if (isPJ()) {
            return arredondar(bruto - calcularMEI());
        }
        return arredondar(bruto - calcularINSS() - calcularIRRF());
    }
}