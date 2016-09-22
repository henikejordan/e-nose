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

/**
 *
 * @author henike
 */
public class TwoWaySerialComm {

    private static String retorno;

    public TwoWaySerialComm() {
        super();
    }

    void connect(String portName) throws Exception {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if (portIdentifier.isCurrentlyOwned()) {
            System.out.println("Error: Port is currently in use");
        } else {
            CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);

            if (commPort instanceof SerialPort) {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

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
                    retorno += new String(buffer, 0, len);
                }
            } catch (IOException e) {
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
            }
        }
    }

    public String getTemperaturaSensor1() {
        if (retorno != null) {
            String[] split = retorno.split(";");
            for (int i = split.length - 1; i >= 0; i--) {
                if ("SENSOR 1".equals(split[i])) {
                    try {
                        return split[i + 1];
                    } catch (IndexOutOfBoundsException e) {
                    }
                }
            }
        }
        return null;
    }

    public String getLuminosidadeSensor1() {
        if (retorno != null) {
            String[] split = retorno.split(";");
            for (int i = split.length - 1; i >= 0; i--) {
                if ("SENSOR 1".equals(split[i])) {
                    try {
                        return split[i + 2];
                    } catch (IndexOutOfBoundsException e) {
                    }
                }
            }
        }
        return null;
    }

    public String getTemperaturaSensor2() {
        if (retorno != null) {
            String[] split = retorno.split(";");
            for (int i = split.length - 1; i >= 0; i--) {
                if ("SENSOR 2".equals(split[i])) {
                    try {
                        return split[i + 1];
                    } catch (IndexOutOfBoundsException e) {
                    }
                }
            }
        }
        return null;
    }

    public String getLuminosidadeSensor2() {
        if (retorno != null) {
            String[] split = retorno.split(";");
            for (int i = split.length - 1; i >= 0; i--) {
                if ("SENSOR 2".equals(split[i])) {
                    try {
                        return split[i + 2];
                    } catch (IndexOutOfBoundsException e) {
                    }
                }
            }
        }
        return null;
    }

}
