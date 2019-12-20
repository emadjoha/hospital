package com.spring.boot.security.jwtsecurity.repository;

import com.spring.boot.security.jwtsecurity.model.Prize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PrizeRepository extends JpaRepository<Prize,Long> {
    @Query(value = "select p.* from Prize as p inner join User as u ON u.id = p.user_id WHERE u.id= :userId",nativeQuery = true)
    List<Prize> findAllPrizesByUserId(@Param("userId")Long userId);
    @Query(value = "select p.* from Prize as p inner join User as u ON u.id = p.user_id WHERE (u.id= :userId AND p.id= :prizeId)",nativeQuery = true)
    Optional<Prize> findAllPrizesByUserIdAndPrizeId(@Param("userId")Long userId, @Param("prizeId")Long prizeId);
}
