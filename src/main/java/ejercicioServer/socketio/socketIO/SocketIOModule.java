package ejercicioServer.socketio.socketIO;




import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.google.gson.JsonObject;


import database.entity.Alumno;
import database.entity.DDBBSimulada;
import ejercicioServer.sockerio.model.MessageInput;
import ejercicioServer.sockerio.model.MessageOutput;
import ejercicioServer.sokcetio.config.Events;
import ejercicioServer.sokcetio.config.MessageCode;

import com.google.gson.Gson;

/**
 * Server control main configuration class
 */
public class SocketIOModule {

	// The server
	private SocketIOServer server = null;

	public SocketIOModule(SocketIOServer server) {
		super();
		this.server = server;

		// Default events (for control the connection of clients)
		server.addConnectListener(onConnect());
		server.addDisconnectListener(onDisconnect());

		// Custom events
		server.addEventListener(Events.ON_BUSCAR_TELEFONO.value, MessageInput.class, this.buscarTelefono());
		server.addEventListener(Events.ON_ANNIADIR_ALUMNO.value, MessageInput.class, this.anniadirAlumno());
		server.addEventListener(Events.ON_BORRAR_ALUMNO.value, MessageInput.class, this.borrarAlumno());
		server.addEventListener(Events.ON_MODIFICAR_ALUMNO.value, MessageInput.class, this.modificarAlumno());
		server.addEventListener(Events.ON_RESPUESTA_SERVIDOR.value, MessageInput.class, this.respuestaServidor());
	}

	private DataListener<MessageInput> respuestaServidor() {
		// TODO Auto-generated method stub
		return null;
	}

	private DataListener <MessageInput>modificarAlumno() {
		// TODO Auto-generated method stub
		return null;
	}

	private DataListener<MessageInput> borrarAlumno() {
		// TODO Auto-generated method stub
		return null;
	}

	private DataListener <MessageInput>anniadirAlumno() {
		// TODO Auto-generated method stub
		return null;
	}

	private DataListener<MessageInput> buscarTelefono() {
		return ((client, data, ackSender) -> {
			System.out.println("Client  " + client.getRemoteAddress() + " quiere buscar un telefono");
			
			String message = data.getMessage();
			
			Gson gson = new Gson();
			JsonObject jsonObject = gson.fromJson(message,JsonObject.class );
			String nombre = jsonObject.get("nombre").getAsString();
			String apellido = jsonObject.get("apellido").getAsString();
			
			Alumno alumno = DDBBSimulada.getintstance().buscarAlumno(nombre, apellido);
			
			if(null == alumno) {
			String answerMessage = gson.toJson(MessageCode.ON_TELEFONO_NO_ENCONTRADO);
			MessageOutput messageOutput = new MessageOutput(answerMessage);
			client.sendEvent(Events.ON_RESPUESTA_SERVIDOR.value,messageOutput);
			}else {
				String answerMessage = gson.toJson(alumno);
				MessageOutput messageOutput = new MessageOutput(answerMessage);
				client.sendEvent(Events.ON_TELEFONO_ENCONTRADO.value,messageOutput);
			}
			
		});
	}

	// Default events

	private ConnectListener onConnect() {
		return (client -> {
			client.joinRoom("default-room");
			System.out.println("New connection, Client: " + client.getRemoteAddress());
		});
	}

	private DisconnectListener onDisconnect() {
		return (client -> {
			client.leaveRoom("default-room");
			System.out.println(client.getRemoteAddress() + " disconected from server");
		});
	}

	// Custom events

//	private DataListener<MessageInput> login() {
//		return ((client, data, ackSender) -> {
//			System.out.println("Client from " + client.getRemoteAddress() + " wants to login");
//
//			// The JSON message from MessageInput
//			String message = data.getMessage();
//
//			// We parse the JSON into an JsonObject
//			// The JSON should be something like this: {"message": "patata"}
//			Gson gson = new Gson();
//			JsonObject jsonObject = gson.fromJson(message, JsonObject.class);
//			String userName = jsonObject.get("message").getAsString();
//
//			// We access to database and...
//			// Let's say it answers with this...
//			Alumno alumno = new Alumno(0, userName, "potato", 213141321);
//
//			// We parse the answer into JSON
//			String answerMessage = gson.toJson(alumno);
//
//			// ... and we send it back to the client inside a MessageOutput
//			MessageOutput messageOutput = new MessageOutput(answerMessage);
//			client.sendEvent(Events..value, messageOutput);
//		});
//	}
//
//	private DataListener<MessageInput> getAll() {
//		return ((client, data, ackSender) -> {
//			// This time, we simply write the message in data
//			System.out.println("Client from " + client.getRemoteAddress() + " wants to getAll");
//
//			// We access to database and... we get a bunch of people
//			List<Alumno> alumnos = new ArrayList<Alumno>();
//			alumnos.add(new Alumno(0, "patata", "potato", "pass", 20));
//			alumnos.add(new Alumno(1, "patata2", "potato2", "pass2", 22));
//			alumnos.add(new Alumno(2, "patata3", "potato3", "pass3", 23));
//
//			// We parse the answer into JSON
//			String answerMessage = new Gson().toJson(alumnos);
//
//			// ... and we send it back to the client inside a MessageOutput
//			MessageOutput messageOutput = new MessageOutput(answerMessage);
//			client.sendEvent(Events.ON_GET_ALL_ANSWER.value, messageOutput);
//		});
//	}
//
//	private DataListener<MessageInput> logout() {
//		return ((client, data, ackSender) -> {
//			// This time, we simply write the message in data
//			System.out.println("Client from " + client.getRemoteAddress() + " wants to logout");
//
//			// The JSON message from MessageInput
//			String message = data.getMessage();
//
//			// We parse the JSON into an JsonObject
//			// The JSON should be something like this: {"message": "patata"}
//			Gson gson = new Gson();
//			JsonObject jsonObject = gson.fromJson(message, JsonObject.class);
//			String userName = jsonObject.get("message").getAsString();
//
//			// We do something on dataBase? ¯_(ツ)_/¯
//
//			System.out.println(userName + " loged out");
//		});
//	}
	
	// Server control 

	public void start() {
		server.start();
		System.out.println("Server started...");
	}

	public void stop() {
		server.stop();
		System.out.println("Server stopped");
	}
}