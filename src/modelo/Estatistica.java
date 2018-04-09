package modelo;

import java.util.Arrays;

/**
 *
 * @author usuario
 */
public class Estatistica {

    private final int N;
    private final double[] valor;
    private final String opc;

    public Estatistica(int N, String opc) {
        this.N = N;
        valor = new double[N];
        this.opc = opc;
    }

    public double calcula(double original) {
        atualiza(original);
        switch (opc) {
            case "Média":
                return media();
            case "Mediana":
                return mediana();
            case "Retirar Extremos":
                return retiraExtremos();
            default:
                return retiraMaioresDesvios();
        }
    }

    public void atualiza(double original) {
        for (int i = N - 1; i > 0; i--) {
            valor[i] = valor[i - 1];
        }
        valor[0] = original;
    }

    public int maior() {
        double maior = valor[0];
        int index = 0;
        for (int i = 1; i < N; i++) {
            if (valor[i] > maior) {
                maior = valor[i];
                index = i;
            }
        }
        return index;
    }

    public int menor() {
        double menor = valor[0];
        int index = 0;
        for (int i = 1; i < N; i++) {
            if (valor[i] < menor) {
                menor = valor[i];
                index = i;
            }
        }
        return index;
    }

    public double normaliza(double valor, double min, double max) {
        return (valor - min) / (max - min);
    }

    public double desvioPadrao() {
        double media = media(), total = 0;
        for (int i = 0; i < N; i++) {
            total += Math.pow(valor[i] - media, 2);
        }
        return Math.sqrt(total / N);
    }

    public double media() {
        double total = 0;
        for (int i = 0; i < N; i++) {
            total += valor[i];
        }
        return total / N;
    }

    public double mediana() {
        int primeiro = N / 2;
        if (N == 1) {
            return valor[0];
        }
        double aux[] = Arrays.copyOf(valor, N);
        Arrays.sort(aux);
        return (aux[primeiro] + aux[primeiro + 1]) / 2;
    }

    public double retiraExtremos() {
        double total = 0;
        int aux = 0, maior = maior(), menor = menor();

        for (int i = 0; i < N; i++) {
            if (i != maior && i != menor) {
                total += valor[i];
                aux++;
            }
        }
        if (aux == 0) {
            return valor[0];
        }
        return total / aux;
    }

    public double retiraMaioresDesvios() {
        double desvioPadrao = desvioPadrao(), media = media(), total = 0;
        int aux = 0;

        for (int i = 0; i < N; i++) {
            if (Math.abs(valor[i] - media) < 3 * desvioPadrao) {
                total += valor[i];
                aux++;
            }
        }
        if (aux == 0) {
            return valor[0];
        }
        return total / aux;
    }

}
