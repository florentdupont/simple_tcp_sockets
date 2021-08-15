package com.company;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("192.168.1.12", 8080);

        OutputStream output = socket.getOutputStream();

        // envoyer des données
        PrintWriter writer = new PrintWriter(output, true);
        writer.println("GET /index.html");

        // lit les données
        InputStream input = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line = reader.readLine();    // reads a line of text
        System.out.println("received : "  + line );

        socket.close();

    }
}
