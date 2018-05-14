import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;

class Client
{


	public static void main(String[] args)
	{
		new Client().start();
	}

	/*---------- V A L T O Z O K ----------*/
	public String inputfile="users.txt";
	public List<User> users;
	public boolean finishclient;
	public int choice;
	public boolean finished;
	public int title;

	public Client()
	{
			users = new ArrayList<User>();
	}

	private void start()
	{
		try
		{
			finishclient = false;
			while(! finishclient)
			{
				System.out.print("Kerem valassza ki hogy mit szeretne csinalni(1. Belepes, 2. Regisztracio 3. Kilepes): ");
				choice = getUserInput();
				switch(choice)
				{
					case 1:
						login();
					break;
					case 2:
						register();
					break;
					case 3:
						finishclient= true;
					break;
				}//end of switch
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

	/*---------- S T R I N G   I N P U T ----------*/
  	private String readUserInput()throws IOException
  	{
  		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
  		String line = input.readLine();
  		return line;
  	}

	/*---------- R E A D ----------*/
		private void read() throws IOException
		{
			BufferedReader be = new BufferedReader(new FileReader(inputfile));
			String in = new String("");
			users.clear();
			while(!((in=be.readLine())==null))
			{
				String[] line= in.split("\\,");
				for(int i=0;i<line.length;i++)
				{
					String[] adat = line[i].split("\\|");
					adat[0]=adat[0].substring(1,adat[0].length());
					if(i==line.length-1)
					{
							adat[2]=adat[2].substring(0,adat[2].length()-1);
					}
					try
					{
						users.add(new User(adat[0], adat[1], Integer.parseInt(adat[2])));
					}
					catch(NumberFormatException ex)
					{
						System.out.println("Sikertelen beolvasas!");
					}
				}
			}
		}// end of readLine

		public void login()
		{
			try
			{
				try
				{
						read();
				}
				catch(StringIndexOutOfBoundsException ex)
				{
					System.out.println("Users file is empty!");
				}
			}
			catch(IOException ex)
			{
				System.out.println("Could not find file!");
			}//end of read try

			try
			{
				System.out.println("Kerem adja meg a felhasznalo nevet:");
				String username = readUserInput();
				System.out.println("Kerem adja meg a jelszavat:");
				String password = readUserInput();
				Iterator<User> i = users.iterator();

				while(i.hasNext())
				{
					User element = i.next();
					if((element.getUsername().equals(username)) && ((element.getPassword().equals(password))))
					{
						title = element.getTitle();
					}
				}

				switch(title)
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
						Konyvelo konyvelo = new Konyvelo();
						finished = false;
						while(! finished)
						{
							konyvelo.showMenu();
							finished=konyvelo.executeServer();
						}
					break;
					case 4:
						finishclient= true;
					break;
				}//end of switch
			}// end of main try
			catch(IOException ex)
			{
				System.out.println("Somethin went wrong!");
			}




		}//end of login

		public void register()
		{
			try
			{
				try
				{
						read();
				}
				catch(StringIndexOutOfBoundsException ex)
				{
					System.out.println("Users file is empty!");
				}
			}
			catch(IOException ex)
			{
				System.out.println("Could not find file!");
			}
			try
			{
				System.out.println("Kerem adja meg a felhasznalo nevet:");
				String username = readUserInput();
				System.out.println("Kerem adja meg a jelszavat:");
				String password = readUserInput();
				System.out.println("Kerem adja meg a munkajat ugy ahogy fel van sorolva(Diszpecser/Raktaros/Konyvelo):");
				String designation = readUserInput();
				if(designation.equals("Diszpecser"))
				{
					title=1;
				}
				else if(designation.equals("Raktaros"))
				{
					title=2;
				}
				else if(designation.equals("Konyvelo"))
				{
					title=3;
				}
				else
				{
					System.out.println("Rossz adatot adott meg a munkajara.");
					System.out.println("Kerem adja meg a munkajat ujra ugy ahogy fel van sorolva(Diszpecser/Raktaros/Konyvelo):");
					designation = readUserInput();
					if(designation.equals("Diszpecser"))
					{
						title=1;
					}
					else if(designation.equals("Raktaros"))
					{
						title=2;
					}
					else if(designation.equals("Konyvelo"))
					{
						title=3;
					}
				}// end of kiertekeles

				User temp = new User(username,password,title);
				users.add(temp);
				PrintWriter datafile = new PrintWriter(inputfile);
				datafile.println(users);
				datafile.flush();
				datafile.close();
			}//end of try
			catch(IOException ex)
			{
				System.out.println("Something went wrong!");
			}

		}
}
