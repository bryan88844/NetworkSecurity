import java.net.*;

public class Node3 {
    public static void main(String[] args) throws Exception {
        // Create a socket to receive datagrams
        DatagramSocket socket = new DatagramSocket(1235);

        // Create a buffer to store the received message
        byte[] buffer = new byte[256];

        // Create a packet to receive the message
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        // Receive the packet
        System.out.println("receiving");
        socket.receive(packet);
        System.out.println("packet received");

        // Convert the message to a string and print it
        String message = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Received message: " + message);

        // Close the socket
        socket.close();
    }
}
