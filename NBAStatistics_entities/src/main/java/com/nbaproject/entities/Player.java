package com.nbaproject.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the players database table.
 * 
 */
@Entity
@Table(name="players")
@NamedQuery(name="Player.findAll", query="SELECT p FROM Player p")
public class Player implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int playerid;

	private String firstname;

	private String lastname;

	@Column(name="photo_url")
	private String photoUrl;

	private String position;

	@Column(name="position_category")
	private String positionCategory;

	//bi-directional many-to-one association to Team
	@ManyToOne
	@JoinColumn(name="teamid")
	private Team team;

	public Player() {
	}

	public int getPlayerid() {
		return this.playerid;
	}

	public void setPlayerid(int playerid) {
		this.playerid = playerid;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhotoUrl() {
		return this.photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPositionCategory() {
		return this.positionCategory;
	}

	public void setPositionCategory(String positionCategory) {
		this.positionCategory = positionCategory;
	}

	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}