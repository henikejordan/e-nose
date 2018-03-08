package modelo;

/**
 *
 * @author usuario
 */
public class Umidade implements Sensor {

    @Override
    public int getId() {
        return 12;
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
