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
        int receiverPort = Integer.parseInt(args[1]);
        int senderPort = Integer.parseInt(args[2]);
        String fileName = args[3];
        String maxDataSize = args[4];
        String timeout = args[5];

        DatagramSocket ds = new DatagramSocket();
        ds.setSoTimeout(1000);

        // TODO: READ FILE AND STORE IN STRING

        String fileContent = "hello this is really cool lets split it up correctly!";
        int maxSize = 7; // TODO: REPLACE THIS WITH THE ARGUMENT MAX SIZE VARIABLE
        byte[] bufferData;
        for (int i = 0; i < (fileContent.length() / maxSize) + 1; i++) {
            bufferData = generateDatagramPacketBuffer(fileContent, maxSize, i);
            ds.send(new DatagramPacket(bufferData, bufferData.length, InetAddress.getByName(ip), receiverPort));

        }
        ds.close();
    }

    public static byte[] generateDatagramPacketBuffer(String data, int maxSize, int packetIndex) {
        byte[] bufferData = new byte[maxSize + 1];
        int endIndex = packetIndex == data.length() / maxSize ? data.length() : maxSize * (packetIndex + 1);
        for (int index = maxSize * packetIndex; index < endIndex; index++) {
            bufferData[maxSize + index - endIndex] = (byte) data.charAt(index);
        }
        bufferData[maxSize] = (byte) (packetIndex % 2);
        System.out.println(bufferData[maxSize]);
        return bufferData;
    }

}
