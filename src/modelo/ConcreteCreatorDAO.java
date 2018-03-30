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
                return new DAOGases("gases");
            case "Pressão Atmosférica":
                return new DAOPressao("pressao");
            case "Temperatura":
                return new DAOTemperatura("temperatura");
            case "Umidade do Ar":
                return new DAOUmidade("umidade");
            default:
                return null;
        }
    }
}
