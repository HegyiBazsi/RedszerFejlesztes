class Polc
{
  public int polcAzon;
  public int sorszam;
  public int oszlopszam;
  public Raklap polc[][];
  Raklap tempraklap= new Raklap("ures","ures",0,false);

  public Polc(int sorszam, int oszlopszam, int polcAzon)
  {
    this.sorszam = sorszam;
    this.oszlopszam = oszlopszam;
    this.polcAzon = polcAzon;

    for(int i = 0; i < sorszam; i++)
    {
      for(int j = 0; j < oszlopszam; j++)
      {
          polc[i][j] = tempraklap;
      }
    }
  }//end of polc Constructors

  public void addRaklap(Raklap ujRaklap)
  {
    do
    {

    }while();
  }//end of addRaklap

}//end of class
