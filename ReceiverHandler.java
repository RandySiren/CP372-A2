import java.io.IOException;
import java.net.*;

public class ReceiverHandler {
    private DatagramSocket ds;

    public void startReceiving(String address, int senderPort, int receiverPort, String outputFileName) throws SocketException {
        System.out.println("Starting to receive on address: " + address + " at port: " + receiverPort);
        ds = new DatagramSocket();
        ds.bind(new InetSocketAddress(address, receiverPort));
        byte[] buf = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, 1024);
        while (true) {
            try {
                ds.receive(dp);
                StringBuilder data = new StringBuilder();
                for (int i = 0; i < dp.getLength() - 1; i++) {
                    data.append((char) dp.getData()[i]);
                }
                int sequenceNumber = dp.getData()[dp.getLength() - 1];
            } catch (IOException exception) {
                break;
            }
        }
    }

    public void stopReceiving() {
        ds.close();
    }

}
