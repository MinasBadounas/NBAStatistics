package com.nbaproject.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the teamstatsbypositionpergame database table.
 * 
 */
@Embeddable
public class TeamstatsbypositionpergamePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int gameid;

	@Column(insertable=false, updatable=false)
	private int teamid;

	@Column(insertable=false, updatable=false)
	private int opponentteamid;

	@Column(insertable=false, updatable=false)
	private int categoryposition;

	public TeamstatsbypositionpergamePK() {
	}
	public int getGameid() {
		return this.gameid;
	}
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}
	public int getTeamid() {
		return this.teamid;
	}
	public void setTeamid(int teamid) {
		this.teamid = teamid;
	}
	public int getOpponentteamid() {
		return this.opponentteamid;
	}
	public void setOpponentteamid(int opponentteamid) {
		this.opponentteamid = opponentteamid;
	}
	public int getCategoryposition() {
		return this.categoryposition;
	}
	public void setCategoryposition(int categoryposition) {
		this.categoryposition = categoryposition;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TeamstatsbypositionpergamePK)) {
			return false;
		}
		TeamstatsbypositionpergamePK castOther = (TeamstatsbypositionpergamePK)other;
		return 
			(this.gameid == castOther.gameid)
			&& (this.teamid == castOther.teamid)
			&& (this.opponentteamid == castOther.opponentteamid)
			&& (this.categoryposition == castOther.categoryposition);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.gameid;
		hash = hash * prime + this.teamid;
		hash = hash * prime + this.opponentteamid;
		hash = hash * prime + this.categoryposition;
		
		return hash;
	}
}