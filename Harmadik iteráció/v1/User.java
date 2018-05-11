class User
{
  public String username;
  public String password;
  public int title;

  public User(String username, String password, int title)
  {
    this.username = username;
    this.password = password;
    this.title = title;
  }

  public String getUsername()
  {
    return username;
  }

  public String getPassword()
  {
    return password;
  }

  public int getTitle()
  {
    return title;
  }

  public String toString()
  {
    return this.username + "|" + this.password + "|" + this.title;
  }
}
