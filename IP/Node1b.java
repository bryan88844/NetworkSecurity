import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.*;
import java.util.*;

public class Node1b {
    public static void main(String[] args) {
        try {
           

            byte[] dstIP = {'0', 'x', '2', 'B'};
            byte[] srcIP = {'0', 'x', '1', 'A'};

            byte[] pingProtocol = {'2'};
            byte[] dataLength = {'1', '3'};


            
            // Define the Ethernet frame data as a byte array
            byte[] frameData = {(byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, 
                                (byte) 0x06, (byte) 0x07, (byte) 0x08, (byte) 0x09, (byte) 0x0a, (byte) 0x0b, 
                                (byte) 0x08, (byte) 0x00, (byte) 0x45, (byte) 0x00, (byte) 0x00, (byte) 0x00
                                , (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00
                                };
            
            // Create a new DatagramPacket containing the Ethernet frame data
            DatagramPacket packet = new DatagramPacket(frameData, frameData.length);
            
            // Set the destination address and port to send the packet to the Ethernet emulator
            packet.setAddress(InetAddress.getByName("127.0.0.1"));
            packet.setPort(501);
            
            String message0 = "Hello, Node3!";
            byte[] msg = message0.getBytes();
            
            
            // Create a new DatagramSocket to send the packet
            frameData[0] = srcIP[0];
            frameData[1] = srcIP[1];
            frameData[2] = srcIP[2];
            frameData[3] = srcIP[3];

            frameData[4] = dstIP[0];
            frameData[5] = dstIP[1];
            frameData[6] = dstIP[2];
            frameData[7] = dstIP[3];

            frameData[8] = pingProtocol[0];
            frameData[9] = dataLength[0];
            frameData[10] = dataLength[1];

            frameData[11] = msg[0]; 
            frameData[12] = msg[1];
            frameData[13] = msg[2];
            frameData[14] = msg[3];
            frameData[15] = msg[4];
            frameData[16] = msg[5];
            frameData[17] = msg[6]; 
            frameData[18] = msg[7];
            frameData[19] = msg[8];
            frameData[20] = msg[9];
            frameData[21] = msg[10];
            frameData[22] = msg[11];
            frameData[23] = msg[12];

            DatagramSocket socket = new DatagramSocket(499);
            
            // Send the packet to the Ethernet emulator
            socket.send(packet);

            System.out.println("Sending packet: ");
            System.out.println(""+(char)frameData[0] + ""+ (char)frameData[1]+""+ (char)frameData[2]+ ""+(char)frameData[3]+ "|" + (char)frameData[4]
            + (char)frameData[5]+ (char)frameData[6]+ (char)frameData[7]+ "|" + (char)frameData[8]+ "|" + (char)frameData[9]
            + (char)frameData[10]+ "|" + (char)frameData[11]+(char)frameData[12]+(char)frameData[13]+
            (char)frameData[14]+(char)frameData[15]+ (char)frameData[16] + (char)frameData[17]+ (char)frameData[18]
            + (char)frameData[19]+ (char)frameData[20]+ (char)frameData[21]+ (char)frameData[22]+ (char)frameData[23]);
            System.out.println("Packet Sent!");
            
            // Close the socket
            // socket.close();

            // Create a buffer to hold the Ethernet frame data
            byte[] buffer = new byte[1024];
            
            // Create a DatagramPacket to receive Ethernet frames
            DatagramPacket ethPacket = new DatagramPacket(buffer, buffer.length);

            while (true) {
                System.out.println("Listening to IP Packets...");
                socket.receive(ethPacket);
                System.out.println("Receiving incoming packet...");
                
                
            System.out.println("Received IP Packet: ");
            byte[] message = new byte[]{buffer[19],buffer[20],buffer[21],buffer[22],buffer[23],buffer[24],buffer[25],buffer[26],buffer[27],
                buffer[28],buffer[29], buffer[30], buffer[31], buffer[32], buffer[33], buffer[34], buffer[35], buffer[36]
                , buffer[37], buffer[38], buffer[39], buffer[40], buffer[41], buffer[42]};
            
            if ((char)message[7] == (char)'A'){
            
                System.out.println( (char)message[0] + "" + (char)message[1] + "" + (char)message[2] + "" + (char)message[3]+ "|" 
                + (char)message[4] + (char)message[5]+ (char)message[6] + (char)message[7]  + "|" 
                + (char)message[8]+ "|" 
                + (char)message[9]+ (char)message[10]+ "|" 
                + (char)message[11]+ (char)message[12] 
                + (char)message[13]+ (char)message[14] + (char)message[15]+ (char)message[16]+ (char)message[17]+ (char)message[18]+ (char)message[19]+ (char)message[20] 
                + (char)message[21]+ (char)message[22] + (char)message[23]
                );
            }
            else{
            System.out.println("Mismatched Destination IP! Package Dropped! ");
            }
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
