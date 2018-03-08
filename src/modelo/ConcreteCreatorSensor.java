package modelo;

/**
 *
 * @author usuario
 */
public class ConcreteCreatorSensor implements CreatorSensor {

    @Override
    public Sensor factoryMethod(String tipo) {
        switch (tipo) {
            case "Temperatura":
                return new Temperatura();
            case "Pressão Atmosférica":
                return new Pressao();
            case "Umidade do Ar":
                return new Umidade();
            case "Gases":
                return new Gases();
            default:
                return null;
        }
    }
}
