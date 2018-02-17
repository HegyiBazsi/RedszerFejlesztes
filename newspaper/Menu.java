import java.io.*;

public class Menu
{
	private Tarolo t;
	private boolean beolvasva;
	
	public Menu()
	{
		try
		{
			t = new Tarolo();
		}
		catch( NoClassDefFoundError ex)
		{
			System.out.println("Baj van!");
		}
		beolvasva=false;
	}
	
	public void start()
	{
		String valasz = new String();
		do
		{
			try
			{
				System.out.println("--- Menu ---");
				
				System.out.println("Listaz:");
				System.out.println(" 11-mind");
				System.out.println(" 12-Folyoiratok");
				System.out.println(" 13-Konyvek");
				System.out.println(" 14-HangosKonyvek");
				
				System.out.println("Hozzaad");
				System.out.println(" 21-Konyv");
				System.out.println(" 22-Folyoirat");
				System.out.println(" 23-HangosKonyv");
				
				System.out.println("Kereses");
				System.out.println(" 31-Cimre");
				System.out.println(" 32-Szerzore");
				
				System.out.println("Beolvas");
				System.out.println(" 4-be.txt");
				
				System.out.println("Rendezes");
				System.out.println(" 5 - Rendez");
				
				System.out.println("9-Kilep");
				
				BufferedReader be = new BufferedReader(new InputStreamReader(System.in));
				valasz=be.readLine();
				
				String cim;
				String ezt;
				boolean b;
				
				switch(valasz)
				{
					case "11":
						t.listaz();
						break;
					case "12":
						t.listazFolyoirat();
						break;
					case "13":
						t.listazKonyv();
						break;
					case "14":
						t.listazHangosKonyv();
						break;
					case "21":
						String szerzo;
						System.out.print("Adja meg a szerzo nevet: ");
						szerzo=be.readLine();
						System.out.print("Adja meg a konyv cimet: ");
						cim=be.readLine();
						t.hozzaad(new Konyv(cim,szerzo));
						break;
					case "22":
						int ev,ho,nap;
						System.out.print("Adja meg a folyoirat cimet: ");
						cim=be.readLine();
						System.out.print("Adja meg a evet: ");
						ev=Integer.parseInt(be.readLine());
						System.out.print("Adja meg a honapot: ");
						ho=Integer.parseInt(be.readLine());
						System.out.print("Adja meg a napot: ");
						nap=Integer.parseInt(be.readLine());
						t.hozzaad(new Folyoirat(cim,ev,ho,nap));
						break;
					case "23":
						int ora,perc;
						System.out.print("Adja meg a HangosKonyv cimet: ");
						cim=be.readLine();
						System.out.print("Adja meg, hogy hany oras: ");
						ora=Integer.parseInt(be.readLine());
						System.out.print("Adja meg, hogy hany perces: ");
						perc=Integer.parseInt(be.readLine());
						t.hozzaad(new HangosKonyv(cim,ora,perc));
						break;
					case "31":
						System.out.print("Adja meg a cimet: ");
						ezt=be.readLine();
						b = t.keresCimre(ezt);
						if(!b)
							System.out.println("Nincs talalat!");
						break;
					case "32":
						System.out.print("Adja meg a szerzo: ");
						ezt=be.readLine();
						b = t.keresSzerzore(ezt);
						if(!b)
							System.out.println("Nincs talalat!");
						break;
					case "4":
						if(!beolvasva)
						{
							t.beolvas();
							beolvasva=true;
						}
						else
							System.out.println("Mar beolvasta a fajlt!");
						break;
					case "5":
						t.rendez();
						break;
					case "9":
						break;
					default :
						System.out.println("Nincs ilyen menupont!");
						break;
				}
			}
			catch( IOException ex)
			{
				System.out.println("Baj van!");
			}
		}
		while(!valasz.equals("9"));
	}
}