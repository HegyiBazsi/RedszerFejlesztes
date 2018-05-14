import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;

class Raktaros
{
  /*---------- V A L T O Z O K ----------*/
  	private Controller contr;
  	public List<Szallitas> szallitasok;
    public List<Raklap> importok;
    public List<Raklap> exportok;
    public Szallitas temp;
    public Raklap tempraklap;
    public String inputfile="szallitasok.txt";
    public String importokfile = "importok.txt";
    public String exportokfile = "exportok.txt";
    public boolean van_mar;

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
        System.out.println("3 - Be erkezo raklapok Listazasa");
        System.out.println("4 - Hibas raklapok rogzitese");
        System.out.println("5 - Raklapok kiszallitasanak rogzitese");
        System.out.println("6 - Ki meno raklapok Listazasa");
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
            case 3: importListazas(); break;
            case 4: hibakFelvitele(); break;
            case 5: exportfelvet(); break;
            case 6: exportListazas(); break;
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
      importok = new ArrayList<Raklap>();
      exportok = new ArrayList<Raklap>();
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
    						adat[6]=adat[6].substring(0,adat[6].length()-1);
    				}
    				try
    				{
    					szallitasok.add(new Szallitas(Integer.parseInt(adat[0]), adat[1], Integer.parseInt(adat[2]),
    					adat[3], Integer.parseInt(adat[4]), adat[5], Boolean.parseBoolean(adat[6])));
    				}
    				catch(NumberFormatException ex)
    				{
    					System.out.println("Sikertelen beolvasas!");
    				}
    			}
    		}
    	}

      /*---------- I M P O R T   R E A D ----------*/
      	private void importRead() throws IOException
      	{
      		BufferedReader be = new BufferedReader(new FileReader(importokfile));
      		String in = new String("");
      		importok.clear();
      		while(!((in=be.readLine())==null))
      		{
      			String[] line= in.split("\\,");
      			for(int i=0;i<line.length;i++)
      			{
      				String[] adat = line[i].split("\\|");
      				adat[0]=adat[0].substring(1,adat[0].length());
              if(i==line.length-1)
              {
                  adat[5]=adat[5].substring(0,adat[5].length()-1);
              }
              try
              {
                exportok.add(new Raklap(adat[0],adat[1],Integer.parseInt(adat[2]), Boolean.parseBoolean(adat[3]),adat[4], Boolean.parseBoolean(adat[5])));
              }
      				catch(NumberFormatException ex)
      				{
      					System.out.println("Sikertelen beolvasas!");
      				}
      			}
      		}
      	}

        /*---------- E X P O R T   R E A D ----------*/
        	private void exportRead() throws IOException
        	{
        		BufferedReader be = new BufferedReader(new FileReader(exportokfile));
        		String in = new String("");
        		exportok.clear();
        		while(!((in=be.readLine())==null))
        		{
        			String[] line= in.split("\\,");
        			for(int i=0;i<line.length;i++)
        			{
        				String[] adat = line[i].split("\\|");
        				adat[0]=adat[0].substring(1,adat[0].length());
        				if(i==line.length-1)
        				{
        						adat[5]=adat[5].substring(0,adat[5].length()-1);
        				}
        				try
        				{
        					exportok.add(new Raklap(adat[0],adat[1],Integer.parseInt(adat[2]), Boolean.parseBoolean(adat[3]),adat[4], Boolean.parseBoolean(adat[5])));
        				}
        				catch(NumberFormatException ex)
        				{
        					System.out.println("Sikertelen beolvasas!");
        				}
        			}
        		}
        	}

      /*---------- R E N D E L E S  L I S T A Z A S ----------*/
      	public void rendelesListazas() throws IOException
      	{
          try
          {
              read();
          }
          catch(StringIndexOutOfBoundsException ex)
          {
            System.out.println("Shipping File is empty!");
          }
          Iterator<Szallitas> i = szallitasok.iterator();
          String leftAlignFormat = "| %-4d | %-14s | %-13d | %-13s | %-13d | %-13s | %-13s |%n";
      		System.out.format("+-----------------------------------------------------------------------+%n");
      		System.out.format("| ID   | Supplier name	| Quantity	|Date		|Terminal	|Type | Frozen |%n");
      		System.out.format("+-----------------------------------------------------------------------+%n");
      		while(i.hasNext())
      		{
      			Szallitas element = i.next();
      		  System.out.format(leftAlignFormat, element.getInternalID(), element.getsupplier_name(), element.getquantity(), element.getDate(), element.getTerminal(), element.getType(), element.getFrozen());
      		}
      		System.out.format("+-----------------------------------------------------------------------+%n");
      	}

    /*---------- I M P O R T  L I S T A Z A S ----------*/
    	public void importListazas() throws IOException
    	{
    		Iterator<Raklap> i = importok.iterator();
    		String leftAlignFormat = "| %-9s | %-17s | %-9s | %-9s | %-17s | %-9s |%n";

    		System.out.format("+---------------------------------------------------------------------------+%n");
    		System.out.format("| InnerID   | Supplier name	| Supply ID | Damaged	| Date              | Frozen | %n");
    		System.out.format("+---------------------------------------------------------------------------+%n");
    		while(i.hasNext())
    		{
    			Raklap element = i.next();
    		  System.out.format(leftAlignFormat, element.getInnerId(), element.getSupplierName(), element.getSupplID(), element.getHibase(), element.getDate(), element.getFrozen());
    		}
    		System.out.format("+---------------------------------------------------------------------------+%n");
    	}

      /*---------- E X P O R T  L I S T A Z A S ----------*/
      	public void exportListazas() throws IOException
      	{
          Iterator<Raklap> i = exportok.iterator();
          String leftAlignFormat = "| %-9s | %-17s | %-9s | %-9s | %-17s | %-9s |%n";

      		System.out.format("+---------------------------------------------------------------------------+%n");
      		System.out.format("| InnerID   | Supplier name	| Supply ID | Damaged	| Date              | Frozen | %n");
      		System.out.format("+---------------------------------------------------------------------------+%n");
      		while(i.hasNext())
      		{
      			Raklap element = i.next();
      		  System.out.format(leftAlignFormat, element.getInnerId(), element.getSupplierName(), element.getSupplID(), element.getHibase(), element.getDate(), element.getFrozen());
      		}
      		System.out.format("+---------------------------------------------------------------------------+%n");
      	}

    /*-------B E E R K E Z O  R A K L A P  F E L V E T E L E ---------- */
    private void importfelvet() throws IOException
    {
      try
      {
          read();
      }
      catch(StringIndexOutOfBoundsException ex)
      {
        System.out.println("Shipping File is empty!");
      }

      try
      {
          importRead();
      }
      catch(StringIndexOutOfBoundsException ex)
      {
        System.out.println("Imports File is empty!");
      }
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
                 String datum = element.getDate();
                 Boolean frozen = element.getFrozen();

                 try
                 {
                  Iterator<Raklap> listiter = importok.iterator();
                  while(listiter.hasNext())
               		{

                    Raklap elem = listiter.next();
               			if(elem.getSupplID() != id)
               			{
               			      van_mar=false;
               			}
                    else
                    {
                        System.out.println("Van mar ilyen import felveve!");
                        System.out.println();
                        van_mar=true;
                    }
               		}//end of check while
                  if(van_mar==false)
                  {
                    importok.add(new Raklap(suppname,innerId,id,false,datum,frozen));
                  }

                }//end of try
                 catch(NoSuchMethodError ex)
                 {
                   System.out.println("Sikertelen beolvasas!");
                 }
              }
              //H I B A K   R O G Z I T E S E
              hibakFelvitele();
          }
        }//while

        PrintWriter datafile = new PrintWriter(importokfile);
    		datafile.println(importok);
    		datafile.flush();
    		datafile.close();
        System.out.println("Az import adatokat sikeresen eltaroltuk!");
    }

    /*-------K I M E N O  R A K L A P  F E L V E T E L E ---------- */
    private void exportfelvet() throws IOException
    {
      try
      {
          read();
      }
      catch(StringIndexOutOfBoundsException ex)
      {
        System.out.println("Shipping File is empty!");
      }

      try
      {
          exportRead();
      }
      catch(StringIndexOutOfBoundsException ex)
      {
        System.out.println("Exports File is empty!");
      }
      rendelesListazas();
      System.out.println();
  		System.out.println("Kimeno szallitas azonositoja: ");
  		int number = getUserInput();

  		ListIterator<Raklap> iter = importok.listIterator();
      while(iter.hasNext())
  		{
        Raklap element = iter.next();
        if(number == element.getSupplID())
  			{
            int supplId = number;
            try
            {
              if(supplId == element.getSupplID())
              {
                String suppname = element.getSupplierName();
                String inID = element.getInnerId();
                int supID = element.getSupplID();
                boolean fault = element.getHibase();
                String datum = element.getDate();
                Boolean frozen = element.getFrozen();
                Iterator<Raklap> i = exportok.iterator();
                while(i.hasNext())
                {

                  Raklap elem = i.next();
                  if(elem.getSupplID() != supplId)
                  {
                      van_mar=false;
                  }
                  else
                  {
                      System.out.println("Van mar ilyen export felveve!");
                      System.out.println();
                      van_mar=true;
                  }
                }//end of check while
                if(van_mar==false)
                {
                  exportok.add(new Raklap(suppname,inID,supID,fault,datum,frozen));
                }
              }//end of if
            }
            catch (NullPointerException e)
            {
              System.out.println("GEBASZ");
            }

        }
      }//while
      PrintWriter datafile = new PrintWriter(exportokfile);
      datafile.println(exportok);
      datafile.flush();
      datafile.close();
      System.out.println("Az export adatokat sikeresen eltaroltuk!");
    }



    /*---------- H I B A K  F E L V I T E L E ----------*/
    public void hibakFelvitele() throws IOException
    {
      //H I B A K   R O G Z I T E S E
      importListazas();
      System.out.println("Adja meg a hibas raklapok belso azonositoit vesszovel elvalasztva!");
      String hibasak;
      hibasak = readUserInput();

      String[] line= hibasak.split("\\,");
      for(int i=0;i<line.length;i++)
      {
        try
        {
          ListIterator<Raklap> it = importok.listIterator();
          System.out.println("Bevitt belso azon:" + line[i]);
          while(it.hasNext())
          {
            Raklap curr=it.next();

            if(curr.getInnerId().equals(line[i]))
            {

              String innerId=curr.getInnerId();
              String suppname=curr.getSupplierName();
              int supplId = curr.getSupplID();
              String date = curr.getDate();
              Boolean frozen = curr.getFrozen();

              it.set(new Raklap(suppname,innerId,supplId,true,date,frozen));
            }
          }
        }
        catch(NumberFormatException ex)
        {
          System.out.println("Hibas formatum!");
        }
      }
      importListazas();
    }

    /*---------- I N C O R R E C T ----------*/
  	private void Incorrect()
  	{
  		System.out.println();
  		System.out.println("Incorrect command.");
  		System.out.println();
  	}
}
