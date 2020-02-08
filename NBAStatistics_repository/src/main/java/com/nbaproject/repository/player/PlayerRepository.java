package com.nbaproject.repository.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nbaproject.entities.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer>  {

}
