package com.dawn.ebms.repository;

import com.dawn.ebms.entity.PurchaseItem;
import com.dawn.ebms.entity.PurchaseItemId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, PurchaseItemId> {
    List<PurchaseItem> findByPurchaseIdEquals(Integer purchaseId);
}
