package com.blog.itmc.repositories;

import com.blog.itmc.models.MemTeam;
import com.blog.itmc.models.MemTeamId;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MemTeamRepo extends JpaRepository<MemTeam, MemTeamId> {
	
	@Transactional
	@Modifying
	@Query("delete from MemTeam u where u.memberId = ?1 ")
    void deleteByMemId(int memId );
	
	@Transactional
	@Modifying
	@Query("delete from MemTeam u where u.teamId = ?1")
    void deleteByTeamId(String teamId);
}
