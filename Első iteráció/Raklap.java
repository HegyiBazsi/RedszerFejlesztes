class Raklap
{
	public String supplier_name;
	public int innerId;
  public boolean hibas_e;

/*Constructor*/


	public Raklap( String supplier_name, int innerId, boolean hibas_e)
	{
		this.supplier_name = supplier_name;
		this.innerId = innerId;
    this.hibas_e = hibas_e;
	}

/*Getter*/
	public String getSupplierName()
	{
		return supplier_name;
	}

	public int getInnerId()
	{
		return  innerId;
	}

  public boolean getHibase()
	{
		return hibas_e;
	}

/*To*/
	public String toString()
	{
		return this.supplier_name + " " + this.internalid + " " + this.hibas_e;
	}
}
