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
                return new DAOGases(data_hora_ini, data_hora_fim);
            case "Pressão Atmosférica":
                return new DAOPressao(data_hora_ini, data_hora_fim);
            case "Poeira 10":
                return new DAOPoeira10(data_hora_ini, data_hora_fim);
            case "Poeira 25":
                return new DAOPoeira25(data_hora_ini, data_hora_fim);
            case "Temperatura":
                return new DAOTemperatura(data_hora_ini, data_hora_fim);
            case "Umidade do Ar":
                return new DAOUmidade(data_hora_ini, data_hora_fim);
            default:
                return null;
        }
    }
}
