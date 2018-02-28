import java.io.*;

class Client
{

	public static void main(String[] args)
	{
		new Client().start();
	}

	private void start()
	{
		View view = new View();
		boolean finished = false;
		while(! finished)
		{
			view.showMenu();
			finished = view.executeServer();
		}
	}
}
