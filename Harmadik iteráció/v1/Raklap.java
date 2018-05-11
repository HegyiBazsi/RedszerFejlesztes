		class Raklap
		{
			public String supplier_name;
			public String innerId;
			public int suppl_id;
		  public boolean hibas_e;
			public String date;
			public boolean frozen;

		/*Constructor*/
			public Raklap( String supplier_name, String innerId, int suppl_id, boolean hibas_e, String date, boolean frozen)
			{
				this.supplier_name = supplier_name;
				this.innerId = innerId;
		    this.hibas_e = hibas_e;
				this.suppl_id = suppl_id;
				this.date = date;
				this.frozen = frozen;
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

			public int getSupplID()
			{
				return suppl_id;
			}

		  public boolean getHibase()
			{
				return hibas_e;
			}

			public String getDate()
			{
				return date;
			}

			public boolean getFrozen()
			{
				return frozen;
			}

		/*To*/
			public String toString()
			{
				return this.supplier_name + "|" + this.innerId + "|" + this.suppl_id + "|" + this.hibas_e + "|" + this.date + "|" + this.frozen;
			}
		}
