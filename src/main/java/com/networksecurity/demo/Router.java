import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Router {

    public static void main(String[] args) throws Exception {
        // Router settings
        String node1Ip = "192.168.0.1";
        int node1Port = 8888;
        String node2Ip = "192.168.0.2";
        int node2Port = 8888;
        String node3Ip = "192.168.1.1";
        int node3Port = 8888;
        String node4Ip = "192.168.1.2";
        int node4Port = 8888;
        byte[] secretKey = "MySecretKey12345".getBytes();

        // Initialize sockets
        DatagramSocket node1Socket = new DatagramSocket();
        DatagramSocket node2Socket = new DatagramSocket();
        DatagramSocket node3Socket = new DatagramSocket();
        DatagramSocket node4Socket = new DatagramSocket();

        // Receive message from node 1 or node 3
        while (true) {
            // Receive message from node 1 or node 3
            byte[] receivedData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receivedData, receivedData.length);
            node1Socket.receive(receivePacket);
            receivedData = Arrays.copyOf(receivePacket.getData(), receivePacket.getLength());
            byte[] decryptedData = decryptData(receivedData, secretKey);
            byte[] receivedHash = Arrays.copyOfRange(decryptedData, decryptedData.length - 32, decryptedData.length);
            byte[] message = Arrays.copyOfRange(decryptedData, 0, decryptedData.length - 32);
            byte[] calculatedHash = hashData(message);

            if (!Arrays.equals(receivedHash, calculatedHash)) {
                // Hashes don't match - data has been tampered with
                System.out.println("Data has been tampered with!");
                return;
            }

            // Display received message
            System.out.println("Received message from node 1: " + new String(message));

            // Send message to node 2 or node 4
            if (message[0] == '3') {
                // Message from node 1 is for node 3 - send via node 4
                byte[] encryptedMessage = encryptData(concatenateByteArrays(message, calculatedHash), secretKey);
                InetAddress node4Address = InetAddress.getByName(node4Ip);
                DatagramPacket sendPacket = new DatagramPacket(encryptedMessage, encryptedMessage.length, node4Address, node4Port);
                node4Socket.send(sendPacket);
                System.out.println("Sent message from node 1 to node 4 via node 2: " + new String(message));
            } else {
                // Message from node 1 is for node 2 - send directly
                byte[] encryptedMessage = encryptData(concatenateByteArrays(message, calculatedHash), secretKey);
                InetAddress node2Address = InetAddress.getByName(node2Ip);
                DatagramPacket sendPacket = new DatagramPacket(encryptedMessage, encryptedMessage.length, node2Address, node2Port);
                node2Socket.send(sendPacket);
                System.out.println("Sent message from node 1 to node 2: " + new String(message));
            }
            
            // Receive message from node 3
            receivedHash = Arrays.copyOfRange(decryptedData, decryptedData.length - 32, decryptedData.length);
            message = Arrays.copyOfRange(decryptedData, 0, decryptedData.length - 32);
            calculatedHash = hashData(message);

            if (!Arrays.equals(receivedHash, calculatedHash)) {
                // Hashes don't match - data has been tampered with
                System.out.println("Data has been tampered with!");
                return;
            }

            // Display received message
            System.out.println("Received message from node 3: " + new String(message));

            // Send message to node 4 or node 1
            if (message[0] == '2') {
                // Message from node 3 is for node 2 - send directly
                byte[] encryptedMessage = encryptData(concatenateByteArrays(message, calculatedHash), secretKey);
                InetAddress node2Address = InetAddress.getByName(node2Ip);
                DatagramPacket sendPacket = new DatagramPacket(encryptedMessage, encryptedMessage.length, node2Address, node2Port);
                node2Socket.send(sendPacket);
                System.out.println("Sent message from node 3 to node 2: " + new String(message));
            } else {
                // Message from node 3 is for node 1 - send via node 4
                byte[] encryptedMessage = encryptData(concatenateByteArrays(message, calculatedHash), secretKey);
                InetAddress node4Address = InetAddress.getByName(node4Ip);
                DatagramPacket sendPacket = new DatagramPacket(encryptedMessage, encryptedMessage.length, node4Address, node4Port);
                node4Socket.send(sendPacket);
                System.out.println("Sent message from node 3 to node 1 via node 4: " + new String(message));
            }
        }
    }

    // Hashes the given data using SHA-256 and returns the hash
    private static byte[] hashData(byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(data);
    }

    // Encrypts the given data using AES and the given key and returns the encrypted data
    private static byte[] encryptData(byte[] data, byte[] key) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher.doFinal(data);
    }

    // Decrypts the given data using AES and the given key and returns the decrypted data
    private static byte[] decryptData(byte[] encryptedData, byte[] key) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return cipher.doFinal(encryptedData);
    }

    // Concatenates two byte arrays into one
    private static byte[] concatenateByteArrays(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }
}
            
