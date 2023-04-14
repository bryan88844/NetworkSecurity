import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.*;
import java.util.*;
import java.io.*;

public class Node4c {
    public static void main(String[] args) {

        int count = 0;

        try {
            DatagramSocket ethSocket = new DatagramSocket(503);

            // Create a buffer to hold the Ethernet frame data
            byte[] buffer = new byte[1024];

            // Create a DatagramPacket to receive Ethernet frames
            DatagramPacket ethPacket = new DatagramPacket(buffer, buffer.length);

            while (true) {
                System.out.println("Listening to Ethernet frames...");
                ethSocket.receive(ethPacket);
                System.out.println("Receiving incoming packet...");
                byte[] message = new byte[] { buffer[19], buffer[20], buffer[21], buffer[22], buffer[23], buffer[24],
                        buffer[25], buffer[26], buffer[27],
                        buffer[28], buffer[29], buffer[30], buffer[31], buffer[32], buffer[33], buffer[34], buffer[35],
                        buffer[36], buffer[37], buffer[38], buffer[39], buffer[40], buffer[41], buffer[42] };
                // Get the source and destination MAC addresses from the Ethernet frame

                // checking if dest ip is node3 ip, if not drop
                if ((char) message[7] == (char) 'B') {

                    System.out.println((char) message[0] + "" + (char) message[1] + "" + (char) message[2] + ""
                            + (char) message[3] + "|"
                            + (char) message[4] + (char) message[5] + (char) message[6] + (char) message[7] + "|"
                            + (char) message[8] + "|"
                            + (char) message[9] + (char) message[10] + "|"
                            + (char) message[11] + (char) message[12]
                            + (char) message[13] + (char) message[14] + (char) message[15] + (char) message[16]
                            + (char) message[17] + (char) message[18] + (char) message[19] + (char) message[20]
                            + (char) message[21] + (char) message[22] + (char) message[23]);
                    count += 1;
                    if (count > 5) {
                        System.out.println("Please wait a bit before trying again");
                        System.out.println("Listening to Ethernet frames...");
                        while (true) {
                            ethSocket.receive(ethPacket);
                        }
                    }
                } else {
                    System.out.println("Mismatched Destination IP! Package Dropped! ");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
