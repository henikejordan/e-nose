package modelo;

/**
 *
 * @author usuario
 */
public class Gases implements Sensor {

    @Override
    public String getNome() {
        return "Gases";
    }

    @Override
    public int[] getIndices() {
        int[] ret = {13, 0, 1, 2, 3, 4, 5, 6, 7};
        return ret;
    }

    @Override
    public String[] getInfo() {
        String[] ret = {"MQ-2", "MQ-3", "MQ-4", "MQ-5", "MQ-6", "MQ-7", "MQ-8", "MQ-9", "MQ-135"};
        return ret;
    }

    @Override
    public String getUnidade() {
        return "Analog";
    }

}
