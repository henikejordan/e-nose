package controle;

import javax.swing.JOptionPane;

/**
 *
 * @author usuario
 */
public final class EditarTemperatura extends javax.swing.JFrame implements Editar {

    private static EditarTemperatura instance = null;
    private final String[] opcInfo = {"Temperatura"};
    private final int[] opcIndices = {16};
    private String[] info;
    private int[] indices;
    private final boolean[] estado = new boolean[1];

    /**
     * Creates new form EditarGases
     */
    private EditarTemperatura() {
        initComponents();
        estadoAtual();
        preencheVetores();
    }

    public static EditarTemperatura getInstance() {
        if (instance == null) {
            instance = new EditarTemperatura();
        }
        return instance;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxTemp = new javax.swing.JCheckBox();
        jButtonConfirmar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jIntegerFieldMedia = new modelo.JIntegerField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jCheckBoxTemp.setSelected(true);
        jCheckBoxTemp.setText("Temperatura");

        jButtonConfirmar.setText("Confirmar");
        jButtonConfirmar.setSelected(true);
        jButtonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jLabel1.setText("Média móvel:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBoxTemp)
                .addGap(43, 43, 43))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonConfirmar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar))
                    .addComponent(jLabel1)
                    .addComponent(jIntegerFieldMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jCheckBoxTemp)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jIntegerFieldMedia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConfirmar)
                    .addComponent(jButtonCancelar))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarActionPerformed
        if (!isSelecionado()) {
            JOptionPane.showMessageDialog(null, "Selecione pelo menos uma opção");
            return;
        }
        estadoAtual();
        preencheVetores();
        dispose();
    }//GEN-LAST:event_jButtonConfirmarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        estadoAnterior();
        dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        estadoAnterior();
        dispose();
    }//GEN-LAST:event_formWindowClosing

    @Override
    public boolean isSelecionado() {
        return jCheckBoxTemp.isSelected();
    }

    @Override
    public void estadoAtual() {
        estado[0] = jCheckBoxTemp.isSelected();
    }

    @Override
    public void estadoAnterior() {
        jCheckBoxTemp.setSelected(estado[0]);
    }

    @Override
    public void preencheVetores() {
        int aux = 0;
        for (int i = 0; i < estado.length; i++) {
            if (estado[i]) {
                aux++;
            }
        }
        indices = new int[aux];
        info = new String[aux];
        aux = 0;
        for (int i = 0; i < estado.length; i++) {
            if (estado[i]) {
                info[aux] = opcInfo[i];
                indices[aux] = opcIndices[i];
                aux++;
            }
        }
    }

    @Override
    public String[] getOpcInfo() {
        return opcInfo;
    }

    @Override
    public int[] getOpcIndices() {
        return opcIndices;
    }

    @Override
    public String[] getInfo() {
        return info;
    }

    @Override
    public int[] getIndices() {
        return indices;
    }

    @Override
    public int getMedia() {
        return Integer.parseInt(jIntegerFieldMedia.getText());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JCheckBox jCheckBoxTemp;
    private modelo.JIntegerField jIntegerFieldMedia;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
