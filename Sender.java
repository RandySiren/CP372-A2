import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender {
    public static void main(String[] args) throws IOException {
        if (args.length != 6) {
            System.out.println("Incomplete arguments, program shutting down.");
            System.exit(0);
        }
        String ip = args[0];
        String receiverPort = args[1];
        String senderPort = args[2];
        String fileName = args[3];
        String maxDataSize = args[4];
        String timeout = args[5];

        DatagramSocket ds = new DatagramSocket();

        String data = "TestDfta";


        DatagramPacket dp = new DatagramPacket(data.getBytes(), data.length(), InetAddress.getByName("127.0.0.1"), 3321);

        ds.send(dp);
        ds.close();
    }
}
