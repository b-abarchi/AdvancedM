package app;

import java.io.Serializable;
import java.util.function.Consumer;

public class Client extends Connection {

	private String IP;
	private int port;
	
	public Client(Consumer<Serializable> onReceiveCallBack, String IP, int port) {
		//Call constructor of superclass
		super(onReceiveCallBack);
		
		//IP address of the server
		this.IP = IP;
		
		//Connection port
		this.port = port;
	}

	@Override
	protected boolean isServer() {
		return false;
	}

	@Override
	protected String getIP() {
		return IP;
	}

	@Override
	protected int getPort() {
		return port;
	}

}
