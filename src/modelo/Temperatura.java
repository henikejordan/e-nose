package modelo;

/**
 *
 * @author usuario
 */
public class Temperatura implements Sensor {

    @Override
    public String getNome() {
        return "Temperatura";
    }

    @Override
    public int[] getIndices() {
        int[] ret = {8};
        return ret;
    }

    @Override
    public String[] getInfo() {
        String ret[] = {"Temperatura"};
        return ret;
    }

    @Override
    public String getUnidade() {
        return "°C";
    }

}
