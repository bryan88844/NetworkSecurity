import java.net.*;

public class Node1 {
    public static void main(String[] args) throws Exception {
        // Create a socket to send and receive datagrams
        DatagramSocket socket = new DatagramSocket();

        // Create a message to send
        String message = "Hello, Node2!";
        byte[] buffer = message.getBytes();

        // Create a packet with the destination IP address and port number
        InetAddress address = InetAddress.getByName("255.255.255.255");
        int port = 1234;
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);

        // Send the packet
        socket.send(packet);
        System.out.println("sending packet");
        System.out.println(packet);
        // Close the socket
        // socket.close();
    }
}
