package app;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public abstract class Connection {
	//Create Thread object
	private ConnectionThread connThread = new ConnectionThread();
	
	//Object received from a consumer
	private Consumer<Serializable> onReceiveCallBack;
	
	public Connection(Consumer<Serializable> onReceiveCallBack) {
		this.onReceiveCallBack = onReceiveCallBack;
		
		//Set Deamon to true so that while loop in ConnectionThread doesn't block JVM from exiting
		connThread.setDaemon(true);
	}
	
	//Start the connection
	public void startConnection() throws Exception {
		connThread.start();
	}
	
	//Send data
	public void send(Serializable data) throws Exception {
		connThread.out.writeObject(data);
	}
	
	//Close connection
	public void closeConnection() throws Exception {
		connThread.socket.close();
	}
	
	//Variables for the nested class "ConnectionThread"
	protected abstract boolean isServer();
	protected abstract String getIP();
	protected abstract int getPort();
	
	private class ConnectionThread extends Thread {
		private Socket socket;
		private ObjectOutputStream out;
		
		//Override method "run" to make Thread executable
		@Override
		public void run() {
			//Instantiate server is isServer only
			try (ServerSocket server = isServer() ? new ServerSocket(getPort()) : null;
				//Accept the connection is it's server, else instantiate the client
				//The method blocks execution until connection is made
				 Socket socket = isServer() ? server.accept() : new Socket(getIP(), getPort());
				//Receive objects / data
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				//Send objects / data
				 ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());) 
			{
				
				this.socket = socket;
				this.out = out;
				
				//Disable buffering / do not wait for buffer to fill up
				socket.setTcpNoDelay(true);
				
				
				while(true) {
					//Data represents the object received from the other end
					Serializable data = (Serializable)in.readObject();
					onReceiveCallBack.accept(data);
				}
				
			} catch(Exception e) {
				onReceiveCallBack.accept("Connection closed.");
			}
		}
	}
}
