package com.nbaproject.repository.defencematchup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nbaproject.entities.Defencematchup;

@Repository
public interface DefencematchupRepository extends JpaRepository<Defencematchup,Integer>{

}
