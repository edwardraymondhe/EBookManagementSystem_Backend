package com.dawn.ebms.dao;

import com.dawn.ebms.entity.Purchase;
import com.dawn.ebms.entity.PurchaseItem;

import java.util.Date;
import java.util.List;

public interface PurchaseDao {

    List<Purchase> findPurchaseByUserIdEquals(Integer userId);
    List<PurchaseItem> findPurchaseItemByPurchaseIdEquals(Integer purchaseId);
    List<Purchase> updatePurchase(Purchase purchase);
    Purchase initializePurchase(Purchase purchase);
    PurchaseItem updatePurchaseItem(PurchaseItem purchaseItem);

    List<Purchase> findPurchaseByUserIdAndByRange(Integer id, Date startDate, Date endDate);

    List<Purchase> findPurchaseByRange(Date startDate, Date endDate);


    List<List<PurchaseItem>> findPurchaseItemByPurchaseIds(List<Integer> purchases);

    List<Purchase> findAllPurchases();

}
