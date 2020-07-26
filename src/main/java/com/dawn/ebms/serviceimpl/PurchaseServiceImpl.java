package com.dawn.ebms.serviceimpl;

import com.dawn.ebms.dao.BookDao;
import com.dawn.ebms.dao.CartDao;
import com.dawn.ebms.dao.PurchaseDao;

import com.dawn.ebms.entity.*;
import com.dawn.ebms.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseDao purchaseDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private CartDao cartDao;

    public List<Purchase> updatePurchase(Integer userId)
    {
        List<Cart> cartList = cartDao.findCartByUserIdEquals(userId);
        if(!cartList.isEmpty()) {
            double totPrice = 0;
            Purchase purchase = new Purchase();
            purchase.setUserId(userId);
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("Current created time is: " + date);
            purchase.setCreateTime(date);
            Purchase fetchPurchase = purchaseDao.initializePurchase(purchase);

            Integer purchaseId = fetchPurchase.getPurchaseId();

            for (int i = 0; i < cartList.size(); i++) {
                Cart cart = cartList.get(i);
                Integer quantity = cart.getQuantity();
                Integer bookId = cart.getBookId();

                Book fetchBook = bookDao.findBookByBookId(bookId);

                totPrice += fetchBook.getPrice() * quantity;

                PurchaseItem purchaseItem = new PurchaseItem();
                purchaseItem.setBookId(fetchBook.getBookId());
                purchaseItem.setPrice(fetchBook.getPrice());
                purchaseItem.setQuantity(quantity);
                purchaseItem.setPurchaseId(purchaseId);

                purchaseDao.updatePurchaseItem(purchaseItem);
            }
            fetchPurchase.setTotalPrice(totPrice);

            purchaseDao.updatePurchase(fetchPurchase);
            cartDao.deleteCart(userId);
        }

        return purchaseDao.findPurchaseByUserIdEquals(userId);
    }

    public List<Purchase> updatePurchaseItems(Integer userId, List<Integer> items) {
        System.out.println(cartDao.findCartByUserIdEquals(userId));
        if (!items.isEmpty()) {
            double totPrice = 0;
            Purchase purchase = new Purchase();
            purchase.setUserId(userId);
            System.out.println("Current created time is: ");
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("Current created time is: " + date);
            purchase.setCreateTime(date);
            Purchase fetchPurchase = purchaseDao.initializePurchase(purchase);

            Integer purchaseId = fetchPurchase.getPurchaseId();

            for (int i = 0; i < items.size(); i++) {
                Cart cart = cartDao.findCartByUserIdAndBookId(userId, items.get(i));
                Integer quantity = cart.getQuantity();

                Book fetchBook = bookDao.findBookByBookId(items.get(i));
                if(quantity <= fetchBook.getStock())
                    bookDao.setBookStockByBookId(fetchBook.getBookId(), fetchBook.getStock() - quantity);
                else
                    return null;

                totPrice += fetchBook.getPrice() * quantity;

                PurchaseItem purchaseItem = new PurchaseItem();
                purchaseItem.setBookId(fetchBook.getBookId());
                purchaseItem.setPrice(fetchBook.getPrice());
                purchaseItem.setQuantity(quantity);
                purchaseItem.setPurchaseId(purchaseId);

                purchaseDao.updatePurchaseItem(purchaseItem);
                cartDao.deleteCartItem(userId, items.get(i));
            }
            fetchPurchase.setTotalPrice(totPrice);
            purchaseDao.updatePurchase(fetchPurchase);
        }
        System.out.println(cartDao.findCartByUserIdEquals(userId));
        return purchaseDao.findPurchaseByUserIdEquals(userId);
    }


    public List<Purchase> findPurchaseByUserId(Integer userId)
    {
        return purchaseDao.findPurchaseByUserIdEquals(userId);
    }

    public List<Purchase> findPurchaseByUserIdAndByRange(Integer id, Date startDate, Date endDate)
    {
        return purchaseDao.findPurchaseByUserIdAndByRange(id, startDate, endDate);
    }

    public List<Purchase> findPurchaseByRange(Date startDate, Date endDate)
    {
        return purchaseDao.findPurchaseByRange(startDate, endDate);
    }


    public List<PurchaseItem> findPurchaseItemByPurchaseId(Integer id)
    {
        return purchaseDao.findPurchaseItemByPurchaseIdEquals(id);
    }

    public List<List<PurchaseItem>> findPurchaseItemByPurchaseIds(List<Integer> purchases)
    {
        return purchaseDao.findPurchaseItemByPurchaseIds(purchases);
    }

    public List<Purchase> findAllPurchases()
    {
        return purchaseDao.findAllPurchases();
    }

}
