package com.nbaproject.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the playerstatspergame database table.
 * 
 */
@Embeddable
public class PlayerstatspergamePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int gameid;

	@Column(insertable=false, updatable=false)
	private int playerid;

	public PlayerstatspergamePK() {
	}
	
	
	public PlayerstatspergamePK(int gameid, int playerid) {
		super();
		this.gameid = gameid;
		this.playerid = playerid;
	}


	public int getGameid() {
		return this.gameid;
	}
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}
	public int getPlayerid() {
		return this.playerid;
	}
	public void setPlayerid(int playerid) {
		this.playerid = playerid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PlayerstatspergamePK)) {
			return false;
		}
		PlayerstatspergamePK castOther = (PlayerstatspergamePK)other;
		return 
			(this.gameid == castOther.gameid)
			&& (this.playerid == castOther.playerid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.gameid;
		hash = hash * prime + this.playerid;
		
		return hash;
	}
}