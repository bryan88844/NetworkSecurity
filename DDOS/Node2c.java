import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.*;
import java.util.*;
import java.io.*;

public class Node2c {
    public static void main(String[] args) {

        try {
            DatagramSocket socket = new DatagramSocket(499);
            for (int i = 0; i < 10; i++) {
                byte[] dstIP = { '0', 'x', '1', 'A' };
                byte[] srcIP = { '0', 'x', '2', 'B' };

                byte[] pingProtocol = { '0' };
                byte[] dataLength = { '1', '3' };

                // Define the Ethernet frame data as a byte array
                byte[] frameData = { (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05,
                        (byte) 0x06, (byte) 0x07, (byte) 0x08, (byte) 0x09, (byte) 0x0a, (byte) 0x0b,
                        (byte) 0x08, (byte) 0x00, (byte) 0x45, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00
                };

                // Create a new DatagramPacket containing the Ethernet frame data
                DatagramPacket packet = new DatagramPacket(frameData, frameData.length);

                // Set the destination address and port to send the packet to the Ethernet
                // emulator
                packet.setAddress(InetAddress.getByName("127.0.0.1"));
                packet.setPort(501);

                String message0 = "Hello, Node1!";
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

                // Send the packet to the Ethernet emulator
                socket.send(packet);

                System.out.println("Sending packet: ");
                System.out.println("" + (char) frameData[0] + "" + (char) frameData[1] + "" + (char) frameData[2] + ""
                        + (char) frameData[3] + "|" + (char) frameData[4]
                        + (char) frameData[5] + (char) frameData[6] + (char) frameData[7] + "|" + (char) frameData[8]
                        + "|" + (char) frameData[9]
                        + (char) frameData[10] + "|" + (char) frameData[11] + (char) frameData[12]
                        + (char) frameData[13] +
                        (char) frameData[14] + (char) frameData[15] + (char) frameData[16] + (char) frameData[17]
                        + (char) frameData[18]
                        + (char) frameData[19] + (char) frameData[20] + (char) frameData[21] + (char) frameData[22]
                        + (char) frameData[23]);
                System.out.println("Packet Sent!");

                // Close the socket
                // socket.close();

                // Create a buffer to hold the Ethernet frame data
                byte[] buffer = new byte[1024];

                // Create a DatagramPacket to receive Ethernet frames
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
