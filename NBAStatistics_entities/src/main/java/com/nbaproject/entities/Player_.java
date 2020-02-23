package com.nbaproject.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-02-22T13:20:12.944+0200")
@StaticMetamodel(Player.class)
public class Player_ {
	public static volatile SingularAttribute<Player, Integer> playerid;
	public static volatile SingularAttribute<Player, String> firstname;
	public static volatile SingularAttribute<Player, String> lastname;
	public static volatile SingularAttribute<Player, String> photoUrl;
	public static volatile SingularAttribute<Player, String> position;
	public static volatile SingularAttribute<Player, String> positionCategory;
	public static volatile SingularAttribute<Player, Team> team;
	public static volatile ListAttribute<Player, Playerstatspergame> playerstatspergames;
}
