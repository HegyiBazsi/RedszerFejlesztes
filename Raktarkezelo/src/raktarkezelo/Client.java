/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raktarkezelo;

/**
 *
 * @author hegyi
 */
import java.io.*;

public class Client
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
			boolean finishclient = false;
			while(! finishclient)
			{
				System.out.println("Kerem valassza ki a belepo tipusat: (1. Diszpecser, 2. Raktaros 3. Kilepes)");
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
					case 3:
						finishclient= true;
					break;
				}
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

