package com.nbaproject.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the playerseasonstats database table.
 * 
 */
@Embeddable
public class PlayerseasonstatPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int statid;

	@Column(insertable=false, updatable=false)
	private int teamid;

	@Column(insertable=false, updatable=false)
	private int playerid;

	public PlayerseasonstatPK(int statid, int teamid, int playerid) {
		super();
		this.statid = statid;
		this.teamid = teamid;
		this.playerid = playerid;
	}
	
	public PlayerseasonstatPK() {
	}
	public int getStatid() {
		return this.statid;
	}
	public void setStatid(int statid) {
		this.statid = statid;
	}
	public int getTeamid() {
		return this.teamid;
	}
	public void setTeamid(int teamid) {
		this.teamid = teamid;
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
		if (!(other instanceof PlayerseasonstatPK)) {
			return false;
		}
		PlayerseasonstatPK castOther = (PlayerseasonstatPK)other;
		return 
			(this.statid == castOther.statid)
			&& (this.teamid == castOther.teamid)
			&& (this.playerid == castOther.playerid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.statid;
		hash = hash * prime + this.teamid;
		hash = hash * prime + this.playerid;
		
		return hash;
	}
}