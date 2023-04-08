import java.net.*;

import javax.xml.crypto.Data;

public class Node1 {
    public static void main(String[] args) throws Exception {
        // Create a socket to send and receive datagrams
        DatagramSocket socket = new DatagramSocket();

        // Create a message to send
        String message = "Hello, Node3!";
        byte[] buffer = message.getBytes();
        String sourceIP = "0x1A";
        String destIP = "0x2B";



        DatagramPacket IPpacket = new DatagramPacket(sourceIP, destIP, buffer, buffer.length);

        String sourcemac = "N1";
        String destmac = "R1";

        DatagramPacket ethFrame = new DatagramPacket(sourcemac, destmac, IPpacket);
        socket.send(ethFrame);
        System.out.println("sending packet");
        System.out.println(ethFrame);
    }
}
