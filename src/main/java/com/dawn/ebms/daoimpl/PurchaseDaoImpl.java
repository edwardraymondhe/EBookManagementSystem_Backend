package com.dawn.ebms.daoimpl;

import com.dawn.ebms.dao.PurchaseDao;
import com.dawn.ebms.dao.UserDao;
import com.dawn.ebms.entity.*;
import com.dawn.ebms.entity.Purchase;
import com.dawn.ebms.entity.PurchaseItem;
import com.dawn.ebms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class PurchaseDaoImpl implements PurchaseDao {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseItemRepository purchaseItemRepository;


    public List<Purchase> findPurchaseByUserIdEquals(Integer userId) { return purchaseRepository.findByUserIdEquals(userId);}

    public List<PurchaseItem> findPurchaseItemByPurchaseIdEquals(Integer purchaseId) { return purchaseItemRepository.findByPurchaseIdEquals(purchaseId);}

    public List<Purchase> updatePurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
        return findPurchaseByUserIdEquals(purchase.getUserId());
    }


    public Purchase initializePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public PurchaseItem updatePurchaseItem(PurchaseItem purchaseItem) {
        return purchaseItemRepository.save(purchaseItem);
    }

    public List<Purchase> findPurchaseByUserIdAndByRange(Integer id, Date startDate, Date endDate)
    {
        System.out.println(startDate);
        System.out.println(endDate);
        System.out.println(purchaseRepository.findByUserIdEquals(1).get(1).getCreateTime().getClass().toString());
        System.out.println(purchaseRepository.findByUserIdEquals(1).get(1).getCreateTime());
        return(purchaseRepository.findByUserIdEqualsAndCreateTimeGreaterThanEqualAndCreateTimeLessThanEqual(id, startDate, endDate));
    }

    public List<Purchase> findPurchaseByRange(Date startDate, Date endDate)
    {
        return (purchaseRepository.findByCreateTimeGreaterThanEqualAndCreateTimeLessThanEqual(startDate, endDate));
    }

    public List<List<PurchaseItem>> findPurchaseItemByPurchaseIds(List<Integer> purchases)
    {
        List<List<PurchaseItem>> itemList = new ArrayList<List<PurchaseItem>>();
        for(int i = 0; i < purchases.size(); i++) {
            System.out.println(purchaseItemRepository.findByPurchaseIdEquals(purchases.get(i)));
            itemList.add(purchaseItemRepository.findByPurchaseIdEquals(purchases.get(i)));
            System.out.println("itemList: " + itemList);
        }
        return itemList;
    }

    public List<Purchase> findAllPurchases()
    {
        return purchaseRepository.findAll();
    }
}
