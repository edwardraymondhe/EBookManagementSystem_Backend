package com.dawn.ebms.controller;

import com.dawn.ebms.entity.Purchase;
import com.dawn.ebms.entity.PurchaseItem;
import com.dawn.ebms.service.PurchaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @RequestMapping(value = "/updatePurchase")
    public List<Purchase> updatePurchase(@RequestParam("userId") Integer userId)
    {
        System.out.println("UserId: " + userId);
        return purchaseService.updatePurchase(userId);
    }

    @RequestMapping(value = "/updatePurchaseItems")
    public List<Purchase> updatePurchase(@RequestParam("userId") Integer userId, @RequestParam("checkedItems") String checkedItems) throws JsonProcessingException {
        System.out.println("UserId: " + userId);
        System.out.println("Items: " + checkedItems);
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        List<Integer> items = objectMapper.readValue(checkedItems, typeFactory.constructCollectionType(List.class, Integer.class));
        return purchaseService.updatePurchaseItems(userId, items);
    }

    @RequestMapping(value = "/getPurchase")
    public List<Purchase> findPurchaseByUserId(@RequestParam("userId") Integer id)
    {
        System.out.println("Get Purchase ... UserId: " + id);
        return purchaseService.findPurchaseByUserId(id);
    }

    @RequestMapping(value = "/getPurchaseByUserIdAndByRange")
    public List<Purchase> findPurchaseByUserIdAndByRange(@RequestParam("userId") Integer id, @RequestParam("startDate") String startDateStr, @RequestParam("endDate") String endDateStr) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date startDate = formatter.parse(startDateStr);
        Date endDate = formatter.parse(endDateStr);
        System.out.println(startDate);
        System.out.println(endDate);
        List<Purchase> list = purchaseService.findPurchaseByUserIdAndByRange(id, startDate, endDate);
        System.out.println(list);
        return list;
    }

    @RequestMapping(value = "/getPurchaseByRange")
    public List<Purchase> findPurchaseByRange(@RequestParam("startDate") String startDateStr, @RequestParam("endDate") String endDateStr) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date startDate = formatter.parse(startDateStr);
        Date endDate = formatter.parse(endDateStr);
        List<Purchase> list = purchaseService.findPurchaseByRange(startDate, endDate);
        System.out.println(list);
        return list;
    }

    @RequestMapping(value = "/getPurchaseItem")
    public List<PurchaseItem> findPurchaseItemByUserIdAndByPurchaseId(@RequestParam("purchaseId") Integer purchaseId)
    {
        List<PurchaseItem> list = purchaseService.findPurchaseItemByPurchaseId(purchaseId);
        System.out.println(list);
        return list;
    }

    @RequestMapping(value = "/getPurchaseItems")
    public List<List<PurchaseItem>> updatePurchase(@RequestParam("purchases") String purchases) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        List<Integer> items = objectMapper.readValue(purchases, typeFactory.constructCollectionType(List.class, Integer.class));
        System.out.println(items);
        return purchaseService.findPurchaseItemByPurchaseIds(items) ;
    }

    @RequestMapping(value = "/getAllPurchases")
    public List<Purchase> findAllPurchases()
    {
        return purchaseService.findAllPurchases();
    }


}
