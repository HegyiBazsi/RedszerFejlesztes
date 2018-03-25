class Raklap
{
	public String name;
	public String goods;
	public int quantity;
	
/*Constructor*/	
	public Raklap(String name, String goods, int quantity)
	{
		this.name = name;
		this.goods = goods;
		this.quantity = quantity;
	}
		
/*Getter*/		
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
		return this.name + " " + this.goods + " " + this.quantity;		
	}
}