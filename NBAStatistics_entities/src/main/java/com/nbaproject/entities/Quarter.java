package com.nbaproject.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the quarters database table.
 * 
 */
@Entity
@Table(name="quarters")
@NamedQuery(name="Quarter.findAll", query="SELECT q FROM Quarter q")
public class Quarter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int quarterid;

	private int awaycore;

	private int homescore;

	private String name;

	private int number;

	private int season;

	//bi-directional many-to-one association to Boxscore
	@ManyToOne
	@JoinColumn(name="gameid")
	private Boxscore boxscore;

	public Quarter() {
	}

	public int getQuarterid() {
		return this.quarterid;
	}

	public void setQuarterid(int quarterid) {
		this.quarterid = quarterid;
	}

	public int getAwaycore() {
		return this.awaycore;
	}

	public void setAwaycore(int awaycore) {
		this.awaycore = awaycore;
	}

	public int getHomescore() {
		return this.homescore;
	}

	public void setHomescore(int homescore) {
		this.homescore = homescore;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSeason() {
		return this.season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public Boxscore getBoxscore() {
		return this.boxscore;
	}

	public void setBoxscore(Boxscore boxscore) {
		this.boxscore = boxscore;
	}

}