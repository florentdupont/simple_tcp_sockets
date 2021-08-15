package com.company;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


// https://stackoverflow.com/questions/27014955/socket-connect-vs-bind
public class Server {

    private static final boolean USE_BIND_ADDRESS = false;
    private static final String LOCAL_IP = "192.168.1.12"; 
    
    
    public static void main(String[] args) throws IOException {

        
        ServerSocket serverSocket;
        if(USE_BIND_ADDRESS) {
            String[] strings = LOCAL_IP.split("\\.");
            byte[] bytes = new byte[4];
            for(var i = 0; i < strings.length; i++) {
                // les Byte sont forcément signed.
                bytes[i] = (byte) Integer.parseInt(strings[i]);
            }
            InetAddress inetAddress = InetAddress.getByAddress(bytes);  
            serverSocket = new ServerSocket(8080, 50, inetAddress);    
        }
         else {
            serverSocket = new ServerSocket(8080);      
        }
       
        Socket socket = serverSocket.accept();

        // recoit les données de la requête
        InputStream input = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line = reader.readLine();    // reads a line of text
        System.out.println("received : " + line);

              
        // envoi la réponse
        OutputStream output = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(output, true);
        writer.println("<html><head></head><body>hello</body></html>");

        socket.close();

    }
}
