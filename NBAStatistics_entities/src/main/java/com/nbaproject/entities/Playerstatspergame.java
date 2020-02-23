package com.nbaproject.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the playerstatspergame database table.
 * 
 */
@Entity
@NamedQuery(name="Playerstatspergame.findAll", query="SELECT p FROM Playerstatspergame p")
public class Playerstatspergame implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PlayerstatspergamePK id;

	private double assists;

	private double assistspercentage;

	private double blockedshots;

	private double blockspercentage;

	private double defensiverebounds;

	private double defensivereboundspercentage;

	private double doubledoubles;

	private String draftkingsposition;

	private double effectivefieldgoalspercentage;

	private String fanduelposition;

	private double fieldgoalsattempted;

	private double fieldgoalsmade;

	private double fieldgoalspercentage;

	private double freethrowsattempted;

	private double freethrowspercentage;

	private double freethrowssmade;

	private int games;

	private String injurestatus;

	private byte isclosed;

	private String lineupconfirmed;

	private String lineupstatus;

	private int minutes;

	private double offensiverebounds;

	private double offensivereboundspercentage;

	private int opponentid;

	private int opponentpositionrank;

	private int opponentrank;

	private double personalfouls;

	private double playerefficiencyrating;

	private double plusminus;

	private double points;

	private double rebounds;

	private int season;

	private int seasontype;

	private int seconds;

	private int started;

	private double steals;

	private double stealspercentage;

	private int teamid;

	private double threepointersattempted;

	private double threepointersmade;

	private double threepointerspercentage;

	private double totalreboundspercentage;

	private double tripledoubles;

	private double trueshootingattempts;

	private double trueshootingpercentage;

	private double turnovers;

	private double turnoverspercentage;

	private double twopointersattempted;

	private double twopointersmade;

	private double twopointerspercentage;

	private double usageratepercentage;

	//bi-directional many-to-one association to Boxscore
	@ManyToOne(optional=false)
	@JoinColumn(name="gameid", insertable=false, updatable=false)
	private Boxscore boxscore;

	//bi-directional many-to-one association to Player
	@ManyToOne(optional=false)
	@JoinColumn(name="playerid", insertable=false, updatable=false)
	private Player player;

	public Playerstatspergame() {
	}

	public PlayerstatspergamePK getId() {
		return this.id;
	}

	public void setId(PlayerstatspergamePK id) {
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

	public double getDoubledoubles() {
		return this.doubledoubles;
	}

	public void setDoubledoubles(double doubledoubles) {
		this.doubledoubles = doubledoubles;
	}

	public String getDraftkingsposition() {
		return this.draftkingsposition;
	}

	public void setDraftkingsposition(String draftkingsposition) {
		this.draftkingsposition = draftkingsposition;
	}

	public double getEffectivefieldgoalspercentage() {
		return this.effectivefieldgoalspercentage;
	}

	public void setEffectivefieldgoalspercentage(double effectivefieldgoalspercentage) {
		this.effectivefieldgoalspercentage = effectivefieldgoalspercentage;
	}

	public String getFanduelposition() {
		return this.fanduelposition;
	}

	public void setFanduelposition(String fanduelposition) {
		this.fanduelposition = fanduelposition;
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

	public String getInjurestatus() {
		return this.injurestatus;
	}

	public void setInjurestatus(String injurestatus) {
		this.injurestatus = injurestatus;
	}

	public byte getIsclosed() {
		return this.isclosed;
	}

	public void setIsclosed(byte isclosed) {
		this.isclosed = isclosed;
	}

	public String getLineupconfirmed() {
		return this.lineupconfirmed;
	}

	public void setLineupconfirmed(String lineupconfirmed) {
		this.lineupconfirmed = lineupconfirmed;
	}

	public String getLineupstatus() {
		return this.lineupstatus;
	}

	public void setLineupstatus(String lineupstatus) {
		this.lineupstatus = lineupstatus;
	}

	public int getMinutes() {
		return this.minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
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

	public int getOpponentid() {
		return this.opponentid;
	}

	public void setOpponentid(int opponentid) {
		this.opponentid = opponentid;
	}

	public int getOpponentpositionrank() {
		return this.opponentpositionrank;
	}

	public void setOpponentpositionrank(int opponentpositionrank) {
		this.opponentpositionrank = opponentpositionrank;
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

	public double getPlusminus() {
		return this.plusminus;
	}

	public void setPlusminus(double plusminus) {
		this.plusminus = plusminus;
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

	public int getSeconds() {
		return this.seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public int getStarted() {
		return this.started;
	}

	public void setStarted(int started) {
		this.started = started;
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

	public int getTeamid() {
		return this.teamid;
	}

	public void setTeamid(int teamid) {
		this.teamid = teamid;
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

	public double getTripledoubles() {
		return this.tripledoubles;
	}

	public void setTripledoubles(double tripledoubles) {
		this.tripledoubles = tripledoubles;
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

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}