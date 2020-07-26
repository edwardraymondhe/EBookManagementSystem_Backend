package com.dawn.ebms.service;

import com.dawn.ebms.entity.Purchase;
import com.dawn.ebms.entity.PurchaseItem;

import java.util.Date;
import java.util.List;

public interface PurchaseService {
    List<Purchase> updatePurchase(Integer userId);
    List<Purchase> findPurchaseByUserId(Integer userId);

    List<Purchase> updatePurchaseItems(Integer userId, List<Integer> items);

    List<Purchase> findPurchaseByUserIdAndByRange(Integer id, Date startDate, Date endDate);

    List<PurchaseItem> findPurchaseItemByPurchaseId(Integer purchaseId);

    List<List<PurchaseItem>> findPurchaseItemByPurchaseIds(List<Integer> purchases);

    List<Purchase> findAllPurchases();

    List<Purchase> findPurchaseByRange(Date startDate, Date endDate);
}
