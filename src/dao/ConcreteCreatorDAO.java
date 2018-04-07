package dao;

/**
 *
 * @author usuario
 */
public class ConcreteCreatorDAO implements CreatorDAO {

    @Override
    public DAO factoryMethod(String tipo, String data_hora_ini, String data_hora_fim) {
        switch (tipo) {
            case "Gases":
                return new DAOGases("gases", data_hora_ini, data_hora_fim);
            case "Pressão Atmosférica":
                return new DAOPressao("pressao", data_hora_ini, data_hora_fim);
            case "Temperatura":
                return new DAOTemperatura("temperatura", data_hora_ini, data_hora_fim);
            case "Umidade do Ar":
                return new DAOUmidade("umidade", data_hora_ini, data_hora_fim);
            default:
                return null;
        }
    }
}
