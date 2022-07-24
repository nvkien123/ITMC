package com.blog.itmc.repositories;

import com.blog.itmc.models.MemDepart;
import com.blog.itmc.models.MemDepartId;
import com.blog.itmc.models.MemDepart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MemDepartRepo extends JpaRepository<MemDepart, MemDepartId> {
	
	@Transactional
	@Modifying
	@Query("delete from MemDepart u where u.memberId = ?1 ")
    void deleteByMemId(int memId );
	
	@Transactional
	@Modifying
	@Query("delete from MemDepart u where u.departId = ?1")
    void deleteByDepartId(String departId);
}
