package com.gpcoder.tcp;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	public final static String SERVER_IP="127.0.0.1";
	public final static int SERVER_PORT=8;
	
	public static void main(String[] args) throws IOException,InterruptedException {
		Socket socket=null;
		Scanner scanner=new Scanner(System.in);
		try {
			socket=new Socket(SERVER_IP,SERVER_PORT); // connect to server
			System.out.println("Connected "+socket);
			InputStream is =socket.getInputStream();
			OutputStream os=socket.getOutputStream();
			for(int i='0';i<'9';i++)
			{
				os.write(i); // send each number to server
				int ch=is.read(); // waiting for result of the server
				System.out.println((char)ch+" "); // display the result received from the server
				Thread.sleep(1000);
				
			}
		}
		catch(Exception e){
			System.out.println("Error is "+e);
		}
		finally {
			if(socket!=null)
				socket.close();
		}

	}

}
