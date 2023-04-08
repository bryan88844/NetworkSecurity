import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Node1 {
    public static void main(String[] args) {
        try {
            // Define the destination MAC address as "R1"
            byte[] dstMAC = {'R', '1'};
            
            // Define the source MAC address as "N1"
            byte[] srcMAC = {'N', '1'};

            byte[] dstIP = {'0', 'x', '2', 'A'};
            byte[] srcIP = {'0', 'x', '1', 'A'};

            byte[] pingProtocol = {'0'};
            byte[] dataLength = {'1', '7'};


            
            // Define the Ethernet frame data as a byte array
            byte[] frameData = {(byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, 
                                (byte) 0x06, (byte) 0x07, (byte) 0x08, (byte) 0x09, (byte) 0x0a, (byte) 0x0b, 
                                (byte) 0x08, (byte) 0x00, (byte) 0x45, (byte) 0x00, (byte) 0x00, (byte) 0x00
                                , (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00
                                , (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};
            
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

            frameData[4] = srcIP[0];
            frameData[5] = srcIP[1];
            frameData[6] = srcIP[2];
            frameData[7] = srcIP[3];

            frameData[8] = dstIP[0];
            frameData[9] = dstIP[1];
            frameData[10] = dstIP[2];
            frameData[11] = dstIP[3];

            frameData[12] = pingProtocol[0];
            frameData[13] = dataLength[0];
            frameData[14] = dataLength[1];



            frameData[15] = msg[0]; 
            frameData[16] = msg[1];
            frameData[17] = msg[2];
            frameData[18] = msg[3];
            frameData[19] = msg[4];
            frameData[20] = msg[5];
            frameData[21] = msg[6]; 
            frameData[22] = msg[7];
            frameData[23] = msg[8];
            frameData[24] = msg[9];
            frameData[25] = msg[10];
            frameData[26] = msg[11];
            frameData[27] = msg[12];



            DatagramSocket socket = new DatagramSocket();
            
            // Send the packet to the Ethernet emulator
            socket.send(packet);

            System.out.println("Sending packet: ");
            System.out.println((char)frameData[2] + "" + (char)frameData[3] + "|" + (char)frameData[0] + (char)frameData[1] + "|"
            + (char)frameData[4] + (char)frameData[5]+ (char)frameData[6]+ (char)frameData[7]+ "|" + (char)frameData[8]
            + (char)frameData[9]+ (char)frameData[10]+ (char)frameData[11]+ "|" + (char)frameData[12]+ "|" + (char)frameData[13]
            + (char)frameData[14]+ "|" + (char)frameData[15]+ (char)frameData[16] + (char)frameData[17]+ (char)frameData[18]
            + (char)frameData[19]+ (char)frameData[20]+ (char)frameData[21]+ (char)frameData[22]+ (char)frameData[23]+ (char)frameData[24]
            + (char)frameData[25]+ (char)frameData[26]+ (char)frameData[27]);
            System.out.println("Packet Sent!");
            
            // Close the socket
            socket.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
