public class HangosKonyv
{
	private String cim;
	private int ora,perc;
	public HangosKonyv(String cim,int ora,int perc)
	{
		this.cim=cim;
		this.ora=ora;
		this.perc=perc;
	}
	
	public String toString()
	{
		return new String("Cim: "+cim+"\t"+ora+":"+perc);
	}
	
	public String getCim()
	{
		return cim;
	}
}