import java.io.*;
import java.net.*;

class Server
{
	public static void main(String[] args)
	{
		new Server().start();
	}

	private void start()
	{
		System.out.println("SERVER...");
		Controller contr = new Controller(new Model());
		Socket client = null;
		try
		{
			// 1. register server
			ServerSocket server = new ServerSocket(10000);
			// 2. servicing loop
			while(true){
				client = server.accept();
				System.out.println("client connected");
				BufferedReader input =new BufferedReader(new InputStreamReader(client.getInputStream()));
				String cmd = input.readLine();
				System.out.println("command: " + cmd);				
				// send back to client
				PrintWriter out =new PrintWriter(client.getOutputStream(), true);				
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				client.close();
			}
			catch(IOException e)
			{
			}
		}
	}
}