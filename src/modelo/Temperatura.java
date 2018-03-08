package modelo;

/**
 *
 * @author usuario
 */
public class Temperatura implements Sensor {

    @Override
    public int getId() {
        return 8;
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
