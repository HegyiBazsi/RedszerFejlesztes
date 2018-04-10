import java.io.*;

class Client
{
	public static void main(String[] args)
	{
		new Client().start();
	}

	private void start()
	{
		Diszpecser diszpecser = new Diszpecser();
		boolean finished = false;
		while(! finished)
		{
			diszpecser.showMenu();
			finished = diszpecser.executeServer();
		}
	}
}
