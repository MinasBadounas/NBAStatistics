package com.nbaproject.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-02-12T21:44:47.821+0200")
@StaticMetamodel(Quarter.class)
public class Quarter_ {
	public static volatile SingularAttribute<Quarter, Integer> quarterid;
	public static volatile SingularAttribute<Quarter, Integer> awaycore;
	public static volatile SingularAttribute<Quarter, Integer> homescore;
	public static volatile SingularAttribute<Quarter, String> name;
	public static volatile SingularAttribute<Quarter, Integer> number;
	public static volatile SingularAttribute<Quarter, Integer> season;
	public static volatile SingularAttribute<Quarter, Boxscore> boxscore;
}
