package server;


import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;

import ejercicioServer.socketio.socketIO.SocketIOModule;

public class App {
	
	private static final String HOST_NAME = "localhost";
	private static final int PORT = 3000;
	
	public static void main(String[] args) {
		
		// Server configuration 
		Configuration config = new Configuration ();
		config.setHostname(HOST_NAME);
		config.setPort(PORT);
		
		// We start the server
		SocketIOServer server = new SocketIOServer(config);
		SocketIOModule module = new SocketIOModule(server);
		module.start();
	}
}