package com.nbaproject.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the opponentplayerstatspergame database table.
 * 
 */
@Entity
@NamedQuery(name="Opponentplayerstatspergame.findAll", query="SELECT o FROM Opponentplayerstatspergame o")
public class Opponentplayerstatspergame implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OpponentplayerstatspergamePK id;

	private double AVG_assists;

	private double AVG_assistspercentage;

	private double AVG_blockedshots;

	private double AVG_blockspercentage;

	private double AVG_defensiverebounds;

	private double AVG_defensivereboundspercentage;

	private double AVG_effectivefieldgoalspercentage;

	private double AVG_fieldgoalsattempted;

	private double AVG_fieldgoalsmade;

	private double AVG_fieldgoalspercentage;

	private double AVG_freethrowsattempted;

	private double AVG_freethrowspercentage;

	private double AVG_freethrowssmade;

	private double AVG_offensiverebounds;

	private double AVG_offensivereboundspercentage;

	private double AVG_personalfouls;

	private double AVG_playerefficiencyrating;

	private double AVG_plusminus;

	private double AVG_points;

	private double AVG_rebounds;

	private double AVG_steals;

	private double AVG_stealspercentage;

	private double AVG_threepointersattempted;

	private double AVG_threepointersmade;

	private double AVG_threepointerspercentage;

	private double AVG_totalreboundspercentage;

	private double AVG_trueshootingattempts;

	private double AVG_trueshootingpercentage;

	private double AVG_turnovers;

	private double AVG_turnoverspercentage;

	private double AVG_twopointersattempted;

	private double AVG_twopointersmade;

	private double AVG_twopointerspercentage;

	private double AVG_usageratepercentage;

	private int games;

	private int MAX_opponentpositionrank;

	private int MIN_opponentpositionrank;

	private double minutes;

	private int opponentid;

	private int opponentrank;

	private int season;

	private int seasontype;

	private int teamid;

	//bi-directional many-to-one association to Boxscore
	@ManyToOne
	@JoinColumn(name="gameid",insertable=false, updatable=false)
	private Boxscore boxscore;

	//bi-directional many-to-one association to Player
	@ManyToOne
	@JoinColumn(name="playerid",insertable=false, updatable=false)
	private Player player;

	public Opponentplayerstatspergame() {
	}

	public OpponentplayerstatspergamePK getId() {
		return this.id;
	}

	public void setId(OpponentplayerstatspergamePK id) {
		this.id = id;
	}

	public double getAVG_assists() {
		return this.AVG_assists;
	}

	public void setAVG_assists(double AVG_assists) {
		this.AVG_assists = AVG_assists;
	}

	public double getAVG_assistspercentage() {
		return this.AVG_assistspercentage;
	}

	public void setAVG_assistspercentage(double AVG_assistspercentage) {
		this.AVG_assistspercentage = AVG_assistspercentage;
	}

	public double getAVG_blockedshots() {
		return this.AVG_blockedshots;
	}

	public void setAVG_blockedshots(double AVG_blockedshots) {
		this.AVG_blockedshots = AVG_blockedshots;
	}

	public double getAVG_blockspercentage() {
		return this.AVG_blockspercentage;
	}

	public void setAVG_blockspercentage(double AVG_blockspercentage) {
		this.AVG_blockspercentage = AVG_blockspercentage;
	}

	public double getAVG_defensiverebounds() {
		return this.AVG_defensiverebounds;
	}

	public void setAVG_defensiverebounds(double AVG_defensiverebounds) {
		this.AVG_defensiverebounds = AVG_defensiverebounds;
	}

	public double getAVG_defensivereboundspercentage() {
		return this.AVG_defensivereboundspercentage;
	}

	public void setAVG_defensivereboundspercentage(double AVG_defensivereboundspercentage) {
		this.AVG_defensivereboundspercentage = AVG_defensivereboundspercentage;
	}

	public double getAVG_effectivefieldgoalspercentage() {
		return this.AVG_effectivefieldgoalspercentage;
	}

	public void setAVG_effectivefieldgoalspercentage(double AVG_effectivefieldgoalspercentage) {
		this.AVG_effectivefieldgoalspercentage = AVG_effectivefieldgoalspercentage;
	}

	public double getAVG_fieldgoalsattempted() {
		return this.AVG_fieldgoalsattempted;
	}

	public void setAVG_fieldgoalsattempted(double AVG_fieldgoalsattempted) {
		this.AVG_fieldgoalsattempted = AVG_fieldgoalsattempted;
	}

	public double getAVG_fieldgoalsmade() {
		return this.AVG_fieldgoalsmade;
	}

	public void setAVG_fieldgoalsmade(double AVG_fieldgoalsmade) {
		this.AVG_fieldgoalsmade = AVG_fieldgoalsmade;
	}

	public double getAVG_fieldgoalspercentage() {
		return this.AVG_fieldgoalspercentage;
	}

	public void setAVG_fieldgoalspercentage(double AVG_fieldgoalspercentage) {
		this.AVG_fieldgoalspercentage = AVG_fieldgoalspercentage;
	}

	public double getAVG_freethrowsattempted() {
		return this.AVG_freethrowsattempted;
	}

	public void setAVG_freethrowsattempted(double AVG_freethrowsattempted) {
		this.AVG_freethrowsattempted = AVG_freethrowsattempted;
	}

	public double getAVG_freethrowspercentage() {
		return this.AVG_freethrowspercentage;
	}

	public void setAVG_freethrowspercentage(double AVG_freethrowspercentage) {
		this.AVG_freethrowspercentage = AVG_freethrowspercentage;
	}

	public double getAVG_freethrowssmade() {
		return this.AVG_freethrowssmade;
	}

	public void setAVG_freethrowssmade(double AVG_freethrowssmade) {
		this.AVG_freethrowssmade = AVG_freethrowssmade;
	}

	public double getAVG_offensiverebounds() {
		return this.AVG_offensiverebounds;
	}

	public void setAVG_offensiverebounds(double AVG_offensiverebounds) {
		this.AVG_offensiverebounds = AVG_offensiverebounds;
	}

	public double getAVG_offensivereboundspercentage() {
		return this.AVG_offensivereboundspercentage;
	}

	public void setAVG_offensivereboundspercentage(double AVG_offensivereboundspercentage) {
		this.AVG_offensivereboundspercentage = AVG_offensivereboundspercentage;
	}

	public double getAVG_personalfouls() {
		return this.AVG_personalfouls;
	}

	public void setAVG_personalfouls(double AVG_personalfouls) {
		this.AVG_personalfouls = AVG_personalfouls;
	}

	public double getAVG_playerefficiencyrating() {
		return this.AVG_playerefficiencyrating;
	}

	public void setAVG_playerefficiencyrating(double AVG_playerefficiencyrating) {
		this.AVG_playerefficiencyrating = AVG_playerefficiencyrating;
	}

	public double getAVG_plusminus() {
		return this.AVG_plusminus;
	}

	public void setAVG_plusminus(double AVG_plusminus) {
		this.AVG_plusminus = AVG_plusminus;
	}

	public double getAVG_points() {
		return this.AVG_points;
	}

	public void setAVG_points(double AVG_points) {
		this.AVG_points = AVG_points;
	}

	public double getAVG_rebounds() {
		return this.AVG_rebounds;
	}

	public void setAVG_rebounds(double AVG_rebounds) {
		this.AVG_rebounds = AVG_rebounds;
	}

	public double getAVG_steals() {
		return this.AVG_steals;
	}

	public void setAVG_steals(double AVG_steals) {
		this.AVG_steals = AVG_steals;
	}

	public double getAVG_stealspercentage() {
		return this.AVG_stealspercentage;
	}

	public void setAVG_stealspercentage(double AVG_stealspercentage) {
		this.AVG_stealspercentage = AVG_stealspercentage;
	}

	public double getAVG_threepointersattempted() {
		return this.AVG_threepointersattempted;
	}

	public void setAVG_threepointersattempted(double AVG_threepointersattempted) {
		this.AVG_threepointersattempted = AVG_threepointersattempted;
	}

	public double getAVG_threepointersmade() {
		return this.AVG_threepointersmade;
	}

	public void setAVG_threepointersmade(double AVG_threepointersmade) {
		this.AVG_threepointersmade = AVG_threepointersmade;
	}

	public double getAVG_threepointerspercentage() {
		return this.AVG_threepointerspercentage;
	}

	public void setAVG_threepointerspercentage(double AVG_threepointerspercentage) {
		this.AVG_threepointerspercentage = AVG_threepointerspercentage;
	}

	public double getAVG_totalreboundspercentage() {
		return this.AVG_totalreboundspercentage;
	}

	public void setAVG_totalreboundspercentage(double AVG_totalreboundspercentage) {
		this.AVG_totalreboundspercentage = AVG_totalreboundspercentage;
	}

	public double getAVG_trueshootingattempts() {
		return this.AVG_trueshootingattempts;
	}

	public void setAVG_trueshootingattempts(double AVG_trueshootingattempts) {
		this.AVG_trueshootingattempts = AVG_trueshootingattempts;
	}

	public double getAVG_trueshootingpercentage() {
		return this.AVG_trueshootingpercentage;
	}

	public void setAVG_trueshootingpercentage(double AVG_trueshootingpercentage) {
		this.AVG_trueshootingpercentage = AVG_trueshootingpercentage;
	}

	public double getAVG_turnovers() {
		return this.AVG_turnovers;
	}

	public void setAVG_turnovers(double AVG_turnovers) {
		this.AVG_turnovers = AVG_turnovers;
	}

	public double getAVG_turnoverspercentage() {
		return this.AVG_turnoverspercentage;
	}

	public void setAVG_turnoverspercentage(double AVG_turnoverspercentage) {
		this.AVG_turnoverspercentage = AVG_turnoverspercentage;
	}

	public double getAVG_twopointersattempted() {
		return this.AVG_twopointersattempted;
	}

	public void setAVG_twopointersattempted(double AVG_twopointersattempted) {
		this.AVG_twopointersattempted = AVG_twopointersattempted;
	}

	public double getAVG_twopointersmade() {
		return this.AVG_twopointersmade;
	}

	public void setAVG_twopointersmade(double AVG_twopointersmade) {
		this.AVG_twopointersmade = AVG_twopointersmade;
	}

	public double getAVG_twopointerspercentage() {
		return this.AVG_twopointerspercentage;
	}

	public void setAVG_twopointerspercentage(double AVG_twopointerspercentage) {
		this.AVG_twopointerspercentage = AVG_twopointerspercentage;
	}

	public double getAVG_usageratepercentage() {
		return this.AVG_usageratepercentage;
	}

	public void setAVG_usageratepercentage(double AVG_usageratepercentage) {
		this.AVG_usageratepercentage = AVG_usageratepercentage;
	}

	public int getGames() {
		return this.games;
	}

	public void setGames(int games) {
		this.games = games;
	}

	public int getMAX_opponentpositionrank() {
		return this.MAX_opponentpositionrank;
	}

	public void setMAX_opponentpositionrank(int MAX_opponentpositionrank) {
		this.MAX_opponentpositionrank = MAX_opponentpositionrank;
	}

	public int getMIN_opponentpositionrank() {
		return this.MIN_opponentpositionrank;
	}

	public void setMIN_opponentpositionrank(int MIN_opponentpositionrank) {
		this.MIN_opponentpositionrank = MIN_opponentpositionrank;
	}

	public double getMinutes() {
		return this.minutes;
	}

	public void setMinutes(double minutes) {
		this.minutes = minutes;
	}

	public int getOpponentid() {
		return this.opponentid;
	}

	public void setOpponentid(int opponentid) {
		this.opponentid = opponentid;
	}

	public int getOpponentrank() {
		return this.opponentrank;
	}

	public void setOpponentrank(int opponentrank) {
		this.opponentrank = opponentrank;
	}

	public int getSeason() {
		return this.season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public int getSeasontype() {
		return this.seasontype;
	}

	public void setSeasontype(int seasontype) {
		this.seasontype = seasontype;
	}

	public int getTeamid() {
		return this.teamid;
	}

	public void setTeamid(int teamid) {
		this.teamid = teamid;
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