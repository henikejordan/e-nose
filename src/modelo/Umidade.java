package modelo;

/**
 *
 * @author usuario
 */
public class Umidade extends Sensor {

    @Override
    public String getNome() {
        return "Umidade do Ar";
    }

    @Override
    public String getUnidade() {
        return "%";
    }

}
