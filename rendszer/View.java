import java.io.*;
import java.net.*;
import java.util.*;

class View
{
/*---------- V A L T O Z O K ----------*/	
	private Controller contr;
	public List<Object> o;
	public Raklap c;
	public int db;

/*----------- C O N T R O L L E R ----------*/	
	public View(Controller contr)
	{
		this.contr = contr;
	}

/*----------- V I E W ----------*/	
	public View()
	{
		o = new ArrayList<Object>();
	}
		
/*---------- H O Z Z A A D----------*/		
	public void hozzaad(Object x)
	{
		o.add(x);
	}
		
/*---------- S H O W   M E N U ----------*/		
	public void showMenu()
	{
		System.out.println("Raktar Rendszer");
		System.out.println("0 - Adatok beolvasas");
		System.out.println("1 - Foglalas Felvitele");
		System.out.println("2 - Listazas");
		System.out.println("3 - Modositas");
		System.out.println("4 - Torles");
		System.out.println("9 - Exit");
	}
	
/*---------- E X E C U T E   S E R V E R ----------*/	
	public boolean executeServer()
	{
		try
		{
			Socket socket = new Socket("localhost", 10000);
			int cmd = getUserInput();
			if (cmd == 9)
			{
				return true;
			}
			else
			{
					executeUserCommand(socket,cmd);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
/*---------- E X E   U S E R   C O M M A N D ----------*/	
	public boolean executeUserCommand(Socket socket, int cmd)
	{
		try
		{
			switch (cmd)
			{
				case 0: beolvas(); break;
				case 1: rendelesFelvetel(); break;
				case 2: rendelesListazas(); break;
				//case 3: Modositas(); break;
				case 4: Torles(); break;
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
		BufferedReader be = new BufferedReader(new FileReader("be.txt"));
		String in = new String("");
		while(!((in=be.readLine())==null))
		{
			String[] adat = in.split("\\|");
			try
			{
				o.add(new Raklap(adat[0], adat[1], Integer.parseInt(adat[2])));
			}
			catch(NumberFormatException ex)
			{
				System.out.println("Wrong!");
			}
		}
	}
		
/*---------- B E O L V A S ----------*/		
	public void beolvas() throws IOException
	{
		read();
		Iterator<Object> i = o.iterator();
		Object a;
		while(i.hasNext())
		{
			a=i.next();
			if(a instanceof Raklap)
			{
				System.out.println(a);
			}
		}
	}
	
/*----------F E L V E T E L----------*/	
	private void rendelesFelvetel() throws IOException
	{
		System.out.println("Nev: ");
		String name = readUserInput();
			
		System.out.println("Termek: ");
		String goods = readUserInput();
			
		System.out.println("Mennyiseg: ");
		int quantity = getUserInput();
			
		c = new Raklap(name, goods, quantity);
		o.add(c);
				
		PrintWriter datafile = new PrintWriter("ki.txt");
		datafile.println(o);
		datafile.flush();
		datafile.close();
	}
		
/*---------- L I S T A Z A S ----------*/		
	public void rendelesListazas() throws IOException
	{
		Iterator<Object> i = o.iterator();
		Object a;
		while(i.hasNext())
		{
			a=i.next();
			if(a instanceof Raklap)
			{
				System.out.println(a);
			}
		}
	}
		
/*---------- T O R L E S ----------*/	

	public void Torles() throws IOException
	{
		System.out.println("Torlendo tetel szama: ");
		int number = getUserInput(); 	

		Iterator<Object> i = o.iterator();
		while (i.hasNext()) 
		{
			Object o = i.next();
			if(o.equals(number))
			{
				i.remove();
			}
		}		
	}

/*---------- I N C O R R E C T ----------*/
	private void Incorrect()
	{
		System.out.println();
		System.out.println("Incorrect command.");
		System.out.println();
	}
}//END OF CLASS