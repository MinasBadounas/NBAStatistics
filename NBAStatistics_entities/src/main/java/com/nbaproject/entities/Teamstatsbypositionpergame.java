package com.nbaproject.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the teamstatsbypositionpergame database table.
 * 
 */
@Entity
@NamedQuery(name="Teamstatsbypositionpergame.findAll", query="SELECT t FROM Teamstatsbypositionpergame t")
public class Teamstatsbypositionpergame implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TeamstatsbypositionpergamePK id;

	private double assists;

	private double assistspercentage;

	private double blockedshots;

	private double blockspercentage;

	private double defensiverebounds;

	private double defensivereboundspercentage;

	private double effectivefieldgoalspercentage;

	private double fieldgoalsattempted;

	private double fieldgoalsmade;

	private double fieldgoalspercentage;

	private double freethrowsattempted;

	private double freethrowspercentage;

	private double freethrowssmade;

	private int games;

	private byte isclosed;

	private double offensiverebounds;

	private double offensivereboundspercentage;

	private int opponentrank;

	private double personalfouls;

	private double playerefficiencyrating;

	private double points;

	private double rebounds;

	private int season;

	private int seasontype;

	private double steals;

	private double stealspercentage;

	private double threepointersattempted;

	private double threepointersmade;

	private double threepointerspercentage;

	private double totalreboundspercentage;

	private double trueshootingattempts;

	private double trueshootingpercentage;

	private double turnovers;

	private double turnoverspercentage;

	private double twopointersattempted;

	private double twopointersmade;

	private double twopointerspercentage;

	private double usageratepercentage;

	//bi-directional many-to-one association to Boxscore
	@ManyToOne
	@JoinColumn(name="gameid",insertable=false, updatable=false)
	private Boxscore boxscore;

	//bi-directional many-to-one association to Team
	@ManyToOne
	@JoinColumn(name="opponentteamid",insertable=false, updatable=false)
	private Team team1;

	//bi-directional many-to-one association to Team
	@ManyToOne
	@JoinColumn(name="teamid",insertable=false, updatable=false)
	private Team team2;

	public Teamstatsbypositionpergame() {
	}

	public TeamstatsbypositionpergamePK getId() {
		return this.id;
	}

	public void setId(TeamstatsbypositionpergamePK id) {
		this.id = id;
	}

	public double getAssists() {
		return this.assists;
	}

	public void setAssists(double assists) {
		this.assists = assists;
	}

	public double getAssistspercentage() {
		return this.assistspercentage;
	}

	public void setAssistspercentage(double assistspercentage) {
		this.assistspercentage = assistspercentage;
	}

	public double getBlockedshots() {
		return this.blockedshots;
	}

	public void setBlockedshots(double blockedshots) {
		this.blockedshots = blockedshots;
	}

	public double getBlockspercentage() {
		return this.blockspercentage;
	}

	public void setBlockspercentage(double blockspercentage) {
		this.blockspercentage = blockspercentage;
	}

	public double getDefensiverebounds() {
		return this.defensiverebounds;
	}

	public void setDefensiverebounds(double defensiverebounds) {
		this.defensiverebounds = defensiverebounds;
	}

	public double getDefensivereboundspercentage() {
		return this.defensivereboundspercentage;
	}

	public void setDefensivereboundspercentage(double defensivereboundspercentage) {
		this.defensivereboundspercentage = defensivereboundspercentage;
	}

	public double getEffectivefieldgoalspercentage() {
		return this.effectivefieldgoalspercentage;
	}

	public void setEffectivefieldgoalspercentage(double effectivefieldgoalspercentage) {
		this.effectivefieldgoalspercentage = effectivefieldgoalspercentage;
	}

	public double getFieldgoalsattempted() {
		return this.fieldgoalsattempted;
	}

	public void setFieldgoalsattempted(double fieldgoalsattempted) {
		this.fieldgoalsattempted = fieldgoalsattempted;
	}

	public double getFieldgoalsmade() {
		return this.fieldgoalsmade;
	}

	public void setFieldgoalsmade(double fieldgoalsmade) {
		this.fieldgoalsmade = fieldgoalsmade;
	}

	public double getFieldgoalspercentage() {
		return this.fieldgoalspercentage;
	}

	public void setFieldgoalspercentage(double fieldgoalspercentage) {
		this.fieldgoalspercentage = fieldgoalspercentage;
	}

	public double getFreethrowsattempted() {
		return this.freethrowsattempted;
	}

	public void setFreethrowsattempted(double freethrowsattempted) {
		this.freethrowsattempted = freethrowsattempted;
	}

	public double getFreethrowspercentage() {
		return this.freethrowspercentage;
	}

	public void setFreethrowspercentage(double freethrowspercentage) {
		this.freethrowspercentage = freethrowspercentage;
	}

	public double getFreethrowssmade() {
		return this.freethrowssmade;
	}

	public void setFreethrowssmade(double freethrowssmade) {
		this.freethrowssmade = freethrowssmade;
	}

	public int getGames() {
		return this.games;
	}

	public void setGames(int games) {
		this.games = games;
	}

	public byte getIsclosed() {
		return this.isclosed;
	}

	public void setIsclosed(byte isclosed) {
		this.isclosed = isclosed;
	}

	public double getOffensiverebounds() {
		return this.offensiverebounds;
	}

	public void setOffensiverebounds(double offensiverebounds) {
		this.offensiverebounds = offensiverebounds;
	}

	public double getOffensivereboundspercentage() {
		return this.offensivereboundspercentage;
	}

	public void setOffensivereboundspercentage(double offensivereboundspercentage) {
		this.offensivereboundspercentage = offensivereboundspercentage;
	}

	public int getOpponentrank() {
		return this.opponentrank;
	}

	public void setOpponentrank(int opponentrank) {
		this.opponentrank = opponentrank;
	}

	public double getPersonalfouls() {
		return this.personalfouls;
	}

	public void setPersonalfouls(double personalfouls) {
		this.personalfouls = personalfouls;
	}

	public double getPlayerefficiencyrating() {
		return this.playerefficiencyrating;
	}

	public void setPlayerefficiencyrating(double playerefficiencyrating) {
		this.playerefficiencyrating = playerefficiencyrating;
	}

	public double getPoints() {
		return this.points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public double getRebounds() {
		return this.rebounds;
	}

	public void setRebounds(double rebounds) {
		this.rebounds = rebounds;
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

	public double getSteals() {
		return this.steals;
	}

	public void setSteals(double steals) {
		this.steals = steals;
	}

	public double getStealspercentage() {
		return this.stealspercentage;
	}

	public void setStealspercentage(double stealspercentage) {
		this.stealspercentage = stealspercentage;
	}

	public double getThreepointersattempted() {
		return this.threepointersattempted;
	}

	public void setThreepointersattempted(double threepointersattempted) {
		this.threepointersattempted = threepointersattempted;
	}

	public double getThreepointersmade() {
		return this.threepointersmade;
	}

	public void setThreepointersmade(double threepointersmade) {
		this.threepointersmade = threepointersmade;
	}

	public double getThreepointerspercentage() {
		return this.threepointerspercentage;
	}

	public void setThreepointerspercentage(double threepointerspercentage) {
		this.threepointerspercentage = threepointerspercentage;
	}

	public double getTotalreboundspercentage() {
		return this.totalreboundspercentage;
	}

	public void setTotalreboundspercentage(double totalreboundspercentage) {
		this.totalreboundspercentage = totalreboundspercentage;
	}

	public double getTrueshootingattempts() {
		return this.trueshootingattempts;
	}

	public void setTrueshootingattempts(double trueshootingattempts) {
		this.trueshootingattempts = trueshootingattempts;
	}

	public double getTrueshootingpercentage() {
		return this.trueshootingpercentage;
	}

	public void setTrueshootingpercentage(double trueshootingpercentage) {
		this.trueshootingpercentage = trueshootingpercentage;
	}

	public double getTurnovers() {
		return this.turnovers;
	}

	public void setTurnovers(double turnovers) {
		this.turnovers = turnovers;
	}

	public double getTurnoverspercentage() {
		return this.turnoverspercentage;
	}

	public void setTurnoverspercentage(double turnoverspercentage) {
		this.turnoverspercentage = turnoverspercentage;
	}

	public double getTwopointersattempted() {
		return this.twopointersattempted;
	}

	public void setTwopointersattempted(double twopointersattempted) {
		this.twopointersattempted = twopointersattempted;
	}

	public double getTwopointersmade() {
		return this.twopointersmade;
	}

	public void setTwopointersmade(double twopointersmade) {
		this.twopointersmade = twopointersmade;
	}

	public double getTwopointerspercentage() {
		return this.twopointerspercentage;
	}

	public void setTwopointerspercentage(double twopointerspercentage) {
		this.twopointerspercentage = twopointerspercentage;
	}

	public double getUsageratepercentage() {
		return this.usageratepercentage;
	}

	public void setUsageratepercentage(double usageratepercentage) {
		this.usageratepercentage = usageratepercentage;
	}

	public Boxscore getBoxscore() {
		return this.boxscore;
	}

	public void setBoxscore(Boxscore boxscore) {
		this.boxscore = boxscore;
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

}