package com.spring.boot.security.jwtsecurity.repository;
import com.spring.boot.security.jwtsecurity.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount,Long> {

    @Query(value = "select p.* from Prize as p inner join User as u ON u.id = p.user_id WHERE u.id= :userId",nativeQuery = true)
    List<Discount> findAllDiscountsByUserId(@Param("userId")Long userId);
    @Query(value = "select p.* from Prize as p inner join User as u ON u.id = p.user_id WHERE (u.id= :userId AND p.id= :discountId)",nativeQuery = true)
    Optional<Discount> findAllDiscountsByUserIdAndDiscountId(@Param("userId")Long userId, @Param("discountId")Long discountId);
}
