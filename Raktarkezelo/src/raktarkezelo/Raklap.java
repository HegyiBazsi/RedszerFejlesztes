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
public class Raklap
{
	public String supplier_name;
	public String innerId;
  public boolean hibas_e;

/*Constructor*/


	public Raklap( String supplier_name, String innerId, boolean hibas_e)
	{
		this.supplier_name = supplier_name;
		this.innerId = innerId;
    this.hibas_e = hibas_e;
	}

/*Getter*/
	public String getSupplierName()
	{
		return supplier_name;
	}

	public String getInnerId()
	{
		return  innerId;
	}

  public boolean getHibase()
	{
		return hibas_e;
	}

/*To*/
	public String toString()
	{
		return this.supplier_name + " " + this.innerId + " " + this.hibas_e;
	}
}

