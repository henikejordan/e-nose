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
import java.util.Random;

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
        Random gerador = new Random();
        try {
            //if (ret != null) {
                //split = ret.split(";");
                //for (int i = split.length - 1; i >= 0; i--) {
                    //if (sensor.equals(split[i])) {
                        switch (info) {
                            case "Temperatura":
                                return String.valueOf(gerador.nextDouble() * 100);
                                //return split[i + 1];
                            case "Luminosidade":
                                return String.valueOf(gerador.nextDouble() * 100);
                                //return split[i + 2];
                            case "Umidade do ar":
                                return String.valueOf(gerador.nextDouble() * 100);
                                //return split[i + 3];
                            case "Umidade do solo":
                                return String.valueOf(gerador.nextDouble() * 100);
                                //return split[i + 4];
                        }
                    //}
                //}
            //}
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
