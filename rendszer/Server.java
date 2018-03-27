import java.io.*;
import java.net.*;
import java.util.*;

class Server
{
  private ServerThread newServer;
	private static List<ServerThread> clients;
	public static void main(String[] args)
	{
		Server sr = new Server();
		clients = new ArrayList();
		sr.serve();
	}

	void serve()
	{
		System.out.println("Server is running...");
		try
		{
			ServerSocket server = new ServerSocket(10000);
			while(true){
				Socket temp = server.accept();
				newServer = new ServerThread(temp);
				clients.add(newServer);
				newServer.start();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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

  public static void remove(ServerThread toRemove)
  {
    clients.remove(toRemove);
  }
}
