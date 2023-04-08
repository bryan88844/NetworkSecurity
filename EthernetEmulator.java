import java.net.*;
import java.util.*;

public class EthernetEmulator {
    
    // Define MAC addresses for each node and router
    private static final byte[] N1_MAC = {'N', '1'};
    private static final byte[] N2_MAC = {'N', '2'};
    private static final byte[] N3_MAC = {'N', '3'};
    private static final byte[] R1_MAC = {'R', '1'};
    private static final byte[] R2_MAC = {'R', '2'};
    
    public static void main(String[] args) {
        
        try {
            // Create a DatagramSocket for sending and receiving Ethernet frames
            DatagramSocket ethSocket = new DatagramSocket(501);
            
            // Create a buffer to hold the Ethernet frame data
            byte[] buffer = new byte[1024];
            
            // Create a DatagramPacket to receive Ethernet frames
            DatagramPacket ethPacket = new DatagramPacket(buffer, buffer.length);
            
            // Receive Ethernet frames in a loop
            while (true) {
                System.out.println("Listening to Ethernet frames...");
                ethSocket.receive(ethPacket);
                System.out.println("Receiving incoming packet...");
   
                
                // Get the source and destination MAC addresses from the Ethernet frame
                System.out.println("Received ethernet frame: ");
                byte[] dstMAC = new byte[]{buffer[0], buffer[1]};
                byte[] srcMAC = new byte[]{buffer[2], buffer[3]};
                byte[] message = new byte[]{buffer[4], buffer[5], buffer[6],buffer[7],buffer[8],buffer[9],buffer[10],buffer[11],buffer[12],buffer[13],buffer[14],buffer[15],buffer[16],};


                System.out.println((char)srcMAC[0] + "" + (char)srcMAC[1] + "|" + (char)dstMAC[0] 
                + "" + (char)dstMAC[1] + "|" + ethPacket.getLength() + "|" + (char)message[0] + 
                (char)message[1] + (char)message[2] + (char)message[3]+ (char)message[4]+ (char)message[5]+ (char)message[6]
                + (char)message[7]+ (char)message[8]+ (char)message[9]+ (char)message[10]+ (char)message[11]+ (char)message[12]
                );


                
                // Process the Ethernet frame based on its destination MAC address
                if (Arrays.equals(dstMAC, N1_MAC)) {
                    System.out.println("Forwarding to N1");
                    // Handle Ethernet frame destined for Node1
                } else if (Arrays.equals(dstMAC, N2_MAC)) {
                    System.out.println("Forwarding to N2");
                    // Handle Ethernet frame destined for Node2
                } else if (Arrays.equals(dstMAC, N3_MAC)) {
                    System.out.println("Forwarding to N3");
                    // Handle Ethernet frame destined for Node3
                } else if (Arrays.equals(dstMAC, R1_MAC)) {
                    System.out.println("Forwarding to R1");

                    // Handle Ethernet frame destined for Router's first interface
                } else if (Arrays.equals(dstMAC, R2_MAC)) {
                    System.out.println("Forwarding to R2");

                    // Handle Ethernet frame destined for Router's second interface
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void sendEthernetFrame(byte[] data, byte[] srcMAC, byte[] dstMAC) {
        try {
            // Create a DatagramPacket to send the Ethernet frame
            DatagramPacket ethPacket = new DatagramPacket(data, data.length);
            
            // Set the source and destination MAC addresses in the Ethernet frame
            ethPacket.getData()[0] = srcMAC[0];
            ethPacket.getData()[1] = srcMAC[1];
            ethPacket.getData()[2] = dstMAC[0];
            ethPacket.getData()[3] = dstMAC[1];
            
            // Set the destination IP address and port to match the destination MAC address
            if (Arrays.equals(dstMAC, N1_MAC)) {
                ethPacket.setAddress(InetAddress.getByName("127.0.0.1"));
                ethPacket.setPort(5001);
            } else if (Arrays.equals(dstMAC, N2_MAC)) {
                ethPacket.setAddress(InetAddress.getByName("127.0.0.1"));
                ethPacket.setPort(5002);
            } else if (Arrays.equals(dstMAC, N3_MAC)) {
                ethPacket.setAddress(InetAddress.getByName("127.0.0.1"));
                ethPacket.setPort(5003);
            } else if (Arrays.equals(dstMAC, R1_MAC)) {
                ethPacket.setAddress(InetAddress.getByName("127.0.0.1"));
                ethPacket.setPort(5004);
            } else if (Arrays.equals(dstMAC, R2_MAC)) {
                ethPacket.setAddress(InetAddress.getByName("127.0.0.1"));
                ethPacket.setPort(5005);
            }
            
            // Create a DatagramSocket to send the Ethernet frame
            DatagramSocket ethSocket = new DatagramSocket();
            
            // Send the Ethernet frame
            ethSocket.send(ethPacket);
            
            // Close the DatagramSocket
            ethSocket.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    