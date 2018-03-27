package modelo;

/**
 *
 * @author usuario
 */
public class Temperatura extends Sensor {

    @Override
    public String getNome() {
        return "Temperatura";
    }

    @Override
    public String getUnidade() {
        return "°C";
    }

}
