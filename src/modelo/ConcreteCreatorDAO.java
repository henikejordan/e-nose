package modelo;

/**
 *
 * @author usuario
 */
public class ConcreteCreatorDAO implements CreatorDAO {

    @Override
    public DAO factoryMethod(String tipo) {
        switch (tipo) {
            case "Gases":
                return new DAOGases();
            case "Pressão Atmosférica":
                return new DAOPressao();
            case "Temperatura":
                return new DAOTemperatura();
            case "Umidade do Ar":
                return new DAOUmidade();
            default:
                return null;
        }
    }
}
