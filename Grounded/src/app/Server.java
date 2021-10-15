package app;

import java.io.Serializable;
import java.util.function.Consumer;

public class Server extends Connection {

	private int port;
	
	public Server(Consumer<Serializable> onReceiveCallBack, int port) {
		//Call constructor of superclass
		super(onReceiveCallBack);
		
		//Incoming connection port
		this.port = port;
	}

	@Override
	protected boolean isServer() {
		return true;
	}

	@Override
	protected String getIP() {
		return null;
	}

	@Override
	protected int getPort() {
		return port;
	}

}
