class Szallitas
{
	public int internalid;
	public String supplier_name;
  public int quantity;
  public String date;
	public int terminal;
	public String type;
	public boolean frozen;

	public Szallitas(int internalid, String supplier_name, int quantity, String date, int terminal, String type, boolean frozen)
	{
		this.internalid = internalid;
		this.supplier_name = supplier_name;
		this.quantity = quantity;
		this.date = date;
		this.terminal = terminal;
		this.type = type;
		this.frozen = frozen;
	}

/*Getter*/
	public int getInternalID()
	{
		return internalid;
	}

	public String getsupplier_name()
	{
		return supplier_name;
	}

	public int getquantity()
	{
		return quantity;
	}

	public String getDate()
	{
		return date;
	}

	public int getTerminal()
	{
		return terminal;
	}

	public String getType()
	{
		return type;
	}

	public boolean getFrozen()
	{
		return frozen;
	}

/*To*/
	public String toString()
	{
		return this.internalid + "|" + this.supplier_name + "|" + this.quantity + "|" + this.date + "|" + this.terminal + "|" + this.type + "|" + this.frozen;
	}
}
