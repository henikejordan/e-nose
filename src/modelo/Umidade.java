package modelo;

/**
 *
 * @author usuario
 */
public class Umidade implements Sensor {

    @Override
    public String getNome() {
        return "Umidade do Ar";
    }

    @Override
    public int[] getIndices() {
        int[] ret = {12};
        return ret;
    }

    @Override
    public String[] getInfo() {
        String[] ret = {"Umidade do Ar"};
        return ret;
    }

    @Override
    public String getUnidade() {
        return "%";
    }

}
