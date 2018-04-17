import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;

class Raktaros
{
  /*---------- V A L T O Z O K ----------*/
  	private Controller contr;
  	public List<Szallitas> szallitasok;
  	public Szallitas temp;

  	public int db;

  /*----------- C O N T R O L L E R ----------*/
  	public Raktaros(Controller contr)
  	{
  		this.contr = contr;
  	}

  /*----------- V I E W ----------*/
  	public Raktaros()
  	{
  		szallitasok = new ArrayList<Szallitas>();
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
      		szallitasok.clear();
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
      					szallitasok.add(new Szallitas(Integer.parseInt(adat[0]), adat[1], Integer.parseInt(adat[2]),
      					adat[3], Integer.parseInt(adat[4])));
      				}
      				catch(NumberFormatException ex)
      				{
      					System.out.println("Sikertelen beolvasas!");
      				}
      			}
      		}
      	}
      /*---------- L I S T A Z A S ----------*/
      	public void rendelesListazas() throws IOException
      	{
      		read();
      		Iterator<Szallitas> i = szallitasok.iterator();
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

      /*-------B E E R K E Z O  R A K L A P  F E L V E T E L E ---------- */
      private void importfelvet() throws IOException
      {
        read();
        rendelesListazas();
        System.out.println();
    		System.out.println("Modositando tetel szama: ");
    		int number = getUserInput();

    		ListIterator<Szallitas> iter = szallitasok.listIterator();
    		while(iter.hasNext())
    		{
    			if(number == iter.next().getInternalID())
    			{
              int id = number;
              int palets = iter.next().getquantity();
              System.out.println("Raklap szam: ");
              for(int i=0; i<palets; i++)
              {
                System.out.println("Hibas-e? (Igen): ");
        				String name = readUserInput();
              }
          }
        }
      }
}
