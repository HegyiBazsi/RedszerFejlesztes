class Raklap
{
	public String supplier_name;
	public int innerId;

/*Constructor*/
	

	public Raklap( String supplier_name, int innerId )
	{
		this.supplier_name = supplier_name;
		this.innerId = innerId;
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

/*To*/
	public String toString()
	{
		return this.supplier_name;
	}
}
