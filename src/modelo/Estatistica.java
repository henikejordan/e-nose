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
            case "Mediana":
                return mediana();
            case "Média":
                return media();
            case "Média Ponderada":
                return mediaPonderada();
            case "Média Exponencial":
                return mediaExponencial();
            case "Media Sem Extremos":
                return mediaSemExtremos();
            case "Media Sem Desvios":
                return mediaSemDesvios();
            default:
                return mediaPonderadaSemDesvios();
        }
    }

    public void zeraJanela() {
        for (int i = 0; i < N; i++) {
            valor[i] = 0.0;
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

    public double mediana() {
        int primeiro = N / 2;
        if (N == 1) {
            return valor[0];
        }
        double aux[] = Arrays.copyOf(valor, N);
        Arrays.sort(aux);
        return (aux[primeiro] + aux[primeiro + 1]) / 2;
    }

    public double media() {
        double total = 0;
        for (int i = 0; i < N; i++) {
            total += valor[i];
        }
        return total / N;
    }

    public double mediaSemExtremos() {
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

    public double mediaSemDesvios() {
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

    public double mediaPonderada() {
        double total = 0;
        int den = 0;
        for (int i = 0; i < N; i++) {
            total += valor[i] * (N - i);
            den += (N - i);
        }
        return total / den;
    }

    public double mediaPonderadaSemDesvios() {
        double desvioPadrao = desvioPadrao(), media = media(), total = 0;
        int den = 0;

        for (int i = 0; i < N; i++) {
            if (Math.abs(valor[i] - media) < 3 * desvioPadrao) {
                total += valor[i] * (N - i);
                den += (N - i);
            }
        }
        if (den == 0) {
            return valor[0];
        }
        return total / den;
    }

    public double mediaExponencial() {
        double total = 0, alpha = 0.01;
        for (int i = 0; i < N; i++) {
            total += valor[i] * Math.pow(1 - alpha, i);
        }
        return alpha * total;
    }

}
