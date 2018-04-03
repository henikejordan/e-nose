package modelo;

/**
 *
 * @author usuario
 */
public class MediaMovel {

    private final int N;
    private final double[] valor;

    public MediaMovel(int N) {
        this.N = N;
        valor = new double[N];
    }

    public double calcula(double original) {
        for (int i = N - 1; i > 0; i--) {
            valor[i] = valor[i - 1];
        }
        valor[0] = original;

        double total = 0;

        for (int i = 0; i < N; i++) {
            total += valor[i];
        }
        return total / N;
    }

}
