package com.nbaproject.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the teams database table.
 * 
 */
@Entity
@Table(name="teams")
@NamedQuery(name="Team.findAll", query="SELECT t FROM Team t")
public class Team implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int teamid;

	private String city;

	private String conference;

	private String division;

	private String teamkey;

	private String teamname;

	private String wikipedialogourl;

	//bi-directional many-to-one association to Boxscore
	@OneToMany(mappedBy="team1")
	private List<Boxscore> boxscores1;

	//bi-directional many-to-one association to Boxscore
	@OneToMany(mappedBy="team2")
	private List<Boxscore> boxscores2;

	//bi-directional many-to-one association to Player
	@OneToMany(mappedBy="team")
	private List<Player> players;

	public Team() {
	}

	public int getTeamid() {
		return this.teamid;
	}

	public void setTeamid(int teamid) {
		this.teamid = teamid;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getConference() {
		return this.conference;
	}

	public void setConference(String conference) {
		this.conference = conference;
	}

	public String getDivision() {
		return this.division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getTeamkey() {
		return this.teamkey;
	}

	public void setTeamkey(String teamkey) {
		this.teamkey = teamkey;
	}

	public String getTeamname() {
		return this.teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public String getWikipedialogourl() {
		return this.wikipedialogourl;
	}

	public void setWikipedialogourl(String wikipedialogourl) {
		this.wikipedialogourl = wikipedialogourl;
	}

	public List<Boxscore> getBoxscores1() {
		return this.boxscores1;
	}

	public void setBoxscores1(List<Boxscore> boxscores1) {
		this.boxscores1 = boxscores1;
	}

	public Boxscore addBoxscores1(Boxscore boxscores1) {
		getBoxscores1().add(boxscores1);
		boxscores1.setTeam1(this);

		return boxscores1;
	}

	public Boxscore removeBoxscores1(Boxscore boxscores1) {
		getBoxscores1().remove(boxscores1);
		boxscores1.setTeam1(null);

		return boxscores1;
	}

	public List<Boxscore> getBoxscores2() {
		return this.boxscores2;
	}

	public void setBoxscores2(List<Boxscore> boxscores2) {
		this.boxscores2 = boxscores2;
	}

	public Boxscore addBoxscores2(Boxscore boxscores2) {
		getBoxscores2().add(boxscores2);
		boxscores2.setTeam2(this);

		return boxscores2;
	}

	public Boxscore removeBoxscores2(Boxscore boxscores2) {
		getBoxscores2().remove(boxscores2);
		boxscores2.setTeam2(null);

		return boxscores2;
	}

	public List<Player> getPlayers() {
		return this.players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Player addPlayer(Player player) {
		getPlayers().add(player);
		player.setTeam(this);

		return player;
	}

	public Player removePlayer(Player player) {
		getPlayers().remove(player);
		player.setTeam(null);

		return player;
	}

}