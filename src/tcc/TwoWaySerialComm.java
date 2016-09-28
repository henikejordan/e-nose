/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author henike
 */
public class TwoWaySerialComm {

    private static String ret;
    private String[] split;

    /**
     * Connect to the port with specific baudrate.
     *
     * @param portName
     * @param baudrate
     * @throws java.lang.Exception
     */
    public void connect(String portName, Integer baudrate) throws Exception {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if (portIdentifier.isCurrentlyOwned()) {
            System.out.println("Error: Port is currently in use");
        } else {
            CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);

            if (commPort instanceof SerialPort) {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

                InputStream in = serialPort.getInputStream();
                OutputStream out = serialPort.getOutputStream();

                (new Thread(new SerialReader(in))).start();
                (new Thread(new SerialWriter(out))).start();
            } else {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }
    }

    /**
     *
     */
    public static class SerialReader implements Runnable {

        InputStream in;

        public SerialReader(InputStream in) {
            this.in = in;
        }

        @Override
        public void run() {
            byte[] buffer = new byte[1024];
            int len;
            try {
                while ((len = this.in.read(buffer)) > -1) {
                    //System.out.print(new String(buffer, 0, len));
                    ret += new String(buffer, 0, len);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     *
     */
    public static class SerialWriter implements Runnable {

        OutputStream out;

        public SerialWriter(OutputStream out) {
            this.out = out;
        }

        @Override
        public void run() {
            try {
                int c;
                while ((c = System.in.read()) > -1) {
                    this.out.write(c);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getSensor1(String info) {
        return this.getData(info, "SENSOR 1");
    }

    public String getSensor2(String info) {
        return this.getData(info, "SENSOR 2");
    }

    public String getData(String info, String sensor) {
        try {
            if (ret != null) {
                split = ret.split(";");
                for (int i = split.length - 1; i >= 0; i--) {
                    if (sensor.equals(split[i])) {
                        switch (info) {
                            case "Temperatura":
                                return split[i + 1];
                            case "Luminosidade":
                                return split[i + 2];
                            case "Umidade do ar":
                                return split[i + 3];
                            case "Umidade do solo":
                                return split[i + 4];
                        }
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Values[] getValues() {
        Values values[] = new Values[2];
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        int flag = 0;

        for (int i = 0; i < values.length; i++) {
            values[i] = new Values();
        }

        try {
            if (ret != null) {
                split = ret.split(";");
                for (int i = split.length - 1; i >= 0; i--) {
                    if (flag == 2) {
                        break;
                    } else if ("SENSOR 1".equals(split[i])) {
                        values[0].setSensor(split[i]);
                        values[0].setData_hora(dateFormat.format(date));
                        values[0].setTemperatura(Float.parseFloat(split[i + 1]));
                        values[0].setLuminosidade(Float.parseFloat(split[i + 2]));
                        values[0].setUmidade_ar(Float.parseFloat(split[i + 3]));
                        values[0].setUmidade_solo(Float.parseFloat(split[i + 4]));
                        flag++;
                    } else if ("SENSOR 2".equals(split[i])) {
                        values[1].setSensor(split[i]);
                        values[1].setData_hora(dateFormat.format(date));
                        values[1].setTemperatura(Float.parseFloat(split[i + 1]));
                        values[1].setLuminosidade(Float.parseFloat(split[i + 2]));
                        values[1].setUmidade_ar(Float.parseFloat(split[i + 3]));
                        values[1].setUmidade_solo(Float.parseFloat(split[i + 4]));
                        flag++;
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
        return values;
    }

}
