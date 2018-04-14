import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;

class Diszpecser
{
/*---------- V A L T O Z O K ----------*/
	private Controller contr;
	public List<Szallitas> o;
	public Szallitas c;
	public int db;

/*----------- C O N T R O L L E R ----------*/
	public Diszpecser(Controller contr)
	{
		this.contr = contr;
	}

/*----------- V I E W ----------*/
	public Diszpecser()
	{
		o = new ArrayList<Szallitas>();
	}

/*---------- H O Z Z A A D----------*/
	public void hozzaad(Szallitas x)
	{
		o.add(x);
	}

/*---------- S H O W   M E N U ----------*/
	public void showMenu()
	{
		System.out.println();
		System.out.println("Raktar Rendszer");
		System.out.println("1 - Adatok beolvasas");
		System.out.println("2 - Foglalas Felvitele");
		System.out.println("3 - Listazas");
		System.out.println("4 - Modositas");
		System.out.println("5 - Torles");
		System.out.println("9 - Exit");
	}

/*---------- E X E C U T E   S E R V E R ----------*/
	public boolean executeServer()
	{
		try
		{
			Socket socket = new Socket("localhost", 10000);
			int cmd = getUserInput();
			System.out.println();
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
				case 1: read(); break;
				case 2: rendelesFelvetel(); break;
				case 3: rendelesListazas(); break;
				case 4: Modositas(); break;
				case 5: Torles(); break;
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

/*----------Main Finctions-----*/
/*---------- R E A D ----------*/
	private void read() throws IOException
	{
		BufferedReader be = new BufferedReader(new FileReader("ki.txt"));
		String in = new String("");
		o.clear();
		while(!((in=be.readLine())==null))
		{
			String[] line= in.split("\\,");
			for(int i=0;i<line.length;i++)
			{
				String[] adat = line[i].split("\\|");
				adat[0]=adat[0].substring(1,adat[0].length());
				if(i==line.length-1)
				{
						adat[4]=adat[4].substring(0,adat[4].length()-1);
				}
				try
				{
					o.add(new Szallitas(Integer.parseInt(adat[0]), adat[1], Integer.parseInt(adat[2]),
					adat[3], Integer.parseInt(adat[4])));
				}
				catch(NumberFormatException ex)
				{
					System.out.println("Sikertelen beolvasas!");
				}
			}
		}
	}

/*----------F E L V E T E L----------*/
	private void rendelesFelvetel() throws IOException
	{
		read();
		int max = 0;

		Iterator<Szallitas> i = o.iterator();
		while(i.hasNext())
		{
			Szallitas element = i.next();
			if(element.getInternalID() > max)
			{
				max = element.getInternalID();
			}
		}

		int id = max+1;

		System.out.println("Beszallito neve: ");
		String name = readUserInput();

		System.out.println("Raklap szam: ");
		int quantity = getUserInput();

		System.out.println("Datum: [engedett formatum = 2018.04.12. vagy 2018-04-12]");
		String date = readUserInput();

		System.out.println("Terminal szama: ");
		int terminal = getUserInput();

		c = new Szallitas(id, name, quantity, date, terminal);
		o.add(c);

		PrintWriter datafile = new PrintWriter("ki.txt");
		datafile.println(o);
		datafile.flush();
		datafile.close();
	}

/*---------- L I S T A Z A S ----------*/
	public void rendelesListazas() throws IOException
	{
		read();
		Iterator<Szallitas> i = o.iterator();
		Object a;
		while(i.hasNext())
		{
			a=i.next();
			if(a instanceof Szallitas)
			{
				System.out.println(a);
			}
		}
	}
/*----------M O D O S I T A S--------*/
	public void Modositas() throws IOException
	{
		read();
		//rendelesListazas();
		System.out.println();
		System.out.println("Modositando tetel szama: ");
		int number = getUserInput();

		ListIterator<Szallitas> iter = o.listIterator();
		while(iter.hasNext())
		{
			if(number == iter.next().getInternalID())
			{
				int id = number;
				System.out.println("Nev: ");
				String name = readUserInput();

				System.out.println("Raklap szam: ");
				int quantity = getUserInput();

				System.out.println("Datum: [engedett formatum = 2018.04.12. vagy 2018-04-12]");
				String date = readUserInput();

				System.out.println("Terminal szama: ");
				int terminal = getUserInput();

				c = new Szallitas(id, name, quantity, date, terminal);
				iter.set(c);
			}
		}
		PrintWriter datafile = new PrintWriter("ki.txt");
		datafile.println(o);
		datafile.flush();
		datafile.close();
	}

/*---------- T O R L E S ----------*/

	public void Torles() throws IOException
	{
		read();
		rendelesListazas();
		System.out.println("Torlendo tetel szama: ");
		int number = getUserInput();

		ListIterator<Szallitas> iter = o.listIterator();
		while(iter.hasNext())
		{
    	if(number == iter.next().getInternalID())
			{
        iter.remove();
    	}
		}
		PrintWriter datafile = new PrintWriter("ki.txt");
		datafile.println(o);
		datafile.flush();
		datafile.close();
	}

/*---------- I N C O R R E C T ----------*/
	private void Incorrect()
	{
		System.out.println();
		System.out.println("Incorrect command.");
		System.out.println();
	}
}//END OF CLASS
