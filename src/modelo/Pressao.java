package modelo;

/**
 *
 * @author usuario
 */
public class Pressao implements Sensor {

    @Override
    public String getNome() {
        return "Pressão Atmosférica";
    }

    @Override
    public int[] getIndices() {
        int[] ret = {10};
        return ret;
    }

    @Override
    public String[] getInfo() {
        String[] ret = {"Pressão Atmosférica"};
        return ret;
    }

    @Override
    public String getUnidade() {
        return "Atm";
    }

}
