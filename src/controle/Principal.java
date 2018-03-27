package controle;

import gnu.io.CommPortIdentifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import modelo.ConcreteCreatorSensor;
import modelo.Sensor;

/**
 *
 * @author Henike
 */
public class Principal extends javax.swing.JFrame {

    private EditarGases editarGases = EditarGases.getInstance();
    private EditarPressao editarPressao = EditarPressao.getInstance();
    private EditarTemperatura editarTemperatura = EditarTemperatura.getInstance();
    private EditarUmidade editarUmidade = EditarUmidade.getInstance();

    public Principal() {
        initComponents();
        getRootPane().setDefaultButton(jButtonAbrir);
        this.fillComboBoxPorta();

        MaskFormatter formDia, formHora;
        try {
            formDia = new MaskFormatter("##/##/####");
            formHora = new MaskFormatter("##:##");
            jFormattedTextFieldDataIni.setFormatterFactory(new DefaultFormatterFactory(formDia));
            jFormattedTextFieldDataFim.setFormatterFactory(new DefaultFormatterFactory(formDia));
            jFormattedTextFieldHoraIni.setFormatterFactory(new DefaultFormatterFactory(formHora));
            jFormattedTextFieldHoraFim.setFormatterFactory(new DefaultFormatterFactory(formHora));

            // pega data do sistema
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date hoje = new Date();
            jFormattedTextFieldDataIni.setText(df.format(hoje));
            jFormattedTextFieldDataFim.setText(df.format(hoje));
            jFormattedTextFieldHoraIni.setText("00:00");
            jFormattedTextFieldHoraFim.setText("23:59");
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void fillComboBoxPorta() {
        Enumeration en = CommPortIdentifier.getPortIdentifiers();
        jComboBoxPorta.addItem("");
        while (en.hasMoreElements()) {
            CommPortIdentifier cpi = (CommPortIdentifier) en.nextElement();
            if (cpi.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                jComboBoxPorta.addItem(cpi.getName());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBotoes = new javax.swing.ButtonGroup();
        jLabelPorta = new javax.swing.JLabel();
        jButtonAbrir = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();
        jComboBoxPorta = new javax.swing.JComboBox<>();
        jRadioButtonLer = new javax.swing.JRadioButton();
        jRadioButtonRel = new javax.swing.JRadioButton();
        jLabelDataIni = new javax.swing.JLabel();
        jLabelHoraIni = new javax.swing.JLabel();
        jLabelDataFim = new javax.swing.JLabel();
        jLabelHoraFim = new javax.swing.JLabel();
        jFormattedTextFieldDataIni = new javax.swing.JFormattedTextField();
        jFormattedTextFieldDataFim = new javax.swing.JFormattedTextField();
        jFormattedTextFieldHoraIni = new javax.swing.JFormattedTextField();
        jFormattedTextFieldHoraFim = new javax.swing.JFormattedTextField();
        jLabelTempo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldTempo = new javax.swing.JTextField();
        jCheckBoxGases = new javax.swing.JCheckBox();
        jCheckBoxTemp = new javax.swing.JCheckBox();
        jCheckBoxUmidade = new javax.swing.JCheckBox();
        jCheckBoxPressao = new javax.swing.JCheckBox();
        jButtonEditarGases = new javax.swing.JButton();
        jButtonEditarTemp = new javax.swing.JButton();
        jButtonEditarPressao = new javax.swing.JButton();
        jButtonEditarUmidade = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Real Time");
        setResizable(false);

        jLabelPorta.setText("Porta Serial:");

        jButtonAbrir.setText("Abrir");
        jButtonAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAbrirActionPerformed(evt);
            }
        });

        jButtonSair.setText("Sair");
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        grupoBotoes.add(jRadioButtonLer);
        jRadioButtonLer.setText("Ler");
        jRadioButtonLer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonLerMouseClicked(evt);
            }
        });

        grupoBotoes.add(jRadioButtonRel);
        jRadioButtonRel.setText("Relatório");
        jRadioButtonRel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonRelMouseClicked(evt);
            }
        });

        jLabelDataIni.setText("Data inicial:");

        jLabelHoraIni.setText("Hora inicial:");

        jLabelDataFim.setText("Data final:");

        jLabelHoraFim.setText("Hora final:");

        jFormattedTextFieldDataIni.setEnabled(false);

        jFormattedTextFieldDataFim.setEnabled(false);

        jFormattedTextFieldHoraIni.setEnabled(false);

        jFormattedTextFieldHoraFim.setEnabled(false);

        jLabelTempo.setText("Tempo de atualização:");

        jLabel1.setText("segundos");

        jTextFieldTempo.setEnabled(false);
        jTextFieldTempo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldTempoKeyTyped(evt);
            }
        });

        jCheckBoxGases.setText("Gases");

        jCheckBoxTemp.setText("Temperatura");

        jCheckBoxUmidade.setText("Umidade");

        jCheckBoxPressao.setText("Pressão");

        jButtonEditarGases.setText("+");
        jButtonEditarGases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarGasesActionPerformed(evt);
            }
        });

        jButtonEditarTemp.setText("+");
        jButtonEditarTemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarTempActionPerformed(evt);
            }
        });

        jButtonEditarPressao.setText("+");
        jButtonEditarPressao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarPressaoActionPerformed(evt);
            }
        });

        jButtonEditarUmidade.setText("+");
        jButtonEditarUmidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarUmidadeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDataIni)
                            .addComponent(jLabelDataFim))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextFieldDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextFieldDataIni, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelHoraIni)
                                    .addComponent(jLabelHoraFim))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jFormattedTextFieldHoraIni)
                                    .addComponent(jFormattedTextFieldHoraFim, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jButtonAbrir)
                                .addGap(74, 74, 74)
                                .addComponent(jButtonSair))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jRadioButtonLer)
                                        .addGap(42, 42, 42))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCheckBoxUmidade)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonEditarUmidade)
                                        .addGap(30, 30, 30))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jCheckBoxGases)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonEditarGases)
                                        .addGap(41, 41, 41)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCheckBoxPressao)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonEditarPressao))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jCheckBoxTemp)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonEditarTemp))
                                    .addComponent(jRadioButtonRel)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelTempo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)))))
                .addGap(0, 50, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(jLabelPorta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPorta)
                    .addComponent(jComboBoxPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxGases)
                    .addComponent(jCheckBoxTemp)
                    .addComponent(jButtonEditarGases)
                    .addComponent(jButtonEditarTemp))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxUmidade)
                    .addComponent(jCheckBoxPressao)
                    .addComponent(jButtonEditarPressao)
                    .addComponent(jButtonEditarUmidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonLer)
                    .addComponent(jRadioButtonRel))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTempo)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldTempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelHoraIni)
                    .addComponent(jFormattedTextFieldHoraIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDataIni)
                    .addComponent(jFormattedTextFieldDataIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelHoraFim)
                    .addComponent(jFormattedTextFieldHoraFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDataFim))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAbrir)
                    .addComponent(jButtonSair))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbrirActionPerformed
        String port = (String) jComboBoxPorta.getSelectedItem();
        Sensor sensor;
        if (jCheckBoxGases.isSelected()) {
            sensor = new ConcreteCreatorSensor().factoryMethod("Gases");
            if (!"".equals(port) && jRadioButtonLer.isSelected()) {
                sensor.setIndices(editarGases.getOpcIndices());
                sensor.setInfo(editarGases.getOpcInfo());
                buildChart(sensor, port);
            } else if (jRadioButtonRel.isSelected()) {
                sensor.setIndices(editarGases.getIndices());
                sensor.setInfo(editarGases.getInfo());
                showChart(sensor);
            }
        }
        if (jCheckBoxPressao.isSelected()) {
            sensor = new ConcreteCreatorSensor().factoryMethod("Pressão Atmosférica");
            if (!"".equals(port) && jRadioButtonLer.isSelected()) {
                sensor.setIndices(editarPressao.getOpcIndices());
                sensor.setInfo(editarPressao.getOpcInfo());
                buildChart(sensor, port);
            } else if (jRadioButtonRel.isSelected()) {
                sensor.setIndices(editarPressao.getIndices());
                sensor.setInfo(editarPressao.getInfo());
                showChart(sensor);
            }
        }
        if (jCheckBoxTemp.isSelected()) {
            sensor = new ConcreteCreatorSensor().factoryMethod("Temperatura");
            if (!"".equals(port) && jRadioButtonLer.isSelected()) {
                sensor.setIndices(editarTemperatura.getOpcIndices());
                sensor.setInfo(editarTemperatura.getOpcInfo());
                buildChart(sensor, port);
            } else if (jRadioButtonRel.isSelected()) {
                sensor.setIndices(editarTemperatura.getIndices());
                sensor.setInfo(editarTemperatura.getInfo());
                showChart(sensor);
            }
        }
        if (jCheckBoxUmidade.isSelected()) {
            sensor = new ConcreteCreatorSensor().factoryMethod("Umidade do Ar");
            if (!"".equals(port) && jRadioButtonLer.isSelected()) {
                sensor.setIndices(editarUmidade.getOpcIndices());
                sensor.setInfo(editarUmidade.getOpcInfo());
                buildChart(sensor, port);
            } else if (jRadioButtonRel.isSelected()) {
                sensor.setIndices(editarUmidade.getIndices());
                sensor.setInfo(editarUmidade.getInfo());
                showChart(sensor);
            }
        }
    }//GEN-LAST:event_jButtonAbrirActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jRadioButtonLerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonLerMouseClicked
        jTextFieldTempo.setEnabled(true);
        jFormattedTextFieldDataIni.setEnabled(false);
        jFormattedTextFieldDataFim.setEnabled(false);
        jFormattedTextFieldHoraIni.setEnabled(false);
        jFormattedTextFieldHoraFim.setEnabled(false);
    }//GEN-LAST:event_jRadioButtonLerMouseClicked

    private void jRadioButtonRelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonRelMouseClicked
        jTextFieldTempo.setEnabled(false);
        jFormattedTextFieldDataIni.setEnabled(true);
        jFormattedTextFieldDataFim.setEnabled(true);
        jFormattedTextFieldHoraIni.setEnabled(true);
        jFormattedTextFieldHoraFim.setEnabled(true);
    }//GEN-LAST:event_jRadioButtonRelMouseClicked

    private void jTextFieldTempoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTempoKeyTyped
        try {
            if (jTextFieldTempo.getText().length() >= 5) {
                evt.consume();
            } else if (jTextFieldTempo.getText().length() == 0 && Long.parseLong(String.valueOf(evt.getKeyChar())) == 0) {
                evt.consume();
            }
        } catch (NumberFormatException ex) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldTempoKeyTyped

    private void jButtonEditarGasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarGasesActionPerformed
        editarGases.setVisible(true);
    }//GEN-LAST:event_jButtonEditarGasesActionPerformed

    private void jButtonEditarTempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarTempActionPerformed
        editarTemperatura.setVisible(true);
    }//GEN-LAST:event_jButtonEditarTempActionPerformed

    private void jButtonEditarUmidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarUmidadeActionPerformed
        editarUmidade.setVisible(true);
    }//GEN-LAST:event_jButtonEditarUmidadeActionPerformed

    private void jButtonEditarPressaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarPressaoActionPerformed
        editarPressao.setVisible(true);
    }//GEN-LAST:event_jButtonEditarPressaoActionPerformed

    private GravacaoChart buildChart(Sensor sensor, String port) {
        try {
            long tempo = Long.parseLong(jTextFieldTempo.getText());
            return new GravacaoChart(sensor, port, tempo);
        } catch (NumberFormatException ex) {
            ex.getMessage();
        }
        return null;
    }

    private LeituraChart showChart(Sensor sensor) {
        String dataHoraIni, dataHoraFim;
        try {
            SimpleDateFormat formatoAtual = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            SimpleDateFormat formatoDesejado = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dataHoraIni = formatoDesejado.format(formatoAtual.parse(jFormattedTextFieldDataIni.getText() + " " + jFormattedTextFieldHoraIni.getText()));
            dataHoraFim = formatoDesejado.format(formatoAtual.parse(jFormattedTextFieldDataFim.getText() + " " + jFormattedTextFieldHoraFim.getText()));

            return new LeituraChart(sensor, dataHoraIni, dataHoraFim);
        } catch (ParseException ex) {
            ex.getMessage();
        }
        return null;
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Principal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup grupoBotoes;
    private javax.swing.JButton jButtonAbrir;
    private javax.swing.JButton jButtonEditarGases;
    private javax.swing.JButton jButtonEditarPressao;
    private javax.swing.JButton jButtonEditarTemp;
    private javax.swing.JButton jButtonEditarUmidade;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JCheckBox jCheckBoxGases;
    private javax.swing.JCheckBox jCheckBoxPressao;
    private javax.swing.JCheckBox jCheckBoxTemp;
    private javax.swing.JCheckBox jCheckBoxUmidade;
    private javax.swing.JComboBox<String> jComboBoxPorta;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataFim;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataIni;
    private javax.swing.JFormattedTextField jFormattedTextFieldHoraFim;
    private javax.swing.JFormattedTextField jFormattedTextFieldHoraIni;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelDataFim;
    private javax.swing.JLabel jLabelDataIni;
    private javax.swing.JLabel jLabelHoraFim;
    private javax.swing.JLabel jLabelHoraIni;
    private javax.swing.JLabel jLabelPorta;
    private javax.swing.JLabel jLabelTempo;
    private javax.swing.JRadioButton jRadioButtonLer;
    private javax.swing.JRadioButton jRadioButtonRel;
    private javax.swing.JTextField jTextFieldTempo;
    // End of variables declaration//GEN-END:variables
}
