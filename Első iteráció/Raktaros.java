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
    public List<Raklap> raklapok;
    public String inputfile="szallitasok.txt";
    public int TerminalStationRows; // minden terminalnal van egy lepakolo allomas ami egy matrix
    public int TerminalStationCols; // aminek ez a ket valtozo a sor es oszlop szama

  	public int db;

  /*----------- C O N T R O L L E R ----------*/
  	public Raktaros(Controller contr)
  	{
  		this.contr = contr;
  	}
    /*---------- S H O W   M E N U ----------*/
    	public void showMenu()
    	{
    		System.out.println();
    		System.out.println("Raktar Rendszer");
        System.out.println("1 - Szallitasok beolvasasa");
        System.out.println("2 - Import Adatok Felvitele");
        System.out.println("3 - Export Adatok Felvitele");
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
  				case 2: importfelvet(); break;
          case 3: exportfelvet(); break;
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
    		BufferedReader be = new BufferedReader(new FileReader(inputfile));
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
        String leftAlignFormat = "| %-4d | %-14s | %-13d | %-13s | %-13d |%n";
        System.out.format("+-----------------------------------------------------------------------+%n");
        System.out.format("| ID   | Supplier name	| Quantity	|Date		|Terminal	| %n");
        System.out.format("+-----------------------------------------------------------------------+%n");
        while(i.hasNext())
        {
          Szallitas element = i.next();
          System.out.format(leftAlignFormat, element.getInternalID(), element.getsupplier_name(), element.getquantity(), element.getDate(), element.getTerminal());
        }
        System.out.format("+-----------------------------------------------------------------------+%n");
      }

    /*-------B E E R K E Z O  R A K L A P  F E L V E T E L E ---------- */
    private void importfelvet() throws IOException
    {
      read();
      rendelesListazas();
      System.out.println();
  		System.out.println("Beerkezo szallitas azonositoja: ");
  		int number = getUserInput();

  		ListIterator<Szallitas> iter = szallitasok.listIterator();
      try
      {
        while(iter.hasNext())
    		{
    			if(number == iter.next().getInternalID())
    			{
              int id = number;
              int palets = iter.next().getquantity();
              System.out.println("Raklap szam: " + palets);
              for(int i=0; i<palets; i++)
              {
                 String suppname=iter.next().getsupplier_name();
                 int shipid=iter.next().getInternalID();
                 String temp=suppname.substring(1,3);
                 String innerId= temp + String.valueOf(shipid) + String.valueOf(i);

                 Raklap tempraklap= new Raklap(suppname,innerId,false);
                 raklapok.add(tempraklap);
              }

              //matrix kiiratas
              System.out.println("Kapuknal levo mezo sorainak szama: ");
          		TerminalStationRows = getUserInput();

              System.out.println("Kapuknal levo mezo oszlopainak szama: ");
          		TerminalStationCols = getUserInput();

              for(int i=0; i<TerminalStationRows; i++)
              {
                for(int j=0; j<TerminalStationCols; j++)
                {

                }
              }
          }
        }
    }
    catch(NullPointerException e)
    {
      System.out.println("Baj van");
    }
    }//END OF FUN

    /*-------K I M E N O   R A K L A P  F E L V E T E L E ---------- */
    private void exportfelvet() throws IOException
    {
      read();
      rendelesListazas();

      System.out.println("Adja meg a beszallito nevet: ");
      String name = readUserInput();

      System.out.println("Adja meg a datumot: ");
      String export_date = readUserInput();

      ListIterator<Szallitas> iter = szallitasok.listIterator();

  		while(iter.hasNext())
      {
        	if( name.equals(iter.next().getsupplier_name()) && export_date.equals(iter.next().getDate()) )
          {
              
          }
      }
    }//END OF FUN

    /*---------- I N C O R R E C T ----------*/
  	private void Incorrect()
  	{
  		System.out.println();
  		System.out.println("Incorrect command.");
  		System.out.println();
  	}
}
