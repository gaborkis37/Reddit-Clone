package com.homeProj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.homeProj.domain.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
	
	@Query("SELECT v FROM Vote v where v.createdBy = :createdBy and v.direction = :direction and v.link.id = :linkId")
	List<Vote> findByCreatedByAndDirectionAndLinkId(@Param("createdBy")String createdBy,@Param("direction")short direction,@Param("linkId")Long linkId);
	
}
