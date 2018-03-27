package modelo;

/**
 *
 * @author usuario
 */
public abstract class Sensor {

    private int[] indices;
    private String[] info;

    public int[] getIndices() {
        return indices;
    }

    public void setIndices(int[] indices) {
        this.indices = indices;
    }

    public String[] getInfo() {
        return info;
    }

    public void setInfo(String[] info) {
        this.info = info;
    }

    public abstract String getNome();

    public abstract String getUnidade();

}
