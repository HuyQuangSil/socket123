package com.gpcoder.tcp;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	public final static int SERVER_PORT=7;
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket=null;
		try {
			System.out.println("Binding to Port "+SERVER_PORT+",please waiting....");
			serverSocket=new ServerSocket(SERVER_PORT);
			System.out.println("Server started: "+serverSocket);
			System.out.println("waiting for client....");
			while(true) {
				try {
					Socket socket=serverSocket.accept();
					System.out.println("Client accepted: "+socket);
					
					OutputStream os=socket.getOutputStream();
					InputStream is=socket.getInputStream();
					
					int ch=0;
					while(true) {
						ch=is.read(); // received from client
						if(ch==-1) { // not received data from client anymore
							break;
						}
						os.write(ch); // send result to client
					}
					socket.close();
				}catch(Exception e)
				{
					System.out.println("Connection error "+e);
				}
			}
		}
		catch(Exception e1)
		{
			System.out.println("Error");
			System.out.println(e1);
		}
		finally {
			if(serverSocket!=null)
				serverSocket.close();
		}

	}

}
