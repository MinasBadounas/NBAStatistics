package com.nbaproject.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the defencematchup database table.
 * 
 */
@Entity
@NamedQuery(name="Defencematchup.findAll", query="SELECT d FROM Defencematchup d")
public class Defencematchup implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DefencematchupPK id;

	//bi-directional many-to-one association to Boxscore
	@ManyToOne
	@JoinColumn(name="gameid", insertable=false, updatable=false)
	private Boxscore boxscore;

	//bi-directional many-to-one association to Player
	@ManyToOne
	@JoinColumn(name="playerid_1", insertable=false, updatable=false)
	private Player player1;

	//bi-directional many-to-one association to Player
	@ManyToOne
	@JoinColumn(name="playerid_2", insertable=false, updatable=false)
	private Player player2;

	public Defencematchup() {
	}

	public DefencematchupPK getId() {
		return this.id;
	}

	public void setId(DefencematchupPK id) {
		this.id = id;
	}

	public Boxscore getBoxscore() {
		return this.boxscore;
	}

	public void setBoxscore(Boxscore boxscore) {
		this.boxscore = boxscore;
	}

	public Player getPlayer1() {
		return this.player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return this.player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

}