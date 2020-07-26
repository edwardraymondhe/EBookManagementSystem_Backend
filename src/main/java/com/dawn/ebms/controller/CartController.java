package com.dawn.ebms.controller;

import com.dawn.ebms.entity.Cart;
import com.dawn.ebms.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/updateCart")
    public List<Cart> updateCart(@RequestParam("userId") Integer userId, @RequestParam("bookId") Integer bookId, @RequestParam("quantity") Integer quantity)
    {
        System.out.println("UserId: " + userId + " BookId: " + bookId + " Quantity: " + quantity);
        return cartService.updateCart(userId, bookId, quantity);
    }

    @RequestMapping(value = "/getCart")
    public List<Cart> findCartByUserId(@RequestParam("userId") Integer id)
    {
        return cartService.findCartByUserId(id);
    }
}
