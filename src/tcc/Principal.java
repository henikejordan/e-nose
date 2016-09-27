/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import gnu.io.CommPortIdentifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author henike
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        getRootPane().setDefaultButton(jButtonAbrir);
        this.fillComboBoxPorta();

        MaskFormatter formDia, formHora;
        try {
            formDia = new MaskFormatter("##/##/####");
            formHora = new MaskFormatter("##:##");
            jFormattedTextFieldData.setFormatterFactory(new DefaultFormatterFactory(formDia));
            jFormattedTextFieldDataAte.setFormatterFactory(new DefaultFormatterFactory(formDia));
            jFormattedTextFieldHora.setFormatterFactory(new DefaultFormatterFactory(formHora));
            jFormattedTextFieldHoraAte.setFormatterFactory(new DefaultFormatterFactory(formHora));

            // pega data do sistema
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date hoje = new Date();
            jFormattedTextFieldData.setText(df.format(hoje));
            jFormattedTextFieldDataAte.setText(df.format(hoje));
            jFormattedTextFieldHora.setText("00:00");
            jFormattedTextFieldHoraAte.setText("23:59");
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void fillComboBoxPorta() {
        Enumeration en = CommPortIdentifier.getPortIdentifiers();
        jComboBoxPorta.addItem("");
        while (en.hasMoreElements()) {
            CommPortIdentifier cpi = (CommPortIdentifier) en.nextElement();
            jComboBoxPorta.addItem(cpi.getName());
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
        jLabelDados = new javax.swing.JLabel();
        jCheckBoxTemp = new javax.swing.JCheckBox();
        jCheckBoxLum = new javax.swing.JCheckBox();
        jCheckBoxUmiAr = new javax.swing.JCheckBox();
        jCheckBoxUmiSolo = new javax.swing.JCheckBox();
        jRadioButtonLer = new javax.swing.JRadioButton();
        jRadioButtonRel = new javax.swing.JRadioButton();
        jLabelData = new javax.swing.JLabel();
        jLabelDataAte = new javax.swing.JLabel();
        jLabelHora = new javax.swing.JLabel();
        jLabelHoraAte = new javax.swing.JLabel();
        jFormattedTextFieldData = new javax.swing.JFormattedTextField();
        jFormattedTextFieldDataAte = new javax.swing.JFormattedTextField();
        jFormattedTextFieldHora = new javax.swing.JFormattedTextField();
        jFormattedTextFieldHoraAte = new javax.swing.JFormattedTextField();

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

        jLabelDados.setText("Dados:");

        jCheckBoxTemp.setText("Temperatura");

        jCheckBoxLum.setText("Luminosidade");

        jCheckBoxUmiAr.setText("Umidade do ar");

        jCheckBoxUmiSolo.setText("Umidade do solo");

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

        jLabelData.setText("Data:");

        jLabelDataAte.setText("Até:");

        jLabelHora.setText("Hora:");

        jLabelHoraAte.setText("Até:");

        jFormattedTextFieldData.setEnabled(false);

        jFormattedTextFieldDataAte.setEnabled(false);

        jFormattedTextFieldHora.setEnabled(false);

        jFormattedTextFieldHoraAte.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDados, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelData, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelHora, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxTemp)
                            .addComponent(jCheckBoxUmiAr))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxUmiSolo)
                            .addComponent(jCheckBoxLum)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelPorta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButtonLer)
                                    .addComponent(jButtonAbrir))
                                .addGap(62, 62, 62))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextFieldHora)
                                    .addComponent(jFormattedTextFieldData))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelDataAte, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelHoraAte, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonSair)
                            .addComponent(jRadioButtonRel)
                            .addComponent(jFormattedTextFieldDataAte, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                            .addComponent(jFormattedTextFieldHoraAte))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPorta)
                    .addComponent(jComboBoxPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDados)
                    .addComponent(jCheckBoxTemp)
                    .addComponent(jCheckBoxLum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxUmiAr)
                    .addComponent(jCheckBoxUmiSolo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonLer)
                    .addComponent(jRadioButtonRel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelData)
                    .addComponent(jLabelDataAte)
                    .addComponent(jFormattedTextFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldDataAte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelHora)
                    .addComponent(jLabelHoraAte)
                    .addComponent(jFormattedTextFieldHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldHoraAte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
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
        String info;
        if (jCheckBoxTemp.isSelected()) {
            info = "Temperatura";
            if (!"".equals(port)) {
                if (jRadioButtonLer.isSelected()) {
                    buildChart(port, info);
                } else if (jRadioButtonRel.isSelected()) {
                    showChart(info);
                }
            }
        }
        if (jCheckBoxLum.isSelected()) {
            info = "Luminosidade";
            if (!"".equals(port)) {
                if (jRadioButtonLer.isSelected()) {
                    buildChart(port, info);
                } else if (jRadioButtonRel.isSelected()) {
                    showChart(info);
                }
            }
        }
        if (jCheckBoxUmiAr.isSelected()) {
            info = "Umidade do ar";
            if (!"".equals(port)) {
                if (jRadioButtonLer.isSelected()) {
                    buildChart(port, info);
                } else if (jRadioButtonRel.isSelected()) {
                    showChart(info);
                }
            }
        }
        if (jCheckBoxUmiSolo.isSelected()) {
            info = "Umidade do solo";
            if (!"".equals(port)) {
                if (jRadioButtonLer.isSelected()) {
                    buildChart(port, info);
                } else if (jRadioButtonRel.isSelected()) {
                    showChart(info);
                }
            }
        }
    }//GEN-LAST:event_jButtonAbrirActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jRadioButtonLerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonLerMouseClicked
        jFormattedTextFieldData.setEnabled(false);
        jFormattedTextFieldDataAte.setEnabled(false);
        jFormattedTextFieldHora.setEnabled(false);
        jFormattedTextFieldHoraAte.setEnabled(false);
    }//GEN-LAST:event_jRadioButtonLerMouseClicked

    private void jRadioButtonRelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonRelMouseClicked
        jFormattedTextFieldData.setEnabled(true);
        jFormattedTextFieldDataAte.setEnabled(true);
        jFormattedTextFieldHora.setEnabled(true);
        jFormattedTextFieldHoraAte.setEnabled(true);
    }//GEN-LAST:event_jRadioButtonRelMouseClicked

    private RealTime buildChart(String port, String info) {
        return new RealTime(port, info);
    }

    private LineChart showChart(String info) {
        return new LineChart(info);
    }

    /**
     * @param args the command line arguments
     */
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
    private javax.swing.JButton jButtonSair;
    private javax.swing.JCheckBox jCheckBoxLum;
    private javax.swing.JCheckBox jCheckBoxTemp;
    private javax.swing.JCheckBox jCheckBoxUmiAr;
    private javax.swing.JCheckBox jCheckBoxUmiSolo;
    private javax.swing.JComboBox<String> jComboBoxPorta;
    private javax.swing.JFormattedTextField jFormattedTextFieldData;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataAte;
    private javax.swing.JFormattedTextField jFormattedTextFieldHora;
    private javax.swing.JFormattedTextField jFormattedTextFieldHoraAte;
    private javax.swing.JLabel jLabelDados;
    private javax.swing.JLabel jLabelData;
    private javax.swing.JLabel jLabelDataAte;
    private javax.swing.JLabel jLabelHora;
    private javax.swing.JLabel jLabelHoraAte;
    private javax.swing.JLabel jLabelPorta;
    private javax.swing.JRadioButton jRadioButtonLer;
    private javax.swing.JRadioButton jRadioButtonRel;
    // End of variables declaration//GEN-END:variables
}
