package com.nbaproject.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the boxscore database table.
 * 
 */
@Entity
@NamedQuery(name="Boxscore.findAll", query="SELECT b FROM Boxscore b")
public class Boxscore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int gameid;

	private String awayteam;

	private int awayteamscore;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;

	private String hometeam;

	private int hometeamscore;

	private byte islosed;

	private double overunder;

	private double pointspread;

	private int season;
	
	private String status;

	//bi-directional many-to-one association to Team
	@ManyToOne
	@JoinColumn(name="awayteamid")
	private Team team1;

	//bi-directional many-to-one association to Team
	@ManyToOne
	@JoinColumn(name="hometeamid")
	private Team team2;

	//bi-directional many-to-one association to Playerstatspergame
	@OneToMany(mappedBy="boxscore")
	private List<Playerstatspergame> playerstatspergames;

	//bi-directional many-to-one association to Quarter
	@OneToMany(mappedBy="boxscore")
	private List<Quarter> quarters;

	public Boxscore() {
	}

	public int getGameid() {
		return this.gameid;
	}

	public void setGameid(int gameid) {
		this.gameid = gameid;
	}

	public String getAwayteam() {
		return this.awayteam;
	}

	public void setAwayteam(String awayteam) {
		this.awayteam = awayteam;
	}

	public int getAwayteamscore() {
		return this.awayteamscore;
	}

	public void setAwayteamscore(int awayteamscore) {
		this.awayteamscore = awayteamscore;
	}

	public Date getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getHometeam() {
		return this.hometeam;
	}

	public void setHometeam(String hometeam) {
		this.hometeam = hometeam;
	}

	public int getHometeamscore() {
		return this.hometeamscore;
	}

	public void setHometeamscore(int hometeamscore) {
		this.hometeamscore = hometeamscore;
	}

	public byte getIslosed() {
		return this.islosed;
	}

	public void setIslosed(byte islosed) {
		this.islosed = islosed;
	}

	public double getOverunder() {
		return this.overunder;
	}

	public void setOverunder(double overunder) {
		this.overunder = overunder;
	}

	public double getPointspread() {
		return this.pointspread;
	}

	public void setPointspread(double pointspread) {
		this.pointspread = pointspread;
	}

	public int getSeason() {
		return this.season;
	}

	public void setSeason(int season) {
		this.season = season;
	}
	
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Team getTeam1() {
		return this.team1;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	public Team getTeam2() {
		return this.team2;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	public List<Playerstatspergame> getPlayerstatspergames() {
		return this.playerstatspergames;
	}

	public void setPlayerstatspergames(List<Playerstatspergame> playerstatspergames) {
		this.playerstatspergames = playerstatspergames;
	}

	public Playerstatspergame addPlayerstatspergame(Playerstatspergame playerstatspergame) {
		getPlayerstatspergames().add(playerstatspergame);
		playerstatspergame.setBoxscore(this);

		return playerstatspergame;
	}

	public Playerstatspergame removePlayerstatspergame(Playerstatspergame playerstatspergame) {
		getPlayerstatspergames().remove(playerstatspergame);
		playerstatspergame.setBoxscore(null);

		return playerstatspergame;
	}

	public List<Quarter> getQuarters() {
		return this.quarters;
	}

	public void setQuarters(List<Quarter> quarters) {
		this.quarters = quarters;
	}

	public Quarter addQuarter(Quarter quarter) {
		getQuarters().add(quarter);
		quarter.setBoxscore(this);

		return quarter;
	}

	public Quarter removeQuarter(Quarter quarter) {
		getQuarters().remove(quarter);
		quarter.setBoxscore(null);

		return quarter;
	}

}