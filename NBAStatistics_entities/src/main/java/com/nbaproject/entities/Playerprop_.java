package com.nbaproject.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-04-10T12:55:53.264+0300")
@StaticMetamodel(Playerprop.class)
public class Playerprop_ {
	public static volatile SingularAttribute<Playerprop, PlayerpropPK> id;
	public static volatile SingularAttribute<Playerprop, String> betresult;
	public static volatile SingularAttribute<Playerprop, Date> datetime;
	public static volatile SingularAttribute<Playerprop, Double> overpayout;
	public static volatile SingularAttribute<Playerprop, Double> overunder;
	public static volatile SingularAttribute<Playerprop, Double> statresult;
	public static volatile SingularAttribute<Playerprop, Double> underpayout;
	public static volatile SingularAttribute<Playerprop, Boxscore> boxscore;
	public static volatile SingularAttribute<Playerprop, Player> player;
}
