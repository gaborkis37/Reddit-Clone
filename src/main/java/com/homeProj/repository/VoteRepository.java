package com.homeProj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.homeProj.domain.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {

	@Query("SELECT v FROM Vote v where v.createdBy = :createdBy and v.direction = :direction and v.link.id = :linkId")
	Optional<Vote> findOneByCreatedByAndDirectionAndLinkId(@Param("createdBy") String createdBy,
			@Param("direction") short direction, @Param("linkId") Long linkId);

	@Query("SELECT v FROM Vote v where v.createdBy = :createdBy and v.direction = 1 and v.link.id = :linkId")
	Optional<Vote> findUpVote(@Param("createdBy") String createdBy, @Param("linkId") Long linkId);

	@Query("SELECT v FROM Vote v where v.createdBy = :createdBy and v.direction = -1 and v.link.id = :linkId")
	Optional<Vote> findDownVote(@Param("createdBy") String createdBy, @Param("linkId") Long linkId);
}
