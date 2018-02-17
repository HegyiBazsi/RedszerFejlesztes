public class Konyv
{
	private String szerzo,cim;
	public Konyv(String cim,String szerzo)
	{
		this.szerzo=szerzo;
		this.cim=cim;
	}
	
	public String toString()
	{
		return new String("Cim: "+cim+"\tSzerzo: "+szerzo);
	}
	
	public String getCim()
	{
		return cim;
	}
	
	public String getSzerzo()
	{	
		return szerzo;
	}
}