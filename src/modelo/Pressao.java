package modelo;

/**
 *
 * @author usuario
 */
public class Pressao extends Sensor {

    @Override
    public String getNome() {
        return "Pressão Atmosférica";
    }

    @Override
    public String getUnidade() {
        return "Pa";
    }

}
