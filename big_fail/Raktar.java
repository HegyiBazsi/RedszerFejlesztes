class Raktar extends Polc
{
  public String location; //kesobbi bovites szempontjabol esetleges uj raktar
                          // komplexumban valo hasznalathoz raktarak megkulonboztetese miatt
                          //hoztuk letre ezt a valtozot
  public int numberOfPolc;
  public Polc[] polcok;
  public int row=5;
  public int col=10;

  public Raktar( String location, int numberOfPolc )
  {
    location = location;
    numberOfPolc = numberOfPolc;
    for(int i = 0; i < numberOfPolc; i++)
    {
      polcok[i]= new Polc( this.row, this.col );
    }

  }
}
