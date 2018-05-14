import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;

class Konyvelo
{
  /*---------- V A L T O Z O K ----------*/
  	private Controller contr;
  	public List<Szallitas> szallitasok;
    public List<Raklap> importok;
    public List<Raklap> exportok;
    public List<Raklap> forgalom;
    public Szallitas temp;
    public Raklap tempraklap;
    public String inputfile="szallitasok.txt";
    public String importokfile = "importok.txt";
    public String exportokfile = "exportok.txt";

  public Konyvelo(Controller contr)
  {
    this.contr = contr;
  }

  public Konyvelo()
  {
    szallitasok = new ArrayList<Szallitas>();
    importok = new ArrayList<Raklap>();
    exportok = new ArrayList<Raklap>();
    forgalom = new ArrayList<Raklap>();
  }

  public void showMenu()
  {
    System.out.println();
    System.out.println("Raktar Rendszer");
    System.out.println("1 - Napi forgalom listazasas");
    System.out.println("9 - Exit");
  }

  public boolean executeUserCommand(Socket socket, int cmd)
  {
    try
    {
      switch (cmd)
      {
        case 1: napiForgalom(); break;
        default: Incorrect(); break;
      }
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      out.println(cmd);
    }
    catch(IOException e)
    {
      System.out.println("Incorrect command.");
    }
    return false;
  }

  public boolean executeServer()
  {
    try
    {
      Socket socket = new Socket("localhost", 10000);
      System.out.println();
      System.out.print("Kerem valassa ki a menupontot: ");
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

/*N A P I   F O R G A L O M   L I S T A Z A S A*/
    public void napiForgalom() throws IOException
    {
      datumListazas();
      System.out.println("Kerem adja meg a napot (yyyy.mm.dd formatumban) amikori forgalmat szeretne lekerdezni");
      String date = readUserInput();

      BufferedReader impbe = new BufferedReader(new FileReader(importokfile));
      String impin = new String("");
      forgalom.clear();
      while(!((impin=impbe.readLine())==null))
      {
        String[] impline= impin.split("\\,");
        for(int i=0;i<impline.length;i++)
        {
          String[] impadat = impline[i].split("\\|");
          impadat[0]=impadat[0].substring(1,impadat[0].length());
          if(i==impline.length-1)
          {
              impadat[5]=impadat[5].substring(0,impadat[5].length()-1);
          }
          try
          {
            if(date.equals(impadat[4]))
            {
              forgalom.add(new Raklap(impadat[0],impadat[1],Integer.parseInt(impadat[2]), Boolean.parseBoolean(impadat[3]),impadat[4], Boolean.parseBoolean(impadat[5])));
            }
          }
          catch(NumberFormatException ex)
          {
            System.out.println("Sikertelen beolvasas!");
          }
        }
      }//end import read to forgalmak

      BufferedReader expbe = new BufferedReader(new FileReader(exportokfile));
      String expin = new String("");
      while(!((expin=expbe.readLine())==null))
      {
        String[] expline= expin.split("\\,");
        for(int i=0;i<expline.length;i++)
        {
          String[] expadat = expline[i].split("\\|");
          expadat[0]=expadat[0].substring(1,expadat[0].length());
          if(i==expline.length-1)
          {
              expadat[5]=expadat[5].substring(0,expadat[5].length()-1);
          }
          try
          {
            if(date.equals(expadat[4]))
            {
              forgalom.add(new Raklap(expadat[0],expadat[1],Integer.parseInt(expadat[2]), Boolean.parseBoolean(expadat[3]),expadat[4], Boolean.parseBoolean(expadat[5])));
            }
          }
          catch(NumberFormatException ex)
          {
            System.out.println("Sikertelen beolvasas!");
          }
        }
      }//end export read to forgalmak

      forgalomListazas();
    }


    /*---------- I N C O R R E C T ----------*/
  	private void Incorrect()
  	{
  		System.out.println();
  		System.out.println("Incorrect command.");
  		System.out.println();
  	}

    /*---------- I M P O R T  L I S T A Z A S ----------*/
    	public void importListazas() throws IOException
    	{
    		Iterator<Raklap> i = importok.iterator();
    		String leftAlignFormat = "| %-9s | %-17s | %-9s | %-9s | %-17s | %-9s |%n";

    		System.out.format("+-----------------------------------------------------------------------------------+%n");
    		System.out.format("| InnerID   | Supplier name	| Supply ID | Damaged	| Date              | Frozen   | %n");
    		System.out.format("+-----------------------------------------------------------------------------------+%n");
    		while(i.hasNext())
    		{
    			Raklap element = i.next();
    		  System.out.format(leftAlignFormat, element.getInnerId(), element.getSupplierName(), element.getSupplID(), element.getHibase(), element.getDate(), element.getFrozen());
    		}
    		System.out.format("+-----------------------------------------------------------------------------------+%n");
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

      /*---------- D A T U M O K  L I S T A Z A S ----------*/
        public void datumListazas() throws IOException
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
          String leftAlignFormat = "| %-4d | %-14s | %-13s |%n";
      		System.out.format("+---------------------------------------+%n");
      		System.out.format("| ID   | Supplier name	|Date		|%n");
      		System.out.format("+---------------------------------------+%n");
      		while(i.hasNext())
      		{
      			Szallitas element = i.next();
      		  System.out.format(leftAlignFormat, element.getInternalID(), element.getsupplier_name(), element.getDate());
      		}
      		System.out.format("+---------------------------------------+%n");
        }


        /*---------- F O R G A L O M  L I S T A Z A S ----------*/
        	public void forgalomListazas() throws IOException
        	{
        		Iterator<Raklap> i = forgalom.iterator();
        		String leftAlignFormat = "| %-9s | %-17s | %-9s | %-9s | %-17s | %-9s |%n";

        		System.out.format("+---------------------------------------------------------------------------------------+%n");
        		System.out.format("| InnerID   | Supplier name	| Supply ID | Damaged	| Date              | Frozen    | %n");
        		System.out.format("+---------------------------------------------------------------------------------------+%n");
        		while(i.hasNext())
        		{
        			Raklap element = i.next();
        		  System.out.format(leftAlignFormat, element.getInnerId(), element.getSupplierName(), element.getSupplID(), element.getHibase(), element.getDate(), element.getFrozen());
        		}
        		System.out.format("+---------------------------------------------------------------------------------------+%n");
        	}
}//E N D   O F    C L A S S
