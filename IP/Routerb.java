import java.net.*;
import java.util.*;

public class Routerb {
    
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
                System.out.println("Listening to IP Packets...");
                ethSocket.receive(ethPacket);
                System.out.println("Receiving incoming packet...");
   
                
                // Get the source and destination MAC addresses from the Ethernet frame
                System.out.println("Received IP Packet: ");

                byte[] dstIP = new byte[]{buffer[4], buffer[5],buffer[6],buffer[7]};
                byte[] srcIP = new byte[]{buffer[0], buffer[1], buffer[2], buffer[3]};
                
                byte[] message = new byte[]{buffer[8],buffer[9],buffer[10],buffer[11],buffer[12],buffer[13],buffer[14],
                    buffer[15],buffer[16],buffer[17], buffer[18], buffer[19],buffer[20],buffer[21],buffer[22],buffer[23]};


                System.out.println((char)srcIP[0] + "" + (char)srcIP[1]+ "" + (char)srcIP[2] + "" + (char)srcIP[3]+ "|" + (char)dstIP[0] 
                + "" + (char)dstIP[1]+ "" + (char)dstIP[2] + "" + (char)dstIP[3]+ "|" + (char)message[0] + "|" +
                (char)message[1] + (char)message[2] + "|" + (char)message[3] + (char)message[4] + (char)message[5]+ (char)message[6]
                + (char)message[7]  + (char)message[8]+ (char)message[9]+ (char)message[10] + (char)message[11]+ (char)message[12] 
                + (char)message[13]+ (char)message[14] + (char)message[15]);


                if ((char)dstIP[2] == (char)'2'){
                    System.out.println("Forwarding packet to R2");

                    try {

            
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
                        packet.setPort(505);
                        
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
            
                        DatagramSocket socket = new DatagramSocket();
                        
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
                        socket.close();
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if ((char)dstIP[2] == (char)'1') {
                    try {
                        System.out.println("Forwarding packet to Node1");
                        // byte[] pingProtocol = {'2'};
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
                        packet.setPort(499);
                        
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
            
                        DatagramSocket socket = new DatagramSocket();
                        
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
                        socket.close();
                        
                        // Close the socket
                        socket.close();
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Destination IP not found, packet dropped!");
                }
                
                // Process the Ethernet frame based on its destination MAC address
                // if (Arrays.equals(dstMAC, N1_MAC)) {
                //     System.out.println("Forwarding to N1");
                //     // Handle Ethernet frame destined for Node1
                // } else if (Arrays.equals(dstMAC, N2_MAC)) {
                //     System.out.println("Forwarding to N2");
                //     // Handle Ethernet frame destined for Node2
                // } else if (Arrays.equals(dstMAC, N3_MAC)) {
                //     System.out.println("Forwarding to N3");
                //     // Handle Ethernet frame destined for Node3
                // } else if (Arrays.equals(dstMAC, R1_MAC)) {
                //     System.out.println("Forwarding to R1");

                //     // Handle Ethernet frame destined for Router's first interface
                // } else if (Arrays.equals(dstMAC, R2_MAC)) {
                //     System.out.println("Forwarding to R2");

                //     // Handle Ethernet frame destined for Router's second interface
                // }
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
    