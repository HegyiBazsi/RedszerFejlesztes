class Raklap
{
	private String first_name;
	private String second_name;
	private String goods;
	private int quantity;
	
	public Raklap(String first_name, String second_name, String goods, int quantity)
	{
		this.first_name = first_name;
		this.second_name = second_name;
		this.goods = goods;
		this.quantity = quantity;
	}
	
	public String getFirstName()
	{
		return first_name;
	}
	
	public String getSecondName()
	{
		return second_name;
	}
	
	public String getGoods()
	{
		return goods;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
}