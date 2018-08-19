package modelo;

/**
 *
 * @author usuario
 */
public class ConcreteCreatorSensor implements CreatorSensor {

    @Override
    public Sensor factoryMethod(String tipo) {
        switch (tipo) {
            case "Gases":
                return new Gases();
            case "Pressão Atmosférica":
                return new Pressao();
            case "Poeira 10":
                return new Poeira10();
            case "Poeira 25":
                return new Poeira25();
            case "Temperatura":
                return new Temperatura();
            case "Umidade do Ar":
                return new Umidade();
            default:
                return null;
        }
    }
}
