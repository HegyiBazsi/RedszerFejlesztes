class Raklap
{
	public int internalid;
	public String name;
	public String goods;
	public int quantity;


/*Constructor*/
	public Raklap(int internalid, String name, String goods, int quantity)
	{
		this.internalid = internalid;
		this.name = name;
		this.goods = goods;
		this.quantity = quantity;
	}

/*Getter*/
	public int getInternalID()
	{
		return internalid;
	}

	public String getName()
	{
		return name;
	}

	public String getGoods()
	{
		return goods;
	}

	public int getQuantity()
	{
		return quantity;
	}

/*To*/
	public String toString()
	{
		return this.internalid + "|" + this.name + "|" + this.goods + "|" + this.quantity;
	}
}
