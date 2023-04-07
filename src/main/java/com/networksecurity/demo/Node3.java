package com.networksecurity.demo;
import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Node3 {

    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String SECRET_KEY_ALGORITHM = "AES";
    private static final String HASH_ALGORITHM = "SHA-256";

    // Concatenate two byte arrays
    private static byte[] concatenateByteArrays(byte[] a, byte[] b) {
        int aLen = a.length;
        int bLen = b.length;
        byte[] c = new byte[aLen + bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }

    // Hash data using SHA-256
    private static byte[] hashData(byte[] data) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
        return messageDigest.digest(data);
    }

    // Encrypt data using AES with CBC mode and PKCS5Padding
    private static byte[] encryptData(byte[] data, SecretKeySpec secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] iv = cipher.getIV();
        byte[] encryptedData = cipher.doFinal(data);
        return concatenateByteArrays(iv, encryptedData);
    }

    // Decrypt data using AES with CBC mode and PKCS5Padding
    private static byte[] decryptData(byte[] encryptedData, SecretKeySpec secretKey) throws Exception {
        byte[] iv = Arrays.copyOfRange(encryptedData, 0, 16);
        byte[] data = Arrays.copyOfRange(encryptedData, 16, encryptedData.length);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
        return cipher.doFinal(data);
    }
    
    public static void main(String[] args) throws Exception {
        // Initialize variables
        String routerIp = "192.168.2.1";
        int routerPort = 5000;
        int node3Port = 8000;
        byte[] secretKey = "secretpassword".getBytes();

        // Create socket and streams for sending data to the router
        DatagramSocket routerSocket = new DatagramSocket();
        InetAddress routerAddress = InetAddress.getByName(routerIp);
        ByteArrayOutputStream routerOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream routerObjectOutputStream = new ObjectOutputStream(routerOutputStream);

        // Create socket and streams for receiving data from the router
        DatagramSocket node3Socket = new DatagramSocket(node3Port);
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

        while (true) {
            // Send message to node 4 via router
            String message = "Hello from node 3!";
            byte[] hashedMessage = hashData(message.getBytes());
            byte[] encryptedMessage = encryptData(concatenateByteArrays(message.getBytes(), hashedMessage), secretKey);
            routerOutputStream.reset();
            routerObjectOutputStream.writeObject(encryptedMessage);
            byte[] sendData = routerOutputStream.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, routerAddress, routerPort);
            routerSocket.send(sendPacket);
            System.out.println("Sent message to node 4: " + message);

            // Receive message from node 4 via router
            node3Socket.receive(receivePacket);
            byte[] receivedData = Arrays.copyOf(receivePacket.getData(), receivePacket.getLength());
            byte[] decryptedData = decryptData(receivedData, secretKey);
            byte[] receivedHash = Arrays.copyOfRange(decryptedData, decryptedData.length - 32, decryptedData.length);
            byte[] receivedMessage = Arrays.copyOfRange(decryptedData, 0, decryptedData.length - 32);
            byte[] calculatedHash = hashData(receivedMessage);

            if (!Arrays.equals(receivedHash, calculatedHash)) {
                // Hashes don't match - data has been tampered with
                System.out.println("Data has been tampered with!");
                continue;
            }

            // Display received message
            System.out.println("Received message from node 4: " + new String(receivedMessage));
        }
    }
}



