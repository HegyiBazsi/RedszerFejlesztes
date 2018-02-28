import java.io.*;
import java.net.*;

class View
{
	private Controller contr;

	public View()
	{
	}

	public View(Controller contr)
	{
		this.contr = contr;
	}

	public void showMenu()
	{
		System.out.println("Raktar Rendszer");
		System.out.println("1 - Foglalas Felvitele");
		System.out.println("2 - Modositas");
		System.out.println("3 - Torles");
		System.out.println("9 - Exit");
	}

	public boolean executeServer()
	{
		try
		{
			Socket socket = new Socket("localhost", 10000);

			executeUserCommand(socket);

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public boolean executeUserCommand(Socket socket)
	{
		try
		{
			int cmd = getUserInput();
			switch (cmd)
			{
				case 1: FoglalasFelvitele(); break;
				case 2: Modositas(); break;
				case 3: Torles(); break;
				case 9: return true; // exit
				default: Incorrect(); break;
				//contr.execute(cmd);
			}
			// server part
			// 1. connect to server - BLOCKING
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println(cmd);	// send to server
		}
		catch(IOException e)
		{
			System.out.println("Incorrect command.");
		}
		return false;
	}

	private void FoglalasFelvitele()
	{
		System.out.println();
		System.out.println("Kerem Toltse ki a foglalas adatait!");
		System.out.println();
	}

	private void Modositas()
	{
		System.out.println();
		System.out.println("Kerem adja meg a modositando foglalas azonositojat!");
		System.out.println();
	}

	private void Torles()
	{
		System.out.println();
		System.out.println("Kerem adja meg a torlendo foglalas azonositojat!");
		System.out.println();
	}

	private void Incorrect()
	{
		System.out.println();
		System.out.println("Incorrect command.");
		System.out.println();
	}


	private int getUserInput()
		throws IOException
		{
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Select command: ");
			String line = input.readLine();
			return Integer.valueOf(line);
	}
}
