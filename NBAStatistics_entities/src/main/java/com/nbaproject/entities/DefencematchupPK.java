package com.nbaproject.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the defencematchup database table.
 * 
 */
@Embeddable
public class DefencematchupPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int gameid;

	@Column(name="playerid_1", insertable=false, updatable=false)
	private int playerid1;

	@Column(name="playerid_2", insertable=false, updatable=false)
	private int playerid2;

	
	public DefencematchupPK(int gameid, int playerid1, int playerid2) {
		super();
		this.gameid = gameid;
		this.playerid1 = playerid1;
		this.playerid2 = playerid2;
	}
	
	public DefencematchupPK() {
	}
	public int getGameid() {
		return this.gameid;
	}
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}
	public int getPlayerid1() {
		return this.playerid1;
	}
	public void setPlayerid1(int playerid1) {
		this.playerid1 = playerid1;
	}
	public int getPlayerid2() {
		return this.playerid2;
	}
	public void setPlayerid2(int playerid2) {
		this.playerid2 = playerid2;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DefencematchupPK)) {
			return false;
		}
		DefencematchupPK castOther = (DefencematchupPK)other;
		return 
			(this.gameid == castOther.gameid)
			&& (this.playerid1 == castOther.playerid1)
			&& (this.playerid2 == castOther.playerid2);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.gameid;
		hash = hash * prime + this.playerid1;
		hash = hash * prime + this.playerid2;
		
		return hash;
	}
}