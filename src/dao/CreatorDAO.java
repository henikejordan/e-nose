package dao;

/**
 *
 * @author usuario
 */
public interface CreatorDAO {

    DAO factoryMethod(String tipo, String data_hora_ini, String data_hora_fim);
}
