import java.io.*;
import java.net.*;
import java.util.Scanner;

class View
{
	private Controller contr;
	Raklap[] lists = new Raklap[10]; 
	public View()
	{
		lists[0] = new Raklap("Simon","Gyuszi","Hulyeseg megelozo tabletta",100);
		lists[1]  = new Raklap("Heckl","Pista","Homloker kenocs",200);		
	}
			
	public View(Controller contr)
	{
		this.contr = contr;
	}

/*------------M E N U-------------*/
	public void showMenu()
	{
		System.out.println("Raktar Rendszer");
		System.out.println("1 - Foglalas Felvitele");
		System.out.println("2 - Listazas");
		System.out.println("3 - Modositas");
		System.out.println("4 - Torles");	
		System.out.println("9 - Exit");
	}

/*---------E X E C U T E   S E R V E R-------*/	
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

/*------------U S E R   C O M M A N D---------*/
	public boolean executeUserCommand(Socket socket)
	{
		try
		{
			int cmd = getUserInput();
			switch (cmd)
			{				
				case 1: FoglalasFelvitele(); break;
				case 2: Listazas(); break;
				case 3: Modositas(); break;
				case 4: Torles(); break;
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

/*-------------F O G L A L A S   F E L V I T E L E-----------*/
	private void FoglalasFelvitele()
	{
		System.out.println();
		System.out.println("Kerem Toltse ki a foglalas adatait!");	

		try
		{
			System.out.println("\nAdja meg a vezeteknevet: ");
			String new_secondName = readboardInput();	
			System.out.println("\nAdja meg a keresztnevet: ");			
			String new_firstName = readboardInput();			
			System.out.println("\nAdja meg az aru nevet: ");			
			String new_goods = readboardInput();		
			System.out.println("\nAdja meg az aru mennyiseget: ");			
			int new_quantity = keyboardInput();	
					
			for(int i=0; i< lists.length; i++)
			{
				if(lists[i]==null)
				{
					lists[i] = new Raklap(new_firstName, new_secondName, new_goods, new_quantity);
					break;
				}
			}
			System.out.println("Foglalas sikeresen felveve...\n");
		}
		catch(IOException e)
		{
			System.out.println("GIKSZER");
		}
		
		System.out.println();
	}

/*-------------F O G L A L A S   L I S T A Z A S A-----------*/
	private void Listazas()
	{
		try
		{
			PrintWriter pr = new PrintWriter("foglalas.txt");
			for (int i=0; i<lists.length; i++)
			{
				if(lists[i] != null)
				{
					pr.println("\t" + (i+1) + " - " +lists[i].getFirstName() + " " + lists[i].getSecondName() + ", " + lists[i].getGoods() + ", " + lists[i].getQuantity());
				}
			}
			pr.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("GIKSZER");
		}
		
		System.out.println();
		for (int i=0; i<lists.length; i++)
		{
			if(lists[i] != null)
			{
				System.out.println("\t" + (i+1) + " - " +lists[i].getFirstName()+ " " + lists[i].getSecondName()+ ", " + lists[i].getGoods() + ", " + lists[i].getQuantity());
			}
		}
		System.out.println();
	}	

/*-------------F O G L A L A S   M O D O S I T A S A-----------*/
	private void Modositas()
	{
		System.out.println();
		
		int position;
		Scanner input = new Scanner(System.in);
		System.out.println("Kerem adja meg a modositando foglalas azonositojat!");
		position = input.nextInt();
		
		if(position >= lists.length+1)
		{
			System.out.println("Modositas nem lehetseges");
		}
		else
		{
			try
			{
				for(int i = position - 1; i < lists.length - 1; i++)
				{
					lists[i] = lists[i+1];
					
					System.out.println("Uj vezeteknev: ");
					String new_secondName = readboardInput();
					
					System.out.println("Uj keresztnev: ");
					String new_firstName = readboardInput();										
						
					System.out.println("Uj arunev: ");
					String new_goodsName = readboardInput();
					
					System.out.println("Uj mennyiseg: ");
					int new_Quantity = keyboardInput();
		
					lists[i] = new Raklap(new_secondName,new_firstName,new_goodsName,new_Quantity);
					
					break;
				}
			}
			catch(IOException e)
			{
				System.out.println("GEBASZ");
			}
		}				
		System.out.println();
	}

/*-------------F O G L A L A S   T O R L E S E-----------*/
	private void Torles()
	{
		System.out.println();
				
		int position;
		Scanner input = new Scanner(System.in);
		System.out.println("Kerem adja meg a torlendo foglalas azonositojat!");
		position = input.nextInt();
		
		if(position >= lists.length+1)
		{
			System.out.println("Torles nem lehetseges");
		}
		else
		{
			for(int i = position - 1; i < lists.length - 1; i++)
			{
				lists[i] = lists[i+1];
			}
		}		
		System.out.println();
	}

/*------------I N C O R R E C T   C O M M A N D--------*/	
	private void Incorrect()
	{
		System.out.println();
		System.out.println("Incorrect command.");
		System.out.println();
	}

/*---------------I N P U T - C O M M A N D--------------------*/
	private int getUserInput()
	throws IOException
	{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Select command: ");
		String line = input.readLine();
		return Integer.valueOf(line);
	}

//---------------I N P U T - I N T-------------------
	int keyboardInput() throws IOException
	{
		BufferedReader input =new BufferedReader(new InputStreamReader(System.in));			
		String text = input.readLine();
		return Integer.valueOf(text);
	}	
	
//---------------I N P U T - S T R I N G-------------------
	String readboardInput() throws IOException
	{
		BufferedReader input =new BufferedReader(new InputStreamReader(System.in));			
		String text = input.readLine();
		return String.valueOf(text);
	}	
}