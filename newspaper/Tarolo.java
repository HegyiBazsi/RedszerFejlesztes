import java.io.*;
import java.util.*;

public class Tarolo
{
	private List<Object> t;
	public Tarolo()
	{
		t=new ArrayList<Object>();
	}
	
	public void hozzaad(Object x)
	{
		t.add(x);
	}
	
	public void listaz()
	{
		Iterator<Object> i = t.iterator();
		while(i.hasNext())
		{
			System.out.println(i.next());
		}
	}
	
	public void beolvas()
	{
		try
		{
			BufferedReader be = new BufferedReader(new FileReader("be.txt"));
			String in=new String("");
			while(!((in=be.readLine())==null))
			{
				String[] adat= in.split("\\|");
				try
				{
					if(adat.length==2)
					{
						t.add(new Konyv(adat[0],adat[1]));
					}
					else if(adat.length==3)
					{
						t.add(new HangosKonyv(adat[0],Integer.parseInt(adat[1]),Integer.parseInt(adat[2])));
					}
					else if(adat.length==4)
					{
						t.add(new Folyoirat(adat[0],Integer.parseInt(adat[1]),Integer.parseInt(adat[2]),Integer.parseInt(adat[3])));
					}
				}
				catch(NumberFormatException ex)
				{
					System.out.println("Baj van!");
				}
			}
		}
		catch(IOException ex)
		{
			System.out.println("Baj van!");
		}
		catch(NoClassDefFoundError ex)
		{
			System.out.println("Baj van!");
		}
	}
	
	public void listazHangosKonyv()
	{
		Iterator<Object> i = t.iterator();
		Object a;
		while(i.hasNext())
		{
			a=i.next();
			if(a instanceof HangosKonyv)
				System.out.println(a);
		}
	}
	
	public void listazKonyv()
	{
		Iterator<Object> i = t.iterator();
		Object a;
		while(i.hasNext())
		{
			a=i.next();
			if(a instanceof Konyv)
				System.out.println(a);
		}
	}
	
	public void listazFolyoirat()
	{
		Iterator<Object> i = t.iterator();
		Object a;
		while(i.hasNext())
		{
			a=i.next();
			if(a instanceof Folyoirat)
				System.out.println(a);
		}
	}
	
	//List <Object> t = new ArrayList<Object>();
	//ArrayList<String> listofcountries = new ArrayList<String>();
	public void rendez()
	{
		System.out.println("After Sorting:");
		Collections.sort(t);
		for(Object counter: t)
		{
			System.out.println(counter);
		}
	}
	
	public boolean keresCimre(String ezt)
	{
		Iterator<Object> i = t.iterator();
		while(i.hasNext())
		{
			Object a = i.next();
			if(a instanceof Konyv)
			{
				Konyv b = (Konyv)a;
				if(ezt.equals(b.getCim()))
				{
					System.out.println(b);
					return true;
				}
			}
			else if(a instanceof Folyoirat)
			{
				Folyoirat b = (Folyoirat)a;
				if(ezt.equals(b.getCim()))
				{
					System.out.println(b);
					return true;
				}
			}
			else if(a instanceof HangosKonyv)
			{	
				HangosKonyv b = (HangosKonyv)a;
				if(ezt.equals(b.getCim()))
				{
					System.out.println(b);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean keresSzerzore(String ezt)
	{
		Iterator<Object> i = t.iterator();
		while(i.hasNext())
		{
			Object a = i.next();
			if(a instanceof Konyv)
			{
				Konyv b = (Konyv)a;
				if(ezt.equals(b.getSzerzo()))
				{
					System.out.println((Konyv)a);
					return true;
				}
			}
		}
		return false;
	}
	
	
}