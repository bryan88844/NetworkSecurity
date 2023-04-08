import java.net.*;

public class Router {
    // Define MAC addresses for each node and router
    private static final byte[] N1_MAC = {'N', '1'};
    private static final byte[] N2_MAC = {'N', '2'};
    private static final byte[] N3_MAC = {'N', '3'};
    private static final byte[] R1_MAC = {'R', '1'};
    private static final byte[] R2_MAC = {'R', '2'};
    
    public static void main(String[] args) {
        
        try {
            // Create a datagram socket for sending and receiving Ethernet frames
            DatagramSocket ethSocket = new DatagramSocket(500);
            
            // Create a buffer to hold the Ethernet frame data
            byte[] buffer = new byte[1024];
            
            // Create a DatagramPacket to receive Ethernet frames
            DatagramPacket ethPacket = new DatagramPacket(buffer, buffer.length);
            
            // Receive Ethernet frames in a loop
            while (true) {
                ethSocket.receive(ethPacket);
                
                // Get the source and destination MAC addresses from the Ethernet frame
                byte[] srcMAC = new byte[]{buffer[0], buffer[1]};
                byte[] dstMAC = new byte[]{buffer[2], buffer[3]};
                
                // Process the Ethernet frame based on its destination MAC address
                if (Arrays.equals(dstMAC, N1_MAC)) {
                    // Handle Ethernet frame destined for Node1
                    System.out.println("Forwarding packet to Node 1!");
                } else if (Arrays.equals(dstMAC, N2_MAC)) {
                    // Handle Ethernet frame destined for Node2
                    System.out.println("Forwarding packet to Node 2!");
                } else if (Arrays.equals(dstMAC, N3_MAC)) {
                    // Handle Ethernet frame destined for Node3
                    System.out.println("Forwarding packet to Node 3!");
                } else if (Arrays.equals(dstMAC, R1_MAC)) {
                    System.out.println("Forwarding packet to R1!");
                } else if (Arrays.equals(dstMAC, R2_MAC)) {
                    // Handle Ethernet frame destined for Router's second interface
                    System.out.println("Forwarding packet to R2!");

                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}