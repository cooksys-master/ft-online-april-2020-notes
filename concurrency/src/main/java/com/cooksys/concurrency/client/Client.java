package com.cooksys.concurrency.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Client implements Runnable {
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(new Client()).start();
		}
	}

	@Override
	public void run() {
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e1) {
//			System.out.println("Could not sleep");
//		}
		try (Socket socket = new Socket("localhost", 8080);) {
			JsonParser jsonParser = new JsonFactory().createParser(socket.getInputStream());
			jsonParser.setCodec(new ObjectMapper());
			Message message = jsonParser.readValueAs(Message.class);
			System.out.println("Message recieved from the server: " + message);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
