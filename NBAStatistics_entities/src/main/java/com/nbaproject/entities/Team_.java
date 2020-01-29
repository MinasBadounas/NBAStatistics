package com.nbaproject.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-01-28T23:33:24.988+0200")
@StaticMetamodel(Team.class)
public class Team_ {
	public static volatile SingularAttribute<Team, Integer> teamid;
	public static volatile SingularAttribute<Team, Byte> active;
	public static volatile SingularAttribute<Team, String> city;
	public static volatile SingularAttribute<Team, String> conference;
	public static volatile SingularAttribute<Team, String> division;
	public static volatile SingularAttribute<Team, String> teamkey;
	public static volatile SingularAttribute<Team, String> teamname;
	public static volatile SingularAttribute<Team, String> wikipedialogourl;
}
