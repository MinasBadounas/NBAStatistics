package com.nbaproject.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-04-12T20:32:48.530+0300")
@StaticMetamodel(Boxscore.class)
public class Boxscore_ {
	public static volatile SingularAttribute<Boxscore, Integer> gameid;
	public static volatile SingularAttribute<Boxscore, String> awayteam;
	public static volatile SingularAttribute<Boxscore, Integer> awayteamscore;
	public static volatile SingularAttribute<Boxscore, Date> datetime;
	public static volatile SingularAttribute<Boxscore, String> hometeam;
	public static volatile SingularAttribute<Boxscore, Integer> hometeamscore;
	public static volatile SingularAttribute<Boxscore, Byte> isclosed;
	public static volatile SingularAttribute<Boxscore, Double> overunder;
	public static volatile SingularAttribute<Boxscore, Double> pointspread;
	public static volatile SingularAttribute<Boxscore, Integer> season;
	public static volatile SingularAttribute<Boxscore, String> status;
	public static volatile SingularAttribute<Boxscore, Team> team1;
	public static volatile SingularAttribute<Boxscore, Team> team2;
	public static volatile ListAttribute<Boxscore, Playerstatspergame> playerstatspergames;
	public static volatile ListAttribute<Boxscore, Quarter> quarters;
}
