public class Professor extends FuncionarioPJ { // extends: quando um classe é filha da outra classe

    public Professor(String nome, double valorHoraAula, double horasSemanais) {
        super(nome, valorHoraAula, horasSemanais, false);
    }

    @Override
    public double calcularBruto() {
        double horasMensais = getHorasTrabalhadas() * 4.5;
        double remuneracaoAulas = getValorHora() * horasMensais;
        double dsr = remuneracaoAulas / 6.0;
        return arredondar(remuneracaoAulas + dsr);
    }
}