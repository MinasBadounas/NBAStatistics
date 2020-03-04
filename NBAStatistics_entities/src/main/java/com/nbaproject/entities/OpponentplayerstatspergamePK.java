package com.nbaproject.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the opponentplayerstatspergame database table.
 * 
 */
@Embeddable
public class OpponentplayerstatspergamePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int gameid;

	@Column(insertable=false, updatable=false)
	private int playerid;

	public OpponentplayerstatspergamePK() {
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
		if (!(other instanceof OpponentplayerstatspergamePK)) {
			return false;
		}
		OpponentplayerstatspergamePK castOther = (OpponentplayerstatspergamePK)other;
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