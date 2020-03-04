package com.nbaproject.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the appconfig database table.
 * 
 */
@Entity
@NamedQuery(name="Appconfig.findAll", query="SELECT a FROM Appconfig a")
public class Appconfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String keytitle;

	private String keyvalue;

	public Appconfig() {
	}

	public String getKeytitle() {
		return this.keytitle;
	}

	public void setKeytitle(String keytitle) {
		this.keytitle = keytitle;
	}

	public String getKeyvalue() {
		return this.keyvalue;
	}

	public void setKeyvalue(String keyvalue) {
		this.keyvalue = keyvalue;
	}

}