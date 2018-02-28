class Controller
{
	private Model model;

	public Controller(Model model)
	{
		this.model = model;
	}

	public void execute(int cmd)
	{
		System.out.println("Controller: " + cmd);
	}

	public String[] getContacts()
	{
		return model.getContacts();
	}
}
