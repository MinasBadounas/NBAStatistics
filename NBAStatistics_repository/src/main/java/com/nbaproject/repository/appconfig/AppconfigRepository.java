package com.nbaproject.repository.appconfig;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nbaproject.entities.Appconfig;

@Repository
public interface AppconfigRepository extends JpaRepository<Appconfig, String >{
	
	 @Query(value = "select keyvalue from appconfig where keytitle like ?1 ;", nativeQuery = true)
	    String findKeyValue(String keytitle);

}
