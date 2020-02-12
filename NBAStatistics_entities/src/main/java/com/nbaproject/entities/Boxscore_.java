package com.nbaproject.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-02-12T22:25:48.992+0200")
@StaticMetamodel(Boxscore.class)
public class Boxscore_ {
	public static volatile SingularAttribute<Boxscore, Integer> gameid;
	public static volatile SingularAttribute<Boxscore, String> awayteam;
	public static volatile SingularAttribute<Boxscore, Integer> awayteamid;
	public static volatile SingularAttribute<Boxscore, Integer> awayteamscore;
	public static volatile SingularAttribute<Boxscore, Date> datetime;
	public static volatile SingularAttribute<Boxscore, Date> day;
	public static volatile SingularAttribute<Boxscore, String> hometeam;
	public static volatile SingularAttribute<Boxscore, Integer> hometeamid;
	public static volatile SingularAttribute<Boxscore, Integer> hometeamscore;
	public static volatile SingularAttribute<Boxscore, Double> overunder;
	public static volatile SingularAttribute<Boxscore, Double> pointspread;
	public static volatile SingularAttribute<Boxscore, Integer> season;
	public static volatile ListAttribute<Boxscore, Quarter> quarters;
}
