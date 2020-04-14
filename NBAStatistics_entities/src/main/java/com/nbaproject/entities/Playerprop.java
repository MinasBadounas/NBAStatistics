package com.nbaproject.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the playerprops database table.
 * 
 */
@Entity
@Table(name="playerprops")
@NamedQuery(name="Playerprop.findAll", query="SELECT p FROM Playerprop p")
public class Playerprop implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PlayerpropPK id;

	private String betresult;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;

	private double overpayout;

	private double overunder;

	private double statresult;

	private double underpayout;

	//bi-directional many-to-one association to Boxscore
	@ManyToOne
	@JoinColumn(name="gameid", insertable=false, updatable=false)
	private Boxscore boxscore;

	//bi-directional many-to-one association to Player
	@ManyToOne
	@JoinColumn(name="playerid", insertable=false, updatable=false)
	private Player player;

	public Playerprop() {
	}

	public PlayerpropPK getId() {
		return this.id;
	}

	public void setId(PlayerpropPK id) {
		this.id = id;
	}

	public String getBetresult() {
		return this.betresult;
	}

	public void setBetresult(String betresult) {
		this.betresult = betresult;
	}

	public Date getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public double getOverpayout() {
		return this.overpayout;
	}

	public void setOverpayout(double overpayout) {
		this.overpayout = overpayout;
	}

	public double getOverunder() {
		return this.overunder;
	}

	public void setOverunder(double overunder) {
		this.overunder = overunder;
	}

	public double getStatresult() {
		return this.statresult;
	}

	public void setStatresult(double statresult) {
		this.statresult = statresult;
	}

	public double getUnderpayout() {
		return this.underpayout;
	}

	public void setUnderpayout(double underpayout) {
		this.underpayout = underpayout;
	}

	public Boxscore getBoxscore() {
		return this.boxscore;
	}

	public void setBoxscore(Boxscore boxscore) {
		this.boxscore = boxscore;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}