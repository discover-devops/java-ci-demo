package com.demo;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;

public class HelloWorld {

    public static void main(String[] args) throws Exception {

        String version = "Version 2 - Deployed via Jenkins CI/CD";
        String deployTime = LocalDateTime.now().toString();

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/", new HttpHandler() {
            public void handle(HttpExchange exchange) {
                try {

                    String response = "Hello from Jenkins CI/CD Pipeline\n"
                                    + version + "\n"
                                    + "Deployment Time: " + deployTime;

                    exchange.sendResponseHeaders(200, response.length());

                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        server.setExecutor(null);
        server.start();

        System.out.println("Server started on port 8000");
        System.out.println("Application Version: " + version);
    }
}