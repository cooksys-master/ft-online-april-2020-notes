package com.cooksys.concurrency.server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	
	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(8080)) {
			while (true) {
				new Thread(new ClientHandler(serverSocket.accept())).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
