package controle;

/**
 *
 * @author usuario
 */
public interface Editar {

    boolean isSelecionado();

    void estadoAtual();

    void estadoAnterior();

    void preencheVetores();

    String[] getOpcInfo();

    int[] getOpcIndices();

    String[] getInfo();

    int[] getIndices();

    int getMedia();

}
