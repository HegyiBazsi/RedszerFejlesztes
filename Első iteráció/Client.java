import java.io.*;

class Client
{
	public boolean finished;

	public static void main(String[] args)
	{
		new Client().start();
	}

	private void start()
	{
		try
		{
			System.out.println("Kerem valassza ki a belepo tipusat: (1. Diszpecser, 2. Raktaros)");
			int choice = getUserInput();
			switch(choice)
			{
				case 1:
					Diszpecser diszpecser = new Diszpecser();
					finished = false;
					while(! finished)
					{
						diszpecser.showMenu();
						finished = diszpecser.executeServer();
					}
				break;

				case 2:
					Raktaros raktaros = new Raktaros();
					finished = false;
					while(! finished)
					{
						raktaros.showMenu();
						finished=raktaros.executeServer();
					}
				break;
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

	}

	/*---------- I N T   I N P U T ----------*/
	private int getUserInput()throws IOException
	{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line = input.readLine();
		return Integer.valueOf(line);
	}
}
