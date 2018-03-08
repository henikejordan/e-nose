package modelo;

/**
 *
 * @author usuario
 */
public class Gases implements Sensor {

    @Override
    public int getId() {
        return -1;
    }

    @Override
    public String[] getInfo() {
        String[] ret = {"MQ-3", "MQ-4", "MQ-5", "MQ-6", "MQ-7", "MQ-8", "MQ-9", "MQ-135"};
        return ret;
    }

    @Override
    public String getUnidade() {
        return "Analog";
    }

}
