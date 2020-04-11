package com.nbaproject.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the playerprops database table.
 * 
 */
@Embeddable
public class PlayerpropPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int playerid;

	@Column(insertable=false, updatable=false)
	private int gameid;

	private int description;

	
	public PlayerpropPK(int playerid, int gameid, int description) {
		super();
		this.playerid = playerid;
		this.gameid = gameid;
		this.description = description;
	}
	public PlayerpropPK() {
	}
	public int getPlayerid() {
		return this.playerid;
	}
	public void setPlayerid(int playerid) {
		this.playerid = playerid;
	}
	public int getGameid() {
		return this.gameid;
	}
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}
	public int getDescription() {
		return this.description;
	}
	public void setDescription(int description) {
		this.description = description;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PlayerpropPK)) {
			return false;
		}
		PlayerpropPK castOther = (PlayerpropPK)other;
		return 
			(this.playerid == castOther.playerid)
			&& (this.gameid == castOther.gameid)
			&& (this.description==castOther.description);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.playerid;
		hash = hash * prime + this.gameid;
		hash = hash * prime + this.description;
		
		return hash;
	}
}