package com.cooksys.concurrency.server;

import java.io.IOException;
import java.net.Socket;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClientHandler implements Runnable {
	
	private Socket socket;
	
	public ClientHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try (JsonGenerator jsonGenerator = new JsonFactory().createGenerator(socket.getOutputStream())) {
			jsonGenerator.setCodec(new ObjectMapper());
			jsonGenerator.writeObject(new Message("Hello World!"));
			jsonGenerator.flush();
			
		
			// { "text": "Hello World!" }
//			jsonGenerator.writeStartObject();
//			jsonGenerator.writeStringField("text", "Hello World!");
//			jsonGenerator.writeEndObject();
//			jsonGenerator.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
