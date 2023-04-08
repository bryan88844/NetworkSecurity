import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendEthernetFrame {
    public static void main(String[] args) {
        try {
            // Define the destination MAC address as "R1"
            byte[] dstMAC = {'R', '1'};
            
            // Define the source MAC address as "N1"
            byte[] srcMAC = {'N', '1'};
            
            // Define the Ethernet frame data as a byte array
            byte[] frameData = {(byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, 
                                (byte) 0x06, (byte) 0x07, (byte) 0x08, (byte) 0x09, (byte) 0x0a, (byte) 0x0b, 
                                (byte) 0x08, (byte) 0x00, (byte) 0x45, (byte) 0x00, (byte) 0x00};
            
            // Create a new DatagramPacket containing the Ethernet frame data
            DatagramPacket packet = new DatagramPacket(frameData, frameData.length);
            
            // Set the destination address and port to send the packet to the Ethernet emulator
            packet.setAddress(InetAddress.getByName("127.0.0.1"));
            packet.setPort(501);
            
            String message = "Hello, Node2!";
            byte[] msg = message.getBytes();
            
            
            // Create a new DatagramSocket to send the packet
            frameData[0] = dstMAC[0];
            frameData[1] = dstMAC[1];
            frameData[2] = srcMAC[0];
            frameData[3] = srcMAC[1];

            frameData[4] = msg[0]; 
            frameData[5] = msg[1];
            frameData[6] = msg[2];
            frameData[7] = msg[3];
            frameData[8] = msg[4];
            frameData[9] = msg[5];
            frameData[10] = msg[6]; 
            frameData[11] = msg[7];
            frameData[12] = msg[8];
            frameData[13] = msg[9];
            frameData[14] = msg[10];
            frameData[15] = msg[11];
            frameData[16] = msg[12];

            System.out.println((char)frameData[16]);

            DatagramSocket socket = new DatagramSocket();
            
            // Send the packet to the Ethernet emulator
            socket.send(packet);
            
            // Close the socket
            socket.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}