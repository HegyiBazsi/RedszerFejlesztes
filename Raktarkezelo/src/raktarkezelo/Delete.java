/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raktarkezelo;

/**
 *
 * @author hegyi
 */
import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;

public class Delete
{
   public String inputfile="szallitasok.txt";

  /*---------- I N T   I N P U T ----------*/
	private int getUserInput()throws IOException
	{
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line = input.readLine();
		return Integer.valueOf(line);
	}
  public void Delete(List<Szallitas> szallitasok) throws IOException
  {
    
    /*---------- T O R L E S ----------*/
  	System.out.println("Torlendo tetel szama: ");
  	int number = getUserInput();

  	ListIterator<Szallitas> iter = szallitasok.listIterator();
  	while(iter.hasNext())
  	{
    	if(number == iter.next().getInternalID())
  		{
        iter.remove();
    	}
  	}
    PrintWriter datafile = new PrintWriter(inputfile);
    datafile.println(szallitasok);
    datafile.flush();
    datafile.close();
  }

 

/*  public del(List<Szallitas> szallitasok)
  {

  }*/
}

