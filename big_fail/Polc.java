import java.io.*;

class Polc extends Raklap
{
  public int row;
  public int col;
  public Raklap[][] structure;

  public Polc( int row, int col )
  {
    this.row = row;
    this.col = col;
    this.structure = new Raklap[row][col];

    for(int i = 0; i < this.row; i++)
    {
      for(int j = 0; j < this.col; j++)
      {
        this.structure[i][j] = new Raklap("empty", 0);
      }
    }

  }

  public int getRow()
  {
    return row;
  }

  public int getCol()
  {
    return col;
  }

  public void showPolc()
  {
    for(int i = 0; i < row; i++)
    {
      for(int j = 0; j < col; j++)
      {
        System.out.println(structure[i][j]);
      }
    }
  }
}
