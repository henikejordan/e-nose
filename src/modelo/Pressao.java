package modelo;

/**
 *
 * @author usuario
 */
public class Pressao implements Sensor {

    @Override
    public int getId() {
        return 10;
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
