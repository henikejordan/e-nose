package controle;

import gnu.io.CommPortIdentifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import dao.ConcreteCreatorDAO;
import modelo.ConcreteCreatorSensor;
import dao.DAO;
import modelo.Estatistica;
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
    private EditarPoeira10 editarPoeira10 = EditarPoeira10.getInstance();
    private EditarPoeira25 editarPoeira25 = EditarPoeira25.getInstance();
    private GravacaoChart gravacaoChart[] = new GravacaoChart[4];
    private LeituraChart leituraChart[] = new LeituraChart[4];

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
        jLabelSegundos = new javax.swing.JLabel();
        jCheckBoxGases = new javax.swing.JCheckBox();
        jCheckBoxTemp = new javax.swing.JCheckBox();
        jCheckBoxUmidade = new javax.swing.JCheckBox();
        jCheckBoxPressao = new javax.swing.JCheckBox();
        jButtonEditarGases = new javax.swing.JButton();
        jButtonEditarTemp = new javax.swing.JButton();
        jButtonEditarPressao = new javax.swing.JButton();
        jButtonEditarUmidade = new javax.swing.JButton();
        jIntegerFieldTempo = new modelo.JIntegerField();
        jTextFieldClasse = new javax.swing.JTextField();
        jLabelClasse = new javax.swing.JLabel();
        jButtonEditarPoeira10 = new javax.swing.JButton();
        jButtonEditarPoeira25 = new javax.swing.JButton();
        jCheckBoxPoeira10 = new javax.swing.JCheckBox();
        jCheckBoxPoeira25 = new javax.swing.JCheckBox();

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

        jLabelSegundos.setText("segundos");

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

        jTextFieldClasse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldClasseKeyReleased(evt);
            }
        });

        jLabelClasse.setText("Classe:");

        jButtonEditarPoeira10.setText("+");
        jButtonEditarPoeira10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarPoeira10ActionPerformed(evt);
            }
        });

        jButtonEditarPoeira25.setText("+");
        jButtonEditarPoeira25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarPoeira25ActionPerformed(evt);
            }
        });

        jCheckBoxPoeira10.setText("Poeira 10 PM");

        jCheckBoxPoeira25.setText("Poeira 25 PM");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jCheckBoxGases, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jCheckBoxUmidade))
                            .addComponent(jCheckBoxPoeira10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonEditarPoeira10)
                            .addComponent(jButtonEditarUmidade)
                            .addComponent(jButtonEditarGases))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
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
                                        .addComponent(jLabelTempo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jIntegerFieldTempo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabelSegundos))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(130, 130, 130)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jCheckBoxPoeira25)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonEditarPoeira25))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jCheckBoxTemp)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jButtonEditarTemp)
                                                    .addGap(0, 0, Short.MAX_VALUE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jCheckBoxPressao)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jButtonEditarPressao)))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jRadioButtonLer)
                                                .addGap(42, 42, 42)
                                                .addComponent(jRadioButtonRel))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabelClasse)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextFieldClasse, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addGap(0, 50, Short.MAX_VALUE))))
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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEditarPoeira10)
                    .addComponent(jButtonEditarPoeira25)
                    .addComponent(jCheckBoxPoeira10)
                    .addComponent(jCheckBoxPoeira25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonLer)
                    .addComponent(jRadioButtonRel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldClasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelClasse))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTempo)
                    .addComponent(jLabelSegundos)
                    .addComponent(jIntegerFieldTempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        String port = (String) jComboBoxPorta.getSelectedItem(), dataHoraIni, dataHoraFim;
        long tempo;
        Sensor sensor;
        DAO dao;
        Estatistica estatistica;
        try {
            SimpleDateFormat formatoAtual = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            SimpleDateFormat formatoDesejado = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dataHoraIni = formatoDesejado.format(formatoAtual.parse(jFormattedTextFieldDataIni.getText() + " " + jFormattedTextFieldHoraIni.getText()));
            dataHoraFim = formatoDesejado.format(formatoAtual.parse(jFormattedTextFieldDataFim.getText() + " " + jFormattedTextFieldHoraFim.getText()));
            tempo = Long.parseLong(jIntegerFieldTempo.getText());
        } catch (ParseException | NumberFormatException ex) {
            ex.getMessage();
            return;
        }
        if (jCheckBoxGases.isSelected()) {
            sensor = new ConcreteCreatorSensor().factoryMethod("Gases");
            dao = new ConcreteCreatorDAO().factoryMethod("Gases", dataHoraIni, dataHoraFim);
            estatistica = new Estatistica(editarGases.getMedia(), "Media Sem Desvios");
            if (!"".equals(port) && jRadioButtonLer.isSelected()) {
                sensor.setIndices(editarGases.getOpcIndices());
                sensor.setInfo(editarGases.getOpcInfo());
                gravacaoChart[0] = new GravacaoChart(sensor, port, tempo, dao, estatistica);
            } else if (jRadioButtonRel.isSelected()) {
                sensor.setIndices(editarGases.getIndices());
                sensor.setInfo(editarGases.getInfo());
                leituraChart[0] = new LeituraChart(sensor, dao, estatistica);
            }
        }
        if (jCheckBoxPressao.isSelected()) {
            sensor = new ConcreteCreatorSensor().factoryMethod("Pressão Atmosférica");
            dao = new ConcreteCreatorDAO().factoryMethod("Pressão Atmosférica", dataHoraIni, dataHoraFim);
            estatistica = new Estatistica(editarPressao.getMedia(), "Media Sem Desvios");
            if (!"".equals(port) && jRadioButtonLer.isSelected()) {
                sensor.setIndices(editarPressao.getOpcIndices());
                sensor.setInfo(editarPressao.getOpcInfo());
                gravacaoChart[1] = new GravacaoChart(sensor, port, tempo, dao, estatistica);
            } else if (jRadioButtonRel.isSelected()) {
                sensor.setIndices(editarPressao.getIndices());
                sensor.setInfo(editarPressao.getInfo());
                leituraChart[1] = new LeituraChart(sensor, dao, estatistica);
            }
        }
        if (jCheckBoxTemp.isSelected()) {
            sensor = new ConcreteCreatorSensor().factoryMethod("Temperatura");
            dao = new ConcreteCreatorDAO().factoryMethod("Temperatura", dataHoraIni, dataHoraFim);
            estatistica = new Estatistica(editarTemperatura.getMedia(), "Media Sem Desvios");
            if (!"".equals(port) && jRadioButtonLer.isSelected()) {
                sensor.setIndices(editarTemperatura.getOpcIndices());
                sensor.setInfo(editarTemperatura.getOpcInfo());
                gravacaoChart[2] = new GravacaoChart(sensor, port, tempo, dao, estatistica);
            } else if (jRadioButtonRel.isSelected()) {
                sensor.setIndices(editarTemperatura.getIndices());
                sensor.setInfo(editarTemperatura.getInfo());
                leituraChart[2] = new LeituraChart(sensor, dao, estatistica);
            }
        }
        if (jCheckBoxUmidade.isSelected()) {
            sensor = new ConcreteCreatorSensor().factoryMethod("Umidade do Ar");
            dao = new ConcreteCreatorDAO().factoryMethod("Umidade do Ar", dataHoraIni, dataHoraFim);
            estatistica = new Estatistica(editarUmidade.getMedia(), "Media Sem Desvios");
            if (!"".equals(port) && jRadioButtonLer.isSelected()) {
                sensor.setIndices(editarUmidade.getOpcIndices());
                sensor.setInfo(editarUmidade.getOpcInfo());
                gravacaoChart[3] = new GravacaoChart(sensor, port, tempo, dao, estatistica);
            } else if (jRadioButtonRel.isSelected()) {
                sensor.setIndices(editarUmidade.getIndices());
                sensor.setInfo(editarUmidade.getInfo());
                leituraChart[3] = new LeituraChart(sensor, dao, estatistica);
            }
        }
        if (jCheckBoxPoeira10.isSelected()) {
            sensor = new ConcreteCreatorSensor().factoryMethod("Poeira 10");
            dao = new ConcreteCreatorDAO().factoryMethod("Poeira 10", dataHoraIni, dataHoraFim);
            estatistica = new Estatistica(editarPoeira10.getMedia(), "Media Sem Desvios");
            if (!"".equals(port) && jRadioButtonLer.isSelected()) {
                sensor.setIndices(editarPoeira10.getOpcIndices());
                sensor.setInfo(editarPoeira10.getOpcInfo());
                gravacaoChart[3] = new GravacaoChart(sensor, port, tempo, dao, estatistica);
            } else if (jRadioButtonRel.isSelected()) {
                sensor.setIndices(editarPoeira10.getIndices());
                sensor.setInfo(editarPoeira10.getInfo());
                leituraChart[3] = new LeituraChart(sensor, dao, estatistica);
            }
        }
        if (jCheckBoxPoeira25.isSelected()) {
            sensor = new ConcreteCreatorSensor().factoryMethod("Poeira 25");
            dao = new ConcreteCreatorDAO().factoryMethod("Poeira 25", dataHoraIni, dataHoraFim);
            estatistica = new Estatistica(editarPoeira25.getMedia(), "Media Sem Desvios");
            if (!"".equals(port) && jRadioButtonLer.isSelected()) {
                sensor.setIndices(editarPoeira25.getOpcIndices());
                sensor.setInfo(editarPoeira25.getOpcInfo());
                gravacaoChart[3] = new GravacaoChart(sensor, port, tempo, dao, estatistica);
            } else if (jRadioButtonRel.isSelected()) {
                sensor.setIndices(editarPoeira25.getIndices());
                sensor.setInfo(editarPoeira25.getInfo());
                leituraChart[3] = new LeituraChart(sensor, dao, estatistica);
            }
        }
    }//GEN-LAST:event_jButtonAbrirActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jRadioButtonLerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonLerMouseClicked
        jIntegerFieldTempo.setEnabled(true);
        jFormattedTextFieldDataIni.setEnabled(false);
        jFormattedTextFieldDataFim.setEnabled(false);
        jFormattedTextFieldHoraIni.setEnabled(false);
        jFormattedTextFieldHoraFim.setEnabled(false);
    }//GEN-LAST:event_jRadioButtonLerMouseClicked

    private void jRadioButtonRelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonRelMouseClicked
        jIntegerFieldTempo.setEnabled(false);
        jFormattedTextFieldDataIni.setEnabled(true);
        jFormattedTextFieldDataFim.setEnabled(true);
        jFormattedTextFieldHoraIni.setEnabled(true);
        jFormattedTextFieldHoraFim.setEnabled(true);
    }//GEN-LAST:event_jRadioButtonRelMouseClicked

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

    private void jButtonEditarPoeira10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarPoeira10ActionPerformed
        editarPoeira10.setVisible(true);
    }//GEN-LAST:event_jButtonEditarPoeira10ActionPerformed

    private void jButtonEditarPoeira25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarPoeira25ActionPerformed
        editarPoeira25.setVisible(true);
    }//GEN-LAST:event_jButtonEditarPoeira25ActionPerformed

    private void jTextFieldClasseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldClasseKeyReleased
        if (gravacaoChart != null) {
            if (jTextFieldClasse.getText().length() == 0) {
                for (GravacaoChart gc : gravacaoChart) {
                    gc.setClasse(null);
                }
            } else {
                for (GravacaoChart gc : gravacaoChart) {
                    gc.setClasse(jTextFieldClasse.getText());
                }
            }
        }
    }//GEN-LAST:event_jTextFieldClasseKeyReleased

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
    private javax.swing.JButton jButtonEditarPoeira10;
    private javax.swing.JButton jButtonEditarPoeira25;
    private javax.swing.JButton jButtonEditarPressao;
    private javax.swing.JButton jButtonEditarTemp;
    private javax.swing.JButton jButtonEditarUmidade;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JCheckBox jCheckBoxGases;
    private javax.swing.JCheckBox jCheckBoxPoeira10;
    private javax.swing.JCheckBox jCheckBoxPoeira25;
    private javax.swing.JCheckBox jCheckBoxPressao;
    private javax.swing.JCheckBox jCheckBoxTemp;
    private javax.swing.JCheckBox jCheckBoxUmidade;
    private javax.swing.JComboBox<String> jComboBoxPorta;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataFim;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataIni;
    private javax.swing.JFormattedTextField jFormattedTextFieldHoraFim;
    private javax.swing.JFormattedTextField jFormattedTextFieldHoraIni;
    private modelo.JIntegerField jIntegerFieldTempo;
    private javax.swing.JLabel jLabelClasse;
    private javax.swing.JLabel jLabelDataFim;
    private javax.swing.JLabel jLabelDataIni;
    private javax.swing.JLabel jLabelHoraFim;
    private javax.swing.JLabel jLabelHoraIni;
    private javax.swing.JLabel jLabelPorta;
    private javax.swing.JLabel jLabelSegundos;
    private javax.swing.JLabel jLabelTempo;
    private javax.swing.JRadioButton jRadioButtonLer;
    private javax.swing.JRadioButton jRadioButtonRel;
    private javax.swing.JTextField jTextFieldClasse;
    // End of variables declaration//GEN-END:variables
}
