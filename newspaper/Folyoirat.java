public class Folyoirat
{
	private String cim;
	private int ev,ho,nap;
	public Folyoirat(String cim,int ev,int ho,int nap)
	{
		this.cim=cim;
		this.ev=ev;
		this.ho=ho;
		this.nap=nap;
	}
	
	public String toString()
	{
		return new String("Cim: "+cim+"\t"+ev+"."+ho+"."+nap+".");
	}
	
	public String getCim()
	{
		return cim;
	}
}