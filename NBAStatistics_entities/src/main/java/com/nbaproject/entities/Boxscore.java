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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int gameid;

	private String awayteam;

	private int awayteamid;

	private int awayteamscore;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;

	@Temporal(TemporalType.DATE)
	private Date day;

	private String hometeam;

	private int hometeamid;

	private int hometeamscore;

	private double overunder;

	private double pointspread;

	private int season;

	//bi-directional many-to-one association to Quarter
	@OneToMany(mappedBy="boxscore", fetch=FetchType.EAGER)
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

	public int getAwayteamid() {
		return this.awayteamid;
	}

	public void setAwayteamid(int awayteamid) {
		this.awayteamid = awayteamid;
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

	public Date getDay() {
		return this.day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public String getHometeam() {
		return this.hometeam;
	}

	public void setHometeam(String hometeam) {
		this.hometeam = hometeam;
	}

	public int getHometeamid() {
		return this.hometeamid;
	}

	public void setHometeamid(int hometeamid) {
		this.hometeamid = hometeamid;
	}

	public int getHometeamscore() {
		return this.hometeamscore;
	}

	public void setHometeamscore(int hometeamscore) {
		this.hometeamscore = hometeamscore;
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