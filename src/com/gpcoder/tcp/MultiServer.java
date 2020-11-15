package com.gpcoder.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class MultiServer {
	public static final int NUM_OF_THREAD=4;
	public static final int SERVER_PORT=8;
	public static void main(String[] args) throws IOException {
		ExecutorService executor =Executors.newFixedThreadPool(NUM_OF_THREAD);
		ServerSocket serverSocket=null;
		try {
			System.out.println("binding to port "+SERVER_PORT+" ,please wait....");
			serverSocket=new ServerSocket(SERVER_PORT);
			System.out.println("Server started: "+serverSocket);
			System.out.println("Waiting for client...");
			while(true) {
				try {
					Socket socket=serverSocket.accept();
					System.out.println("Client accepted: "+socket);
					WorkerThread handler = new WorkerThread(socket);
					executor.execute(handler);
				}catch(Exception e) {
					System.out.println("Error "+e);
				}
			}
		}
		catch(Exception e1)
		{
			System.out.println("Error "+e1);
		}
		finally {
			if(serverSocket!=null)
				serverSocket.close();
		}

	}

}
