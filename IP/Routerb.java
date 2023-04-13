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
                        // Define the destination MAC address as "R1"
                        String destAddressStr = "ff:ff:ff:ff:ff:ff";
                        byte[] dstMAC2 = (destAddressStr).getBytes();
                        
                        
                        // Define the source MAC address as "N1"
                        byte[] srcMAC2 = {'R', '2'};
            
                        // byte[] dstIP = {'0', 'x', '2', 'A'};
                        // byte[] srcIP = {'0', 'x', '1', 'A'};
            
                        byte[] pingProtocol = {'2'};
                        byte[] dataLength = {'1', '3'};
            

                        // System.out.println((char)dstMAC2[2]);
                        
                        // Define the Ethernet frame data as a byte array
                        byte[] frameData = {(byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, 
                                            (byte) 0x06, (byte) 0x07, (byte) 0x08, (byte) 0x09, (byte) 0x0a, (byte) 0x0b, 
                                            (byte) 0x08, (byte) 0x00, (byte) 0x45, (byte) 0x00, (byte) 0x00, (byte) 0x00
                                            , (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00
                                            , (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};
                        
                        // Create a new DatagramPacket containing the Ethernet frame data
                        DatagramPacket packet = new DatagramPacket(frameData, frameData.length);
                        
              
                        String message2 = "Hello, Node2!";
                        byte[] msg = message2.getBytes();
                        
                        
                        // Create a new DatagramSocket to send the packet
                        frameData[0] = dstMAC2[0];
                        frameData[1] = dstMAC2[1];

                        frameData[2] = dstMAC2[2];
                        frameData[3] = dstMAC2[3];
                        frameData[4] = dstMAC2[4];
                        frameData[5] = dstMAC2[5];
                        frameData[6] = dstMAC2[6];
                        frameData[7] = dstMAC2[7];
                        frameData[8] = dstMAC2[8];
                        frameData[9] = dstMAC2[9];
                        frameData[10] = dstMAC2[10];
                        frameData[11] = dstMAC2[11];
                        frameData[12] = dstMAC2[12];
                        frameData[13] = dstMAC2[13];
                        frameData[14] = dstMAC2[14];
                        frameData[15] = dstMAC2[15];
                        frameData[16] = dstMAC2[16];



                        frameData[17] = srcMAC2[0];
                        frameData[18] = srcMAC2[1];
            
                        frameData[19] = srcIP[0];
                        frameData[20] = srcIP[1];
                        frameData[21] = srcIP[2];
                        frameData[22] = srcIP[3];
            
                        frameData[23] = dstIP[0];
                        frameData[24] = dstIP[1];
                        frameData[25] = dstIP[2];
                        frameData[26] = dstIP[3];
            
                        frameData[27] = pingProtocol[0];
                        frameData[28] = dataLength[0];
                        frameData[29] = dataLength[1];
            
            
            
                        frameData[30] = msg[0]; 
                        frameData[31] = msg[1];
                        frameData[32] = msg[2];
                        frameData[33] = msg[3];
                        frameData[34] = msg[4];
                        frameData[35] = msg[5];
                        frameData[36] = msg[6]; 
                        frameData[37] = msg[7];
                        frameData[38] = msg[8];
                        frameData[39] = msg[9];
                        frameData[40] = msg[10];
                        frameData[41] = msg[11];
                        frameData[42] = msg[12];
            
            
            
                        DatagramSocket socket = new DatagramSocket();
                        

            
                        System.out.println("Sending packet: ");
                        System.out.println(


                          (char)frameData[19]+ ""+ (char)frameData[20]+ ""+ (char)frameData[21]+ ""+(char)frameData[22] + "|"

                        +   (char)frameData[23]+ (char)frameData[24]+  (char)frameData[25]+ (char)frameData[26] + "|"

                        + (char)frameData[27]+ "|" 
                        + (char)frameData[28]+ (char)frameData[29] + "|" +
                        
                        (char)frameData[30]+ (char)frameData[31]
                        + (char)frameData[32]+ (char)frameData[33]+ (char)frameData[34]+ (char)frameData[35]+ (char)frameData[36]+ (char)frameData[37]
                        + (char)frameData[38]+ (char)frameData[39]+ (char)frameData[40]+ (char)frameData[41]+ (char)frameData[42]);


                        // if dest IP belongs to Node 2, send to Node 2
                        if ((char)frameData[25] == '2' && (char)frameData[26] == 'A'){
                            packet.setAddress(InetAddress.getByName("127.0.0.1"));
                            packet.setPort(502);
                            
                            System.out.println("Packet sent!");
                            // Send the packet to the Ethernet emulator
                            socket.send(packet);
                        }
                        else if ((char)frameData[25] == '2' && (char)frameData[26] == 'B'){
                            packet.setAddress(InetAddress.getByName("127.0.0.1"));
                            packet.setPort(503);
                            
                            System.out.println("Packet sent!");
                            // Send the packet to the Ethernet emulator
                            socket.send(packet);
                        }

                       
                        // Close the socket
                        socket.close();
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if ((char)dstIP[2] == (char)'1') {
                    try {
                        System.out.println("Forwarding packet to R1");
                        // Define the destination MAC address as "R1"
                        String destAddressStr = "ff:ff:ff:ff:ff:ff";
                        byte[] dstMAC2 = (destAddressStr).getBytes();
                        
                        
                        // Define the source MAC address as "N1"
                        byte[] srcMAC2 = {'R', '1'};
            
                        // byte[] dstIP = {'0', 'x', '2', 'A'};
                        // byte[] srcIP = {'0', 'x', '1', 'A'};
            
                        byte[] pingProtocol = {'2'};
                        byte[] dataLength = {'1', '3'};
            

                        // System.out.println((char)dstMAC2[2]);
                        
                        // Define the Ethernet frame data as a byte array
                        byte[] frameData = {(byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, 
                                            (byte) 0x06, (byte) 0x07, (byte) 0x08, (byte) 0x09, (byte) 0x0a, (byte) 0x0b, 
                                            (byte) 0x08, (byte) 0x00, (byte) 0x45, (byte) 0x00, (byte) 0x00, (byte) 0x00
                                            , (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00
                                            , (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};
                        
                        // Create a new DatagramPacket containing the Ethernet frame data
                        DatagramPacket packet = new DatagramPacket(frameData, frameData.length);
                        
              
                        String message2 = "Hello, Node3!";
                        byte[] msg = message2.getBytes();
                        
                        
                        // Create a new DatagramSocket to send the packet
                        frameData[0] = dstMAC2[0];
                        frameData[1] = dstMAC2[1];

                        frameData[2] = dstMAC2[2];
                        frameData[3] = dstMAC2[3];
                        frameData[4] = dstMAC2[4];
                        frameData[5] = dstMAC2[5];
                        frameData[6] = dstMAC2[6];
                        frameData[7] = dstMAC2[7];
                        frameData[8] = dstMAC2[8];
                        frameData[9] = dstMAC2[9];
                        frameData[10] = dstMAC2[10];
                        frameData[11] = dstMAC2[11];
                        frameData[12] = dstMAC2[12];
                        frameData[13] = dstMAC2[13];
                        frameData[14] = dstMAC2[14];
                        frameData[15] = dstMAC2[15];
                        frameData[16] = dstMAC2[16];



                        frameData[17] = srcMAC2[0];
                        frameData[18] = srcMAC2[1];
            
                        frameData[19] = srcIP[0];
                        frameData[20] = srcIP[1];
                        frameData[21] = srcIP[2];
                        frameData[22] = srcIP[3];
            
                        frameData[23] = dstIP[0];
                        frameData[24] = dstIP[1];
                        frameData[25] = dstIP[2];
                        frameData[26] = dstIP[3];
            
                        frameData[27] = pingProtocol[0];
                        frameData[28] = dataLength[0];
                        frameData[29] = dataLength[1];
            
            
            
                        frameData[30] = msg[0]; 
                        frameData[31] = msg[1];
                        frameData[32] = msg[2];
                        frameData[33] = msg[3];
                        frameData[34] = msg[4];
                        frameData[35] = msg[5];
                        frameData[36] = msg[6]; 
                        frameData[37] = msg[7];
                        frameData[38] = msg[8];
                        frameData[39] = msg[9];
                        frameData[40] = msg[10];
                        frameData[41] = msg[11];
                        frameData[42] = msg[12];
            
            
            
                        DatagramSocket socket = new DatagramSocket();
                        

            
                        System.out.println("Sending packet: ");
                        System.out.println(


                          (char)frameData[19]+ (char)frameData[20]+  (char)frameData[21]+ (char)frameData[22] + "|"

                        +   (char)frameData[23]+ (char)frameData[24]+  (char)frameData[25]+ (char)frameData[26] + "|"

                        + (char)frameData[27]+ "|" 
                        + (char)frameData[28]+ (char)frameData[29] + "|" +
                        
                        (char)frameData[30]+ (char)frameData[31]
                        + (char)frameData[32]+ (char)frameData[33]+ (char)frameData[34]+ (char)frameData[35]+ (char)frameData[36]+ (char)frameData[37]
                        + (char)frameData[38]+ (char)frameData[39]+ (char)frameData[40]+ (char)frameData[41]+ (char)frameData[42]);


                        // Set the destination address and port to send the packet to the Ethernet emulator
                        packet.setAddress(InetAddress.getByName("127.0.0.1"));
                        packet.setPort(499);
                        
                        System.out.println("Packet sent!");
                        // Send the packet to the Ethernet emulator
                        socket.send(packet);
                        
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
    