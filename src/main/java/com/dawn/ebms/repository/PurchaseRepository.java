package com.dawn.ebms.repository;

import com.dawn.ebms.entity.Purchase;
import com.dawn.ebms.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findByUserIdEquals(Integer userId);
    List<Purchase> findByUserIdEqualsAndCreateTimeGreaterThanEqualAndCreateTimeLessThanEqual(Integer userId, Date startDate, Date endDate);

    List<Purchase> findByCreateTimeGreaterThanEqualAndCreateTimeLessThanEqual(Date startDate, Date endDate);

    @Modifying
    @Transactional
    void deleteByUserIdAndPurchaseId(Integer userId, Integer PurchaseId);
}
