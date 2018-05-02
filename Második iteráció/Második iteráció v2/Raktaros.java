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
    public List<Raklap> polcok;
    public Raklap tempraklap;
    public String inputfile="szallitasok.txt";

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
        System.out.println("2 - Raklapok beszallitasanak rogzitese");
        System.out.println("3 - Raklapok Listazasa");
        System.out.println("4 - Hibas raklapok rogzitese");
        System.out.println("5 - Raklapok kiszallitasanak rogzitese");
    		System.out.println("9 - Exit");
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
            case 3: raklapListazas(); break;
            case 4: hibakFelvitele(); break;
            case 5: exportfelvet(); break;
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
      //kiszallitasok = new ArrayList<Szallitas>();
      polcok = new ArrayList<Raklap>();

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

        /*---------- L I S T A Z A S ----------*/
        	public void raklapListazas() throws IOException
        	{
        		Iterator<Raklap> i = polcok.iterator();
        		String leftAlignFormat = "| %-9s | %-17s | %-13s | %-13s |%n";

        		System.out.format("+-----------------------------------------------+%n");
        		System.out.format("| InnerID   | Supplier name	| Supply ID | Damaged	| %n");
        		System.out.format("+-----------------------------------------------+%n");
        		while(i.hasNext())
        		{
        			Raklap element = i.next();
        		  System.out.format(leftAlignFormat, element.getInnerId(), element.getSupplierName(), element.getSupplID(), element.getHibase());
        		}
        		System.out.format("+-----------------------------------------------+%n");
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

      System.out.println("number = " + number);

        while(iter.hasNext())
    		{
          Szallitas element = iter.next();

    			if(number == element.getInternalID())
    			{
              System.out.println("internalid = " + element.getInternalID());
              int id = number;
              int palets = element.getquantity();
              System.out.println("Raklap szam: " + palets);

              for(int i=0; i<palets; i++)
              {
                 String suppname=element.getsupplier_name();
                 int shipid=element.getInternalID();
                 String temp=suppname.substring(0,2);
                 String innerId= temp + String.valueOf(shipid) + String.valueOf(i);
                 try
                 {
                   polcok.add(new Raklap(suppname,innerId,id,false));
                 }
                 catch(NoSuchMethodError ex)
                 {
                   System.out.println("Sikertelen beolvasas!");
                 }
              }
              //H I B A K   R O G Z I T E S E
              hibakFelvitele();

          }
        }//while

    }

    /*-------K I M E N O  R A K L A P  F E L V E T E L E ---------- */
    private void exportfelvet() throws IOException
    {
      read();
      rendelesListazas();
      System.out.println();
  		System.out.println("Kimeno szallitas azonositoja: ");
  		int number = getUserInput();

  		ListIterator<Raklap> iter = polcok.listIterator();
      while(iter.hasNext())
  		{
        Raklap element = iter.next();
  			if(number == element.getSupplID())
  			{
            int supplId = number;
            ListIterator<Raklap> it = polcok.listIterator();
            while(it.hasNext())
        		{
            	if(supplId == it.next().getSupplID())
        			{
                it.remove();
            	}
        		}
            raklapListazas();
        }
      }//while
    }



    /*---------- H I B A K  F E L V I T E L E ----------*/
    public void hibakFelvitele() throws IOException
    {
      //H I B A K   R O G Z I T E S E
      raklapListazas();
      System.out.println("Adja meg a hibas raklapok belso azonositoit vesszovel elvalasztva!");
      String hibasak;
      hibasak = readUserInput();

      String[] line= hibasak.split("\\,");
      for(int i=0;i<line.length;i++)
      {
        try
        {
          ListIterator<Raklap> it = polcok.listIterator();
          System.out.println("Bevitt belso azon:" + line[i]);
          while(it.hasNext())
          {
            Raklap curr=it.next();

            if(curr.getInnerId().equals(line[i]))
            {

              String innerId=curr.getInnerId();
              String suppname=curr.getSupplierName();
              int supplId = curr.getSupplID();
              it.set(new Raklap(suppname,innerId,supplId,true));
            }
          }
        }
        catch(NumberFormatException ex)
        {
          System.out.println("Hibas formatum!");
        }
      }
      raklapListazas();
    }

    /*---------- I N C O R R E C T ----------*/
  	private void Incorrect()
  	{
  		System.out.println();
  		System.out.println("Incorrect command.");
  		System.out.println();
  	}
}
