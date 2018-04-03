package controle;

import javax.swing.JOptionPane;

/**
 *
 * @author usuario
 */
public final class EditarGases extends javax.swing.JFrame implements Editar {

    private static EditarGases instance = null;
    private final String[] opcInfo = {"MQ-2", "MQ-3", "MQ-4", "MQ-5", "MQ-6", "MQ-7", "MQ-8", "MQ-9", "MQ-135"};
    private final int[] opcIndices = {13, 0, 1, 2, 3, 4, 5, 6, 7};
    private String[] info;
    private int[] indices;
    private final boolean[] estado = new boolean[9];

    /**
     * Creates new form EditarGases
     */
    private EditarGases() {
        initComponents();
        estadoAtual();
        preencheVetores();
    }

    public static EditarGases getInstance() {
        if (instance == null) {
            instance = new EditarGases();
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

        jCheckBoxMq2 = new javax.swing.JCheckBox();
        jCheckBoxMq3 = new javax.swing.JCheckBox();
        jCheckBoxMq4 = new javax.swing.JCheckBox();
        jCheckBoxMq5 = new javax.swing.JCheckBox();
        jCheckBoxMq6 = new javax.swing.JCheckBox();
        jCheckBoxMq7 = new javax.swing.JCheckBox();
        jCheckBoxMq8 = new javax.swing.JCheckBox();
        jCheckBoxMq9 = new javax.swing.JCheckBox();
        jCheckBoxMq135 = new javax.swing.JCheckBox();
        jButtonConfirmar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jIntegerFieldMedia = new util.JIntegerField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jCheckBoxMq2.setSelected(true);
        jCheckBoxMq2.setText("MQ-2");

        jCheckBoxMq3.setSelected(true);
        jCheckBoxMq3.setText("MQ-3");

        jCheckBoxMq4.setSelected(true);
        jCheckBoxMq4.setText("MQ-4");

        jCheckBoxMq5.setSelected(true);
        jCheckBoxMq5.setText("MQ-5");

        jCheckBoxMq6.setSelected(true);
        jCheckBoxMq6.setText("MQ-6");

        jCheckBoxMq7.setSelected(true);
        jCheckBoxMq7.setText("MQ-7");

        jCheckBoxMq8.setSelected(true);
        jCheckBoxMq8.setText("MQ-8");

        jCheckBoxMq9.setSelected(true);
        jCheckBoxMq9.setText("MQ-9");

        jCheckBoxMq135.setSelected(true);
        jCheckBoxMq135.setText("MQ-135");

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBoxMq3)
                                    .addComponent(jCheckBoxMq6)
                                    .addComponent(jCheckBoxMq2)
                                    .addComponent(jCheckBoxMq5)
                                    .addComponent(jCheckBoxMq4))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBoxMq8)
                                    .addComponent(jCheckBoxMq7)
                                    .addComponent(jCheckBoxMq9)
                                    .addComponent(jCheckBoxMq135))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonConfirmar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                                .addComponent(jButtonCancelar)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jIntegerFieldMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxMq2)
                    .addComponent(jCheckBoxMq7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxMq3)
                    .addComponent(jCheckBoxMq8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxMq4)
                    .addComponent(jCheckBoxMq9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxMq5)
                    .addComponent(jCheckBoxMq135))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxMq6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jIntegerFieldMedia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
        return jCheckBoxMq2.isSelected() || jCheckBoxMq3.isSelected() || jCheckBoxMq4.isSelected()
                || jCheckBoxMq5.isSelected() || jCheckBoxMq6.isSelected() || jCheckBoxMq7.isSelected()
                || jCheckBoxMq8.isSelected() || jCheckBoxMq9.isSelected() || jCheckBoxMq135.isSelected();
    }

    @Override
    public void estadoAtual() {
        estado[0] = jCheckBoxMq2.isSelected();
        estado[1] = jCheckBoxMq3.isSelected();
        estado[2] = jCheckBoxMq4.isSelected();
        estado[3] = jCheckBoxMq5.isSelected();
        estado[4] = jCheckBoxMq6.isSelected();
        estado[5] = jCheckBoxMq7.isSelected();
        estado[6] = jCheckBoxMq8.isSelected();
        estado[7] = jCheckBoxMq9.isSelected();
        estado[8] = jCheckBoxMq135.isSelected();
    }

    @Override
    public void estadoAnterior() {
        jCheckBoxMq2.setSelected(estado[0]);
        jCheckBoxMq3.setSelected(estado[1]);
        jCheckBoxMq4.setSelected(estado[2]);
        jCheckBoxMq5.setSelected(estado[3]);
        jCheckBoxMq6.setSelected(estado[4]);
        jCheckBoxMq7.setSelected(estado[5]);
        jCheckBoxMq8.setSelected(estado[6]);
        jCheckBoxMq9.setSelected(estado[7]);
        jCheckBoxMq135.setSelected(estado[8]);
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
    private javax.swing.JCheckBox jCheckBoxMq135;
    private javax.swing.JCheckBox jCheckBoxMq2;
    private javax.swing.JCheckBox jCheckBoxMq3;
    private javax.swing.JCheckBox jCheckBoxMq4;
    private javax.swing.JCheckBox jCheckBoxMq5;
    private javax.swing.JCheckBox jCheckBoxMq6;
    private javax.swing.JCheckBox jCheckBoxMq7;
    private javax.swing.JCheckBox jCheckBoxMq8;
    private javax.swing.JCheckBox jCheckBoxMq9;
    private util.JIntegerField jIntegerFieldMedia;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
