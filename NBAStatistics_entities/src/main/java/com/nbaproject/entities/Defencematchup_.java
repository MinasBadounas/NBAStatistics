package com.nbaproject.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-02-27T21:55:53.601+0200")
@StaticMetamodel(Defencematchup.class)
public class Defencematchup_ {
	public static volatile SingularAttribute<Defencematchup, DefencematchupPK> id;
	public static volatile SingularAttribute<Defencematchup, Boxscore> boxscore;
	public static volatile SingularAttribute<Defencematchup, Player> player1;
	public static volatile SingularAttribute<Defencematchup, Player> player2;
}
