package controle;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Henike
 */
public class TwoWaySerialComm {

    private static String data;
    private String[] dataSplit;

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
                //OutputStream out = serialPort.getOutputStream();

                (new Thread(new SerialReader(in))).start();
                //(new Thread(new SerialWriter(out))).start();
            } else {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }
    }

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
                    data += new String(buffer, 0, len);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

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

    public String getData(int i) {
        try {
            if (data != null) {
                dataSplit = data.split(";");
                for (int j = dataSplit.length - 1; j >= 0; j--) {
                    if ("inicio".equals(dataSplit[j])) {
                        return dataSplit[j + i + 1];
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            //System.out.println(e.getMessage());
        }
        return null;
    }

}
